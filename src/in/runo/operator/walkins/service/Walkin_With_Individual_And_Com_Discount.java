package in.runo.operator.walkins.service;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Walkin_With_Individual_And_Com_Discount extends Walkin_With_Individual_Discount {

	public static WebElement addCommonDiscount;
	public static WebElement enterCommonDiscount;
	public static WebElement applyCommonDiscount;
	public static double actualCommonDiscount;

	public static void createWalkinsWithIndividualAndCommonDiscount(String mobileNumber, String customerName, String indDisc, String OverallDiscInPercentage ) throws InterruptedException, IOException {

		customerPersonalDetails(mobileNumber, customerName);
		
		driver.findElement(By.xpath("//div[@class='ng-select-container']")).click();
		
		wait("//span[text()='Ironing (Long Hair)  ( HAIR CUT ) ']");
		driver.findElement(By.xpath("//span[text()='Ironing (Long Hair)  ( HAIR CUT ) ']")).click();
		driver.findElement(By.xpath("//select[@ng-reflect-name='employee']")).click();
		driver.findElement(By.xpath("//select[@ng-reflect-name='employee']")).click();

		wait("//*[text()=' Automation 2 ']");
		driver.findElement(By.xpath("//*[text()=' Automation 2 ']")).click();
		driver.findElement(By.xpath("//input[@maxlength='2']")).sendKeys(indDisc);

		driver.findElement(By.xpath("//button[@class='addSummary']")).click();

		// Validating bill summary title name

		if (billSummaryTitle.equals("SUMMARY")) {

			System.out.println(" Test Case Passed !! Bill summary text has matched with requiremnt");

		} else

		{
			System.out.println(" Test Case Failed !! Bill summary page title has not matched with requirement");

			takeScreenshot("SummaryPageTitle.png");
		}

		// Validating category name for selected service

		categoryName1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__categoryName']")).getText();

		if (categoryName1.equals("HAIR CUT")) {

			System.out.println(" Test Case Passed !! Category name of selected services is correct in summary page");
			takeScreenshot("CategoryNameIssue.png");

		} else {

			System.out.println(
					" Test Case Failed !! The category name of selected services is not correct as per the requirement");
			takeScreenshot("SelectedServiceIssue.png");
		}

		selectedEmployee1 = driver
				.findElement(By.xpath("//span[@class='summaryBox__service__employeeName medium ng-star-inserted']"))
				.getText();

		if (selectedEmployee1.equals("Employee : Automation 2")) {
			System.out.println(" Test Case Passed !! selected employee has taken successfully in summary page");

		} else {
			System.out.println(" Test Case Failed !! Selected employee is not correct");
			takeScreenshot("SelectedEmployeeIssue.png");

		}

		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();

		System.out.println(
				" Service price is :" + getPriceOfSelectedService1 + " ***" + getPriceOfSelectedService1.length());

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);
		System.out.println(" price is :" + priceOfSelectedService1);

		wait("//input[@maxlength='2']");

		String getProvidedDiscountInPercent = driver.findElement(By.xpath("//input[@maxlength='2']"))
				.getAttribute("value");

		getActualProvidedDiscount = Integer.parseInt(getProvidedDiscountInPercent);

		String getActualDiscount = driver
				.findElement(By.xpath("//span[@class='summaryBox__service__price__discount ng-star-inserted'] "))
				.getText();

		actualIndividualDiscount1 = Integer.parseInt(getActualDiscount.split(" ")[0]);

		double expIndidualDiscount = (priceOfSelectedService1 * getActualProvidedDiscount) / 100;

		if (actualIndividualDiscount1 == expIndidualDiscount) {

			System.out.println(" Test Case Passed !! Individual Discount amount has taken correctly in summary page");

			String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
			double actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);

			double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expIndidualDiscount) * 0.18);

			System.out
					.println(" service price : " + priceOfSelectedService1 + " and discount is : " + expectedDiscount);

			if (actualGstAmt == expectedGSTAmt) {
				System.out.println(" Test Case Passed !!  As GST amount is matched with expected GST, that is :"
						+ actualGstAmt + " = " + expectedGSTAmt);

				String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
						.getText();

				actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

				System.out.println(" text is +" + getActualPayable + " value is :" + actualAmountPayable);

				double expectedSubTotal = priceOfSelectedService1 - expIndidualDiscount;

				double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;

				if (actualAmountPayable == expectedTotalPayable) {

					System.out.println(" Test Case Passed !! Total Payable amount is correct ");

					String getAmountPaid = driver
							.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

					actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					System.out.println(" amount paid size is :" + getAmountPaid.length() + " paid amount is : "
							+ actualAmountPaid);

					double expectedAmountPaid = expectedTotalPayable;

					if (actualAmountPaid == expectedAmountPaid) {

						System.out.println(" Test Case Passed !! Amount Paid has taken correctly");

						String getBalanceValue = driver
								.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

						actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
						double expectedBalance = expectedTotalPayable - expectedAmountPaid;

						if (actualBalanceValue == expectedBalance) {
							System.out.println(" Test Case Passed !! Balance amount is correct in summary page");

							String getFullPaidAmount = driver
									.findElement(By.xpath("//input[@min='0' and @ng-reflect-is-disabled='false']"))
									.getAttribute("value");
							actualFullPaidAmount = Double.valueOf(getFullPaidAmount);

							WebElement addCommonDiscount = driver
									.findElement(By.xpath("//span[@class='addDiscount__text']"));

							addCommonDiscount.click();

							enterCommonDiscount = driver.findElement(By.xpath("//input[@placeholder='%']"));

							enterCommonDiscount.sendKeys(OverallDiscInPercentage);

							applyCommonDiscount = driver.findElement(By.xpath("//img[@class='percentage']"));

							applyCommonDiscount.click();

							String getActualCommonDiscount = driver
									.findElement(By.xpath("//span[@class='summaryBox__discount__value']")).getText();

							System.out.println(" actual provided common discount is " + getActualCommonDiscount
									+ " length is :" + getActualCommonDiscount.length());

							actualCommonDiscount = Double.valueOf(getActualCommonDiscount.trim().split(" ")[0]);

							System.out.println(" after converting price is :" + actualCommonDiscount);

							String getProvidedCommonDiscount = driver.findElement(By.xpath("//input[@placeholder='%']"))
									.getAttribute("value");

							System.out.println(" getting provided common discount" + getProvidedCommonDiscount);

							double providedCommonDiscount = Double.valueOf(getProvidedCommonDiscount);

							double expectedCommonDiscount = (expectedSubTotal * providedCommonDiscount) / 100;

							double expTotalDiscount = expIndidualDiscount + expectedCommonDiscount;

							System.out.println("Total discount is : " + expTotalDiscount + " individualDiscount is : "
									+ expIndidualDiscount + " Common Discount is : " + expectedCommonDiscount);

							double expectedSubTotalAfterCommonDiscount = priceOfSelectedService1 - expTotalDiscount;

							double expGstAfterCommonDiscount = twoDigitRounding(
									expectedSubTotalAfterCommonDiscount * 0.18);

							String getGstAmtForComDisc = driver
									.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
							double actualCommonDiscountGstAmount = Double
									.valueOf(getGstAmtForComDisc.trim().split(" ")[0]);
							System.out.println("**** " + getGstAmtForComDisc.length() + " value is :"
									+ actualCommonDiscountGstAmount);

							if (actualCommonDiscountGstAmount == expGstAfterCommonDiscount) {
								System.out.println(
										" Test Case Passed !! Gst amount is correct after prividing common discount "
												+ expGstAfterCommonDiscount);

								double expSubTotalAfterCommonDiscount = priceOfSelectedService1 - expTotalDiscount;

								double expTotalPayableAfterCommonDiscount = twoDigitRounding(
										expSubTotalAfterCommonDiscount + expGstAfterCommonDiscount);

								System.out.println(" Total payable ****** is : " + expTotalPayableAfterCommonDiscount);

								String getActPayableAfterComDiscount = driver
										.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
										.getText();

								double actAmtPayableAfterComDiscount = Double
										.valueOf(getActPayableAfterComDiscount.trim().split(" ")[0]);

								System.out.println(" text is +" + getActPayableAfterComDiscount + " value is :"
										+ actAmtPayableAfterComDiscount);

								if (actAmtPayableAfterComDiscount == expTotalPayableAfterCommonDiscount) {

									System.out.println(
											" Test Case Passed !! After providing common discount, Total Payable amount is correct ");

									double expAmountPaidAfterCommonDiscount = twoDigitRounding(
											expectedSubTotalAfterCommonDiscount + expGstAfterCommonDiscount);

									if (expTotalPayableAfterCommonDiscount == expAmountPaidAfterCommonDiscount) {

										System.out.println(
												" Test Case Passed !! After providing common discount, Paid Amount is correct");

										if (actualBalanceValue == expectedBalance) {

											System.out.println(
													" Test Case Passed !! After providing common discount, Balance value is correct");

											WebElement selectpaymentMethodDropDown = driver
													.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));
											selectpaymentMethodDropDown.click();
											WebElement selectPaymentMode = driver
													.findElement(By.xpath("//option[@ng-reflect-value='Google Pay']"));
											selectPaymentMode.click();
											WebElement submitWalkin = driver
													.findElement(By.xpath("//button[text()='Submit']"));
											submitWalkin.click();
											System.out.println(
													"Congratulation !! Test Case Passed with both Common_And_Individual Discount");

											wait("//button[text()='Create new walkin']");

											driver.findElement(By.xpath("//button[text()='Create new walkin']"))
													.click();

										} else {
											System.out.println(
													" Test Case Failed !! after providing common discount, Balance value is not correct");

											takeScreenshot("BalanceAmountAfterComDiscIssue.png");
										}

									}

									else {
										System.out.println(
												" Test Case Failed !! After providing common discount, Paid Amount is not correct as payable is :"
														+ expTotalPayableAfterCommonDiscount + " and paid is:"
														+ expAmountPaidAfterCommonDiscount);

										takeScreenshot("paidAmountAfterComDiscIssue.png");
									}

								} else {
									System.out.println(
											" Test Case Failed !! As after providing common discount, Total Payable amount is not matched with expected Total Payable");

									takeScreenshot("TotalPayableAfterComDiscIssue.png");
								}

							} else {
								System.out.println(
										" Test Case Failed !! Gst amount is not correct after providing common discount, Gst amount should be : Rs."
												+ expGstAfterCommonDiscount + " instead of Rs." + actualGstAmt);
								takeScreenshot("GstAmountAfterComDiscIssue.png");

							}

						} else {
							System.out.println(" Test Case Failed !! Balance amount is not correct in summary page");

							takeScreenshot("BalanceAmountIssue.png");

						}

					} else {
						System.out.println(
								" Test Case Failed !! Paid amount hasn't been taken correctly in summary page :"
										+ expectedAmountPaid);

						takeScreenshot("PaidAmountIssue.png");

					}
				} else {
					System.out.println(" Test Case Failed !! Total Payable amount is not correct");

					takeScreenshot("TotalPayableIssue.png");
				}

			}

			else {
				System.out.println(" Test Case Failed !! GST amount is not correct. as actual GST amount is : "
						+ actualGstAmt + " and expected GST amount is : " + expectedGSTAmt);

				takeScreenshot("GstAmountIssue.png");

			}

		} else {
			System.out.println(" Test Case Failed !! Individual Discount is not correct as actual Discount amount is : "
					+ actualIndividualDiscount1 + " and expected discount amount is : " + expIndidualDiscount);
			takeScreenshot("IndDiscountIssue.png");

		}

	}
}