//package POMTests;
//
//import POMPages.NavigationPage;
//import org.junit.jupiter.api.*;
//
//
//public class NavigationTest extends BaseTest {
//
//
//
//    @DisplayName("проверка значений направляющих кнопок в исходном состоянии")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void basicStateValuesTest() {
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.open();
//        boolean isPreviousDisabled = navigationPage.isPreviousButtonDisabled();
//
//        Assertions.assertTrue(isPreviousDisabled, "На кнопку не должно быть возможным нажать");
//
//        String expectedFirstParagraphText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
//                "tempor incididunt ut labore et dolore magna aliqua.";
//        String actualFirstParagraphText = navigationPage.getParagraphText();
//
//        Assertions.assertEquals(expectedFirstParagraphText, actualFirstParagraphText, "Значения должны совпадать");
//
//        String expectedLinkForNextButtonText = "navigation2.html";
//        String actualLinkForNextButtonText = navigationPage.getNextButtonLinkInfo();
//
//        Assertions.assertEquals(expectedLinkForNextButtonText, actualLinkForNextButtonText,
//                "Кнопка должна переводить на следующую страницу");
//    }
//
//    @DisplayName("проверка значений направляющих кнопок и переход на вторую страницу")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void transitionToSecondPageTest() {
//        String expectedSecondPageURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation2.html";
//
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.open();
//        navigationPage.clickTwoButton();
//        String actualSecondPageURL = navigationPage.getCurrentURL();
//
//        Assertions.assertEquals(expectedSecondPageURL, actualSecondPageURL, "Значения должны совпадать");
//
//        String expectedParagraphText =
//                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " +
//                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore " +
//                        "eu fugiat nulla pariatur.";
//        String actualParagraphText = navigationPage.getParagraphText();
//
//        Assertions.assertEquals(expectedParagraphText, actualParagraphText, "Значения должны совпадать");
//
//        String expectedNextButtonLinkText = "navigation3.html";
//        String actualNextButtonLinkText = navigationPage.getNextButtonLinkInfo();
//
//        Assertions.assertEquals(expectedNextButtonLinkText, actualNextButtonLinkText, "Значения должны совпадать");
//
//        String expectedPreviousButtonLinkText = "navigation1.html";
//        String actualPreviousButtonLinkText = navigationPage.getPreviousButtonLinkInfo();
//
//        Assertions.assertEquals(expectedPreviousButtonLinkText, actualPreviousButtonLinkText, "Значения должны совпадать");
//    }
//
//    @DisplayName("проверка значений направляющих кнопок и переход на третью страницу")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void transitionToThirdTestPage() {
//        String expectedThirdPageURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation3.html";
//
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.open();
//        navigationPage.clickThreeButton();
//        String actualThirdPageURL = navigationPage.getCurrentURL();
//
//        Assertions.assertEquals(expectedThirdPageURL, actualThirdPageURL, "Значения должны совпадать");
//
//        String expectedParagraphText = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
//                "deserunt mollit anim id est laborum.";
//        String actualParagraphText = navigationPage.getParagraphText();
//
//        Assertions.assertEquals(expectedParagraphText, actualParagraphText, "Значения должны совпадать");
//
//        boolean isNextButtonDisabled = navigationPage.isNextButtonDisabled();
//
//        Assertions.assertTrue(isNextButtonDisabled, "На кнопку нельзя нажать");
//
//        String expectedPreviousButtonLinkText = "navigation2.html";
//        String actualPreviousButtonLinkText = navigationPage.getPreviousButtonLinkInfo();
//
//        Assertions.assertEquals(expectedPreviousButtonLinkText, actualPreviousButtonLinkText, "Значения должны совпадать");
//    }
//
//    @DisplayName("проверка перехода с помощью направляющих кнопок")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void   transitionWithNextButtonTest() {
//        String expectedNextButtonLinkText = "navigation2.html";
//
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.open();
//        String actualNextButtonLinkText = navigationPage.getNextButtonLinkInfo();
//
//        Assertions.assertEquals(expectedNextButtonLinkText, actualNextButtonLinkText, "Значения должны совпадать");
//
//        navigationPage.clickNextButton();
//
//        String expectedFirstTransitionURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation2.html";
//        String actualFirstTransitionURL = navigationPage.getCurrentURL();
//
//        Assertions.assertEquals(expectedFirstTransitionURL, actualFirstTransitionURL, "Значения должны совпадать");
//
//        String expectedPreviousButtonLinkText = "navigation1.html";
//        String actualPreviousButtonLinkText = navigationPage.getPreviousButtonLinkInfo();
//
//        Assertions.assertEquals(expectedPreviousButtonLinkText, actualPreviousButtonLinkText, "Значения должны совпадать");
//
//        navigationPage.clickPreviousButton();
//
//        String expectedSecondTransitionURL = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";
//        String actualSecondTransitionURL = navigationPage.getCurrentURL();
//
//        Assertions.assertEquals(expectedSecondTransitionURL, actualSecondTransitionURL, "Значения должны совпадать");
//    }
//
//    @DisplayName("Проверка ссылки Return to index")
//    @Tags({@Tag("Smoke"), @Tag("UI")})
//    @Test
//    void findReturnToIndexLinkTest() {
//        String expectedURL = "https://bonigarcia.dev/selenium-webdriver-java/index.html";
//
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.open();
//        navigationPage.returnToIndex();
//        String actualURL = navigationPage.getCurrentURL();
//
//        Assertions.assertEquals(expectedURL, actualURL, "Значения должны совпадать");
//    }
//}
