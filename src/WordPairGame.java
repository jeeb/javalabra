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
           // wordTrainerLoop(wpc, sm);
           return true;
       } else if( read.equalsIgnoreCase("e") ) {
           sm.setMode( SettingsManager.Mode.EDITOR );
           return true;
       } else {
           return false;
       }
   }

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
        } while( running );

        return 0;
    }
}
