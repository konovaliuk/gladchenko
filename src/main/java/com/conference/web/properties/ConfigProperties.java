package com.conference.web.properties;

import java.util.ResourceBundle;

/**
 * Created by gleb on 19.12.17.
 */
public class ConfigProperties {
    private static ConfigProperties instance;
    //private ResourceBundle resourceBundle;

    //private static final String BUNDLE_NAME = "com.conference.web.properties.config";
    public static final String ERROR_PAGE_PATH = "/jsp/error.jsp";
    public static final String LOGIN_PAGE_PATH = "/jsp/login.jsp";
    public static final String USER_PAGE_PATH = "/jsp/user.jsp";
    public static final String ADMIN_PAGE_PATH = "/jsp/admin.jsp";
    public static final String MODER_PAGE_PATH = "/jsp/moder.jsp";
    public static final String SPEAKER_PAGE_PATH = "/jsp/speaker.jsp";
    public static final String PAGIN_PAGE_PATH = "topics.jsp";

    public static ConfigProperties getInstance() {
        if (instance == null) {
            instance = new ConfigProperties();
            //instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    //public String getProperty(String key){
       // return (String)resourceBundle.getObject(key);
   // }
}
