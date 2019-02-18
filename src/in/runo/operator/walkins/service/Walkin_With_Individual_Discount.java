package in.runo.operator.walkins.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.runo.operator.login.OperatorLogin;

public class Walkin_With_Individual_Discount extends OperatorLogin {

	public static String date1;
	public static String date2;
	//public String CreditAmount;
	public static double getActualProvidedDiscount;
	public static String billSummaryTitle;
	public static String categoryName1;
	public static String selectedEmployee1;
	public static int priceOfSelectedService1;
	public static double actualIndividualDiscount1;
	public static double actualGstAmt;
	public static double actualAmountPayable;
	public static double actualAmountPaid;
	public static double actualBalanceValue;
	public static double actualFullPaidAmount;
	public static double expectedDiscount;
	public static double expectedGSTAmt;
	public static double expectedAmountPaid;
	public static double expectedBalance;
	public static WebElement selectingPaymentMode;
	public static WebElement submitWalkin;
	public static double expBillValue;
	public static double expectedSubTotal;
	public static WebElement selectServiceDropDownlist;
	public static WebElement selectEmpDropDownList;
	public static WebElement addSummary;
	public static WebElement CreateWalkin;
	public static WebElement paymentMethod;
	public static int actProvidedQuantity;
	public static double actBillValue1;
	public static double actBillValue2;
	public static WebDriverWait wait = new WebDriverWait(driver, 6);

	static double twoDigitRounding(double num) {
		return Math.round(num * 100) / 100.0;
	}

	public static TakesScreenshot ts = (TakesScreenshot) driver;

	public static void takeScreenshot(String FileName) throws IOException {
		Date d = new Date();
		date1 = d.toString();
		date2 = date1.replaceAll(":", "_");
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\Users\\ranje\\eclipse-workspace\\RunoWeb\\TakenScreenShot\\" + date2 + "_" + FileName);
		FileUtils.copyFile(srcFile, destFile);

	}

	public static void wait(String waitElement)

	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(waitElement)));

	}

	public static void customerPersonalDetails(String MobileNumber, String CustomerName) throws InterruptedException {

		wait("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']");
		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys(MobileNumber);

		try {

			wait("//span[@class='search-result-number']");
			if (driver.findElement(By.xpath("//span[@class='search-result-number']")) != null) {
				driver.findElement(By.xpath("//span[@class='search-result-number']")).click();
				System.out.println(" customer is  existing customer");
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

		}
	}

	public static void createWalkinsWithIndividualDiscount(String IndDiscount)
			throws InterruptedException, IOException {

		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		driver.findElement(By.xpath("//span[text()='Skin Pearl Lightening  ( FACIALS ) ']")).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		wait("//*[text()=' Automation 2 ']");
		driver.findElement(By.xpath("//*[text()=' Automation 2 ']")).click();
		driver.findElement(By.xpath("//input[@maxlength='2']")).sendKeys(IndDiscount);
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		wait("//span[text()=' Summary ']");
		billSummaryTitle = driver.findElement(By.xpath("//span[text()=' Summary ']")).getText();

		if (billSummaryTitle.equals("SUMMARY")) {

			System.out.println(" Test Case Passed !! Bill summary text has matched with requiremnt");

			categoryName1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__categoryName']"))
					.getText();

			if (categoryName1.equals("Bleach")) {

				System.out
						.println(" Test Case Passed !! Category name of selected services is correct in summary page");

				selectedEmployee1 = driver
						.findElement(
								By.xpath("//span[@class='summaryBox__service__employeeName medium ng-star-inserted']"))
						.getText();

				if (selectedEmployee1.equals("Employee : Automation 2")) {

					System.out.println(" Test Case Passed !! As selected employee has taken correctly in summary page");

				} else {
					System.out.println(" Test Case Failed !! Selected employee is not correct");

					takeScreenshot("EmployeeName.png");
				}
			} else {
				System.out.println(
						" Test Case Failed !! The category name of selected services is not correct as per the requirement");

				takeScreenshot("CategoryName.png");
			}

			String getPriceOfSelectedService1 = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price']")).getText();

			priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

			wait("//input[@maxlength='2']");

			String getProvidedDiscountInPercent = driver.findElement(By.xpath("//input[@maxlength='2']"))
					.getAttribute("value");

			getActualProvidedDiscount = Double.valueOf(getProvidedDiscountInPercent);

			wait("//span[@class='summaryBox__service__price__discount ng-star-inserted']");

			String getActualDiscount = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price__discount ng-star-inserted']"))
					.getText();

			actualIndividualDiscount1 = Double.valueOf(getActualDiscount.split(" ")[0]);

			double expectedDiscount = (priceOfSelectedService1 * getActualProvidedDiscount) / 100;

			if (actualIndividualDiscount1 == expectedDiscount) {

				System.out.println(
						" Test Case Passed !!As Individual Discount amount has taken correctly in summary page");

				String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']"))
						.getText();
				actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);
				
				double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expectedDiscount) * 0.18);

				if (actualGstAmt == expectedGSTAmt) {
					System.out.println(" Test Case Passed !! As GST amount is correct");

					String getActualPayable = driver
							.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']")).getText();

					actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

					expectedSubTotal = priceOfSelectedService1 - expectedDiscount;

					double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;
//
					if (actualAmountPayable == expectedTotalPayable) {

						System.out.println(
								" Test Case Passed !! As Total Payable amount is matched with expected, that is : "
										+ actualAmountPayable + " = " + expectedTotalPayable);

						String getAmountPaid = driver
								.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

						actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

						double expectedAmountPaid = expectedTotalPayable;

						if (expectedAmountPaid == expectedAmountPaid) {

							System.out.println(" Test Case Passed !! Amount Paid has taken correctly ");

							String getBalanceValue = driver
									.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

							actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
							double expectedBalance = expectedTotalPayable - expectedAmountPaid;

							if (actualBalanceValue == expectedBalance) {
								System.out.println(" Test Case Passed !! Balance amount is correct in summary page");

								wait("//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']");

								String getFullPaidAmount = driver.findElement(By.xpath(
										"//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']"))
										.getAttribute("value");

								actualFullPaidAmount = twoDigitRounding(Double.valueOf(getFullPaidAmount));

								double expFullPaidAmount = twoDigitRounding(expectedTotalPayable);
//
								if (actualFullPaidAmount == expFullPaidAmount) {
									System.out.println(" Test Case Passed !! paid amount has autofilled that is :"
											+ expectedTotalPayable);

									double expBalanceValue = expectedTotalPayable - expectedAmountPaid;

									if (actualBalanceValue == expBalanceValue) {
										System.out.println(
												" Test Case Passed !! remaining balance amount is correct in summary page");

										wait("//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']");
										String getPartialPaidAmounts = driver.findElement(By.xpath(
												"//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']"))
												.getAttribute("value");

										double actualPartial = Double.valueOf(getPartialPaidAmounts);

										double actualPartialPaidAmount = twoDigitRounding((actualPartial));

										double expPartialPaidAmt = twoDigitRounding((actualAmountPaid));
//
										if (actualAmountPaid == expPartialPaidAmt) {
											System.out.println(
													" Test Case Passed !! Paid amount has been updated automatically ");

											WebElement paymentMethod = driver
													.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

											paymentMethod.click();

											driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();

											driver.findElement(By.xpath("//button[text()='Submit']")).click();

											System.out.println(
													" Congratulation !!Test Case Passed with Individual Discount.");

											wait("//button[text()='Create new walkin']");

											CreateWalkin = driver
													.findElement(By.xpath("//button[text()='Create new walkin']"));
											CreateWalkin.click();

										} else {
											System.out.println(
													" Test Case Failed !! partial Paid amount hasn't updated ");

											takeScreenshot("PartialPaidIssue.png");
										}
									} else {
										System.out.println(
												" Test Case Failed !! As Balance amount is not matched with expected "
														+ expectedAmountPaid);
										takeScreenshot("GstAmountIssue.png");

									}
								}

								else {
									System.out.println(" Test Case Failed !! paid amount hasn't autofilled that is : ");

									takeScreenshot("AmountPaidIssue.png");

								}

							} else {
								System.out.println(
										"Test Case Failed !! Balance Amount is not matched with expected balance");

								takeScreenshot("BalanceAmountIssue.png");
							}

						} else {

							System.out.println(" Test Case Failed !! As Amount Paid is not matched with expected");

							takeScreenshot("AmoutPaidIssue.png");
						}

					} else {
						System.out.println(
								" Test Case Failed !! As Total Payable amount is not matched with expected Total Payable ");
						takeScreenshot("TotalPayableIssue.png");
					}

				} else {
					System.out.println(" Test Case Failed !! GST amount is not correct. as actual GST amount is : "
							+ actualGstAmt + " and expected GST amount is : " + expectedGSTAmt);

					takeScreenshot("GstAmountIssue.png");

				}

			} else {

				System.out
						.println(" Test Case Failed !! Individual Discount amount has taken correctly in summary page");
				takeScreenshot("Ind_Disc_Amt_Issue.png");
			}

		} else {

			System.out.println(" Test Case Failed !! As Summary page title is not matched with expected text");
			takeScreenshot("SummaryPageTitleIssue2.png");

		}

	}

}
