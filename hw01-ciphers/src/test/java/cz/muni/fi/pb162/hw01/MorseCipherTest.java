package cz.muni.fi.pb162.hw01;

import org.junit.jupiter.api.Test;

final class MorseCipherTest extends CipherTestBase {

    @Test
    void disney() {
        String cipherText = "-|....|.||.--|.-|-.--||-|---||--.|.|-||...|-|.-|.-.|-|.|-..||..|...||-|---||--.-|..-|..|-||-|.-|.-..|-.-|..|-.|--.||.-|-.|-..||-...|.|--.|..|-.||-..|---|..|-.|--.|.-.-.-|";

        testMorse(Quotes.DISNEY, cipherText);
    }

    @Test
    void lennon() {
        String cipherText = ".-..|..|..-.|.||..|...||.--|....|.-|-||....|.-|.--.|.--.|.|-.|...||.--|....|.|-.||-.--|---|..-||.-|.-.|.||-...|..-|...|-.--||--|.-|-.-|..|-.|--.||---|-|....|.|.-.||.--.|.-..|.-|-.|...|.-.-.-|";

        testMorse(Quotes.LENNON, cipherText);
    }

    @Test
    void mandela() {
        String cipherText = "-|....|.||--.|.-.|.|.-|-|.|...|-||--.|.-..|---|.-.|-.--||..|-.||.-..|..|...-|..|-.|--.||.-..|..|.|...||-.|---|-||..|-.||-.|.|...-|.|.-.||..-.|.-|.-..|.-..|..|-.|--.|--..--||-...|..-|-||..|-.||.-.|..|...|..|-.|--.||.|...-|.|.-.|-.--||-|..|--|.||.--|.||..-.|.-|.-..|.-..|.-.-.-|";

        testMorse(Quotes.MANDELA, cipherText);
    }

    @Test
    void secretMessage() {
        String cipherText = "-|....|..|...||--|.|...|...|.-|--.|.||..|...||...|.|-.-.|.-.|.|-|..--.|";

        testMorse(Quotes.UNKNOWN_SPY, cipherText);
    }

    private void testMorse(String plainText, String cipherText) {
        testCipher(
                new String[]{"-o", "ENCRYPT", "-t", plainText},
                new String[]{"-o", "DECRYPT", "-t", cipherText},
                plainText, cipherText, true
        );
    }
}
