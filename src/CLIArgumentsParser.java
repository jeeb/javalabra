/**
 * Command Line Interface Argument Parser
 *
 * Implements the command line interface argument parsing.
 */
public class CLIArgumentsParser {
    /**
     * Parses the command line arguments given, and writes what they set into the provided SettingsManager object.
     *
     * TODO: !Split into multiple functions!
     * @param args An Array of String objects containing the command line arguments to be parsed.
     * @param sm   A SettingsManager object to which the parsed settings will be written.
     */
    public static boolean parseArguments(String[] args, SettingsManager sm) {
        if( args == null || sm == null ) {
            System.err.println("Warning: arguments or settings manager was null!");
            return false;
        }

        if( args.length == 0 ) {
            return true;
        }

        for( int i = 0; i < args.length; i++ ) {
            if( args[i].equalsIgnoreCase("--wpfile") || args[i].equalsIgnoreCase("-w") ) {
                if( i + 1 < args.length ) {
                    System.err.println("Input file name set: " + args[i + 1]);
                    sm.setFileString(args[i + 1]);

                    i++;
                } else {
                    System.err.println("Warning: Argument " + args[i] + " found, but is last!?");
                }

                continue;
            } else if( args[i].equalsIgnoreCase("--mode") || args[i].equalsIgnoreCase("-m") ) {
                if( i + 1 < args.length ) {
                    if( args[i + 1].equalsIgnoreCase("editor") ) {
                        System.err.println("Mode set: editor");
                        sm.setMode(SettingsManager.Mode.EDITOR);

                        i++;
                    } else if( args[i + 1].equalsIgnoreCase("game") ) {
                        System.err.println("Mode set: game");
                        sm.setMode(SettingsManager.Mode.GAME);

                        i++;
                    } else {
                        System.err.println("Mode not set, unknown mode " + args[i + 1]);

                        i++;
                    }
                } else {
                    System.err.println("Warning: Argument " + args[i] + "found, but is last!?");
                }

                continue;
            } else {
                System.err.println("Warning: Unknown argument " + args[i]);

                continue;
            }
        }

        return true;
    }
}
