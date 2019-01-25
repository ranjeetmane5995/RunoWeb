package in.runo.operator.walkins.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Walkins_With_Individual_And_Common_Discount extends Walkin_With_Individual_Discount {

	public double twoDigitRounding(double num) {
		return Math.round(num * 100) / 100.0;
	}

	public void createWalkins_With_Individual_And_Common_Discount() throws InterruptedException {

		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys("9354154121");

		Thread.sleep(3000);

		// Handling noSuchElementException for existing customer
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

		driver.findElement(By.xpath("//div[@class='ng-select-container']")).click();
		driver.findElement(By.xpath("//span[text()='Full Legs  ( Bleach ) ']")).click();
		driver.findElement(By.xpath("//select[@ng-reflect-name='employee']")).click();
		driver.findElement(By.xpath("//select[@ng-reflect-name='employee']")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()=' Automation 8 ']")).click();
		driver.findElement(By.xpath("//input[@maxlength='2']")).sendKeys("13");

		driver.findElement(By.xpath("//button[@class='addSummary']")).click();

		// Validating bill summary title name

		if (billSummaryTitle.equals("SUMMARY")) {

			System.out.println(" Congratulation !! Bill summary text has matched with requiremnt");

		} else

		{
			System.out.println(" Test Case Failed !! Bill summary page title has not matched with requirement");
		}

		// Validating category name for selected service

		categoryName1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__categoryName']")).getText();

		if (categoryName1.equals("Bleach")) {

			System.out.println(" Congratulation !! Category name of selected services is correct in summary page");

		} else {

			System.out.println(
					" Test Case Failed !! The category name of selected services is not correct as per the requirement");
		}

		selectedEmployee1 = driver
				.findElement(By.xpath("//span[@class='summaryBox__service__employeeName medium ng-star-inserted']"))
				.getText();

		if (selectedEmployee1.equals("Employee : Automation 8")) {
			System.out.println(" Congratulation !! selected employee has taken successfully in summary page");

		} else {
			System.out.println(" Test Case Failed !! Selected employee is not correct");

		}

		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();

		System.out.println(
				" Service price is :" + getPriceOfSelectedService1 + " ***" + getPriceOfSelectedService1.length());

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);
		System.out.println(" price is :" + priceOfSelectedService1);

		Thread.sleep(3000);

		String getProvidedDiscountInPercent = driver.findElement(By.xpath("//input[@maxlength='2']"))
				.getAttribute("value");
		Thread.sleep(3000);

		System.out.println(" value is :: " + getProvidedDiscountInPercent);

		getActualProvidedDiscount = Integer.parseInt(getProvidedDiscountInPercent);

		String getActualDiscount = driver
				.findElement(By.xpath("//span[@class='summaryBox__service__price__discount ng-star-inserted'] "))
				.getText();

		actualIndividualDiscount1 = Integer.parseInt(getActualDiscount.split(" ")[0]);

		double expectedDiscount = (priceOfSelectedService1 * getActualProvidedDiscount) / 100;

		if (actualIndividualDiscount1 == expectedDiscount) {

			System.out.println(" Congratulation !! Individual Discount amount has taken correctly in summary page");

			String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
			double actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);
			System.out.println("**** " + getGstAmount.length() + " value is :" + actualGstAmt);

			double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expectedDiscount) * 0.18);

			if (actualGstAmt == expectedGSTAmt) {
				System.out.println(" Congratulations !! GST amount is correct");

				String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
						.getText();

				actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

				System.out.println(" text is +" + getActualPayable + " value is :" + actualAmountPayable);

				double expectedSubTotal = priceOfSelectedService1 - expectedDiscount;

				double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;

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

							WebElement addCommonDiscount = driver
									.findElement(By.xpath("//span[@class='addDiscount__text']"));

							addCommonDiscount.click();

							WebElement enterCommonDiscount = driver.findElement(By.xpath("//input[@placeholder='%']"));

							enterCommonDiscount.sendKeys("28");

							WebElement applyCommonDiscount = driver.findElement(By.xpath("//img[@class='percentage']"));

							applyCommonDiscount.click();

							String getActualCommonDiscount = driver
									.findElement(By.xpath("//span[@class='summaryBox__discount__value']")).getText();

							System.out.println(" actual provided common discount is " + getActualCommonDiscount
									+ " length is :" + getActualCommonDiscount.length());

							double actualCommonDiscount = Double.valueOf(getActualCommonDiscount.trim().split(" ")[0]);

							System.out.println(" after converting price is :" + actualCommonDiscount);

							String getProvidedCommonDiscount = driver.findElement(By.xpath("//input[@placeholder='%']"))
									.getAttribute("value");

							System.out.println(" getting provided common discount" + getProvidedCommonDiscount);

							double providedCommonDiscount = Double.valueOf(getProvidedCommonDiscount);

							double expectedCommonDiscount = (expectedSubTotal * providedCommonDiscount) / 100;

							double expTotalDiscount = expectedDiscount + expectedCommonDiscount;

							System.out.println("Total discount is : " + expTotalDiscount + " individualDiscount is : "
									+ expectedDiscount + " Common Discount is : " + expectedCommonDiscount);

							double expectedSubTotalAfterCommonDiscount = priceOfSelectedService1 - expTotalDiscount;

							double expGstAfterCommonDiscount = twoDigitRounding(
									expectedSubTotalAfterCommonDiscount * 0.18);
							// double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 -
							// expectedDiscount) * 0.18);

							String getCommonDiscountGstAmount = driver
									.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
							double actualCommonDiscountGstAmount = Double
									.valueOf(getCommonDiscountGstAmount.trim().split(" ")[0]);
							System.out.println("**** " + getCommonDiscountGstAmount.length() + " value is :"
									+ actualCommonDiscountGstAmount);

							if (actualCommonDiscountGstAmount == expGstAfterCommonDiscount) {
								System.out.println(
										" Congratulation !! Gst amount is correct after prividing common discount "
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
											" Congratulation !! After providing common discount, Total Payable amount is correct ");

									double expAmountPaidAfterCommonDiscount = twoDigitRounding(
											expectedSubTotalAfterCommonDiscount + expGstAfterCommonDiscount);

									if (expTotalPayableAfterCommonDiscount == expAmountPaidAfterCommonDiscount) {

										System.out.println(
												" Congratulations !! After providing common discount, Paid Amount is correct");

										if (actualBalanceValue == expectedBalance) {

											System.out.println(
													" Congratulation !! After providing common discount, Balance value is correct");

											WebElement paymentMethod = driver
													.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));
											paymentMethod.click();
											WebElement selectPaymentMode = driver
													.findElement(By.xpath("//option[@ng-reflect-value='Testing']"));
											selectPaymentMode.click();
											WebElement submitWalkin = driver
													.findElement(By.xpath("//button[text()='Submit']"));
											submitWalkin.click();

											System.out.println(" Succccessfully walk-in is done !!!!!!");

										} else {
											System.out.println(
													" Oops !! after providing common discount, Balance value is not correct");
										}

									}

									else {
										System.out.println(
												" Oops !! After providing common discount, Paid Amount is not correct as payable is :"
														+ expTotalPayableAfterCommonDiscount + " and paid is:"
														+ expAmountPaidAfterCommonDiscount);
									}

								}

							} else {
								System.out.println(
										" Oops !! Gst amount is not correct after providing common discount, Gst amount should be : Rs."
												+ expGstAfterCommonDiscount + " instead of Rs." + actualGstAmt);
							}

						} else {
							System.out.println(" Test Case Failed !! Balance amount is not correct in summary page");
						}

					} else {
						System.out.println(
								" Test Case Failed !! Paid amount hasn't been taken correctly in summary page :"
										+ expectedAmountPaid);

					}
				} else {
					System.out.println(" Test Case Failed !! Total Payable amount is not correct");

				}

			}

			else {
				System.out.println(" Test Case Failed !! GST amount is not correct. as actual GST amount is : "
						+ actualGstAmt + " and expected GST amount is : " + expectedGSTAmt);
			}

		} else {
			System.out.println(" Test Case Failed !! Individual Discount is not correct as actual Discount amount is : "
					+ actualIndividualDiscount1 + " and expected discount amount is : " + expectedDiscount);
		}

	}
}