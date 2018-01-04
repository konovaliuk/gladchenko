package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Event;
import com.conference.service.EventService;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Created by gleb on 23.12.17.
 */
public class UpdateEventCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        String page = null;
        Event event = EventService.getEventById(Long.valueOf(request.getParameter("id")));
        event.setTopic(request.getParameter("topic"));
        event.setPlace(request.getParameter("place"));
        event.setCalendar(dateTimeToCalendar(request.getParameter("date"),
                                            request.getParameter("time")));
        EventService.updateEvent(event);
        HttpSession session = request.getSession(true);
        session.setAttribute("event", event);
        page = ConfigProperties.getInstance().MODER_PAGE_PATH;
        return page;
    }

    private static Calendar dateTimeToCalendar(String date, String time) throws PersistException {
        try {
            Calendar calendar = new GregorianCalendar();
            StringTokenizer tokenizer = new StringTokenizer(date,"-");
            calendar.set(Calendar.YEAR, Integer.parseInt(tokenizer.nextToken()));
            calendar.set(Calendar.MONTH, Integer.parseInt(tokenizer.nextToken())-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokenizer.nextToken()));
            tokenizer = new StringTokenizer(time,":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tokenizer.nextToken()));
            calendar.set(Calendar.MINUTE, Integer.parseInt(tokenizer.nextToken()));
            return calendar;
        } catch (Exception e) {
            throw  new PersistException(e);
        }
    }
}
