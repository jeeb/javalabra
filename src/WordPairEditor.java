/**
 * Allows for editing of word pairs in WordPairContainers
 */
public class WordPairEditor {
    private static void printWPC(WordPairContainer wpc) {
        if( wpc == null ) {
            return;
        }

        int i = 0;

        System.err.println(":::::::::::: LIST :::::::::::::");

        if( wpc.isEmpty() ) {
            System.err.println(":: WPC [empty]");
            System.err.println(":::::::::::::::::::::::::::::::");
            return;
        }

        for( WordPair wp : wpc ) {
            System.err.println(":: WPC [" + i + "] ( " + wp.getWord() + ", " + wp.getPair() + ", " + wp.getComment() +
            " )");
            i++;
        }

        return;
    }

    private static void printOptions(WordPairContainer wpc) {
        if( wpc == null ) {
            return;
        }

        System.err.println(":::::::::::: OPTS :::::::::::::");

        System.err.print(":: [A]dd, ");
        if( !wpc.isEmpty() ) {
            System.err.println("[E]dit, [R]emove");
        } else {
            System.err.println("");
        }
        System.err.println(":::::::::::::::::::::::::::::::");

        return;
    }

    private static boolean readOption() {
        System.err.print("Input? \n"); // remember to remove the \n
        return true;
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

        System.err.println(":: { Editor }");

        System.err.println(":: Reading file " + sm.getFileString() + "...");
        /* Try to read given file */
        WordPairContainer wpc = FileReader.createWPCFromFile(sm.getFileString());

        /* If reading failed */
        if( wpc == null ) {
            System.err.println(":: Reading failed\n:: \tassuming either herp derp or a file that doesn't exist.");
            System.err.println(":: Creating new word pair container into memory for editing");
            wpc = new WordPairContainer();
        } else {
            System.err.println(":: Reading succeeded! Got " + wpc.getWordPairCount() + " wordpairs.");
        }

        do {
            printWPC(wpc);
            printOptions(wpc);
            readOption();
            we_are_running = false;
            // give user the choice of either editing, creating or removing
            // read input
            // edit if given that output
            // otherwise set we_are_running to false
        } while( we_are_running );

        return wpc;
    }
}
