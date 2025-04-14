package POM;

import Chapter3Tests.WebFormTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Constants.Constants.WEB_FORM_URL;


public class WebFormPage extends BasePage {

    By textInputText = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Text input']");
    By textForm = By.id("my-text-id");
    By passwordTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Password']");
    By passwordForm = By.name("my-password");
    By textAreaTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Textarea']");
    By textAreaForm = By.name("my-textarea");
    By disabledInputLocatorText = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Disabled input']");
    By disabledInput = By.name("my-disabled");
    By readonlyInputTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Readonly input']");
    By readonlyInputLocator = By.name("my-readonly");
    By dropDownTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (select)']");
    By dropDownSelect = By.className("form-select");
    By dropDownDataListLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (datalist)']");
    By dropDownDataList = By.name("my-datalist");
    By checkedCheckBoxTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked checkbox']");
    By checkedCheckboxLocator = By.id("my-check-1");
    By defaultCheckboxTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default checkbox']");
    By getDefaultCheckboxLocator = By.id("my-check-2");
    By checkedRadioTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked radio']");
    By checkedRadioLocator = By.id("my-radio-1");
    By defaultRadioTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default radio']");
    By defaultRadioLocator = By.id("my-radio-2");
    By colorPickerTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Color picker']");
    By colorPickerLocator = By.name("my-colors");
    By exampleRangeTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Example range']");
    By exampleRangeElement = By.name("my-range");
    By submitButtonTextLocator = By.xpath("//button[(text())='Submit']");
    By submitButtonElement = By.tagName("button");
    By fileInputTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='File input']");
    By fileInputElement = By.name("my-file");
    By datePickerTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Date picker']");
    By datePickerElement = By.name("my-date");


    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(WEB_FORM_URL);
    }

    public String getTextInput() {
        return driver.findElement(textInputText).getText();
    }

        public void sendTextToTextForm(String textForTextForm) {
        driver.findElement(textForm).sendKeys(textForTextForm);
    }

    public String getValueOfTextForm() {
        return driver.findElement(textForm).getDomProperty("value");
    }

    public String getPasswordName() {
        return driver.findElement(passwordTextLocator).getText();
    }

    public void sendTextToPasswordForm(String textForPasswordForm) {
        driver.findElement(passwordForm).sendKeys(textForPasswordForm);
    }

    public String getValueOfPasswordForm() {
        return driver.findElement(passwordForm).getDomProperty("value");
    }

    public String getTextAreaText() {
        return driver.findElement(textAreaTextLocator).getText();
    }

    public void sendTextToTextAreaForm(String textForTextAreaForm) {
        driver.findElement(textAreaForm).sendKeys(textForTextAreaForm);
    }

    public String getValueOfTextAreaForm() {
        return driver.findElement(textAreaForm).getDomProperty("value");
    }

    public String getDisabledInputText() {
        return driver.findElement(disabledInputLocatorText).getText();
    }


    public boolean isDisabledInputEnable() {
        return driver.findElement(disabledInput).isEnabled();
    }

    public void sendTextToDisabledInput(String textForDisabledInput) {
        driver.findElement(disabledInput).sendKeys(textForDisabledInput);
    }

    public String getReadonlyInputText() {
        return driver.findElement(readonlyInputTextLocator).getText();
    }

    public boolean isReadonly() {
        return driver.findElement(readonlyInputLocator).getDomProperty("readOnly") != null;
    }

    public String getDropDownText() {
        WebElement dropDownTextElement = driver.findElement(dropDownTextLocator);
        return dropDownTextElement.getText().split("\n")[0].trim();
    }

    public String chooseOptionFromDropDownSelect(String valueNumber) {
        WebElement dropDownElement = driver.findElement(dropDownSelect);
        Select select = new Select(dropDownElement);
        select.selectByValue(valueNumber);
        return select.getFirstSelectedOption().getText();
    }

    public boolean isOptionSelected(String valueNumber) {
        WebElement dropDownElement = driver.findElement(dropDownSelect);
        Select select = new Select(dropDownElement);
        select.selectByValue(valueNumber);
        return select.getFirstSelectedOption().isSelected();
    }

    public String getDropDownDataListText() {
        return driver.findElement(dropDownDataListLocator).getText();
    }

    public void sendTextToDropDownDataList(String textToSend) {
        driver.findElement(dropDownDataList).sendKeys(textToSend);
    }

    public String getTextFromDropDownDataList() {
        return driver.findElement(dropDownDataList).getDomProperty("value");
    }

    public String getCheckedCheckboxText() {
        return driver.findElement(checkedCheckBoxTextLocator).getText();
    }

    public boolean isCheckboxChecked() {
        WebElement checkedCheckbox = driver.findElement(checkedCheckboxLocator);
        return checkedCheckbox.isSelected();
    }

    public boolean isCheckboxIsNotChecked() {
        WebElement checkedCheckbox = driver.findElement(checkedCheckboxLocator);
        checkedCheckbox.click();
        return checkedCheckbox.isSelected();
    }

    public String getDefaultCheckboxText() {
        return driver.findElement(defaultCheckboxTextLocator).getText();
    }

    public boolean isDefaultCheckboxNotSelected() {
        return driver.findElement(getDefaultCheckboxLocator).isSelected();
    }

    public boolean isDefaultCheckboxSelected() {
        WebElement defaultCheckbox = driver.findElement(getDefaultCheckboxLocator);
        defaultCheckbox.click();
        return defaultCheckbox.isSelected();
    }

    public String getCheckedRadioText() {
        return driver.findElement(checkedRadioTextLocator).getText();
    }

    public boolean isCheckedRadioSelected() {
        return driver.findElement(checkedRadioLocator).isSelected();
    }

    public String getDefaultRadioText() {
        return driver.findElement(defaultRadioTextLocator).getText();
    }

    public boolean isDefaultRadioSelected() {
        return driver.findElement(defaultRadioLocator).isSelected();
    }

    public String getColorPickerText() {
        return driver.findElement(colorPickerTextLocator).getText();
    }

    public String getColorPickerValue() {
        return driver.findElement(colorPickerLocator).getDomAttribute("value");
    }

    public void chooseColor(String color) {
        WebElement colorPicker = driver.findElement(colorPickerLocator);
        String script =
                "arguments[0].setAttribute('value', arguments[1]);" + "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, colorPicker, color);
    }

    public String getExampleRangeText() {
        return driver.findElement(exampleRangeTextLocator).getText();
    }

    public String getExampleRangeValue() {
        return driver.findElement(exampleRangeElement).getDomProperty("value");
    }

    public void moveExampleRangeWithMouse(int x, int y) {
        WebElement exampleRange = driver.findElement(exampleRangeElement);
        exampleRange.click();
        Actions actions = new Actions(driver);
        actions.clickAndHold(exampleRange)
                .moveByOffset(x, y).release().perform();
    }

    public void moveExampleRangeToRightWithKeys() {
        WebElement exampleRange = driver.findElement(exampleRangeElement);
        exampleRange.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_RIGHT);
    }

    public String submitButtonText(){
       return driver.findElement(submitButtonTextLocator).getText();
    }

    public void clickSubmitButton(){
        driver.findElement(submitButtonElement).click();
    }

    public String getFileInputText(){
        return driver.findElement(fileInputTextLocator).getText();
    }

    public void loadFileIntoFileInput(String fileName) throws InterruptedException {
        URL url = WebFormTest.class.getClassLoader().getResource(fileName);
        String absolutePath = null;
        if (url != null) {
            absolutePath = new File(url.getPath()).getAbsolutePath();
        } else {
            throw new InterruptedException("Ресурс не найден");
        }
        WebElement fileUpload = driver.findElement(fileInputElement);
        fileUpload.sendKeys(absolutePath);
    }

    public boolean isContainsFileNameInURL(String fileName) throws RuntimeException {
        WebFormPage webFormPage = new WebFormPage(driver);
        if (webFormPage.getCurrentURL().contains(fileName)) {
            return true;
        } else {
            throw new RuntimeException("Link doesn't contain file name");
        }
    }

    public String getDatePickerText (){

        return driver.findElement(datePickerTextLocator).getText();
    }

    public String dateForDatePicker(String pattern){

        return new SimpleDateFormat(pattern).format(new Date());
    }

    public void setDateForDatePicker(String date){

        driver.findElement(datePickerElement).sendKeys(date);
    }

    public String getDatePickerValue(){

        return driver.findElement(datePickerElement).getDomProperty("value");
    }

}

