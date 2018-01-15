package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Report;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 25.12.17.
 */
public class ReportService {
    private static final Logger LOG = Logger.getLogger(EventService.class.getName());
    private IFactory factory = new MySqlDaoFactory();

    public List<Report> getAllReport() {
        List<Report> reports = new ArrayList<>();
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric reportDao = factory.getDao(connection, Report.class);
            reports = reportDao.getAll();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return reports;
    }

    public void updateReport(Report report) {
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric reportDao = factory.getDao(connection, Report.class);
            reportDao.update(report);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
    }

    public Report getReportByPK(Long id) {
        Report result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric reportDao = factory.getDao(connection, Report.class);
            result = (Report) reportDao.getByPK(id);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }

    public List<Report> getReportsByParam(String param, String value)  throws PersistException {
        List<Report> result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric dao = factory.getDao(connection, Report.class);
            result = dao.getAllByParam(param, value);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }
}
