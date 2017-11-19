package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
//    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\chromedriver.exe"); 
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("http://localhost:4567");
    	WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        System.out.println(driver.getPageSource());
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pek2jijijdddkaa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akke2p");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akke2p");
        
        sleep(2);
        element.submit();
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(1);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
