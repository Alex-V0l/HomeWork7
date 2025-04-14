package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    WebDriver driver;
    By SubTitle = By.className("display-6");
    By returnToIndexLinkLocator = By.xpath("//a[contains(@href, 'index')]");
    By copyrightTextLocator = By.xpath("//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']");
    By developersPageLinkLocator = By.xpath("//a[@href='https://bonigarcia.dev/']");
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentURL (){
        return driver.getCurrentUrl();
    }

    public String getSubtitleText(){
        return driver.findElement(SubTitle).getText();
    }

    public void returnToIndex(){
        driver.findElement(returnToIndexLinkLocator).click();
    }

    public String getCopyrightText(){
        return driver.findElement(copyrightTextLocator).getText();
    }

    public void getDevelopersPageLink(){
        WebElement devpglink = driver.findElement(developersPageLinkLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", devpglink);
    }
}
