package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.entity.Registration;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 27.12.17.
 */
public class RegistrationService {
    private static final Logger LOG = Logger.getLogger(RegistrationService.class.getName());
    private IFactory factory = new MySqlDaoFactory();

    public Registration createRegistration(Registration registration) {
        Registration result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric registrationDao = factory.getDao(connection, Registration.class);
            result = (Registration) registrationDao.persist(registration);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }

    public int getRegistrationsCount() {
        List<Registration> result = new ArrayList<>();
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric registrationDao = factory.getDao(connection, Registration.class);
            result = registrationDao.getAll();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result.size();
    }
}
