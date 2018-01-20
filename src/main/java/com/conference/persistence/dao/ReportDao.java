package com.conference.persistence.dao;

import com.conference.persistence.entity.Report;
import com.conference.persistence.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gleb on 20.12.17.
 */
public class ReportDao extends AbstractDao<Report, Long> {
    private static final Logger LOG = Logger.getLogger(ReportDao.class.getName());
    private String language = "reportEn";

    public void setLanguage(String language) {
        this.language = language;
    }

    public ReportDao(Connection connection) {
        super(connection);
    }

    private class PersistReport extends Report {
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public Report create() throws PersistException {
        Report report = new Report();
        return persist(report);
    }

    @Override
    public String getSelectQuery() {
        return "select id, topic, place, e_date, e_time, id_speaker, id_event, speaker_lname from report  join "+language+" on report.id = "+language+".report_id";
    }

    @Override
    public String getCreateQuery() {
        return "insert into report (e_date, e_time, id_speaker, id_event) values(?, ?, ?, ?);";
    }

    public String getCreateQuerySecond() {
        return "insert into "+language+" (report_id, topic, place, speaker_lname) values(last_insert_id(), ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update report set e_date = ?, e_time = ?, id_speaker = ?, id_event = ? where id = ?;";
    }

    public String getUpdateQuerySecond() {
        return "update "+language+" set topic = ?, place = ?, speaker_lname = ? where report_id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from report where id = ?;";
    }

    @Override
    protected List<Report> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Report> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistReport report = new PersistReport();
                report.setId(rs.getLong("id"));
                report.setTopic(rs.getString("topic"));
                report.setPlace(rs.getString("place"));
                report.setCalendar(DateTimeParser.dateTimeToCalendar(rs.getString("e_date"),
                                                                rs.getString("e_time")));
                report.setIdSpeaker(rs.getLong("id_speaker"));
                report.setIdEvent(rs.getLong("id_event"));
                report.setSpeakerLastName(rs.getString("speaker_lname"));
                result.add(report);
            }
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setDate(1, new Date(object.getCalendar().getTimeInMillis()));
            statement.setTime(2, new Time(object.getCalendar().getTimeInMillis()));
            statement.setLong(3, object.getIdSpeaker());
            statement.setLong(4, object.getIdEvent());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    protected void prepareStatementForInsertSecond(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setString(2, object.getPlace());
            statement.setString(3, object.getSpeakerLastName());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setDate(1, new Date(object.getCalendar().getTimeInMillis()));
            statement.setTime(2, new Time(object.getCalendar().getTimeInMillis()));
            statement.setLong(3, object.getIdSpeaker());
            statement.setLong(4, object.getIdEvent());
            statement.setLong(5, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    protected void prepareStatementForUpdateSecond(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setString(2, object.getPlace());
            statement.setString(3, object.getSpeakerLastName());
            statement.setLong(4, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    public Report persist(Report object) throws PersistException {
        Report persistInstance;
        Report obj = object;
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
            List<Report> list = parseResultSet(resultSet);
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

    public void update(Report object) throws PersistException {
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
            int count1 = statement2.executeUpdate();
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
