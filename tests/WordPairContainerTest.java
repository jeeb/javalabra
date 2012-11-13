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
    public void createNewWordPairContainer() {
        WordPairContainer wpc = new WordPairContainer();

        Assert.assertNotNull(wpc);

        Assert.assertTrue( wpc.isEmpty() );
    }
}
