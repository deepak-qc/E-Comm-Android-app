package E_Commerce;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ValidateTotal extends SetUpEcommerceApk{
	
	@Test
	public void validateTotalofTop2Products() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deepak Singh");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Argentina\")")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.className("android.widget.Button")).click();
		//adding 2 items on cart
		Thread.sleep(2000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']"))
		.get(0).click();
		//Now text change from 'add 2 cart' to 'added 2 cart' so again 'add 2 cart' button index of 2nd element is 0.
		Thread.sleep(2000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']"))
		.get(0).click();
		//in cart page
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.androidsample.generalstore:id/productPrice")));
		List<WebElement> productPrices= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count= productPrices.size();
		double sum=0;
		for(int i=0; i<count;i++) {
			String amountString = productPrices.get(i).getText();
			double price= getFormattedAmount(amountString);
			sum= sum+ price;
		}
		//get total value by id
		String displaySum= driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double displayFormattedSum= getFormattedAmount(displaySum);
		//Assertion
		Assert.assertEquals(displayFormattedSum, sum);
		//click checkbox
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		WebElement ele= driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));		
		// Validate LongPress Gesture
		longPressAction(ele);
		String termsConditionsText= driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
		Assert.assertEquals(termsConditionsText, "Terms Of Conditions");
		driver.findElement(By.id("android:id/button1")).click();
		//visit to website button
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		//switch to web
		Thread.sleep(6000); //also you can give explicit wait to loading browser.
		//driver.getContextHandles(); //Here 2 handles native and webview
		//driver.context("Webview"); //this context  name can be changed depend on developers.
		Set<String> contexts= driver.getContextHandles();
		for(String context: contexts) {
			System.out.println("Context name is: "+context);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore"); //chrome driver
		driver.findElement(By.name("q")).sendKeys("Deepak Singh");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		//come back to native device again
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		//again need to switch to driver for native app.
		driver.context("NATIVE_APP");
	}
	
	public double getFormattedAmount(String amountString) {
		String singleProdPrice= amountString.substring(1); //string 1st character is on 0th index & bcuz decimal so change to double
		double price = Double.parseDouble(singleProdPrice);
		return price;
	}
	
	public void longPressAction(WebElement ele) {
		
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement) ele)
				.getId(),
				"duration", 2000));
	}


}
