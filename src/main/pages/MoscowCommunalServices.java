package pages;

import io.qameta.allure.Step;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import static utils.LoadProperties.COMMUNAL_PAYMENTS_MOSCOW;

public class MoscowCommunalServices extends BasePage {

    By payCommunalInMoscow = By.xpath("//span[text()='Оплатить ЖКУ в Москве']");
    private By payerCode = By.xpath("//input[@name='provider-payerCode']");
    private By period = By.name("provider-period");
    private By sumOfPayment = By.xpath("//*[@class='ui-form__row ui-form__row_combination']//label");
    private By sumOfPaymentInput = By.xpath("//*[@class='ui-form__row ui-form__row_combination']//input");

    private By errorMessageForPayerCode = By.xpath("//*[@data-qa-file='FormFieldWrapper' and descendant::input[@name='provider-payerCode']]//*[@data-qa-file='UIFormRowError']");
    private By errorMessageForPeriod = By.xpath("//*[@data-qa-file='FormFieldWrapper' and descendant::input[@name='provider-period']]//*[@data-qa-file='UIFormRowError']");
    private By errorMessageForSumOfPayment = By.xpath("//*[@data-qa-file='FormFieldWrapper' and descendant::*[contains(text(),'Сумма платежа,')]]//*[@data-qa-file='UIFormRowError']");


    @Step("Select payment of communal services in Moscow")
    public void selectPayCommunalInMoscow() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        waitUntilElementIsVisible(payerCode);
        Assert.assertEquals(getCurrentUrl(), "https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay");
    }

    @Step("Verify obligatorily payer code input")
    public void verifyObligatorilyPayerCodeInput() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(period, "01.2018");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "10");
        sendEnter(sumOfPaymentInput);
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле обязательное");
    }

    @Step("Verify obligatorily period input")
    public void verifyObligatorilyPeriodInput() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(payerCode, "1234567896");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "1505");
        sendEnter(sumOfPaymentInput);
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле обязательное");
    }

    @Step("Verify obligatorily sum input")
    public void verifyObligatorilySumInput() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(payerCode, "1234567895");
        sendEnter(payerCode);
        fillInputField(period, "03.2018");
        sendEnter(period);
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Поле обязательное");
    }

    @Step("Verify invalid payer code")
    public void verifyInvalidPayerCode() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(payerCode, "1550");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле неправильно заполнено");
        fillInputField(payerCode, "-5");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле неправильно заполнено");
        fillInputField(payerCode, RandomStringUtils.randomAlphabetic(10));
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле обязательное");
        fillInputField(payerCode, "%*&^@212121");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPayerCode), "Поле неправильно заполнено");
    }

    @Step("Verify invalid period")
    public void verifyInvalidPeriod() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        fillInputField(period, "00.0000");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле заполнено некорректно");
        fillInputField(period, "13.1998");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле заполнено некорректно");
        fillInputField(period, RandomStringUtils.randomAlphabetic(10));
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле обязательное");
        fillInputField(period, "11.0000");
        loseFocus();
        fillInputField(period, "12.199");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForPeriod), "Поле заполнено некорректно");

    }

    @Step("Verify invalid sum")
    public void verifyInvalidSum() {
        navigateTo(COMMUNAL_PAYMENTS_MOSCOW);
        waitForElementAndClick(payCommunalInMoscow);
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "0");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Минимум — 10 \u20BD");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "-5");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Поле заполнено неверно");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "9");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Минимум — 10 \u20BD");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "9.99");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Минимум — 10 \u20BD");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "14999");
        loseFocus();
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "15000.01");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Максимум — 15 000 \u20BD");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "15001");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Максимум — 15 000 \u20BD");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, "100000");
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Максимум — 15 000 \u20BD");
        waitForElementAndClick(sumOfPayment);
        fillInputField(sumOfPaymentInput, RandomStringUtils.randomAlphabetic(20));
        loseFocus();
        Assert.assertEquals(getText(errorMessageForSumOfPayment), "Поле обязательное");
    }

}
