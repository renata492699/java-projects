package cz.muni.fi.pb162.hw01.impl;

import com.beust.jcommander.Parameter;
import cz.muni.fi.pb162.hw01.CipherType;
import cz.muni.fi.pb162.hw01.Operation;
import cz.muni.fi.pb162.hw01.cmd.CipherTypeConverter;
import cz.muni.fi.pb162.hw01.cmd.CommandLine;
import cz.muni.fi.pb162.hw01.cmd.OperationConverter;

/**
 * Application class represents the command line interface of this application.
 *
 * You are expected to implement  the  {@link Application#run()} method
 *
 * @author jcechace
 */
public class Application {

    @Parameter(names = {"--cipher", "-c"}, converter = CipherTypeConverter.class)
    private CipherType cipherType = CipherType.MORSE_CODE;

    @Parameter(names = {"--operation", "-o"}, required = true, converter = OperationConverter.class)
    private Operation operation;

    @Parameter(names = {"--text", "-t"}, required = true)
    private String text;

    @Parameter(names = {"--shift"})
    private int caesarShift;

    @Parameter(names = {"--key"})
    private String vigenereKey;

    @Parameter(names = "--help", help = true)
    private boolean showUsage = false;

    /**
     * Application entry point
     *
     * @param args command line arguments of the application
     */
    public static void main(String[] args) {
        Application app = new Application();

        CommandLine cli = new CommandLine(app);
        cli.parseArguments(args);

        if (app.showUsage) {
            cli.showUsage();
        } else {
            app.run();
        }
    }

    /**
     * Application runtime logic
     */
    private void run() {
        // TODO: Remove the following lines and implement the functionality
        System.err.println("Not implemented yet!");
        System.exit(2);
    }
}
