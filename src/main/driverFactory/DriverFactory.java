package driverFactory;

import Events.EventLoger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static utils.LoadProperties.*;
import static utils.LoadProperties.DRIVER_NAME;

public class DriverFactory extends Options {
    public static DriverFactory getInstance() {
        if (driverThread.get() == null) {
            synchronized (DriverFactory.class) {
                driverThread.set(new DriverFactory());
            }
        }
        return driverThread.get();
    }

    private static ThreadLocal<DriverFactory> driverThread = new ThreadLocal<>();

    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = initialDriver(DRIVER_NAME);
            return driver;
        } else {
            return driver;
        }
    }

    private DriverFactory() {
    }

    private synchronized WebDriver initialDriver(String driverName) {
        switch (driverName) {
            case "CHROME": {
                System.setProperty(DRIVER_NAME_CHROME,
                        System.getProperty("os.name").equals("Linux") ? System.getProperty("user.dir") + LINUX_PATH :
                                System.getProperty("user.dir") + WINDOWS_PATH
                );
                driver = new ChromeDriver(chromeOptions());
                break;
            }
            case "FIREFOX": {
                driver = new FirefoxDriver(firefoxOptions());
                break;
            }

        }
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        EventLoger log = new EventLoger() {
        };
        driver = eventDriver.register(log);
        return driver;
    }

    public void destroy() {
        if (driver != null) {
            getDriver().quit();
            driver = null;
        }
    }

}
