package E_Commerce;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BrowserBaseTest {
	public static AndroidDriver driver;
	public static AppiumDriverLocalService service;
	@BeforeTest()
	public static void appiumTest() throws MalformedURLException, URISyntaxException {
		 service= new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\deepa\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		 service.start();
		UiAutomator2Options options = new UiAutomator2Options(); /*use to allow automation android device
		either app or mobile browser */
		options.setDeviceName("DeepakPhone7");
		System.out.println("user dir path is: "+System.getProperty("user.dir"));
		String driverPath= System.getProperty("user.dir")+"\\src\\test\\java\\chromedriver\\chromedriver.exe";
		System.out.println("driver path is: "+driverPath);
		options.setChromedriverExecutable(driverPath);
		options.setCapability("browserName", "Chrome");
		//to increase adb Execution Timeout capability
		//options.setCapability("adbExecTimeout", 60000);
		
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); /* u need to create
		android driver object */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		

	}
	
//	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
