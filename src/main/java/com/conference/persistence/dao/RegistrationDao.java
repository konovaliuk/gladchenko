package com.conference.persistence.dao;

import com.conference.persistence.entity.Registration;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gleb on 26.12.17.
 */
public class RegistrationDao extends AbstractDao<Registration, Long> {
    private static final Logger LOG = Logger.getLogger(RegistrationDao.class.getName());
    public RegistrationDao(Connection connection) {
        super(connection);
    }

    private class PersistRegistration extends Registration {
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public Registration create() throws PersistException {
        Registration registration = new Registration();
        return persist(registration);
    }

    @Override
    public String getSelectQuery() {
        return "select id, id_user, id_event, id_report from registration";
    }

    @Override
    public String getCreateQuery() {
        return "insert into registration (id_user, id_event, id_report) values(?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update registration set id_user = ?, id_event = ?, id_report = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from registration where id = ?;";
    }

    @Override
    protected List<Registration> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Registration> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistRegistration registration = new PersistRegistration();
                registration.setId(rs.getLong("id"));
                registration.setUserId(rs.getLong("id_user"));
                registration.setEventId(rs.getLong("id_event"));
                registration.setReportId(rs.getLong("id_report"));
                result.add(registration);
            }
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Registration object) throws PersistException {
        try {
            statement.setLong(1, object.getUserId());
            statement.setLong(2, object.getEventId());
            statement.setLong(3, object.getReportId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Registration object) throws PersistException {
        try {
            statement.setLong(1, object.getUserId());
            statement.setLong(2, object.getEventId());
            statement.setLong(3, object.getReportId());
            statement.setLong(4, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Registration object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }
}
