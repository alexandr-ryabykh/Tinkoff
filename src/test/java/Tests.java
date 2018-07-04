import driverFactory.DriverFactory;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.*;

public class Tests extends BasePage {
    private HomePage homePage = new HomePage();
    private CommunalServicePayment communalServicePayment = new CommunalServicePayment();
    private Payments payments = new Payments();
    private MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();


    /*1*/
    @Test
    @Description("Open home page")
    public void openHomePageTest() {
        homePage.openHomePage();
        Assert.assertTrue(elementIsDisplayed(homePage.payments));
    }

    /*2*/
    @Test
    @Description("Open payments page")
    public void openPaymentsPageTest() {
        homePage.openHomePage();
        homePage.openPaymentsLink();
    }

    /*3*/
    @Test
    @Description("Check if communal payments is present and open it")
    public void verifyAndGoToCommunalPaymentsTest() {
        homePage.openHomePage();
        payments.openCommunalPayments();
    }

    /*4*/
    @Test
    @Description("Check is the current city is Moscow")
    public void verifyCurrentCityIsMoscowTest() {
        communalServicePayment.verifyTheCityIsMoscow();
    }

    /*5*/
    @Test
    @Description("Open Moscow communal services")
    public void goToMoscowCommunalServiceTest() {
        communalServicePayment.goToMoscowCommunalService();
    }

    /*6*/
    @Test
    @Description("Select payment of communal services in Moscow")
    public void selectPaymentInMoscowTest() {
        moscowCommunalServices.selectPayCommunalInMoscow();
    }

    /*7*/
    @Test
    @Description("Verify obligatorily payer code input")
    public void verifyObligatorilyPayerCodeInputTest() {
        moscowCommunalServices.verifyObligatorilyPayerCodeInput();
    }

    @Test
    @Description("Verify obligatorily period input")
    public void verifyObligatorilyPeriodInputTest() {
        moscowCommunalServices.verifyObligatorilyPeriodInput();
    }

    @Test
    @Description("Verify obligatorily sum input")
    public void verifyObligatorilySumInputTest() {
        moscowCommunalServices.verifyObligatorilySumInput();
    }

    @Test
    @Description("Verify invalid payer code")
    public void verifyInvalidPayerCodeTest() {
        moscowCommunalServices.verifyInvalidPayerCode();
    }

    @Test
    @Description("Verify invalid period")
    public void verifyInvalidPeriodTest() {
        moscowCommunalServices.verifyInvalidPeriod();
    }

    @Test
    @Description("Verify invalid sum")
    public void verifyInvalidSumTest() {
        moscowCommunalServices.verifyInvalidSum();
    }

    /*8-9-10*/
    @Test
    @Description("Search for Moscow Communal Service using QuickSearch")
    public void searchingOfMoscowServiceUsingQuickSearchTest() {
        homePage.openHomePage();
        homePage.openPaymentsLink();
        payments.searchForServiceUsingQuickSearch();
    }

    /*11*/
    @Test
    @Description("Verify the page is Moscow Communal ServiceA after searching")
    public void verifyThePageIsMoscowCommunalServiceTest() {
        homePage.openHomePage();
        homePage.openPaymentsLink();
        payments.verifyThePageIsMoscowCommunalServiceAfterSearching();
    }

    /*12-13-14*/
    @Test
    @Description("Change city to another and check absence of Moscow Service")
    public void changeRegionToAnotherTest() {
        communalServicePayment.changeCityToAnotherAndCheckAbsenceOfMoscowService();
    }


    @AfterMethod
    public void stopTest() {
        DriverFactory.getInstance().quitDriver();
    }
}
