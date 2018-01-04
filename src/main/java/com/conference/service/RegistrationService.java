package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.entity.Registration;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Created by gleb on 27.12.17.
 */
public class RegistrationService {
    private static final Logger LOG = Logger.getLogger(RegistrationService.class.getName());
    private static IFactory factory = new MySqlDaoFactory();

    public static Registration createRegistration(Registration registration) {
        Registration result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric registrationDao = factory.getDao(connection, Registration.class);
            result = (Registration) registrationDao.persist(registration);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }
}
