package FactoryTests;

import Configs.TestPropertiesConfig;
import FactoryPOMPages.LoginFormFactoryPage;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

public class LoginFormFactoryTest extends BaseFactoryTest {

    TestPropertiesConfig config;
    private LoginFormFactoryPage loginFormFactoryPage;

    @BeforeEach
    void setUpLoginForm(){
        loginFormFactoryPage = new LoginFormFactoryPage(driver);
        config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        loginFormFactoryPage.open();
    }

    @DisplayName("Проверка проведения авторизации")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void signInTest(){
        String expectedMessage = "Login successful";
        String userNameFromProperties = config.getUsername();
        String passwordFromProperties = config.getPassword();

        loginFormFactoryPage.sendTextToLoginForm(userNameFromProperties);
        loginFormFactoryPage.sendTextToPasswordForm(passwordFromProperties);
        loginFormFactoryPage.clickSubmitButton();
        String actualMessage = loginFormFactoryPage.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage, "Значения должны совпадать");
    }

    @DisplayName("Проверка провала авторизации")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void InvalidSignInTest(){
        String userNameFromProperties = config.getUsername();
        String InvalidPassword = "1234";
        String expectedMessage = "Invalid credentials";

        loginFormFactoryPage.sendTextToLoginForm(userNameFromProperties);
        loginFormFactoryPage.sendTextToPasswordForm(InvalidPassword);
        loginFormFactoryPage.clickSubmitButton();
        String actualMessage = loginFormFactoryPage.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage, "Значения должны совпадать");
    }
}
