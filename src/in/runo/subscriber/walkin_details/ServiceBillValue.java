package in.runo.subscriber.walkin_details;

import java.io.IOException;

import org.openqa.selenium.By;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.walkins.service.Walkin_With_Credits;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.operator.walkins.service.Walkin_With_PartialPaid_Amount;
import in.runo.subscriber.login.SubscriberLogin;
import in.runo.subscriber.logout.SubscriberLogout;

public class ServiceBillValue extends Walkin_With_Individual_Discount {

	public static void verifyPartialPaidBillValue() throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();
		Walkin_With_PartialPaid_Amount.verifyPartialPaidAmount("9800111103", "verify_Partial_Paid_BillValue");
		OperatorLogout.operatorLogout();
		SubscriberLogin.subscriberLogin();
		wait("//a[@ng-reflect-router-link='/walkins']");
		driver.findElement(By.xpath("//a[@ng-reflect-router-link='/walkins']")).click();

		wait("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']");

		driver.findElement(By.xpath("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Today')]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys("9800111103");

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span");

		String getBillValue = driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span"))
				.getText();

		double getActBillValue = Double.valueOf(getBillValue.trim().split(" ")[1]);

		if (getActBillValue == actualAmountPayable) {

			System.out.println(" Test Case Passed !! As bill value is taking correctly");

		} else {
			System.out.println(" Test Case Failed !! As bill value is not correct. Bill value should be "
					+ actualAmountPayable + " instead of " + getActBillValue);
		}

	}

	public static void verifyPastDueAmountBillValue() throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();

		Walkin_With_PartialPaid_Amount.verifyPastDueAmount("9800111103");

		OperatorLogout.operatorLogout();
		SubscriberLogin.subscriberLogin();
		wait("//a[@ng-reflect-router-link='/walkins']");
		driver.findElement(By.xpath("//a[@ng-reflect-router-link='/walkins']")).click();

		wait("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']");

		driver.findElement(By.xpath("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Today')]")).click();

		wait("//input[@placeholder='Search walkins']");

		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys("9800111103");

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span");

		String getBillValue = driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span"))
				.getText();

		double actBillValues = Double.valueOf(getBillValue.trim().split(" ")[1]);

		if (actBillValues == expBillValue) {

			System.out.println(" Test Case Passed !! As bill value is taking correctly");

		} else {
			System.out.println(" Test Case Failed !! As bill value is not correct. Bill value should be " + expBillValue
					+ " instead of " + actBillValues);
		}

	}

	public static void verifyAddCreditsBillValue() throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();

		Walkin_With_Credits.usingCreditsForBilling();

		OperatorLogout.operatorLogout();
		SubscriberLogin.subscriberLogin();

		wait("//a[@ng-reflect-router-link='/walkins']");
		driver.findElement(By.xpath("//a[@ng-reflect-router-link='/walkins']")).click();

		wait("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']");

		driver.findElement(By.xpath("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']")).click();

		wait("//span[@class='mat-option-text'][contains(text(),'Today')]");
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Today')]")).click();

		wait("//input[@placeholder='Search walkins']");

		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys("8830175067");

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span");

		String getBillValue = driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span"))
				.getText();

		int getActBillValue = Integer.parseInt(getBillValue.trim().split(" ")[1]);

		System.out.println(" get bill value from web :" + getActBillValue);

		System.out.println(" act amount is :" + Walkin_With_Credits.providedCreditAmt);

		if (Walkin_With_Credits.providedCreditAmt == getActBillValue) {
			System.out.println(" Test Case Passed !! As Bill value is correct ");
		} else {
			System.out.println(" Test Case Failed !! As bill value is not correct, Billvalue should be "
					+ Walkin_With_Credits.providedCreditAmt + " instead of " + getActBillValue);
			takeScreenshot("creditBillValue.png");
		}

	}

	public static void verify_Credits_Used_BillValue() throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();

		Walkin_With_Credits.usingCreditsForBilling();

		OperatorLogout.operatorLogout();
		SubscriberLogin.subscriberLogin();

		wait("//a[@ng-reflect-router-link='/walkins']");
		driver.findElement(By.xpath("//a[@ng-reflect-router-link='/walkins']")).click();

		wait("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']");

		driver.findElement(By.xpath("//mat-select[@id='mat-select-7']//div[@class='mat-select-arrow']")).click();
		wait("//span[@class='mat-option-text'][contains(text(),'Today')]");
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Today')]")).click();

		wait("//input[@placeholder='Search walkins']");

		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys("8830175067");

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span");

		String getBillValue = driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[5]/span"))
				.getText();

		int getActBillValue = Integer.parseInt(getBillValue.trim().split(" ")[1]);

		System.out.println(" get bill value from web :" + getActBillValue);

		System.out.println(" act amount is :" + Walkin_With_Credits.actUsedCreditsBillValue);

		if (Walkin_With_Credits.actUsedCreditsBillValue == getActBillValue) {
			System.out.println(
					" Test Case Passed !! As Bill value is coming as '0' , if user has used credits for billing");
		} else {
			System.out.println(" Test Case Failed !! As bill value is not correct, Billvalue should be "
					+ Walkin_With_Credits.actUsedCreditsBillValue + " instead of " + getActBillValue);
			takeScreenshot("creditBillValue.png");
		}
	}

	public static void verify_UsingCreditsAndPaid_BillValue() throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();
		Walkin_With_Credits.addingCredits("", "1000", "500");
		// Walkin_With_Credits.addingWalkinForCredits("//span[text()='Crimping
		// (Starting) ( HAIR CUT ) ']");

	}

}
