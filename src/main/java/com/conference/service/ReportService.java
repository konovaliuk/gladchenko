package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.dao.PersistException;
import com.conference.persistence.dao.ReportDao;
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

    public List<Report> getAllReport(String lang) {
        List<Report> reports = new ArrayList<>();
        try (Connection connection = (Connection) factory.getContext()) {
            ReportDao reportDao = (ReportDao) factory.getDao(connection, Report.class);
            reportDao.setLanguage(lang);
            reports = reportDao.getAll();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return reports;
    }

    public void updateReport(Report report, String lang) {
        try (Connection connection = (Connection) factory.getContext()) {
            ReportDao reportDao = (ReportDao) factory.getDao(connection, Report.class);
            reportDao.setLanguage(lang);
            reportDao.update(report);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
    }

    public Report getReportByPK(Long id, String lang) {
        Report result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            ReportDao reportDao = (ReportDao) factory.getDao(connection, Report.class);
            reportDao.setLanguage(lang);
            result = reportDao.getByPK(id);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }

    public List<Report> getReportsByParam(String param, String value, String lang)  throws PersistException {
        List<Report> result = null;
        try (Connection connection = (Connection) factory.getContext()) {
            ReportDao reportDao = (ReportDao) factory.getDao(connection, Report.class);
            reportDao.setLanguage(lang);
            result = reportDao.getAllByParam(param, value);
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return result;
    }
}
