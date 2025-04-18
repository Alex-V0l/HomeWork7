package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    private WebDriver driver;

    @FindBy(className = "display-4")
    private WebElement title;

    @FindBy(xpath = "//h5[text()='Practice site']")
    private WebElement subTitle;

    @FindBy(className = "img-fluid")
    private WebElement logo;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitleText() {
        return title.getText();
    }

    public String getHeaderSubTitleText() {
        return subTitle.getText();
    }

    public void clickLogo() {
        logo.click();
    }
}
