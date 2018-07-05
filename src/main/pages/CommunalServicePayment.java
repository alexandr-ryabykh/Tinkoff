package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;
import static utils.LoadProperties.COMMUNAL_PAYMENTS_LINK;

public class CommunalServicePayment extends BasePage {

    private ListOfRegions list = new ListOfRegions();

    MoscowCommunalServices moscowCommunalServicesPage = new MoscowCommunalServices();

    private By cityInCommunalPayments = By.xpath("(//*[@aria-label = 'Москве'])[1]");
    private By locateCityInCommunalPayments = By.xpath("(//*[@data-qa-file = 'PaymentsCatalogHeader'])[3]");
    public By moscowCommunalService = By.xpath("//span[text()='ЖКУ-Москва']");
    private By allServiceProviders = By.className("UILayoutSection__section_32gTj");

    @Step("Check is the current city is Moscow")
    public void verifyTheCityIsMoscow() {
        navigateTo(COMMUNAL_PAYMENTS_LINK);
        if (elementIsDisplayed(cityInCommunalPayments)) {
            Assert.assertTrue(getText(cityInCommunalPayments).contains("Москв"));
        } else {
            waitForElementAndClick(locateCityInCommunalPayments);
            waitForElementAndClick(list.moscowRegion);
        }
    }

    @Step("Open Moscow communal services")
    public void goToMoscowCommunalService() {
        navigateTo(COMMUNAL_PAYMENTS_LINK);
        waitForElementAndClick(moscowCommunalService);
        waitUntilElementIsVisible(moscowCommunalServicesPage.payCommunalInMoscow);
    }

    @Step("Change city to another and check absence of Moscow Service")
    public void changeCityToAnotherAndCheckAbsenceOfMoscowService() {
        navigateTo(COMMUNAL_PAYMENTS_LINK);
        waitForElementAndClick(locateCityInCommunalPayments);
        waitForElementAndClick(list.spbRegion);

        for (WebElement webElement : selectFromList(allServiceProviders)/*driver.findElements(allServiceProviders)*/) {
            if (webElement.getText().equals("ЖКУ-Москва")) {
                assertTrue(false);
            }
        }
    }
}
