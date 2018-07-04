package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitingUtility;

import java.util.List;

public class BasePage extends WaitingUtility {

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected String getCurrentUrl(){
       return driver.getCurrentUrl();
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    protected void refreshUrl() {
        driver.navigate().refresh();
    }

    public String getText(By locator) {
        return waitUntilClickable(locator).getText();
    }

    protected void fillInputField(By locator, String message) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(message);
    }

    public void setAttribute(By locator, String message) {
        waitUntilClickable(locator);
    }
    List<WebElement> selectFromList(By locator) {
        return driver.findElements(locator);
    }


}
