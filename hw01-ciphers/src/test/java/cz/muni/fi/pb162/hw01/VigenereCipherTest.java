package cz.muni.fi.pb162.hw01;

import org.junit.jupiter.api.Test;

final class VigenereCipherTest extends CipherTestBase {

    @Test
    void disney() {
        String cipherText = "p7F z8g Nc 6Fc QbDLh03 Rv bR eG8U w8TNCb2 BWg JHAW9 EXlLO.";

        testVigenere(Quotes.DISNEY, cipherText, "WaltDisney");
    }

    @Test
    void lennon() {
        String cipherText = "UMCH CV aKjX KlJSHRV aEHy bRY jVB mOVb PjOFQr RWLH0 MOlHV.";

        testVigenere(Quotes.LENNON, cipherText, "JohnLennon");
    }

    @Test
    void mandela() {
        String cipherText = "gBF KUq0W7MU tFPZc uD ECW80A TMH4 QHN 80 OMZH3 I3FM80A, FX5 LG S85COO H74UR U8z8 eI r0OE.";

        testVigenere(Quotes.MANDELA, cipherText, "NelsonMandela");
    }

    @Test
    void secretMessage() {
        String cipherText = "lBAZ VwMKHAN CK MNuL6a!";

        testVigenere(Quotes.UNKNOWN_SPY, cipherText, "Secret");
    }

    private void testVigenere(String plainText, String cipherText, String key) {
        testCipher(
                new String[]{"-c", "VIGENERE", "--key", key, "-o", "ENCRYPT", "-t", plainText},
                new String[]{"-c", "VIGENERE", "--key", key, "-o", "DECRYPT", "-t", cipherText},
                plainText, cipherText, false
        );
    }
}
