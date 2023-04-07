package cz.muni.fi.pb162.hw01;

import org.junit.jupiter.api.Test;

final class CaesarCipherTest extends CipherTestBase {

    @Test
    void disney() {
        String cipherText = "Znk 2g4 zu mkz yzgxzkj oy zu w0oz zgrqotm gtj hkmot juotm.";

        testCaesar(Quotes.DISNEY, cipherText, 6);
    }

    @Test
    void lennon() {
        String cipherText = "Zwts w6 Avo7 vo33s16 Avs1 C28 o5s p86C 0oyw1u 27vs5 3zo16.";

        testCaesar(Quotes.LENNON, cipherText, 14);
    }

    @Test
    void mandela() {
        String cipherText = "kyv x8vrAv9A x258F z4 2zCz4x 2zv9 45A z4 4vCv8 wr22z4x, sBA z4 8z9z4x vCv8F Az3v Dv wr22.";

        testCaesar(Quotes.MANDELA, cipherText, 17);
    }

    @Test
    void secretMessage() {
        String cipherText = "Vjku oguucig ku ugetgv!";

        testCaesar(Quotes.UNKNOWN_SPY, cipherText, 2);
    }

    private void testCaesar(String plainText, String cipherText, int shift) {
        testCipher(
                new String[]{"-c", "CAESAR", "--shift", Integer.toString(shift), "-o", "ENCRYPT", "-t", plainText},
                new String[]{"-c", "CAESAR", "--shift", Integer.toString(shift), "-o", "DECRYPT", "-t", cipherText},
                plainText, cipherText, false
        );
    }
}
