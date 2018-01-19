package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.service.EventService;
import com.conference.service.ReportService;
import com.conference.web.Localization;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by gleb on 10.01.18.
 */
public class ChangeLanguageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        String lang = request.getParameter("lang");
        ReportService reportService = new ReportService();
        EventService eventService = new EventService();
        HttpSession session = request.getSession();
        long role = (long) session.getAttribute("role");
        String page;

        if (lang.equalsIgnoreCase("DE")) {
            session.setAttribute("local", "DE");
            Locale locale = Locale.GERMANY;
            ResourceBundle bundle = ResourceBundle.getBundle("local", locale);
            Localization.setLocalProp(session, bundle);
            session.setAttribute("event", eventService.getEventById(1L, "eventDe"));
            session.setAttribute("eventlist", eventService.getAllEvent("eventDe"));
            session.setAttribute("list", reportService.getReportsByParam("id_event", "1", "reportDe"));
        }
        if (lang.equalsIgnoreCase("RU")) {
            session.setAttribute("local", "RU");
            Locale locale = new Locale("ru", "RU");
            ResourceBundle bundle = ResourceBundle.getBundle("local", locale);
            Localization.setLocalProp(session, bundle);
            session.setAttribute("event", eventService.getEventById(1L, "eventRu"));
            session.setAttribute("eventlist", eventService.getAllEvent("eventRu"));
            session.setAttribute("list", reportService.getReportsByParam("id_event", "1", "reportRu"));
        }
        if (lang.equalsIgnoreCase("EN")) {
            session.setAttribute("local", "EN");
            ResourceBundle bundle = ResourceBundle.getBundle("local", Locale.getDefault());
            Localization.setLocalProp(session, bundle);
            session.setAttribute("event", eventService.getEventById(1L, "eventEn"));
            session.setAttribute("eventlist", eventService.getAllEvent("eventEn"));
            session.setAttribute("list", reportService.getReportsByParam("id_event", "1", "reportEn"));
        }

        if (role == 4) {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
        } else if (role == 3) {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.SPEAKER_PAGE_PATH);
        } else if (role == 2) {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
        } else if (role == 1) {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ADMIN_PAGE_PATH);
        } else {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ERROR_PAGE_PATH);
        }
        return page;
    }
}
