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

    @DisplayName("Проверка перехода на страницу с тестом")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getLongPageURL() {
        WebElement lonPageLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='long-page.html']"));
        lonPageLink.click();
        String actualURL = driver.getCurrentUrl();
        WebElement LongPageText = driver.findElement(By.className("display-6"));
        WebElement footer = driver.findElement(By.tagName(FOOTER));
        int footersHeight = Integer.parseInt(String.valueOf(footer.getRect().y));
        int windowsHeight = Integer.parseInt(String.valueOf(driver.manage().window().getSize().getHeight()));
        boolean isFooterVisible = footersHeight < windowsHeight;

        Assertions.assertEquals(LONG_PAGE_URL, actualURL, "Значения должны совпадать");
        Assertions.assertEquals("This is a long page", LongPageText.getText(), "Значения должны совпадать");
        Assertions.assertFalse(isFooterVisible, "При переходе на страницу футер не видно");
    }

    @DisplayName("Проверка скролла страницы")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void scrollLongPage() {
        WebElement lonPageLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='long-page.html']"));
        lonPageLink.click();

        WebElement footer = driver.findElement(By.tagName(FOOTER));
        Actions actions = new Actions(driver);
        actions.scrollToElement(footer).perform();
        int footersHeight = Integer.parseInt(String.valueOf(footer.getRect().y));
        int windowsHeight = Integer.parseInt(String.valueOf(driver.manage().window().getSize().getHeight()));
        boolean isFooterVisible = footersHeight >= windowsHeight;

        Assertions.assertTrue(isFooterVisible, "После скролла футер видно");
    }
}
