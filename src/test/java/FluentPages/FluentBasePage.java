package FluentPages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

public class FluentBasePage {
    WebDriver driver;

    @FindBy(className = "display-6")
    private WebElement Subtitle;

    @FindBy(xpath = "//a[contains(@href, 'index')]")
    private WebElement returnToIndexLink;

    @FindBy(xpath = "//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']")
    private WebElement copyRightText;

    @FindBy(xpath = "//a[@href='https://bonigarcia.dev/']")
    private WebElement developersPageLink;

    public FluentBasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("getting current URL")
    public String getCurrentURL (){
        return driver.getCurrentUrl();
    }

    @Step ("get subtitle")
    public String getSubtitleText(){
        return Subtitle.getText();
    }

    @Step("return to homepage")
    public void returnToIndex(){
        returnToIndexLink.click();
    }

    @Step("get text of copyright part")
    public String getCopyrightText(){
        return copyRightText.getText();
    }

    @Step("click to link, that leads to bonigarcia")
    public void getDevelopersPageLink(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", developersPageLink);
    }

    public void clickButton(WebElement cookiesButton){
        cookiesButton.click();
    }

    public String[] getArrayOfWebElementsList(WebElement cookiesList){
        return cookiesList.getText().split("\n");
    }

    public String getFirstElementOfCookiesList(String[] cookiesList){
        return cookiesList[0];
    }

    public String getSecondElementOfCookiesList(String[] cookiesList){
        return cookiesList[1];
    }

    public String getValueOfCookieByItsName(String name) {
        WebDriver.Options options = driver.manage();
        String valuseAsString = options.getCookieNamed(name).toString();
        for (String part : valuseAsString.split(";")) {
            if (part.trim().startsWith("username=")) {
                return  "username=John Doe";
            } else if (part.trim().startsWith("date=")) {
                return "date=10/07/2018";
            }
        }throw new NoSuchElementException("Не удалось найти cookie с подходящим именем");
    }

    public Cookie createACookie(String name, String value){
        return new Cookie(name, value);
    }

    public void addNewCookie(Cookie newCookie){
        WebDriver.Options options = driver.manage();
        options.addCookie(newCookie);
    }

    public String getValueOfCookie(Cookie newCookie){
        WebDriver.Options options = driver.manage();
        return options.getCookieNamed(newCookie.getName()).getValue();
    }

    public String getNameOfCookie(Cookie newCookie){
        WebDriver.Options options = driver.manage();
        return options.getCookieNamed(newCookie.getName()).getName();
    }

    public int getAmountOfCookiesOnPage(){
        WebDriver.Options options = driver.manage();
        return options.getCookies().size();
    }

    public void deleteCookie(String cookieName){
        WebDriver.Options options = driver.manage();
        options.deleteCookieNamed(cookieName);
    }
}
