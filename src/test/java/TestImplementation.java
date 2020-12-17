import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TestImplementation {
//  main function does not gets executed
/*
    public static void main(String[] args) {
        TestImplementation myObj = new TestImplementation();
        System.out.println("main function");
    }
*/

//  constructor gets executed, but is executed twice when there are two or more @Test to run...
/*
    TestImplementation() {
        System.out.println("constructor");
    }
*/

    private static WebDriver driver;

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
    @Tag("Browser")
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
    }


    @Test
    @Tag("demo")
    public void matchTest() {
        System.out.println("\nmatchTest\n");
        assertThat("abc", containsString("c"));
    }


    @Test
    @Tag("demo")
    public void matchTest2() {
        System.out.println("\nmatchTest-2\n");
        assertThat("abc", containsString("a"));
    }


    @Test
    @Tag("demo")
    public void matchTest3() {
        System.out.println("\nmatchTest-3\n");
        assertThat("abc", containsString("b"));
    }
}
