package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static utils.LoadProperties.PAYMENTS_LINK;

public class Payments extends BasePage {

    private MoscowCommunalServices communalServices = new MoscowCommunalServices();
    By communalPayments = By.xpath("(//a[contains(@href, 'kommunalnie-platezhi/')])[2]");
    private By quickSearch = By.className("Input__valueContent_primary_3sxF0");
    private By suggestedCommunalOrganization = By.cssSelector(".SearchSuggest__entry_highlighted_1SPg3 > div:nth-child(1) > div:nth-child(1)");

    public void openCommunalPayments() {
        navigateTo(PAYMENTS_LINK);
        Assert.assertTrue(elementIsDisplayed(communalPayments));
        waitForElementAndClick(communalPayments);
    }


    public void searchForServiceUsingQuickSearch() {
        fillInputField(quickSearch, "ЖКУ-Москва");
        List<WebElement> elements = selectFromList(suggestedCommunalOrganization);
        Assert.assertEquals(elements.get(0).getText(), "ЖКУ-Москва");
        elements.get(0).click();
    }

    public void verifyThePageIsMoscowCommunalServiceAfterSearching() {
        searchForServiceUsingQuickSearch();
        waitForElementAndClick(communalServices.payCommunalInMoscow);
        Assert.assertEquals(getCurrentUrl(), "https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay");
    }
}
