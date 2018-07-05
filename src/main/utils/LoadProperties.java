package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    public static String DRIVER_NAME;
    public static String DRIVER_NAME_CHROME;
    public static String WINDOWS_PATH;
    public static String LINUX_PATH;
    public static String PAYMENTS_LINK;
    public static String COMMUNAL_PAYMENTS_LINK;
    public static String COMMUNAL_PAYMENTS_MOSCOW;
    public static Long WAITING_TIMEOUT;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(System.getProperty("user.dir")
                    + "\\src\\resources\\application.properties"));
            DRIVER_NAME_CHROME = prop.getProperty("DRIVER_NAME_CHROME");
            DRIVER_NAME = prop.getProperty("DRIVER_NAME");
            LINUX_PATH = prop.getProperty("CHROME_DRIVER_PATH_LINUX");
            WINDOWS_PATH = prop.getProperty("CHROME_DRIVER_PATH_WINDOWS");
            PAYMENTS_LINK = prop.getProperty("PAYMENTS_LINK");
            COMMUNAL_PAYMENTS_LINK = prop.getProperty("COMMUNAL_PAYMENTS_LINK");
            COMMUNAL_PAYMENTS_MOSCOW = prop.getProperty("COMMUNAL_PAYMENTS_MOSCOW");
            WAITING_TIMEOUT = Long.valueOf(prop.getProperty("WAITING_TIMEOUT"));
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
