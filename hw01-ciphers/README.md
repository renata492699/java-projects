Homework assignment no. 1, Simple Ciphers
====================================

**Publication date:**  March 20, 2021

**Submission deadline:** April 3, 2021

General information
-------------------
The goal of this homework is to implement a set of simple and well known [substitution ciphers](https://en.wikipedia.org/wiki/Substitution_cipher).
To be more specific, you will be implementing the following ciphers

* **Morse Code** -- Yes even when called a "code" it is a cipher
* **Caesar Cipher** -- Probably the most known substitution cipher out there
* **Vigenere Cipher** -- A series of interwoven Caesar ciphers

All the ciphers in this assignment should work withe the following alphabet

```text
ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789
```

Any other characters in plaintext message should be left unaffected by the encryption algorithm unless stated otherwise in the description of each specific cipher.

**Note:** Different implementation (or sources of information) might differ slightly with performed substitutions due a different alphabet (e.g. only upper case letters).

### Morse Code
A method of encoding text into standardize sequences of two different signal lengths (dots and dashes) names after the inventor of telegraph -- Samuel Morse.
The following table describes the supported alphabet of our Morse Code implementation

| Character | Code      | Character | Code      | Character | Code      | Character | Code      |
| ----      | ---       | ---       | ---       | ---       | ---       | ---       | ---       |
| A         | .-        | K         | -.-       | U         | ..-       | 4         | ....-     |
| B         | -...      | L         | .-..      | V         | ...-      | 5         | ....      |
| C         | -.-.      | M         | --        | W         | .--       | 6         | -....     |
| D         | -..       | N         | -         | X         | -..-      | 7         | --...     |
| E         | .         | P         | .--.      | Y         | -.--      | 8         | ---..     |
| F         | ..-.      | O         | ---       | Z         | --..      | 9         | ----.     |
| G         | --.       | Q         | --.-      | 0         | ----      | .         | .-.-.-    |
| H         | ....      | R         | .-.       | 1         | .---      | ,         | --..--    |
| I         | ..        | S         | ...       | 2         | ..---     | ?         | ..--..    |
| J         | .---      | T         | -         | 3         | ...--     | !         | ..--.     |


The letters of Morse Code should be divided by a single ```|``` and spaces should be represented as ```||```.
Also note that Morse Code is case insensitive and thus lower and upper case letters are equivalent for the purpose of this cipher.

### Caesar Cipher
A simple cipher in which a plaintext letter is replaced by a letter following some fixed number of positions later in the alphabet.
Should the shift be greater than the size of the alphabet then simple modulo by the size of the alphabet is applied to it.

```text
Plaintext Alphabet:     ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789
Shifted by 2 Alphabet:  89ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567
```

### Vigenere Cipher
[Vigeneres Ciphe](https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher) represents a series of interwoven Caesar ciphers. Using a keyword, each character of plaintext message is encrypted by Casar cipher with shift equal to the distance between given plaintext character and character at corresponding position in the key.

```text
Plaintext:  ATTACKATDAWN
Key:	    LEMON
Ciphertext:	LXfOPVEfRNhR
```


### Evaluation
Beside functional correctness this assignment is focused on object oriented design.
This means that the way you structure your program will be an important part of its evaluation.
On the other hand the given set of tests is not trying to provide an elaborate test coverage and incorrect behaviour in corner-cases should not have a large impact on the evaluation.

Note that all this is at your seminar teacher's discretion.

The maximum number of points for this assignment is **10**.

- **6 points** for passing the tests (attached tests do not guarantee a 100% correctness).
- **4 points** for correct and clean implementation (evaluated by your class teacher).

### Preconditions
To successfully implement this assignment you need to know the following

1. What is the difference between _class_ and _object_ and how to work with both.
2. How programs are structured in _object-oriented_ languages.
3. How to use basic control structures such as `if`, `for`/`while` and `switch`.
4. How to work with `String`s and `enum`s in Java.
5. How to work with _arrays_.
6. Being able to understand and navigate a code provided by third-party.

### Project structure
The structure of project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.hw01``` contains classes and interfaces provided as part of the assignment.
  - **Do not modify or add any classes or subpackages into this package.**
2. Package  ```cz.muni.fi.pb162.hw01.impl``` should contain your implementation.
  - **Anything outside this package will be ignored during evaluation.**

### Names in this document
Unless fully classified name is provided, all class names are relative to  package ```cz.muni.fi.pb162.hw01``` or ```cz.muni.fi.pb162.hw01.impl``` for classes implemented as part of your solution.


### Compiling the project
The project can be compiled and packaged in the same way you already know

```bash
$ mvn clean install
```

The only difference is, that unlike with seminar project, this time checks for missing documentation and style violation will produce an error.
You can temporarily disable this behavior when running this command.

```bash
$ mvn clean install -Dcheckstyle.fail=false
```

You can consult your seminar teacher to help you set the ```checkstyle.fail``` property in your IDE (or just google it).

### Running the application
The build descriptor is configured to produce a single runnable jar file located at `target/application.jar`. The application can be run using the following command

```bash
# By default Morse Coder is used
$ java -jar application.jar -o ENCRYPT -t  "Ahoj, jak se mas?"
$ .-|....|---|.---|--..--||.---|.-|-.-||...|.||--|.-|...|..--..|

# Caesar cipher
$ java -jar application.jar -c CAESAR -o ENCRYPT --shift 3 -t  "Ahoj, jak se mas?"
$ Dkrm, mdn vh pdv?

# Caesar cipher decrypt
$ java -jar application.jar -c CAESAR -o DECRYPT --shift 3 -t  "Dkrm, mdn vh pdv?"
$ Ahoj, jak se mas?

# Vigenere cipher
$ java -jar application.jar -c VIGENERE -o ENCRYPT --key BananaPapple -t  "Ahoj, jak se mas?"
$ B7R9, y0P T8 CDI?
```

### Submitting the assignment
The procedure to submit your solution may differ based on your seminar group. However generally it should be OK to submit ```target/homework01-2021-1.0-SNAPSHOT-sources.jar``` to the homework vault.

## Implementation
Generally speaking there are no mandatory requirements on the structure of your code as long as the command line interface of ```Appplication``` class works correctly.
The use of classes, enums and interfaces, provided as part of the project skeleton is up to your decision.
