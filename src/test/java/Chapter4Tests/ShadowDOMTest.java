package Chapter4Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ShadowDOMTest {
        WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private static final String SHADOW_DOM_URL = BASE_URL + "shadow-dom.html";

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

        @DisplayName("Проверка перехода на страницу с тестом")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getShadowDOM() {
        WebElement ShadowDOMLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='shadow-dom.html']"));
        ShadowDOMLink.click();
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(SHADOW_DOM_URL, actualURL, "Значения должны совпадать");

        WebElement ShadowDOMTitleText = driver.findElement(By.className("display-6"));
        Assertions.assertEquals("Shadow DOM", ShadowDOMTitleText.getText(), "Значения должны совпадать");

        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement ShadowDOMText = shadowRoot.findElement(By.cssSelector("p"));
        Assertions.assertEquals("Hello Shadow DOM", ShadowDOMText.getText(), "Значения должны совпадать");
    }
}
