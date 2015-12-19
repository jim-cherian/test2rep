package generic_library;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Base_Class {
	public WebDriver driver = null;
		public void Initialize_browser() throws Exception
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(UtilityClass.getpropertyvalue("URL"));
	}
	
	public void tear_down()
	{
		driver.quit();
	}
	
	public void snapshotmethod(String testcase, String TC_ID, String Order) throws Exception
	{
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		TakesScreenshot snapshot = (TakesScreenshot)driver;
		File screenshotAs = snapshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\snapshot\\"+TC_ID+"-"+Order+"-"+dateformat.format(date)+".png"));
	}

}
