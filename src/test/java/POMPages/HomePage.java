//package POMPages;
//import io.qameta.allure.Step;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import java.util.List;
//
//import static Constants.Constants.BASE_URL;
//
//public class HomePage extends BasePage{
//    By chapterTitle = By.cssSelector("h5.card-title");
//    By WebFormLinkLocator = By.xpath("//a[@href='web-form.html']");
//    By NavigationLinkLocator = By.xpath("//a[@href='navigation1.html']");
//
//    public HomePage(WebDriver driver) {
//        super(driver);
//    }
//
//    @Step("open homepage")
//    public void open() {
//        driver.get(BASE_URL);
//    }
//
//    @Step("get main title")
//    public String getTitle() {
//        return driver.getTitle();
//    }
//
//    @Step("get collection of all chapters on homepage")
//    public List<WebElement> findChapters() {
//        return driver.findElements(chapterTitle);
//    }
//
//    @Step("get collection of all links of new pages on home page and click on them")
//    public int getQuantityOfLinks(int quantityOfLinks, List<WebElement> findChapters) {
//        for (WebElement chapter : findChapters) {
//            List<WebElement> links = chapter.findElements(By.xpath("./../a"));
//            quantityOfLinks += links.size();
//            for (WebElement link : links) {
//                link.click();
//                driver.navigate().back();
//            }
//        }
//        return quantityOfLinks;
//    }
//
//    @Step("click to link that leads to web form page")
//    public WebFormPage openWebFormPage() {
//        driver.findElement(WebFormLinkLocator).click();
//        return new WebFormPage(driver);
//    }
//
//    @Step("click to link that leads to navigation page")
//    public NavigationPage openNavigationPage() {
//        driver.findElement(NavigationLinkLocator).click();
//        return new NavigationPage(driver);
//    }
//}
