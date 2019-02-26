package in.runo.subscriber.walkin_details;

import java.io.IOException;

import org.openqa.selenium.By;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.operator.walkins.service.Walkin_With_PartialPaid_Amount;
import in.runo.subscriber.login.SubscriberLogin;
import in.runo.subscriber.logout.SubscriberLogout;

public class BalancePayable extends Walkin_With_Individual_Discount {

	public static void verifyFullPaidAmount_BalancePayable(String mobileNumber, String customerName)
			throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();
		createWalkinsWithIndividualDiscount(mobileNumber, customerName, "20");
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
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys(mobileNumber);

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[6]");

		String getBalancePayable = driver.findElement(By
				.xpath("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[6]"))
				.getText();

		double actBalancePayable = Double.valueOf(getBalancePayable.trim().split(" ")[1]);

		if (expectedBalance == actBalancePayable) {
			System.out.println(" Test Case Passed !! As Balance Payable has taken correctly in walkin details page");

		} else {
			System.out.println(
					" Test Case Failed !! As Balance Payable has not taken correctly in walkin details, Payable balance should be"
							+ expectedBalance + " instead of :" + actBalancePayable);
			takeScreenshot("Sb_PayableBalance.png");
		}
		SubscriberLogout.sbLogout();

	}

	public static void verifyInitialPaidAmount_BalancePayable(String mobileNumber, String customerName)
			throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();
		Walkin_With_PartialPaid_Amount.verifyPartialPaidAmount(mobileNumber, customerName);
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
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys(mobileNumber);

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[6]");

		String getBalancePayable = driver.findElement(By
				.xpath("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[6]"))
				.getText();

		double actBalancePayable = Double.valueOf(getBalancePayable.trim().split(" ")[1]);

		if (expectedBalance == actBalancePayable) {
			System.out.println(" Test Case Passed !! As Balance Payable has taken correctly in walkin details page");

		} else {
			System.out.println(
					" Test Case Failed !! As Balance Payable has not taken correctly in walkin details, Payable balance should be"
							+ expectedBalance + " instead of :" + actBalancePayable);
			takeScreenshot("Sb_PayableBalance.png");
		}

		SubscriberLogout.sbLogout();

	}

	public static void verifyClearingPastDueAmount_BalancePayable(String mobileNumber)
			throws InterruptedException, IOException {

		OperatorLogin.operatorLogin();
		Walkin_With_PartialPaid_Amount.verifyPastDueAmount(mobileNumber);
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
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys(mobileNumber);

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[6]");

		String getBalancePayable = driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[6]"))
				.getText();

		double actBalancePayable = Double.valueOf(getBalancePayable.trim().split(" ")[1]);

		if (actualBalanceValue == actBalancePayable) {
			System.out.println(" Test Case Passed !! As Balance Payable has taken correctly in walkin details page");

		} else {
			System.out.println(
					" Test Case Failed !! As Balance Payable has not taken correctly in walkin details, Payable balance should be"
							+ actualBalanceValue + " instead of :" + actBalancePayable);
			takeScreenshot("Sb_PayableBalance.png");
		}

		SubscriberLogout.sbLogout();

	}
}
