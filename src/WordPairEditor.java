import java.io.IOException;

/**
 * Allows for editing of word pairs in WordPairContainers
 */
public class WordPairEditor {
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
    }

    private static void printOptions(WordPairContainer wpc) {
        if( wpc == null ) {
            return;
        }

        System.err.println(":::::::::::: OPTS :::::::::::::");

        System.err.print(":: [A]dd");
        if( !wpc.isEmpty() ) {
            System.err.println(", [E]dit, [R]emove");
        } else {
            System.err.println("");
        }
        System.err.println(":: [Q]uit (or any other key)");
        System.err.println(":::::::::::::::::::::::::::::::");
    }

    private static boolean addWordPair(WordPairContainer wpc) {
        String read_word = null;
        String read_pair = null;
        String read_comment = null;

        System.err.println(":: { Addition mode }");

        System.err.print("Word Input? ");
        read_word = StdinReader.readLine();
        if( read_word == null ) {
            return false;
        }

        System.err.print("Pair Input? ");
        read_pair = StdinReader.readLine();
        if( read_pair == null ) {
            return false;
        }

        System.err.print("Comment Input? ");
        read_comment = StdinReader.readLine();
        if( read_comment == null ) {
            return false;
        }

        return wpc.addWordPair(read_word, read_pair, read_comment);
    }

    private static boolean editWordPair(WordPairContainer wpc) {
        boolean running = true;

        if( wpc == null ) {
            return false;
        }

        Integer  read_int     = null;
        String   read_word    = null;
        String   read_pair    = null;
        String   read_comment = null;
        WordPair read_wp      = null;

        System.err.println(":: { Editing mode }");
        System.err.println(":: Please enter a value of an according type, everything else will cancel.");

        do {
            System.err.print("Edit which word pair [number] ? ");
            read_int = StdinReader.readInteger();
            if( read_int == null ) {
                return false;
            }

            if( read_int < 1 || read_int > wpc.getWordPairCount() ) {
                System.err.println(":: Invalid number");
            } else {
                running = false;
            }
        } while( running );

        read_wp = wpc.getWordPair(read_int - 1);
        if( read_wp == null ) {
            return false;
        }

        System.err.println(":: Editing word pair number " + read_int + ", {\n" +
                           read_wp.toString() + ":: }");

        System.err.println(":: Please enter a value of an according type, empty input will leave value unmodified.");

        running = true;

        do {
            System.err.print("Word Input [non-empty] ? ");
            read_word = StdinReader.readLine();
            if( read_word == null ) {
                return false;
            }

            if( read_word.equalsIgnoreCase("") ) {
                System.err.println(":: Empty input, using original value");
                read_word = read_wp.getWord();
                running = false;
            } else {
                running = false;
            }
        } while( running );

        running = true;

        do {
            System.err.print("Pair Input [non-empty] ? ");
            read_pair = StdinReader.readLine();
            if( read_pair == null ) {
                return false;
            }

            if( read_pair.equalsIgnoreCase("") ) {
                System.err.println(":: Empty input, using original value");
                read_pair = read_wp.getPair();
                running = false;
            } else {
                running = false;
            }
        } while( running );

        System.err.println(":: Please enter a value, can be empty. Cannot cancel.");

        System.err.print("Comment Input? ");
        read_comment = StdinReader.readLine();
        if( read_comment == null ) {
            return false;
        }

        if( read_word.equals(read_wp.getWord()) && read_pair.equals(read_wp.getPair()) &&
            read_comment.equals(read_wp.getComment()) ) {
            // Don't change word pair's data
            System.err.println(":: Data unchanged, returning.");
            return true;
        }

        return read_wp.setContents(read_word, read_pair, read_comment);
    }

    private static boolean removeWordPair(WordPairContainer wpc) {
        boolean running = true;

        if( wpc == null ) {
            return false;
        }

        Integer read_int    = null;

        System.err.println(":: { Removal mode }");
        System.err.println(":: Please enter a value of an according type, everything else will cancel.");

        do {
            System.err.print("Remove which word pair [number] ? ");

            read_int = StdinReader.readInteger();
            if( read_int == null ) {
                return false;
            }

            if( read_int < 1 || read_int > wpc.getWordPairCount() ) {
                System.err.println(":: Error: Number out of range!");
            } else {
                running = false;
            }
        } while( running );

        return wpc.removeWordPair(wpc.getWordPair(read_int - 1));
    }

    private static boolean readOption(WordPairContainer wpc) {
        if( wpc == null ) {
            return false;
        }

        String read = null;

        System.err.print("Input? ");
        read = StdinReader.readLine();
        if( read == null ) {
            return false;
        }

        if( read.equalsIgnoreCase("a") ) {
            // addition mode
            return addWordPair(wpc);
        } else if( read.equalsIgnoreCase("e") && !wpc.isEmpty() ) {
            // editing mode
            return editWordPair(wpc);
        } else if( read.equalsIgnoreCase("r") && !wpc.isEmpty() ) {
            // removal mode
            return removeWordPair(wpc);
        } else {
            return false;
        }
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
