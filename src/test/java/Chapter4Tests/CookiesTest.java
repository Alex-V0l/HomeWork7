package Chapter4Tests;

import Configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

import static Constants.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookiesTest {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    private static final String COOKIES_URL = BASE_URL + "cookies.html";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(config.getBaseURl());
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @DisplayName("Проверка перехода на страницу Cookies")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getCookiesURL(){
        WebElement CookiesLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='cookies.html']"));
        CookiesLink.click();
        String actualURL = driver.getCurrentUrl();
        WebElement CookiesText = driver.findElement(By.className("display-6"));

        assertEquals(COOKIES_URL, actualURL, "Значения должны совпадать");
        assertEquals("Cookies", CookiesText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка значений Куки")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getCookiesTest() {
        driver.get(COOKIES_URL);
        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        int cookiesAmount = cookies.size();

        Assertions.assertEquals(2, cookiesAmount, "Начальное количество Cookies должно быть равным 2");

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
    void changeCookiesTest() {
        driver.get(COOKIES_URL);
        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        int initCookiesAmount = cookies.size();

        Assertions.assertEquals(2, initCookiesAmount, "Начальное количество Cookies должно быть равным 2");

        Cookie newCookie = new Cookie("age", "21");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName())
                .getValue();

        Assertions.assertEquals(newCookie.getValue(), readValue, "Должен был появиться новый cookies со своими значениями");

        WebElement CookiesButton = driver.findElement(By.id("refresh-cookies"));
            CookiesButton.click();
            int newCookiesSize = options.getCookies().size();

            Assertions.assertTrue(initCookiesAmount<newCookiesSize, "Количество cookies должно было увеличиться");

            options.deleteCookieNamed("age");
            CookiesButton.click();
            int anotherCoockiesSize = options.getCookies().size();
            Assertions.assertEquals(initCookiesAmount, anotherCoockiesSize, "После удаления, количество cookies должно быть равным изначальному");
    }
}





