import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CommandsTest {
    @Test
    public void main(){
        //Open Browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();

        //navigate to the specified url
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        //get enable button
        WebElement button = driver.findElement(By.cssSelector("#input-example > button"));
        //click on enable button
        button.click();

        //check if loading ended
        WebElement loading = driver.findElement(By.id("loading"));
        boolean loaded = false;
        while(!loading.getCssValue("display").equals("none")){
            loaded = true;
        }

        // - Check that input field became enabled and text 'It's enabled!' is displayed
        WebElement input = driver.findElement(By.cssSelector("#input-example > input"));
        WebElement message;
        if(loaded){
            if(input.isEnabled()){
                System.out.println("input is enabled");

                message = driver.findElement(By.id("message"));
                if (message.getText().equals("It's enabled!")){
                    System.out.println("text changed to \"It's enabled\"");
                }
            }
        }

        // Check that button text has changed from Enable to Disable
        if(button.getText().equals("Disable")){
            System.out.println("Button text changed to \"Disable\"");
        }

        //Write 'Bootcamp' in input field and clear it
        input.sendKeys("Bootcamp");
        System.out.println("Input text has been set to \"Bootcamp\"");
        input.clear();
        System.out.println("Input has been cleared");

        //Navigate to the http://the-internet.herokuapp.com/drag_and_drop
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        WebElement A = driver.findElement(By.id("column-a"));
        WebElement B = driver.findElement(By.id("column-b"));

        // Check if Y coordinate of column A and column B are the same
        if(A.getLocation().getY() == B.getLocation().getY()){
            System.out.println("Y Coordinates of A and B are equal");
        }

        //quit window
        driver.quit();
    }
}
