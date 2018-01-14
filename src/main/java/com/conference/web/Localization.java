package com.conference.web;

import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

/**
 * Created by gleb on 10.01.18.
 */
public class Localization {
    public static void setLocalProp(HttpSession session, ResourceBundle bundle) {
        session.setAttribute("allreportslocalvar", bundle.getString("ALL_REPORTS"));
        session.setAttribute("varchosereport", bundle.getString("CHOOSE_REPORT"));
        session.setAttribute("varchangeevent", bundle.getString("CHANGE_EVENT"));
        session.setAttribute("varchooseevent", bundle.getString("CHOOSE_EVENT"));
        session.setAttribute("varchangeeventbtn", bundle.getString("CHANGE_EVENT_BTN"));
        session.setAttribute("varchangereport", bundle.getString("CHANGE_REPORT"));
        session.setAttribute("varregistrbtn", bundle.getString("REGISTRATION_BTN"));
        session.setAttribute("varlogout", bundle.getString("LOGOUT"));
        session.setAttribute("varlogoutbtn", bundle.getString("LOGOUT_BTN"));
        session.setAttribute("varchangelangbtn", bundle.getString("CHANGE_LANG_BTN"));
        session.setAttribute("varreportlocation", bundle.getString("REPORT_LOCATION"));
    }
}
