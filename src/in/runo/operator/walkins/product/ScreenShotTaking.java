package in.runo.operator.walkins.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import in.runo.runoweb.MainTestCase;

public class ScreenShotTaking extends  MainTestCase {
	
	public static TakesScreenshot ts = (TakesScreenshot) driver;
	
public void takeScreenShot1() throws IOException
	{
		Date d = new Date();
		String date1 = d.toString();
		System.out.println(date1);
		String date2=date1.replaceAll(":", "_");
		
		System.out.println(date2);
		
		driver.get("https://www.google.com/");
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\Users\\ranje\\eclipse-workspace\\RunoWeb\\TakenScreenShot\\"+date2+"_error.png");
		
		FileUtils.copyFile(srcFile, destFile);
		
		System.out.println(" Successfully done !!");
		 
	}
	
	public static void takesScreenShot()
	{
		
	}

}
