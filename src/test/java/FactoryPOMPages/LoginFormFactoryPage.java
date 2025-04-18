package FactoryPOMPages;

import Configs.TestPropertiesConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Constants.Constants.BASE_URL;

public class LoginFormFactoryPage extends BaseFactoryPage {

    TestPropertiesConfig config;
    public static final String LOGIN_FORM_URL = BASE_URL + "login-form.html";

    @FindBy(id = "username")
    private WebElement loginForm;

    @FindBy(id = "password")
    private WebElement passwordForm;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement submitButton;

    @FindBy(className = "alert")
    private WebElement message;


    public LoginFormFactoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("open navigation page")
    public void open() {
        driver.get(LOGIN_FORM_URL);
    }

    @Step("send text to loginForm")
    public void sendTextToLoginForm(String userNameForLoginForm){
        loginForm.sendKeys(userNameForLoginForm);
    }

    @Step("send text to passwordForm")
    public void sendTextToPasswordForm(String passwordForLoginForm){
        passwordForm.sendKeys(passwordForLoginForm);
    }

    @Step("click submit button")
    public void clickSubmitButton(){
        submitButton.click();
    }

    @Step("get message")
    public String getMessage(){
        return message.getText();
    }
}
