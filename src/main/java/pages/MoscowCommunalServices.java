package pages;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static utils.LoadProperties.COMMUNAL_PAYMENTS_MOSCOW;

public class MoscowCommunalServices extends BasePage {


    By payCommunalInMoscow = By.xpath("//span[text()='Оплатить ЖКУ в Москве']");
    private By payerCode = By.name("provider-payerCode");
    private By period = By.name("provider-period");
    By voluntureHomeInsurance = By.xpath("(//*[@class='Input__valueContent_1Os4v Input__valueContent_primary_3sxF0'])[1]");
    private By sumOfPayment = By.xpath("(//*[@class='Input__wrapper_1A9vy'])[2]//child::div/input");

    private By errorMessageForPayerCode = By.xpath("(//*[@class='ui-form-field-error-message ui-form-field-error-message_ui-form'])[1]");
    private By errorMessageForPeriod = By.xpath("(//*[@class='ui-form-field-error-message ui-form-field-error-message_ui-form'])[2]");
    private By errorMessageForSumOfPayment = By.xpath("(//*[@class='ui-form-field-error-message ui-form-field-error-message_ui-form'])[3]");


    public void selectPayCommunalInMoscow() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        Assert.assertEquals(getCurrentUrl(), "https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay");
    }

    //Проверка обязательности заполнения поля "Код плательщика за ЖКУ в Москве"
    public void verifyObligatorilyPayerCodeInput() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(period, "01.2018");

        WebElement wb = driver.findElement(sumOfPayment);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='5151';", wb);


        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPayment, "10");
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле обязательное");
    }

    //Проверка обязательности заполнения поля период оплаты
    public void verifyObligatorilyPeriodInput() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(payerCode, RandomStringUtils.random(10));
        fillInputField(sumOfPayment, "1505");
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле обязательное");
    }

    //Проверка обязательности заполнения поля сумма платежа
    public void verifyObligatorilySumInput() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(payerCode, RandomStringUtils.random(10));
        fillInputField(period, "03.2018");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Поле обязательное");
    }


    //Проверка на невалидные значения код плательщика
    public void verifyInvalidPayerCode() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(payerCode, "1550");
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле неправильно заполнено");
        refreshUrl();
        fillInputField(payerCode, "-5");
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле неправильно заполнено");
        refreshUrl();
        fillInputField(payerCode, RandomStringUtils.randomAlphabetic(10));
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле обязательное");
        refreshUrl();
        fillInputField(payerCode, "%*&^@212121");
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле неправильно заполнено");
    }

    //Проверка на невалидный период
    public void verifyInvalidPeriod() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(period, "00.0000");
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле заполнено некорректно");
        refreshUrl();
        fillInputField(period, "13.1998");
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле заполнено некорректно");
        refreshUrl();
        fillInputField(period, RandomStringUtils.randomAlphabetic(10));
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле обязательное");
        refreshUrl();
        fillInputField(period, "11.0000");
        Assert.assertTrue(!elementIsDisplayed(errorMessageForPeriod));
        refreshUrl();
        fillInputField(period, "12.199");
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле заполнено некорректно");

    }

    //Проверка на невалидную сумму
    public void verifyInvalidSum() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(sumOfPayment, "0");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Минимум — 10 \u20BD");
        refreshUrl();
        fillInputField(sumOfPayment, "-5");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Поле заполнено неверно");
        refreshUrl();
        fillInputField(sumOfPayment, "9");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Минимум — 10 \u20BD");
        refreshUrl();
        fillInputField(sumOfPayment, "9.99");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Минимум — 10 \u20BD");
        refreshUrl();
        fillInputField(sumOfPayment, "14999");
        Assert.assertTrue(!elementIsDisplayed(errorMessageForSumOfPayment));
        fillInputField(sumOfPayment, "15000.01");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Максимум — 15 000 \u20BD");
        refreshUrl();
        fillInputField(sumOfPayment, "15001");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Максимум — 15 000 \u20BD");
        refreshUrl();
        fillInputField(sumOfPayment, "100000");
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Максимум — 15 000 \u20BD");
        refreshUrl();
        fillInputField(sumOfPayment, RandomStringUtils.randomAlphabetic(20));
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Поле обязательное");
    }


}
