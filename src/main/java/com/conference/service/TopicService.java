package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.entity.Topic;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 01.01.18.
 */
public class TopicService {
    private static final Logger LOG = Logger.getLogger(TopicService.class.getName());
    private IFactory factory = new MySqlDaoFactory();

    public Topic createTopic(Topic topic) {
        Topic result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric topicDao = factory.getDao(connection, Topic.class);
            result = (Topic) topicDao.persist(topic);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }

    public List<Topic> getNewTopicsByParam(String param, String value) {
        List<Topic> topics = getTopicsByParam(param, value);
        List<Topic> result = new ArrayList<>();
        for (Topic t : topics) {
            if (t.getStatus().equalsIgnoreCase("new")) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Topic> getConfModerTopicsByParam(String param, String value) {
        List<Topic> topics = getTopicsByParam(param, value);
        List<Topic> result = new ArrayList<>();
        for (Topic t : topics) {
            if (t.getStatus().equalsIgnoreCase("confmod")) {
                result.add(t);
            }
        }
        return result;
    }


    public List<Topic> getTopicsByParam(String param, String value) {
        List<Topic> topics = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric topicDao = factory.getDao(connection, Topic.class);
            topics = topicDao.getAllByParam(param, value);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return topics;
    }

    public Topic getTopicById(long id) {
        Topic result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric topicDao = factory.getDao(connection, Topic.class);
            result = (Topic) topicDao.getByPK(id);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }

    public void updateTopic(Topic topic) {
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric topicDao = factory.getDao(connection, Topic.class);
            topicDao.update(topic);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
    }

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric topicDao = factory.getDao(connection, Topic.class);
            topics = topicDao.getAll();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return topics;
    }
}
