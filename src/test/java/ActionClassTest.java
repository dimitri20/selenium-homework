import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ActionClassTest {
    @Test
    public void main(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();


        driver.get("https://demoqa.com/slider");
        Actions moveSlider = new Actions(driver);
        WebElement slider = driver.findElement(By.cssSelector("#sliderContainer input[type='range'].range-slider"));

        try{
            //get slider current location to calculate the moveByOffset x coordinate change ;
            int moveTo = 50;
            //clickAndHold method clicks in the center of the slider, because of that calculation is performed from the center of the slider
            //in this example slider will change it's position by 0
            int moveByX_value = moveTo - Integer.parseInt(slider.getAttribute("max"))/2;
            moveSlider.clickAndHold(slider).moveByOffset(moveByX_value, 0).release().perform();
            System.out.println("slider has moved up to " + moveTo);
        } catch (Exception e) {
            System.out.println(e);
        }

        driver.quit();

    }
}
