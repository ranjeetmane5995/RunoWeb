package in.runo.operator.walkins.service;

import org.openqa.selenium.By;

public class Walkins_With_Common_Discount extends Walkin_With_Individual_Discount {
	
	
	public double twoDigitRounding(double num) {
		return Math.round(num * 100) / 100.0;
	}
	
	public void createWalkinsWithCommonDiscount() throws InterruptedException
	{
		
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys("9191919191");

		Thread.sleep(3000);

		// Handling noSuchElementException for New Customer
		try {

			if (driver.findElement(By.xpath("//span[@class='search-result-number']")) != null) {
				driver.findElement(By.xpath("//span[@class='search-result-number']")).click();
				System.out.println(" customer is  existing customer");
			}
		} catch (Exception c) {
			driver.findElement(
					By.xpath("//input[@class='nice-textbox customerName ng-untouched ng-pristine ng-invalid']"))
					.sendKeys("Walkin for CommonDiscount");
			driver.findElement(By.xpath("//div[text()='Male']")).click();
		}

		
		
		
		
		
		
		
		
		
		
		
	}

}
