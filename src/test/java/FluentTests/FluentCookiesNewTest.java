package FluentTests;

import FluentPages.FluentCookiesNewPage;
import org.junit.jupiter.api.*;


public class FluentCookiesNewTest extends FluentBaseTest {

    private FluentCookiesNewPage fluentCookiesNewPage;

    @BeforeEach
    void setUpNavigationPage() {
        fluentCookiesNewPage = new FluentCookiesNewPage(driver);
        fluentCookiesNewPage.open();
    }

    @DisplayName("Проверка значений Cookies")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getCookiesTest() {
        int expectedAmountOfCookies = 2;
        String firstCookieNameAndValue = "username=John Doe";
        String secondCookieNameAndValue = "date=10/07/2018";
        int numberOfFirstCookie = 0;
        int numberOfSecondCookie = 1;

        fluentCookiesNewPage
                .getAmountOfCookies()
                .checkEqualityAmountOfCookies(expectedAmountOfCookies)
                .clickCookiesButton()
                .checkCookiesNameAndValue(firstCookieNameAndValue, numberOfFirstCookie)
                .checkCookiesNameAndValue(secondCookieNameAndValue, numberOfSecondCookie);
    }

    @DisplayName("Проверка изменения Cookies")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void changeCookiesTest() {
        int initCookiesAmount = 2;
        String newCookieNameAndValue = "age=21";
        int newCookiesNumber = 2;

        fluentCookiesNewPage
                .getAmountOfCookies()
                .checkEqualityAmountOfCookies(initCookiesAmount)
                .createNewCookie(newCookieNameAndValue)
                .clickCookiesButton()
                .checkCookiesAmountIncreased(initCookiesAmount)
                .checkCookiesNameAndValue(newCookieNameAndValue, newCookiesNumber)
                .deleteCreatedCookie(newCookieNameAndValue)
                .checkEqualityAmountOfCookies(initCookiesAmount);
    }
}
