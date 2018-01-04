package com.conference.persistence.dao;

import com.conference.persistence.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gleb on 14.12.17.
 */

public class UserDao extends AbstractDao<User, Long> {
    public UserDao(Connection connection) {
        super(connection);
    }

    private class PersistUser extends User {
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public User create() throws PersistException {
        User user = new User();
        return persist(user);
    }

    @Override
    public String getSelectQuery() {
        return "select id, login, password, email, name, lastname, id_role from users";
    }

    @Override
    public String getCreateQuery() {
        return "insert into users (login, password, email, name, lastname, id_role) values(?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update users set login = ?, password = ?, email = ?, name = ?, lastname = ?, id_role = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from users where id = ?;";
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                PersistUser user = new PersistUser();
                user.setId(resultSet.getLong("id"));
                user.setLastName(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setIdUserType(resultSet.getLong("id_role"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getName());
            statement.setString(5, object.getLastName());
            statement.setLong(6, object.getIdUserType());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getName());
            statement.setString(5, object.getLastName());
            statement.setLong(6, object.getIdUserType());
            statement.setLong(7, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
