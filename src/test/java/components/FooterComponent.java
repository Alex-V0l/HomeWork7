package components;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterComponent {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']")
    private WebElement copyRightText;

    @FindBy(xpath = "//a[@href='https://bonigarcia.dev/']")
    private WebElement developersPageLink;

    public FooterComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }

    @Step("get text of copyright part")
    public String getCopyrightText(){
        return copyRightText.getText();
    }

    @Step("click to link, that leads to bonigarcia")
    public void clickOnDevelopersPageLink(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", developersPageLink);
    }
}
