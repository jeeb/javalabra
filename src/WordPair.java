/**
 * Works as the "struct" data structure holding the word pairs' strings and helper functions.
 */
public class WordPair {
    private String word;
    private String pair;
    private String comment;

    /**
     * Instantiates a WordPair object with the given values.
     * @param word    The String to be used as the shown word in the word pair.
     * @param pair    The String to be used as the "answer" pair of the shown word.
     * @param comment The String to be used as the comment. Is not essential, and can be passed as an empty String.
     */
    private WordPair(String word, String pair, String comment) {
        this.word    = word;
        this.pair    = pair;
        this.comment = comment;
    }

    /**
     * Creates a WordPair from the given Strings. Validates data given to itself
     * and calls the private constructor that is not to be used by itself.
     *
     * Returns a WordPair object if it is successful, and null in all other cases.
     * @param word    The String to be used as the shown word in the word pair.
     * @param pair    The String to be used as the "answer" pair of the shown word.
     * @param comment The String to be used as the comment. Is not essential, and can be passed as an empty String.
     * @return a WordPair object if successful, null if unsuccessful.
     */
    public static WordPair createWordPair(String word, String pair, String comment) {
        if( word == null || pair == null || comment == null ) {
            System.err.println("Error: Variables cannot be null!");
            return null;
        }

        if( word.equalsIgnoreCase("") || pair.equalsIgnoreCase("") ) {
            System.err.println("Error: Essential variables cannot be empty!");
            return null;
        }

        return new WordPair(word, pair, comment);
    }

    /**
     * Creates a WordPair from the given Strings. Calls createWordPair(String, String, String) internally.
     *
     * Returns a WordPair object if it is successful, and null in all other cases.
     * @param word The String to be used as the shown word in the word pair.
     * @param pair The String to be used as the "answer" pair of the shown word.
     * @return a WordPair object if successful, null if unsuccessful.
     */
    public static WordPair createWordPair(String word, String pair) {
        return createWordPair(word, pair, "");
    }

    /**
     * Gets the currently set word from a given WordPair.
     * @return A String object that contains the currently set word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Gets the currently set pair word from a given WordPair.
     * @return A String object that contains the currently set pair word.
     */
    public String getPair() {
        return pair;
    }

    /**
     * Gets the currently set comment from a given WordPair.
     * @return A String object that contains the currently set comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Takes in a String object, that is then used to set the word for a given WordPair.
     * @param word a String object that will be used to set the word
     * @return true if successful, otherwise returns false.
     */
    public boolean setWord(String word) {
        if( word == null ) {
            System.err.println("Error: Given word is null!");
            return false;
        }

        if( word.equalsIgnoreCase("") ) {
            System.err.println("Error: Cannot change word to an empty string!");
            return false;
        }

        this.word = word;

        return true;
    }

    /**
     * Takes in a String object, that is then used to set the pair word for a given WordPair.
     * @param pair a String object that will be used to set the pair word
     * @return true if successful, otherwise returns false.
     */
    public boolean setPair(String pair) {
        if( pair == null ) {
            System.err.println("Error: Given pair is null!");
            return false;
        }

        if( pair.equalsIgnoreCase("") ) {
            System.err.println("Error: Cannot change pair to an empty string!");
            return false;
        }

        this.pair = pair;

        return true;
    }

    /**
     * Takes in a String object, that is then used to set the comment for a given WordPair.
     * @param comment a String object that will be used to set the comment
     * @return true if successful, otherwise returns false.
     */
    public boolean setComment(String comment) {
        if( comment == null ) {
            System.err.println("Error: Given comment is null!");
            return false;
        }

        this.comment = comment;

        return true;
    }

    /**
     * Takes in three String objects, that will then be used to change the contents of a given WordPair at once.
     *
     * Tries to leave the WordPair unchanged in case of some value failing to be set.
     * @param word    a String object that will be used to set the word
     * @param pair    a String object that will be used to set the pair word
     * @param comment a String object that will be used to set the comment
     * @return true if successful, otherwise returns false.
     */
    public boolean setContents(String word, String pair, String comment) {
        if( word == null || pair == null || comment == null ) {
            System.err.println("Error: A variable given was null!");
            return false;
        }

        String backup_word    = this.word;
        String backup_pair    = this.pair;
        String backup_comment = this.comment;

        if( this.setWord(word) && this.setPair(pair) && this.setComment(comment) ) {
            return true;
        } else {
            this.word    = backup_word;
            this.pair    = backup_pair;
            this.comment = backup_comment;

            return false;
        }
    }

    /**
     * Compares this WordPair against the given WordPair.
     *
     * Returns true if the contents of the given WordPair equal the contents of the compared WordPair.
     * @param obj The WordPair given to compare this WordPair against
     * @return true if the given WordPair represents a WordPair with the exactly same contents as the compared WordPair,
     *         false otherwise.
     */
    public boolean equalsInContent(WordPair obj) {
        /* General checks */
        if( obj == null ) {
            return false;
        }

        return ( this.getComment().equals(obj.getComment()) &&
                 this.getWord().equals(obj.getWord()) &&
                 this.getPair().equals(obj.getPair()) );
    }

    /**
     * Returns a "prettified" String representation of the WordPair object's contents.
     * @return A String object that represents the contents of a WordPair.
     */
    @Override
    public String toString() {
        return "Word: "    + this.getWord()    + "\n" +
               "Pair: "    + this.getPair()    + "\n" +
               "Comment: " + this.getComment() + "\n";
    }
}
