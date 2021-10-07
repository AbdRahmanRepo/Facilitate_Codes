package BaseDemo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import POM.FaceBook;

public class BaseClass{
	public static WebDriver driver;
	
	//Driver define
	
	public static void launch(String browser) {
		if(browser.equalsIgnoreCase("Chrome")) {
			//System.setProperty("webdriver.edge.driver",
				//	"C:\\Users\\ELCOT\\eclipse-workspace\\QuickBuy\\driver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"/driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\ELCOT\\eclipse-workspace\\SeleniumProject\\driver\\msedgedriver.exe");
			driver = new EdgeDriver();
			
		}
		else {
			System.out.println("Load Failed");
		}
	}
	
	//get
	
	public static void go(String link) { //get
		driver.get(link);
	}
	
	//close
	
	public static void shut() {      //close
		driver.close();
	}
	
	//back
	
	public static void back() {     //navigate().back()
		driver.navigate().back();
	}
	
	//forward
	
	public static void forward() {  //navigate().forward()
		driver.navigate().forward();
	}
	
	//refresh
	
	public static void refresh() {  //navigate().refresh()
		driver.navigate().refresh();
	}
	
	//sendkeys
	
	public static void input(WebElement ele,String str) {
		ele.sendKeys(str);
	}
	
	//click
	
	public static void press(WebElement obj) {
		obj.click();
	}
	
	//implicitWait
	
	public static void freeze() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	//Title
	public static String title() {
		return driver.getTitle();
	}
	
	
	//maximize
	
	public static void max() {
		driver.manage().window().maximize();
	}
	
	//action move to Element
	
	public static void moveTo(WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).build().perform();
	}
	
	//action ContextClick
	
	public static void conclik(WebElement cc) {
		Actions a = new Actions(driver);
		a.moveToElement(cc).build().perform();
		a.contextClick().build().perform();
	}
	
	//Robot 
	
	public static void rdown(WebElement Rob) throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//ScreenShot
	
	public static void ss(String name) throws IOException {
		TakesScreenshot snap =(TakesScreenshot)driver;
	    File src = snap.getScreenshotAs(OutputType.FILE);
	    File dest = new File(System.getProperty("user.dir")+"/ScreenShot/"+name+".png");
	    FileUtils.copyFile(src, dest);
	}
	
	//SelectByValue
	
	public static void selectByValue(WebElement ele, String value) {
		Select Sby = new Select(ele);
		Sby.selectByValue(value);
	}
	
	//selectByIndex
	
	public static void selectByIndex(WebElement ele, int value) {
		Select Sby = new Select(ele);
		Sby.selectByIndex(value);
	}
	
	//selectByVisibleText
	
	public static void selectByVisibleText(WebElement ele, String value) {
		Select Sby = new Select(ele);
		Sby.selectByValue(value);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
