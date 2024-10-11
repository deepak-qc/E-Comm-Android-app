package E_Commerce;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E_Comm1 extends SetUpEcommerceApk{
	
	@Test
	public void FillForm() throws InterruptedException { //com.androidsample.generalstore:id/nameField
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
	//	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.androidsample.generalstore:id/nameField")));
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deepak Singh");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Argentina\")")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.className("android.widget.Button")).click();
		//Thread.sleep(3000);
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.androidUIAutomator(null)));
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
//		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\").instance(1)")).click();
		int productSize= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		List<WebElement> product= driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
		for(int i=0;i<productSize;i++) {
			if(product.get(i).getText().equalsIgnoreCase("Jordan 6 Rings")) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				Assert.assertTrue(true);
				break;
			}else {
				Assert.assertTrue(false);
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	
	}

}
