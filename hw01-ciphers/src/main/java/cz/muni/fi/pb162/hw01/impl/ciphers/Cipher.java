package cz.muni.fi.pb162.hw01.impl.ciphers;

/**
 * Interface mandating encrypt end decrypt functionality via {@link #ALPHABET}
 */
public interface Cipher {

    String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    /**
     * Encrypts given plain text using this cipher
     *
     * @param plainText text to be encrypted
     * @return encrypted (cipher) text
     */
    String encrypt(String plainText);

    /**
     * Decrypts given cipher text using this cipher
     *
     * @param cipherText text to be decrypted
     * @return original (plain) text
     */
    String decrypt(String cipherText);
}
