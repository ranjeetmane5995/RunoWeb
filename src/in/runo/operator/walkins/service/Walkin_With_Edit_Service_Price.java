package in.runo.operator.walkins.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.runo.operator.login.OperatorLogin;

public class Walkin_With_Edit_Service_Price extends Walkin_With_Individual_Discount {

	public static WebElement selectServiceDropDownlist;
	public static WebElement selectService;
	public static WebElement selectEmpDropDownList;
	public static WebElement selectEmp;
	public static WebElement addSummary;
	public static WebElement paymentMethod;

	
	public static void editingServicePrice() throws InterruptedException, IOException {
		customerPersonalDetails("9028123122", "EditingServicePrice");
		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		selectService = driver.findElement(By.xpath("//span[text()='Gold Ritual  ( FACIALS ) ']"));
		selectService.click();
		wait("//input[@min='0']");
		WebElement editServicePrice = driver.findElement(By.xpath("//input[@min='0']"));
		editServicePrice.clear();
		editServicePrice.sendKeys("1000");
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		selectEmp = driver.findElement(By.xpath("//*[text()=' Automation 2 ']"));
		selectEmp.click();
		driver.findElement(By.xpath("//input[@maxlength='2']")).sendKeys("25");
		WebElement addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();
		int priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);
		String getProvidedDiscountInPercent = driver.findElement(By.xpath("//input[@maxlength='2']"))
				.getAttribute("value");
		Double getActualProvidedDiscount = Double.valueOf(getProvidedDiscountInPercent);
		String getActualDiscount = driver
				.findElement(By.xpath("//span[@class='summaryBox__service__price__discount ng-star-inserted'] "))
				.getText();

		double actualIndividualDiscount1 = Double.valueOf(getActualDiscount.split(" ")[0]);

		double expectedDiscount = (priceOfSelectedService1 * getActualProvidedDiscount) / 100;
		if (actualIndividualDiscount1 == expectedDiscount) {

			System.out.println(" Test Case Passed !!As Individual Discount amount has taken correctly in summary page");

			String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
			double actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);
			System.out.println("**** " + getGstAmount.length() + " value is :" + actualGstAmt);

			double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expectedDiscount)) * 0.18;
			if (actualGstAmt == expectedGSTAmt) {
				System.out.println(" Test Case Passed !! As GST amount is correct");

				String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
						.getText();

				double actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

				System.out.println(" text is +" + getActualPayable + " value is :" + actualAmountPayable);

				double expectedSubTotal = priceOfSelectedService1 - expectedDiscount;

				double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;
//
				if (actualAmountPayable == expectedTotalPayable) {

					System.out
							.println(" Test Case Passed !! As Total Payable amount is matched with expected, that is : "
									+ actualAmountPayable + " = " + expectedTotalPayable);

					paymentMethod = driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

					paymentMethod.click();

					driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();
					driver.findElement(By.xpath("//button[text()='Submit']")).click();

					System.out.println(
							" Congratulation !!Test Case Passed with Editing Service Price and Individual Discount");

					wait("//button[text()='Create new walkin']");

					WebElement CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
					CreateWalkin.click();

				}
			} else {
				System.out.println(" Test Case Failed !! GST amount is not correct. as actual GST amount is : "
						+ actualGstAmt + " and expected GST amount is : " + expectedGSTAmt);

				takeScreenshot("GstAmountIssue.png");
			}
		} else {

			System.out.println(" Test Case Failed !! Individual Discount amount has taken correctly in summary page");
			takeScreenshot("Ind_Disc_Amt_Issue.png");
		}
	}

	public static void editingServiceQuantity(String noOfService) throws InterruptedException, IOException {
		customerPersonalDetails("9028123121", "editServiceQuantity");
		WebElement selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		wait("//span[text()='Skin Pearl Lightening  ( FACIALS ) ']");
		WebElement selectService = driver.findElement(By.xpath("//span[text()='Skin Pearl Lightening  ( FACIALS ) ']"));
		selectService.click();
		WebElement editServicePrice = driver.findElement(By.xpath("//input[@min='0']"));
		editServicePrice.clear();
		editServicePrice.sendKeys("1000");
		WebElement enterServiceQuantity = driver.findElement(By.xpath("//input[@onfocus='this.select();']"));
		enterServiceQuantity.clear();
		enterServiceQuantity.sendKeys(noOfService);

		String getServiceQuantity = driver.findElement(By.xpath("//input[@onfocus='this.select();']"))
				.getAttribute("value");
		actProvidedQuantity = Integer.parseInt(getServiceQuantity);

		WebElement selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();

		WebElement selectEmp = driver.findElement(By.xpath("//*[text()=' Automation 2 ']"));
		selectEmp.click();
		driver.findElement(By.xpath("//input[@maxlength='2']")).sendKeys("12");
		WebElement addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		String getProvidedQuantity = driver.findElement(By.xpath("//input[@onfocus='this.select();']"))
				.getAttribute("value");
		int actualProvidedQuantity = Integer.parseInt(getProvidedQuantity);
		String getEditedServicePrice = driver.findElement(By.xpath("//input[@min='0']")).getAttribute("value");
		int actualEditedServicePrice = Integer.parseInt(getEditedServicePrice);
		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();
		int priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);
		int expServicePrice = actualEditedServicePrice * actualProvidedQuantity;
		System.out.println(" Service Price is : " + expServicePrice);

		if (priceOfSelectedService1 == expServicePrice) {
			System.out
					.println("Test Case Passed !! As Service price is matched with expected service price, that is : +"
							+ priceOfSelectedService1 + " = " + expServicePrice);

			String getProvidedDiscountInPercent = driver.findElement(By.xpath("//input[@maxlength='2']"))
					.getAttribute("value");
			double actProvidedIndDisc = Double.valueOf(getProvidedDiscountInPercent);

			String getActIndDiscount = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price__discount ng-star-inserted']"))
					.getText();
			double actualIndividualDiscount1 = Double.valueOf(getActIndDiscount.split(" ")[0]);

			double expectedDiscount = (expServicePrice * actProvidedIndDisc) / 100;

			if (actualIndividualDiscount1 == expectedDiscount) {

				System.out.println(
						" Test Case Passed !!As Individual Discount amount has taken correctly in summary page");

				String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']"))
						.getText();
				double actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);
				System.out.println("**** " + getGstAmount.length() + " value is :" + actualGstAmt);

				double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expectedDiscount) * 0.18);

				if (actualGstAmt == expectedGSTAmt) {
					System.out.println(" Test Case Passed !! As GST amount is correct");

					String getActualPayable = driver
							.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']")).getText();

					double actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

					System.out.println(" text is +" + getActualPayable + " value is :" + actualAmountPayable);

					double expectedSubTotal = priceOfSelectedService1 - expectedDiscount;

					double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;
//
					if (actualAmountPayable == expectedTotalPayable) {

						System.out.println(
								" Test Case Passed !! As Total Payable amount is matched with expected, that is : "
										+ actualAmountPayable + " = " + expectedTotalPayable);

						String getAmountPaid = driver
								.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

						double actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

						System.out.println(" amount paid size is :" + getAmountPaid.length() + " paid amount is : "
								+ actualAmountPaid);

						double expectedAmountPaid = expectedTotalPayable;

						if (expectedAmountPaid == expectedAmountPaid) {

							System.out.println(" Test Case Passed !! Amount Paid has taken correctly ");

							String getBalanceValue = driver
									.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

							double actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
							double expectedBalance = expectedTotalPayable - expectedAmountPaid;

							if (actualBalanceValue == expectedBalance) {
								System.out.println(" Test Case Passed !! Balance amount is correct in summary page");

								wait("//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']");

								String getFullPaidAmount = driver.findElement(By.xpath(
										"//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']"))
										.getAttribute("value");

								System.out.println(" Getting Paid Amount" + getFullPaidAmount);

								double actualFullPaidAmount = twoDigitRounding(Double.valueOf(getFullPaidAmount));

								double expFullPaidAmount = twoDigitRounding(expectedTotalPayable);

								if (actualFullPaidAmount == expFullPaidAmount) {
									System.out.println(" Test Case Passed !! paid amount has autofilled that is :"
											+ expectedTotalPayable);

									double expBalanceValue = expectedTotalPayable - expectedAmountPaid;

									if (actualBalanceValue == expBalanceValue) {
										System.out.println(
												" Test Case Passed !! remaining balance amount is correct in summary page");

										String getPartialPaidAmounts = driver.findElement(By.xpath(
												"//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']"))
												.getAttribute("value");

										System.out.println(" dynamic value is :" + getPartialPaidAmounts);

										double actualPartial = Double.valueOf(getPartialPaidAmounts);

										double actualPartialPaidAmount = twoDigitRounding((actualPartial));

										System.out.println(" actual Dyanamic paid value : " + actualPartialPaidAmount);

										double expPartialPaidAmt = twoDigitRounding((actualAmountPaid));

										if (actualAmountPaid == expPartialPaidAmt) {
											System.out.println(
													" Test Case Passed !! Paid amount has been updated automatically ");

											WebElement paymentMethod = driver
													.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

											paymentMethod.click();

											driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();

											driver.findElement(By.xpath("//button[text()='Submit']")).click();

											System.out.println(
													" Congratulation !!Test Case Passed with editing Quantity, Editing Service Price and Ind Discount");

											wait("//button[text()='Create new walkin']");

											WebElement CreateWalkin = driver
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
								" Test Case Failed !! As Total Payable amount is not matched with expected Total Payable, That is :"
										+ actualAmountPayable + " = " + expectedTotalPayable);
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

		} else

		{
			System.out.println("Test Case Failed !! As Service price is not matched with expected service price");

			takeScreenshot("EditedServicePrice.png");
		}

	}

}