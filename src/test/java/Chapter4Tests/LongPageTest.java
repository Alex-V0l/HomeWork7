package Chapter4Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class LongPageTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private static final String LONG_PAGE_URL = BASE_URL + "long-page.html";
    private static final String FOOTER = "footer";

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

    @DisplayName("Проверка перехода на страницу Long page")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getLongPage_URL() {
        WebElement lonPageLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='long-page.html']"));
        lonPageLink.click();
        String actualURL = driver.getCurrentUrl();
        WebElement LongPageText = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(LONG_PAGE_URL, actualURL, "Значения должны совпадать");
        Assertions.assertEquals("This is a long page", LongPageText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка скролла страницы")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void scrollLongPageTest() {
        driver.get(LONG_PAGE_URL);
        WebElement footer = driver.findElement(By.tagName(FOOTER));
        int footersInitHeight = Integer.parseInt(String.valueOf(footer.getRect().y));
        int windowsHeight = Integer.parseInt(String.valueOf(driver.manage().window().getSize().getHeight()));
        boolean isFooterVisible = footersInitHeight < windowsHeight;

        Assertions.assertFalse(isFooterVisible, "При переходе на страницу футер не должно быть видно");

        Actions actions = new Actions(driver);
        actions.scrollToElement(footer).perform();
        int footerNewHeight = Integer.parseInt(String.valueOf(footer.getRect().y));
        boolean isFooterStillVisible = footerNewHeight >= windowsHeight;

        Assertions.assertTrue(isFooterStillVisible, "После скролла футер видно");
    }
}
