package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gleb on 19.12.17.
 */
public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistException;
}
