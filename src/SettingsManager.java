/**
 * Implements the general settings reading and writing, as well as setting the defaults in a SettingsManager object.
 */
public class SettingsManager {
    /* List the possible game modes */
    public enum Mode {
        GAME, EDITOR
    }

    private Mode   game_mode;   // Is the game in edit mode
    private String file_string; // The string that is a path towards the WordPair file

    public SettingsManager() {
        /* Set defaults */
        game_mode   =  Mode.GAME;
        file_string = "herp";
    }

    public boolean setMode(Mode mode) {
        if( mode == null ) {
            return false;
        }

        game_mode = mode;

        return true;
    }

    public boolean setFileString(String string) {
        if( string == null ) {
            return false;
        }

        if( string.equalsIgnoreCase("") ) {
            return false;
        }

        file_string = string;

        return true;
    }

    public Mode getMode() {
        return game_mode;
    }

    public String getFileString() {
        return file_string;
    }
}
