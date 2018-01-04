package com.conference.persistence.dao;

import com.conference.persistence.entity.Report;
import com.conference.persistence.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gleb on 20.12.17.
 */
public class ReportDao extends AbstractDao<Report, Long> {
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
        return "select id, topic, place, e_date, e_time, id_speaker, id_event from report";
    }

    @Override
    public String getCreateQuery() {
        return "insert into report (topic, place, e_date, e_time, id_speaker, id_event) "
                + "values(?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update report set topic = ?, place = ?, e_date = ?, e_time = ?, id_speaker = ?, id_event = ? where id = ?;";
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
                result.add(report);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setString(2, object.getPlace());
            statement.setDate(3, new Date(object.getCalendar().getTimeInMillis()));
            statement.setTime(4, new Time(object.getCalendar().getTimeInMillis()));
            statement.setLong(5, object.getIdSpeaker());
            statement.setLong(6, object.getIdEvent());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setString(2, object.getPlace());
            statement.setDate(3, new Date(object.getCalendar().getTimeInMillis()));
            statement.setTime(4, new Time(object.getCalendar().getTimeInMillis()));
            statement.setLong(5, object.getIdSpeaker());
            statement.setLong(6, object.getIdEvent());
            statement.setLong(7, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Report object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
