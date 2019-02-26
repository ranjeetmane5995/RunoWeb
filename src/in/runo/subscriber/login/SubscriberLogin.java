package in.runo.subscriber.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import in.runo.runoweb.MainTestCase;

public class SubscriberLogin extends MainTestCase {

	public static void subscriberLogin() throws InterruptedException {
		
		Thread.sleep(4000);

		WebElement sbUser = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
		sbUser.sendKeys("6666666667");
		WebElement sbPassword = driver.findElement(By.xpath("//input[@type='password']"));
		sbPassword.sendKeys("555555555");

		WebElement sbLogin = driver.findElement(By.xpath("//input[@type='submit']"));
		sbLogin.click();

		System.out.println(" Subscriber login successfully !!");

	}

}
