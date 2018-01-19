package com.conference.web.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by gleb on 19.12.17.
 */
public class MessageProperties {
    private static MessageProperties instance;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME ="message";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE="IO_EXCEPTION_ERROR_MESSAGE";
    public static final String SUCCESS_REPORT_REGISTRATION = "SUCCESS_REPORT_REGISTRATION";

    public static MessageProperties getInstance(Locale locale){
        if (instance == null){
            instance = new MessageProperties();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        }
        return instance;
    }

    public String getProperty(String key){
       return resourceBundle.getString(key);
    }
}
