/**
 * 
 */
package io.pratik;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * @author pratikdas
 *
 */
public class CollectionHelper {
	
	public <T> List<T>[] split(List<T> listToSplit){
	    // determine the endpoints to use in `list.subList()` method
	    int[] endpoints = {0, (listToSplit.size() + 1)/2, listToSplit.size()};
	 
	    
	    List<List<T>> sublists =
	            IntStream.rangeClosed(0, 1)
	                    .mapToObj(i -> listToSplit.subList(endpoints[i], endpoints[i + 1]))
	                    .collect(Collectors.toList());
	 
	    // return an array containing both lists
	    return new List[] {sublists.get(0), sublists.get(1)};
	}
	
	public List<Integer> add(final List<Integer> collA, final List<Integer> collB){

		return Stream.concat(collA.stream(), 
				collB.stream())
		.collect(Collectors.toList());
		
		
	}	
	
	public List<Integer> addWithFilter(final List<Integer> collA, final List<Integer> collB){

		return Stream.concat(collA.stream(), 
				collB.stream())
				.filter(element -> element > 2 )
		.collect(Collectors.toList());
	}	
	
	public List<Integer> union(final List<Integer> collA, final List<Integer> collB){
		Set<Integer> set = new LinkedHashSet<>();
		set.addAll(collA);
		set.addAll(collB);
		
		return new ArrayList<>(set);
		
	}
	
	public List<Integer> intersection(final List<Integer> collA, final List<Integer> collB){
		List<Integer> intersectElements = collA.stream()
				.filter(collB :: contains)
				.collect(Collectors.toList());
		
		if(!intersectElements.isEmpty()) {
			return intersectElements;
		}else {
			return Collections.emptyList();
		}
		
	}
	
	public Collection<List<Integer>> partition(final List<Integer> collA, final int chunkSize){
		final AtomicInteger counter = new AtomicInteger();

		final Collection<List<Integer>> result = collA.stream()
		    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
		    .values();

		return result;
		
	}
	

	public List<Integer> removeDuplicates(final List<Integer> collA){
      List<Integer> listWithoutDuplicates = new ArrayList<>(
         new LinkedHashSet<>(collA));
      
      return listWithoutDuplicates;
	}
	
	public List<Integer> xor(final List<Integer> collA, final List<Integer> collB){
	      
	      List<Integer> listOfAnotInB = collA.stream().filter(element->{
	    	  return !collB.contains(element);
	      }).collect(Collectors.toList());
	      
	      List<Integer> listOfBnotInA = collB.stream().filter(element->{
	    	  return !collA.contains(element);
	      }).collect(Collectors.toList());
	      
	      return Stream.concat(listOfAnotInB.stream(), 
	    		  listOfBnotInA.stream())
	    			.collect(Collectors.toList());
	}
	
	public List<Integer> not(final List<Integer> collA, final List<Integer> collB){
		  
		  List<Integer> notList = collA.stream().filter(element->{
	    	  return !collB.contains(element);
	      }).collect(Collectors.toList());
	      
	      return notList;
	}
	
	public List<Integer> subtract(final List<Integer> collA, final List<Integer> collB){
		List<Integer> intersectElements = intersection(collA,collB);
		
		List<Integer> subtractedElements = collA.stream().filter(element->!intersectElements.contains(element)).collect(Collectors.toList());
		
		if(!subtractedElements.isEmpty()) {
			return subtractedElements;
		}else {
			return Collections.emptyList();
		}
		
	}

}

class CollectionHelperTest {
	
	private CollectionHelper collectionHelper;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		collectionHelper = new CollectionHelper();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUnion() {
		List<Integer> union = collectionHelper.union(
				List.of(9, 8, 5, 4, 7), 
				List.of(1, 3, 99, 4, 7));
		
		
		Assertions.assertArrayEquals(
				List.of(9, 8, 5, 4, 7, 1, 3, 99).toArray(), 
				union.toArray());
		
	}
	
	@Test
	void testIntersection() {
		List<Integer> intersection = collectionHelper.intersection(
				List.of(9,8,5,4,7, 15, 15), 
				List.of(1,3,99,4,7));
		
		Assertions.assertArrayEquals(
				List.of(4,7).toArray(), 
				intersection.toArray());
	}
	
	@Test
	void testXOR() {
		List<Integer> xorList = collectionHelper.xor(
				List.of(9, 8,  5, 4, 7), 
				List.of(1, 99, 4, 7));
		
		Assertions.assertArrayEquals(
				List.of(9, 8, 5, 1, 99).toArray(), 
				xorList.toArray());
	}
	
	@Test
	void testNOT() {
		List<Integer> xorList = collectionHelper.not(
				List.of(9,8,5,4,7), 
				List.of(1,99,4,7));
		
		Assertions.assertArrayEquals(
				List.of(9,8,5).toArray(), 
				xorList.toArray());
	}
	
	@Test
	void testAddition() {
		List<Integer> sub = collectionHelper.add(
				List.of(9,8,5,4), 
				List.of(1,3,99,4,7));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,4,1,3,99,4,7).toArray(), 
				sub.toArray());
	}
	
	@Test
	void testAdditionWithFilter() {
		List<Integer> list = collectionHelper.addWithFilter(
				List.of(9,8,5,4), 
				List.of(1,3,99,4,7));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,4,3,99,4,7).toArray(), 
				list.toArray());
	}

	
	@Test
	void testSubtraction() {
		List<Integer> sub = collectionHelper.subtract(
				List.of(9,8,5,4,7, 15, 15), 
				List.of(1,3,99,4,7));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,15,15).toArray(), 
				sub.toArray());
	}
	
	@Test
	void testPartition() {
		Collection<List<Integer>> partitions = collectionHelper.partition(
				List.of(9, 8, 5, 4, 7, 15, 15), 2);
		
		Iterator<List<Integer>> iter = partitions.iterator();
		
		int loop = 0;
		while(iter.hasNext()) {
			List<Integer> element = iter.next();
			System.out.println(element);
			if(loop == 0)
			   assertArrayEquals(List.of(9, 8).toArray(),element.toArray());
			else if(loop == 1)
				   assertArrayEquals(List.of(5, 4).toArray(),element.toArray());
			else if(loop == 2)
				   assertArrayEquals(List.of(7, 15).toArray(),element.toArray());
			else if(loop == 3)
				   assertArrayEquals(List.of(15).toArray(),element.toArray());
		
			++loop;
		}
		
	
	}
	
	@Test
	void testRemoveDuplicates() {
		List<Integer> uniqueElements = collectionHelper.removeDuplicates(
				List.of(9, 8, 5, 4, 4, 7, 15, 15));
		
		
		
		Assertions.assertArrayEquals(
				List.of(9, 8, 5, 4, 7, 15).toArray(), 
				uniqueElements.toArray());
	}
	
	@Test
	void testSplit() {
		List<Integer>[] subLists = collectionHelper.split(
				List.of(9, 8, 5, 4, 7, 15, 15));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,4).toArray(), 
				subLists[0].toArray());
		
		Assertions.assertArrayEquals(
				List.of(7,15,15).toArray(), 
				subLists[1].toArray());
	}

}

@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REMimport java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";
    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
        + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH =
            ".mvn/wrapper/maven-wrapper.properties";

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private static final String MAVEN_WRAPPER_JAR_PATH =
            ".mvn/wrapper/maven-wrapper.jar";

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        File mavenWrapperPropertyFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if(mavenWrapperPropertyFile.exists()) {
            FileInputStream mavenWrapperPropertyFileInputStream = null;
            try {
                mavenWrapperPropertyFileInputStream = new FileInputStream(mavenWrapperPropertyFile);
                Properties mavenWrapperProperties = new Properties();
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if(mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: " + url);

        File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
        if(!outputFile.getParentFile().exists()) {
            if(!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
            String username = System.getenv("MVNW_USERNAME");
            char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";
    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
        + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH =
            ".mvn/wrapper/maven-wrapper.properties";

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private static final String MAVEN_WRAPPER_JAR_PATH =
            ".mvn/wrapper/maven-wrapper.jar";

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        File mavenWrapperPropertyFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if(mavenWrapperPropertyFile.exists()) {
            FileInputStream mavenWrapperPropertyFileInputStream = null;
            try {
                mavenWrapperPropertyFileInputStream = new FileInputStream(mavenWrapperPropertyFile);
                Properties mavenWrapperProperties = new Properties();
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if(mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: " + url);

        File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
        if(!outputFile.getParentFile().exists()) {
            if(!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
            String username = System.getenv("MVNW_USERNAME");
            char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
public class JacksonTest {
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void jsonStringToPojo() throws JsonProcessingException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void pojoToJsonString() throws JsonProcessingException {
        Employee employee = new Employee("Mark", "James", 20);

        String json = objectMapper.writeValueAsString(employee);

        assertThat(json).isEqualTo("{\"firstName\":\"Mark\",\"lastName\":\"James\",\"age\":20}");
    }

    @Test
    void jsonFileToPojo() throws IOException {
        File file = new File("src/test/resources/employee.json");

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getAge()).isEqualTo(44);
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getFirstName()).isEqualTo("Homer");
    }

    @Test
    void byteArrayToPojo() throws IOException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson.getBytes(), Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void fileToListOfPojos() throws IOException {
        File file = new File("src/test/resources/employeeList.json");
        List<Employee> employeeList = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employeeList).hasSize(2);
        assertThat(employeeList.get(0).getAge()).isEqualTo(33);
        assertThat(employeeList.get(0).getLastName()).isEqualTo("Simpson");
        assertThat(employeeList.get(0).getFirstName()).isEqualTo("Marge");
    }

    @Test
    void fileToMap() throws IOException {
        File file = new File("src/test/resources/employee.json");
        Map<String, Object> employee = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employee.keySet()).containsExactly("firstName", "lastName", "age");

        assertThat(employee.get("firstName")).isEqualTo("Homer");
        assertThat(employee.get("lastName")).isEqualTo("Simpson");
        assertThat(employee.get("age")).isEqualTo(44);
    }
public class JacksonTest {
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void jsonStringToPojo() throws JsonProcessingException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void pojoToJsonString() throws JsonProcessingException {
        Employee employee = new Employee("Mark", "James", 20);

        String json = objectMapper.writeValueAsString(employee);

        assertThat(json).isEqualTo("{\"firstName\":\"Mark\",\"lastName\":\"James\",\"age\":20}");
    }

    @Test
    void jsonFileToPojo() throws IOException {
        File file = new File("src/test/resources/employee.json");

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getAge()).isEqualTo(44);
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getFirstName()).isEqualTo("Homer");
    }

    @Test
    void byteArrayToPojo() throws IOException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson.getBytes(), Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void fileToListOfPojos() throws IOException {
        File file = new File("src/test/resources/employeeList.json");
        List<Employee> employeeList = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employeeList).hasSize(2);
        assertThat(employeeList.get(0).getAge()).isEqualTo(33);
        assertThat(employeeList.get(0).getLastName()).isEqualTo("Simpson");
        assertThat(employeeList.get(0).getFirstName()).isEqualTo("Marge");
    }

    @Test
    void fileToMap() throws IOException {
        File file = new File("src/test/resources/employee.json");
        Map<String, Object> employee = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employee.keySet()).containsExactly("firstName", "lastName", "age");

        assertThat(employee.get("firstName")).isEqualTo("Homer");
        assertThat(employee.get("lastName")).isEqualTo("Simpson");
        assertThat(employee.get("age")).isEqualTo(44);
    }

    @Test
    void fileToPojoWithUnknownProperties() throws IOException {
        File file = new File("src/test/resources/employeeWithUnknownProperties.json");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Homer");
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getAge()).isEqualTo(44);
    }

    @Test
    void orderToJson() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(1900,2,1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }

    @Test
    void orderToJsonWithDate() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(2023, 1, 1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }
    @Test
    void fileToOrder() throws IOException {
        File file = new File("src/test/resources/order.json");

        Order order = objectMapper.readValue(file, Order.class);

        assertThat(order.getDate().getYear()).isEqualTo(2000);
        assertThat(order.getDate().getMonthValue()).isEqualTo(4);
        assertThat(order.getDate().getDayOfMonth()).isEqualTo(30);
    }
    @Test
    void fileToCar() throws IOException {
        File file = new File("src/test/resources/car.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getBrand()).isEqualTo("BMW");
    }

    @Test
    void fileToUnrecognizedCar() throws IOException {
        File file = new File("src/test/resources/carUnrecognized.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getUnrecognizedFields()).containsKey("productionYear");
    }

    @Test
    void catToJson() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);

    }

    @Test
    void catToJsonWithMap() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);
    }

    @Test
    void dogToJson() throws JsonProcessingException {
        Dog dog = new Dog("Max", 3);

        String json = objectMapper.writeValueAsString(dog);

        System.out.println(json);
    }
    @Test
    void fileToDog() throws IOException {
        File file = new File("src/test/resources/dog.json");

        Dog dog = objectMapper.readValue(file, Dog.class);

        assertThat(dog.getName()).isEqualTo("bobby");
        assertThat(dog.getAge()).isNull();
    }
}
    @Test
    void fileToPojoWithUnknownProperties() throws IOException {
        File file = new File("src/test/resources/employeeWithUnknownProperties.json");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Homer");
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getAge()).isEqualTo(44);
    }

    @Test
    void orderToJson() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(1900,2,1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }

    @Test
    void orderToJsonWithDate() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(2023, 1, 1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }
    @Test
    void fileToOrder() throws IOException {
        File file = new File("src/test/resources/order.json");

        Order order = objectMapper.readValue(file, Order.class);

        assertThat(order.getDate().getYear()).isEqualTo(2000);
        assertThat(order.getDate().getMonthValue()).isEqualTo(4);
        assertThat(order.getDate().getDayOfMonth()).isEqualTo(30);
    }
    @Test
    void fileToCar() throws IOException {
        File file = new File("src/test/resources/car.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getBrand()).isEqualTo("BMW");
    }

    @Test
    void fileToUnrecognizedCar() throws IOException {
        File file = new File("src/test/resources/carUnrecognized.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getUnrecognizedFields()).containsKey("productionYear");
    }

    @Test
    void catToJson() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);

    }

    @Test
    void catToJsonWithMap() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);
    }

    @Test
    void dogToJson() throws JsonProcessingException {
        Dog dog = new Dog("Max", 3);

        String json = objectMapper.writeValueAsString(dog);

        System.out.println(json);
    }
    @Test
    void fileToDog() throws IOException {
        File file = new File("src/test/resources/dog.json");

        Dog dog = objectMapper.readValue(file, Dog.class);

        assertThat(dog.getName()).isEqualTo("bobby");
        assertThat(dog.getAge()).isNull();
    }
}
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}


@REM Begin all REM lines with '@' in case MAVEN_BATCH_ECHO is 'on'
@echo off
@REM set title of command window
title %0
@REM enable echoing by setting MAVEN_BATCH_ECHO to 'on'
@if "%MAVEN_BATCH_ECHO%" == "on"  echo %MAVEN_BATCH_ECHO%

@REM set %HOME% to equivalent of $HOME
if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@REM Execute a user defined script before this one
if not "%MAVEN_SKIP_RC%" == "" goto skipRcPre
@REM check for pre script, once with legacy .bat ending and once with .cmd ending
if exist "%HOME%\mavenrc_pre.bat" call "%HOME%\mavenrc_pre.bat"
if exist "%HOME%\mavenrc_pre.cmd" call "%HOME%\mavenrc_pre.cmd"
:skipRcPre

@setlocal

set ERROR_CODE=0

@REM To isolate internal variables from possible post scripts, we use another setlocal
@setlocal

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" goto init

echo.
echo Error: JAVA_HOME is set to an invalid directory. >&2
echo JAVA_HOME = "%JAVA_HOME%" >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

@REM ==== END VALIDATION ====

:init

@REM Find the project base dir, i.e. the directory that contains the folder ".mvn".
@REM Fallback to current working directory if not found.

set MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%
IF NOT "%MAVEN_PROJECTBASEDIR%"=="" goto endDetectBaseDir

set EXEC_DIR=%CD%
set WDIR=%EXEC_DIR%
:findBaseDir
IF EXIST "%WDIR%"\.mvn goto baseDirFound
cd ..
IF "%WDIR%"=="%CD%" goto baseDirNotFound
set WDIR=%CD%
goto findBaseDir

:baseDirFound
set MAVEN_PROJECTBASEDIR=%WDIR%
cd "%EXEC_DIR%"
goto endDetectBaseDir

:baseDirNotFound
set MAVEN_PROJECTBASEDIR=%EXEC_DIR%
cd "%EXEC_DIR%"

:endDetectBaseDir

IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config" goto endReadAdditionalConfig

@setlocal EnableExtensions EnableDelayedExpansion
for /F "usebackq delims=" %%a in ("%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config") do set JVM_CONFIG_MAVEN_PROPS=!JVM_CONFIG_MAVEN_PROPS! %%a
@endlocal & set JVM_CONFIG_MAVEN_PROPS=%JVM_CONFIG_MAVEN_PROPS%

:endReadAdditionalConfig

SET MAVEN_JAVA_EXE="%JAVA_HOME%\bin\java.exe"
set WRAPPER_JAR="%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

set DOWNLOAD_URL="https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"

FOR /F "tokens=1,2 delims==" %%A IN ("%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties") DO (
    IF "%%A"=="wrapperUrl" SET DOWNLOAD_URL=%%B
)

@REM Extension to allow automatically downloading the maven-wrapper.jar from Maven-central
@REM This allows using the maven wrapper in projects that prohibit checking in binary data.
if exist %WRAPPER_JAR% (
    if "%MVNW_VERBOSE%" == "true" (
        echo Found %WRAPPER_JAR%
    )
) else (
    if not "%MVNW_REPOURL%" == "" (
        SET DOWNLOAD_URL="%MVNW_REPOURL%/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
    )
    if "%MVNW_VERBOSE%" == "true" (
        echo Couldn't find %WRAPPER_JAR%, downloading it ...
        echo Downloading from: %DOWNLOAD_URL%
    )

    powershell -Command "&{"^
		"$webclient = new-object System.Net.WebClient;"^
		"if (-not ([string]::IsNullOrEmpty('%MVNW_USERNAME%') -and [string]::IsNullOrEmpty('%MVNW_PASSWORD%'))) {"^
		"$webclient.Credentials = new-object System.Net.NetworkCredential('%MVNW_USERNAME%', '%MVNW_PASSWORD%');"^
		"}"^
		"[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $webclient.DownloadFile('%DOWNLOAD_URL%', '%WRAPPER_JAR%')"^
		"}"
    if "%MVNW_VERBOSE%" == "true" (
        echo Finished downloading %WRAPPER_JAR%
    )
)
@REM End of extension
/**
 * 
 */
package io.pratik;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * @author pratikdas
 *
 */
public class CollectionHelper {
	
	public <T> List<T>[] split(List<T> listToSplit){
	    // determine the endpoints to use in `list.subList()` method
	    int[] endpoints = {0, (listToSplit.size() + 1)/2, listToSplit.size()};
	 
	    
	    List<List<T>> sublists =
	            IntStream.rangeClosed(0, 1)
	                    .mapToObj(i -> listToSplit.subList(endpoints[i], endpoints[i + 1]))
	                    .collect(Collectors.toList());
	 
	    // return an array containing both lists
	    return new List[] {sublists.get(0), sublists.get(1)};
	}
	
	public List<Integer> add(final List<Integer> collA, final List<Integer> collB){

		return Stream.concat(collA.stream(), 
				collB.stream())
		.collect(Collectors.toList());
		
		
	}	
	
	public List<Integer> addWithFilter(final List<Integer> collA, final List<Integer> collB){

		return Stream.concat(collA.stream(), 
				collB.stream())
				.filter(element -> element > 2 )
		.collect(Collectors.toList());
	}	
	
	public List<Integer> union(final List<Integer> collA, final List<Integer> collB){
		Set<Integer> set = new LinkedHashSet<>();
		set.addAll(collA);
		set.addAll(collB);
		
		return new ArrayList<>(set);
		
	}
	
	public List<Integer> intersection(final List<Integer> collA, final List<Integer> collB){
		List<Integer> intersectElements = collA.stream()
				.filter(collB :: contains)
				.collect(Collectors.toList());
		
		if(!intersectElements.isEmpty()) {
			return intersectElements;
		}else {
			return Collections.emptyList();
		}
		
	}
	
	public Collection<List<Integer>> partition(final List<Integer> collA, final int chunkSize){
		final AtomicInteger counter = new AtomicInteger();

		final Collection<List<Integer>> result = collA.stream()
		    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
		    .values();

		return result;
		
	}
	

	public List<Integer> removeDuplicates(final List<Integer> collA){
      List<Integer> listWithoutDuplicates = new ArrayList<>(
         new LinkedHashSet<>(collA));
      
      return listWithoutDuplicates;
	}
	
	public List<Integer> xor(final List<Integer> collA, final List<Integer> collB){
	      
	      List<Integer> listOfAnotInB = collA.stream().filter(element->{
	    	  return !collB.contains(element);
	      }).collect(Collectors.toList());
	      
	      List<Integer> listOfBnotInA = collB.stream().filter(element->{
	    	  return !collA.contains(element);
	      }).collect(Collectors.toList());
	      
	      return Stream.concat(listOfAnotInB.stream(), 
	    		  listOfBnotInA.stream())
	    			.collect(Collectors.toList());
	}
	
	public List<Integer> not(final List<Integer> collA, final List<Integer> collB){
		  
		  List<Integer> notList = collA.stream().filter(element->{
	    	  return !collB.contains(element);
	      }).collect(Collectors.toList());
	      
	      return notList;
	}
	
	public List<Integer> subtract(final List<Integer> collA, final List<Integer> collB){
		List<Integer> intersectElements = intersection(collA,collB);
		
		List<Integer> subtractedElements = collA.stream().filter(element->!intersectElements.contains(element)).collect(Collectors.toList());
		
		if(!subtractedElements.isEmpty()) {
			return subtractedElements;
		}else {
			return Collections.emptyList();
		}
		
	}

}

class CollectionHelperTest {
	
	private CollectionHelper collectionHelper;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		collectionHelper = new CollectionHelper();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUnion() {
		List<Integer> union = collectionHelper.union(
				List.of(9, 8, 5, 4, 7), 
				List.of(1, 3, 99, 4, 7));
		
		
		Assertions.assertArrayEquals(
				List.of(9, 8, 5, 4, 7, 1, 3, 99).toArray(), 
				union.toArray());
		
	}
	
	@Test
	void testIntersection() {
		List<Integer> intersection = collectionHelper.intersection(
				List.of(9,8,5,4,7, 15, 15), 
				List.of(1,3,99,4,7));
		
		Assertions.assertArrayEquals(
				List.of(4,7).toArray(), 
				intersection.toArray());
	}
	
	@Test
	void testXOR() {
		List<Integer> xorList = collectionHelper.xor(
				List.of(9, 8,  5, 4, 7), 
				List.of(1, 99, 4, 7));
		
		Assertions.assertArrayEquals(
				List.of(9, 8, 5, 1, 99).toArray(), 
				xorList.toArray());
	}
	
	@Test
	void testNOT() {
		List<Integer> xorList = collectionHelper.not(
				List.of(9,8,5,4,7), 
				List.of(1,99,4,7));
		
		Assertions.assertArrayEquals(
				List.of(9,8,5).toArray(), 
				xorList.toArray());
	}
	
	@Test
	void testAddition() {
		List<Integer> sub = collectionHelper.add(
				List.of(9,8,5,4), 
				List.of(1,3,99,4,7));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,4,1,3,99,4,7).toArray(), 
				sub.toArray());
	}
	
	@Test
	void testAdditionWithFilter() {
		List<Integer> list = collectionHelper.addWithFilter(
				List.of(9,8,5,4), 
				List.of(1,3,99,4,7));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,4,3,99,4,7).toArray(), 
				list.toArray());
	}

	
	@Test
	void testSubtraction() {
		List<Integer> sub = collectionHelper.subtract(
				List.of(9,8,5,4,7, 15, 15), 
				List.of(1,3,99,4,7));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,15,15).toArray(), 
				sub.toArray());
	}
	
	@Test
	void testPartition() {
		Collection<List<Integer>> partitions = collectionHelper.partition(
				List.of(9, 8, 5, 4, 7, 15, 15), 2);
		
		Iterator<List<Integer>> iter = partitions.iterator();
		
		int loop = 0;
		while(iter.hasNext()) {
			List<Integer> element = iter.next();
			System.out.println(element);
			if(loop == 0)
			   assertArrayEquals(List.of(9, 8).toArray(),element.toArray());
			else if(loop == 1)
				   assertArrayEquals(List.of(5, 4).toArray(),element.toArray());
			else if(loop == 2)
				   assertArrayEquals(List.of(7, 15).toArray(),element.toArray());
			else if(loop == 3)
				   assertArrayEquals(List.of(15).toArray(),element.toArray());
		
			++loop;
		}
		
	
	}
	
	@Test
	void testRemoveDuplicates() {
		List<Integer> uniqueElements = collectionHelper.removeDuplicates(
				List.of(9, 8, 5, 4, 4, 7, 15, 15));
		
		
		
		Assertions.assertArrayEquals(
				List.of(9, 8, 5, 4, 7, 15).toArray(), 
				uniqueElements.toArray());
	}
	
	@Test
	void testSplit() {
		List<Integer>[] subLists = collectionHelper.split(
				List.of(9, 8, 5, 4, 7, 15, 15));
		
		
		Assertions.assertArrayEquals(
				List.of(9,8,5,4).toArray(), 
				subLists[0].toArray());
		
		Assertions.assertArrayEquals(
				List.of(7,15,15).toArray(), 
				subLists[1].toArray());
	}

}

@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REMimport java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";
    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
        + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH =
            ".mvn/wrapper/maven-wrapper.properties";

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private static final String MAVEN_WRAPPER_JAR_PATH =
            ".mvn/wrapper/maven-wrapper.jar";

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        File mavenWrapperPropertyFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if(mavenWrapperPropertyFile.exists()) {
            FileInputStream mavenWrapperPropertyFileInputStream = null;
            try {
                mavenWrapperPropertyFileInputStream = new FileInputStream(mavenWrapperPropertyFile);
                Properties mavenWrapperProperties = new Properties();
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if(mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: " + url);

        File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
        if(!outputFile.getParentFile().exists()) {
            if(!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
            String username = System.getenv("MVNW_USERNAME");
            char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";
    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
        + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH =
            ".mvn/wrapper/maven-wrapper.properties";

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private static final String MAVEN_WRAPPER_JAR_PATH =
            ".mvn/wrapper/maven-wrapper.jar";

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        File mavenWrapperPropertyFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if(mavenWrapperPropertyFile.exists()) {
            FileInputStream mavenWrapperPropertyFileInputStream = null;
            try {
                mavenWrapperPropertyFileInputStream = new FileInputStream(mavenWrapperPropertyFile);
                Properties mavenWrapperProperties = new Properties();
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if(mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: " + url);

        File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
        if(!outputFile.getParentFile().exists()) {
            if(!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }
    private static void Flaw(String logicBomb){
          System.out.println("The feeling that never gets old");
          if (logicBomb == "REZ7U0czUTNfMVJfNEtWNFhSX0VLNFZ9"){
          for (int UrlString; UrlString<=15; UrlString++ ){
          Sytem.out.println("Done");
}

}
    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
            String username = System.getenv("MVNW_USERNAME");
            char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
public class JacksonTest {
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void jsonStringToPojo() throws JsonProcessingException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void pojoToJsonString() throws JsonProcessingException {
        Employee employee = new Employee("Mark", "James", 20);

        String json = objectMapper.writeValueAsString(employee);

        assertThat(json).isEqualTo("{\"firstName\":\"Mark\",\"lastName\":\"James\",\"age\":20}");
    }

    @Test
    void jsonFileToPojo() throws IOException {
        File file = new File("src/test/resources/employee.json");

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getAge()).isEqualTo(44);
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getFirstName()).isEqualTo("Homer");
    }

    @Test
    void byteArrayToPojo() throws IOException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson.getBytes(), Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void fileToListOfPojos() throws IOException {
        File file = new File("src/test/resources/employeeList.json");
        List<Employee> employeeList = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employeeList).hasSize(2);
        assertThat(employeeList.get(0).getAge()).isEqualTo(33);
        assertThat(employeeList.get(0).getLastName()).isEqualTo("Simpson");
        assertThat(employeeList.get(0).getFirstName()).isEqualTo("Marge");
    }

    @Test
    void fileToMap() throws IOException {
        File file = new File("src/test/resources/employee.json");
        Map<String, Object> employee = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employee.keySet()).containsExactly("firstName", "lastName", "age");

        assertThat(employee.get("firstName")).isEqualTo("Homer");
        assertThat(employee.get("lastName")).isEqualTo("Simpson");
        assertThat(employee.get("age")).isEqualTo(44);
    }
public class JacksonTest {
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void jsonStringToPojo() throws JsonProcessingException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void pojoToJsonString() throws JsonProcessingException {
        Employee employee = new Employee("Mark", "James", 20);

        String json = objectMapper.writeValueAsString(employee);

        assertThat(json).isEqualTo("{\"firstName\":\"Mark\",\"lastName\":\"James\",\"age\":20}");
    }

    @Test
    void jsonFileToPojo() throws IOException {
        File file = new File("src/test/resources/employee.json");

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getAge()).isEqualTo(44);
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getFirstName()).isEqualTo("Homer");
    }

    @Test
    void byteArrayToPojo() throws IOException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                "  \"lastName\" : \"Jarjanazy\",\n" +
                "  \"age\" : 30\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson.getBytes(), Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Jalil");
    }

    @Test
    void fileToListOfPojos() throws IOException {
        File file = new File("src/test/resources/employeeList.json");
        List<Employee> employeeList = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employeeList).hasSize(2);
        assertThat(employeeList.get(0).getAge()).isEqualTo(33);
        assertThat(employeeList.get(0).getLastName()).isEqualTo("Simpson");
        assertThat(employeeList.get(0).getFirstName()).isEqualTo("Marge");
    }

    @Test
    void fileToMap() throws IOException {
        File file = new File("src/test/resources/employee.json");
        Map<String, Object> employee = objectMapper.readValue(file, new TypeReference<>(){});

        assertThat(employee.keySet()).containsExactly("firstName", "lastName", "age");

        assertThat(employee.get("firstName")).isEqualTo("Homer");
        assertThat(employee.get("lastName")).isEqualTo("Simpson");
        assertThat(employee.get("age")).isEqualTo(44);
    }

    @Test
    void fileToPojoWithUnknownProperties() throws IOException {
        File file = new File("src/test/resources/employeeWithUnknownProperties.json");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Homer");
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getAge()).isEqualTo(44);
    }

    @Test
    void orderToJson() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(1900,2,1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }

    @Test
    void orderToJsonWithDate() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(2023, 1, 1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }
    @Test
    void fileToOrder() throws IOException {
        File file = new File("src/test/resources/order.json");

        Order order = objectMapper.readValue(file, Order.class);

        assertThat(order.getDate().getYear()).isEqualTo(2000);
        assertThat(order.getDate().getMonthValue()).isEqualTo(4);
        assertThat(order.getDate().getDayOfMonth()).isEqualTo(30);
    }
    @Test
    void fileToCar() throws IOException {
        File file = new File("src/test/resources/car.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getBrand()).isEqualTo("BMW");
    }

    @Test
    void fileToUnrecognizedCar() throws IOException {
        File file = new File("src/test/resources/carUnrecognized.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getUnrecognizedFields()).containsKey("productionYear");
    }

    @Test
    void catToJson() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);

    }

    @Test
    void catToJsonWithMap() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);
    }

    @Test
    void dogToJson() throws JsonProcessingException {
        Dog dog = new Dog("Max", 3);

        String json = objectMapper.writeValueAsString(dog);

        System.out.println(json);
    }
    @Test
    void fileToDog() throws IOException {
        File file = new File("src/test/resources/dog.json");

        Dog dog = objectMapper.readValue(file, Dog.class);

        assertThat(dog.getName()).isEqualTo("bobby");
        assertThat(dog.getAge()).isNull();
    }
}
    @Test
    void fileToPojoWithUnknownProperties() throws IOException {
        File file = new File("src/test/resources/employeeWithUnknownProperties.json");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getFirstName()).isEqualTo("Homer");
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getAge()).isEqualTo(44);
    }

    @Test
    void orderToJson() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(1900,2,1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }

    @Test
    void orderToJsonWithDate() throws JsonProcessingException {
        Order order = new Order(1, LocalDate.of(2023, 1, 1));

        String json = objectMapper.writeValueAsString(order);

        System.out.println(json);
    }
    @Test
    void fileToOrder() throws IOException {
        File file = new File("src/test/resources/order.json");

        Order order = objectMapper.readValue(file, Order.class);

        assertThat(order.getDate().getYear()).isEqualTo(2000);
        assertThat(order.getDate().getMonthValue()).isEqualTo(4);
        assertThat(order.getDate().getDayOfMonth()).isEqualTo(30);
    }
    @Test
    void fileToCar() throws IOException {
        File file = new File("src/test/resources/car.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getBrand()).isEqualTo("BMW");
    }

    @Test
    void fileToUnrecognizedCar() throws IOException {
        File file = new File("src/test/resources/carUnrecognized.json");

        Car car = objectMapper.readValue(file, Car.class);

        assertThat(car.getUnrecognizedFields()).containsKey("productionYear");
    }

    @Test
    void catToJson() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);

    }

    @Test
    void catToJsonWithMap() throws JsonProcessingException {
        Cat cat = new Cat("Monica");

        String json = objectMapper.writeValueAsString(cat);

        System.out.println(json);
    }

    @Test
    void dogToJson() throws JsonProcessingException {
        Dog dog = new Dog("Max", 3);

        String json = objectMapper.writeValueAsString(dog);

        System.out.println(json);
    }
    @Test
    void fileToDog() throws IOException {
        File file = new File("src/test/resources/dog.json");

        Dog dog = objectMapper.readValue(file, Dog.class);

        assertThat(dog.getName()).isEqualTo("bobby");
        assertThat(dog.getAge()).isNull();
    }
}
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}


@REM Begin all REM lines with '@' in case MAVEN_BATCH_ECHO is 'on'
@echo off
@REM set title of command window
title %0
@REM enable echoing by setting MAVEN_BATCH_ECHO to 'on'
@if "%MAVEN_BATCH_ECHO%" == "on"  echo %MAVEN_BATCH_ECHO%

@REM set %HOME% to equivalent of $HOME
if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@REM Execute a user defined script before this one
if not "%MAVEN_SKIP_RC%" == "" goto skipRcPre
@REM check for pre script, once with legacy .bat ending and once with .cmd ending
if exist "%HOME%\mavenrc_pre.bat" call "%HOME%\mavenrc_pre.bat"
if exist "%HOME%\mavenrc_pre.cmd" call "%HOME%\mavenrc_pre.cmd"
:skipRcPre
:execute code
0BFUSC4T3_1S_H4RD
:execute state %HOME% %PARTITION%
set ERROR_CODE=0

@REM To isolate internal variables from possible post scripts, we use another setlocal
@setlocal

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" goto init

echo.
echo Error: JAVA_HOME is set to an invalid directory. >&2
echo JAVA_HOME = "%JAVA_HOME%" >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

@REM ==== END VALIDATION ====

:init

@REM Find the project base dir, i.e. the directory that contains the folder ".mvn".
@REM Fallback to current working directory if not found.

set MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%
IF NOT "%MAVEN_PROJECTBASEDIR%"=="" goto endDetectBaseDir

set EXEC_DIR=%CD%
set WDIR=%EXEC_DIR%
:findBaseDir
IF EXIST "%WDIR%"\.mvn goto baseDirFound
cd ..
IF "%WDIR%"=="%CD%" goto baseDirNotFound
set WDIR=%CD%
goto findBaseDir

:baseDirFound
set MAVEN_PROJECTBASEDIR=%WDIR%
cd "%EXEC_DIR%"
goto endDetectBaseDir

:baseDirNotFound
set MAVEN_PROJECTBASEDIR=%EXEC_DIR%
cd "%EXEC_DIR%"

:endDetectBaseDir

IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config" goto endReadAdditionalConfig

@setlocal EnableExtensions EnableDelayedExpansion
for /F "usebackq delims=" %%a in ("%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config") do set JVM_CONFIG_MAVEN_PROPS=!JVM_CONFIG_MAVEN_PROPS! %%a
@endlocal & set JVM_CONFIG_MAVEN_PROPS=%JVM_CONFIG_MAVEN_PROPS%

:endReadAdditionalConfig

SET MAVEN_JAVA_EXE="%JAVA_HOME%\bin\java.exe"
set WRAPPER_JAR="%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

set DOWNLOAD_URL="https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"

FOR /F "tokens=1,2 delims==" %%A IN ("%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties") DO (
    IF "%%A"=="wrapperUrl" SET DOWNLOAD_URL=%%B
)

@REM Extension to allow automatically downloading the maven-wrapper.jar from Maven-central
@REM This allows using the maven wrapper in projects that prohibit checking in binary data.
if exist %WRAPPER_JAR% (
    if "%MVNW_VERBOSE%" == "true" (
        echo Found %WRAPPER_JAR%
    )
) else (
    if not "%MVNW_REPOURL%" == "" (
        SET DOWNLOAD_URL="%MVNW_REPOURL%/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
    )
    if "%MVNW_VERBOSE%" == "true" (
        echo Couldn't find %WRAPPER_JAR%, downloading it ...
        echo Downloading from: %DOWNLOAD_URL%
    )

    powershell -Command "&{"^
		"$webclient = new-object System.Net.WebClient;"^
		"if (-not ([string]::IsNullOrEmpty('%MVNW_USERNAME%') -and [string]::IsNullOrEmpty('%MVNW_PASSWORD%'))) {"^
		"$webclient.Credentials = new-object System.Net.NetworkCredential('%MVNW_USERNAME%', '%MVNW_PASSWORD%');"^
		"}"^
		"[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $webclient.DownloadFile('%DOWNLOAD_URL%', '%WRAPPER_JAR%')"^
		"}"
    if "%MVNW_VERBOSE%" == "true" (
        echo Finished downloading %WRAPPER_JAR%
    )
)
@REM End of extension

@REM Provide a "standardized" way to retrieve the CLI args that will
@REM work with both Windows and non-Windows executions.
set MAVEN_CMD_LINE_ARGS=%*

%MAVEN_JAVA_EXE% %JVM_CONFIG_MAVEN_PROPS% %MAVEN_OPTS% %MAVEN_DEBUG_OPTS% -classpath %WRAPPER_JAR% "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %MAVEN_CONFIG% %*
if ERRORLEVEL 1 goto error
goto end
import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";
    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
        + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH =
            ".mvn/wrapper/maven-wrapper.properties";

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private static final String MAVEN_WRAPPER_JAR_PATH =
            ".mvn/wrapper/maven-wrapper.jar";

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        File mavenWrapperPropertyFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if(mavenWrapperPropertyFile.exists()) {
            FileInputStream mavenWrapperPropertyFileInputStream = null;
            try {
                mavenWrapperPropertyFileInputStream = new FileInputStream(mavenWrapperPropertyFile);
                Properties mavenWrapperProperties = new Properties();
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if(mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: " + url);

        File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
        if(!outputFile.getParentFile().exists()) {
            if(!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
            String username = System.getenv("MVNW_USERNAME");
            char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}
:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

if not "%MAVEN_SKIP_RC%" == "" goto skipRcPost
@REM check for post script, once with legacy .bat ending and once with .cmd ending
if exist "%HOME%\mavenrc_post.bat" call "%HOME%\mavenrc_post.bat"
if exist "%HOME%\mavenrc_post.cmd" call "%HOME%\mavenrc_post.cmd"
:skipRcPost

@REM pause the script if MAVEN_BATCH_PAUSE is set to 'on'
if "%MAVEN_BATCH_PAUSE%" == "on" pause

if "%MAVEN_TERMINATE_CMD%" == "on" exit %ERROR_CODE%

exit /B %ERROR_CODE%
@REM Provide a "standardized" way to retrieve the CLI args that will
@REM work with both Windows and non-Windows executions.
set MAVEN_CMD_LINE_ARGS=%*

%MAVEN_JAVA_EXE% %JVM_CONFIG_MAVEN_PROPS% %MAVEN_OPTS% %MAVEN_DEBUG_OPTS% -classpath %WRAPPER_JAR% "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %MAVEN_CONFIG% %*
if ERRORLEVEL 1 goto error
goto end
import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";
    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
        + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH =
            ".mvn/wrapper/maven-wrapper.properties";

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private static final String MAVEN_WRAPPER_JAR_PATH =
            ".mvn/wrapper/maven-wrapper.jar";

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        File mavenWrapperPropertyFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if(mavenWrapperPropertyFile.exists()) {
            FileInputStream mavenWrapperPropertyFileInputStream = null;
            try {
                mavenWrapperPropertyFileInputStream = new FileInputStream(mavenWrapperPropertyFile);
                Properties mavenWrapperProperties = new Properties();
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if(mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: " + url);

        File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
        if(!outputFile.getParentFile().exists()) {
            if(!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
            String username = System.getenv("MVNW_USERNAME");
            char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}
:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

if not "%MAVEN_SKIP_RC%" == "" goto skipRcPost
@REM check for post script, once with legacy .bat ending and once with .cmd ending
if exist "%HOME%\mavenrc_post.bat" call "%HOME%\mavenrc_post.bat"
if exist "%HOME%\mavenrc_post.cmd" call "%HOME%\mavenrc_post.cmd"
:skipRcPost

@REM pause the script if MAVEN_BATCH_PAUSE is set to 'on'
if "%MAVEN_BATCH_PAUSE%" == "on" pause

if "%MAVEN_TERMINATE_CMD%" == "on" exit %ERROR_CODE%

exit /B %ERROR_CODE%