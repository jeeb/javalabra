import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Implements the tests for the testing of the FileReader class
 */
public class FileReaderTest {
    @Before
    public void setup() {

    }

    @Test
    public void derp() {
        Assert.assertFalse(false);
    }

    @Test
    public void testReadingHerp() {
        Assert.assertNotNull( FileReader.createWPCFromFile("herp") );
    }

    @Test
    public void testReadingAFileThatDoesNotExist() {
        Assert.assertNull( FileReader.createWPCFromFile("some_file_that_does_not_exist_yo") );
    }

    @Test
    public void testReadingAFileNameThatIsNull() {
        Assert.assertNull( FileReader.createWPCFromFile(null) );
    }
}
