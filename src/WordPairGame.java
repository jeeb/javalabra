/**
 * The actual word pair game
 */
public class WordPairGame {
    private static void printMainMenu() {
        System.err.println(":::::::::::::::::::::::::::::::");
        System.err.println("::     { !WordTrainer! }     ::");
        System.err.println(":::::::::::::::::::::::::::::::");

        System.err.println(":: Welcome to Word Trainer!  ::");
        System.err.println(":: Please select option      ::");

        System.err.println(":::::::::::: OPTS :::::::::::::");
        System.err.println(":: [N]ew game [E]ditor       ::");
        System.err.println(":: [Q]uit the game           ::");
        System.err.println(":: (or any other key)        ::");
        System.err.println(":::::::::::::::::::::::::::::::");
    }

    private static boolean printWordPair(WordPair wp) {
        if( wp == null ) {
            return false;
        }

        System.err.println(":: Here is a word, what is its pair or answer?");
        System.err.println(":: > \"" + wp.getWord() + "\"");
        System.err.println(":: Hint/Comment: " + wp.getComment());

        return true;
    }

    private static boolean askAndCheckAnswer(WordPair wp) {
        if( wp == null ) {
            return false;
        }

        String answer = StdinReader.readLine();
        if( answer == null ) {
            return false;
        }

        if( answer.equalsIgnoreCase("") ) {
            return false;
        } else if( answer.equalsIgnoreCase(wp.getPair()) ) {
            System.err.print(":: Yes! The pair or answer to \"" + wp.getWord() + "\" is\n " +
                             "\"" + wp.getPair() + "\"");
            return true;
        } else {
            System.err.println(":: Unfortunately you were incorrect. The pair or answer to \"" + wp.getWord() + "\" " +
                               "is \"" + wp.getPair() + "\"");
            return true;
        }
    }

    private static boolean wordTrainerLoop(WordPairContainer wpc) {
        boolean running = true;

        if( wpc == null ) {
            return false;
        }

        WordPair wp = null;

        do {
            wp = wpc.getRandomWordPair();
            if( wp == null ) {
                return false;
            }

            printWordPair(wp);

            if( !askAndCheckAnswer(wp) ) {
                running = false;
            }
        } while( running );

        return true;
    }

    private static boolean readOption(WordPairContainer wpc, SettingsManager sm) {
        if( wpc == null ) {
            return false;
        }

        String read = null;

        System.err.print("Input? ");
        read = StdinReader.readLine();
        if( read == null ) {
            return false;
        }

        if( read.equalsIgnoreCase("n") ) {
            if( wpc.isEmpty() ) {
                System.err.println(":: Cannot play with no word pairs.");
                return true;
            }
            wordTrainerLoop(wpc);
            return true;
        } else if( read.equalsIgnoreCase("e") ) {
            sm.setMode( SettingsManager.Mode.EDITOR );
            return true;
        } else {
            return false;
        }
    }

    /**
     * Starts the game loop, begins the Word Trainer game
     * @param wpc The WordPairContainer object to be used during gameplay
     * @param sm  The SettingsManager object to be used during gameplay
     * @return integer value that sends out the returned result of the function.
     *
     * This can be one of the next values:
     *  -  '0' if nothing special happened and everything went fine.
     *  - '-1' if an error state was found during initialization.
     *  -  '1' if the user switched to the editing mode.
     *  -  '2' if after the game loop ends the WordPairContainer is found to be empty.
     *
     */
    public static int playGame(WordPairContainer wpc, SettingsManager sm) {
        boolean running = true;

        if( wpc == null ) {
            return -1;
        }

        do {
            printMainMenu();

            if( !readOption(wpc, sm) ) {
                running = false;
            }

            if( sm.getMode() == SettingsManager.Mode.EDITOR ) {
                return 1;
            }
        } while( running );

        if( wpc.isEmpty() ) {
            return 2;
        }

        return 0;
    }
}
