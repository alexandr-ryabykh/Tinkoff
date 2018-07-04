package driverFactory;

import org.openqa.selenium.chrome.ChromeOptions;

abstract class Options {

    static ChromeOptions chromeOptions(){
        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--start-maximized",
                "--disable-infobars",
                "--disable-notifications",
                "test-type"
        );
        return options;
    }
}
