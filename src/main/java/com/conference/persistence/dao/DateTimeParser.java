package com.conference.persistence.dao;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Created by gleb on 25.12.17.
 */
public class DateTimeParser {
    private static final Logger LOG = Logger.getLogger(DateTimeParser.class.getName());
    public static Calendar dateTimeToCalendar(String date, String time) throws PersistException {
        try {
            Calendar calendar = new GregorianCalendar();
            StringTokenizer tokenizer = new StringTokenizer(date,"-");
            calendar.set(Calendar.YEAR, Integer.parseInt(tokenizer.nextToken()));
            calendar.set(Calendar.MONTH, Integer.parseInt(tokenizer.nextToken())-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokenizer.nextToken()));
            tokenizer = new StringTokenizer(time,":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tokenizer.nextToken()));
            calendar.set(Calendar.MINUTE, Integer.parseInt(tokenizer.nextToken()));
            calendar.set(Calendar.SECOND, Integer.parseInt(tokenizer.nextToken()));
            return calendar;
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw  new PersistException(e);
        }
    }
}
