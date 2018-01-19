package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.service.EventService;
import com.conference.service.ReportService;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gleb on 05.01.18.
 */
public class ChangeEventCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        String page = null;
        EventService eventService = new EventService();
        ReportService reportService = new ReportService();
        long eventId = Long.parseLong(request.getParameter("idevent"));
        HttpSession session = request.getSession();
        session.setAttribute("idevent", eventId);
        long role = (long) session.getAttribute("role");
        String language;
        String reportLanguage;
        String lang = (String) session.getAttribute("local");
        if (lang.equalsIgnoreCase("RU")) {
            language = "eventRu";
            reportLanguage = "reportRu";
        } else if (lang.equalsIgnoreCase("DE")) {
            language = "eventDe";
            reportLanguage = "reportDe";
        } else {
            language = "eventEn";
            reportLanguage = "reportEn";
        }

        session.setAttribute("list", reportService.getReportsByParam("id_event", String.valueOf(eventId), reportLanguage));

        if (role == 4) {
            session.setAttribute("event", eventService.getEventById(eventId, language));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
        } else if (role == 3) {
            session.setAttribute("event", eventService.getEventById(eventId, language));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.SPEAKER_PAGE_PATH);
        } else if (role == 2) {
            session.setAttribute("event", eventService.getEventById(eventId, language));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
        } else if (role == 1) {
            session.setAttribute("event", eventService.getEventById(eventId, language));
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ADMIN_PAGE_PATH);
        }
        return page;
    }
}
