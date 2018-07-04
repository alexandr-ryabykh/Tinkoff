package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.WaitingUtility;

import java.util.List;

public class BasePage extends WaitingUtility {

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public String getText(By locator) {
        return waitUntilClickable(locator).getText();
    }

    protected void fillInputField(By locator, String message) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(message);
    }

    List<WebElement> selectFromList(By locator) {
        return driver.findElements(locator);
    }

    public void loseFocusWithWait() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(-10000, 0).click().perform();
    }


}
