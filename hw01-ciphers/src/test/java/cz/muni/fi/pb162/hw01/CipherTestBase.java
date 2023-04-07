package cz.muni.fi.pb162.hw01;

import cz.muni.fi.pb162.hw01.impl.Application;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

abstract class CipherTestBase {

    private static PrintStream outBcp;

    private static ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static PrintStream outStream = new PrintStream(out);

    @BeforeAll
    static void setupClass() {
        outBcp = System.out;
        System.setOut(outStream);
    }

    @AfterAll
    static void teardownClass() {
        System.setOut(outBcp);
    }

    void testCipher(String[] cipherCmd, String[] decipherCmd, String plainText, String cipherText, boolean ignoreCase) {
        SoftAssertions.assertSoftly(softly -> {
            Application.main(cipherCmd);
            assertOutput(softly, cipherText, ignoreCase, "Encryption of \"" + plainText + "\"");

            Application.main(decipherCmd);
            assertOutput(softly, plainText, ignoreCase, "Decryption \"" + cipherText + "\"");
        });
    }

    private void assertOutput(SoftAssertions softly, String expected, boolean ignoreCase, String description) {
        String actual = out.toString().trim();

        if (ignoreCase) {
            softly.assertThat(actual)
                    .as(description)
                    .containsIgnoringCase(expected);
        } else {
            softly.assertThat(actual)
                    .as(description)
                    .contains(expected);
        }

        out.reset();
    }
}
