package in.runo.operator.walkins.service;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Walkin_With_Membership extends Walkin_With_Individual_Discount {

	public static void addMembership(String newCustomerNumber, String CustomerName)
			throws InterruptedException, IOException {

		wait("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']");
		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']")).sendKeys(newCustomerNumber);

		try {

			wait("//span[@class='search-result-number']");
			if (driver.findElement(By.xpath("//span[@class='search-result-number']")) != null) {
				driver.findElement(By.xpath("//span[@class='search-result-number']")).click();

				System.out.println(" Test Case Failed !!As customer has already Membership plan");

			}
		} catch (Exception c) {

			wait("//input[@class='nice-textbox customerName ng-untouched ng-pristine ng-invalid']");
			driver.findElement(
					By.xpath("//input[@class='nice-textbox customerName ng-untouched ng-pristine ng-invalid']"))
					.sendKeys(CustomerName);
			driver.findElement(By.xpath("//input[@ng-reflect-name='email']")).sendKeys("automation@gmail.com");

			WebElement selectDob = driver.findElement(By.xpath("//input[@ng-reflect-name='dob']"));

			selectDob.click();

			driver.findElement(By.xpath("//button[@class='previous']")).click();

			driver.findElement(By.xpath("//span[text()='15']")).click();

			driver.findElement(By.xpath("//div[text()='Male']")).click();

			driver.findElement(By.xpath("//input[@ng-reflect-name='locality']"))
					.sendKeys("Sr No: 22/2, 2nd Floor Near Petrol Pump Bidar");
			driver.findElement(By.xpath("//input[@ng-reflect-name='city']")).sendKeys("Bidar");
			driver.findElement(By.xpath("//input[@ng-reflect-name='pincode']")).sendKeys("410047");
			driver.findElement(By.xpath("//input[@ng-reflect-name='notes']")).sendKeys(" He is a regular customer ");

			wait("//span[@class='buyMembership__text']");
			driver.findElement(By.xpath("//span[@class='buyMembership__text']")).click();			
			
		    String textValue = driver.findElement(By.xpath("//div[@class='selectMembership']//span[@class='selectMembership__text medium']")).getText();
		    
		    System.out.println(" Text value si :"+textValue);
			
			
			//
			wait("//div[@class='selectMembership']//span[@class='selectMembership__text medium']");
			
			
			driver.findElement(By.xpath("//div[@class='selectMembership']//span[@class='selectMembership__text medium']")).click();
			
			wait("//span[@class='summaryBox__service__categoryName']");
			String actCategoryName = driver.findElement(By.xpath("//span[@class='summaryBox__service__categoryName']"))
					.getText();

			String expCategoryName = "Membership";

			if (actCategoryName.equals(expCategoryName))

			{
				System.out.println(" Test Case Passed !! As Category name is matched with expected category name");
			}

			String actMembershipName = driver.findElement(By.xpath("//span[@class='summaryBox__service__serviceName']"))
					.getText();
			String expMembershipName = "GOLDEN";

			if (actMembershipName.equals(expMembershipName)) {
				System.out.println(" Test Case Passed !! As Membership name has taken correctly in summary page");
			}

			String actMembershipPrice = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
					.getText();

			System.out.println("Price is " + actMembershipPrice);

			String getActAmountPaid = driver.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']"))
					.getText();

			int actAmountPaid = Integer.parseInt(getActAmountPaid.trim().split(" ")[0]);

			int expAmounPaid = Integer.parseInt(actMembershipPrice);

			if (actAmountPaid == expAmounPaid) {
				System.out.println(" Test Case Passed !! As Memership price and AMount Paid amount is equal");

				wait("/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[2]/div/div/div[4]/div[2]/div/select");

				List<WebElement> modeList = driver.findElements(By.xpath(
						"/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[2]/div/div/div[4]/div[2]/div/select"));

				wait("//option[text()='Paytm']");

				driver.findElement(By.xpath("//option[text()='Paytm']")).click();

				driver.findElement(By.xpath("//button[@class='submitButton proceedBtn ng-star-inserted']")).click();

				System.out.println(" Congratulation !! Membership plan has been added successfully !");

			} else {
				System.out.println(" Test Case Failed !! Membership price and amount paid amount is not equal :"
						+ actMembershipPrice + " = " + expAmounPaid);
				takeScreenshot("MembeshipPrice.png");
			}

		} // end catch block

	}

	public static void availMembershipBenefits(String CustomerNumber) throws InterruptedException {

		wait("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']");

		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys(CustomerNumber);

		wait("//span[@class='search-result-number']");
		if (driver.findElement(By.xpath("//span[@class='search-result-number']")) != null) {
			driver.findElement(By.xpath("//span[@class='search-result-number']")).click();
			System.out.println(" customer is  existing customer");

			selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
			selectServiceDropDownlist.click();
			wait("//span[text()=' Age Defining  ( FACIALS ) ']");
			driver.findElement(By.xpath("//span[text()=' Age Defining  ( FACIALS ) ']")).click();
			selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
			selectEmpDropDownList.click();
			driver.findElement(By.xpath("//option[text()=' Automation 3 ']")).click();
			addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
			addSummary.click();

		}

	}
}
