package ru.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

/**
 * Edit by Tikhonov Sergey
 */
public class Init {
    private static Map<String, String> settings = new HashMap<>();

    static {
        PropertyResourceBundle propertyResource = (PropertyResourceBundle) PropertyResourceBundle.getBundle("config");
        settings.put("logFileURL", propertyResource.getString("logFileURL"));
        settings.put("jdbcClassDriver", propertyResource.getString("jdbcClassDriver"));
        settings.put("DataBaseConnectionURL", propertyResource.getString("DataBaseConnectionURL"));
    }

    public static String getJDBCClassDriverName() {
        return settings.get("jdbcClassDriver");

    }

    public static String getLogFileName() {
        return settings.get("logFileURL");

    }

    public static String getDataBaseURL() {
        return settings.get("DataBaseConnectionURL");

    }

}
