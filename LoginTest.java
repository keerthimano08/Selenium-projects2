
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginTest {
	    
	// Method to extract text within single quotes
	public static String extractTextWithinQuotes(String input) {
	    Pattern pattern = Pattern.compile("'(.*?)'");
	    Matcher matcher = pattern.matcher(input);	        
	    if (matcher.find()) {
	        return matcher.group(1);
	    } else {
	        return "No quoted text found";
	    }
	    }

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //wait for each step
		//login attempt with invalid password
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys("keerthi");
        driver.findElement(By.name("inputPassword")).sendKeys("password");
        driver.findElement(By.className("signInBtn")).click();
        //forgot password field check
        String error = driver.findElement(By.cssSelector("p.error")).getText();
        System.out.println("Error message : " + error);
        //reset the password
        driver.findElement(By.xpath("//a[text()='Forgot your password?']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[placeholder='Name']")).sendKeys("keerthi");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("keerthi@yahoo.com");
        driver.findElement(By.cssSelector("input[placeholder='Phone Number']")).sendKeys("7878787878");
        driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
        //getting the temporary password using a method
        String tempPassword = driver.findElement(By.className("infoMsg")).getText();
        String pwd = extractTextWithinQuotes(tempPassword);
        System.out.println("Temporary password id:" + pwd);
        driver.findElement(By.cssSelector("button.go-to-login-btn")).click();
        Thread.sleep(1000);
        //login with actual password
        driver.findElement(By.id("inputUsername")).sendKeys("keerthi");
        driver.findElement(By.name("inputPassword")).sendKeys(pwd);
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.id("chkboxTwo")).click();
        driver.findElement(By.className("signInBtn")).click();
        String loginCheck = driver.findElement(By.xpath("//div/p")).getText();
        System.out.println("Logged in Message"+loginCheck);
        //logout
        driver.findElement(By.cssSelector("button.logout-btn")).click();        
        driver.close();
       
	}
}
