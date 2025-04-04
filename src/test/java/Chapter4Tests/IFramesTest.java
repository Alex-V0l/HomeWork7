package Chapter4Tests;

import Configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static Constants.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IFramesTest {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    private static final String IFRAMES_URL = BASE_URL + "iframes.html";

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

    @DisplayName("Проверка перехода на страницу IFrames")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getIFramesURL() {
        WebElement IFramesButton = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='iframes.html']"));
        IFramesButton.click();
        String actualURL = driver.getCurrentUrl();
        WebElement InfiniteScrollText = driver.findElement(By.className("display-6"));

        assertEquals(IFRAMES_URL, actualURL, "Значения должны совпадать");
        assertEquals("IFrame", InfiniteScrollText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка скролла внутри IFrame")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void scrollInsideIFrameTest()    {
        driver.get(IFRAMES_URL);
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
