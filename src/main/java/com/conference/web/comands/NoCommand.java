package com.conference.web.comands;

import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gleb on 19.12.17.
 */
public class NoCommand implements ICommand{
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*в случае прямого обращения к контроллеру переадресация на страницу ввода логина*/
        String page = ConfigProperties.getInstance().LOGIN_PAGE_PATH;
        return page;
    }
}
