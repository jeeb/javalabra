import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 13.11.2012
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class EmptyWordPairContainerTest {
    private WordPairContainer wpc;

    @Before
    public void setEmptyWordPairContainerUp() {
        wpc = new WordPairContainer();
    }

    @Test
    public void testCreatedWordPairContainerIsNotNull() {
        Assert.assertNotNull( wpc );
    }

    @Test
    public void testCreatedWordPairContainerIsEmpty() {
        Assert.assertTrue( wpc.isEmpty() );
    }

    @Test
    public void testAddingAProperThreeStringPair() {
        String herp    = "herp";
        String derp    = "derp";
        String comment = "comment";

        Assert.assertTrue( wpc.addWordPair(herp, derp, comment) );
    }

    @Test
    public void testAddingAProperTwoStringPair() {
        String herp = "herp";
        String derp = "derp";

        Assert.assertTrue( wpc.addWordPair(herp, derp) );
    }
}
