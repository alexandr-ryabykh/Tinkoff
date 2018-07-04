package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage {
    private Payments communal = new Payments();

    public By payments = By.cssSelector("div.Tabs__item_y4Mo9:nth-child(6) > a:nth-child(1) > span:nth-child(1)");

    public void openHomePage() {
        openUrl("https://www.tinkoff.ru/");
    }

    public void openPaymentsLink() {
        waitForElementAndClick(payments);
        waitUntilElementIsVisible(communal.communalPayments);
        Assert.assertTrue(elementIsDisplayed(communal.communalPayments));
    }
}
