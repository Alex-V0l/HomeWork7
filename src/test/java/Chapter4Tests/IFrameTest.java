package Chapter4Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class IFrameTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/iframes.html";

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

    @DisplayName("Проверка перехода на страницу с тестом и проверка скролла")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getIFrameURLAndScroll() {

        String actualURL = driver.getCurrentUrl();
        assertEquals(BASE_URL, actualURL, "Значения должны совпадать");

        WebElement InfiniteScrollText = driver.findElement(By.className("display-6"));
        assertEquals("IFrame", InfiniteScrollText.getText(), "Значения должны совпадать");

        WebElement iframe = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframe);

        WebElement MagnisText = driver.findElement(By.xpath("//p[@class='lead' and contains(text(), 'Magnis')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Boolean isMagnisTextInitVisible = (Boolean) js.executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", MagnisText
        );

        Assertions.assertFalse(isMagnisTextInitVisible, "Элемент, содержащий текст Magnis, изначально должен быть вне зоны видимости");

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> (Boolean) js.executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", MagnisText));

                Boolean isMagnisTextVisible = (Boolean) js.executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", MagnisText
        );

        Assertions.assertTrue(isMagnisTextVisible, "Элемент, содержащий текст Magnis, должен быть видим после скролла");
    }
}
