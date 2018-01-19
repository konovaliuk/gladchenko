package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Registration;
import com.conference.service.RegistrationService;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by gleb on 27.12.17.
 */
public class RegistrationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        Registration registration = new Registration();
        HttpSession session = request.getSession(true);
        String lang = (String) session.getAttribute("local");
        Locale locale;
        registration.setUserId(Long.parseLong(request.getParameter("userid")));
        registration.setEventId(Long.parseLong(request.getParameter("eventid")));
        registration.setReportId(Long.parseLong(request.getParameter("reportid")));
        RegistrationService registrationService = new RegistrationService();
        registrationService.createRegistration(registration);
        if (lang.equalsIgnoreCase("DE")) {
            locale = Locale.GERMANY;
        } else if (lang.equalsIgnoreCase("RU")) {
            locale = new Locale("ru", "RU");
        } else {
            locale = Locale.getDefault();
        }
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        String message = encoding(bundle.getString("SUCCESS_REPORT_REGISTRATION"));
        session.setAttribute("succesmsg", message);
        return ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
    }

    public static String encoding(String value) throws UnsupportedEncodingException {
        return new String(value.getBytes("ISO-8859-1"), "UTF-8");
    }
}
