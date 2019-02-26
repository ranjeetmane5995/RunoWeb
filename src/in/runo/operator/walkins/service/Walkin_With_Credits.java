package in.runo.operator.walkins.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class Walkin_With_Credits extends Walkin_With_Individual_Discount {

	public static int providedCreditAmt;
	public static double usedCreditValue;
	public static double usedCredits;
	public static double paidAmount;

	public static double actUsedCreditsBillValue;

	public static void addingWalkinForCredits(String serviceName) throws InterruptedException {
		customerPersonalDetails("8830175067", "Adding Credits");
		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();

		wait(serviceName);
		driver.findElement(By.xpath(serviceName)).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		driver.findElement(By.xpath("//*[text()=' Automation 3 ']")).click();
		WebElement addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();
		paymentMethod = driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));
		paymentMethod.click();
		driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		System.out.println(" Congratulation !!Walk-in has been created successfully for adding credits");

		wait("//button[text()='Create new walkin']");
		WebElement CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
		CreateWalkin.click();

	}

	public static void addingCredits(String CustomerNumber, String CreditValue, String CreditAmt)
			throws InterruptedException, IOException {
		customerPersonalDetails(CustomerNumber, "Adding Credits");

		providedCreditAmt = Integer.parseInt(CreditAmt);

		wait("//div[@class='creditsIcon col-sm-6 p-30 m-0']//span[@class='creditsIcon__txt']");
		String getPreCreditValue = driver
				.findElement(By.xpath("//div[@class='creditsIcon col-sm-6 p-30 m-0']//span[@class='creditsIcon__txt']"))
				.getText();

		double availableCreditValue = Double.valueOf(getPreCreditValue);

		System.out.println(" prefevious credit value is :" + availableCreditValue);

		wait("//span[contains(text(),'Add Credits')]");

		WebElement addCredit = driver.findElement(By.xpath("//span[contains(text(),'Add Credits')]"));
		addCredit.click();
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='addCredits__wrapper ng-star-inserted']")));

		driver.findElement(By
				.xpath("/html/body/app-dashboard/div/main/div/ng-component/div[3]/div/div[2]/div/div[2]/div/div/input"))
				.sendKeys(CreditValue);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div[3]/div/div[2]/div/div[2]/div/div/span[1]")));
		driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div[3]/div/div[2]/div/div[2]/div/div/span[1]"))
				.click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Credits']")));

		String getActCategoryName = driver.findElement(By.xpath("//span[text()='Credits']")).getText();
		String expCategoryName = "Credits";

		if (getActCategoryName.equals(expCategoryName)) {

			// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			System.out.println(" Test Case Passed !! As category name is matched with expected Categoryame");

			wait("//span[@class='summaryBox__service__price']//input[@type='number']");
			String getActCreditAmt = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price']//input[@type='number']"))
					.getAttribute("value");

			if (getActCreditAmt.equals(CreditValue)) {
				System.out.println(
						" Test Case Passed !! As Entire Credit Value has been updated automatically in ' To be Paid ' ");

				driver.findElement(By.xpath("//span[@class='summaryBox__service__price']//input[@type='number']"))
						.clear();

				driver.findElement(By.xpath("//span[@class='summaryBox__service__price']//input[@type='number']"))
						.sendKeys(CreditAmt);

				int expAmountPaid = Integer.parseInt(CreditAmt);

				String getActAmountPaid = driver.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']"))
						.getText();
				int actAmountpaid = Integer.parseInt(getActAmountPaid.trim().split(" ")[0]);

				System.out.println(" paid amount is :" + actAmountpaid);

				if (actAmountpaid == expAmountPaid) {

					System.out.println(" Test Case Passed !! As Amount paid has matched with expected amount paid");

					List<WebElement> modeList = driver.findElements(By.xpath(
							"//div[@class='nice-wrap']//select[@class='js-example-basic-single nice-textbox ng-untouched ng-pristine ng-valid']"));

					driver.findElement(By.xpath("//option[text()='Google Pay']")).click();

					driver.findElement(By.xpath("//button[text()='Submit']")).click();

					wait("//button[text()='Create new walkin']");

					driver.findElement(By.xpath("//button[text()='Create new walkin']")).click();

					System.out.println(" Congratulation !! Credit has been added into customer's account successfully");

				} else {
					System.out.println(
							" Test Case Failed !! As actual amount paid is not matched with expected amount paid");
					takeScreenshot("AmountPaid.png");
				}

			} else {
				System.out
						.println(" Test Case Failed !! As Credit value has not updated automatically in 'To be paid' ");
				takeScreenshot("CreditValue.png");
			}

		} else {
			System.out.println(" Test Case Failed !! As Category name is not matched with expected CategoryName");
			takeScreenshot("CreditCategoryName.png");

		}

	}

	public static void usingCreditsForBilling(String CustomerMobile) throws InterruptedException, IOException {
		customerPersonalDetails(CustomerMobile, "Adding Credits");
		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();

		wait("//span[text()='Gold Ritual  ( FACIALS ) ']");
		driver.findElement(By.xpath("//span[text()='Gold Ritual  ( FACIALS ) ']")).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		driver.findElement(By.xpath("//*[text()=' Automation 3 ']")).click();
		WebElement addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		wait("//span[@class='summaryBox__grandTotal__value']");
		String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
				.getText();

		String payable = getActualPayable.trim().split(" ")[0];

		double actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

		wait("//div[@class='creditsIcon col-sm-6 p-30 m-0']//span[@class='creditsIcon__txt']");

		String getPreCreditValue = driver
				.findElement(By.xpath("//div[@class='creditsIcon col-sm-6 p-30 m-0']//span[@class='creditsIcon__txt']"))
				.getText();

		double availableCreditValue = Double.valueOf(getPreCreditValue);

		if (actualAmountPayable <= availableCreditValue) {
			System.out.println(" Credit amount is is greater than total payable that is , credit value :"
					+ availableCreditValue + " and payable amount is :" + actualAmountPayable);

			driver.findElement(By.xpath(
					"/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[1]/div[2]/div/div[1]/div[2]/input"))
					.sendKeys(payable);

			driver.findElement(
					By.xpath("//div[@class='redeemCredits col-sm-6 p-0 m-0 ng-star-inserted']//button[@type='submit']"))
					.click();

			String getUsedCreditValue = driver.findElement(By.xpath("//span[@class='summaryBox__credits__value']"))
					.getText();

			usedCreditValue = Double.valueOf(getUsedCreditValue.trim().split(" ")[1]);

			actUsedCreditsBillValue = actualAmountPayable - usedCreditValue;

			if (usedCreditValue == actualAmountPayable) {
				System.out.println("Test Case Passed !! As used credit amount is showing correctly in summary page");

				String getPaidAmount = driver.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']"))
						.getText();

				double actPaidAmount = Double.valueOf(getPaidAmount.trim().split(" ")[0]);

				double expPaidAmount = actualAmountPayable - usedCreditValue;

				if (actPaidAmount == expPaidAmount) {

					System.out.println(" Test Case Passed !! As Paid amount is correct");

					String getBalanceAmount = driver
							.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

					double actBalanceAmount = Double.valueOf(getBalanceAmount.trim().split(" ")[0]);

					double expBalance = usedCreditValue - actualAmountPayable;

					System.out.println(" @@@ Used CREDIT VALUE IS : " + Walkin_With_Credits.usedCreditValue);
					System.out.println(" Amount payable is :" + actualAmountPayable);

					if (actBalanceAmount == expBalance) {
						System.out.println(" Test Case Passed !! As Balance Amount is correct");

						String getActPartialPaid = driver
								.findElement(By.xpath("//input[@class='nice-textbox ng-untouched ng-pristine']"))
								.getAttribute("value");

						double actPartialPaid = Double.valueOf(getActPartialPaid);

						double expPartialPaid = actualAmountPayable - usedCreditValue;

						if (actPaidAmount == expPartialPaid) {

							System.out.println(" Test Case Passed !! As partial paid amount has taken correct amount");

							wait("//select[@class='js-example-basic-single nice-textbox ng-untouched ng-pristine']");

							WebElement paymentMethod = driver.findElement(By.xpath(
									"//select[@class='js-example-basic-single nice-textbox ng-untouched ng-pristine']"));

							paymentMethod.click();

							driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();

							driver.findElement(By.xpath("//button[text()='Submit']")).click();

							wait("//button[text()='Create new walkin']");

							System.out.println(" Congratulation !! Customer has used credits fpr billing successfully");

							driver.findElement(By.xpath("//button[text()='Create new walkin']")).click();

						} else {
							System.out.println(" Test Case Failed !! As partial paid amount is not correct");
							takeScreenshot("partialPaidAmount.png");
						}

					} else {
						System.out.println(" Test Case Failed !! As Balance amount is not correct");
					}

				} else {
					System.out.println(" Test Case Failed !! As Paid amoount is not correct, actPaid is :"
							+ actPaidAmount + " and expPaid is:" + expPaidAmount);
					takeScreenshot("PaidAmount.png");
				}

			} else {
				System.out.println("Test Case Failed !! As used credit amount is not showing correctly ");
				takeScreenshot("UsedCreditAmount.png");
			}

		} else {
			System.out.println(" Test Case Failed !! As credit value is less than payable amount" + actualAmountPayable
					+ " = " + availableCreditValue);
			takeScreenshot("lessCreditValue.png");
		}

	}

	public static void partialCreditAndPaidAmount(String CustomerMobile) throws InterruptedException, IOException {

		customerPersonalDetails(CustomerMobile, "");

		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();

		wait("//span[text()='Skin Pearl Lightening  ( FACIALS ) ']");
		driver.findElement(By.xpath("//span[text()='Skin Pearl Lightening  ( FACIALS ) ']")).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		driver.findElement(By.xpath("//*[text()=' Automation 3 ']")).click();
		WebElement addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		wait("//span[@class='summaryBox__grandTotal__value']");
		String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
				.getText();

		String payable = getActualPayable.trim().split(" ")[0];

		double actPayable = Double.valueOf(payable);

		wait("//div[@class='creditsIcon col-sm-6 p-30 m-0']//span[@class='creditsIcon__txt']");

		String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
		String actualGstAmt = getGstAmount.trim().split(" ")[0];
		usedCredits = Double.valueOf(actualGstAmt);
		paidAmount = actPayable - usedCredits;
		wait("/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[1]/div[2]/div/div[1]/div[2]/input");
		driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[1]/div[2]/div/div[1]/div[2]/input"))
				.sendKeys(actualGstAmt);

		driver.findElement(
				By.xpath("//div[@class='redeemCredits col-sm-6 p-0 m-0 ng-star-inserted']//button[@type='submit']"))
				.click();

		wait("//select[@ng-reflect-is-disabled='false']");

		WebElement paymentMethod = driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

		paymentMethod.click();

		driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();

		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		wait("//button[text()='Create new walkin']");

		System.out.println(" Congratulation !! Walk-in is created with partial cash and credit amount");

		driver.findElement(By.xpath("//button[text()='Create new walkin']")).click();

	}

}
