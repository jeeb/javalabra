import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 13.11.2012
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class WordPairContainerTest {
    @Test
    public void testNewWordPairContainerCreation() {
        WordPairContainer wpc = new WordPairContainer();

        Assert.assertNotNull(wpc);

        Assert.assertTrue( wpc.isEmpty() );
    }

    @Test
    public void testAddingRemovingWordPairsFromContainer() {
        /* Predefined strings */
        String herp    = "herp";
        String derp    = "derp";
        String comment = "comment";


        WordPairContainer wpc = new WordPairContainer();

        Assert.assertNotNull("wpc was null after creation!?", wpc);
        Assert.assertTrue("wpc is not empty after creation!?", wpc.isEmpty());

        wpc.addWordPair(herp, derp, comment);

        Assert.assertFalse("wpc was empty after adding a pair!", wpc.isEmpty());

        wpc.addWordPair(herp, derp);

        Assert.assertTrue("word pair count was not 2!", wpc.getWordPairCount() == 2);

        Assert.assertNull("getWordPair let you take [2] with just 0..1!", wpc.getWordPair(2));
        Assert.assertNull("getWordPair let you take [-9000]..!", wpc.getWordPair(-9000));

        WordPair wp = wpc.getRandomWordPair();

        Assert.assertTrue("wpc changed from 2 after just grabbing a value o_O", wpc.getWordPairCount() == 2);

        wpc.removeWordPair(wp);

        Assert.assertTrue("wpc's count did not go 2->1!?", wpc.getWordPairCount() == 1);

        wp = wpc.getRandomWordPair();
        wpc.removeWordPair(wp);

        Assert.assertTrue("after removing all pairs wpc's not empty!?", wpc.isEmpty());
    }
}
