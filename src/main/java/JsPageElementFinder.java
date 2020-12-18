import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class JsPageElementFinder {
/*
    Scans the page searching for elements with tag name "tagName"
    read the raw html code for each element
    check if the raw html code contains "textToFind"
    return the first element found or null if nothing found
    
    usage : WebElement element = JsPageElementFinder(driver, "button", "Click here");
*/
    public static WebElement JsPageElementFinder(WebDriver driver, String tagName, String textToFind) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        for (int i = 0; i < elements.size(); i++){
            String value = (String) js.executeScript("return arguments[0].outerHTML || '--';", elements.get(i));
            if(value.contains(textToFind)) {
                return elements.get(i);
            }
        }
        return null;
    }
}
