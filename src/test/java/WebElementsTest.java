import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class WebElementsTest {
    @Test
    public void main(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();

        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement button_add_element = driver.findElement(By.xpath("//div[contains(@class, \"example\")]/button"));
        for (int i = 0; i < 3; i++) {
            button_add_element.click();
        }
        System.out.println("clicked 3 times '%s'".formatted(button_add_element.getText()));

        WebElement button_delete;
        button_delete = driver.findElement(By.cssSelector("#elements > button:last-child"));
        System.out.println("(By.cssSelector) last Delete button is '%s' ".formatted(button_delete.getText()));

        List<WebElement> buttons_delete = driver.findElements(By.cssSelector("button[class^='added']"));
        System.out.println("(By.findElements) last Delete button is '%s' ".formatted(buttons_delete.get(buttons_delete.size() - 1).getText()));

        button_delete = driver.findElement(By.xpath("//button[contains(@class, 'manually') and text()='Delete'][last()]"));
        System.out.println("(By.xPath) last Delete button is '%s' ".formatted(button_delete.getText()));

            
        driver.navigate().to("http://the-internet.herokuapp.com/challenging_dom");
        WebElement lorem_value;

        lorem_value = driver.findElement(By.xpath("//td[text()='Apeirian9']//preceding-sibling::td"));
        System.out.println("Lorem value of element, that has 'Apeirian9' as Ipsum value is '%s' ".formatted(lorem_value.getText()));

        lorem_value = driver.findElement(By.xpath("//td[text()='Apeirian9']//following-sibling::td"));
        System.out.println("Dolor value of element, that has 'Apeirian9' as Ipsum value is '%s' ".formatted(lorem_value.getText()));

        driver.quit();
    }
}
