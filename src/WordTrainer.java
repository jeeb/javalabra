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
        WordPairContainer wpc  = null;
        WordPairContainer wpc2 = null;

        /* Set default settings */
        SettingsManager sm = new SettingsManager();

        CLIArgumentsParser.parseArguments(args, sm);

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

        WordPairGame.playGame(wpc, sm);

        wpc = FileReader.createWPCFromFile(sm.getFileString());

        /*
         * Write the container's contents into a file called "herp" for debugging purposes.
         * Does not work if the file exists already.
         */
        FileWriter.writeWordPairContainerToFile(wpc, "herp");

        /* Try to parse the written file and create another container */
        wpc2 = FileReader.createWPCFromFile("herp");

        /* Let's check if they match! */
        if( wpc2 != null && wpc.equalsInContent(wpc2) ) {
            System.err.println("We are victorious!");
        }

        /* Exit normally */
        System.exit(0);
    }
}
