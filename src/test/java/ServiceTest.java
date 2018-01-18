import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.*;
import com.conference.service.*;
import org.junit.Test;


import javax.mail.internet.AddressException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by gleb on 29.12.17.
 */
public class ServiceTest {

    @Test
    public void salaryServiceTest() throws PersistException {
        SalaryService mock = mock(SalaryService.class);
        Salary salary = mock(Salary.class);

        when(mock.getSalary(3L)).thenReturn(salary);
        Salary result = mock.getSalary(3L);

        doThrow(new PersistException()).when(mock).getSalary(3L);
        assertEquals(salary, result);
    }

    @Test
    public void emailServiceTest() throws AddressException {
        EmailService mock = mock(EmailService.class);
        mock.sendEmailToAllUsers("", "");
        doThrow(new AddressException()).when(mock).sendEmailToAllUsers("", "");
        verify(mock).sendEmailToAllUsers("", "");
    }

    @Test
    public void loginServiceTest() {
        LoginService mock = mock(LoginService.class);

        when(mock.checkLogin("", "")).thenReturn(true);
        boolean result = mock.checkLogin("", "");
        assertEquals(true, result);

        when(mock.checkRole("", 1)).thenReturn(true);
        boolean result1 = mock.checkRole("", 1);
        assertEquals(true, result1);

        when(mock.getUserId("")).thenReturn(2L);
        long result2 = mock.getUserId("");
        assertEquals(2, result2);
    }

    @Test
    public void registrationServiceTest() {
        RegistrationService mock = mock(RegistrationService.class);
        Registration registration = mock(Registration.class);

        when(mock.createRegistration(registration)).thenReturn(registration);
        Registration result = mock.createRegistration(registration);
        assertEquals(registration, result);

        when(mock.getRegistrationsCount()).thenReturn(10);
        int result1 = mock.getRegistrationsCount();
        assertEquals(10, result1);
    }

    @Test
    public void userServiceTest() {
        UserService mock = mock(UserService.class);
        List<User> users = mock(ArrayList.class);

        when(mock.getUsersByParam("", "")).thenReturn(users);
        List<User> result = mock.getUsersByParam("", "");
        assertEquals(users, result);

        when(mock.getUserRole("")).thenReturn(2L);
        long result1 = mock.getUserRole("");
        assertEquals(2, result1);
    }

    @Test
    public void topicServiceTest() {
        TopicService mock = mock(TopicService.class);
        Topic topic = mock(Topic.class);
        List<Topic> topics = mock(ArrayList.class);

        when(mock.createTopic(topic)).thenReturn(topic);
        Topic result1 = mock.createTopic(topic);
        assertEquals(topic, result1);

        when(mock.getNewTopicsByParam("", "")).thenReturn(topics);
        List<Topic> result2 = mock.getNewTopicsByParam("", "");
        assertEquals(topics, result2);

        when(mock.getConfModerTopicsByParam("", "")).thenReturn(topics);
        List<Topic> result3 = mock.getConfModerTopicsByParam("", "");
        assertEquals(topics, result3);
    }

    @Test
    public void eventServiceTest() {
        EventService mock = mock(EventService.class);
        List<Event> events = mock(ArrayList.class);
        Event event = mock(Event.class);

        when(mock.getAllEvent()).thenReturn(events);
        List<Event> result = mock.getAllEvent();
        assertEquals(events, result);

        when(mock.getEventById(1)).thenReturn(event);
        Event result1 = mock.getEventById(1);
        assertEquals(event, result1);

        mock.updateEvent(event);
        verify(mock).updateEvent(event);

        when(mock.getAllEvent("")).thenReturn(events);
        List<Event> result2 = mock.getAllEvent("");
        assertEquals(events, result2);

        when(mock.getEventById(1, "")).thenReturn(event);
        Event result3 = mock.getEventById(1, "");
        assertEquals(event, result3);

        mock.updateEvent(event, "");
        verify(mock).updateEvent(event);
    }

    @Test
    public void reportServiceTest() throws PersistException {
        ReportService mock = mock(ReportService.class);
        List<Report> reports = mock(ArrayList.class);
        Report report = mock(Report.class);

        when(mock.getAllReport("reportEn")).thenReturn(reports);
        List<Report> result = mock.getAllReport("reportEn");
        assertEquals(reports, result);

        mock.updateReport(report, "reportEn");
        verify(mock).updateReport(report, "reportEn");

        when(mock.getReportByPK(1L)).thenReturn(report);
        Report result1= mock.getReportByPK(1L);
        assertEquals(report, result1);

        when(mock.getReportsByParam("", "", "reportEn")).thenReturn(reports);
        List<Report> result2 = mock.getReportsByParam("", "", "reportEn");
        doThrow(new PersistException()).when(mock).getReportsByParam("", "", "reportEn");
        assertEquals(reports, result);

    }
}
