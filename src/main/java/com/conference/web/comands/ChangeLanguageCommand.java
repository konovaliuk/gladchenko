package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.service.EventService;
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
        EventService eventService = new EventService();
        HttpSession session = request.getSession();

        if (lang.equalsIgnoreCase("DE")) {
            session.setAttribute("local", "DE");
            Locale locale = Locale.GERMANY;
            ResourceBundle bundle = ResourceBundle.getBundle("local", locale);
            Localization.setLocalProp(session, bundle);
            session.setAttribute("event", eventService.getEventById(1L, "eventDe"));
            session.setAttribute("eventlist", eventService.getAllEvent("eventDe"));
        }
        if (lang.equalsIgnoreCase("RU")) {
            session.setAttribute("local", "RU");
            Locale locale = new Locale("ru", "RU");
            ResourceBundle bundle = ResourceBundle.getBundle("local", locale);
            Localization.setLocalProp(session, bundle);
            session.setAttribute("event", eventService.getEventById(1L, "eventRu"));
            session.setAttribute("eventlist", eventService.getAllEvent("eventRu"));
        }
        if (lang.equalsIgnoreCase("EN")) {
            session.setAttribute("local", "EN");
            ResourceBundle bundle = ResourceBundle.getBundle("local", Locale.getDefault());
            Localization.setLocalProp(session, bundle);
            session.setAttribute("event", eventService.getEventById(1L, "eventEn"));
            session.setAttribute("eventlist", eventService.getAllEvent("eventEn"));
        }
        return ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
    }
}
