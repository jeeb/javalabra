import org.junit.Assert;
import org.junit.Test;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 13.11.2012
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class WordTrainerTest {
    @Test
    public void testFileParsingWithValidFile() {
        String filename = "derp";
        /* currently does not fail like a boss */
        WordPairContainer wpc = WordTrainer.parseWordPairFile(Paths.get(filename));

        Assert.assertNotNull(wpc);

        Assert.assertTrue(wpc.getWordPairCount() == 3);
    }
}
