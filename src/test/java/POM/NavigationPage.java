package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Constants.Constants.NAVIGATION_URL;


public class NavigationPage extends BasePage{

    By ParagraphTextLocator = By.className("lead");
    By previousButtonFirstPageLocator = By.xpath("//li[@class='page-item disabled']/a");
    By nextButtonFirstPageLocator = By.xpath("//a[@class='page-link' and text()='Next']");
    By twoButtonElementLocator = By.xpath("//a[@href='navigation2.html']");
    By previousButtonElement = By.xpath("//a[@class='page-link' and text()='Previous']");
    By threeButtonElementLocator = By.xpath("//a[@href='navigation3.html']");
    By nextButtonLastPageLocator = By.xpath("//li[@class='page-item disabled']/a");


    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(NAVIGATION_URL);
    }

    public boolean isPreviousButtonDisabled(){
        WebElement previousButton = driver.findElement(previousButtonFirstPageLocator);
        WebElement parentLi = previousButton.findElement(By.xpath("./.."));
        return parentLi.getDomAttribute("class").contains("disabled");
    }

    public String getParagraphText(){
        return driver.findElement(ParagraphTextLocator).getText();
    }

    public String getNextButtonLinkInfo(){
        return driver.findElement(nextButtonFirstPageLocator).getDomAttribute("href");
    }

    public void clickTwoButton(){
        driver.findElement(twoButtonElementLocator).click();
    }

    public String getPreviousButtonLinkInfo(){
        return driver.findElement(previousButtonElement).getDomAttribute("href");
    }

    public void clickThreeButton(){
        driver.findElement(threeButtonElementLocator).click();
    }

    public boolean isNextButtonDisabled(){
        WebElement NextButton = driver.findElement(nextButtonLastPageLocator);
        WebElement parentLi = NextButton.findElement(By.xpath("./.."));
        return parentLi.getDomAttribute("class").contains("disabled");
    }

    public void clickNextButton(){
        driver.findElement(nextButtonFirstPageLocator).click();
    }

    public void clickPreviousButton(){
        driver.findElement(previousButtonElement).click();
    }
}
