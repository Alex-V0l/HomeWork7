package Chapter4Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfiniteScrollTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html";


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
    void getInfiniteScrollURL() {

        String actualURL = driver.getCurrentUrl();
        assertEquals(BASE_URL, actualURL, "Значения должны совпадать");

        WebElement InfiniteScrollText = driver.findElement(By.className("display-6"));
        assertEquals("Infinite scroll", InfiniteScrollText.getText(), "Значения должны совпадать");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> LoremElements = driver.findElements(By.xpath("//b[text()='Lorem ipsum']"));
        int initialCount = LoremElements.size();
        assertTrue(initialCount > 0, "Хотя бы один элемент с текстом 'Lorem ipsum' должен быть изначально");

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//b[text()='Lorem ipsum']"), initialCount));

        List<WebElement> updatedLoremElements = driver.findElements(By.xpath("//b[text()='Lorem ipsum']"));
        int updatedCount = updatedLoremElements.size();

        assertTrue(updatedCount > initialCount, "После скролла появился новый элемент, содержащий текст 'Lorem ipusm'");
    }

    @DisplayName("Проверка бесконечного скролла и прекращения появления новых элементов")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void scrollInfiniteScroll() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> LoremElements = driver.findElements(By.xpath("//b[text()='Lorem ipsum']"));
        int initialCount = LoremElements.size();
        assertTrue(initialCount > 0, "Хотя бы один элемент с текстом 'Lorem ipsum' должен быть изначально");
        int amountOfScrolls = 5;

        for (int i = 0; i < amountOfScrolls; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.xpath("//b[text()='Lorem ipsum']"), initialCount));

            List<WebElement> updatedLoremElements = driver.findElements(By.xpath("//b[text()='Lorem ipsum']"));
            int updatedCount = updatedLoremElements.size();

            assertTrue(updatedCount > initialCount, "После скролла должно было появитьсь больше новых элемента, содержащих текст 'Lorem ipusm'");
        }
    }
}



