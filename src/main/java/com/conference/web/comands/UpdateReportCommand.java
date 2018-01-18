package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Report;
import com.conference.service.ReportService;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by gleb on 25.12.17.
 */
public class UpdateReportCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        String page = null;
        HttpSession session = request.getSession(true);
        ReportService reportService = new ReportService();
        Report report = reportService.getReportByPK(Long.valueOf(request.getParameter("idreport")));
        report.setTopic(request.getParameter("topicreport"));
        report.setPlace(request.getParameter("placereport"));
        report.setCalendar(dateTimeToCalendar(request.getParameter("datereport"),
                                            request.getParameter("timereport")));
        long eventId = Long.parseLong(request.getParameter("idevent"));
        report.setIdEvent(eventId);
        report.setIdSpeaker(Long.parseLong(request.getParameter("idspeaker")));
        String language;
        String lang = (String) session.getAttribute("local");
        if (lang.equalsIgnoreCase("RU")) {
            language = "reportRu";
        } else if (lang.equalsIgnoreCase("DE")) {
            language = "reportDe";
        } else {
            language = "reportEn";
        }
        reportService.updateReport(report, language);
        session.setAttribute("list", reportService.getReportsByParam("id_event", String.valueOf(eventId), language));
        page = ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
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
