package Chapter4Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class UnitWebStorageTest {
    public Map<String, String> getLocalStorage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getLocalStorageScript = "var items = {}; " +
                "for (var i = 0; i < window.localStorage.length; i++) { " +
                "   var key = window.localStorage.key(i); " +
                "   items[key] = window.localStorage.getItem(key); " +
                "} " +
                "return items;";
        return (Map<String, String>) js.executeScript(getLocalStorageScript);
    }

    public Map<String, String> getSessionStorage(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getSessionStorageScript = "var items ={};" +
                "for (var i= 0; I < window.sessionStorage.length; i++) {" +
                "var key = window.sessionStorage.key(i); " +
                "item[key] = window.sessionStorage.getItem(key); "+
                "} " +
                "return items: ";
        return (Map<String, String>) js.executeScript(getSessionStorageScript);
    }

    public String getFormattedStorage(String key, String value) {
        return String.format("{\"%s\":\"%s\"}", key, value);
    }
}
