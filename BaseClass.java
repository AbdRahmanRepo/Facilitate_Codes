import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass{
	public static WebDriver driver;
	public static HttpURLConnection connection;

	//Driver define

	public static WebDriver launch(String browser) {
		try {
			if(browser.equalsIgnoreCase("Chrome")) {
				//			System.setProperty("webdriver.edge.driver",
				//	"C:\\Users\\ELCOT\\eclipse-workspace\\QuickBuy\\driver\\chromedriver.exe");
				//			System.setProperty("webdriver.chrome.driver",
				//					System.getProperty("user.dir")+"/driver/chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("Edge")) {
				//			System.setProperty("webdriver.edge.driver",
				//					"C:\\Users\\ELCOT\\eclipse-workspace\\SeleniumProject\\driver\\msedgedriver.exe");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

			}
			else {
				System.out.println("Load Failed");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return driver;

	}

	//get

	public static void go(String link) { //get
		try {
			driver.get(link);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	//close

	public static void shut() {      //close
		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//back

	public static void back() {     //navigate().back()
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//forward

	public static void forward() {  //navigate().forward()
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//refresh

	public static void refresh() {  //navigate().refresh()
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//sendkeys

	public static void input(WebElement ele,String str) {
		try {
			ele.sendKeys(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//click

	public static void press(WebElement obj) {
		try {
			obj.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//implicitWait

	public static void freeze() {
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Title
	public static String title() {
		try {
			driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver.getTitle();
	}


	//maximize

	public static void max() {
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//action move to Element

	public static void moveTo(WebElement ele) {
		try {
			Actions a = new Actions(driver);
			a.moveToElement(ele).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//action ContextClick

	public static void conclik(WebElement cc) {
		try {
			Actions a = new Actions(driver);
			a.moveToElement(cc).build().perform();
			a.contextClick().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Robot 

	public static void rdown(WebElement Rob) throws AWTException {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	//ScreenShot

	public static void ss(String name) throws IOException {
		try {
			TakesScreenshot snap =(TakesScreenshot)driver;
			File src = snap.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir")+"/ScreenShot/"+name+".png");
			FileUtils.copyFile(src, dest);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//SelectByValue

	public static void selectByValue(WebElement ele, String value) {
		try {
			Select Sby = new Select(ele);
			Sby.selectByValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//selectByIndex

	public static void selectByIndex(WebElement ele, int value) {
		try {
			Select Sby = new Select(ele);
			Sby.selectByIndex(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//selectByVisibleText

	public static void selectByVisibleText(WebElement ele, String value) {
		try {
			Select Sby = new Select(ele);
			Sby.selectByValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void httpGet(String path) throws Throwable {
		URL url = new URL(path);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		
		PrinthttpsCodeMsg();
		httpinputReader();

	}
	public static void httpPost(String path, String JsonBody) throws Throwable {
		URL url = new URL(path);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		String jsonBody = JsonBody;
		byte[] inputJson = jsonBody.getBytes();

		OutputStream os = connection.getOutputStream();
		os.write(inputJson);

		PrinthttpsCodeMsg();
		httpinputReader();
	}
	public static void httpPut(String path, String modJsonBody) throws Throwable {
		URL url = new URL(path);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("PUT");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		String jsonBody = modJsonBody;
		byte[] inputJson = jsonBody.getBytes();

		OutputStream os = connection.getOutputStream();
		os.write(inputJson);

		PrinthttpsCodeMsg();
		httpinputReader();
	}
	public static void httpDelete(String path, String modJsonBody) throws Throwable {
		URL url = new URL(path);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("DELETE");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		PrinthttpsCodeMsg();
		httpinputReader();
	}
	
	
	
	
	public static void httpinputReader() throws Throwable {
		InputStream ips = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(ips);

		BufferedReader br = new BufferedReader(reader);
		String line;
		StringBuffer data = new StringBuffer();
		while((line = br.readLine())!=null) {
			data.append(line);
		}
		System.out.println(data);
	}
	public static void PrinthttpsCodeMsg() throws Throwable {
		int statusCode = connection.getResponseCode();
		System.out.println("Status Code is "+ statusCode);

		String Responsemsg = connection.getResponseMessage();
		System.out.println("Response Message is"+Responsemsg);

	}


	





















}
