package com.conference.web;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * Created by gleb on 10.01.18.
 */
public class Localization {
    public static void setLocalProp(HttpSession session, ResourceBundle bundle) throws UnsupportedEncodingException {
        long role = (long) session.getAttribute("role");
        session.setAttribute("varAllReports", encoding(bundle.getString("ALL_REPORTS")));
        session.setAttribute("varchangeevent", encoding(bundle.getString("CHANGE_EVENT")));
        session.setAttribute("varchooseevent", encoding(bundle.getString("CHOOSE_EVENT")));
        session.setAttribute("varchangeeventbtn", encoding(bundle.getString("CHANGE_EVENT_BTN")));
        session.setAttribute("varLogoutBtn", encoding(bundle.getString("LOGOUT_BTN")));
        session.setAttribute("varchangelangbtn", encoding(bundle.getString("CHANGE_LANG_BTN")));
        session.setAttribute("varReportLocation", encoding(bundle.getString("REPORT_LOCATION")));
        if (role == 4) {
            session.setAttribute("varchosereport", encoding(bundle.getString("CHOOSE_REPORT")));
            session.setAttribute("varchangereport", encoding(bundle.getString("CHANGE_REPORT")));
            session.setAttribute("varregistrbtn", encoding(bundle.getString("REGISTRATION_BTN")));
            session.setAttribute("varlogout", encoding(bundle.getString("LOGOUT")));
        }
        if (role == 3) {
            session.setAttribute("varSalary", encoding(bundle.getString("SALARY")));
            session.setAttribute("varRating", encoding(bundle.getString("RATING")));
            session.setAttribute("varBonus", encoding(bundle.getString("BONUS")));
            session.setAttribute("varProposeTopic", encoding(bundle.getString("PROPOSE_TOPIC")));
            session.setAttribute("varTopic", encoding(bundle.getString("TOPIC")));
            session.setAttribute("varProposeTopicBtn", encoding(bundle.getString("PROPOSE_TOPIC_BTN")));
            session.setAttribute("varNewTopics", encoding(bundle.getString("NEW_TOPICS")));
            session.setAttribute("varConfirmedTopics", encoding(bundle.getString("CONFIRMED_TOPICS")));
            session.setAttribute("varConfirmedOrCancel", encoding(bundle.getString("CONFIRM_OR_CANCEL")));
            session.setAttribute("varConfirmTopicBtn", encoding(bundle.getString("CONFIRM_TOPIC_BTN")));
            session.setAttribute("varAllTopics", encoding(bundle.getString("ALL_TOPICS")));
        }
    }

    public static String encoding(String value) throws UnsupportedEncodingException {
        return new String(value.getBytes("ISO-8859-1"), "UTF-8");
    }
}
