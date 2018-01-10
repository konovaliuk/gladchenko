package com.conference.web.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by gleb on 19.12.17.
 */
public class ConfigProperties {
    private static ConfigProperties instance;
    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String USER_PAGE_PATH = "USER_PAGE_PATH";
    public static final String ADMIN_PAGE_PATH = "ADMIN_PAGE_PATH";
    public static final String MODER_PAGE_PATH = "MODER_PAGE_PATH";
    public static final String SPEAKER_PAGE_PATH = "SPEAKER_PAGE_PATH";


    public static ConfigProperties getInstance() {
        if (instance == null) {
            instance = new ConfigProperties();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }


}
