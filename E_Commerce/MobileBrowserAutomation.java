package E_Commerce;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MobileBrowserAutomation extends BrowserBaseTest {
	
	@Test
	public void browserTest()
	{
		
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("q")).sendKeys("Deepak Singh");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
	}

}
