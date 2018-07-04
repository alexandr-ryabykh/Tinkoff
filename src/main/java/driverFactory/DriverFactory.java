package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static utils.LoadProperties.*;

public class DriverFactory extends Options {

    private static ThreadLocal<DriverFactory> tlDriver = new ThreadLocal<>();

    private static WebDriver driver;



    public WebDriver getDriver() {
        if (driver == null) {
            driver = createNewDriver();
            return driver;
        } else {
            return driver;
        }
    }

    private static WebDriver createNewDriver() {
        System.setProperty(DRIVER_NAME_CHROME,
                System.getProperty("os.name").equals("Linux") ? System.getProperty("user.dir") + LINUX_PATH :
                        System.getProperty("user.dir") + WINDOWS_PATH
        );
        return new ChromeDriver(chromeOptions());
    }

    public static DriverFactory getInstance() {
        if (tlDriver.get() == null) {
            synchronized (DriverFactory.class) {
                tlDriver.set(new DriverFactory());
            }
        }
        return tlDriver.get();
    }

    public static String testName;

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
