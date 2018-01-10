package com.conference.web;

import com.conference.web.properties.LocalProperties;

import javax.servlet.http.HttpSession;

/**
 * Created by gleb on 10.01.18.
 */
public class Localization {
    public static void setLocalProp(HttpSession session) {
        session.setAttribute("allreportslocalvar", LocalProperties.getInstance(session).getProperty(LocalProperties.ALL_REPORTS));
        session.setAttribute("varchosereport", LocalProperties.getInstance(session).getProperty(LocalProperties.CHOOSE_REPORT));
        session.setAttribute("varchangeevent", LocalProperties.getInstance(session).getProperty(LocalProperties.CHANGE_EVENT));
        session.setAttribute("varchooseevent", LocalProperties.getInstance(session).getProperty(LocalProperties.CHOOSE_EVENT));
        session.setAttribute("varchangeeventbtn", LocalProperties.getInstance(session).getProperty(LocalProperties.CHANGE_EVENT_BTN));
        session.setAttribute("varchangereport", LocalProperties.getInstance(session).getProperty(LocalProperties.CHANGE_REPORT));
        session.setAttribute("varregistrbtn", LocalProperties.getInstance(session).getProperty(LocalProperties.REGISTRATION_BTN));
        session.setAttribute("varlogout", LocalProperties.getInstance(session).getProperty(LocalProperties.LOGOUT));
        session.setAttribute("varlogoutbtn", LocalProperties.getInstance(session).getProperty(LocalProperties.LOGOUT_BTN));
    }
}
