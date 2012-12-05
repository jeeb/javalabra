import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Allows for easier handling of a collection (ArrayList) of WordPair objects via relatively usable interfaces.
 */
public class WordPairContainer implements Iterable<WordPair> {
    /* ArrayList full of WordPairs */
    private ArrayList<WordPair> wordpairs = null;
    /* Random number generator */
    Random generator = new Random();

    public WordPairContainer() {
        wordpairs = new ArrayList<WordPair>();
    }

    public Iterator<WordPair> iterator() {
        Iterator<WordPair> iwp = wordpairs.iterator();
        return iwp;
    }

    public boolean addWordPair(String word, String pair, String comment) {
        /* Check if wordpairs is null */
        if( wordpairs == null ) {
            System.err.println("Error: Word pair list is null!");
            return false;
        }

        /* Check if we are given nulls, those bastards :< */
        if( word == null || pair == null || comment == null ) {
            System.err.println("Error: Some of the given Strings are null!");
            return false;
        }

        /* Check if the essential strings given were empty */
        if( word.equalsIgnoreCase("") || pair.equalsIgnoreCase("") ) {
            System.err.println("Error: Either the word or pair was an empty string!");
            return false;
        }

        WordPair wp = WordPair.createWordPair(word, pair, comment);
        wordpairs.add(wp);

        return true;
    }

    public boolean addWordPair(String word, String pair) {
        return this.addWordPair(word, pair, "");
    }

    private boolean addWordPair(WordPair wp) {
        /* Check if wordpairs is null */
        if( wordpairs == null || wp == null ) {
            System.err.println("Error: Word pair list or given word pair was null!");
            return false;
        }

        wordpairs.add(wp);
        return true;
    }

    public boolean removeWordPair(WordPair wp) {
        /* Check if wordpairs is null */
        if( wordpairs == null || wp == null ) {
            System.err.println("Error: Word pair list is null or given word pair was null!");
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

    public boolean isEmpty() {
        /* Check if wordpairs is null */
        if( wordpairs == null ) {
            System.err.println("Error: Word pair list is null!");
            return true;
        }

        return wordpairs.isEmpty();
    }

    /**
     * Compares this WordPairContainer against the specified object. The result is true if the object is not null
     * and if it represents a WordPairContainer with the same contents.
     *
     * @param obj The object to compare this WordPairContainer against
     * @return true if the given object represents a WordPairContainer with the exactly same contents,
     *         false otherwise.
     */
    public boolean equalsInContent(Object obj) {
        /* General checks */
        if( obj == null || obj.getClass() != getClass() ) {
            return false;
        }

        /* Cast the object into the needed type */
        final WordPairContainer compared = (WordPairContainer) obj;

        /* Check if both are empty */
        if( this.isEmpty() && compared.isEmpty() ) {
            return true;
        }

        /* Check if their emptiness status does not match */
        if( ( compared.isEmpty() && !this.isEmpty() ) || ( !compared.isEmpty() && this.isEmpty() ) ) {
            return false;
        }

        /* Check if their count of word pairs does not match */
        if( this.getWordPairCount() != compared.getWordPairCount() ) {
            return false;
        }

        /* Since their count of word pairs matches, just iterate through them all and compare word pairs */
        for( int i = 0; i < this.getWordPairCount(); i++ ) {
            /* Return false if the contained word pairs don't match */
            if( !this.getWordPair(i).equalsInContent(compared.getWordPair(i)) ) {
                return false;
            }
        }

        return true;
    }
}
