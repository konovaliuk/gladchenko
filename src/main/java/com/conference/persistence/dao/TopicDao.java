package com.conference.persistence.dao;

import com.conference.persistence.entity.Topic;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gleb on 27.12.17.
 */
public class TopicDao extends AbstractDao<Topic, Long> {
    private static final Logger LOG = Logger.getLogger(EventDao.class.getName());
    public TopicDao(Connection connection) {
        super(connection);
    }

    private class PersistTopic extends Topic {
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public Topic create() throws PersistException {
        Topic topic = new Topic();
        return persist(topic);
    }

    @Override
    public String getSelectQuery() {
        return "select id, topic, id_speaker, id_moder, status from topic";
    }

    @Override
    public String getCreateQuery() {
        return "insert into topic (topic, id_speaker, id_moder, status) values(?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update topic set topic = ?, id_speaker = ?, id_moder = ?, status = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from topic where id = ?;";
    }

    @Override
    protected List<Topic> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Topic> result = new LinkedList<>();
        try {
            while (rs.next()) {
                TopicDao.PersistTopic topic = new TopicDao.PersistTopic();
                topic.setId(rs.getLong("id"));
                topic.setTopic(rs.getString("topic"));
                topic.setIdSpeaker(rs.getLong("id_speaker"));
                topic.setIdModer(rs.getLong("id_moder"));
                topic.setStatus(rs.getString("status"));
                result.add(topic);
            }
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Topic object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setLong(2, object.getIdSpeaker());
            statement.setLong(3, object.getIdModer());
            statement.setString(4, object.getStatus());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Topic object) throws PersistException {
        try {
            statement.setString(1, object.getTopic());
            statement.setLong(2, object.getIdSpeaker());
            statement.setLong(3, object.getIdModer());
            statement.setString(4, object.getStatus());
            statement.setLong(5, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Topic object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }
}
