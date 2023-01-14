---------------------------------------------------------------------------
# __EliteGhost-CTF-2023__  
---------------------------------------------------------------------------

EGCTF2023 is a puzzle-based, three-days Capture The Flag (CTF) challenge consists of 50 questions from multiple categories, organized by a community called Elite Ghost. This repository is about providing writeups on challenges that have been done by me (@BiskutIkatTepi)  

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
  
- [**OSINT**](#OSINT)
  - [Farewell](#Farewell)
  - [Broken Man](#Broken-Man)
  - [Octopus](#Octopus)
  - [Oldest Historical Tree](#Oldest-Historical-Tree)
  - [Thirsty](#Thirsty)
  - [SixSenses](#SixSenses)
  
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
### Diamond
### Vault
### [Complexity]  
![ComplexityQues](https://github.com/mzahiruliman/EliteGhost-CTF-2023/blob/main/Complexity/Complexity.png?raw=true)  

|Files|
|-----|
|[NonSense.java](Complexity/NonSense.java)|  

Hint:  
> Step one, identify the target.
> Step 1, identify the target and the “FLAW”.  

The files provided consists of thousand of lines of Java code. The hint tell us to idemtify the target and the flaw. I think that the target here is the flag, and the flaw is the flaw in the code. I go through the code and found something:  
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
The flaw that I found was difinitely the right one. Then I used [Shift Cipher Decryptor](https://www.dcode.fr/shift-cipher) to decrypt again until I got the flag:  
```
EG{TH3R3_1S_4LW4YS_FL4W}
```


### Ordered
---------------------------------------------------------------------------
## Web
### Japan
### Error 500
### EVE
### Error 404
### TutTut
### Birthday
---------------------------------------------------------------------------
## Forensics
### Corrupted
### Signal
### AutoBot
### Broken Oyen
---------------------------------------------------------------------------
## Steganography
### CatMeow
### Mastery
### Class
### GustaveCourbet
---------------------------------------------------------------------------
## OSINT
### Farewell
### Broken Man
### Octopus
### Oldest Historical Tree
### Thirsty
### SixSenses
---------------------------------------------------------------------------
## Networking
### IPV4
### IPV6
### AirDream Network
---------------------------------------------------------------------------
## Misc
### Hashcat
### Mystery Code
### SuperBrain
### MysteryBunny
---------------------------------------------------------------------------
