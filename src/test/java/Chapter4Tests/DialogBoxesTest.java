package Chapter4Tests;

import Configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static Constants.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class DialogBoxesTest {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    private static final String DIALOG_BOXES_URL = BASE_URL + "dialog-boxes.html";

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

    @DisplayName("Проверка перехода на страницу Dialog boxes")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getDialogBoxesURL() {
        WebElement dialogBoxesLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='dialog-boxes.html']"));
        dialogBoxesLink.click();
        String actualURL = driver.getCurrentUrl();
        WebElement InfiniteScrollText = driver.findElement(By.className("display-6"));

        assertEquals(DIALOG_BOXES_URL, actualURL, "Значения должны совпадать");
        assertEquals("Dialog boxes", InfiniteScrollText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка alert")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myAlertTest() {
        driver.get(DIALOG_BOXES_URL);
        WebElement AlertButton = driver.findElement(By.id("my-alert"));
        AlertButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assertions.assertEquals("Hello world!", alertText, "Значения должны совпадать");
        alert.accept();
    }

    @DisplayName("Проверка принятия Confirm")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myConfirmAcceptTest() {
        driver.get(DIALOG_BOXES_URL);
        WebElement ConfirmButton = driver.findElement(By.id("my-confirm"));
        ConfirmButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String confirmText = alert.getText();

        Assertions.assertEquals("Is this correct?", confirmText, "Значения должны совпадать");

        alert.accept();
        WebElement ConfirmText = driver.findElement(By.id("confirm-text"));
        String ConfirmTextAsString = ConfirmText.getText();
        Assertions.assertEquals("You chose: true", ConfirmTextAsString, "Значения должны совпадать");
    }

    @DisplayName("Проверка отмены Confirm")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myConfirmDismissTest(){
        driver.get(DIALOG_BOXES_URL);
        WebElement ConfirmButton = driver.findElement(By.id("my-confirm"));
        ConfirmButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        WebElement DismissText = driver.findElement(By.id("confirm-text"));
        String DismissTextAsString =DismissText.getText();

        Assertions.assertEquals("You chose: false", DismissTextAsString, "Значения должны совпадать");
    }

    @DisplayName("Проверка Prompt и ввода значения")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myPromptTest() {
        driver.get(DIALOG_BOXES_URL);
        WebElement promptButton = driver.findElement(By.id("my-prompt"));
        promptButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String promptText = alert.getText();

        Assertions.assertEquals("Please enter your name", promptText, "Значения должны совпадать");
    }

    @DisplayName("Проверка ввода значения в Prompt")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myPromptInputTest(){
        String textForPrompt = "Alex";

        driver.get(DIALOG_BOXES_URL);
        WebElement promptButton = driver.findElement(By.id("my-prompt"));
        promptButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(textForPrompt);
        alert.accept();
        WebElement representedText = driver.findElement(By.id("prompt-text"));
        String expectedText = "You typed: " + textForPrompt;
        String actualText = representedText.getText();

        Assertions.assertEquals(expectedText, actualText, "Значения должны совпадать");
    }

    @DisplayName("Проверка отмены Prompt")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void myPromptDismissTest(){
        driver.get(DIALOG_BOXES_URL);
        WebElement promptButton = driver.findElement(By.id("my-prompt"));
        promptButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        secondWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("prompt-text"), "You typed: null"));
        WebElement DismissText = driver.findElement(By.id("prompt-text"));
        String DismissTextAsString = DismissText.getText();

        Assertions.assertEquals("You typed: null", DismissTextAsString, "Значения должны совпадать");
    }

    @DisplayName("Проверка Модального окна и кнопки close")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void ModalCloseButtonTest() {
        driver.get(DIALOG_BOXES_URL);
        WebElement ModalButton = driver.findElement(By.id("my-modal"));
        ModalButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.and(presenceOfElementLocated(By.className("modal-content")),
                (visibilityOfElementLocated(By.xpath("//div[@class='modal-content']//div[@class='modal-body' and normalize-space(text())='This is the modal body']")))));
        WebElement modalText = driver.findElement(By.xpath("//div[@class='modal-content']//div[@class='modal-body' and normalize-space(text())='This is the modal body']"));
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
        driver.get(DIALOG_BOXES_URL);
        WebElement ModalButton = driver.findElement(By.id("my-modal"));
        ModalButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.and(presenceOfElementLocated(By.className("modal-content")),
                (presenceOfElementLocated(By.xpath("//button[@class='btn btn-primary model-button']")))));
        WebElement saveChangesButton = driver.findElement(By.xpath("//button[@class='btn btn-primary model-button']"));
        saveChangesButton.click();
        WebElement saveChangesText = driver.findElement(By.id("modal-text"));
        String actualSaveChangesText = saveChangesText.getText();

        Assertions.assertEquals("You chose: Save changes", actualSaveChangesText, "Значения должны совпадать");
    }
}
