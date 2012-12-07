/**
 * Main WordTrainer application class.
 *
 * Implements the main() of the application as well as most close related things.
 */
public class WordTrainer {
    /**
     * Main function (for testing currently)
     * @param args command line arguments given
     */
    public static void main(String[] args) {
        boolean running = true;
        int     retval  = 0;

        WordPairContainer wpc  = null;

        /* Set default settings */
        SettingsManager sm = new SettingsManager();

        CLIArgumentsParser.parseArguments(args, sm);

        do {
            if( sm.getMode() == SettingsManager.Mode.EDITOR ) {
                wpc = WordPairEditor.runEditor(sm);
            } else {
                wpc = FileReader.createWPCFromFile(sm.getFileString());
            }

            /* Error state happened and null was returned */
            if( wpc == null ) {
                System.err.println("Uh-oh, either editor or file reading returned null!");
                System.exit(1);
            }

            retval = WordPairGame.playGame(wpc, sm);

            if( retval != -2 && retval != 1 ) {
                running = false;
            }

        } while( running );

        /*
         * Write the container's contents into a file.
         * Does not work if the file exists already.
         */
        if( !FileWriter.writeWordPairContainerToFile(wpc, sm.getFileString()) ) {
            retval = 1;
        }

        /* Exit */
        System.exit(retval);
    }
}
