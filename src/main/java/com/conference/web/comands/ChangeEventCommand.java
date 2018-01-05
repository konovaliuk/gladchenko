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
        long eventId = Long.parseLong(request.getParameter("idevent"));
        HttpSession session = request.getSession(true);
        session.setAttribute("event", EventService.getEventById(eventId));
        session.setAttribute("idevent", eventId);
        session.setAttribute("list", ReportService.getReportsByParam("id_event", String.valueOf(eventId)));
        long role = (long) session.getAttribute("role");
        if (role == 4) {
            page = ConfigProperties.getInstance().USER_PAGE_PATH;
        } else if (role == 3) {
            page = ConfigProperties.getInstance().SPEAKER_PAGE_PATH;
        } else if (role == 2) {
            page = ConfigProperties.getInstance().MODER_PAGE_PATH;
        } else if (role == 1) {
            page = ConfigProperties.getInstance().ADMIN_PAGE_PATH;
        }
        return page;
    }
}
