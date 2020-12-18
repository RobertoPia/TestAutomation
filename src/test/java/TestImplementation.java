import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TestImplementation extends JsPageElementFinder {
//  main function does not gets executed
/*
    public static void main(String[] args) {
        System.out.println("main function");
    }
*/

//  constructor gets executed, but is executed twice when there are two or more @Test to run...
/*
    TestImplementation() {
        System.out.println("constructor");
    }
*/

    private static ChromeDriver driver;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
    }

    /*In case of all tests uses the browser*/
//    @BeforeEach
//    public void setup() {
//        driver = new ChromeDriver();
//    }

    @AfterAll
    public static void teardown() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("BuyAMac")
    public void verifyPageTitle() throws InterruptedException {
        driver = new ChromeDriver(); /*In case only this test uses the browser*/
        driver.get("http://apple.com");
        Dimension d = new Dimension(1200,900);
        driver.manage().window().setSize(d);
        System.out.println("\nPage title : " + driver.getTitle()+"\n");

        Thread.sleep(2000);

        WebElement element = driver.findElement(By.linkText("Mac"));
        element.click();

        Thread.sleep(2000);

        System.out.println("\nPage URL : " + driver.getCurrentUrl()+"\n");
        System.out.println("\nPage title : " + driver.getTitle()+"\n");
        assertThat(driver.getTitle(), containsString("Apple"));

        JavascriptExecutor js = (JavascriptExecutor)driver;

        element = driver.findElement(By.className("chapternav-link"));
        element.click();

        Thread.sleep(2000);

        element = driver.findElement(By.linkText("Buy"));
        element.click();

        Thread.sleep(2000);

        element = driver.findElement(By.name("proceed"));
        element.click();

        Thread.sleep(2000);

        element = driver.findElement(By.id("memory__dummy_z124_065_c99n_2"));
        js.executeScript("arguments[0].focus();", element);
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", element);

        Thread.sleep(2000);

        element = driver.findElement(By.id("hard_drivesolid_state_drive__dummy_z124_065_c99v_3"));
        js.executeScript("arguments[0].focus();", element);
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", element);

        Thread.sleep(2000);

        element = driver.findElement(By.name("add-to-cart"));
        element.click();

        Thread.sleep(2000);

        element = driver.findElement(By.id("MagicMouse2GridGroup_0_label"));
        js.executeScript("arguments[0].focus();", element);
        Thread.sleep(2000);
        element.click();

        Thread.sleep(2000);

//        List<WebElement> elements = driver.findElements(By.tagName("button"));
//        for (int i = 0; i < elements.size(); i++){
//            String value = (String) js.executeScript("return arguments[0].outerHTML || '--';", elements.get(i));
//            if(value.contains("MRME2LL/A") || value.contains("MLA02LL/A")) {
//                elements.get(i).click(); //Selenium
//              //  js.executeScript("arguments[0].click();", elements.get(i)); //JavaScript in case Selenium doesn't works
//                break;
//            }
//        }
        element =  JsPageElementFinder(driver, "button", "MLA02LL/A");
        if(element != null) {
            element.click();
        } else {
            element =  JsPageElementFinder(driver, "button", "MRME2LL/A");
            if(element != null) {
                element.click();
            }
        }

        Thread.sleep(2000);

        element = driver.findElement(By.className("form-submit-btn"));
        element.click();

        Thread.sleep(2000);

        element = driver.findElement(By.id("shoppingCart.actions.checkout"));
        js.executeScript("arguments[0].focus();", element);
        Thread.sleep(2000);
        element.click();

        Thread.sleep(2000);
    }


    @Test
    @Tag("Demo")
    public void matchTest() {
        System.out.println("\nmatchTest\n");
        assertThat("abc", containsString("c"));
    }


    @Test
    @Tag("Demo")
    public void matchTest2() {
        System.out.println("\nmatchTest-2\n");
        assertThat("abc", containsString("b"));
    }


    @Test
    @Tag("Demo")
    public void matchTest3() {
        System.out.println("\nmatchTest-3\n");
        assertThat("abc", containsString("b"));
    }
}
