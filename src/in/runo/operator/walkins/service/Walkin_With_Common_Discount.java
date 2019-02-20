package in.runo.operator.walkins.service;

import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Walkin_With_Common_Discount extends Walkin_With_Individual_Discount {

	public static WebElement selectingPaymentMode;
	public static WebElement submitWalkin;

	public void commonDiscountInPercentage(String discount) throws InterruptedException, IOException {

		wait("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']");

		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys("9191919192");

		// Handling noSuchElementException for New Customer
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
					.sendKeys("WebAuto ComDiscInPercentage");
			driver.findElement(By.xpath("//input[@ng-reflect-name='email']")).sendKeys("automation@gmail.com");

			WebElement selectDob = driver.findElement(By.xpath("//input[@ng-reflect-name='dob']"));

			selectDob.click();

			driver.findElement(By.xpath("//button[@class='previous']")).click();

			driver.findElement(By.xpath("//span[text()='12']")).click();

			driver.findElement(By.xpath("//div[text	()='Others']")).click();

			driver.findElement(By.xpath("//input[@ng-reflect-name='locality']"))
					.sendKeys("Sr No: 22/2, 2nd Floor Near Petrol Pump Bidar");
			driver.findElement(By.xpath("//input[@ng-reflect-name='city']")).sendKeys("Bidar");
			driver.findElement(By.xpath("//input[@ng-reflect-name='pincode']")).sendKeys("410048");
			driver.findElement(By.xpath("//input[@ng-reflect-name='notes']")).sendKeys(" He is a regular customer ");
		}

		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		
		wait("//span[text()='Crimping (Starting)  ( HAIR CUT ) ']");
		driver.findElement(By.xpath("//span[text()='Crimping (Starting)  ( HAIR CUT ) ']")).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		driver.findElement(By.xpath("//option[text()=' Automation 3 ']")).click();
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		driver.findElement(By.xpath("//span[@class='addDiscount__text']"));
		Walkin_With_Individual_And_Com_Discount.addCommonDiscount = driver
				.findElement(By.xpath("//span[@class='addDiscount__text']"));

		Walkin_With_Individual_And_Com_Discount.addCommonDiscount.click();

		Walkin_With_Individual_And_Com_Discount.enterCommonDiscount = driver
				.findElement(By.xpath("//input[@placeholder='%']"));

		Walkin_With_Individual_And_Com_Discount.enterCommonDiscount.sendKeys(discount);

		Walkin_With_Individual_And_Com_Discount.applyCommonDiscount = driver
				.findElement(By.xpath("//img[@class='percentage']"));

		Walkin_With_Individual_And_Com_Discount.applyCommonDiscount.click();

		String getActualCommonDiscount = driver.findElement(By.xpath("//span[@class='summaryBox__discount__value']"))
				.getText();

		Walkin_With_Individual_And_Com_Discount.actualCommonDiscount = Double
				.valueOf(getActualCommonDiscount.trim().split(" ")[0]);

		System.out
				.println(" after converting price is :" + Walkin_With_Individual_And_Com_Discount.actualCommonDiscount);

		String getProvidedCommonDiscount = driver.findElement(By.xpath("//input[@placeholder='%']"))
				.getAttribute("value");

		System.out.println(" getting provided common discount" + getProvidedCommonDiscount);

		double providedCommonDiscount = Double.valueOf(getProvidedCommonDiscount);

		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

		double expCommonDiscount = twoDigitRounding(priceOfSelectedService1 * providedCommonDiscount) / 100;

		if (Walkin_With_Individual_And_Com_Discount.actualCommonDiscount == expCommonDiscount)

		{
			System.out.println(" Test Case Passed !! Common discount is correct as actual discount is : "
					+ Walkin_With_Individual_And_Com_Discount.actualCommonDiscount + " = " + expCommonDiscount);

			String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
			actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);
			System.out.println("**** " + getGstAmount.length() + " value is :" + actualGstAmt);
			double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expCommonDiscount) * 0.18);

			if (actualGstAmt == expectedGSTAmt) {
				System.out.println(" Test Case Passed !! As GST amount is matched with expected Gst amount, that is : "
						+ actualGstAmt + " = " + expectedGSTAmt);

				String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
						.getText();

				actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

				double expectedSubTotal = priceOfSelectedService1 - expCommonDiscount;
				double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;

				if (actualAmountPayable == expectedTotalPayable) {
					System.out.println(
							" Test Case Passed !! As total payable amount is matched with expected total payable, that is :"
									+ actualAmountPayable + " = " + expectedTotalPayable);

					String getAmountPaid = driver
							.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

					actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expectedAmountPaid = expectedTotalPayable;

					if (actualAmountPaid == expectedAmountPaid) {

						System.out.println(
								"Test Case Passed !! As Amount paid is matched with expected Amount paid, that is :"
										+ actualAmountPaid + " = " + expectedAmountPaid);

						String getBalanceValue = driver
								.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

						actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
						double expectedBalance = expectedTotalPayable - expectedAmountPaid;

						if (actualBalanceValue == expectedBalance) {

							System.out.println(
									" Test Case Passed !! As Balance Amount is matched with expected Balance Amount, that is : "
											+ actualBalanceValue + " = " + expectedBalance);

							paymentMethod = driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

							paymentMethod.click();

							selectingPaymentMode = driver
									.findElement(By.xpath("//option[@ng-reflect-value='Credit/Debit Card']"));
							selectingPaymentMode.click();

							submitWalkin = driver.findElement(By.xpath("//button[text()='Submit']"));
							submitWalkin.click();

							System.out.println("Congratulation !! Whole Test Case Passed with Common Discount");

							wait("//button[text()='Create new walkin']");

							driver.findElement(By.xpath("//button[text()='Create new walkin']")).click();

						} else {
							System.out.println(
									" Test Case Failed !! As Balance amount is not matched with expected Balance Amount, that is :"
											+ actualBalanceValue + " = " + expectedBalance);
							takeScreenshot("BalanceAmount_Issue.png");

						}

					} else {
						System.out.println(
								" Test Case Failed !! As Amount paid is not matched with expected Amount paid, that is : "
										+ actualAmountPaid + " = " + expectedAmountPaid);
						takeScreenshot("AmountPaid_Issue.png");

					}

				} else {
					System.out.println(
							" Test Case Failed !! As total payable amount is not matched with expected total payable, That is "
									+ actualAmountPayable + " = " + expectedTotalPayable);
					takeScreenshot("TotalPayable_Issue.png");

				}

			} else {

				System.out.println(" Test Case Failed !! As GST amount is not matched with expected GST , that is : "
						+ actualGstAmt + " = " + expectedGSTAmt);
				takeScreenshot("GstAmount_Issue.png");

			}

		} else {

			System.out.println(" Test Case Failed !! actual common discount is : "
					+ Walkin_With_Individual_And_Com_Discount.actualCommonDiscount
					+ " and expected common discount is : " + expCommonDiscount);
			takeScreenshot("ComDiscInPercentage_Issue.png");

		}

	}

	public void commonDiscountInINR(String discount) throws IOException, InterruptedException {

		wait("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']");

		driver.findElement(By.xpath("//input[@class='nice-textbox mobileNumber ng-untouched ng-pristine ng-invalid']"))
				.sendKeys("9191919193");

		// Handling noSuchElementException for New Customer
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
					.sendKeys("WebAuto ComDiscIn INR ");
			driver.findElement(By.xpath("//input[@ng-reflect-name='email']")).sendKeys("automation@gmail.com");

			WebElement selectDob = driver.findElement(By.xpath("//input[@ng-reflect-name='dob']"));

			selectDob.click();

			driver.findElement(By.xpath("//button[@class='previous']")).click();

			driver.findElement(By.xpath("//span[text()='22']")).click();

			driver.findElement(By.xpath("//div[text	()='Others']")).click();

			driver.findElement(By.xpath("//input[@ng-reflect-name='locality']"))
					.sendKeys("Sr No: 22/2, 2nd Floor Near Petrol Pump Bidar");
			driver.findElement(By.xpath("//input[@ng-reflect-name='city']")).sendKeys("Bidar");
			driver.findElement(By.xpath("//input[@ng-reflect-name='pincode']")).sendKeys("410048");
			driver.findElement(By.xpath("//input[@ng-reflect-name='notes']")).sendKeys(" He is a regular customer ");
		}

		selectServiceDropDownlist = driver.findElement(By.xpath("//div[@class='ng-select-container']"));
		selectServiceDropDownlist.click();
		wait("//span[text()='Ironing (Long Hair)  ( HAIR CUT ) ']");
		driver.findElement(By.xpath("//span[text()='Ironing (Long Hair)  ( HAIR CUT ) ']")).click();
		selectEmpDropDownList = driver.findElement(By.xpath("//select[@ng-reflect-name='employee']"));
		selectEmpDropDownList.click();
		driver.findElement(By.xpath("//option[text()=' Automation 2 ']")).click();
		addSummary = driver.findElement(By.xpath("//button[@class='addSummary']"));
		addSummary.click();

		driver.findElement(By.xpath("//span[@class='addDiscount__text']"));
		Walkin_With_Individual_And_Com_Discount.addCommonDiscount = driver
				.findElement(By.xpath("//span[@class='addDiscount__text']"));

		Walkin_With_Individual_And_Com_Discount.addCommonDiscount.click();

		Walkin_With_Individual_And_Com_Discount.applyCommonDiscount = driver
				.findElement(By.xpath("//img[@class='percentage']"));

		Walkin_With_Individual_And_Com_Discount.applyCommonDiscount.click();

		Walkin_With_Individual_And_Com_Discount.enterCommonDiscount = driver
				.findElement(By.xpath("//input[@placeholder='INR']"));

		Walkin_With_Individual_And_Com_Discount.enterCommonDiscount.sendKeys(discount);

		Walkin_With_Individual_And_Com_Discount.applyCommonDiscount = driver
				.findElement(By.xpath("//img[@class='percentage']"));

		Walkin_With_Individual_And_Com_Discount.applyCommonDiscount.click();

		String getActualCommonDiscount = driver.findElement(By.xpath("//span[@class='summaryBox__discount__value']"))
				.getText();

		Walkin_With_Individual_And_Com_Discount.actualCommonDiscount = Double
				.valueOf(getActualCommonDiscount.trim().split(" ")[0]);

		String getProvidedCommonDiscount = driver.findElement(By.xpath("//input[@placeholder='INR']"))
				.getAttribute("value");

		double providedCommonDiscount = Double.valueOf(getProvidedCommonDiscount);

		String getPriceOfSelectedService1 = driver.findElement(By.xpath("//span[@class='summaryBox__service__price']"))
				.getText();

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService1.trim().split(" ")[0]);

		double expCommonDiscount = twoDigitRounding(providedCommonDiscount);

		if (Walkin_With_Individual_And_Com_Discount.actualCommonDiscount == expCommonDiscount)

		{
			System.out.println(" Test Case Passed !! Common discount In INR is correct as actual discount is : "
					+ Walkin_With_Individual_And_Com_Discount.actualCommonDiscount + " = " + expCommonDiscount);

			String getGstAmount = driver.findElement(By.xpath("//span[@class='summaryBox__GST__money ']")).getText();
			actualGstAmt = Double.valueOf(getGstAmount.trim().split(" ")[0]);
			System.out.println("**** " + getGstAmount.length() + " value is :" + actualGstAmt);
			double expectedGSTAmt = twoDigitRounding((priceOfSelectedService1 - expCommonDiscount) * 0.18);

			if (actualGstAmt == expectedGSTAmt) {
				System.out.println(" Test Case Passed !! As GST amount is matched with expected Gst amount, that is : "
						+ actualGstAmt + " = " + expectedGSTAmt);

				String getActualPayable = driver.findElement(By.xpath("//span[@class='summaryBox__grandTotal__value']"))
						.getText();

				actualAmountPayable = Double.valueOf(getActualPayable.trim().split(" ")[0]);

				double expectedSubTotal = priceOfSelectedService1 - expCommonDiscount;
				double expectedTotalPayable = expectedSubTotal + expectedGSTAmt;

				if (actualAmountPayable == expectedTotalPayable) {
					System.out.println(
							" Test Case Passed !! As total payable amount is matched with expected total payable, that is :"
									+ actualAmountPayable + " = " + expectedTotalPayable);

					String getAmountPaid = driver
							.findElement(By.xpath("//span[@class='summaryBox__amountPaid__value']")).getText();

					actualAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expectedAmountPaid = expectedTotalPayable;

					if (actualAmountPaid == expectedAmountPaid) {

						System.out.println(
								"Test Case Passed !! As Amount paid is matched with expected Amount paid, that is :"
										+ actualAmountPaid + " = " + expectedAmountPaid);

						String getBalanceValue = driver
								.findElement(By.xpath("//span[@class='summaryBox__balance__value']")).getText();

						actualBalanceValue = Double.valueOf(getBalanceValue.trim().split(" ")[0]);
						double expectedBalance = expectedTotalPayable - expectedAmountPaid;

						if (actualBalanceValue == expectedBalance) {

							System.out.println(
									" Test Case Passed !! As Balance Amount is matched with expected Balance Amount, that is : "
											+ actualBalanceValue + " = " + expectedBalance);

							paymentMethod = driver.findElement(By.xpath("//select[@ng-reflect-is-disabled='false']"));

							paymentMethod.click();

							selectingPaymentMode = driver.findElement(By.xpath("//option[@ng-reflect-value='Cash']"));
							selectingPaymentMode.click();

							submitWalkin = driver.findElement(By.xpath("//button[text()='Submit']"));
							submitWalkin.click();

							System.out.println("Test Case Passed !!Test Case Passed with Common Discount");

							wait("//button[text()='Create new walkin']");

							driver.findElement(By.xpath("//button[text()='Create new walkin']")).click();

						} else {
							System.out.println(
									" Test Case Failed !! As Balance amount is not matched with expected Balance Amount, that is :"
											+ actualBalanceValue + " = " + expectedBalance);
							takeScreenshot("BalanceAmount_Issue.png");

						}

					} else {
						System.out.println(
								" Test Case Failed !! As Amount paid is not matched with expected Amount paid, that is : "
										+ actualAmountPaid + " = " + expectedAmountPaid);
						takeScreenshot("AmountPaid_Issue.png");

					}

				} else {
					System.out.println(
							" Test Case Failed !! As total payable amount is not matched with expected total payable, That is "
									+ actualAmountPayable + " = " + expectedTotalPayable);
					takeScreenshot("TotalPayable_Issue.png");

				}

			} else {

				System.out.println(" Test Case Failed !! As GST amount is not matched with expected GST , that is : "
						+ actualGstAmt + " = " + expectedGSTAmt);
				takeScreenshot("GstAmount_Issue.png");
			}

		} else {

			System.out.println(" Test Case Failed !! actual common discount in INR is : "
					+ Walkin_With_Individual_And_Com_Discount.actualCommonDiscount
					+ " and expected common discount is : " + expCommonDiscount);
			takeScreenshot("ComDiscInINR_Issue.png");

		}

	}

}
