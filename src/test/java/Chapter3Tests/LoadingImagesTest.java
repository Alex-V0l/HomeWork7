package Chapter3Tests;

import Configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
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

import static Constants.Constants.BASE_URL;

public class LoadingImagesTest {
        WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    private static final String LOADING_IMAGES_URL = BASE_URL + "loading-images.html";

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

    @DisplayName("Проверка перехода на страницу loading images")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getLoadingImagesURL(){
        WebElement loadingImagesLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='loading-images.html']"));
        loadingImagesLink.click();
        String actualURL = driver.getCurrentUrl();
        WebElement loadingImagesText = driver.findElement(By.className("display-6"));

        Assertions.assertEquals("Loading images", loadingImagesText.getText(), "Значения должны совпадать");
        Assertions.assertEquals(LOADING_IMAGES_URL, actualURL, "Значения должны совпадать");
    }

    @DisplayName("Проверка количества загружаемых картинок")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void loadingImagesAmountTest() {
        driver.get(LOADING_IMAGES_URL);
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
    void loadingImagesValueTest(String imageId, int expectedIndex) {
        driver.get(LOADING_IMAGES_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='image-container']/img"), 4));
        List<WebElement> images = driver.findElements(By.xpath("//div[@id='image-container']/img"));
        WebElement expectedImage = driver.findElement(By.id(imageId));
        String expectedAlt = expectedImage.getDomAttribute("alt");
        String actualAlt = images.get(expectedIndex).getDomAttribute("alt");

        Assertions.assertEquals(expectedAlt, actualAlt, "Значения должны совпадать");
    }
}
