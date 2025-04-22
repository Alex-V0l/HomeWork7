package FluentPages;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FluentBasePage {
    WebDriver driver;

    @FindBy(id = "cookies-list")
    private WebElement cookiesList;

    public FluentBasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickButton(WebElement Button){
        Button.click();
    }

    public String getCookieFromCookieList(int numberOfCookie){
        String[] arrayOfCookies = cookiesList.getText().split("\n");
        return arrayOfCookies[numberOfCookie];
    }

    public void addNewCookie(String nameAndValue) {
        String[] nameAndValueAsArray = nameAndValue.split("=", 2);
        if (nameAndValueAsArray.length == 2) {
            String name = nameAndValueAsArray[0].trim();
            String value = nameAndValueAsArray[1].trim();
            Cookie newCookie = new Cookie(name, value);
            WebDriver.Options options = driver.manage();
            options.addCookie(newCookie);
        }
    }

    public int getAmountOfCookiesOnPage(){
        WebDriver.Options options = driver.manage();
        return options.getCookies().size();
    }

    public void deleteCookie(String nameAndValue){
        String[] nameAndValueAsArray = nameAndValue.split("=", 2);
        if (nameAndValueAsArray.length == 2) {
            String name = nameAndValueAsArray[0].trim();
            WebDriver.Options options = driver.manage();
            options.deleteCookieNamed(name);
        }
    }
}
