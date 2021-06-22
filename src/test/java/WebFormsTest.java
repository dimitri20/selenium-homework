import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class WebFormsTest {
    @Test
    public void main(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");

        WebElement languages_dropdown = driver.findElement(By.id("dropdowm-menu-1"));
        Select programming_languages = new Select(languages_dropdown);
        String language = "C#";
        programming_languages.selectByVisibleText(language);


        if(programming_languages.getFirstSelectedOption().getText().equals(language))
            System.out.println(language + " is selected");
        else
            System.out.println(language + " was not selected ");


        //check all unselected checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        for (WebElement checkbox : checkboxes){
            if(!checkbox.isSelected())
                checkbox.click();
        }

        WebElement yellow_radio = driver.findElement(By.cssSelector("input[type='radio'][value='yellow']"));
        yellow_radio.click();



        WebElement fruits_dropdown_tag = driver.findElement(By.id("fruit-selects"));
        Select fruits_dropdown = new Select(fruits_dropdown_tag);
        String option_to_be_checked = "Orange";
        List<WebElement> fruits = fruits_dropdown.getOptions();
        for (WebElement fruit : fruits){
            if (fruit.getText().equals(option_to_be_checked)){

                if (!fruit.isEnabled())
                    System.out.println(option_to_be_checked + " is disabled");
                else
                    System.out.println(option_to_be_checked + " is not disabled");
            }
        }

        driver.quit();

    }

}
