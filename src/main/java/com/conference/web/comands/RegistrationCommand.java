package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Registration;
import com.conference.persistence.entity.Report;
import com.conference.service.RegistrationService;
import com.conference.service.ReportService;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gleb on 27.12.17.
 */
public class RegistrationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        Registration registration = new Registration();
        registration.setUserId(Long.parseLong(request.getParameter("userid")));
        registration.setEventId(Long.parseLong(request.getParameter("eventid")));
        registration.setReportId(Long.parseLong(request.getParameter("reportid")));
        registration = RegistrationService.createRegistration(registration);
        Report report = ReportService.getReportByPK(registration.getReportId());
        HttpSession session = request.getSession(true);
        session.setAttribute("report", report);
        String message = "Registration successful! \n Report: " + report.getTopic();
        session.setAttribute("succesmsg", message);
        return ConfigProperties.getInstance().USER_PAGE_PATH;
    }
}
