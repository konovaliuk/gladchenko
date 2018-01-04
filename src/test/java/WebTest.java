import com.conference.web.ControllerHelper;
import com.conference.web.properties.ConfigProperties;
import com.conference.web.properties.MessageProperties;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gleb on 29.12.17.
 */
public class WebTest {

    @Test
    public void testControllerHelperGetInstance() {
        ControllerHelper controllerHelper = ControllerHelper.getInstance();
        Assert.assertNotNull(controllerHelper);
    }

    @Test
    public void testConfigPropertiesGetInstance() {
        ConfigProperties configProperties = ConfigProperties.getInstance();
        Assert.assertNotNull(configProperties);
    }

    @Test
    public void testMessagePropertiesGetInstance() {
        MessageProperties messageProperties = MessageProperties.getInstance();
        Assert.assertNotNull(messageProperties);
    }
}
