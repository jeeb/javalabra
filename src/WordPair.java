/**
 * Works as the "struct" data structure holding the word pairs' strings and helper functions.
 */
public class WordPair {
    private String word;
    private String pair;
    private String comment;

    /**
     *
     * @param word
     * @param pair
     * @param comment
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
     *
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @return
     */
    public String getPair() {
        return pair;
    }

    /**
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

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

    public boolean setComment(String comment) {
        if( comment == null ) {
            System.err.println("Error: Given comment is null!");
            return false;
        }

        this.comment = comment;

        return true;
    }

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

        if( this.getComment().equals(obj.getComment()) &&
            this.getWord().equals(obj.getWord()) &&
            this.getPair().equals(obj.getPair()) ) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Word: "    + this.getWord()    + "\n" +
               "Pair: "    + this.getPair()    + "\n" +
               "Comment: " + this.getComment() + "\n";
    }
}
