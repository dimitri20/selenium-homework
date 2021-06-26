
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import CustomClasses.Table;

import java.util.ArrayList;
import java.util.List;

public class WebTablesTest {
    WebDriver driver;

    public WebTablesTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void main(){
        driver.get("http://techcanvass.com/Examples/webtable.html");
        Table table = new Table(driver, By.id("t01"));
        ArrayList<List<WebElement>> table_elements = table.getTable();
        table_elements.remove(0);

        for (int i = 0; i < table_elements.size(); i++){
            for (int j = 0; j < table_elements.get(i).size(); j++){
                if(table_elements.get(i).get(j).getText().equals("Honda"))
                    System.out.println("Price of honda is " + table_elements.get(i).get(2).getText());
            }
        }

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
