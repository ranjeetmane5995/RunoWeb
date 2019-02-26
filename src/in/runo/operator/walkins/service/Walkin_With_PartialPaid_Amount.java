package in.runo.operator.walkins.service;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Walkin_With_PartialPaid_Amount extends Walkin_With_Individual_And_Com_Discount {

	public void runMethod() throws InterruptedException, IOException {
		System.out.println("inside the runmethod");
		for (int i = 1; i <= 10; i++) {
			System.out.println("iterating loop" + i);
			verifyPartialPaidAmount("", "");
			verifyPastDueAmount("");
		}
	}

	public static void verifyPartialPaidAmount(String mobileNumber, String customerName)
			throws InterruptedException, IOException {

		customerPersonalDetails(mobileNumber, customerName);
		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		wait("//span[text()='Gold Ritual  ( FACIALS ) ']");
		driver.findElement(By.xpath("//span[text()='Gold Ritual  ( FACIALS ) ']")).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		driver.findElement(By.xpath("//option[text()=' Automation 3 ']")).click();
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		wait("//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']");

		WebElement enterPartialPaid = driver.findElement(By.xpath(
				"//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']"));

		enterPartialPaid.clear();
		enterPartialPaid.clear();
		enterPartialPaid.sendKeys("300");

		wait("//span[@class='summaryBox__balance__value']");

		String getBalanceValue = driver.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

		actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();
		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

		String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
				.getText();

		actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

		System.out.println(" getPayable : " + actualAmountPayable);

		expectedGSTAmt = twoDigitRounding(priceOfSelectedService1 * 0.18);
		double expectedTotalPayable = priceOfSelectedService1 + expectedGSTAmt;

		System.out.println(" service price is  and Gst is :" + priceOfSelectedService1 + " , " + expectedGSTAmt);

		String getAmountPaid = driver.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

		actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

		expectedBalance = expectedTotalPayable - actualAmountPaid;

		System.out.println(" Total payable Amount is  :" + expectedTotalPayable);

		System.out.println(" act is :" + actualBalanceValue + " exp is : " + expectedBalance);

		if (actualBalanceValue == expectedBalance) {

			System.out.println(
					" Test Case Passed !! As Balance Amount is matched with expected Balance Amount, that is : "
							+ actualBalanceValue + " = " + expectedBalance);

			paymentMethod = driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

			paymentMethod.click();

			selectingPaymentMode = driver.findElement(By.xpath("//option[@ng-reflect-value='Credit/Debit Card']"));
			selectingPaymentMode.click();

			submitWalkin = driver.findElement(By.xpath("//button[text()='Submit']"));
			submitWalkin.click();

			System.out.println("Test Case Passed !! Test Case Passed with Partial Paid Amount ");

			wait("//button[text()='Create new walkin']");

			CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
			CreateWalkin.click();

		} else {
			System.out.println(
					" Test Case Failed !! As Balance Amount is not matched with expected Balance Amount, that is : "
							+ actualBalanceValue + " = " + expectedBalance);

			takeScreenshot("BalanceAmountIssue.png");

		}
	}

	public static void verifyPastDueAmount(String mobileNumber) throws IOException, InterruptedException {

		wait("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']");
		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys(mobileNumber);

		wait("//span[@class='search-result-number']");

		if (driver.findElement(By.xpath("//span[@class='search-result-number']")) != null) {
			driver.findElement(By.xpath("//span[@class='search-result-number']")).click();
			System.out.println(" customer is  existing customer");

			wait("//div[@class='ng-select-container']");
			selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
			selectServiceDropDownlist.click();
			wait("//span[text()='Gold Ritual  ( FACIALS ) ']");
			driver.findElement(By.xpath("//span[text()='Gold Ritual  ( FACIALS ) ']")).click();
			selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
			selectEmpDropDownList.click();
			driver.findElement(By.xpath("//option[text()=' Automation 3 ']")).click();
			addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
			addSummary.click();

			wait("//span[@class='summaryBox__prevBal__value']");
			String getActPastDue = driver.findElement(By.xpath("//span[@class='summaryBox__prevBal__value']"))
					.getText();

			double actPastDue = Double.valueOf(getActPastDue.trim().split(" ")[0]);
			double expPastDue = expectedBalance;

			String getBalanceValue = driver
					.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

			actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
			
			if (actPastDue == expPastDue) {
				System.out.println(" Test Case Passes !! As Past Due amount is matched with expected Past Due Amount ");

				String getActTotalPayable = driver
						.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']")).getText();

				double actTotalPayable = Double.valueOf(getActTotalPayable.trim().split(" ")[0]);

				double expTotalPayable = (priceOfSelectedService1 + expectedGSTAmt) + expPastDue;

				expBillValue = priceOfSelectedService1 + expectedGSTAmt;

				if (actTotalPayable == expTotalPayable) {

					System.out.println(" Test Case Passed !! As Total Payable is matched with expected Total Payable");

					paymentMethod = driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));
					paymentMethod.click();

					selectingPaymentMode = driver.findElement(By.xpath("//option[@ng-reflect-value='Google Pay']"));
					selectingPaymentMode.click();

					submitWalkin = driver.findElement(By.xpath("//button[text()='Submit']"));
					submitWalkin.click();
					wait("//button[text()='Create new walkin']");

					CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
					CreateWalkin.click();

					System.out.println(
							" Congratulation !!  Whole Test Case for Partial Paid and Past Due Amount has successfully verified !!");

				} else {
					System.out
							.println(" Test Case Failed !! As Total Payable is not matched with expected Total Payable"
									+ actTotalPayable + " = " + expTotalPayable);
					takeScreenshot("TotalPayableIssue.png");

				}

			} else {
				System.out.println(" Test Case Failed !! As Past due amount is not matched with expected, that is :  "
						+ actPastDue + " = " + expPastDue);
				takeScreenshot("PastDueAmountIssue.png");
			}

		} else {
			System.out
					.println(" Test Case Failed !! As customer is not existing customer to validate past due amount ");

		}

	}

}
