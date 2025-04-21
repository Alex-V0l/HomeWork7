package FluentTests;

import FluentPages.FluentCookiesPageExcessive;
import org.junit.jupiter.api.*;


public class FluentCookiesTestExcessive extends FluentBaseTest {

    private FluentCookiesPageExcessive fluentCookiesPageExcessive;

    @BeforeEach
    void setUpNavigationPage() {
        fluentCookiesPageExcessive = new FluentCookiesPageExcessive(driver);
        fluentCookiesPageExcessive.open();
    }

    @DisplayName("Проверка значений Cookies")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getCookiesTest() {
        int expectedAmountOfCookies = 2;
        String firstCookieName = "username";
        String secondCookieName = "date";

        fluentCookiesPageExcessive
                .getAmountOfCookies()
                .checkEqualityAmountOfCookies(expectedAmountOfCookies)
                .getValueOfCookieByName(firstCookieName)
                .getValueOfCookieByName(secondCookieName)
                .clickCookiesButton()
                .getArrayOfCookies()
                .getFirstCookieValue()
                .getSecondCookieValue()
                .checkCookiesFirstName(firstCookieName)
                .checkCookiesSecondName(secondCookieName);
    }

    @DisplayName("Проверка изменения Cookies")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void changeCookiesTest() {
        int initCookiesAmount = 2;
        String newCookieName = "age";
        String newCookieValue = "21";

        fluentCookiesPageExcessive
                .getAmountOfCookies()
                .checkEqualityAmountOfCookies(initCookiesAmount)
                .createNewCookie(newCookieName, newCookieValue)
                .getNameOfCreatedCookie(newCookieName, newCookieValue)
                .getValueOfCreatedCookie(newCookieName, newCookieValue)
                .checkCookiesNames(newCookieName, newCookieValue)
                .checkCookiesValues(newCookieName, newCookieValue)
                .clickCookiesButton()
                .getAmountOfCookies()
                .checkCookiesAmountIncreased(initCookiesAmount)
                .deleteCreatedCookie(newCookieName)
                .getAmountOfCookies()
                .checkEqualityAmountOfCookies(initCookiesAmount);
    }
}
