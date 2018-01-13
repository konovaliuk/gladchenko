package com.conference.web.properties;

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

    public static MessageProperties getInstance(){
        if (instance == null){
            instance = new MessageProperties();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key){
       return (String)resourceBundle.getObject(key);
    }
}
