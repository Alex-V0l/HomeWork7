package FluentPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Constants.Constants.BASE_URL;

public class FluentCookiesPageExcessive extends FluentBasePage{

    public static final String COOKIES_URL = BASE_URL + "cookies.html";

    @FindBy(id = "refresh-cookies")
    private WebElement cookiesButton;

    @FindBy(id = "cookies-list")
    private WebElement cookiesList;

    public FluentCookiesPageExcessive(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
    }

    @Step("open cookies page")
    public void open() {
        driver.get(COOKIES_URL);
    }

    @Step("get amount of cookies")
    public FluentCookiesPageExcessive getAmountOfCookies(){
        getAmountOfCookiesOnPage();
        return this;
    }

    @Step("check initial amount of cookies")
    public FluentCookiesPageExcessive checkEqualityAmountOfCookies(int expectedAmountOfCookies){
        Assertions.assertEquals(expectedAmountOfCookies, getAmountOfCookiesOnPage(),
                "Начальное количество Cookies должно быть равным 2");
        return this;
    }

    @Step("get value of cookie by name")
    public FluentCookiesPageExcessive getValueOfCookieByName(String name){
        getValueOfCookieByItsName(name);
        return this;
    }

    @Step("check values of first cookies")
    public FluentCookiesPageExcessive checkCookiesFirstName(String name){
        Assertions.assertEquals(getValueOfCookieByItsName(name),
                getFirstElementOfCookiesList(getArrayOfWebElementsList(cookiesList))
                , "Значения должны совпадать");
        return this;
    }

    @Step("check values of second cookies")
    public FluentCookiesPageExcessive checkCookiesSecondName(String name){
        Assertions.assertEquals(getValueOfCookieByItsName(name),
                getSecondElementOfCookiesList(getArrayOfWebElementsList(cookiesList))
                , "Значения должны совпадать");
        return this;
    }

    @Step("click cookies button")
    public FluentCookiesPageExcessive clickCookiesButton(){
        clickButton(cookiesButton);
        return this;
    }

    @Step("get Array of Cookies")
    public FluentCookiesPageExcessive getArrayOfCookies(){
        getArrayOfWebElementsList(cookiesList);
        return this;
    }

    @Step("get value of first cookie")
    public FluentCookiesPageExcessive getFirstCookieValue(){
        getFirstElementOfCookiesList(getArrayOfWebElementsList(cookiesList));
        return this;
    }

    @Step("get value of second cookie")
    public FluentCookiesPageExcessive getSecondCookieValue(){
        getSecondElementOfCookiesList(getArrayOfWebElementsList(cookiesList));
        return this;
    }

    @Step("create new cookie")
    public FluentCookiesPageExcessive createNewCookie(String name, String value){
        addNewCookie(createACookie(name, value));
        return this;
    }

    @Step("get name of created cookie")
    public FluentCookiesPageExcessive getNameOfCreatedCookie(String name, String value){
        getNameOfCookie(createACookie(name, value));
        return this;
    }

    @Step("get value of created cookie")
    public FluentCookiesPageExcessive getValueOfCreatedCookie(String name, String value){
        getValueOfCookie(createACookie(name, value));
        return this;
    }

    @Step("check cookies names")
    public FluentCookiesPageExcessive checkCookiesNames(String name, String value){
        Assertions.assertEquals(name, getNameOfCookie(createACookie(name, value)),
                "Должен был добавиться новый cookie со своим именем");
        return this;
    }

    @Step("check cookies values")
    public FluentCookiesPageExcessive checkCookiesValues(String name, String value){
        Assertions.assertEquals(value, getValueOfCookie(createACookie(name, value)),
                "Должен был добавиться новый cookie со своим значением");
        return this;
    }

    @Step("check that amount of cookies has changed")
    public FluentCookiesPageExcessive checkCookiesAmountIncreased(int initAmount){
        Assertions.assertTrue(initAmount<getAmountOfCookiesOnPage(),
                "Общее количество cookies должно было увеличиться после добавления нового cookie");
        return this;
    }

    @Step("delete created cookie")
    public FluentCookiesPageExcessive deleteCreatedCookie(String cookieName){
        deleteCookie(cookieName);
        return this;
    }
}
