//package POMPages;
//
//import POMTests.WebFormTest;
//import io.qameta.allure.Step;
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;
//
//
//import java.io.File;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static Constants.Constants.BASE_URL;
//
//public class WebFormPage extends BasePage {
//
//    public static final String WEB_FORM_URL = BASE_URL + "web-form.html";
//
//    By textInputText = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Text input']");
//    By textForm = By.id("my-text-id");
//    By passwordTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Password']");
//    By passwordForm = By.name("my-password");
//    By textAreaTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Textarea']");
//    By textAreaForm = By.name("my-textarea");
//    By disabledInputLocatorText = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Disabled input']");
//    By disabledInput = By.name("my-disabled");
//    By readonlyInputTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Readonly input']");
//    By readonlyInputLocator = By.name("my-readonly");
//    By dropDownTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (select)']");
//    By dropDownSelect = By.className("form-select");
//    By dropDownDataListLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (datalist)']");
//    By dropDownDataList = By.name("my-datalist");
//    By checkedCheckBoxTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked checkbox']");
//    By checkedCheckboxLocator = By.id("my-check-1");
//    By defaultCheckboxTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default checkbox']");
//    By getDefaultCheckboxLocator = By.id("my-check-2");
//    By checkedRadioTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked radio']");
//    By checkedRadioLocator = By.id("my-radio-1");
//    By defaultRadioTextLocator = By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default radio']");
//    By defaultRadioLocator = By.id("my-radio-2");
//    By colorPickerTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Color picker']");
//    By colorPickerLocator = By.name("my-colors");
//    By exampleRangeTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Example range']");
//    By exampleRangeElement = By.name("my-range");
//    By submitButtonTextLocator = By.xpath("//button[(text())='Submit']");
//    By submitButtonElement = By.tagName("button");
//    By fileInputTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='File input']");
//    By fileInputElement = By.name("my-file");
//    By datePickerTextLocator = By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Date picker']");
//    By datePickerElement = By.name("my-date");
//
//
//    public WebFormPage(WebDriver driver) {
//        super(driver);
//    }
//
//    @Step("open web form page")
//    public void open() {
//        driver.get(WEB_FORM_URL);
//    }
//
//    @Step("get text from text input web-element")
//    public String getTextInput() {
//        return driver.findElement(textInputText).getText();
//    }
//
//    @Step("send text to text form web-element")
//    public void sendTextToTextForm(String textForTextForm) {
//    driver.findElement(textForm).sendKeys(textForTextForm);
//    }
//
//    @Step("get value from text input web-element")
//    public String getValueOfTextForm() {
//        return driver.findElement(textForm).getDomProperty("value");
//    }
//
//    @Step("get name of the password web-element")
//    public String getPasswordName() {
//        return driver.findElement(passwordTextLocator).getText();
//    }
//
//    @Step("send text to password web-element")
//    public void sendTextToPasswordForm(String textForPasswordForm) {
//        driver.findElement(passwordForm).sendKeys(textForPasswordForm);
//    }
//
//    @Step("get value of password web-element")
//    public String getValueOfPasswordForm() {
//        return driver.findElement(passwordForm).getDomProperty("value");
//    }
//
//    @Step("get name of text area web-element")
//    public String getTextAreaText() {
//        return driver.findElement(textAreaTextLocator).getText();
//    }
//
//    @Step("send text to text area web-element")
//    public void sendTextToTextAreaForm(String textForTextAreaForm) {
//        driver.findElement(textAreaForm).sendKeys(textForTextAreaForm);
//    }
//
//    @Step("get value of text area web-element")
//    public String getValueOfTextAreaForm() {
//        return driver.findElement(textAreaForm).getDomProperty("value");
//    }
//
//    @Step("get name of disabled input web-element")
//    public String getDisabledInputText() {
//        return driver.findElement(disabledInputLocatorText).getText();
//    }
//
//    @Step("check that disabled input has disabled status")
//    public boolean isDisabledInputEnable() {
//        return driver.findElement(disabledInput).isEnabled();
//    }
//
//    @Step("send text to disabled input web-element")
//    public void sendTextToDisabledInput(String textForDisabledInput) {
//        driver.findElement(disabledInput).sendKeys(textForDisabledInput);
//    }
//
//    @Step("get name of read only web-element")
//    public String getReadonlyInputText() {
//        return driver.findElement(readonlyInputTextLocator).getText();
//    }
//
//    @Step("check that read only web-element has readonly status")
//    public boolean isReadonly() {
//        return driver.findElement(readonlyInputLocator).getDomProperty("readOnly") != null;
//    }
//
//    @Step("get name of dropdown web-element")
//    public String getDropDownText() {
//        WebElement dropDownTextElement = driver.findElement(dropDownTextLocator);
//        return dropDownTextElement.getText().split("\n")[0].trim();
//    }
//
//    @Step("find, choose and return as string option from dropdown web-element")
//    public String chooseOptionFromDropDownSelect(String valueNumber) {
//        WebElement dropDownElement = driver.findElement(dropDownSelect);
//        Select select = new Select(dropDownElement);
//        select.selectByValue(valueNumber);
//        return select.getFirstSelectedOption().getText();
//    }
//
//    @Step("check if option from dropdown is selected")
//    public boolean isOptionSelected(String valueNumber) {
//        WebElement dropDownElement = driver.findElement(dropDownSelect);
//        Select select = new Select(dropDownElement);
//        select.selectByValue(valueNumber);
//        return select.getFirstSelectedOption().isSelected();
//    }
//
//    @Step("get name of dropdown datalist web-element")
//    public String getDropDownDataListText() {
//        return driver.findElement(dropDownDataListLocator).getText();
//    }
//
//    @Step("send text to dropdown datalist web-element")
//    public void sendTextToDropDownDataList(String textToSend) {
//        driver.findElement(dropDownDataList).sendKeys(textToSend);
//    }
//
//    @Step("get value of dropdown datalist web-element")
//    public String getTextFromDropDownDataList() {
//        return driver.findElement(dropDownDataList).getDomProperty("value");
//    }
//
//    @Step("get name of checked checkbox web-element")
//    public String getCheckedCheckboxText() {
//        return driver.findElement(checkedCheckBoxTextLocator).getText();
//    }
//
//    @Step("check if checked checkbox web-element is selected")
//    public boolean isCheckboxChecked() {
//        WebElement checkedCheckbox = driver.findElement(checkedCheckboxLocator);
//        return checkedCheckbox.isSelected();
//    }
//
//    @Step("check if checked checkbox web-element is selected not after click")
//    public boolean isCheckboxIsNotChecked() {
//        WebElement checkedCheckbox = driver.findElement(checkedCheckboxLocator);
//        checkedCheckbox.click();
//        return checkedCheckbox.isSelected();
//    }
//
//    @Step("get name of default checkbox web-element")
//    public String getDefaultCheckboxText() {
//        return driver.findElement(defaultCheckboxTextLocator).getText();
//    }
//
//    @Step("check is default checkbox web-element is selected")
//    public boolean isDefaultCheckboxNotSelected() {
//        return driver.findElement(getDefaultCheckboxLocator).isSelected();
//    }
//
//    @Step("check if default checkbox web-element is not selected after click")
//    public boolean isDefaultCheckboxSelected() {
//        WebElement defaultCheckbox = driver.findElement(getDefaultCheckboxLocator);
//        defaultCheckbox.click();
//        return defaultCheckbox.isSelected();
//    }
//
//    @Step("get name of checked radio web-element")
//    public String getCheckedRadioText() {
//        return driver.findElement(checkedRadioTextLocator).getText();
//    }
//
//    @Step("check if checked radio web-element is selected")
//    public boolean isCheckedRadioSelected() {
//        return driver.findElement(checkedRadioLocator).isSelected();
//    }
//
//    @Step("get name of default radio web-element")
//    public String getDefaultRadioText() {
//        return driver.findElement(defaultRadioTextLocator).getText();
//    }
//
//    @Step("check if default radio web-element is selected")
//    public boolean isDefaultRadioSelected() {
//        return driver.findElement(defaultRadioLocator).isSelected();
//    }
//
//    @Step("get name of color picker web-element")
//    public String getColorPickerText() {
//        return driver.findElement(colorPickerTextLocator).getText();
//    }
//
//    @Step("get initial value of colour of color picker")
//    public String getColorPickerValue() {
//        return driver.findElement(colorPickerLocator).getDomAttribute("value");
//    }
//
//    @Step("find color picker web-element and change its colour to what is ni the string")
//    public void chooseColor(String color) {
//        WebElement colorPicker = driver.findElement(colorPickerLocator);
//        String script =
//                "arguments[0].setAttribute('value', arguments[1]);" + "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript(script, colorPicker, color);
//    }
//
//    @Step("get name of example range web-element")
//    public String getExampleRangeText() {
//        return driver.findElement(exampleRangeTextLocator).getText();
//    }
//
//    @Step("get initial value of example range web-element")
//    public String getExampleRangeValue() {
//        return driver.findElement(exampleRangeElement).getDomProperty("value");
//    }
//
//    @Step("find example range web-element, move mouse to it and move element to x and y value")
//    public void moveExampleRangeWithMouse(int x, int y) {
//        WebElement exampleRange = driver.findElement(exampleRangeElement);
//        exampleRange.click();
//        Actions actions = new Actions(driver);
//        actions.clickAndHold(exampleRange)
//                .moveByOffset(x, y).release().perform();
//    }
//
//    @Step("find example range web-element and move it once to right by arrow")
//    public void moveExampleRangeToRightWithKeys() {
//        WebElement exampleRange = driver.findElement(exampleRangeElement);
//        exampleRange.click();
//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.ARROW_RIGHT);
//    }
//
//    @Step("get name of submit button web-element")
//    public String submitButtonText(){
//       return driver.findElement(submitButtonTextLocator).getText();
//    }
//
//    @Step("click to submit button web-element")
//    public void clickSubmitButton(){
//        driver.findElement(submitButtonElement).click();
//    }
//
//    @Step("get name of file input web-element")
//    public String getFileInputText(){
//        return driver.findElement(fileInputTextLocator).getText();
//    }
//
//    @Step("create url for file to load, find file input web-element and send created url")
//    public void loadFileIntoFileInput(String fileName) throws InterruptedException {
//        URL url = WebFormTest.class.getClassLoader().getResource(fileName);
//        String absolutePath = null;
//        if (url != null) {
//            absolutePath = new File(url.getPath()).getAbsolutePath();
//        } else {
//            throw new InterruptedException("Ресурс не найден");
//        }
//        WebElement fileUpload = driver.findElement(fileInputElement);
//        fileUpload.sendKeys(absolutePath);
//    }
//
//    @Step("check that there is file's name in the link")
//    public boolean isContainsFileNameInURL(String fileName) throws RuntimeException {
//        WebFormPage webFormPage = new WebFormPage(driver);
//        if (webFormPage.getCurrentURL().contains(fileName)) {
//            return true;
//        } else {
//            throw new RuntimeException("Link doesn't contain file name");
//        }
//    }
//
//    @Step("get name of date picker web-element")
//    public String getDatePickerText (){
//        return driver.findElement(datePickerTextLocator).getText();
//    }
//
//    @Step("create current date and format it to given in string pattern")
//    public String dateForDatePicker(String pattern){
//        return new SimpleDateFormat(pattern).format(new Date());
//    }
//
//    @Step("send created date to date picker web-element")
//    public void setDateForDatePicker(String date){
//        driver.findElement(datePickerElement).sendKeys(date);
//    }
//
//    @Step("get value of date picker web-element")
//    public String getDatePickerValue(){
//        return driver.findElement(datePickerElement).getDomProperty("value");
//    }
//}
//
