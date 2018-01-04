package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Report;
import com.conference.service.*;
import com.conference.web.properties.ConfigProperties;
import com.conference.web.properties.MessageProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by gleb on 19.12.17.
 */
public class LoginCommand implements ICommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistException {
        String page = null;
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        if (LoginService.checkLogin(login, pass) && LoginService.checkRole(login, 1)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("list", ReportService.getAllReport());
            session.setAttribute("event", EventService.getEventById(1L));
            //определение пути к admin.jsp
            page = ConfigProperties.getInstance().ADMIN_PAGE_PATH;
        } else if (LoginService.checkLogin(login, pass) && LoginService.checkRole(login, 2)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("list", ReportService.getAllReport());
            session.setAttribute("event", EventService.getEventById(1L));
            session.setAttribute("speakers", UserService.getUsersByParam("id_role", "3"));
            session.setAttribute("moderid",  LoginService.getUserId(login));
            session.setAttribute("conftopic", TopicService.getTopicsByParam("status", "confirmed"));
            //определение пути к moder.jsp
            page = ConfigProperties.getInstance().MODER_PAGE_PATH;
        } else if (LoginService.checkLogin(login, pass) && LoginService.checkRole(login, 3)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("list", ReportService.getAllReport());
            session.setAttribute("event", EventService.getEventById(1L));
            session.setAttribute("salary", SalaryService.getSalary(LoginService.getUserId(login)));
            session.setAttribute("userid",  LoginService.getUserId(login));
            session.setAttribute("newtopic", TopicService.getNewTopicsByParam("id_speaker",
                                                        String.valueOf(LoginService.getUserId(login))));
            //определение пути к speaker.jsp
            page = ConfigProperties.getInstance().SPEAKER_PAGE_PATH;
        } else if (LoginService.checkLogin(login, pass)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("event", EventService.getEventById(1L));
            session.setAttribute("userid",  LoginService.getUserId(login));
            session.setAttribute("list", ReportService.getAllReport());
            //определение пути к user.jsp
            page = ConfigProperties.getInstance().USER_PAGE_PATH;
        } else {
            request.setAttribute("errorMessage",
                    MessageProperties.getInstance().LOGIN_ERROR_MESSAGE);
            page = ConfigProperties.getInstance().ERROR_PAGE_PATH;
        }
        return page;
    }
}
