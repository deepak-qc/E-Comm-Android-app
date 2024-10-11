package E_Commerce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;


import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class pracFull extends SetUpEcommerceApk{

		public static void eCommFull() throws InterruptedException {
			driver.findElement(By.id("android:id/text1")).click();
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Benin\"))"));
			Thread.sleep(2000);
			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Benin\")")).click();
			driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))
			.sendKeys("Ds");
			driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
			Thread.sleep(5000);
			driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"))
			.get(0).click();
			driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"))
			.get(1).click();
			driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
			Thread.sleep(5000);
		}
		
		public static void cartPage() {
			double sum=0;
			List<WebElement> productPrice= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		
			for(int i=0;i<productPrice.size();i++) {
				String productPriceStringWithoutDollar= productPrice.get(i).getText().substring(1);
				double productNumPrice= Double.parseDouble(productPriceStringWithoutDollar);
				sum= sum+ productNumPrice;
				System.out.println("Total sum of product is: " +sum);
			}
			
			String stringtotalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
					.getText().substring(1);
			double actualTotalAmount= Double.parseDouble(stringtotalAmount);
			System.out.println("Total amount of product on cart page is "+actualTotalAmount);
			Assert.assertEquals(sum, actualTotalAmount);
		}
		
		public static void goToWebPage() {
			driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
			WebElement ele= driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
			longclickGesture(ele);
			driver.findElement(By.id("android:id/button1")).click();
			driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
			
		}
		
		public static void longclickGesture(WebElement ele) {
			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) ele).getId()
				));
		}

	

}
