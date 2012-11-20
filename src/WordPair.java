/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 13.11.2012
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
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
    public WordPair(String word, String pair, String comment) {
        this.word    = word;
        this.pair    = pair;
        this.comment = comment;
    }

    /**
     *
     * @param word
     * @param pair
     */
    public WordPair(String word, String pair) {
        this.word    = word;
        this.pair    = pair;
        this.comment = "";
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

    /**
     * Compares this WordPair against the specified object. The result is true if the object is not null
     * and if it represents a WordPair with the same contents.
     *
     * @param obj The object to compare this WordPair against
     * @return true if the given object represents a WordPair with the exactly same contents,
     *         false otherwise.
     */
    public boolean equalsInContent(Object obj) {
        /* General checks */
        if( obj == null || obj.getClass() != getClass() ) {
            return false;
        }

        /* Cast the object into the needed type */
        final WordPair compared = (WordPair) obj;

        if( this.getComment().equals(compared.getComment()) &&
            this.getWord().equals(compared.getWord()) &&
            this.getPair().equals(compared.getPair())) {

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
