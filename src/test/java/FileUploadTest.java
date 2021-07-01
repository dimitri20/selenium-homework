import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;


import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileUploadTest {
    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {

        if(browser.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        }
        else if(browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(new FirefoxOptions().addArguments("headless"));
        }
        else {
            throw new Exception("Browser is not correct");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void filUpload() throws StaleElementReferenceException {
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement file_upload = driver.findElement(By.id("file-upload"));

        //upload file
        try {
            File file = new File("assets/file.jpg");
            file_upload.sendKeys(file.getAbsolutePath());
            file_upload.submit();
            driver.navigate().back();

        } catch (Exception e){
            System.out.println(e);
        }

        //cause StaleElementReferenceException
        try {
            file_upload.click();
            driver.navigate().refresh();
        } catch (StaleElementReferenceException e){
            System.out.println("StaleElementReferenceException has been invoked and handled successfully");
        }
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
