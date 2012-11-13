import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 13.11.2012
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class WordPairContainer {
    /* ArrayList full of WordPairs */
    private ArrayList<WordPair> wordpairs = null;
    /* Random number generator */
    Random generator = new Random();

    public WordPairContainer() {
        wordpairs = new ArrayList<WordPair>();
    }

    public boolean addWordPair(WordPair wp) {
        /* Check if wordpairs is null */
        if( wordpairs == null ) {
            System.err.println("Error: Word pair list is null!");
            return false;
        }

        wordpairs.add(wp);
        return true;
    }

    public boolean removeWordPair(WordPair wp) {
        /* Check if wordpairs is null */
        if( wordpairs == null ) {
            System.err.println("Error: Word pair list is null!");
            return false;
        }

        /* Check if wordpairs is empty */
        if( wordpairs.isEmpty() ) {
            System.err.println("Error: Word pair list is empty!");
            return false;
        }

        /* Check if wordpairs contains given object */
        if ( !wordpairs.contains(wp) ) {
            System.err.println("Error: No such object in the word pair!");
            return false;
        }

        wordpairs.remove(wp);
        return true;
    }

    public WordPair getRandomWordPair() {
        /* Check if wordpairs is null */
        if( wordpairs == null ) {
            System.err.println("Error: Word pair list is null!");
            return null;
        }

        /* Check if wordpairs is empty */
        if( wordpairs.isEmpty() ) {
            System.err.println("Error: Word pair list is empty!");
            return null;
        }

        /* Pick a random word pair within the list */
        int selection = generator.nextInt(wordpairs.size());

        /* Grab the word pair from the array list */
        return wordpairs.get(selection);
    }

    public WordPair getWordPair(int selection) {
        /* Check if wordpairs is null */
        if( wordpairs == null ) {
            System.err.println("Error: Word pair list is null!");
            return null;
        }

        if( selection > (wordpairs.size() - 1) || selection < 0 ) {
            System.err.println("Error: Number out of range!");
            return null;
        }

        return wordpairs.get(selection);
    }

    public int getWordPairCount() {
        /* Check if wordpairs is null */
        if( wordpairs == null ) {
            System.err.println("Error: Word pair list is null!");
            return 0;
        }

        return wordpairs.size();
    }
}
