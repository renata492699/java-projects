package cz.muni.fi.pb162.hw01.impl;

import cz.muni.fi.pb162.hw01.Utils;
import cz.muni.fi.pb162.hw01.impl.ciphers.Cipher;

/**
 * Vigenere Cipher class implements interface Cipher.
 *
 * Represents a series of interwoven Caesar ciphers.
 * Using a keyword, each character of plaintext message is encrypted by Casar cipher
 * with shift equal to the distance between given plaintext character and
 * character at corresponding position in the key.
 *
 * @author Renata Urbanova
 */
public class VigenereCipher implements Cipher {
    private final String key;
    private final char[] alphabet = Cipher.ALPHABET.toCharArray();

    /**
     * Create Vinegere Cipher object with given key.
     *
     * @param key keyword for encrypting and decrypting
     */
    public VigenereCipher(String key) {
        this.key = key;
    }

    @Override
    public String decrypt(String cipherText) {
        return encryptDecrypt(cipherText, true);
    }

    @Override
    public String encrypt(String plainText) {
        return encryptDecrypt(plainText, false);
    }

    /**
     * This method helps with decrypting and encrypting.
     *
     * In case of decrypting we subtract the position of "normal" char in the alphabet
     * from the position of the key in the alphabet.
     * The key char has the same position as the char we want to decrypt.
     *
     * In case of encrypting the positions are adding up instead of subtracting.
     *
     * The characters such as "!", ".", "," and "?" or space are preserved.
     *
     * @param text we want to encrypt or decrypt (code/ plain text)
     * @param decrypt is false if we are decrypting, otherwise true
     * @return encrypted/ decrypted string
     */
    private String encryptDecrypt(String text, boolean decrypt) {
        String result = "";
        char letter, keyPair;
        int charIndex, keyIndex;
        for (int i = 0; i < text.length(); i++) {
            letter = text.charAt(i);
            if (letter == ' ' || letter == '.' || letter == '?' || letter == '!' || letter == ',') {
                result += letter;
            } else {
                charIndex = Utils.searchIndex(alphabet, letter);
                keyPair = key.charAt(i % key.length());
                keyIndex = Utils.searchIndex(alphabet, keyPair);
                if (decrypt) {
                    result += alphabet[(charIndex - keyIndex + alphabet.length) % alphabet.length];
                } else {
                    result += alphabet[(charIndex + keyIndex) % alphabet.length];
                }
            }
        }

        return result;
    }
}
