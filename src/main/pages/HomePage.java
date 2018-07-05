package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage {

    public By payments = By.cssSelector("div.Tabs__item_y4Mo9:nth-child(6) > a:nth-child(1) > span:nth-child(1)");

    @Step("Open home page")
    public void openHomePage() {
        openUrl("https://www.tinkoff.ru/");
        Assert.assertTrue(elementIsDisplayed(payments));
    }

    @Step("Open payments page")
    public void openPaymentsLink() {
        Payments communal = new Payments();
        waitForElementAndClick(payments);
        waitUntilElementIsVisible(communal.communalPayments);
        Assert.assertTrue(elementIsDisplayed(communal.communalPayments));
    }
}
