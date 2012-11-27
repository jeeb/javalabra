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

    /**
     * Instantiates a SettingsManager object and sets the default values.
     */
    public SettingsManager() {
        /* Set defaults */
        game_mode   =  Mode.GAME;
        file_string = "herp";
    }

    /**
     * Takes in a Mode "enum" object, that is then used to set the game mode.
     * @param mode a Mode "enum" object that will be used to set the game mode
     * @return true if successful, otherwise returns false.
     */
    public boolean setMode(Mode mode) {
        if( mode == null ) {
            return false;
        }

        game_mode = mode;

        return true;
    }

    /**
     * Takes in a String object, that is then used to set the file path string.
     * @param string a String object that will be used to set the file path string
     * @return true if successful, otherwise returns false.
     */
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

    /**
     * Gets the currently set game mode.
     * @return A Mode object that identifies the currently set game mode.
     */
    public Mode getMode() {
        return game_mode;
    }

    /**
     * Gets the currently set file name string.
     * @return A String object that contains the currently set file path string.
     */
    public String getFileString() {
        return file_string;
    }
}
