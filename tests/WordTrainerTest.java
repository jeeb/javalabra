import org.junit.Assert;
import org.junit.Test;
import java.nio.file.Paths;

/**
 * Implements general tests related to the application.
 */
public class WordTrainerTest {
    @Test
    public void testFileParsingWithValidFile() {
        String filename = "derp";
        /* currently does not fail like a boss */
        WordPairContainer wpc = FileReader.createWPCFromFile(filename);

        Assert.assertNotNull(wpc);

        Assert.assertTrue(wpc.getWordPairCount() == 3);
    }
}
