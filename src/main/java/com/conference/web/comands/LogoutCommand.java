package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gleb on 08.01.18.
 */
public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ConfigProperties.getInstance().getProperty(ConfigProperties.LOGIN_PAGE_PATH);
    }
}
