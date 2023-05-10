package cz.muni.fi.pb162.hw01.impl;

import cz.muni.fi.pb162.hw01.Utils;
import cz.muni.fi.pb162.hw01.impl.ciphers.Cipher;

/**
 * MorseCode class implements Cipher interface.
 *
 * A method of encoding text into standardize
 * sequences of two different signal lengths (dots and dashes).
 * The letters of Morse Code are divided by a single | and
 * spaces are represented as ||.
 *
 * @author Renata Urbanova
 */
public class MorseCode implements Cipher {
    private final String[] morseCode = new String[] {".-", "-...", "-.-.", "-..", ".", "..-.",
            "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
            "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...",
            "---..", "----.", ".-.-.-", "--..--", "..--..", "..--."};

    private final char[] alphabet = Cipher.ALPHABET_MORSE.toCharArray();

    @Override
    public String decrypt(String cipherText) {
        StringBuilder letter = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int charIndex;
        boolean space = false;
        for (int i = 0; i < cipherText.length(); i++) {
            char sight = cipherText.charAt(i);
            if (sight == '|') {
                if (space) {
                    result.append(" ");
                    space = false;
                } else {
                    space = true;
                    charIndex = Utils.searchIndex(morseCode, letter.toString());
                    result.append(alphabet[charIndex]);
                    letter = new StringBuilder();
                }
            } else {
                space = false;
                letter.append(sight);
            }
        }
        return result.toString();
    }

    @Override
    public String encrypt(String plainText) {
        String result = "";
        char letter;
        int letterIndex;
        for (int i = 0; i < plainText.length(); i++) {
            letter = plainText.charAt(i);
            if (letter == ' ') {
                result += "|";
            } else {
                letterIndex = Utils.searchIndex(alphabet, letter);
                result += (morseCode[letterIndex % (26 + 10 + 4)]) + "|";
            }
        }
        return result;
    }
}
