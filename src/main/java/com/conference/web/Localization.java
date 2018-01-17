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
        session.setAttribute("allreportslocalvar", encoding(bundle.getString("ALL_REPORTS")));
        session.setAttribute("varchosereport", encoding(bundle.getString("CHOOSE_REPORT")));
        session.setAttribute("varchangeevent", encoding(bundle.getString("CHANGE_EVENT")));
        session.setAttribute("varchooseevent", encoding(bundle.getString("CHOOSE_EVENT")));
        session.setAttribute("varchangeeventbtn", encoding(bundle.getString("CHANGE_EVENT_BTN")));
        session.setAttribute("varchangereport", encoding(bundle.getString("CHANGE_REPORT")));
        session.setAttribute("varregistrbtn", encoding(bundle.getString("REGISTRATION_BTN")));
        session.setAttribute("varlogout", encoding(bundle.getString("LOGOUT")));
        session.setAttribute("varlogoutbtn", encoding(bundle.getString("LOGOUT_BTN")));
        session.setAttribute("varchangelangbtn", encoding(bundle.getString("CHANGE_LANG_BTN")));
        session.setAttribute("varreportlocation", encoding(bundle.getString("REPORT_LOCATION")));
    }

    public static String encoding(String value) throws UnsupportedEncodingException {
        return new String(value.getBytes("ISO-8859-1"), "UTF-8");
    }
}
