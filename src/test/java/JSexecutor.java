import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class JSexecutor {
    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {

        if(browser.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else {
            throw new Exception("Browser is not correct");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void hover() {

        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement practice_magic = driver.findElement(By.cssSelector("#container > ul > li:nth-child(3)"));

        //move mouse to the left side of element with Actions class
        action.moveToElement(practice_magic).perform();

        //delete with selenium Actions class
        // action.moveToElement(practice_magic, -(int) (practice_magic.getSize().getWidth() / 2.0), 0).click().perform();

        //delete with JavascriptExecutor and remove()
        js.executeScript("arguments[0].remove()", practice_magic);

    }

    @Test
    public void scrollTest(){
        //this link will automatically scroll to the element with id zone2
//        driver.get("http://webdriveruniversity.com/Scrolling/index.html#zone2");

        driver.get("http://webdriveruniversity.com/Scrolling/index.html");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement entrie_element = driver.findElement(By.id("zone2"));
        String entrie_text;

        js.executeScript("arguments[0].scrollIntoView();", entrie_element);

        entrie_text = js.executeScript("return arguments[0].innerText.includes(\"Entries\");", entrie_element).toString();

        if(entrie_text.equals("true"))
            System.out.println("Entries text contains Entries");
        else
            System.out.println("Entries text doesn't contain Entries");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
