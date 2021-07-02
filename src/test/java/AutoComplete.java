import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutoComplete {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void autoComplete(){
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("Automation");
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul[role='listbox']")));
        List<WebElement> options = driver.findElements(By.cssSelector("ul[role='listbox'] li"));

        WebElement lastElement = options.get(options.size() - 1);
        System.out.println("Last element of AutoComplete is: " + lastElement);
        lastElement.click();
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
