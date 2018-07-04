import driverFactory.DriverFactory;
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
    public void openHomePageTest() {
        homePage.openHomePage();
        Assert.assertTrue(elementIsDisplayed(homePage.payments));
    }

    /*2*/
    @Test
    public void openPaymentsPageTest() {
        homePage.openHomePage();
        homePage.openPaymentsLink();
    }

    /*3*/
    @Test
    public void verifyAndGoToCommunalPaymentsTest() {
        homePage.openHomePage();
        payments.openCommunalPayments();
    }

    /*4*/
    @Test
    public void verifyCurrentCityIsMoscowTest() {
        communalServicePayment.verifyTheCityIsMoscow();
    }

    /*5*/
    @Test
    public void goToMoscowCommunalServiceTest() {
        communalServicePayment.goToMoscowCommunalService();
    }

    /*6*/
    @Test
    public void selectPaymentInMoscowTest() {
        moscowCommunalServices.selectPayCommunalInMoscow();
    }

    /*7*/
    @Test
    public void testsForCheckingFieldsTest() {
        moscowCommunalServices.verifyObligatorilyPayerCodeInput();
    }

    @Test
    public void verifyObligatorilyPeriodInputTest() {
        moscowCommunalServices.verifyObligatorilyPeriodInput();
    }

    @Test
    public void verifyObligatorilySumInputTest() {
        moscowCommunalServices.verifyObligatorilySumInput();
    }

    @Test
    public void verifyInvalidPayerCodeTest() {
        moscowCommunalServices.verifyInvalidPayerCode();
    }

    @Test
    public void verifyInvalidPeriodTest() {
        moscowCommunalServices.verifyInvalidPeriod();
    }

    @Test
    public void verifyInvalidSumTest() {
        moscowCommunalServices.verifyInvalidSum();
    }

    /*8-9-10*/
    @Test
    public void searchingOfMoscowServiceUsingQuickSearchTest() {
        homePage.openHomePage();
        homePage.openPaymentsLink();
        payments.searchForServiceUsingQuickSearch();
    }

    /*11*/
    @Test
    public void verifyThePageIsMoscowCommunalServiceTest() {
        homePage.openHomePage();
        homePage.openPaymentsLink();
        payments.verifyThePageIsMoscowCommunalServiceAfterSearching();
    }

    /*12-13-14*/
    @Test
    public void changeRegionToAnotherTest() {
        communalServicePayment.changeCityToAnotherAndCheckAbsenceOfMoscowService();
    }


    @AfterMethod
    public void stopTest() {
        DriverFactory.getInstance().quitDriver();
    }
}
