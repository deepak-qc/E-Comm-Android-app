package E_Commerce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ToastMessage extends SetUpEcommerceApk {
	@Test
	public void FillForm() throws InterruptedException { //com.androidsample.generalstore:id/nameField
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.androidsample.generalstore:id/nameField")));
//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deepak Singh");
//		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Argentina\")")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.className("android.widget.Button")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//android.widget.Toast[@text=\"Please enter your name\"]")));

		String toastMessage= driver.findElement(By.xpath("//android.widget.Toast[@text=\"Please enter your name\"]")).getText();
		System.out.println(toastMessage);
		Assert.assertEquals(toastMessage, "Please enter your name");
		
	}

}
