import org.junit.Assert;
import org.junit.Test;

/**
 * Implements the tests for WordPair objects
 */
public class WordPairTest {
    @Test
    public void testWordPairCreationThreeArgs() {
        /* Predefined strings */
        String herp    = "herp";
        String derp    = "derp";
        String comment = "comment";

        /* Create new wordpair */
        WordPair wp = new WordPair(herp, derp, comment);

        /* Assert that the thing is not null */
        Assert.assertNotNull(wp);

        /* Assert that the things we get from it equal what we supposedly set */
        Assert.assertTrue(wp.getWord().equals(herp) && wp.getPair().equals(derp) &&
                          wp.getComment().equals(comment));
    }

    @Test
    public void testWordPairCreationTwoArgs() {
        /* Predefined strings */
        String herp = "herp";
        String derp = "derp";

        /* Create new wordpair */
        WordPair wp = new WordPair(herp, derp);

        /* Assert that the thing is not null */
        Assert.assertNotNull(wp);

        /* Assert that the things we get from it equal what we supposedly set */
        Assert.assertTrue(wp.getWord().equals(herp) && wp.getPair().equals(derp));
    }
}
