package utils;

import driverFactory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static utils.LoadProperties.WAITING_TIMEOUT;

public class WaitingUtility {
    protected WebDriver driver = DriverFactory.getInstance().getDriver();

    protected WebElement waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIMEOUT);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementAndClick(By locator) {
        waitUntilClickable(locator).click();
    }

    protected boolean elementIsDisplayed(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    protected void waitUntilElementIsVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIMEOUT);
        wait.until(visibilityOfElementLocated(locator));
    }

}