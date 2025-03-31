package Chapter3Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.List;

public class LoadingImagesTest {
        WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";

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

    @DisplayName("Проверка количества загружаемых картинок")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void loadingImagesAmountTest() {
        List<WebElement> imagesAtTheBeginning = driver.findElements(By.xpath("//div[@id='image-container']/img"));
        int initAmountOfImages = imagesAtTheBeginning.size();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='image-container']/img"), 4));

        List<WebElement> imagesInTheEnd = driver.findElements(By.xpath("//div[@id='image-container']/img"));
        int actualAmountOfImages = imagesInTheEnd.size();

        Assertions.assertTrue(actualAmountOfImages > initAmountOfImages);
    }

    @DisplayName("Проверка соответствия загруженных картинок")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @ParameterizedTest
    @CsvSource({
            "compass, 0",
            "calendar, 1",
            "award, 2",
            "landscape, 3"
    })
    void loadingImagesTest(String imageId, int expectedIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='image-container']/img"), 4));
        List<WebElement> images = driver.findElements(By.xpath("//div[@id='image-container']/img"));

        WebElement expectedImage = driver.findElement(By.id(imageId));
        String expectedAlt = expectedImage.getDomAttribute("alt");

        String actualAlt = images.get(expectedIndex).getDomAttribute("alt");
        Assertions.assertEquals(expectedAlt, actualAlt, "Значения должны совпадать");
    }
}
