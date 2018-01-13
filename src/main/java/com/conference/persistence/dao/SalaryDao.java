package com.conference.persistence.dao;

import com.conference.persistence.entity.Salary;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gleb on 26.12.17.
 */
public class SalaryDao extends AbstractDao<Salary, Long> {
    private static final Logger LOG = Logger.getLogger(EventDao.class.getName());
    public SalaryDao(Connection connection) {
        super(connection);
    }

    private class PersistSalary extends Salary {
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public Salary create() throws PersistException {
        Salary salary = new Salary();
        return persist(salary);
    }

    @Override
    public String getSelectQuery() {
        return "select id, rating, bonus, id_user from salary";
    }

    @Override
    public String getCreateQuery() {
        return "insert into salary (rating, bonus, id_user) values(?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update salary set rating = ?, bonus = ?, id_user = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from salary where id = ?;";
    }

    @Override
    protected List<Salary> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Salary> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistSalary salary = new PersistSalary();
                salary.setId(rs.getLong("id"));
                salary.setRating(rs.getInt("rating"));
                salary.setBonus(rs.getDouble("bonus"));
                salary.setUserId(rs.getLong("id_user"));
                result.add(salary);
            }
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Salary object) throws PersistException {
        try {
            statement.setInt(1, object.getRating());
            statement.setDouble(2, object.getBonus());
            statement.setLong(3, object.getUserId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Salary object) throws PersistException {
        try {
            statement.setInt(1, object.getRating());
            statement.setDouble(2, object.getBonus());
            statement.setLong(3, object.getUserId());
            statement.setLong(4, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Salary object) throws PersistException {
        try {
            statement.setLong(1, object.getId());
        } catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new PersistException(e);
        }
    }
}
