import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Implements the tests for the testing of the CLIArgumentsParser class.
 */
public class CLIArgumentsParserTest {
    private SettingsManager sm;
    private String[] empty_array;

    @Before
    public void setup() {
        sm          = new SettingsManager();
        empty_array = new String[0];
    }

    @Test
    public void testParserWithNullArray() {
        Assert.assertFalse( CLIArgumentsParser.parseArguments(null, sm) );
    }

    @Test
    public void testParserWithEmptyArray() {
        Assert.assertTrue( CLIArgumentsParser.parseArguments(empty_array, sm) );
    }

    @Test
    public void testParserWithNullSettingsManager() {
        Assert.assertFalse( CLIArgumentsParser.parseArguments(empty_array, null) );
    }
}
