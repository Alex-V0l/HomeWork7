package Chapter4Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DialogBoxesTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html";

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
    void getDialogBoxesURL() {

        String actualURL = driver.getCurrentUrl();
        assertEquals(BASE_URL, actualURL, "Значения должны совпадать");

        WebElement InfiniteScrollText = driver.findElement(By.className("display-6"));
        assertEquals("Dialog boxes", InfiniteScrollText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка алёрта")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myAlertTest() {
        WebElement AlertButton = driver.findElement(By.id("my-alert"));
        AlertButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        Assertions.assertEquals("Hello world!", alertText, "Значения должны совпадать");
        alert.accept();
    }

    @DisplayName("Проверка Конфёрма")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myConfirmTest() {
        WebElement ConfirmButton = driver.findElement(By.id("my-confirm"));
        ConfirmButton.click();

        WebDriverWait firstWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        firstWait.until(ExpectedConditions.alertIsPresent());
        Alert alertFirstCall = driver.switchTo().alert();

        String confirmText = alertFirstCall.getText();
        Assertions.assertEquals("Is this correct?", confirmText, "Значения должны совпадать");
        alertFirstCall.accept();

        WebElement ConfirmText = driver.findElement(By.id("confirm-text"));
        String ConfirmTextAsString = ConfirmText.getText();
        Assertions.assertEquals("You chose: true", ConfirmTextAsString, "Значения должны совпадать");

        ConfirmButton.click();
        WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        secondWait.until(ExpectedConditions.alertIsPresent());
        Alert alertSecondCall = driver.switchTo().alert();
        alertSecondCall.dismiss();
        WebElement DismissText = driver.findElement(By.id("confirm-text"));
        String DismissTextAsString =DismissText.getText();
        Assertions.assertEquals("You chose: false", DismissTextAsString, "Значения должны совпадать");
    }

    @DisplayName("Проверка Промпта")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myPromptTest() {
        WebElement PromptButton = driver.findElement(By.id("my-prompt"));
        PromptButton.click();

        WebDriverWait firstWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        firstWait.until(ExpectedConditions.alertIsPresent());
        Alert alertFirstCall = driver.switchTo().alert();

        String confirmText = alertFirstCall.getText();
        Assertions.assertEquals("Please enter your name", confirmText, "Значения должны совпадать");

        String textForPrompt = "Alex";
        alertFirstCall.sendKeys(textForPrompt);
        alertFirstCall.accept();
        WebElement representedText = driver.findElement(By.id("prompt-text"));
        String expectedText = "You typed: " + textForPrompt;
        String actualText = representedText.getText();
        Assertions.assertEquals(expectedText, actualText, "Значения должны совпадать");

        PromptButton.click();
        WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        secondWait.until(ExpectedConditions.alertIsPresent());
        Alert alertSecondCall = driver.switchTo().alert();
        alertSecondCall.dismiss();

        WebDriverWait thirdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        thirdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("prompt-text"), "You typed: null"));
        WebElement DismissText = driver.findElement(By.id("prompt-text"));
        String DismissTextAsString = DismissText.getText();
        Assertions.assertEquals("You typed: null", DismissTextAsString, "Значения должны совпадать");
    }

    @DisplayName("Проверка Модального окна и кнопки close")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void ModalCloseButtonTest() {
        WebElement ModalButton = driver.findElement(By.id("my-modal"));
        ModalButton.click();

        WebDriverWait firstWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        firstWait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));
        WebElement modalText = driver.findElement(By.xpath("//div[@class='modal-content']//div[@class='modal-body' and normalize-space(text())='This is the modal body']"));
        WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        secondWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']//div[@class='modal-body' and normalize-space(text())='This is the modal body']")));
        String actualText = modalText.getText();
        Assertions.assertEquals("This is the modal body", actualText, "Значения должны совпадать");

        WebElement closeButton = driver.findElement(By.xpath("//button[@class='btn btn-secondary model-button']"));
        closeButton.click();
        WebElement closeText = driver.findElement(By.id("modal-text"));
        String actualCloseText = closeText.getText();
        Assertions.assertEquals("You chose: Close", actualCloseText, "Значения должны совпадать");
    }

    @DisplayName("Проверка Модального окна и кнопки save changes")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void ModalSaveButtonTest(){
        WebElement ModalButton = driver.findElement(By.id("my-modal"));
        ModalButton.click();

        WebDriverWait firstWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        firstWait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));

        WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        secondWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn btn-primary model-button']")));
        WebElement saveChangesButton = driver.findElement(By.xpath("//button[@class='btn btn-primary model-button']"));
        saveChangesButton.click();
        WebElement saveChangesText = driver.findElement(By.id("modal-text"));
        String actualSaveChangesText = saveChangesText.getText();
        Assertions.assertEquals("You chose: Save changes", actualSaveChangesText, "Значения должны совпадать");
    }
}
