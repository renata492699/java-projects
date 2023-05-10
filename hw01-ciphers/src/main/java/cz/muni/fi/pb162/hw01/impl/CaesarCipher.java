package cz.muni.fi.pb162.hw01.impl;

import cz.muni.fi.pb162.hw01.Utils;
import cz.muni.fi.pb162.hw01.impl.ciphers.Cipher;

/**
 * Caesar Cipher class implements Cipher interface.
 *
 * A simple cipher in which a plaintext letter is replaced by a letter
 * following some fixed number of positions later in the alphabet.
 *
 * @author Renata Urbanova
 */

public class CaesarCipher implements Cipher {
    private final int shift;
    private final char[] alphabet = Cipher.ALPHABET.toCharArray();

    /**
     * Create CaesarCipher object with a given shift.
     *
     * @param shift of the given text
     */
    public CaesarCipher (int shift) {
        this.shift = shift;
    }

    @Override
    public String decrypt(String cipherText) {
        return encryptDecrypt(cipherText, -shift);
    }

    @Override
    public String encrypt(String plainText) {
        return encryptDecrypt(plainText, shift);
    }

    /**
     * This method helps with encrypting and decrypting.
     *
     * In case of decrypting the code must be shifted to the left
     * so we are subtracting shift from the coded position.
     * The shift in this case is given as a negative value.
     *
     * In case of encrypting the char position is shifted (increased) by shift value.
     *
     * The characters such as "!", ".", "," and "?" or space are preserved.
     * @param text we are encrypting/ decrypting
     * @param shift represents, of what we are shifting
     * @return decrypted/ encrypted text
     */
    private String encryptDecrypt(String text, int shift) {
        char letter;
        String result = "";
        int newIndex, charIndex;
        for (int i = 0; i < text.length(); i++) {
            letter = text.charAt(i);
            if (letter == ' ' || letter == '?' || letter == '!' || letter == ',' || letter == '.') {
                result += letter;
            } else {
                charIndex = Utils.searchIndex(alphabet, letter);
                newIndex = charIndex + shift;
                result +=  alphabet[(newIndex + alphabet.length) % alphabet.length];
            }
        }
        return result;
    }
}
