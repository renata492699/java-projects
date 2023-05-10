package cz.muni.fi.pb162.hw02.impl;

import com.beust.jcommander.Parameter;
import cz.muni.fi.pb162.hw02.Messages;
import cz.muni.fi.pb162.hw02.cmd.CommandLine;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;


/**
 * Application class represents the command line interface of this application.
 * <p>
 * You are expected to implement  the  {@link Application#run(CommandLine)} method
 *
 * @author jcechace
 */
public class Application {
    @Parameter(names = {"--help"}, help = true)
    private boolean showUsage = false;

    @Parameter(names = {"--file"}, required = true)
    private String path;

    @Parameter(description = "\nSupported operations:\n" +
            "-u -> Filter unique lines.\n" +
            "-s -> Sort lines by natural ordering.\n" +
            "-d -> Filter duplicate lines.\n" +
            "lines -> Print lines (default operation).\n" +
            "count -> Count lines.\n" +
            "sizes -> Counts the characters for each line.\n" +
            "similar -> Lists pairs of most similar lines according to Levenshtein distance.\n")
    private List<String> operations = new ArrayList<>();

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
            app.run(cli);
        }
    }

    /**
     * Application runtime logic
     *
     * @param cli command line interface
     */
    private void run(CommandLine cli) {
        try {
            Operations text = new TerminalOperations(path);
            if (operations.contains("-u") && operations.contains("-d")) {
                System.err.println(Messages.INVALID_OPTION_COMBINATION);
                cli.showUsage();
            }
            if (operations.isEmpty()) {
                text.lines();
                return;
            }
            for (String operation : operations) {
                switch (operation) {
                    case "-u": text.unique();
                        break;
                    case "-s": text.sort();
                        break;
                    case "-d": text.duplicates();
                        break;
                    case "lines": text.lines();
                        break;
                    case "count": text.count();
                        break;
                    case "sizes": text.sizes();
                        break;
                    case "similar": text.similar();
                        break;
                    default:System.err.println("Invalid command.");
                }
            }
            Set<String> terminal = Set.of("-u", "-s", "-d");
            if (terminal.contains(operations.get(operations.size() - 1))) {
                text.lines();
            }
        } catch (IOException ex) {
            System.err.printf((Messages.IO_ERROR), path);

        }
    }
}
