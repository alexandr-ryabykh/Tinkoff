package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static utils.LoadProperties.PAYMENTS_LINK;

public class Payments extends BasePage {

    private MoscowCommunalServices communalServices = new MoscowCommunalServices();
    By communalPayments = By.xpath("(//a[contains(@href, 'kommunalnie-platezhi/')])[2]");
    private By quickSearch = By.xpath("//*[@data-qa-file='FormFieldSearchAndPay']//input");
    private By suggestedCommunalOrganization = By.xpath("//*[@data-qa-file='SuggestEntry' and descendant::*[@data-qa-file='Text']]/div/div");

    @Step("Check if communal payments is present and open it")
    public void openCommunalPayments() {
        navigateTo(PAYMENTS_LINK);
        Assert.assertTrue(elementIsDisplayed(communalPayments));
        waitForElementAndClick(communalPayments);
    }

    @Step("Search for Moscow Communal Service using QuickSearch")
    public void searchForServiceUsingQuickSearch() {
        fillInputField(quickSearch, "ЖКУ-Москва");
        List<WebElement> elements = selectFromList(suggestedCommunalOrganization);
        Assert.assertEquals(elements.get(0).getText(), "ЖКУ-Москва");
        elements.get(0).click();
    }

    @Step("Verify the page is Moscow Communal ServiceA after searching")
    public void verifyThePageIsMoscowCommunalServiceAfterSearching() {
        searchForServiceUsingQuickSearch();
        waitForElementAndClick(communalServices.payCommunalInMoscow);
        Assert.assertEquals(getCurrentUrl(), "https://www.tinkoff.ru/zhku-moskva/");
    }
}
