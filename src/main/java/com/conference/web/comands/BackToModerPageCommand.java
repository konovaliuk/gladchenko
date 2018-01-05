package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gleb on 05.01.18.
 */
public class BackToModerPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        return ConfigProperties.getInstance().MODER_PAGE_PATH;
    }
}
