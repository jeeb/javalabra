import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Implements the tests for the testing of the FileWriter class
 */
public class FileWriterTest {
    private WordPairContainer wpc;
    private WordPairContainer wpc2;
    String testString;

    @Before
    public void setup() {
        String herp = "herp";
        String derp = "derp";

        wpc        = new WordPairContainer();
        wpc2       = new WordPairContainer();
        testString = "welp";

        wpc2.addWordPair(herp, derp);
    }

    @Test
    public void testWritingAnEmptyWPC() {
        Assert.assertFalse( FileWriter.WriteWordPairContainerToFile(wpc, testString) );
    }

    @Test
    public void testWritingANullFileName() {
        Assert.assertFalse( FileWriter.WriteWordPairContainerToFile(wpc2, null) );
    }
}
