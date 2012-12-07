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

        int i = 1;

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

        return wpc.addWordPair(read_word, read_pair, read_comment);
    }

    private static boolean editWordPair(WordPairContainer wpc) {
        if( wpc == null ) {
            return false;
        }

        int      read_int     = -1;
        String   read_word    = null;
        String   read_pair    = null;
        String   read_comment = null;
        WordPair read_wp      = null;


        System.err.println(":: { Editing mode }");
        System.err.print("Edit which? ");

        if( reader.hasNextInt() ) {
            read_int = reader.nextInt();
        }

        if( read_int < 1 || read_int > wpc.getWordPairCount() ) {
            System.err.println(":: Invalid number");
            return false;
        }

        read_wp = wpc.getWordPair(read_int - 1);
        if( read_wp == null ) {
            return false;
        }

        System.err.println(":: Editing word pair number " + read_int + ", {\n" +
                           read_wp.toString() + ":: }");

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

        return read_wp.setContents(read_word, read_pair, read_comment);
    }

    private static boolean readOption(WordPairContainer wpc) {
        if( wpc == null ) {
            return false;
        }

        String what_we_read = null;
        System.err.print("Input? "); // remember to remove the \n

        if( reader.hasNext() ) {
            what_we_read = reader.next();
        }

        if( what_we_read == null ) {
            return false;
        }

        if( what_we_read.equalsIgnoreCase("a") ) {
            // addition mode
            return addWordPair(wpc);
        } else if( what_we_read.equalsIgnoreCase("e") && !wpc.isEmpty() ) {
            // editing mode
            return editWordPair(wpc);
        } else if( what_we_read.equalsIgnoreCase("r") && !wpc.isEmpty() ) {
            // removal mode
        } else {
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

            if( !readOption(wpc) ) {
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
