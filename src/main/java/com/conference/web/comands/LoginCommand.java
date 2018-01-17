package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.service.*;
import com.conference.web.Localization;
import com.conference.web.properties.ConfigProperties;
import com.conference.web.properties.MessageProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by gleb on 19.12.17.
 */
public class LoginCommand implements ICommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistException {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        LoginService loginService = new LoginService();
        UserService userService = new UserService();
        TopicService topicService = new TopicService();
        EventService eventService = new EventService();
        ReportService reportService = new ReportService();

        if (loginService.checkLogin(login, pass) && loginService.checkRole(login, 1)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("list", reportService.getAllReport());
            session.setAttribute("event", eventService.getEventById(1L));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ADMIN_PAGE_PATH);
        } else if (loginService.checkLogin(login, pass) && loginService.checkRole(login, 2)) {
            HttpSession session = request.getSession();
            session.setAttribute("role", userService.getUserRole(login));
            session.setAttribute("list", reportService.getReportsByParam("id_event", "1"));
            session.setAttribute("event", eventService.getEventById(1L, "eventRu"));
            session.setAttribute("speakers", userService.getUsersByParam("id_role", "3"));
            session.setAttribute("moderid",  loginService.getUserId(login));
            session.setAttribute("local", "RU");
            session.setAttribute("conftopic", topicService.getTopicsByParam("status", "confirmed"));
            session.setAttribute("registrstat", new RegistrationService().getRegistrationsCount());
            session.setAttribute("eventlist", eventService.getAllEvent("eventRu"));
            ResourceBundle bundle = ResourceBundle.getBundle("local", new Locale("ru", "RU"));
            Localization.setLocalProp(session, bundle);session.setAttribute("newspeakertopics", topicService.getTopicsByParam("status", "newsp"));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
        } else if (loginService.checkLogin(login, pass) && loginService.checkRole(login, 3)) {
            HttpSession session = request.getSession();
            session.setAttribute("role", userService.getUserRole(login));
            session.setAttribute("list", reportService.getReportsByParam("id_event", "1"));
            session.setAttribute("event", eventService.getEventById(1L, "eventRu"));
            session.setAttribute("salary", new SalaryService().getSalary(loginService.getUserId(login)));
            session.setAttribute("local", "RU");
            ResourceBundle bundle = ResourceBundle.getBundle("local", new Locale("ru", "RU"));
            Localization.setLocalProp(session, bundle);
            session.setAttribute("userid",  loginService.getUserId(login));
            session.setAttribute("eventlist", eventService.getAllEvent("eventRu"));
            session.setAttribute("newtopic", topicService.getNewTopicsByParam("id_speaker", String.valueOf(loginService.getUserId(login))));
            session.setAttribute("confmodtopic", topicService.getConfModerTopicsByParam("id_speaker", String.valueOf(loginService.getUserId(login))));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.SPEAKER_PAGE_PATH);
        } else if (loginService.checkLogin(login, pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("role", userService.getUserRole(login));
            session.setAttribute("event", eventService.getEventById(1L, "eventRu"));
            session.setAttribute("local", "RU");
            ResourceBundle bundle = ResourceBundle.getBundle("local", new Locale("ru", "RU"));
            Localization.setLocalProp(session, bundle);
            session.setAttribute("userid",  loginService.getUserId(login));
            session.setAttribute("list", reportService.getReportsByParam("id_event", "1"));
            session.setAttribute("eventlist", eventService.getAllEvent("eventRu"));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
        } else {
            request.setAttribute("errormessage", MessageProperties.getInstance().getProperty(MessageProperties.LOGIN_ERROR_MESSAGE));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ERROR_PAGE_PATH);
        }
        return page;
    }
}
