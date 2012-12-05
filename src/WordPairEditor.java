/**
 * Allows for editing of word pairs in WordPairContainers
 */
public class WordPairEditor {
    private static void printWPC(WordPairContainer wpc) {
        if( wpc == null ) {
            return;
        }

        int i = 0;
        if( wpc.isEmpty() ) {
            System.err.println(":: WPC [empty]");
            return;
        }

        for( WordPair wp : wpc ) {
            System.err.println(":: WPC [" + i + "] ( " + wp.getWord() + ", " + wp.getPair() + ", " + wp.getComment() +
            " )");
            i++;
        }

        return;
    }

    /**
     *
     * @param sm
     * @return
     */
    public static WordPairContainer runEditor(SettingsManager sm) {
        boolean we_are_running = true;

        if( sm == null ) {
            return null;
        }

        System.err.println("{ Editor }");

        System.err.print(":: Reading file " + sm.getFileString() + ": ");
        /* Try to read given file */
        WordPairContainer wpc = FileReader.createWPCFromFile(sm.getFileString());

        /* If reading failed */
        if( wpc == null ) {
            System.err.println("failed\n\tassuming either herp derp or a file that doesn't exist.");
            System.err.println(":: Creating new word pair container into memory for editing");
            wpc = new WordPairContainer();
        } else {
            System.err.println("success!");
        }

        do {
            printWPC(wpc);
            we_are_running = false;
            // give user the choice of either editing, creating or removing
            // read input
            // edit if given that output
            // otherwise set we_are_running to false
        } while( we_are_running );

        return wpc;
    }
}
