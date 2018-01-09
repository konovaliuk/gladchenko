package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by gleb on 05.01.18.
 */
public class BackToMainPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        String page = null;
        HttpSession session = request.getSession(true);
        long role = (long) session.getAttribute("role");
        if (role == 3) {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.SPEAKER_PAGE_PATH);
        } else if (role == 2) {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
        } else if (role == 1) {
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ADMIN_PAGE_PATH);
        }
        return page;
    }
}
