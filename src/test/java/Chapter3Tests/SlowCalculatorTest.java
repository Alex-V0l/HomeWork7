package Chapter3Tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static Constants.Constants.BASE_URL;


public class SlowCalculatorTest {

    WebDriver driver;
    private static final String SLOW_CALCULATOR_URL = BASE_URL+ "slow-calculator.html";

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
    void getSlowCalculatorURL() {
        WebElement slowCalculatorLink = driver.findElement(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='slow-calculator.html']"));
        slowCalculatorLink.click();
        String actualURL = driver.getCurrentUrl();
        WebElement slowCalculatorText = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(SLOW_CALCULATOR_URL, actualURL, "Значения должны совпадать");
        Assertions.assertEquals("Slow calculator", slowCalculatorText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка соответствия значения ожидания расчета")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void delayOfSlowCalculatorTest() {
        driver.get(SLOW_CALCULATOR_URL);
        WebElement DelayField = driver.findElement(By.id("delay"));
        int delayValue = Integer.parseInt(String.valueOf(DelayField.getDomAttribute("value")));

        Assertions.assertEquals(5, delayValue, "Значения должны совпадать");
    }

    @DisplayName("Позитивная проверка с использованием цифр, операторов и стирания значений")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @ParameterizedTest
    @CsvSource({
            "3, '+', 9, '12'",
            "6, '-', 1, '5'",
            "7, 'x', 5, '35'",
            "8, '÷', 2, '4'"})
    void useSlowCalculatorTest(int numberForInput, char symbolOfOperation, int secondNumberForInput, String result){
            driver.get(SLOW_CALCULATOR_URL);
            performCalculator(numberForInput, symbolOfOperation, secondNumberForInput);
            WebElement resultOnScreen = driver.findElement(By.className("screen"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, result));
            String resultOfCalculation = resultOnScreen.getText();

            Assertions.assertEquals(result, resultOfCalculation, "Значения должны совпадать");
            clear();
    }

    @DisplayName("Негативные проверки с делением")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @ParameterizedTest
    @CsvSource({
            "4, '÷', 0, 'Infinity'",
            "0, '÷', 0, 'NaN'"})
    void SlowCalculatorNegativeTest(int numberForInput, char symbolOfOperation, int secondNumberForInput, String result){
        driver.get(SLOW_CALCULATOR_URL);
        performCalculator(numberForInput, symbolOfOperation, secondNumberForInput);
        WebElement resultOnScreen = driver.findElement(By.className("screen"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, result));
        String resultOfCalculation = resultOnScreen.getText();

        Assertions.assertEquals(result, resultOfCalculation, "Значения должны совпадать");
        clear();
    }

    @DisplayName("Изменение ожидания расчета")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Disabled//значение ожидания не меняется, хотя кнопками его можно поменять. Значение value в элементе при этом не меняется.
    @Test
    void changeDelayTest(){
        String newDelayValue = "3";
        String expectedCalculationResult = "3";

        driver.get(SLOW_CALCULATOR_URL);
        WebElement DelayField = driver.findElement(By.id("delay"));
        DelayField.clear();
        DelayField.sendKeys(newDelayValue);
        WebDriverWait firstWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        firstWait.until(ExpectedConditions.domAttributeToBe(DelayField, "value", newDelayValue));
        String actualDelayValue = DelayField.getDomAttribute("value");
        performCalculator(1, '+', 2);
        WebElement resultOnScreen = driver.findElement(By.className("screen"));
        WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(6));
        secondWait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, expectedCalculationResult));
        String actualCalculationResult = resultOnScreen.getText();

        Assertions.assertEquals(newDelayValue, actualDelayValue, "Должно было задаться новое значение для ожидания");
        Assertions.assertEquals(expectedCalculationResult, actualCalculationResult, "Значения должны совпадать");
    }

    @DisplayName("Проверка совершения операции с результатом предыдущего вычисления")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void usePreviousResultTest(){
       String FirstResultOfCalculation = "10";
       String LastResultOfCalculation = "18";

       driver.get(SLOW_CALCULATOR_URL);
       performCalculator(1,'+',9);
       WebElement resultOnScreen = driver.findElement(By.className("screen"));
       WebDriverWait firstWait = new WebDriverWait(driver, Duration.ofSeconds(5));
       firstWait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, FirstResultOfCalculation));
       String resultOfFirstCalculation = resultOnScreen.getText();

       Assertions.assertEquals(FirstResultOfCalculation, resultOfFirstCalculation, "Значения должны совпадать");

       chooseOperation('+');
       chooseNumber(8);
       equals();
       WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(5));
       secondWait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, LastResultOfCalculation));
       String resultOfLastCalculation = resultOnScreen.getText();

       Assertions.assertEquals(LastResultOfCalculation, resultOfLastCalculation, "Значения должны совпадать");
    }

    @DisplayName("Вычисление с дробным числом")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void SlowCalculatorFractionalTest(){
        String result = "7.5";

        driver.get(SLOW_CALCULATOR_URL);
        chooseNumber(2);
        separator();
        chooseNumber(5);
        chooseOperation('x');
        chooseNumber(3);
        equals();
        WebElement resultOnScreen = driver.findElement(By.className("screen"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, result));
        String resultOfCalculation = resultOnScreen.getText();

        Assertions.assertEquals(result, resultOfCalculation, "Значения должны совпадать");
    }

    @DisplayName("Вычисление с отрицательным число")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void SlowCalculatorNegativeNumberTest(){
        String expectedResult = "-10";

        driver.get(SLOW_CALCULATOR_URL);
        chooseOperation('-');
        chooseNumber(2);
        chooseOperation('x');
        chooseNumber(5);
        equals();
        WebElement resultOnScreen = driver.findElement(By.className("screen"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, expectedResult));
        String actualResult = resultOnScreen.getText();

        Assertions.assertEquals(expectedResult, actualResult, "Значения должны совпадать");
    }

    @DisplayName("Использование нескольких операторов подряд")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void severalOperatorsTest(){
        String expectedResult = "6";

        driver.get(SLOW_CALCULATOR_URL);
        chooseNumber(9);
        chooseOperation('x');
        chooseOperation('-');
        chooseNumber(3);
        equals();
        WebElement resultOnScreen = driver.findElement(By.className("screen"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, expectedResult));
        String actualResult = resultOnScreen.getText();

        Assertions.assertEquals(expectedResult, actualResult, "При использовании нескольких операторов подряд, должен был примениться последний");
    }

    @DisplayName("Использование нескольких точек подряд")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void severalSeparatorsTest(){
        String expectedResult = "10";

        driver.get(SLOW_CALCULATOR_URL);
        chooseNumber(5);
        separator();
        separator();
        chooseNumber(4);
        chooseOperation('+');
        chooseNumber(4);
        separator();
        separator();
        chooseNumber(6);
        equals();
        WebElement resultOnScreen = driver.findElement(By.className("screen"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(resultOnScreen, expectedResult));
        String actualResult = resultOnScreen.getText();

        Assertions.assertEquals(expectedResult, actualResult, "При использовании несколькхи точек, длолжна использоваться только одна");
    }

    private void performCalculator(int numberForInput, char symbolOfOperation, int secondNumberForInput){
        chooseNumber(numberForInput);
        chooseOperation(symbolOfOperation);
        chooseNumber(secondNumberForInput);
        equals();
    }

    private void chooseOperation(char symbolOfOperation){
    WebElement operationButton = driver.findElement(By.xpath("//span[contains(@class, 'operator') and contains(@class, 'btn') and contains(@class, 'btn-outline-success') and text()='" + symbolOfOperation + "']"));
    operationButton.click();
    }

    private void chooseNumber (int numberForInput){
    WebElement numberButton = driver.findElement(By.xpath("//span[contains(@class, 'btn') and contains(@class, 'btn-outline-primary') and text()='" + numberForInput + "']"));
    numberButton.click();
    }

    private void separator (){
    WebElement separatorButton = driver.findElement(By.xpath("//span[contains(@class, 'btn') and contains(@class, 'btn-outline-primary') and text()='.']"));
    separatorButton.click();
    }

    private void equals (){
    WebElement equalsButton = driver.findElement(By.xpath("//span[contains(@class, 'btn') and contains(@class, 'btn-outline-warning')]"));
    equalsButton.click();
    }

    private void clear (){
    WebElement clearButton = driver.findElement(By.xpath("//span[contains(@class, 'clear') and contains(@class, 'btn-outline-danger')]"));
    clearButton.click();
    }
}
