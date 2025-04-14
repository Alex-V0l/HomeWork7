//package Chapter4Tests;
//
//import Configs.TestPropertiesConfig;
//import org.aeonbits.owner.ConfigFactory;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import java.time.Duration;
//import java.util.Map;
//
//import static Constants.Constants.BASE_URL;
//
//public class WebStorageTest {
//        WebDriver driver;
//    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
//    private static final String WEB_STORAGE_URL = BASE_URL + "web-storage.html";
//
//    @BeforeEach
//    void setUp() {
//        driver = new ChromeDriver();
//        driver.get(config.getBaseURl());
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//    }
//
//    @AfterEach
//    void tearDown() {
//        driver.quit();
//    }
//
//    @DisplayName("Проверка перехода на страницу Web storage")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void getWebStorageURL() {
//        WebElement WebStorageLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='web-storage.html']"));
//        WebStorageLink.click();
//        String actualURL = driver.getCurrentUrl();
//        Assertions.assertEquals(WEB_STORAGE_URL, actualURL, "Значения должны совпадать");
//
//        WebElement WebStorageTitleText = driver.findElement(By.className("display-6"));
//        Assertions.assertEquals("Web storage", WebStorageTitleText.getText(), "Значения должны совпадать");
//    }
//
//    @DisplayName("Проверка значений local storage")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void localStorageValueTest() {
//        driver.get(WEB_STORAGE_URL);
//
//        WebElement localStorageButton = driver.findElement(By.id("display-local"));
//        localStorageButton.click();
//        WebElement localStorageText = driver.findElement(By.id("local-storage"));
//        String actualLocalStorageData = localStorageText.getText().trim();
//        UtilWebStorageTest expectedLocalStorageCollection = new UtilWebStorageTest();
//        Map<String, String> localStorageCollection = expectedLocalStorageCollection.getLocalStorage(driver);
//        String expectedLocalStorageData = expectedLocalStorageCollection.getFormattedStorage(localStorageCollection);
//
//        Assertions.assertEquals(expectedLocalStorageData, actualLocalStorageData, "Значения должны совпадать");
//    }
//
//    @DisplayName("Проверка значений session storage")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void sessionStorageValueTest() {
//        driver.get(WEB_STORAGE_URL);
//
//        WebElement sessionStorageButton = driver.findElement(By.id("display-session"));
//        sessionStorageButton.click();
//        WebElement sessionStorageText = driver.findElement(By.id("session-storage"));
//        String actualSessionStorageData = sessionStorageText.getText().trim();
//        UtilWebStorageTest expectedSessionStorageCollection = new UtilWebStorageTest();
//        Map<String, String> sessionStorageCollection = expectedSessionStorageCollection.getSessionStorage(driver);
//        String expectedSessionStorageData = expectedSessionStorageCollection.getFormattedStorage(sessionStorageCollection);
//
//        Assertions.assertEquals(expectedSessionStorageData, actualSessionStorageData, "Значения должны совпадать");
//    }
//}
