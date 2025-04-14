package POM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Constants.Constants.BASE_URL;

public class HomePage extends BasePage{
    By chapterTitle = By.cssSelector("h5.card-title");
    By WebFormLinkLocator = By.xpath("//a[@href='web-form.html']");
    By NavigationLinkLocator = By.xpath("//a[@href='navigation1.html']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findChapters() {
        return driver.findElements(chapterTitle);
    }

    public int getQuantityOfLinks(int quantityOfLinks, List<WebElement> findChapters) {
        for (WebElement chapter : findChapters) {
            List<WebElement> links = chapter.findElements(By.xpath("./../a"));
            quantityOfLinks += links.size();
            for (WebElement link : links) {
                link.click();
                driver.navigate().back();
            }
        }
        return quantityOfLinks;
    }

    public WebFormPage openWebFormPage() {
        driver.findElement(WebFormLinkLocator).click();
        return new WebFormPage(driver);
    }

    public NavigationPage openNavigationPage() {
        driver.findElement(NavigationLinkLocator).click();
        return new NavigationPage(driver);
    }
}
