import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 20.11.2012
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class PrefilledWordPairContainerTest {
    private WordPairContainer wpc;
    private WordPairContainer wpc2;
    private WordPairContainer wpc3;

    @Before
    public void setup() {
        String herp    = "herp";
        String derp    = "derp";
        String comment = "comment";

        wpc  = new WordPairContainer();
        wpc2 = new WordPairContainer();
        wpc3 = new WordPairContainer();

        wpc.addWordPair(herp + "1", derp, comment);
        wpc.addWordPair(herp + "2", derp);

        wpc2.addWordPair(herp + "before_first", derp, comment);
        wpc2.addWordPair(herp, derp);
        wpc2.addWordPair(herp + "after_first", derp, comment);
        wpc2.addWordPair(herp, derp);
        wpc2.addWordPair(herp + "after the second", derp, comment);

        wpc3.addWordPair(herp + "before_first", derp, comment);
        wpc3.addWordPair(herp, derp);
        wpc3.addWordPair(herp + "after_first", derp, comment);
        wpc3.addWordPair(herp + "after the second", derp, comment);
    }

    @Test
    public void testThatWordPairContainerIsNotNull() {
        Assert.assertFalse( wpc == null );
    }

    @Test
    public void testThatWordPairContainerIsNotEmpty() {
        Assert.assertFalse( wpc.isEmpty() );
    }

    @Test
    public void testThatAmountOfWordPairsInContainerMatchesAssumedCount() {
        Assert.assertTrue( wpc.getWordPairCount() == 2 );
    }

    @Test
    public void testValidRemovalOfWordPairFromWordPairContainer() {
        Assert.assertTrue(wpc.removeWordPair(wpc.getRandomWordPair()));
    }

    @Test
    public void testInvalidRemovalOfWordPairFromWordPairContainer() {
        Assert.assertFalse( wpc.removeWordPair(wpc.getWordPair(9001)) );
    }

    @Test
    public void testIfCorrectIdenticalContentGetsRemoved() {
        String herp    = "herp";
        String derp    = "derp";
        String comment = "comment";
        WordPair wp = null;

        Assert.assertTrue( wpc2.removeWordPair( wpc2.getWordPair(3) ) );

        Assert.assertTrue( wpc2.equalsInContent(wpc3) );
    }
}
