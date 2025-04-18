package POMPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Constants.Constants.BASE_URL;


public class NavigationPage extends BasePage{

    public static final String NAVIGATION_URL = BASE_URL + "navigation1.html";

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

    @Step("open navigation page")
    public void open() {
        driver.get(NAVIGATION_URL);
    }

    @Step("find PREVIOUS button and check its disabled status")
    public boolean isPreviousButtonDisabled(){
        WebElement previousButton = driver.findElement(previousButtonFirstPageLocator);
        WebElement parentLi = previousButton.findElement(By.xpath("./.."));
        return parentLi.getDomAttribute("class").contains("disabled");
    }

    @Step("get text of paragraph that represented in the current navigation page")
    public String getParagraphText(){
        return driver.findElement(ParagraphTextLocator).getText();
    }

    @Step("get info about to what page NEXT button would lead")
    public String getNextButtonLinkInfo(){
        return driver.findElement(nextButtonFirstPageLocator).getDomAttribute("href");
    }

    @Step("click to button 2")
    public void clickTwoButton(){
        driver.findElement(twoButtonElementLocator).click();
    }

    @Step("get info about to what page PREVIOUS button would lead")
    public String getPreviousButtonLinkInfo(){
        return driver.findElement(previousButtonElement).getDomAttribute("href");
    }

    @Step("click to button 3")
    public void clickThreeButton(){
        driver.findElement(threeButtonElementLocator).click();
    }

    @Step("find NEXT button and check its disabled status")
    public boolean isNextButtonDisabled(){
        WebElement NextButton = driver.findElement(nextButtonLastPageLocator);
        WebElement parentLi = NextButton.findElement(By.xpath("./.."));
        return parentLi.getDomAttribute("class").contains("disabled");
    }

    @Step("click to NEXT button")
    public void clickNextButton(){
        driver.findElement(nextButtonFirstPageLocator).click();
    }

    @Step("click to PREVIOUS button")
    public void clickPreviousButton(){
        driver.findElement(previousButtonElement).click();
    }
}
