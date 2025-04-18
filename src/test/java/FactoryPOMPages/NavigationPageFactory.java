package FactoryPOMPages;

import components.FooterComponent;
import components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Constants.Constants.BASE_URL;


public class NavigationPageFactory extends BaseFactoryPage {

    public static final String NAVIGATION_URL = BASE_URL + "navigation1.html";
    HeaderComponent header;
    FooterComponent footer;

    @FindBy(className = "lead")
            private WebElement paragraphText;

    @FindBy(xpath = "//a[@class='page-link' and text()='Previous']")
            private WebElement previousButton;

    @FindBy(xpath = "//a[@class='page-link' and text()='Next']")
            private WebElement nextButton;

    @FindBy(xpath = "//a[@class='page-link' and text()='2']")
            private WebElement twoButton;

    @FindBy(xpath = "//a[@class='page-link' and text()='3']")
            private WebElement threeButton;

    public NavigationPageFactory(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("get Header")
    public HeaderComponent getHeaderComponent(){
        return header;
    }

    @Step("get footer")
    public FooterComponent getFooterComponent(){
        return footer;
    }

    @Step("open navigation page")
    public void open() {
        driver.get(NAVIGATION_URL);
    }

    @Step("find PREVIOUS button and check its disabled status")
    public boolean isPreviousButtonDisabled(){
        return previousButton.findElement(By.xpath("./..")).getDomAttribute("class").contains("disabled");
    }

    @Step("get text of paragraph that represented in the current navigation page")
    public String getParagraphText(){
        return paragraphText.getText();
    }

    @Step("get info about to what page NEXT button would lead")
    public String getNextButtonLinkInfo(){
        return nextButton.getDomAttribute("href");
    }

    @Step("click to button 2")
    public void clickTwoButton(){
        twoButton.click();
    }

    @Step("get info about to what page PREVIOUS button would lead")
    public String getPreviousButtonLinkInfo(){
        return previousButton.getDomAttribute("href");
    }

    @Step("click to button 3")
    public void clickThreeButton(){
        threeButton.click();
    }

    @Step("find NEXT button and check its disabled status")
    public boolean isNextButtonDisabled(){
        return nextButton.findElement(By.xpath("./..")).getDomAttribute("class").contains("disabled");
    }

    @Step("click to NEXT button")
    public void clickNextButton(){
        nextButton.click();
    }

    @Step("click to PREVIOUS button")
    public void clickPreviousButton(){
        previousButton.click();
    }
}
