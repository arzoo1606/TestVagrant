import com.sun.javafx.PlatformUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
       
        
        //1st window
        driver.findElement(By.linkText("Your trips")).click();
      Set<String> win = driver.getWindowHandles();
        Iterator<String> iterate= win.iterator();
        String first_win = iterate.next();
        System.out.println(first_win);
        
        driver.findElement(By.id("SignIn")).click();
        waitFor(2000);
     
        //2nd window
        
        win = driver.getWindowHandles();
        iterate= win.iterator();
       
        System.out.println(iterate.next());
        String second_window = iterate.next();
        System.out.println(second_window);
        
        driver.switchTo().window(second_window);
        
        waitFor(2000);
        		
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/dl[1]/dd[5]/button[1]")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
