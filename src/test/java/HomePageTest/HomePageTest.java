package HomePageTest;

import POM.BaseTest;
import POM.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Constants.Constants.BASE_URL;

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
}
