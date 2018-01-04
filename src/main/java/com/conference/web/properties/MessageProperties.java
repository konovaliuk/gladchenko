package com.conference.web.properties;

import java.util.ResourceBundle;

/**
 * Created by gleb on 19.12.17.
 */
public class MessageProperties {
    private static MessageProperties instance;
    //private ResourceBundle resourceBundle;
    //private static final String BUNDLE_NAME ="com.conference.web.properties.message";
    public static final String LOGIN_ERROR_MESSAGE = "Incorrect login or password";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "ServletException: Servlet encounters difficulty";
    public static final String IO_EXCEPTION_ERROR_MESSAGE="IOException: input or output error while handling the request";

    public static MessageProperties getInstance(){
        if (instance == null){
            instance = new MessageProperties();
            //instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    //public String getProperty(String key){
     //   return (String)resourceBundle.getObject(key);
    //}
}
