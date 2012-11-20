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

        /* Check for argument count */
        if( args.length < 1 ) {
            System.err.println("Error: Give at least one argument");
            System.exit(1);
        }

        /* Print the file name used for debug purposes, and create the path */
        System.err.println("File name set: " + args[0]);

        wpc = FileReader.createWPCFromFile(args[0]);

        /* Error state happened and null was returned */
        if( wpc == null ) {
            System.err.println("Error state happened, exiting program");
            System.exit(1);
        }

        /*
         * Write the container's contents into a file called "herp" for debugging purposes.
         * Does not work if the file exists already.
         */
        FileWriter.WriteWordPairContainerToFile(wpc, "herp");

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
