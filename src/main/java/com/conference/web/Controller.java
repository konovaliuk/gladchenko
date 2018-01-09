package com.conference.web;

import com.conference.web.comands.ICommand;
import com.conference.web.properties.ConfigProperties;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by gleb on 16.12.17.
 */
public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    private ControllerHelper controllerHelper = ControllerHelper.getInstance();

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                                            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = Locale.getDefault();
        String page = null;
        try {
            ICommand command = controllerHelper.getCommand(request);        //определение команды, пришедшей из JSP
            page = command.execute(request,response);                       //передача параметров классу-обработчику конкретной команды
        } catch (Exception e) {
            LOG.error("Exception: ", e);                           //генерация сообщения об ошибке
            page = ConfigProperties.getInstance().getProperty(ConfigProperties.ERROR_PAGE_PATH);          //вызов JSP-страницы с сообщением об ошибке
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);  //вызов страницы ответа на запрос
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
