package com.conference.persistence.dao;

import com.conference.persistence.entity.Event;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gleb on 12.12.17.
 */
public class EventDao extends AbstractDao<Event, Long> {
    private String language = "eventEn";

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public EventDao(Connection connection) {
        super(connection);
    }

    private class PersistEvent extends Event {
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public Event create() throws PersistException {
        Event event = new Event();
        return persist(event);
    }

    @Override
    public String getSelectQuery() {
        return "select id, topic, place, e_date, e_time from event join "+language+" on event.id = "+language+".event_id";
    }

    @Override
    public String getCreateQuery() {
        return "BEGIN; INSERT INTO event (e_date, e_time) VALUES(?, ?); INSERT INTO eventEn (event_id, topic, place) VALUES(?, ?, ?); COMMIT;";

        //return "insert into event (topic, place, e_date, e_time) "
           //     + "values(?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update event, "+language+" set topic = ?, place = ?, e_date = ?, e_time = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from event where id = ?;";
    }

    @Override
    protected List<Event> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Event> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistEvent event = new PersistEvent();
                event.setId(rs.getLong("id"));
                event.setTopic(rs.getString("topic"));
                event.setPlace(rs.getString("place"));
                event.setCalendar(DateTimeParser.dateTimeToCalendar(rs.getString("e_date"), rs.getString("e_time")));
                result.add(event);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement,
                                             Event object) throws PersistException {
        try {
            statement.setDate(1, new Date(object.getCalendar().getTimeInMillis()));
            statement.setTime(2, new Time(object.getCalendar().getTimeInMillis()));
            statement.setString(3, "LAST_INSERT_ID()");
            statement.setString(4, object.getTopic());
            statement.setString(5, object.getPlace());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement,
                                             Event object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setString(2, object.getPlace());
            statement.setDate(3, new Date(object.getCalendar().getTimeInMillis()));
            statement.setTime(4, new Time(object.getCalendar().getTimeInMillis()));
            statement.setLong(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement,
                                             Event object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
