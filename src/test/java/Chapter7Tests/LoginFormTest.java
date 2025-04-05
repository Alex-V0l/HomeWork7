package Chapter7Tests;

import Configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static Constants.Constants.BASE_URL;

public class LoginFormTest {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    private static final String LOGIN_FORM_URL = BASE_URL + "login-form.html";

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

    @DisplayName("Проверка перехода на страницу Login form")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void getLoginFormURL(){
        WebElement loginFormLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='login-form.html']"));
        loginFormLink.click();
        String actualURL = driver.getCurrentUrl();
        WebElement loginFormText = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(LOGIN_FORM_URL, actualURL, "Значения должны совпадать");
        Assertions.assertEquals("Login form", loginFormText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка проведения авторизации")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void signInTest(){
        driver.get(LOGIN_FORM_URL);
        WebElement loginForm = driver.findElement(By.id("username"));
        WebElement passwordForm = driver.findElement(By.id("password"));
        loginForm.sendKeys(config.getUsername());
        passwordForm.sendKeys(config.getPassword());
        WebElement submitButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        submitButton.click();
        WebElement message = driver.findElement(By.className("alert"));

        Assertions.assertEquals("Login successful", message.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка провала авторизации")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void InvalidSignInTest(){
        String InvalidPassword = "1234";

        driver.get(LOGIN_FORM_URL);
        WebElement loginForm = driver.findElement(By.id("username"));
        WebElement passwordForm = driver.findElement(By.id("password"));
        loginForm.sendKeys(config.getUsername());
        passwordForm.sendKeys(InvalidPassword);
        WebElement submitButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        submitButton.click();
        WebElement message = driver.findElement(By.xpath("//div[@role='alert']"));

        Assertions.assertEquals("Invalid credentials", message.getText(), "Значения должны совпадать");
    }
}
