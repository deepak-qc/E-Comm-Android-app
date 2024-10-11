package E_Commerce;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class SetUpEcommerceApk {
	public static AndroidDriver driver= null;
	public static AppiumDriverLocalService service;
	@BeforeTest()
	public static void appiumTest() throws MalformedURLException, URISyntaxException {
		 service= new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\deepa\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		 service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("DeepakPhone7");
		options.setApp("D:\\apkfiles\\APKFiles\\resources\\General-Store.apk");
		//to increase adb Execution Timeout capability
		options.setCapability("adbExecTimeout", 60000);
		options.setChromedriverExecutable("//zyx//xyz//chromedriver");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
	//	driver.quit();
	//	service.stop();

	}
	
//	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
