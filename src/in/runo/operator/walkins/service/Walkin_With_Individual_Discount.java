package in.runo.operator.walkins.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import in.runo.operator.login.OperatorLogin;

public class Walkin_With_Individual_Discount extends OperatorLogin {

	int getActualProvidedDiscount;
	public static String billSummaryTitle;
	public static String categoryName1;
	String selectedEmployee1;
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

	public double twoDigitRounding(double num) {
		return Math.round(num * 100) / 100.0;
	}

	public void createWalkins_With_Individual_Discount() throws InterruptedException {

		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys("9354154121");

		Thread.sleep(1000);

		try {

			if (driver.findElement(By.xpath("//span[@class='search-result-number']")) != null) {
				driver.findElement(By.xpath("//span[@class='search-result-number']")).click();
				System.out.println(" customer is  existing customer");
			}
		} catch (Exception c) {
			driver.findElement(
					By.xpath("//input[@class='nice-textbox customerName ng-untouched ng-pristine ng-invalid']"))
					.sendKeys("Web Automation For IndDiscount");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[text	()='Female']")).click();
		}

		driver.findElement(By.xpath("//div[@class='ng-select-container']")).click();
		driver.findElement(By.xpath("//span[text()='Full Legs  ( Bleach ) ']")).click();
		driver.findElement(By.xpath("//select[@ng-reflect-name='employee']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()=' Automation 8 ']")).click();
		driver.findElement(By.xpath("//input[@maxlength='2']")).sendKeys("11");
		driver.findElement(By.xpath("//button[@class='addSummary']")).click();

		billSummaryTitle = driver.findElement(By.xpath("//span[text()=' Summary ']")).getText();

		if (billSummaryTitle.equals("SUMMARY")) {

			System.out.println(" Congratulation !! Bill summary text has matched with requiremnt");

			categoryName1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__categoryName']"))
					.getText();

			if (categoryName1.equals("Bleach")) {

				System.out.println(" Congratulation !! Category name of selected services is correct in summary page");

				selectedEmployee1 = driver
						.findElement(
								By.xpath("//span[@class='summaryBox__service__employeeName medium ng-star-inserted']"))
						.getText();

				if (selectedEmployee1.equals("Employee : Automation 8")) {

					System.out.println(" Congratulation !! selected employee has taken successfully in summary page");
				} else {
					System.out.println(" Test Case Failed !! Selected employee is not correct");
				}
			} else {
				System.out.println(
						" Test Case Failed !! The category name of selected services is not correct as per the requirement");
			}

			String getPriceOfSelectedService1 = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price']")).getText();

			System.out.println(
					" Service price is :" + getPriceOfSelectedService1 + " ***" + getPriceOfSelectedService1.length());

			priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);
			System.out.println(" price is :" + priceOfSelectedService1);

			Thread.sleep(3000);

			String getProvidedDiscountInPercent = driver.findElement(By.xpath("//input[@maxlength='2']"))
					.getAttribute("value");
			Thread.sleep(3000);

			System.out.println(" value is " + getProvidedDiscountInPercent);

			getActualProvidedDiscount = Integer.parseInt(getProvidedDiscountInPercent);

			String getActualDiscount = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price__discount ng-star-inserted'] "))
					.getText();

			actualIndividualDiscount1 = Double.valueOf(getActualDiscount.split(" ")[0]);

			double expectedDiscount = (priceOfSelectedService1 * getActualProvidedDiscount) / 100;

			if (actualIndividualDiscount1 == expectedDiscount) {

				System.out.println(" Congratulation !! Individual Discount amount has taken correctly in summary page");

				String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']"))
						.getText();
				actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);
				System.out.println("**** " + getGstAmount.length() + " value is :" + actualGstAmt);

				double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expectedDiscount) * 0.18);

				if (actualGstAmt == expectedGSTAmt) {
					System.out.println(" Congratulations !! GST amount is correct");

					String getActualPayable = driver
							.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']")).getText();

					actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

					System.out.println(" text is +" + getActualPayable + " value is :" + actualAmountPayable);

					double expectedSubTotal = priceOfSelectedService1 - expectedDiscount;

					double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;
//
					if (actualAmountPayable == expectedTotalPayable) {

						System.out.println(" Congratulation !! Total Payable amount is correct ");

						String getAmountPaid = driver
								.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

						actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

						System.out.println(" amount paid size is :" + getAmountPaid.length() + " paid amount is : "
								+ actualAmountPaid);

						double expectedAmountPaid = expectedTotalPayable;

						if (expectedAmountPaid == expectedTotalPayable) {

							System.out.println(" Congratulation !! Amount Paid has taken correctly");

							String getBalanceValue = driver
									.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

							actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
							double expectedBalance = expectedTotalPayable - expectedAmountPaid;

							if (actualBalanceValue == expectedBalance) {
								System.out.println(" Congratulation !! Balance amount is correct in summary page");

								String getFullPaidAmount = driver
										.findElement(By.xpath("//input[@min='0' and @ng-reflect-is-disabled='false']"))
										.getAttribute("value");

								actualFullPaidAmount = Double.valueOf(getFullPaidAmount);
//
								if (actualFullPaidAmount == expectedTotalPayable) {
									System.out.println(" Congratulation!! paid amount has autofilled that is :"
											+ expectedTotalPayable);

									System.out.println(" get value : " + getBalanceValue + " actaul value : "
											+ actualBalanceValue);

									double expectedBalancePayableValue = expectedTotalPayable - expectedAmountPaid;

									if (actualBalanceValue == expectedBalancePayableValue) {
										System.out.println(
												" Congratulation !! remaining balance amount is correct in summary page");

										String getPartialPaidAmounts = driver
												.findElement(By.xpath("//input[@ng-reflect-is-disabled='false']"))
												.getAttribute("value");

										System.out.println(" dynamic value is :" + getPartialPaidAmounts);

										double actualPartial = Double.valueOf(getPartialPaidAmounts);

										double actualPartialPaidAmount = twoDigitRounding((actualPartial));

										System.out.println(" actual Dyanamic paid value : " + actualPartialPaidAmount);

										double expectedDynamicPaidValue = twoDigitRounding((actualAmountPaid));
//
										if (actualAmountPaid == expectedDynamicPaidValue) {
											System.out.println(
													" Congratulation !! Paid amount has been updated automatically ");

								WebElement paymentMethod = 	driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));
								
								paymentMethod.click();

										}
										driver.findElement(By.xpath("//option[@ng-reflect-value='Testing']")).click();

										driver.findElement(By.xpath("//button[text()='Submit']")).click();

										System.out.println(
												" Congratulation !! Walk-in with individual discount for service has successfully verified");

										Thread.sleep(2000);

										driver.findElement(By.xpath("//button[text()='Create new walkin']")).click();
									} else {
										System.out.println(
												" Test Case Failed !! Paid amount hasn't been taken correctly in summary page :"
														+ expectedAmountPaid);
									}
								}
								// Thread.sleep(1000);

								else {
									System.out.println(" Opps  !! remaining balance is not correct");
								}

							} else {
								System.out.println("Test Case Failed !! Amount paid has not autofilled as it coming : "
										+ actualAmountPaid);
							}

						} else {
							System.out.println(" Test Case Failed !! Balance amount is not correct in summary page");
						}

					} else {
						System.out.println(
								" Test Case Failed !! Amount paid has not been updated automatically. Amount paid is "
										+ expectedAmountPaid);
					}

				} else {
					System.out.println(" Test Case Failed !! Total Payable amount is not correct");

				}

			} else {
				System.out.println(" Test Case Failed !! GST amount is not correct. as actual GST amount is : "
						+ actualGstAmt + " and expected GST amount is : " + expectedGSTAmt);
			}

		} else {

			System.out.println(" Test Case Failed !! Individual Discount is not correct as actual Discount amount is : "
					+ actualIndividualDiscount1 + " and expected discount amount is : " + expectedDiscount);
		}

		
	}

}
