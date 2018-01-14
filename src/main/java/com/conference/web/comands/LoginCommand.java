package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Report;
import com.conference.service.*;
import com.conference.web.Localization;
import com.conference.web.properties.ConfigProperties;
import com.conference.web.properties.MessageProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
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

        if (LoginService.checkLogin(login, pass) && LoginService.checkRole(login, 1)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("list", ReportService.getAllReport());
            session.setAttribute("event", EventService.getEventById(1L));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ADMIN_PAGE_PATH);
        } else if (LoginService.checkLogin(login, pass) && LoginService.checkRole(login, 2)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("list", ReportService.getReportsByParam("id_event", "1"));
            session.setAttribute("event", EventService.getEventById(1L));
            session.setAttribute("speakers", UserService.getUsersByParam("id_role", "3"));
            session.setAttribute("moderid",  LoginService.getUserId(login));
            session.setAttribute("conftopic", TopicService.getTopicsByParam("status", "confirmed"));
            session.setAttribute("registrstat", RegistrationService.getRegistrationsCount());
            session.setAttribute("role", UserService.getUserRole(login));
            session.setAttribute("eventlist", EventService.getAllEvent());
            session.setAttribute("newspeakertopics", TopicService.getTopicsByParam("status", "newsp"));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
        } else if (LoginService.checkLogin(login, pass) && LoginService.checkRole(login, 3)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("list", ReportService.getReportsByParam("id_event", "1"));
            session.setAttribute("event", EventService.getEventById(1L));
            session.setAttribute("salary", SalaryService.getSalary(LoginService.getUserId(login)));
            session.setAttribute("userid",  LoginService.getUserId(login));
            session.setAttribute("role", UserService.getUserRole(login));
            session.setAttribute("eventlist", EventService.getAllEvent());
            session.setAttribute("newtopic", TopicService.getNewTopicsByParam("id_speaker", String.valueOf(LoginService.getUserId(login))));
            session.setAttribute("confmodtopic", TopicService.getConfModerTopicsByParam("id_speaker", String.valueOf(LoginService.getUserId(login))));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.SPEAKER_PAGE_PATH);
        } else if (LoginService.checkLogin(login, pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("event", EventService.getEventById(1L, "eventRu"));
            session.setAttribute("local", "RU");
            ResourceBundle bundle = ResourceBundle.getBundle("local", new Locale("ru", "RU"));
            Localization.setLocalProp(session, bundle);
            session.setAttribute("userid",  LoginService.getUserId(login));
            session.setAttribute("list", ReportService.getReportsByParam("id_event", "1"));
            session.setAttribute("role", UserService.getUserRole(login));
            session.setAttribute("eventlist", EventService.getAllEvent("eventRu"));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
        } else {
            request.setAttribute("errormessage", MessageProperties.getInstance().getProperty(MessageProperties.LOGIN_ERROR_MESSAGE));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ERROR_PAGE_PATH);
        }
        return page;
    }
}
