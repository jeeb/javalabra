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
}
