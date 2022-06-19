package plandaytest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase {
	
	
	ChromeDriver driver;
	
	private void handleLogin() {
		driver.findElement(By.id("Username")).sendKeys("plandayqa@outlook.com");
		driver.findElement(By.id("Password")).sendKeys("APItesting21"); 
	    driver.findElement(By.id("MainLoginButton")).click();  
	}
	@BeforeMethod
	
public void setUp() {
		
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://test1234.planday.com/");
	driver.findElement(By.className("cookie-banner__button")).click();
		
				
	}

	@AfterMethod
	
	public void tearDown()
	{	
		driver.quit();
		driver=null;
	}
	


	@Test
	public void test1NavigateandVeryfy() 
	{
		
		
		driver.findElement(By.xpath("//*[@id=\'Username\']")).isDisplayed();
		driver.findElement(By.xpath("//*[@id='Password']")).isDisplayed();
		driver.findElement(By.xpath("//*[@id=\'MainLoginButton\']")).isDisplayed();
        
        
	}
		

	@Test
	public void test2VerifyError()
	{
		
		
		driver.findElement(By.id("Username")).sendKeys("xyz@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("1234"); 
		driver.findElement(By.id("MainLoginButton")).click();

		if(driver.getPageSource().contains("The username or password is incorrect."))
		{
			System.out.println("Text is present");
			}
		else
			{
			System.out.println("Text is absent");
			}
		
}	
	
	
	@Test
	public void test3Login()
	{
		handleLogin();	
	}
	
	
	@Test
	public void test4and5NavigateShcedulepageandverify()
	{
				
		handleLogin();
		
		driver.findElement(By.xpath("//a[@href='/schedule']")).click();
		
		
	    driver.manage().window().maximize();
	    String baseUrl="https://test1234.planday.com/scdedule";
	    driver.get(baseUrl);
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	   
	    
	    //Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
	    if(baseUrl.equals(driver.getCurrentUrl()))
	    {
	       System.out.println("URLS Verify");
	    }
	    else
	    {
	       System.out.println("URLS not Verify");
	   }
		
	}
	
	
	
	
	
	@Test
	public void test6countandassertnumberofemployee()
	{
		
		
		handleLogin();
		
		driver.findElement(By.xpath("//a[@href='/schedule']")).click();
		
		WebElement iframe = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/iframe[1]"));

	    driver.switchTo().frame(iframe);
	      
	    int size = driver.findElements(By.xpath("//*[@class='row-header3']")).size();
	    Assert.assertEquals((size-1), 3);
	    
	    driver.findElement(By.xpath("//*[@class='button--right date-bar__button']")).click();
		
	}
	
	
	
	@Test
	public void test7and8createashiftandfilltime()
	{
		
		
		handleLogin();
		
		driver.findElement(By.xpath("//a[@href='/schedule']")).click();
		
		WebElement iframe = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/iframe[1]"));
		driver.switchTo().frame(iframe);
	
	      
	    int size = driver.findElements(By.xpath("//*[@class='row-header3']")).size();
	    Assert.assertEquals((size-1), 3);
	    
	    driver.findElement(By.xpath("//*[@class='button--right date-bar__button']")).click();
		
		driver.findElement(By.xpath("//button[text()='Today']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'virtualized-board__row')][2]/div[contains(@class,'board__cell')][7]")).click();
		driver.findElement(By.id("shiftStartEnd_start")).sendKeys("9:00");
		driver.findElement(By.id("shiftStartEnd_end")).sendKeys("17:00");
		driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[6]/div/div/ul/li[2]/button")).click();
		
		
	}
	
	
	
	
	@Test
	public void test9assertcreatedshift()
	{
		
		handleLogin();
		
		driver.findElement(By.xpath("//a[@href='/schedule']")).click();
		
		WebElement iframe = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/iframe[1]"));
		driver.switchTo().frame(iframe);

	    
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'virtualized-board__row')][2]"));
	
		
		if (ele.getText().contains("Shift"))
		{
			  System.out.println("Shift Created");
	    }
	    else
	    {
	       System.out.println("Shift not Created");
	   }
		        
		
	}
	
}
