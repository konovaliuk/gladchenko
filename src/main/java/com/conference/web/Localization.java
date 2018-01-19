package com.conference.web;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * Created by gleb on 10.01.18.
 */
public class Localization {
    public static String encoding(String value) throws UnsupportedEncodingException {
        return new String(value.getBytes("ISO-8859-1"), "UTF-8");
    }

    public static void setLocalProp(HttpSession session, ResourceBundle bundle) throws UnsupportedEncodingException {
        long role = (long) session.getAttribute("role");
        session.setAttribute("varAllReports", encoding(bundle.getString("ALL_REPORTS")));
        session.setAttribute("varChangeEvent", encoding(bundle.getString("CHANGE_EVENT")));
        session.setAttribute("varChooseEvent", encoding(bundle.getString("CHOOSE_EVENT")));
        session.setAttribute("varChangeEventBtn", encoding(bundle.getString("CHANGE_EVENT_BTN")));
        session.setAttribute("varLogoutBtn", encoding(bundle.getString("LOGOUT_BTN")));
        session.setAttribute("varChangeLangBtn", encoding(bundle.getString("CHANGE_LANG_BTN")));
        session.setAttribute("varReportLocation", encoding(bundle.getString("REPORT_LOCATION")));
        if (role == 4) {
            session.setAttribute("varChooseReport", encoding(bundle.getString("CHOOSE_REPORT")));
            session.setAttribute("varChangeReport", encoding(bundle.getString("CHANGE_REPORT")));
            session.setAttribute("varRegistrationBtn", encoding(bundle.getString("REGISTRATION_BTN")));
            session.setAttribute("varLogout", encoding(bundle.getString("LOGOUT")));
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
            session.setAttribute("varConfirmOrCancel", encoding(bundle.getString("CONFIRM_OR_CANCEL")));
            session.setAttribute("varConfirm", encoding(bundle.getString("CONFIRM")));
            session.setAttribute("varCancel", encoding(bundle.getString("CANCEL")));
            session.setAttribute("varConfirmTopicBtn", encoding(bundle.getString("CONFIRM_TOPIC_BTN")));
            session.setAttribute("varAllTopics", encoding(bundle.getString("ALL_TOPICS")));
        }
        if (role == 2) {
            session.setAttribute("varEditPlaceDate", encoding(bundle.getString("EDIT_PLACE_DATE")));
            session.setAttribute("varPlace", encoding(bundle.getString("PLACE")));
            session.setAttribute("varDate", encoding(bundle.getString("DATE")));
            session.setAttribute("varTime", encoding(bundle.getString("TIME")));
            session.setAttribute("varEditPlaceDateBtn", encoding(bundle.getString("EDIT_PLACE_DATE_BTN")));
            session.setAttribute("varEditReport", encoding(bundle.getString("EDIT_REPORT")));
            session.setAttribute("varChooseReport", encoding(bundle.getString("CHOOSE_REPORT")));
            session.setAttribute("varChooseSpeaker", encoding(bundle.getString("CHOOSE_SPEAKER")));
            session.setAttribute("varTopic", encoding(bundle.getString("TOPIC")));
            session.setAttribute("varEditReportBtn", encoding(bundle.getString("EDIT_REPORT_BTN")));
            session.setAttribute("varProposeTopic", encoding(bundle.getString("PROPOSE_TOPIC")));
            session.setAttribute("varProposeTopicBtn", encoding(bundle.getString("PROPOSE_TOPIC_BTN")));
            session.setAttribute("varConfirmedTopics", encoding(bundle.getString("CONFIRMED_TOPICS")));
            session.setAttribute("varConfirmOrCancel", encoding(bundle.getString("CONFIRM_OR_CANCEL")));
            session.setAttribute("varConfirm", encoding(bundle.getString("CONFIRM")));
            session.setAttribute("varCancel", encoding(bundle.getString("CANCEL")));
            session.setAttribute("varConfirmTopicBtn", encoding(bundle.getString("CONFIRM_TOPIC_BTN")));
            session.setAttribute("varRegistrations", encoding(bundle.getString("REGISTRATIONS")));
            session.setAttribute("varRegistrationStatistic", encoding(bundle.getString("REGISTRATIONS_STATISTIC")));
            session.setAttribute("varVisitors", encoding(bundle.getString("VISITORS")));
            session.setAttribute("varSendEmails", encoding(bundle.getString("SEND_EMAILS")));
            session.setAttribute("varText", encoding(bundle.getString("TEXT")));
            session.setAttribute("varSendEmailsBtn", encoding(bundle.getString("SEND_EMAILS_BTN")));
            session.setAttribute("varAllTopics", encoding(bundle.getString("ALL_TOPICS")));
        }
        if (role == 1) {
            session.setAttribute("varChooseReport", encoding(bundle.getString("CHOOSE_REPORT")));
            session.setAttribute("varChangeReport", encoding(bundle.getString("CHANGE_REPORT")));
            session.setAttribute("varRegistrationBtn", encoding(bundle.getString("REGISTRATION_BTN")));
            session.setAttribute("varLogout", encoding(bundle.getString("LOGOUT")));
            session.setAttribute("varAllTopics", encoding(bundle.getString("ALL_TOPICS")));
            session.setAttribute("varTopic", encoding(bundle.getString("TOPIC")));
            session.setAttribute("varPlace", encoding(bundle.getString("PLACE")));
            session.setAttribute("varDate", encoding(bundle.getString("DATE")));
            session.setAttribute("varTime", encoding(bundle.getString("TIME")));
        }
    }
}
