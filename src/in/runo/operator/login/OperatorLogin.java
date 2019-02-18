package in.runo.operator.login;

import org.openqa.selenium.By;
import in.runo.runoweb.MainTestCase;

public class OperatorLogin extends MainTestCase {

	public static void operatorLogin() throws InterruptedException {

		
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@id='mat-tab-label-0-1' and @ng-reflect-disabled='false']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Mobile Number']")).sendKeys("8830175062");
		driver.findElement(By.xpath("//input[@placeholder='password']")).sendKeys("Jeet5995**");

		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		System.out.println(" Operator Login Successfully !! ");

	}

}
