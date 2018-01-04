package com.conference.persistence.dao;

import com.conference.persistence.entity.*;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gleb on 12.12.17.
 */
public class MySqlDaoFactory implements IFactory<Connection> {
    private Map<Class, IDaoCreator> creators;

    public MySqlDaoFactory() {
        creators = new HashMap<>();
        creators.put(Event.class, new IDaoCreator<Connection>() {
            @Override
            public IGeneric create(Connection connection) {
                return new EventDao(connection);
            }
        });
        creators.put(User.class, new IDaoCreator<Connection>() {
            @Override
            public IGeneric create(Connection connection) {
                return new UserDao(connection);
            }
        });
        creators.put(Report.class, new IDaoCreator<Connection>() {
            @Override
            public IGeneric create(Connection connection) {
                return new ReportDao(connection);
            }
        });
        creators.put(Salary.class, new IDaoCreator<Connection>() {
            @Override
            public IGeneric create(Connection connection) {
                return new SalaryDao(connection);
            }
        });
        creators.put(Registration.class, new IDaoCreator<Connection>() {
            @Override
            public IGeneric create(Connection connection) {
                return new RegistrationDao(connection);
            }
        });
        creators.put(Topic.class, new IDaoCreator<Connection>() {
            @Override
            public IGeneric create(Connection connection) {
                return new TopicDao(connection);
            }
        });
    }

    public IGeneric getDao(Connection connection, Class dtoClass) throws PersistException {
        IDaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            InitialContext initContext= new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/TestDB");
            connection = ds.getConnection();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return  connection;
    }
}
