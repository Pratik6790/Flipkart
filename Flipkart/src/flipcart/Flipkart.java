package flipcart;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

public class Flipkart {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("https://www.flipkart.com/");
            
            try {
                WebElement PopupIcon = driver.findElement(By.xpath("/html/body/div[3]/div/span"));
                PopupIcon.click();
            } catch (NoSuchElementException ignored) {
                
            }

            WebElement SearchBox = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input"));
            SearchBox.sendKeys("iPhone 16 Pro" + Keys.ENTER);

            wait.until(new Function<WebDriver, Boolean>() 
            {
                public Boolean apply(WebDriver webDriver) 
                {
                    return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
                }
            });
            
            WebElement Phone = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[3]/div/div/div/a/div[2]/div[1]/div[1]"));
            Phone.click();
            
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }

            wait.until(new Function<WebDriver, Boolean>() 
            {
                public Boolean apply(WebDriver webDriver) 
                {
                    return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
                }
            });
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,900)");

            Screenshot(driver, "ProductInfo.png");

            WebElement buyNowButton = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[2]/form/button"));
            buyNowButton.click();
             Thread.sleep(5000);
            Screenshot(driver, "LoginPopup.png");
            
            driver.quit();
            
               }

    public static void Screenshot(WebDriver driver, String FileName) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcFile, new File("..//Flipkart//Screenshots//" + FileName));
        System.out.println("Screenshot saved successfully: " + FileName);
    }
		
	}
    

