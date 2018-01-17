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
        session.setAttribute("allreportslocalvar", uncoding(bundle.getString("ALL_REPORTS")));
        session.setAttribute("varchosereport", uncoding(bundle.getString("CHOOSE_REPORT")));
        session.setAttribute("varchangeevent", uncoding(bundle.getString("CHANGE_EVENT")));
        session.setAttribute("varchooseevent", uncoding(bundle.getString("CHOOSE_EVENT")));
        session.setAttribute("varchangeeventbtn", uncoding(bundle.getString("CHANGE_EVENT_BTN")));
        session.setAttribute("varchangereport", uncoding(bundle.getString("CHANGE_REPORT")));
        session.setAttribute("varregistrbtn", uncoding(bundle.getString("REGISTRATION_BTN")));
        session.setAttribute("varlogout", uncoding(bundle.getString("LOGOUT")));
        session.setAttribute("varlogoutbtn", uncoding(bundle.getString("LOGOUT_BTN")));
        session.setAttribute("varchangelangbtn", uncoding(bundle.getString("CHANGE_LANG_BTN")));
        session.setAttribute("varreportlocation", uncoding(bundle.getString("REPORT_LOCATION")));
    }

    public static String uncoding(String value) throws UnsupportedEncodingException {
        return new String(value.getBytes("ISO-8859-1"), "UTF-8");
    }
}
