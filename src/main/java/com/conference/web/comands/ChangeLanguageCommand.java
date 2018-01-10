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

/**
 * Created by gleb on 10.01.18.
 */
public class ChangeLanguageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        String lang = request.getParameter("lang");
        String dbLang = null;
        HttpSession session = request.getSession();
        if (lang.equalsIgnoreCase("EN")) {
            session.setAttribute("local", "EN");
            Localization.setLocalProp(session);
            dbLang = "eventEn";
        }
        if (lang.equalsIgnoreCase("RU")) {
            session.setAttribute("local", "RU");
            Localization.setLocalProp(session);
            dbLang = "eventRu";
        }
        session.setAttribute("event", EventService.getEventById(1L, dbLang));
        session.setAttribute("eventlist", EventService.getAllEvent(dbLang));
        return ConfigProperties.getInstance().getProperty(ConfigProperties.USER_PAGE_PATH);
    }
}
