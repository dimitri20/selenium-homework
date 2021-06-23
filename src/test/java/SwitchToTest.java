import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SwitchToTest {

    @Test
    public void main(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://the-internet.herokuapp.com/iframe");
        WebElement frame_1 = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frame_1);
        System.out.println("switched to iframe - mce_0_ifr");

        WebElement p_tag = driver.findElement(By.tagName("p"));
        p_tag.sendKeys("Here Goes");
        driver.switchTo().defaultContent();
        System.out.println("inserted text into p tag and switched to main document");

        WebElement align_center_button = driver.findElement(By.cssSelector("button.tox-tbtn[aria-label='Align center']"));
        align_center_button.click();
        System.out.println("clicked on align-center");

        driver.navigate().to("https://demoqa.com/alerts");

        WebElement alert_button = driver.findElement(By.id("alertButton"));
        alert_button.click();
        System.out.println("clicked on alert-button");

        try{
            driver.switchTo().alert().accept();
            System.out.println("accepted alert");
        } catch (Exception e){
            System.out.println(e);
        }
        driver.quit();
    }

}
