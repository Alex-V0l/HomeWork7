package FluentPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Constants.Constants.BASE_URL;

public class FluentCookiesNewPage extends FluentBasePage{

    public static final String COOKIES_URL = BASE_URL + "cookies.html";

    @FindBy(id = "refresh-cookies")
    private WebElement cookiesButton;

    public FluentCookiesNewPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
    }

    @Step("open cookies page")
    public void open() {
        driver.get(COOKIES_URL);
    }

    @Step("get amount of cookies")
    public FluentCookiesNewPage getAmountOfCookies(){
        getAmountOfCookiesOnPage();
        return this;
    }

    @Step("check initial amount of cookies")
    public FluentCookiesNewPage checkEqualityAmountOfCookies(int expectedAmountOfCookies){
        Assertions.assertEquals(expectedAmountOfCookies, getAmountOfCookiesOnPage(),
                "Количество Cookies должно быть равным 2");
        return this;
    }


    @Step("click cookies button")
    public FluentCookiesNewPage clickCookiesButton(){
        clickButton(cookiesButton);
        return this;
    }

    @Step("create new cookie")
    public FluentCookiesNewPage createNewCookie(String nameAndValue){
        addNewCookie(nameAndValue);
        return this;
    }

    @Step("check that amount of cookies has changed")
    public FluentCookiesNewPage checkCookiesAmountIncreased(int initAmount){
        Assertions.assertTrue(initAmount<getAmountOfCookiesOnPage(),
                "Общее количество cookies должно было увеличиться после добавления нового cookie");
        return this;
    }

    @Step("delete created cookie")
    public FluentCookiesNewPage deleteCreatedCookie(String nameAndValue){
        deleteCookie(nameAndValue);
        return this;
    }

    @Step("check cookies expected name and value with what you can see")
    public FluentCookiesNewPage checkCookiesNameAndValue(String CookieNameAndValue, int numberOfCookie){
        Assertions.assertEquals(CookieNameAndValue, getCookieFromCookieList(numberOfCookie),
                "Значение и имя cookies должны были совпасть");
        return this;
    }
}
