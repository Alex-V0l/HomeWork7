package FactoryPOMPages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseFactoryPage {
    WebDriver driver;

    @FindBy(className = "display-6")
            private WebElement Subtitle;


    public BaseFactoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("getting current URL")
    public String getCurrentURL (){
        return driver.getCurrentUrl();
    }

    @Step ("get subtitle")
    public String getSubtitleText(){
        return Subtitle.getText();
    }






}
