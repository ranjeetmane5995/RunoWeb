package in.runo.operator.walkins.service;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Walkin_With_BundledOffer extends Walkin_With_Individual_Discount {

	/*
	 * Below test case contains :
	 * 
	 * 1. Adding Single bundled offer with individual discount
	 * 2. Add walk-in for free services of single bundled offer 
	 * 3. Adding Multiple Bundled Offer with common Discount 
	 * 4. Add walk-in with free services of multiple bundled offer 
	 * 5. validating that after completing free service, if user has taken the same service then this service should be considered as paid service.
	 */

	public static void addSingleBundledOfferWithIndividualDiscount() throws InterruptedException, IOException {

		customerPersonalDetails("9026665352", "Adding using bundled offer");

		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		driver.findElement(By.xpath("//span[text()='3+1 Fruit Facial  (FACIALS)  ( Bundled offer ) ']")).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		wait("//*[text()=' Automation 2 ']");
		driver.findElement(By.xpath("//*[text()=' Automation 2 ']")).click();
		driver.findElement(By.xpath("//input[@maxlength='2']")).sendKeys("25");
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		wait("//span[@class='summaryBox__service__categoryName']");

		categoryName1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__categoryName']")).getText();

		if (categoryName1.equals("Bundled offer")) {

			System.out.println(" Test Case Passed !! Category name of selected services is correct in summary page");

			selectedEmployee1 = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__employeeName medium ng-star-inserted']"))
					.getText();

			if (selectedEmployee1.equals("Employee : Automation 2")) {

				System.out.println(" Test Case Passed !! As selected employee has taken correctly in summary page");

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

				System.out.println(" price is :" + priceOfSelectedService1 + " ind disc :" + getActualDiscount
						+ " exp discount:" + expectedDiscount);

				if (actualIndividualDiscount1 == expectedDiscount) {

					System.out.println(" Test Case Passed !! As Individual discount is correct");

					String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']"))
							.getText();
					actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);

					double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expectedDiscount) * 0.18);

					if (actualGstAmt == expectedGSTAmt) {
						System.out.println(" Test Case Passed !! As Gst amount is correct");

						String getActualPayable = driver
								.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']")).getText();

						actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

						double expectedSubTotal = priceOfSelectedService1 - expectedDiscount;

						double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;

						if (actualAmountPayable == expectedTotalPayable) {
							System.out.println(" Test Case Passed !! As Total Payable amount is correct");

							String getAmountPaid = driver
									.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

							actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

							double expectedAmountPaid = expectedTotalPayable;

							if (expectedAmountPaid == expectedAmountPaid) {
								System.out.println(" Test Case Passed !! As paid amount is correct");

								String getBalanceValue = driver
										.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

								actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
								double expectedBalance = expectedTotalPayable - expectedAmountPaid;

								if (actualBalanceValue == expectedBalance) {
									System.out
											.println(" Test Case Passed !! Balance amount is correct in summary page");

									String getFullPaidAmount = driver.findElement(By.xpath(
											"//input[@class='nice-textbox ng-untouched ng-pristine ng-valid' and @ng-reflect-is-disabled='false']"))
											.getAttribute("value");

									actualFullPaidAmount = twoDigitRounding(Double.valueOf(getFullPaidAmount));

									double expFullPaidAmount = twoDigitRounding(expectedTotalPayable);
									if (actualFullPaidAmount == expFullPaidAmount) {
										System.out.println(" Test Case Passed !! As paid amount is autofilled");

										WebElement paymentMethod = driver
												.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

										paymentMethod.click();

										driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();

										driver.findElement(By.xpath("//button[text()='Submit']")).click();

										System.out
												.println(" Congratulation !!Bundled offer has been added successfully");

										wait("//button[text()='Create new walkin']");

										CreateWalkin = driver
												.findElement(By.xpath("//button[text()='Create new walkin']"));
										CreateWalkin.click();

									} else {
										System.out.println(" Test Case Failed !! As Paid amount is not autofilled");

										takeScreenshot("BOfferpaidAmount_to_beAutofilled.png");
									}

								} else {
									System.out.println(" Test Case Failed !! As Balance amount is not correct");
									takeScreenshot("BOfferBalanceAmount.png");
								}
							} else {
								System.out.println(" Test Case Failed !! As paid amount is not correct");
								takeScreenshot("BOfferpaidAmount.png");
							}

						} else {
							System.out.println(
									" Test Case Failed !! As Total payable amount is not matched with expected");
							takeScreenshot("BOfferTotalPayable.png");
						}

					} else {
						System.out.println(" Test Case Failed !! As GST amount is not correct");
						takeScreenshot("BOfferGStAmount.png");
					}

				} else {
					System.out.println(" Test Case Failed !! As individual discount is not correct");
					takeScreenshot("BOfferInDiscount.png");
				}

			} else {
				System.out.println(" Test Case Failed !! Selected employee is not correct");

				takeScreenshot("EmployeeName.png");
			}
		} else {
			System.out.println(
					" Test Case Failed !! The category name of selected services is not correct as per the requirement");

			takeScreenshot("CategoryName.png");
		}

	}

	public static void availFreeServicesForSingleB_Offer() throws InterruptedException, IOException {

		for (int i = 1; i <= 4; i++) {

			customerPersonalDetails("9026665352", "Adding using bundled offer");

			wait("//div[@class='ng-select-container']");
			selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
			selectServiceDropDownlist.click();
			driver.findElement(By.xpath("//span[text()='Fruit Facial   ( FACIALS ) ']")).click();
			selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
			selectEmpDropDownList.click();
			wait("//*[text()=' Automation 2 ']");
			driver.findElement(By.xpath("//*[text()=' Automation 2 ']")).click();
			addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
			addSummary.click();
			wait("//span[@class='summaryBox__service__price']");

			String getPriceOfSelectedService1 = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price']")).getText();

			priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

			wait("/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[2]/div/div/div[1]/div/span[3]/span");

			String getBundledOfferDiscount = driver.findElement(By.xpath(
					"/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[2]/div/div/div[1]/div/span[3]/span"))
					.getText();

			double actBundledOfferDiscount = Double.valueOf(getBundledOfferDiscount.trim().split(" ")[0]);

			double expBundledOffer = priceOfSelectedService1;

			if (actBundledOfferDiscount == expBundledOffer) {
				System.out
						.println(" Test Case Passed !! As Bundled offer discount is correctly showing in summary page");

				String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']"))
						.getText();
				actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);

				double expGst = (priceOfSelectedService1 - expBundledOffer) * 0.18;

				if (actualGstAmt == expGst) {
					System.out.println(" Test Case Passed !! As GST amount is correct");

					String getActualPayable = driver
							.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']")).getText();

					actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

					double expectedSubTotal = priceOfSelectedService1 - expBundledOffer;

					if (actualAmountPayable == expectedSubTotal) {
						System.out.println(" Test Case Passed !! As total payable amount is correct");

						String getAmountPaid = driver
								.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

						actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

						double expectedAmountPaid = priceOfSelectedService1 - expBundledOffer;

						if (actualAmountPaid == expectedAmountPaid) {
							System.out.println(" Test Case Passed !! As Amount to pe paid is showing correctly");

							String getBalanceValue = driver
									.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

							actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
							double expectedBalance = priceOfSelectedService1 - expBundledOffer;

							if (actualBalanceValue == expectedBalance) {
								System.out.println(" Test Case Passed !! As balance amount is correct");

								wait("//select[@ng-reflect-is-disabled='true']");
								driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='true']")).click();

								driver.findElement(By.xpath("//button[text()='Submit']")).click();

								System.out.println(" Congratulation !! Test Case passed with Free services");

								wait("//button[text()='Create new walkin']");

								CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
								CreateWalkin.click();

							} else {
								System.out.println(" Test Case Failed !! As Balance amount is not correct");
								takeScreenshot("BOfferBalanceAmount.png");
							}

						} else {
							System.out.println(" Test Case Failed !! As Amount to be paid not showing correctly");
							takeScreenshot("BOfferAmount_to_Be_Paid.png");
						}

					} else {
						System.out.println(" Test Case Failed !! As total payable amount is not correct");
						takeScreenshot("BOfferTotalpayable.png");
					}

				} else {
					System.out.println(" Test Case Failed !! As Gst amount is not correct");
					takeScreenshot("BOfferGst_Amt.png");
				}

			} else {
				System.out.println(" Test Case Failed !! As Bundled offer discount is not taking correctly");
				takeScreenshot("BOfferDiscountAmt.png");
			}
		}

		System.out.println(" Availed all free services");
	}

	public static void availPaidSingleService(String ServiceName) throws InterruptedException {
		customerPersonalDetails("9026665352", "Walkin with Paid Service");

		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		driver.findElement(By.xpath(ServiceName)).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		wait("//*[text()=' Automation 2 ']");
		driver.findElement(By.xpath("//*[text()=' Automation 2 ']")).click();
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

		String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
		actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);

		double expectedGSTAmt = twoDigitRounding(priceOfSelectedService1 * 0.18);

		if (actualGstAmt == expectedGSTAmt) {
			System.out.println(" Test Case Passed !! As GSt amount has taken correctly");

			String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
					.getText();

			actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

			double expectedAmountPayable = priceOfSelectedService1 + expectedGSTAmt;

			if (actualAmountPayable == expectedAmountPayable) {
				System.out.println(" Test Case Passed !! As Payable amount is correct");

				wait("//select[@ng-reflect-is-disabled='false']");
				driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']")).click();
				driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();

				System.out.println(" Congratulation !! Test Case passed with Free services");

				wait("//button[text()='Create new walkin']");

				CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
				CreateWalkin.click();

				System.out.println(
						" Congratulation !! Test Case Passed !! As After completing of free services, it has considered as paid service");

			} else {
				System.out.println(" Test Case Failed !! As payable amount is not correct");
			}

		} else {
			System.out.println(
					" Test Case Failed !! As Gst Amount hasn't taken correctly as it has considered still it is a part of bunddled offer");

		}

	}

	public static void addMultipleBundledOfferWithCommonDiscount() throws InterruptedException {

		customerPersonalDetails("9026665352", "Adding multiple using bundled offer");

		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		
		wait("//span[text()='3+3+3 Skin Whitening  (FACIALS),Gold Ritual (FACIALS), Age Defining (FACIALS)  ( Bundled offer ) ']");
		driver.findElement(By.xpath("//span[text()='3+3+3 Skin Whitening  (FACIALS),Gold Ritual (FACIALS), Age Defining (FACIALS)  ( Bundled offer ) ']"))
				.click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		wait("//*[text()=' Automation 2 ']");
		driver.findElement(By.xpath("//*[text()=' Automation 2 ']")).click();
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		wait("//span[@class='summaryBox__service__categoryName']");

		categoryName1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__categoryName']")).getText();

		if (categoryName1.equals("Bundled offer")) {

			System.out.println(" Test Case Passed !! Category name of selected services is correct in summary page");

			driver.findElement(By.xpath("//span[@class='addDiscount__text']")).click();

			driver.findElement(By.xpath("//input[@placeholder='%']")).sendKeys("25");

			driver.findElement(By.xpath("//img[@class='percentage']")).click();

			String getActualCommonDiscount = driver
					.findElement(By.xpath("//span[@class='summaryBox__discount__value']")).getText();

			System.out.println(" com disocunt:" + getActualCommonDiscount);

			double actualCommonDiscount = Double.valueOf(getActualCommonDiscount.trim().split(" ")[0]);

			String getCommonDiscount = driver.findElement(By.xpath("//input[@placeholder='%']")).getAttribute("value");

			int actProvidedCommonDiscount = Integer.parseInt(getCommonDiscount);

			String getPriceOfSelectedService1 = driver
					.findElement(By.xpath("//span[@class='summaryBox__service__price']")).getText();

			priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

			double expCommonDiscount = (priceOfSelectedService1 * actProvidedCommonDiscount) / 100;

			if (actualCommonDiscount == expCommonDiscount) {
				System.out.println(" Test Case Passed !! As expected common discount is correct");

				double expSubTotal = priceOfSelectedService1 - expCommonDiscount;

				double expGstAmount = expSubTotal * 0.18;

				String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']"))
						.getText();
				actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);

				if (actualGstAmt == expGstAmount) {
					System.out.println(" Test Case Passed !! As gst amount is correct ");

					String getActualPayable = driver
							.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']")).getText();

					actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

					double expAmountPayable = expSubTotal + expGstAmount;

					if (actualAmountPayable == expAmountPayable) {
						System.out.println(" Test Case Passed !! As total payable amount is correct ");

						WebElement paymentMethod = driver
								.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

						paymentMethod.click();

						driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']")).click();

						driver.findElement(By.xpath("//button[text()='Submit']")).click();

						System.out.println(" Congratulation !!Bundled offer has been added successfully");

						wait("//button[text()='Create new walkin']");

						CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
						CreateWalkin.click();

					} else {
						System.out.println(" Test Case Failed !! As total payable amount is not correct");
					}

				} else {
					System.out.println(" Test Case Failed !! As gst amount is not correct");
				}

			} else {
				System.out.println(" Test Case Failed !! As common discount is not correct");
			}

		} else {
			System.out.println(" Test Case Failed !! As Category name of bundled offer isn't correct");
		}

	}

	public static void availFreeService(String ServiceName) throws InterruptedException, IOException {
		customerPersonalDetails("9026665352", "Adding using bundled offer");

		wait("//div[@class='ng-select-container']");
		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		driver.findElement(By.xpath(ServiceName)).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		wait("//*[text()=' Automation 2 ']");
		driver.findElement(By.xpath("//*[text()=' Automation 2 ']")).click();
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();
		wait("//span[@class='summaryBox__service__price']");

		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

		wait("/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[2]/div/div/div[1]/div/span[3]/span");

		String getBundledOfferDiscount = driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div[3]/div[2]/div/div/div[1]/div/span[3]/span"))
				.getText();

		double actBundledOfferDiscount = Double.valueOf(getBundledOfferDiscount.trim().split(" ")[0]);

		double expBundledOffer = priceOfSelectedService1;

		if (actBundledOfferDiscount == expBundledOffer) {
			System.out.println(" Test Case Passed !! As Bundled offer discount is correctly showing in summary page");

			String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
			actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);

			double expGst = (priceOfSelectedService1 - expBundledOffer) * 0.18;

			if (actualGstAmt == expGst) {
				System.out.println(" Test Case Passed !! As GST amount is correct");

				String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
						.getText();

				actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

				double expectedSubTotal = priceOfSelectedService1 - expBundledOffer;

				if (actualAmountPayable == expectedSubTotal) {
					System.out.println(" Test Case Passed !! As total payable amount is correct");

					String getAmountPaid = driver
							.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

					actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expectedAmountPaid = priceOfSelectedService1 - expBundledOffer;

					if (actualAmountPaid == expectedAmountPaid) {
						System.out.println(" Test Case Passed !! As Amount to pe paid is showing correctly");

						String getBalanceValue = driver
								.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

						actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
						double expectedBalance = priceOfSelectedService1 - expBundledOffer;

						if (actualBalanceValue == expectedBalance) {
							System.out.println(" Test Case Passed !! As balance amount is correct");

							wait("//select[@ng-reflect-is-disabled='true']");
							driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='true']")).click();

							driver.findElement(By.xpath("//button[text()='Submit']")).click();

							System.out.println(" Congratulation !! Test Case passed with Free services");

							wait("//button[text()='Create new walkin']");

							CreateWalkin = driver.findElement(By.xpath("//button[text()='Create new walkin']"));
							CreateWalkin.click();

						} else {
							System.out.println(" Test Case Failed !! As Balance amount is not correct");
							takeScreenshot("BOfferBalanceAmount.png");
						}

					} else {
						System.out.println(" Test Case Failed !! As Amount to be paid not showing correctly");
						takeScreenshot("BOfferAmount_to_Be_Paid.png");
					}

				} else {
					System.out.println(" Test Case Failed !! As total payable amount is not correct");
					takeScreenshot("BOfferTotalpayable.png");
				}

			} else {
				System.out.println(" Test Case Failed !! As Gst amount is not correct");
				takeScreenshot("BOfferGst_Amt.png");
			}

		} else {
			System.out.println(" Test Case Failed !! As Bundled offer discount is not taking correctly");
			takeScreenshot("BOfferDiscountAmt.png");
		}
	}

	public static void availFreeServicesForMultipleB_Offer() throws InterruptedException, IOException {

		for (int i = 1; i <= 4; i++) {
			availFreeService("//span[text()='Gold Ritual  ( FACIALS ) ']");
			availFreeService("//span[text()=' Age Defining  ( FACIALS ) ']");
			availFreeService("//span[text()='Skin Whitening   ( FACIALS ) ']");

			if (i == 3) {
				System.out.println(" break is done !! as value of i is :" + i);
				break;

			}

		}

		System.out.println(" Availe multiple bundled offer");

	}

	public static void availPaidMultiService() throws InterruptedException {
		availPaidSingleService("//span[text()='Gold Ritual  ( FACIALS ) ']");
		availPaidSingleService("//span[text()=' Age Defining  ( FACIALS ) ']");
		availPaidSingleService("//span[text()='Skin Whitening   ( FACIALS ) ']");

		System.out.println(" Congratulation !! Test Case passed with Multiple Free services ");

	}

};
