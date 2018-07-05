package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.WaitingUtility;

import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class BasePage extends WaitingUtility {

    void openUrl(String url) {
        driver.get(url);
    }

    String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    void navigateTo(String url) {
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
        waitUntilClickable(locator);
        return driver.findElements(locator);
    }

    void loseFocus() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(-10000, 0).click().perform();
    }

    void sendEnter(By locator) {
        waitUntilClickable(locator).sendKeys(ENTER);
    }
}
