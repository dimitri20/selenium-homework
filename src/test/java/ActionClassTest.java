import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.testng.annotations.Test;


public class ActionClassTest {
    @Test
    public void main(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.get("https://demoqa.com/slider");
        Actions moveSlider = new Actions(driver);
        WebElement slider = driver.findElement(By.cssSelector("#sliderContainer input[type='range'].range-slider"));

        try{

            //works excellent
            controlWithAnotherTag(driver, moveSlider, slider);

            //doesn't work with double values
            //withMoveByOffset(slider, moveSlider);

        } catch (Exception e) {
            System.out.println(e);
        }

//        driver.quit();

    }

    public void controlWithAnotherTag(WebDriver driver, Actions moveSlider, WebElement slider) {
        int moveTo = 87;
        double div_mapping = (50 - moveTo)*0.2;

        //add helper div to document
        String script = """
                            var slider_container = document.querySelector("#sliderContainer > div.col-9 > span.range-slider__wrap");
                            elem = document.createElement('div');
                            slider_container.append(elem);
                            elem.setAttribute("id", "helper_div");
                            elem.style.position = "absolute";
                            elem.style.left = "0";
                        """;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);

        WebElement helper_div = driver.findElement(By.id("helper_div"));

        //the position of helper div changes by principle
        js.executeScript("document.querySelector('#helper_div').style.left = 'calc("+ moveTo + "%" + " + " + div_mapping + "px"+")';");

        moveSlider.clickAndHold(slider).moveToElement(helper_div).release().perform();

        System.out.println("slider has moved up to " + moveTo);

    }

    public void withMoveByOffset(WebElement slider, Actions moveSlider){
        int moveTo = 20;
        double width = Double.parseDouble(slider.getCssValue("width").replace("px", ""));

        System.out.println("width = " + width);

        //calculate relationship between 1 px slider value
        double mapping = width/(double)Integer.parseInt(slider.getAttribute("max"));

        //calculate px from value. clickAndHold method clicks in the center of slider and calculation is needed to start from the center
        double moveXBy = (mapping*(moveTo - Double.parseDouble(slider.getAttribute("max"))/2.0));

        System.out.println(moveXBy);
        System.out.println(Math.round(moveXBy));

        moveSlider.clickAndHold(slider).moveByOffset((int)Math.floor(moveXBy), 0).release().perform();
        System.out.println(moveXBy + "   ===>   " + slider.getAttribute("value"));
    }

}
