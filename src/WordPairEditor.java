import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Allows for editing of word pairs in WordPairContainers
 */
public class WordPairEditor {
    // We shall read in lolUTF-8
    private static Charset charset = Charset.forName("UTF-8");

    // We also have a BufferedReader here
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, charset));

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

        System.err.print(":: [A]dd");
        if( !wpc.isEmpty() ) {
            System.err.println(", [E]dit, [R]emove");
        } else {
            System.err.println("");
        }
        System.err.println(":: [Q]uit (or any other key)");
        System.err.println(":::::::::::::::::::::::::::::::");

        return;
    }

    private static boolean addWordPair(WordPairContainer wpc) {
        String read_word = null;
        String read_pair = null;
        String read_comment = null;

        System.err.println(":: { Addition mode }");

        try {
            System.err.print("Word Input? ");
            read_word = reader.readLine();
            if( read_word == null ) {
                return false;
            }

            System.err.print("Pair Input? ");
            read_pair = reader.readLine();
            if( read_pair == null ) {
                return false;
            }

            System.err.print("Comment Input? ");
            read_comment = reader.readLine();
            if( read_comment == null ) {
                return false;
            }

            return wpc.addWordPair(read_word, read_pair, read_comment);
        } catch (IOException e) {
            System.err.println(":: Error reading input:\n\t" + e);
            return false;
        }
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

        try {
            System.err.print("Edit which? ");
            read_word = reader.readLine();
            if( read_word == null ) {
                return false;
            }

            read_int = Integer.parseInt(read_word, 10);

            if( read_int < 1 || read_int > wpc.getWordPairCount() ) {
                System.err.println(":: Invalid number");
                return false;
            }

            read_wp = wpc.getWordPair(read_int - 1);
            if( read_wp == null ) {
                return false;
            }

            read_word = null;

            System.err.println(":: Editing word pair number " + read_int + ", {\n" +
                               read_wp.toString() + ":: }");

            System.err.print("Word Input? ");
            read_word = reader.readLine();
            if( read_word == null ) {
                return false;
            }

            System.err.print("Pair Input? ");
            read_pair = reader.readLine();
            if( read_pair == null ) {
                return false;
            }

            System.err.print("Comment Input? ");
            read_comment = reader.readLine();
            if( read_comment == null ) {
                return false;
            }

            return read_wp.setContents(read_word, read_pair, read_comment);
        } catch (IOException e) {
            System.err.println(":: Error reading input:\n\t" + e);
            return false;
        }
    }

    private static boolean removeWordPair(WordPairContainer wpc) {
        if( wpc == null ) {
            return false;
        }

        String read_string = null;
        int    read_int    = -1;

        System.err.println(":: { Removal mode }");
        System.err.print("Remove which? ");

        try {
            read_string = reader.readLine();
        } catch (IOException e) {
            System.err.println(":: Error reading input:\n\t" + e);
            return false;
        }

        if( read_string == null ) {
            return false;
        }

        read_int = Integer.parseInt(read_string, 10);

        if( read_int < 1 || read_int > wpc.getWordPairCount() ) {
            System.err.println(":: Invalid number");
            return false;
        }

        return wpc.removeWordPair(wpc.getWordPair(read_int - 1));
    }

    private static boolean readOption(WordPairContainer wpc) {
        if( wpc == null ) {
            return false;
        }

        String what_we_read = null;
        System.err.print("Input? "); // remember to remove the \n

        try {
            what_we_read = reader.readLine();
        } catch (IOException e) {
            System.err.println(":: Error reading input:\n\t" + e);
            return false;
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
