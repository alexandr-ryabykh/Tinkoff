import driverFactory.DriverFactory;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CommunalServicePayment;
import pages.HomePage;
import pages.MoscowCommunalServices;
import pages.Payments;

public class Tests {

    /*1*/
    @Test
    @Description("Open home page")
    public void openHomePageTest() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
    }

    /*2*/
    @Test
    @Description("Open payments page")
    public void openPaymentsPageTest() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.openPaymentsLink();
    }

    /*3*/
    @Test
    @Description("Check if communal payments is present and open it")
    public void verifyAndGoToCommunalPaymentsTest() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        Payments payments = new Payments();
        payments.openCommunalPayments();
    }

    /*4*/
    @Test
    @Description("Check is the current city is Moscow")
    public void verifyCurrentCityIsMoscowTest() {
        CommunalServicePayment communalServicePayment = new CommunalServicePayment();
        communalServicePayment.verifyTheCityIsMoscow();
    }

    /*5*/
    @Test
    @Description("Open Moscow communal services")
    public void goToMoscowCommunalServiceTest() {
        CommunalServicePayment communalServicePayment = new CommunalServicePayment();
        communalServicePayment.goToMoscowCommunalService();
    }

    /*6*/
    @Test
    @Description("Select payment of communal services in Moscow")
    public void selectPaymentInMoscowTest() {
        MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();
        moscowCommunalServices.selectPayCommunalInMoscow();
    }

    /*7*/
    @Test
    @Description("Verify obligatorily payer code input")
    public void verifyObligatorilyPayerCodeInputTest() {
        MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();
        moscowCommunalServices.verifyObligatorilyPayerCodeInput();
    }

    @Test
    @Description("Verify obligatorily period input")
    public void verifyObligatorilyPeriodInputTest() {
        MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();
        moscowCommunalServices.verifyObligatorilyPeriodInput();
    }

    @Test
    @Description("Verify obligatorily sum input")
    public void verifyObligatorilySumInputTest() {
        MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();
        moscowCommunalServices.verifyObligatorilySumInput();
    }

    @Test
    @Description("Verify invalid payer code")
    public void verifyInvalidPayerCodeTest() {
        MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();
        moscowCommunalServices.verifyInvalidPayerCode();
    }

    @Test
    @Description("Verify invalid period")
    public void verifyInvalidPeriodTest() {
        MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();
        moscowCommunalServices.verifyInvalidPeriod();
    }

    @Test
    @Description("Verify invalid sum")
    public void verifyInvalidSumTest() {
        MoscowCommunalServices moscowCommunalServices = new MoscowCommunalServices();
        moscowCommunalServices.verifyInvalidSum();
    }

    /*8-9-10*/
    @Test
    @Description("Search for Moscow Communal Service using QuickSearch")
    public void searchingOfMoscowServiceUsingQuickSearchTest() {
        HomePage homePage = new HomePage();
        Payments payments = new Payments();
        homePage.openHomePage();
        homePage.openPaymentsLink();
        payments.searchForServiceUsingQuickSearch();
    }

    /*11*/
    @Test
    @Description("Verify the page is Moscow Communal Service After searching")
    public void verifyThePageIsMoscowCommunalServiceTest() {
        HomePage homePage = new HomePage();
        Payments payments = new Payments();
        homePage.openHomePage();
        homePage.openPaymentsLink();
        payments.verifyThePageIsMoscowCommunalServiceAfterSearching();
    }

    /*12-13-14*/
    @Test
    @Description("Change city to another and check absence of Moscow Service")
    public void changeRegionToAnotherTest() {
        CommunalServicePayment communalServicePayment = new CommunalServicePayment();
        communalServicePayment.changeCityToAnotherAndCheckAbsenceOfMoscowService();
    }


    @AfterMethod
    public void stopTest() {
        DriverFactory.getInstance().destroy();
    }
}
