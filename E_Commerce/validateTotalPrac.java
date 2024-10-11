package E_Commerce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class validateTotalPrac extends SetUpEcommerceApk {
	
	@Test
	public void validateTotal() throws InterruptedException {
	
	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deepak Singh");
	driver.hideKeyboard();
	driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	driver.findElement(
			AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Argentina\")")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	driver.findElement(AppiumBy.className("android.widget.Button")).click();
	//Add 2 cart
	Thread.sleep(2000);
	driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
	driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	//Validate total
	Thread.sleep(3000);
	List<WebElement> productCount= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	double sum=0;
	for(int i=0;i<productCount.size();i++) {
		String productPrice= productCount.get(i).getText().substring(1);
		double numericPrice= Double.parseDouble(productPrice);
		sum= sum+numericPrice;
	}
	System.out.println(sum);
	String totalAmount= driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	String numericTotalAmountValue= totalAmount.substring(1);
	double totalAmountNum= Double.parseDouble(numericTotalAmountValue);
	Assert.assertEquals(sum, totalAmountNum);
	
	
	}

}
