import com.conference.persistence.dao.PersistException;
import com.conference.web.ControllerHelper;
import com.conference.web.comands.ICommand;
import com.conference.web.properties.ConfigProperties;
import com.conference.web.properties.MessageProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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
        MessageProperties messageProperties = MessageProperties.getInstance(Locale.GERMAN);
        Assert.assertNotNull(messageProperties);
    }

    @Test
    public void ICommandTest() throws ServletException, PersistException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ICommand mock = mock(ICommand.class);
        when(mock.execute(request, response)).thenReturn("page");
        String result = mock.execute(request, response);
        doThrow(new ServletException()).when(mock).execute(request, response);
        doThrow(new PersistException()).when(mock).execute(request, response);
        doThrow(new IOException()).when(mock).execute(request, response);
        assertEquals("page", result);
    }
}
