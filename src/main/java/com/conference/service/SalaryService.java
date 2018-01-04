package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Salary;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gleb on 26.12.17.
 */
public class SalaryService {
    private static final Logger LOG = Logger.getLogger(EventService.class.getName());
    private static IFactory factory = new MySqlDaoFactory();

    public static Salary getSalary(long userId)  throws PersistException {
        Salary result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric dao = factory.getDao(connection, Salary.class);
            result = (Salary) dao.getByParam("id_user", String.valueOf(userId));
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }
}
