import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Salary;
import com.conference.service.EmailService;
import com.conference.service.LoginService;
import com.conference.service.SalaryService;
import org.junit.Test;


import javax.mail.internet.AddressException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by gleb on 29.12.17.
 */
public class ServiceTest {

    @Test
    public void salaryServiceGetSalaryTest() throws PersistException {
        SalaryService salaryService = mock(SalaryService.class);
        Salary salary = new Salary();
        when(salaryService.getSalary(3L)).thenReturn(salary);
        Salary result = salaryService.getSalary(3L);
        assertEquals(salary, result);
    }

    @Test
    public void emailServiceSendEmailToAllUsersTest() throws AddressException {
        EmailService mock = mock(EmailService.class);
        doThrow(new AddressException()).when(mock).sendEmailToAllUsers("", "");
    }

    @Test
    public void loginServiceCheckLoginTest() {
        LoginService mock = mock(LoginService.class);
        when(mock.checkLogin("", "")).thenReturn(true);
        boolean result = mock.checkLogin("", "");
        assertEquals(true, result);
    }

    @Test
    public void loginServiceCheckRoleTest() {
        LoginService mock = mock(LoginService.class);
        when(mock.checkRole("", 1)).thenReturn(true);
        boolean result = mock.checkRole("", 1);
        assertEquals(true, result);
    }

    @Test
    public void loginServiceGetUserId() {
        LoginService mock = mock(LoginService.class);
        when(mock.getUserId("")).thenReturn(2L);
        long result = mock.getUserId("");
        assertEquals(2, result);
    }
}
