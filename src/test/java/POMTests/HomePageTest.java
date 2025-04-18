package POMTests;

import POMPages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Constants.Constants.BASE_URL;
import static FactoryPOMPages.NavigationPageFactory.NAVIGATION_URL;
import static POMPages.WebFormPage.WEB_FORM_URL;


public class HomePageTest extends BaseTest {

    @DisplayName("Проверка перехода на страницу и загаловка")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void homePageURLTest() {
        HomePage homePage = new HomePage(driver);
        String expectedTitle = "Hands-On Selenium WebDriver with Java";
        homePage.open();
        String actualURL = homePage.getCurrentURL();
        String actualTitle = homePage.getTitle();

        Assertions.assertEquals(BASE_URL, actualURL, "Значения должны совпадать");
        Assertions.assertEquals(expectedTitle, actualTitle, "Значения должны совпадать");
    }

    @DisplayName("Проверка количества отдельных страниц и перехода на каждую отдельную страницу")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void amountOfLinksInsideChaptersTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        int quantityOfLinks = 0;
        int expectedQuantityOfLinks = 27;
        int expectedQuantityOfChapters = 6;
        List<WebElement> chapters = homePage.findChapters();
        int actualQuantityOfChapters = chapters.size();
        int actualQuantityOfLinks = homePage.getQuantityOfLinks(quantityOfLinks, chapters);

        Assertions.assertEquals(expectedQuantityOfChapters, actualQuantityOfChapters, "Значения должны совпадать");
        Assertions.assertEquals(expectedQuantityOfLinks, actualQuantityOfLinks, "Значения должны совпадать");
    }

    @DisplayName("Проверка перехода на web form и его подзаголовка")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void webFormURLAndSubTitleTest() {
        String expectedSubtitle = "Web form";

        HomePage homePage = new HomePage(driver);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebFormPage();
        String actualURL = webFormPage.getCurrentURL();
        String actualSubtitle = webFormPage.getSubtitleText();

        Assertions.assertEquals(expectedSubtitle, actualSubtitle, "Значения должны совпадать");
        Assertions.assertEquals(WEB_FORM_URL, actualURL, "Значения должны совпадать");
    }

    @DisplayName("Проверка перехода на navigation и его подзаголовка")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void navigationURLAndSubtitleTest() {
        String expectedSubtitle = "Navigation example";

        HomePage homePage = new HomePage(driver);
        homePage.open();
        NavigationPage navigationPage = homePage.openNavigationPage();

        String actualURL = navigationPage.getCurrentURL();
        String actualSubtitle = navigationPage.getSubtitleText();

        Assertions.assertEquals(expectedSubtitle, actualSubtitle, "Значения должны совпадать");
        Assertions.assertEquals(NAVIGATION_URL, actualURL, "Значения должны совпадать");
    }
}
