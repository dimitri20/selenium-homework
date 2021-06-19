import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WaitsTest {

    @Test
    public void main(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/progress-bar");

        WebElement start_button = driver.findElement(By.id("startStopButton"));
        start_button.click();

        WebElement progress_bar = driver.findElement(By.cssSelector("#progressBar > div[role='progressbar']"));

        new WebDriverWait(driver, 10).until(ExpectedConditions.attributeToBe(progress_bar, "aria-valuenow", Integer.toString(100)));
        System.out.println(progress_bar.getText());

        driver.quit();
    }

}
