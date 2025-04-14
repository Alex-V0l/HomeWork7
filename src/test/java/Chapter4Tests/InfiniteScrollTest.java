//package Chapter4Tests;
//
//import Configs.TestPropertiesConfig;
//import org.aeonbits.owner.ConfigFactory;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//import java.util.List;
//
//import static Constants.Constants.BASE_URL;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class InfiniteScrollTest {
//    WebDriver driver;
//    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
//    private static final String INFINITE_SCROLL_URL = BASE_URL + "infinite-scroll.html";
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
//    @DisplayName("Проверка перехода на страницу с тестом и проверка скролла")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void getInfiniteScrollURL() {
//        WebElement infiniteScrollLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='infinite-scroll.html']"));
//        infiniteScrollLink.click();
//        String actualURL = driver.getCurrentUrl();
//        WebElement InfiniteScrollText = driver.findElement(By.className("display-6"));
//
//        assertEquals(INFINITE_SCROLL_URL, actualURL, "Значения должны совпадать");
//        assertEquals("Infinite scroll", InfiniteScrollText.getText(), "Значения должны совпадать");
//    }
//
//    @DisplayName("Проверка бесконечного скролла и прекращения появления новых элементов")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void scrollInfiniteScrollTest() {
//        driver.get(INFINITE_SCROLL_URL);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        List<WebElement> LoremElements = driver.findElements(By.xpath("//b[text()='Lorem ipsum']"));
//        int initialCount = LoremElements.size();
//
//        assertTrue(initialCount > 0, "Хотя бы один элемент с текстом 'Lorem ipsum' должен быть изначально");
//
//        int amountOfScrolls = 5;
//        for (int i = 0; i < amountOfScrolls; i++) {
//            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
//                    By.xpath("//b[text()='Lorem ipsum']"), initialCount));
//            List<WebElement> updatedLoremElements = driver.findElements(By.xpath("//b[text()='Lorem ipsum']"));
//            int updatedCount = updatedLoremElements.size();
//
//            assertTrue(updatedCount > initialCount, "После скролла должно было появитьсь больше новых элемента, содержащих текст 'Lorem ipusm'");
//        }
//    }
//
//    @DisplayName("Проверка положения элемента в зоне видимости после скролла")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void changeVisibilityOfElementAfterScrollTest(){
//        driver.get(INFINITE_SCROLL_URL);
//        WebElement firstParagraphText = driver.findElement(By.xpath("//b[text()='Lorem ipsum']"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        boolean isFirstParagraphTextVisible = (Boolean) js.executeScript(
//                "var rect = arguments[0].getBoundingClientRect();" +
//                        "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", firstParagraphText
//        );
//
//        Assertions.assertTrue(isFirstParagraphTextVisible, "Элемент должно быть видно при переходе на страницу");
//
//        WebElement seventhParagraphText = driver.findElement(By.xpath("//p[@class='lead' and contains(text(), 'Curae')]"));
//        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seventhParagraphText);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(driver -> (Boolean) js.executeScript(
//                "var rect = arguments[0].getBoundingClientRect();" +
//                        "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", seventhParagraphText));
//        Boolean isFirstParagraphTextNotVisible = (Boolean) js.executeScript(
//                "var rect = arguments[0].getBoundingClientRect();" +
//                        "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", firstParagraphText
//        );
//
//        Assertions.assertFalse(isFirstParagraphTextNotVisible, "Первый абзац текста не должно быть видно");
//    }
//
//    @DisplayName("Проверка изменения положения элемента на страницу после скролла по координатам")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void changeElementPositionAfterScrollTest(){
//        driver.get(INFINITE_SCROLL_URL);
//        WebElement firstParagraphText = driver.findElement(By.xpath("//b[text()='Lorem ipsum']"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        Long initialTop = (Long) js.executeScript(
//                "return Math.floor(arguments[0].getBoundingClientRect().top);", firstParagraphText
//        );
//        WebElement seventhParagraphText = driver.findElement(By.xpath("//p[@class='lead' and contains(text(), 'Curae')]"));
//        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seventhParagraphText);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(driver -> (Boolean) js.executeScript(
//                "var rect = arguments[0].getBoundingClientRect();" +
//                        "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", seventhParagraphText));
//        Long newTop = (Long) js.executeScript(
//                "return Math.floor(arguments[0].getBoundingClientRect().top);", firstParagraphText
//        );
//
//        Assertions.assertTrue(initialTop>newTop, "Положение абзаца после скролла должно было измениться");
//    }
//}
//
//
//
