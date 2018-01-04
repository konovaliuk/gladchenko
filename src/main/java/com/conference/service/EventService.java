package com.conference.service;

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

}
