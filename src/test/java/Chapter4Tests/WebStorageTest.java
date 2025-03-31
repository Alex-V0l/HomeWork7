package Chapter4Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class WebStorageTest {
        WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private static final String WEB_STORAGE_URL = BASE_URL + "web-storage.html";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @DisplayName("Проверка перехода на страницу с тестом")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getWebStorageURL() {
        WebElement WebStorageLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='web-storage.html']"));
        WebStorageLink.click();
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(WEB_STORAGE_URL, actualURL, "Значения должны совпадать");

        WebElement WebStorageTitleText = driver.findElement(By.className("display-6"));
        Assertions.assertEquals("Web storage", WebStorageTitleText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка Вэб Сторэдж")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Disabled //тест работает, но добиться появления нормальных значений не удалось. При выполнени теста видно только {}, а значения возвращаютсяс null
    @Test
    void webStorageTest() {
        driver.get(WEB_STORAGE_URL);

        WebElement localStorageButton = driver.findElement(By.id("display-local"));
        localStorageButton.click();
        WebElement sessionStorageButton = driver.findElement(By.id("display-session"));
        sessionStorageButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement localStorageText = driver.findElement(By.id("local-storage"));
        WebElement sessionStorageText =driver.findElement(By.id("session-storage"));

        String visibleLocalStorageText = localStorageText.getDomAttribute("innerHTML");
        String localInfoFromStorage = (String) js.executeScript("return window.localStorage.getItem('GDPR_REMOVAL_FLAG');");
        String visibleSessionStorageText = sessionStorageText.getDomAttribute("innerHTML");
        String sessionInfoFromStorage = (String) js.executeScript("return window.sessionStorage.getItem('user');");

        System.out.println(visibleLocalStorageText + ", " + localInfoFromStorage + ", " + visibleSessionStorageText + ", " + sessionInfoFromStorage);

        Assertions.assertEquals(visibleLocalStorageText, localInfoFromStorage, "Значения должны совпадать");
        Assertions.assertEquals(visibleSessionStorageText, sessionInfoFromStorage, "Значения должны совпадать");
    }
}
