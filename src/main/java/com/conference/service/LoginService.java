package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.User;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by gleb on 19.12.17.
 */
public class LoginService {
    private static final Logger LOG = Logger.getLogger(EventService.class.getName());
    private static IFactory factory = new MySqlDaoFactory();

    public static boolean checkLogin(String login, String password) {
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric dao = factory.getDao(connection, User.class);
            User user = (User) dao.getByParam("login", login);
            if (password.trim().equalsIgnoreCase("")) return false;
            if (user.getPassword().equals(password)) return true;
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        if (login.trim().equalsIgnoreCase("")) return false;
        return false;
    }

    public static boolean checkRole(String login, long role) {
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric dao = factory.getDao(connection, User.class);
            User user = (User) dao.getByParam("login", login);
            if (user.getIdUserType() == role) return true;
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        if (login.trim().equalsIgnoreCase("")) return false;
        return false;
    }

    public static long getUserId(String login) {
        long result = 0L;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric dao = factory.getDao(connection, User.class);
            User user = (User) dao.getByParam("login", login);
            result = user.getId();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }
}
