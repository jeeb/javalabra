import java.util.Scanner;

/**
 * Allows for editing of word pairs in WordPairContainers
 */
public class WordPairEditor {
    private static Scanner reader = new Scanner(System.in);

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

    private static boolean addWordPair(WordPairContainer wpc) {
        String read_word = null;
        String read_pair = null;
        String read_comment = null;

        System.err.println(":: { Addition mode }");
        System.err.print("Word Input? ");

        if( reader.hasNext() ) {
            read_word = reader.next();
        }
        if( read_word == null ) {
            return false;
        }

        System.err.print("Pair Input? ");

        if( reader.hasNext() ) {
            read_pair = reader.next();
        }
        if( read_pair == null ) {
            return false;
        }

        System.err.print("Comment Input? ");

        if( reader.hasNext() ) {
            read_comment = reader.next();
        }
        if( read_comment == null ) {
            return false;
        }

        if( read_comment.equalsIgnoreCase("") ) {
            return wpc.addWordPair(read_word, read_pair);
        } else {
            return wpc.addWordPair(read_word, read_pair, read_comment);
        }
    }

    private static boolean readOption(WordPairContainer wpc) {
        if( wpc == null ) {
            return false;
        }

        boolean retval;
        String what_we_read = null;
        System.err.print("Input? "); // remember to remove the \n

        if( reader.hasNext() ) {
            what_we_read = reader.nextLine();
        }

        if( what_we_read == null ) {
            return false;
        }

        if( what_we_read.equalsIgnoreCase("a") ) {
            // addition mode
            return addWordPair(wpc);
        } else if( what_we_read.equalsIgnoreCase("e") && !wpc.isEmpty() ) {
            // editing mode
        } else if( what_we_read.equalsIgnoreCase("r") && !wpc.isEmpty() ) {
            // removal mode
        } else if( what_we_read.equalsIgnoreCase("") ) {
            return false;
        }

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
            if ( !readOption(wpc) ) {
                we_are_running = false;
            }
            // give user the choice of either editing, creating or removing
            // read input
            // edit if given that output
            // otherwise set we_are_running to false
        } while( we_are_running );

        return wpc;
    }
}
