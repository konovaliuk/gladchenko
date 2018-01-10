package com.conference.web.properties;

import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

/**
 * Created by gleb on 10.01.18.
 */
public class LocalProperties {
    private static LocalProperties instance;
    private ResourceBundle resourceBundle;

    public static final String ALL_REPORTS = "ALL_REPORTS";
    public static final String CHANGE_EVENT = "CHANGE_EVENT";
    public static final String CHOOSE_EVENT = "CHOOSE_EVENT";
    public static final String CHANGE_EVENT_BTN = "CHANGE_EVENT_BTN";
    public static final String CHOOSE_REPORT = "CHOOSE_REPORT";
    public static final String CHANGE_REPORT = "CHANGE_REPORT";
    public static final String REGISTRATION_BTN = "REGISTRATION_BTN";
    public static final String LOGOUT = "LOGOUT";
    public static final String LOGOUT_BTN = "LOGOUT_BTN";

    public static LocalProperties getInstance(HttpSession session) {
        if (instance == null) {
            instance = new LocalProperties();
        }
        if (session.getAttribute("local").equals("EN")) {
            instance.resourceBundle = ResourceBundle.getBundle("local");
        }
        if (session.getAttribute("local").equals("RU")) {
            instance.resourceBundle = ResourceBundle.getBundle("local_ru_UA");
        }
        return instance;
    }

    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
