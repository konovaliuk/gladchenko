package com.conference.persistence.dao;

import com.conference.persistence.entity.Event;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by gleb on 12.12.17.
 */
public class EventDao extends AbstractDao<Event, Long> {
    private static final Logger LOG = Logger.getLogger(EventDao.class.getName());
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
        return "insert into event (e_date, e_time) values(?, ?);";
    }

    public String getCreateQuerySecond() {
        return "insert into "+language+" (event_id, topic, place) values(last_insert_id(), ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update event set e_date = ?, e_time = ? where id = ?;";
    }

    public String getUpdateQuerySecond() {
        return "update "+language+" set topic = ?, place = ? where event_id = ?;";
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
            LOG.error("Exception: ", e);
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
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    protected void prepareStatementForInsertSecond(PreparedStatement statement,
                                             Event object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setString(2, object.getPlace());
        } catch (Exception e) {
           LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement,
                                             Event object) throws PersistException {
        try {
            statement.setDate(1, new Date(object.getCalendar().getTimeInMillis()));
            statement.setTime(2, new Time(object.getCalendar().getTimeInMillis()));
            statement.setLong(3, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    protected void prepareStatementForUpdateSecond(PreparedStatement statement,
                                             Event object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setString(2, object.getPlace());
            statement.setLong(3, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement,
                                             Event object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    public Event persist(Event object) throws PersistException {
        Event persistInstance;
        Event obj = object;
        // Добавляем запись
        String sql = getCreateQuery();
        String sql2 = getCreateQuerySecond();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement statement2 = connection.prepareStatement(sql2)) {
            prepareStatementForInsert(statement, obj);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
            prepareStatementForInsertSecond(statement2, obj);
            int count2 = statement2.executeUpdate();
            if (count2 != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count2);
            }
            connection.commit();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
        // Получаем только что вставленную запись
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Event> list = parseResultSet(resultSet);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
        return persistInstance;
    }

    public void update(Event object) throws PersistException {
        String sql = getUpdateQuery();
        String sql2 = getUpdateQuerySecond();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement statement2 = connection.prepareStatement(sql2)) {
            connection.setAutoCommit(false);
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
            prepareStatementForUpdateSecond(statement2, object);
            int count1 = statement.executeUpdate();
            if (count1 != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
            connection.commit();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }
}
