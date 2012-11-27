import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Implements the tests for the testing of the SettingsManager class
 */
public class SettingsManagerTest {
    SettingsManager sm;

    @Before
    public void setup() {
        sm = new SettingsManager();
    }

    @Test
    public void testSettingANullString() {
        Assert.assertFalse( sm.setFileString( null ) );
    }

    @Test
    public void testSettingAnEmptyString() {
        Assert.assertFalse( sm.setFileString( "" ) );
    }

    @Test
    public void testSettingANullMode() {
        Assert.assertFalse( sm.setMode( null ) );
    }
}
