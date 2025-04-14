package Chapter3Tests;

import POM.HomePage;
import POM.WebFormPage;
import org.junit.jupiter.api.*;
import POM.BaseTest;

import static Constants.Constants.WEB_FORM_URL;

public class WebFormTest extends BaseTest {

    @DisplayName("Проверка перехода на страницу и поиска подзаголовка")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void webFormURLAndSubTitleTest() {
        String expectedSubtitle = "Web form";

        HomePage homePage = new HomePage(driver);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebFormPage();
        String actualURL = webFormPage.getCurrentURL();
        String actualSubtitle = webFormPage.getSubtitleText();

        Assertions.assertEquals(expectedSubtitle, actualSubtitle, "Значения должны совпадать");
        Assertions.assertEquals(WEB_FORM_URL, actualURL, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы text input")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findTextInputFormTest() {
        String textForTextForm = "test";
        String expectedTextInput = "Text input";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualTextInput = webFormPage.getTextInput();
        webFormPage.sendTextToTextForm(textForTextForm);
        String valueOfTextForm = webFormPage.getValueOfTextForm();

        Assertions.assertEquals(expectedTextInput, actualTextInput, "Значения должны совпадать");
        Assertions.assertEquals(textForTextForm, valueOfTextForm, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы password")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findPasswordFormTest() {
        String password = "1234f";
        String expectedPasswordName = "Password";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualPasswordName = webFormPage.getPasswordName();
        webFormPage.sendTextToPasswordForm(password);
        String valueOfPasswordForm = webFormPage.getValueOfPasswordForm();

        Assertions.assertEquals(expectedPasswordName, actualPasswordName, "Значения должны совпадать");
        Assertions.assertEquals(password, valueOfPasswordForm, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы Textarea")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findTextAreaFormTest() {
        String expectedTextAreaText = "Textarea";
        String textForTextArea = "this\n" +
                "text\n" +
                "is\n" +
                "for\n" +
                "text\n" +
                "area\n" +
                "to\n" +
                "test";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualTextAreaText = webFormPage.getTextAreaText();
        webFormPage.sendTextToTextAreaForm(textForTextArea);
        String actualTextAreaValue = webFormPage.getValueOfTextAreaForm();

        Assertions.assertEquals(expectedTextAreaText, actualTextAreaText, "Значения должны совпадать");
        Assertions.assertEquals(textForTextArea, actualTextAreaValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы Disabled input")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDisabledInputFormTest() {
        String expectedDisabledInputText = "Disabled input";
        String textForDisabledInput = "try this text";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualDisabledInputText = webFormPage.getDisabledInputText();
        boolean isEnabled = webFormPage.isDisabledInputEnable();

        Assertions.assertFalse(isEnabled, "Элемент недолжен быть доступным");
        Assertions.assertEquals(expectedDisabledInputText, actualDisabledInputText, "Значения должны совпадать");
        Assertions.assertThrows(org.openqa.selenium.ElementNotInteractableException.class, () -> webFormPage.sendTextToDisabledInput(textForDisabledInput),
                "Дложно было быть выброшено исключение, что с элементом нельзя взаимодействовать");
    }

    @DisplayName("Проверка формы ReadOnlyInput")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findReadOnlyInputFormTest() {
        String expectedReadonlyText = "Readonly input";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualReadonlyInputText = webFormPage.getReadonlyInputText();
        boolean isReadOnly = webFormPage.isReadonly();

        Assertions.assertEquals(expectedReadonlyText, actualReadonlyInputText, "Значения должны совпадать");
        Assertions.assertTrue(isReadOnly, "Поле должно быть доступно только для чтения");
    }

    @DisplayName("Проверка ссылки Return to index")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findReturnToIndexLinkTest() {
        String expectedURL = "https://bonigarcia.dev/selenium-webdriver-java/index.html";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        webFormPage.returnToIndex();
        String actualURL = webFormPage.getCurrentURL();

        Assertions.assertEquals(expectedURL, actualURL, "Значения должны совпадать");
    }

    @DisplayName("Проверка ссылки на страницу разработчика и Copyright")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findBoniGarciaTest() {
        String expectedURL = "https://bonigarcia.dev/";
        String expectedCopyrightText = "Copyright © 2021-2025 Boni García";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualCopyrightText = webFormPage.getCopyrightText();

        Assertions.assertEquals(expectedCopyrightText, actualCopyrightText, "Значения должны совпадать");

        webFormPage.getDevelopersPageLink();
        String actualURL = webFormPage.getCurrentURL();

        Assertions.assertEquals(expectedURL, actualURL, "Значения должны совпадать");
    }

    @DisplayName("Проверка Dropdown (select)")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDropDownSelectTest() {
        String expectedDropDownText = "Dropdown (select)";
        String optionToSelect = "2";
        String expectedDropDownSelectOption = "Two";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualDropDownText = webFormPage.getDropDownText();
        String actualSelectedOption = webFormPage.chooseOptionFromDropDownSelect(optionToSelect);
        boolean isOptionSelected = webFormPage.isOptionSelected(optionToSelect);

        Assertions.assertEquals(expectedDropDownText, actualDropDownText, "Значения должны совпадать");
        Assertions.assertEquals(expectedDropDownSelectOption, actualSelectedOption, "Значения должны совпадать");
        Assertions.assertTrue(isOptionSelected, "Должно быть выбрано значение \"Two\"");
    }

    @DisplayName("Проверка Dropdown (datalist)")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDropDownDataListTest() {
        String expectedDropDownDataListText = "Dropdown (datalist)";
        String textToSendToDropDownDataList = "New York";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualDropDownDataListText = webFormPage.getDropDownDataListText();
        webFormPage.sendTextToDropDownDataList(textToSendToDropDownDataList);
        String actualTextFromDropDownDataList = webFormPage.getTextFromDropDownDataList();

        Assertions.assertEquals(expectedDropDownDataListText, actualDropDownDataListText, "Значения должны совпадать");
        Assertions.assertEquals(textToSendToDropDownDataList, actualTextFromDropDownDataList, "Значения должны совпадать");
    }

    @DisplayName("Проверка Checked checkbox")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findCheckBoxTest() {
        String expectedCheckedCheckboxText = "Checked checkbox";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualCheckedCheckboxText = webFormPage.getCheckedCheckboxText();
        boolean isCheckBoxSelected = webFormPage.isCheckboxChecked();
        boolean isCheckBoxNotSelected = webFormPage.isCheckboxIsNotChecked();

        Assertions.assertEquals(expectedCheckedCheckboxText, actualCheckedCheckboxText, "Значения должны совпадать");
        Assertions.assertTrue(isCheckBoxSelected, "Checkbox must be checked");
        Assertions.assertFalse(isCheckBoxNotSelected, "Checkbox must not be checked");
    }

    @DisplayName("Проверка Default checkbox")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDefaultBoxTest() {
        String expectedDefaultCheckboxText = "Default checkbox";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualDefaultCheckboxText = webFormPage.getDefaultCheckboxText();
        boolean isDefaultCheckboxNotSelected = webFormPage.isDefaultCheckboxNotSelected();
        boolean isDefaultCheckboxSelected = webFormPage.isDefaultCheckboxSelected();

        Assertions.assertEquals(expectedDefaultCheckboxText, actualDefaultCheckboxText, "Значения должны совпадать");
        Assertions.assertFalse(isDefaultCheckboxNotSelected, "Checkbox must not be checked");
        Assertions.assertTrue(isDefaultCheckboxSelected, "Checkbox must be checked");
    }

    @DisplayName("Проверка Checked Radio")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findCheckedRadioTest() {
        String expectedCheckedRadioText = "Checked radio";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualCheckedRadioText = webFormPage.getCheckedRadioText();
        boolean isCheckedRadioSelected = webFormPage.isCheckedRadioSelected();

        Assertions.assertEquals(expectedCheckedRadioText, actualCheckedRadioText, "Значения должны совпадать");
        Assertions.assertTrue(isCheckedRadioSelected, "Radio must not be checked");
    }

    @DisplayName("Проверка Default Radio")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDefaultRadioTest() {
        String expectedDefaultRadioText = "Default radio";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualDefaultRadioText = webFormPage.getDefaultRadioText();
        boolean isDefaultRadioSelected = webFormPage.isDefaultRadioSelected();

        Assertions.assertFalse(isDefaultRadioSelected, "Radio must not be selected");
        Assertions.assertEquals(expectedDefaultRadioText, actualDefaultRadioText, "Значения должны совпадать");
    }

    @DisplayName("Проверка Color Picker")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findColorPickerTest() {
        String expectedColorPickerText = "Color picker";
        String blueColor = "#0000FF";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualColorPickerText = webFormPage.getColorPickerText();
        String initialColorPickerValue = webFormPage.getColorPickerValue();
        webFormPage.chooseColor(blueColor);
        String newColorPickerValue = webFormPage.getColorPickerValue();

        Assertions.assertEquals(expectedColorPickerText, actualColorPickerText, "Значения должны совпадать");
        Assertions.assertNotEquals(initialColorPickerValue, newColorPickerValue, "Цвет должен был измениться на синий");
    }

    @DisplayName("Проверка Date Picker")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDatePickerTest() {
        String expectedDatePickerText = "Date picker";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualDatePickerText = webFormPage.getDatePickerText();

        Assertions.assertEquals(expectedDatePickerText, actualDatePickerText, "Значения должны совпадать");

        String pattern = "MM/dd/yyyy";
        String currentDate = webFormPage.dateForDatePicker(pattern);

        webFormPage.setDateForDatePicker(currentDate);
        String actualDatePickerValue = webFormPage.getDatePickerValue();

        Assertions.assertEquals(currentDate, actualDatePickerValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка Example range")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findExampleRangeTest() {
        String expectedExampleRangeText = "Example range";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualExampleRangeText = webFormPage.getExampleRangeText();

        Assertions.assertEquals(expectedExampleRangeText, actualExampleRangeText, "Значения должны совпадать");

        int rangeToMoveX = 60;
        int rangeToMoveY = 0;

        String initExampleRangeValue = webFormPage.getExampleRangeValue();
        webFormPage.moveExampleRangeWithMouse(rangeToMoveX, rangeToMoveY);
        String newExampleRangeValue = webFormPage.getExampleRangeValue();

        Assertions.assertNotEquals(initExampleRangeValue, newExampleRangeValue, "Положение ползунка должно было измениться");

        webFormPage.moveExampleRangeToRightWithKeys();
        String changedPositionOfExampleRange = webFormPage.getExampleRangeValue();

        Assertions.assertNotEquals(newExampleRangeValue, changedPositionOfExampleRange, "Положение ползунка должно было сместиться еще правее");
    }

    @DisplayName("Проверка Submit")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findSubmitTest() {
        String expectedSubmitButtonText = "Submit";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualSubmitButtonText = webFormPage.submitButtonText();

        Assertions.assertEquals(expectedSubmitButtonText, actualSubmitButtonText, "Значения должны совпадать");

        webFormPage.clickSubmitButton();
        String actualURL = webFormPage.getCurrentURL();
        String expectedURL = "https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=";

        Assertions.assertEquals(expectedURL, actualURL, "Значения должны совпадать");

        String expectedSubtitleText = "Form submitted";
        String actualSubtitleText = webFormPage.getSubtitleText();

        Assertions.assertEquals(expectedSubtitleText, actualSubtitleText, "Значения должны совпадать");
    }

    @DisplayName("Проверка FileInput")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findFileInputTest() throws RuntimeException, InterruptedException {
        String expectedFileInputText = "File input";

        WebFormPage webFormPage = new WebFormPage(driver);
        webFormPage.open();
        String actualFileInputText = webFormPage.getFileInputText();

        Assertions.assertEquals(expectedFileInputText, actualFileInputText, "Значения должны совпадать");

        String fileName = "try.txt";

        webFormPage.loadFileIntoFileInput(fileName);

        webFormPage.clickSubmitButton();
        Thread.sleep(3000);
        String url = webFormPage.getCurrentURL();
        System.out.println(url);
        boolean isContains = webFormPage.isContainsFileNameInURL(fileName);

        Assertions.assertTrue(isContains, "Названия файла нет в URL!");
    }
}
