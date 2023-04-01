---------------------------------------------------------------------------
# __EliteGhost-CTF-2023__  
---------------------------------------------------------------------------

EGCTF2023 is a puzzle-based, three-days Capture The Flag (CTF) challenge consists of 50 questions from multiple categories, organized by a community called Elite Ghost. This repository is about providing writeups on challenges that have been done by me.

In this event, I managed to get 4th place. I have learned a lot during this event and would like to join other CTF challenges in the future.  

---------------------------------------------------------------------------
## __Table of Contents__
- [**Programming**](#Programming)
  - [Rigorous](#Rigorous)

- [**Cryptography**](#Cryptography)
  - [WASD](#WASD)
  - [Cartoon](#Cartoon)
  - [DoReMi](#DoReMi)
  - [NATO](#NATO)
  - [Doraemon](#Doraemon)
<!-- - [Super Secured Encryption](#Super-Secured-Encryption) -->
  
- [**Reverse Engineering**](#Reverse-Engineering)
  - [OldButGold](#OldButGold)
  - [Diamond](#Diamond)
  - [Vault](#Vault)
  - [Complexity](#Complexity)
  - [Ordered](#Ordered)  

- [**Web**](#Web)
  - [Japan](#Japan)
  - [Error 500](#Error-500)
  - [EVE](#EVE)
  - [Error 404](#Error-404)
  - [TutTut](#TutTut)
  - [Birthday](#Birthday)
  
- [**Forensics**](#Forensics)
  - [Corrupted](#Corrupted)
  - [Signal](#Signal)
  - [AutoBot](#AutoBot)
  - [Broken Oyen](#Broken-Oyen)
  
- [**Steganography**](#Steganography)
  - [CatMeow](#CatMeow)
  - [Mastery](#Mastery)
  - [Class](#Class)
  - [GustaveCourbet](#GustaveCourbet)
  
<!-- - [**OSINT**](#OSINT)
  - [Farewell](#Farewell)
  - [Broken Man](#Broken-Man)
  - [Octopus](#Octopus)
  - [Oldest Historical Tree](#Oldest-Historical-Tree)
  - [Thirsty](#Thirsty)
  - [SixSenses](#SixSenses)
 --> 
- [**Networking**](#Networking)
  - [IPV4](#IPV4)
  - [IPV6](#IPV6)
  - [AirDream Network](#AirDream-Network)
  
- [**Misc**](#Misc)
  - [Hashcat](#Hashcat)
  - [Mystery Code](#Mystery-Code)
  - [SuperBrain](#SuperBrain)
  - [MysteryBunny](#MysteryBunny)

---------------------------------------------------------------------------
## Programming
### [Rigorous]
![Rigorous Question](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Rigorous/RigorousQues.png?raw=true)  
| Files         |
| ------------- |
| [flag.zip](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Rigorous/flag.zip)      |

Hint:
> - What is the value of f(x) = sin(x^2 + 1) + 1 ?  
> - Malicious code designed to execute under circumstances I've programmed!  
> - No Arithmetic Operation required..  
> - Useless Regex Expressions! This challenges tricked my brain well!  
> - value of f(x) = sin(x^2 + 1) + 1 is 1. Therefore, its an arrangement for a malicious “code” to executed under circumstances i’ve programmed.  
> - Execution with data outside 33-126 Range.  

After extracting the zip file, there will be 4 text files:  
    ![Extracted](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Rigorous/Extracted%20zip%20file.png?raw=true)  
    
Formula.txt:  
```
apk: f(x) = sin(x^2 + 1) + 1
ipa: 2
exe: 3
osx: 4
```  
[^a-zA-Z0-9_].apk.txt:  
```
^(\\S+)\\s+(\\S+)$ || D'`;M#8n<;G3zx6Te@,PqMo:n%*#(4hffBA.~}+{)9rqvutsl2pohPle+ihJIedcb[!_^@VUTYRQPtTSRQ3ONGkjW
```
[^a-zA-Z0-9_].ipa.txt:  
```
^(\\S+)\\s+(\\S+)$ || D'``_9"n[HX9ih6fe3,sN)LoJ87jGX~DeASc~a_N):rqvutsl2johgfkjc)gIH^]\"`_A@\Uy<;QPt76LpP2NMLKDhHGF?>bO
```
[^a-zA-Z0-9_].exe.txt:  
```
^(\\S+)\\s+(\\S+)$ || D'`%_9"=[l:{8VC54uQ>0).:]%$ki!g21{"cx}O<)srwp6tsrkpongf,jiKgf_%F\[`_^]VzZ<;WVOsMRQJnH0LEJCBf)?DC<;_L
```
[^a-zA-Z0-9_].osx.txt:  
```
^(\\S+)\\s+(\\S+)$ || D'`N@9>~ZZXXyV6Tuu2rNM;:&+H6GiWfBTzcaP|N)y[wpunsl2pohglkjiba'edFb[!_^@?UTxRQPOTSLpP2NMLEDhU
```
Since f(x) = sin(x^2 + 1) + 1 is 1, this means that the contents of formula.txt defines the arrangement of the content in all the files.  

The hint also tells that the Regex expressions is useless. All the files have `^(\\S+)\\s+(\\S+)$ ||` at the very first of the value. So maybe this is useless. Remove them and you get:  
```
D'`;M#8n<;G3zx6Te@,PqMo:n%*#(4hffBA.~}+{)9rqvutsl2pohPle+ihJIedcb[!_^@VUTYRQPtTSRQ3ONGkjW  
D'``_9"n[HX9ih6fe3,sN)LoJ87jGX~DeASc~a_N):rqvutsl2johgfkjc)gIH^]\"`_A@\Uy<;QPt76LpP2NMLKDhHGF?>bO  
D'`%_9"=[l:{8VC54uQ>0).:]%$ki!g21{"cx}O<)srwp6tsrkpongf,jiKgf_%F\[`_^]VzZ<;WVOsMRQJnH0LEJCBf)?DC<;_L  
D'`N@9>~ZZXXyV6Tuu2rNM;:&+H6GiWfBTzcaP|N)y[wpunsl2pohglkjiba'edFb[!_^@?UTxRQPOTSLpP2NMLEDhU
```  

The hint also tells something about Execution with data outside 33-126 Range. I googled up and found about [Malbolge compiler.](https://malbolge.doleczek.pl) I tried to compile all of them at once, but failed. Then, I tried to compile according to their sequence and got base64 values:  
```
RUd7TTRMQjBMRzNfMVNfSDRSRH0=
```  
After that, I decoded using [base64 decoder](https://www.base64decode.org) and got the flag:  
```
EG{M4LB0LG3_1S_H4RD}
```

---------------------------------------------------------------------------
## Cryptography
### [WASD]  
![WASD](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/WASD/WASD.png?raw=true)  
|Files|
|-----|
|[flag.txt](WASD/flag.txt)|  

Hint:  
> What is meant by fat-finger problem?

The content of flag.txt file is:  
```
WFPA2XE2R)X9S2{
```   
The hint tells something about fat-finger problem. Whenever people have fat fingers, they tend to **"shift"** a little bit when pressing the **"keyboard"**. After googling, I found out that the flag is encypted using keyboard shift cipher. Decrypt using [Keyboard Shift Cipher Decryptor](https://www.dcode.fr/keyboard-shift-cipher), use the power of `Ctrl+F` and you found:  
```
EG{S3CR3T_C0D3}
```   

### [Cartoon]  
![Cartoon](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Cartoon/Cartoon.png?raw=true)  

|Files|
|-----|
|[flag.png](Cartoon/flag.png)|  

The file given is an image file:  
![image](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Cartoon/image_2023-01-14_162028939.png?raw=true)  

Hint:  
> - Doodle man!  

By looking at the hint, and some googling, I found that it is a unique font called Dancing Men. I used the [Dancing Men Cipher Decryptor](https://www.dcode.fr/dancing-men-cipher) and got the flag:  
```
EG{UNBELIEVABLECIPHER}  
```   

### [DoReMi]  
![DoReMi](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/DoReMi/Doremi.png?raw=true)  

File:  
![Lagu](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/DoReMi/flaggg.jpg?raw=true)  

Hint:
> Music? Cipher?  

When I look at flaggg.jpg, at first I thought it was a direct translation of the music notes. However, after I look at the hint, I know that it was again a type of cipher which is Music Sheet Cipher (Fyi, only decrypt the music notes on top). After decrypting using [Music Sheet Cipher Decryptor](https://www.dcode.fr/music-sheet-cipher) and able to get the flag:  
```
EG{IFMUSICWASINCODE}
```  

### [NATO]
![NATO](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/NATO/NATO.png?raw=true)  
|Files|
|-----|
|[flag.txt](NATO/flag.txt)|  

The content of flag.txt is:  
```
EG{Romeo Oscar Golf Echo Romeo Tango Hotel Alpha Tango Sierra India Romeo}
```  
The question mentioned about NATO phoetic alphabet. I googled and found this:  
![NATO](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/NATO/ABC_Chart.jpg?raw=true)  

It is actually a direct question. Just follow the chart and you will get the flag:  
```
EG{ROGERTHATSIR}
```  

### [Doraemon]  
![DoraemonQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Doraemon/Doraemon.png?raw=true)  

File:  
![DoraBella font](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Doraemon/flag.png?raw=true)  

Hint:  
> Dora + Fingerlings Tales(Name cartoon) Cipher!

Based on the hint given, there are 4 character in Fingerling Tales which are Gigi, Bella, Boris, Marge. Try all the combination and we have DoraBella Cipher. Use [Dorabella Cipher Decryptor](https://www.dcode.fr/dorabella-cipher) and there you go!:  
```
EG{DORABELLACHPHER}
```  

<!-- ###Super Secured Encryption -->
---------------------------------------------------------------------------
## Reverse Engineering
### [OldButGold]  
![OldButGoldQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/OldButGold/OldButGold.png?raw=true)  

|Files|
|-----|
|[65YearsAgo.txt](OldButGold/65YearsAgo.txt)|  

Hint:  
> Asc  

The hint tells that the flag value in 65YearsAgo.txt is actually in ASCII format. In the text file, there are number sequences:  
```
51, 76, 49, 84, 51, 71, 72, 48, 83, 84, 95, 52, 67, 52, 68, 51, 77, 89
```  
Decrypt the ASCII code using the [ASCII Converter](https://www.dcode.fr/ascii-code) and you will get the flag:  
```
EG{3L1T3GH0ST_4C4D3MY}
```  

### [Diamond]  
![DiamondQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Diamond/Diamond.png?raw=true)  

|Files|
|-----|
|[flag.rb](Diamond/flag.rb)  

Hint:  
> - Ever heard about encryption?
> - R**13 Cipher!  

The files given is a ruby file. The hint mentioned about ROT13 Cipher, and there are lines of codes defining that some value need to be decoded using ROT13 Cipher:  
```
def c(p)
    e = "F0_gu1f_1f_e0g13_J3yp0zr"
    d = rot13(e)
    if p == d
      puts "Correct password, here's your flag! : EG{#{d}}"
    else
      puts "Wrong password"
    end
  end
```  
Decrypt the value using [ROT13 Cipher Decryptor](https://www.dcode.fr/rot-13-cipher) and you will get the flag:  
```
EG{S0_th1s_1s_r0t13_W3lc0me}
```  

### [Vault]  
![VaultQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Vault/Vault.png?raw=true)  

|Files|
|-----|
|[passcode.txt](Vault/passcode.txt)|
|[vault.zip](Vault/vault.zip)|  

Hint: No hint.  

The question is straightforward. The passcode.txt provide lines of code that I believe to be in Python programming language. Paste the code in [Python online compiler](https://www.onlinegdb.com/online_python_compiler#) and put print() function:  
```
x = 1337
y = x % 882726
z = int(str(y)[::-1])
a = z * y
g = x + y + z + a
passcode = (x + g + y + z + a) * 2

print(passcode)
```  
You will get 8 digits number:  
```
39246208
```  
Use the password to unlock the vault.zip file and you will get the flag:  
```
EG{M4THS_1S_FUN}
```  

### [Complexity]  
![ComplexityQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Complexity/Complexity.png?raw=true)  

|Files|
|-----|
|[NonSense.java](Complexity/NonSense.java)|  

Hint:  
> - Step one, identify the target.
> - Step 1, identify the target and the “FLAW”.  

The files provided consists of thousand of lines of Java code. The hint tell us to identify the target and the flaw. I think that the target here is the flag, and the flaw is the flaw in the code. I go through the code and found something:  
```
private static void Flaw(String logicBomb){
          System.out.println("The feeling that never gets old");
          if (logicBomb == "REZ7U0czUTNfMVJfNEtWNFhSX0VLNFZ9"){
          for (int UrlString; UrlString<=15; UrlString++ ){
          Sytem.out.println("Done");
```  
The string `REZ7U0czUTNfMVJfNEtWNFhSX0VLNFZ9` is an encoded base64 format. I decode it using [Base64 decoder](https://www.base64decode.org) and got this:  
```
DF{SG3Q3_1R_4KV4XR_EK4V}
```  
The flaw that I found was definitely the right one. Then I used [Shift Cipher Decryptor](https://www.dcode.fr/shift-cipher) to decrypt again until I got the flag:  
```
EG{TH3R3_1S_4LW4YS_FL4W}
```


### [Ordered]
![OrderedQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Ordered/Ordered.png?raw=true)  

|Files|
|-----|
|[flag.cpp](Ordered/flag.cpp)|  

Hint:  
> A B C D E..  

Given the code from flag.cpp:  
```
#include <iostream>

int main() {
    char A = 1 + 0;
    char E = H + 5;
    char B = E + 2;
    char J = "}" + 10;
    char F = 4 + 6;
    char I = R + 9;
    char D = "{" + 4;
    char G = X + 7;
    char C = G + 3;
    char H = 0 + 8;
    char FLAG = "?";
    }
```
Based on the hint, arrange according to its alphabetical order and you will get:  
```
char A = 1 + 0;
char B = E + 2;
char C = G + 3;
char D = "{" + 4;
char E = H + 5;
char F = 4 + 6;
char G = X + 7;
char H = 0 + 8;
char I = R + 9;
char J = "}" + 10;
```  
Flag:  
```
EG{H4X0R}
```

---------------------------------------------------------------------------
## Web
### [Japan]  
![JapanQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Japan/Japan.png?raw=true)  

|Files|
|-----|
|[admin-sign.pdf](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Japan/admin-sign.pdf)|  

Hint: No hint.  

Go to [who.is](https://who.is) and put jprs.jp. You will get the signing key:  
![sign-key](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Japan/image_2023-01-14_185836272.png?raw=true)  

Use the key to unlock the protected admin-sign.pdf and there is the flag!:  
```
EG{WH01S_L00KUP_1S_34SY}
```  

### [Error 500]  
![Error505Ques](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Error%20505/image_2023-01-14_190535209.png?raw=true)  

Given in the question is the link to a website. Click the link. Inspect element (Ctrl + Shift + I) observe the script and you will find the flag:  

![flag](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Error%20505/image_2023-01-14_190730060.png?raw=true)  

flag:  
```
EG{G1nZ_1s_H3r3}
```   

### [EVE]  
![EVEques](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/EVE/EVE.png?raw=true)  

Hint:  
> - Don't be fooled with the source! The clue already related on what's in front of your eyes  
> - Who is EVE? Hackers always do their research!  
> - Function robots for web? I don't know, make some research dude!  

Given is the link to a website. Based on the hint, this challenge uses `robots.txt`. Put `robots.txt` in the directory as such:  
```
https://eliteghost.tech/wall-e/robots.txt  
```  
You will see lots of directory. Find unusual directory and you will found `czNjcjN0MTMzNw==`. Decode the base64 format using [Base64 Decoder](https://www.base64decode.org) and you will get `s3cr3t1337`. Put the decoded string into the initial directory like this:  
```
https://eliteghost.tech/wall-e/s3cr3t1337/
```  
You will see nothing at first. Try to inspect elements and a base64 format string will appear as below:  
![wow](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/EVE/image_2023-01-14_192134918.png?raw=true)  
Decode the string using the same decoder and you will see the flag:  
```
EG{R0B0tS_W1tH_B64_Wa5_So_FXN_R1GHT}
```  

### [Error 404]  
![Error404Ques](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Error404/Error404.png?raw=true)  

Hint: No hint.  

I tried the files given, but all of them were not the flag. but then I realized that the header of the table is also interactive:  
![1st](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Error404/1st.png?raw=true)  

I clicked Description and got this:  
![2nd](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Error404/2nd.png?raw=true)  

The I tried to decode the base64 strings 2 times using [Base64 Decoder](https://www.base64decode.org) and got the flag:  
```  
EG{JU57_K1DD1NG}  
```  
### [TutTut]  
![TutTutQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/TutTut/TutTut.png?raw=true)  

File:  
![morse](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/TutTut/morsecode.png?raw=true)  

Hint:  
> Just follow the instructions! What number iv represents in exam?  

The URL given in the picture is `eliteghost.tech/IV.html`. I tried to go to the link but the flag was not there. then I tried to change from `IV` to `....-` in the link so the new link will be `eliteghost.tecg/....-.html` and got the flag:  
```
EG{L34RN_M0RS3_C0D3}  
```  

### [Birthday]  
![bdayques](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Birthday/Birthday.png?raw=true)  

|Files|
|-----|
|[present.zip](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Birthday/present.zip)|  

To find when eliteghost.tech was created, go to [who.is](who.is) and paste the  url, you will see the date. Use the date 20221012 to unlock the zip file. You will get a picture in the file:  
![damn](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Birthday/EliteGhost%20CTF%20HN2.png?raw=true)  
If you zoom a little bit on top, you will get the flag:  
```
EG{H4PPY_N3W_Y34R_CTF_2023}
```  

---------------------------------------------------------------------------
## Forensics
### [Corrupted]  
![Corrupted](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Corrupted/Corrupted.png?raw=true)  

|Files|
|-----|
|[currpoted.pss](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Corrupted/curropted.pss)|  

Hint: No hint.  

Change the file extension from pss to png and you will get the flag:  
```
EG{F0R3NS1CS_1S_FUN}
```  

### [Signal]  
![SignalQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Signal/Signal.png?raw=true)  

|Files|
|-----|
|[Signal0xffffffff.wav](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Signal/Signal0xffffffff.wav)|  

Hint:  
> Ever heard about spectogram?

The audio contain morse code. Decode it using [Morse Code Adaptive Audio Decoder](https://morsecode.world/international/decoder/audio-decoder-adaptive.html) and you will get the flag:  
```
EG{SP3CT0GR4M_T0_T3XT}
```  

### [AutoBot]  
![Autobot](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Autobot/Autobot.png?raw=true)  

|Files|
|-----|
|[flag.zip](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Autobot/flag.zip)|  

Hint: No hint  

After you extract the zip file, you will get 4 files. I tried to google image search the formula, and it seems that the formula is not useful. If you see the naming for the remaining 3 files, it seems like there are arrrangement to which will go first. So I looked at the file named `1_PR13_B01_BRKT_CAM.robotic` and used `Ctrl + F` to find the flag format, and I found:  
```
{\Ftxt.shx|b0|i0|c0;\C256;EG\{ R0B}
```  
I proceed with file named `2_FR07_H104.robotic` and used `Ctr; + F` to find `\` and found:  
```
{\Ftxt.shx|b0|i0|c0;\C256;0T1C_1S_}
```  
Next, search the same thing as the second file on the third file named `3_FR07_X101.robotic` and found:  
```
{\Ftxt.shx|b0|i0|c0;\C256;4W3S0M3\}}
```  
Combine those three and I got the flag:  
```
EG{R0B0T1C_1S_4W3S0M3}
```  
### [Broken Oyen]  
![BrokenOyenQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Broken%20Oyen/Broken%20Oyen.png?raw=true)  

|Files|
|-----|
|[cat.zip](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Broken%20Oyen/cat.zip)|  

Hint:  
> - pass (----^%%%%)  
> - 9 char 👍  

Use [Aperisolve](aperisolve.com) to extract the data from `meow.jpg` and you will see the string as below:  
```
pass:
~*t}{~'t(wx%t
```  

Decode it using [Rot47 Decoder](https://www.dcode.fr/rot-47-cipher) and you will get `OYENLOVEWHITE`. Use the code to unlock `meow.zip` (make sure the password in small letters) and you will get two files which are `oyen.txt` and `oyen.zip`. In `oyen.txt` it says:  
```
THE LAST THINGS I REMEMBER MY PASSWORD CONTAIN MY NAME SYMBOL AND NUMBER.
```  
So we know that the `name = oyen`. We still do not know about `symbol, number`, but the hint told that the password has 4 digits and 1 symbol. I have created a python code to create a custom `password.txt`.:  
```
f = open("password.txt","w")
special = ["!","@","#","$","%","^","&","*","(",")"]
for i in special:
  for x in range(1000,10000):
    f.write("oyen" + i + str(x) + "\n")
f.close()
```  
Then I got a txt file with all the potential passcode to unlock `oyen.zip`. I opened my Kali Linux and use zip2john to brute force the password:  
```
zip2john oyen.zip > zip.hash  
john --wordlist=/home/kali/Documents/password.txt zip.hash  
```
The password will appear: `oyen@2102`  
Unzip `oyen.zip` with the password. There is a file called `oyen.dat`. When you run `file oyen.dat`, it is actually `oyen.jpg`. Run the code below:  
```
mv oyen.dat oyen.jpg  
tail oyen.jpg  
```  
You will find a base64 string:  
```
SHlFN1pSam1ESTlURW1TTUpJOUNFR09MWjBTOQ==
```  
Decode according to this rotation: String to Base64 to ROT13 to Base64 to ROT13 and you will get the flag:  
```
EG{0Y3N_ST1LL_BR0K3N}
```  

---------------------------------------------------------------------------
## Steganography
### [CatMeow]  
![CatMeowQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/CatMeow/CatMeow.png?raw=true)  

Hint:  
> - Vroom Vroom!  
> - Looking for QR Code?  

Based on the hint, if you look closely in the video, there is a QR code:  
![QR](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/CatMeow/QR.png?raw=true)  

Scan the QR and you'll get the flag:  
```
EG{QR_C0D3_1S_P0W3RFULL}
```  

### [Mastery]  
![MasteryQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Mastery/Mastery.png?raw=true)  

Watch the video and you will se the flag:  
![flag](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Mastery/flag.png?raw=true)  

The flag:  
```
EG{H4PPY_N3W_Y34R}
```

### [Class]  
![ClassQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Class/Class.png?raw=true)  

File:  
![Mastery](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Class/mastery-encoded.png?raw=true)  

Hint:  
> - Steghide!  
> - Maybe try many services?  

For this challenge, you just have to decode the picture with all the steganography online tools available until you find the [chosen one](https://www.pelock.com/products/steganography-online-codec) and input the password: `NOPASS`. You will get the flag:  
```
EG{m4st3ry_cl44s}
```  

### [GustaveCourbet]  
![GC](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/GustaveCourbet/GC.png?raw=true)  

|Files|
|-----|
|[GustaveCourbet.png](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/GustaveCourbet/Gustave_Courbet_-_Le_Desespere_1845.png)|
|[Clue.zip](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/GustaveCourbet/Clue.zip)|  

Hint:  
> Same as in Clue.zip  

With the hint given, just follow the steps similar to this [video](https://drive.google.com/file/d/1yGnxoJODl0zSTXTNxAHY-qU-OShLcqGq/view) but with different images until you get this QR code:  
![QR](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/GustaveCourbet/QR.png?raw=true)  

Flag:  
```
eg{3b68892a1ba21a26b9efd93f8d8c2fb1}
```
<!-- ---------------------------------------------------------------------------
 ## OSINT
### Farewell
### Broken Man
### Octopus
### Oldest Historical Tree
### Thirsty
### SixSenses
-->
---------------------------------------------------------------------------
## Networking
### [IPV4]  
![IPV4Ques](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/IPV4/IPV4.png?raw=true)  

|Files|
|-----|
|[flaggg.pcapng](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/IPV4/flaggg.pcapng)|   

Fot this challenge, you will need to use Wireshark to open the file given. Once you are in the Wireshark, you will see the base64 strings in packet comments:  
![pckt](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/IPV4/Base64.png?raw=true)  

Decode the string and you will get the flag:  
```
EG{W1R3SH4RK_1S_GR34T}
```  

### [IPV6]  
![IPV6Ques](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/IPV6/IPV6.png?raw=true)  

|Files|
|-----|
|[flagx.pcapng](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/IPV6/flagx.pcapng)|  

Hint:  
> Do not get tricked! Nobody care if you love wireshark!  

Click [this](https://filext.com/online-file-viewer.html) and put the file given. You will see base64 strings:  
```
RUd7VEgxU18xU19USDNfQzBSUjNDVF9GTDRHfQ==
```  
Decode it and you will get the flag:  
```
EG{TH1S_1S_TH3_C0RR3CT_FL4G}
```  

### [AirDream Network]  
![ADQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/AirDream%20Network/AirDream.png?raw=true)  

|Files|
|-----|
|[flag.zip](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/AirDream%20Network/flag.zip)|  

Hint:  
> - Learn more about Subnetting! Don't just use calculator or online tools! Do it Manually! Understand?
> - Amount of Subnets = Subnets Bits ? Hosts = Usable Hosts? Fun fact: In youtube, there's a 1.1M views video where the Ip Add and Subnets Mask given is the same! With the solution that u need!
> - Most common mistake is where people taught subnets mask is subnets. Also, amount of subnets bits is really simple. Maybe refer back the video that got mentioned on the last clue?  

In the question, there a link that shows the format of the password for the zip file:
```
AUExYo0x45FFghijkWW1-"IP"-"CLASS"-"AMOUNT OF SUBNETS"-"SUBNETS"-"HOSTS"
```  

After looking for a YouTube video with 1.1M views, I finally found [one](https://youtu.be/uyRtYUg6bnw)! From the video, you will identify most of the things needed for the password until you get:  
```  
AUExYo0x45FFghijkWW1-192.168.1.0-C-4-16-14
```  
Use it to unlock the zip file and you will get base64 strings. Decode it and you will get the flag:  
```
EG{1P_SUBN3TT1NG_1S_34SY}
```  

---------------------------------------------------------------------------
## Misc
### [Hashcat]  
![HashcatQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Hashcat/Hashcat.png?raw=true)  

|Files|
|-----|
|[flag-meow.pdf](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Hashcat/flag-meow.pdf)| 
|[oyen.png](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/Hashcat/oyen.png)  

Go to [MD5 Converter](https://www.conversion-tool.com/md5/) and take the hash to unlock the pdf file. You will get the flag:  
```
EG{3V3RY_F1L3_H4SH}
```  


### [Mystery Code]  
![MCQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Mystery%20Code/Mystery%20Code.png?raw=true)  

Files:  
![code.jpg](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Mystery%20Code/code.jpg?raw=true)  
![unknown.jpg](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Mystery%20Code/unknown.jpg?raw=true)  

For this one, just follow the sequence of the colours on the keyboard and you will get the flag:  
```
EG{S3CR3T_C0D3}
```  

### [SuperBrain]  
![SBQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/SuperBrain/SuperBrain.png?raw=true)  

File:  
![stateofmind.jpg](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/SuperBrain/stateofmind.jpg?raw=true)  

From the file given, it says `CUT FIRST 3 WORDS`,`0F`,`1LLUS1ON_`. In the YouTube video, cut three words of the title and you will get `M1ND`. Combine everything an you will get the flag:  
```
EG{1LLUSION_OF_M1ND}
```  

### [MysteryBunny]  
![MysteryBunnyQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/MysteryBunny/MysteryBunny.png?raw=true)  

|Files|
|-----|
|[bunny.pdf](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/MysteryBunny/bunny.pdf)|
|[decode.pdf](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/MysteryBunny/decode.pdf)|
|[secretkey.jpg](https://github.com/mzahiruliman/EliteGhost-CTF-2023/raw/main/MysteryBunny/secretkey.jpg)|  

Arrange the contents in `decode.jpg` and sort according to its country sequence and number and you will get the sequence below:  

```
tahniah               (Arab)
123               (1) (Japan)
huj               (2) (Japan)
lmao              (1) (China)
oyaoya            (2) (China)
spasipo               (Rusia)
jjsksjjsjksjjksjj (1) (Korea)
arigatou          (2) (Korea)
```

Combine everything and you will get:  
```
tahniah123hujlmaooyaoyaspasipojjsksjjsjksjjksjjarigatou
```
Use the code to unlock `bunny.pdf` and you will get the flag:  
```
EG{BR1LL14NT_S3CR3T_COD3}
```
---------------------------------------------------------------------------
