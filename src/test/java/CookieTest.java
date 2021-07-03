import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CookieTest {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void getAndDeleteCookies(){
        driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");

        driver.findElement(By.name("username")).sendKeys("test123");
        driver.findElement(By.name("password")).sendKeys("testtest");
        driver.findElement(By.name("submit")).click();
        driver.navigate().refresh();

        Assert.assertEquals(driver.findElement(By.cssSelector("h2.form-signin-heading")).getText(),"You are logged In");
        Set<Cookie> cookies = driver.manage().getCookies();

        System.out.println("Initial Site cookies: ");
        for (Cookie cookie : cookies){
            System.out.println(cookie);
        }

        System.out.println("\n\n\n");

        for (Cookie cookie:cookies){

            if (cookie.getName().equals("Selenium") || Objects.isNull(cookie.getExpiry())){
                System.out.println("deleted: " + cookie);
                driver.manage().deleteCookie(cookie);
                Cookie deletedCookie = driver.manage().getCookieNamed(cookie.getName());
                Assert.assertNull(deletedCookie);
            }

        }

        System.out.println("\n\n\n");

        cookies = driver.manage().getCookies();

        System.out.println("Cookies after deleting: ");
        for (Cookie cookie : cookies){
            System.out.println(cookie);
        }

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
