package driverFactory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

abstract class Options {

    ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--start-maximized",
                "--disable-infobars",
                "--disable-notifications",
                "test-type"
        );
        return options;
    }

    FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-infobars");
        return options;
    }
}
