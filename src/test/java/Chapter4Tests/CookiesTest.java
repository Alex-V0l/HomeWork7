package Chapter4Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class CookiesTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/cookies.html";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @DisplayName("Проверка значений Куки")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getCookies() {

        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        int cookiesAmount = cookies.size();
        Assertions.assertEquals(2, cookiesAmount, "Значения должны совпадать");

        Cookie username = options.getCookieNamed("username");

        String userNameAsString = username.toString();

        for (String part : userNameAsString.split(";")) {
            if (part.trim().startsWith("username=")) {
                userNameAsString = "username=John Doe";
                break;
            }
        }

        Cookie date = driver.manage().getCookieNamed("date");

        String dateAsString = date.toString();

        for (String part : dateAsString.split(";")) {
            if (part.trim().startsWith("date=")) {
                dateAsString = "date=10/07/2018";
                break;
            }
        }

            WebElement CookiesButton = driver.findElement(By.id("refresh-cookies"));
            CookiesButton.click();
            WebElement cookiesList = driver.findElement(By.id("cookies-list"));
            String[] lines = cookiesList.getText().split("\n");
            String actualUserNameCookie = lines[0];
            String actualDateCookie = lines[1];

            Assertions.assertEquals(userNameAsString, actualUserNameCookie, "Значения должны совпадать");
            Assertions.assertEquals(dateAsString, actualDateCookie, "Значения должны совпадать");
        }

    @DisplayName("Проверка изменения Куков")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void changeCookies() {
        WebDriver.Options options = driver.manage();

        Set<Cookie> cookies = options.getCookies();
        int initCookiesAmount = cookies.size();
        Assertions.assertEquals(2, initCookiesAmount, "Значения должны совпадать");

        Cookie newCookie = new Cookie("age", "21");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName())
                .getValue();
        Assertions.assertEquals(newCookie.getValue(), readValue, "Значения должны совпадать");

        WebElement CookiesButton = driver.findElement(By.id("refresh-cookies"));
            CookiesButton.click();

            int newCookiesSize = options.getCookies().size();

            Assertions.assertTrue(initCookiesAmount<newCookiesSize, "Появился новый Куки");

            options.deleteCookieNamed("age");
            CookiesButton.click();
            int anotherCoockiesSize = options.getCookies().size();
            Assertions.assertEquals(initCookiesAmount, anotherCoockiesSize, "Значения должны совпадать");
    }
}





