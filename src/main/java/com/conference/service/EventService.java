package com.conference.service;

import com.conference.persistence.dao.EventDao;
import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Event;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 21.12.17.
 */
public class EventService {
    private static final Logger LOG = Logger.getLogger(EventService.class.getName());
    private static IFactory factory = new MySqlDaoFactory();

    public static List<Event> getAllEvent() {
        List<Event> events = new ArrayList<>();
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric eventDao = factory.getDao(connection, Event.class);
            events = eventDao.getAll();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return events;
    }

    public static Event getEventById(long id) {
        Event event = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric eventDao = factory.getDao(connection, Event.class);
            event = (Event) eventDao.getByPK(id);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return event;
    }

    public static void updateEvent(Event event) {
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric eventDao = factory.getDao(connection, Event.class);
            eventDao.update(event);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
    }

    public static List<Event> getAllEvent(String lang) {
        List<Event> events = new ArrayList<>();
        try (Connection connection = (Connection) factory.getContext()) {
            EventDao eventDao = (EventDao) factory.getDao(connection, Event.class);
            eventDao.setLanguage(lang);
            events = eventDao.getAll();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return events;
    }

    public static Event getEventById(long id, String lang) {
        Event event = null;
        try (Connection connection = (Connection) factory.getContext()) {
            EventDao eventDao = (EventDao) factory.getDao(connection, Event.class);
            eventDao.setLanguage(lang);
            event = (Event) eventDao.getByPK(id);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return event;
    }

    public static void updateEvent(Event event, String lang) {
        try (Connection connection = (Connection) factory.getContext()) {
            EventDao eventDao = (EventDao) factory.getDao(connection, Event.class);
            eventDao.setLanguage(lang);
            eventDao.update(event);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
    }

}
