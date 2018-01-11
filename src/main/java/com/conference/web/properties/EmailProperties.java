package com.conference.web.properties;

import java.util.ResourceBundle;

/**
 * Created by gleb on 11.01.18.
 */
public class EmailProperties {
    private static EmailProperties instance;
    private ResourceBundle resourceBundle;
    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";

    public static EmailProperties getInstance() {
        if (instance == null) {
            instance = new EmailProperties();
            instance.resourceBundle = ResourceBundle.getBundle("email");
        }
        return instance;
    }

    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
