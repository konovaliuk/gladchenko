package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.User;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 27.12.17.
 */
public class UserService {
    private static final Logger LOG = Logger.getLogger(EventService.class.getName());
    private static IFactory factory = new MySqlDaoFactory();

    public static List<User> getUsersByParam(String param, String value) {
        List<User> users = new ArrayList<>();
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric userDao = factory.getDao(connection, User.class);
            users = userDao.getAllByParam(param, value);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return users;
    }

    public static long getUserRole(String login) {
        long result = 0L;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric dao = factory.getDao(connection, User.class);
            User user = (User) dao.getByParam("login", login);
            result = user.getIdUserType();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }
}
