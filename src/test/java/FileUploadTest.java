import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest {
    WebDriver driver;

    public FileUploadTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
