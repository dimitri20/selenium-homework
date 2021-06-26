

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import CustomClasses.Table;

public class Exceptions {
    WebDriver driver;
    public Exceptions(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void main(){

        task_1();
        task_2();
    }


    public void task_1(){
        driver.get("https://jqueryui.com/datepicker/");

        try
        {
            driver.switchTo().frame(driver.findElement(By.cssSelector("#content > iframe")));
            driver.findElement(By.id("datepicker")).click();

            WebElement previous = driver.findElement(By.cssSelector("#ui-datepicker-div a[title='Prev']"));
            previous.click();

            //init custom Table class
            Table table = new Table(driver, By.cssSelector("#ui-datepicker-div > table"));

            //get Last non empty element from calendar with Table class method
            String lastDay = table.getLastNonEmptyElement().getText();
            System.out.println("Last day of previous month is " + lastDay);

        }
        catch (NoSuchFrameException e)
        {
            System.out.println("Couldn't found calendar on web page");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void task_2(){
        driver.navigate().to("https://demoqa.com/alerts");

        try{
            invokeTimeoutException();
//            invokeStaleElementReferenceException();
        }
        catch (NoAlertPresentException e){
            System.out.println("There is no alert on this page");
        }
        catch (TimeoutException e) {
            System.out.println("TimeoutException has been invoked and caught successfully");
        }
        catch (StaleElementReferenceException e){
            System.out.println("Element no longer available in dom. i.e. StaleElementReferenceException has been invoked and handled successfully ");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void invokeTimeoutException(){
        WebElement clickMe_button = driver.findElement(By.id("timerAlertButton"));
        clickMe_button.click();

        //invoke TimeOutException exception
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void invokeStaleElementReferenceException(){
        //invoke StaleElementReferenceException exception
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //create div tag
        String script = """
                                elem = document.createElement('div');
                                document.querySelector('body').append(elem);
                                elem.setAttribute("id", "helper_div");
                            """;
        js.executeScript(script);

        //we can access div tag now
        WebElement helper_div = driver.findElement(By.id("helper_div"));
        System.out.println("Helper div ------>  " + helper_div.getAttribute("id"));

        //refresh page, then helper_div will no longer available
        driver.navigate().refresh();
        System.out.println("Helper div ------>  " + helper_div.getAttribute("id"));
    }



    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
