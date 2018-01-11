package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.service.EmailService;
import com.conference.web.properties.ConfigProperties;

import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gleb on 11.01.18.
 */
public class SendEmailCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        try {
            EmailService.sendEmailToAllUsers(request.getParameter("emailtopic"), request.getParameter("emailtext"));
        } catch (AddressException e) {
            e.printStackTrace();
        }
        String message = "Emails have been sent successful!";
        HttpSession session = request.getSession(true);
        session.setAttribute("msg", message);
        return ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
    }
}
