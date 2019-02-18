package in.runo.subscriber.logout;

import org.openqa.selenium.By;

import in.runo.runoweb.MainTestCase;

public class SubscriberLogout extends MainTestCase{
	
	public static void sbLogout()
	{
		driver.findElement(By.xpath("//input[@class='nav navbar-nav ml-auto logout']")).click();
		
		System.out.println(" Subscriber Logout Successfully");
	}

}
