package FactoryTests;

import FactoryPOMPages.NavigationPageFactory;
import org.junit.jupiter.api.*;


public class NavigationFactoryTest extends BaseFactoryTest {

    private NavigationPageFactory navigationPageFactory;

    @BeforeEach
    void setUpNavigationPage() {
        navigationPageFactory = new NavigationPageFactory(driver);
        navigationPageFactory.open();
    }

    @DisplayName("проверка значений направляющих кнопок в исходном состоянии")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void basicStateValuesTest() {
        boolean isPreviousDisabled = navigationPageFactory.isPreviousButtonDisabled();

        Assertions.assertTrue(isPreviousDisabled, "На кнопку не должно быть возможным нажать");

        String expectedFirstParagraphText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua.";
        String actualFirstParagraphText = navigationPageFactory.getParagraphText();

        Assertions.assertEquals(expectedFirstParagraphText, actualFirstParagraphText, "В начале должен быть видне текст," +
                "начинающийся с Lorem ipsum");

        String expectedLinkForNextButtonText = "navigation2.html";
        String actualLinkForNextButtonText = navigationPageFactory.getNextButtonLinkInfo();

        Assertions.assertEquals(expectedLinkForNextButtonText, actualLinkForNextButtonText,
                "Кнопка должна переводить на вторую страницу");
    }

    @DisplayName("проверка значений направляющих кнопок и переход на вторую страницу")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void transitionToSecondPageTest() {
        String expectedSecondPageURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation2.html";

        navigationPageFactory.clickTwoButton();
        String actualSecondPageURL = navigationPageFactory.getCurrentURL();

        Assertions.assertEquals(expectedSecondPageURL, actualSecondPageURL, "Значения должны совпадать");

        String expectedParagraphText =
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " +
                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore " +
                        "eu fugiat nulla pariatur.";
        String actualParagraphText = navigationPageFactory.getParagraphText();

        Assertions.assertEquals(expectedParagraphText, actualParagraphText, "На второй странице должен быть виден" +
                "текст, начинающийся с Ut enim");

        String expectedNextButtonLinkText = "navigation3.html";
        String actualNextButtonLinkText = navigationPageFactory.getNextButtonLinkInfo();

        Assertions.assertEquals(expectedNextButtonLinkText, actualNextButtonLinkText, "Кнопка должна переводить" +
                "на третью страницу");

        String expectedPreviousButtonLinkText = "navigation1.html";
        String actualPreviousButtonLinkText = navigationPageFactory.getPreviousButtonLinkInfo();

        Assertions.assertEquals(expectedPreviousButtonLinkText, actualPreviousButtonLinkText, "Кнопка должна" +
                "переводить на первую страницу");
    }

    @DisplayName("проверка значений направляющих кнопок и переход на третью страницу")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void transitionToThirdTestPage() {
        String expectedThirdPageURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation3.html";

        navigationPageFactory.clickThreeButton();
        String actualThirdPageURL = navigationPageFactory.getCurrentURL();

        Assertions.assertEquals(expectedThirdPageURL, actualThirdPageURL, "Значения должны совпадать");

        String expectedParagraphText = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                "deserunt mollit anim id est laborum.";
        String actualParagraphText = navigationPageFactory.getParagraphText();

        Assertions.assertEquals(expectedParagraphText, actualParagraphText, "На третьей странице должен быть текст," +
                "начинающийся с Excepteur sint");

        boolean isNextButtonDisabled = navigationPageFactory.isNextButtonDisabled();

        Assertions.assertTrue(isNextButtonDisabled, "На кнопку нельзя нажать");

        String expectedPreviousButtonLinkText = "navigation2.html";
        String actualPreviousButtonLinkText = navigationPageFactory.getPreviousButtonLinkInfo();

        Assertions.assertEquals(expectedPreviousButtonLinkText, actualPreviousButtonLinkText, "Кнопка должна" +
                "переводить на вторую страницу");
    }

    @DisplayName("проверка перехода с помощью направляющих кнопок")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void   transitionWithNextButtonTest() {
        String expectedNextButtonLinkText = "navigation2.html";

        String actualNextButtonLinkText = navigationPageFactory.getNextButtonLinkInfo();

        Assertions.assertEquals(expectedNextButtonLinkText, actualNextButtonLinkText, "Значения должны совпадать");

        navigationPageFactory.clickNextButton();

        String expectedFirstTransitionURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation2.html";
        String actualFirstTransitionURL = navigationPageFactory.getCurrentURL();

        Assertions.assertEquals(expectedFirstTransitionURL, actualFirstTransitionURL, "Значения должны совпадать");

        String expectedPreviousButtonLinkText = "navigation1.html";
        String actualPreviousButtonLinkText = navigationPageFactory.getPreviousButtonLinkInfo();

        Assertions.assertEquals(expectedPreviousButtonLinkText, actualPreviousButtonLinkText, "Кнопка должа переводить" +
                "на первую страницу");

        navigationPageFactory.clickPreviousButton();

        String expectedSecondTransitionURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";
        String actualSecondTransitionURL = navigationPageFactory.getCurrentURL();

        Assertions.assertEquals(expectedSecondTransitionURL, actualSecondTransitionURL, "Значения должны совпадать");
    }

    @DisplayName("проверка title")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void getTitleTest(){
        String expectedTitleText = "Hands-On Selenium WebDriver with Java";
        String actualTitleText = navigationPageFactory.getHeaderComponent().getHeaderTitleText();

        Assertions.assertEquals(expectedTitleText, actualTitleText, "Значения должны совпадать");
    }

    @DisplayName("проверка subtitle in header")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void getSubtitleFromHeaderTest(){
        String expectedSubtitleFromHeaderText = "Practice site";
        String actualSubtitleFromHeaderText = navigationPageFactory.getHeaderComponent().getHeaderSubTitleText();

        Assertions.assertEquals(expectedSubtitleFromHeaderText, actualSubtitleFromHeaderText, "Значения должны совпадать");
    }

    @DisplayName("проверка logo click")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void logoClickTest(){
        String expectedURL = "https://github.com/bonigarcia/selenium-webdriver-java";

        navigationPageFactory.getHeaderComponent().clickLogo();

        String actualURL = navigationPageFactory.getCurrentURL();

        Assertions.assertEquals(expectedURL, actualURL, "Значения должны совпадать");
    }

    @DisplayName("проверка Copyright")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void getCopyRightText(){
        String expectedText = "Copyright © 2021-2025 Boni García";
        String actualText = navigationPageFactory.getFooterComponent().getCopyrightText();

        Assertions.assertEquals(expectedText, actualText, "Значения должны совпадать");
    }

    @DisplayName("проверка developer's page link")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void getDevelopersPageLink(){
        String expectedURL = "https://bonigarcia.dev/";

        navigationPageFactory.getFooterComponent().clickOnDevelopersPageLink();

        String actualURL = navigationPageFactory.getCurrentURL();

        Assertions.assertEquals(expectedURL, actualURL, "Значения должны совпадать");
    }
}
