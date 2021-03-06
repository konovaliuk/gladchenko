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
    private IFactory factory = new MySqlDaoFactory();

    public Salary getSalary(long userId)  throws PersistException {
        Salary result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric dao = factory.getDao(connection, Salary.class);
            result = (Salary) dao.getByParam("id_user", String.valueOf(userId));
            if (result.getRating() > 49) {
                result.setBonus(500);
            }
            if (result.getRating() > 99) {
                result.setBonus(1000);
            }
            if (result.getRating() > 149) {
                result.setBonus(1500);
            }
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }
}
