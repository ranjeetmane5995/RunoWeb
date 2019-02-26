package in.runo.runoweb;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.setpath.SetPath;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.operator.walkins.service.Walkin_With_Membership;
import in.runo.operator.walkins.service.Walkin_With_PartialPaid_Amount;
import in.runo.operator.walkins.service.Walkin_With_BundledOffer;
import in.runo.operator.walkins.service.Walkin_With_Common_Discount;
import in.runo.operator.walkins.service.Walkin_With_Credits;
import in.runo.operator.walkins.service.Walkin_With_Edit_Service_Price;
import in.runo.operator.walkins.service.Walkin_With_Individual_And_Com_Discount;
import in.runo.subscriber.home.PaymentsSummary.PaymentsSummary;
import in.runo.subscriber.home.employee_performance.Employee_Performance;
import in.runo.subscriber.home.service_performance.Service_Performance;
import in.runo.subscriber.login.SubscriberLogin;
import in.runo.subscriber.logout.SubscriberLogout;
import in.runo.subscriber.walkin_details.BalancePayable;
import in.runo.subscriber.walkin_details.ServiceBillValue;
import in.runo.subscriber.walkin_details.ServiceQuantity;

public class MainTestCase extends SetPath {

	public static WebDriver driver;
	public static Robot robot;
	public static JavascriptExecutor javascript;

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {

//		String nodeURL = "http://192.168.0.190:44365/wd/hub";
//		DesiredCapabilities cap = DesiredCapabilities.chrome();
//		cap.setBrowserName("chrome");
//		cap.setPlatform(Platform.WIN10);

		 driver = new ChromeDriver();
		// driver = new FirefoxDriver();
		// driver = new InternetExplorerDriver();

//		driver = new RemoteWebDriver(new URL(nodeURL), cap);
		driver.manage().window().maximize();
		driver.get("http://13.126.72.115:4200/");
		robot = new Robot();
		javascript = (JavascriptExecutor) driver;

		/*
		 * Validating Operator Test Cases -----------------------------------
		 */

//		OperatorLogin.operatorLogin();

//		Walkin_With_BundledOffer.addMultipleBundledOfferWithCommonDiscount("9002531231", "Add MultiB_Offer");
//
// 		Walkin_With_BundledOffer.availFreeServicesForMultipleB_Offer("9002531231","Free Service");
//
//		Walkin_With_BundledOffer.availPaidMultiService("9002531231","Avail_Paid_Service");

//		Walkin_With_BundledOffer.addSingleBundledOfferWithIndividualDiscount("9028481214", "Add SingleB_Offer");
//
//		Walkin_With_BundledOffer.availFreeServicesForSingleB_Offer("9028481214", "Avail Free Service");
//
//		Walkin_With_BundledOffer.availPaidSingleService("9028481214", "Avail Paid Service","//span[text()='Fruit Facial   ( FACIALS ) ']");
//
//		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9024811355", "Ind Disc", "25");
//		Walkin_With_Individual_Discount.takeScreenshot("IgnoreFile");
//
//		Walkin_With_Individual_And_Com_Discount.createWalkinsWithIndividualAndCommonDiscount("9812421565",
//				"Ind and Overall Disc", "25", "30");
//
//		Walkin_With_Common_Discount.commonDiscountInPercentage("9812542152", " Overall_Per_Disc", "35");
//		Walkin_With_Common_Discount.commonDiscountInINR("9824152410", "Overall_INR_Disc", "250");
//
//		Walkin_With_PartialPaid_Amount.verifyPartialPaidAmount("9267848459", "Partial Paid");
//		Walkin_With_PartialPaid_Amount.verifyPastDueAmount("9267848459");
//
//		Walkin_With_Edit_Service_Price.takeScreenshot("Ignore File");
//		Walkin_With_Edit_Service_Price.editingServicePrice();
//		Walkin_With_Edit_Service_Price.editingServiceQuantity("5");
//
//		Walkin_With_Credits.addingWalkinForCredits("//span[text()='Crimping (Starting)  ( HAIR CUT ) ']");
//		Walkin_With_Credits.addingCredits("8830175067", "3540", "2000");
//		Walkin_With_Credits.usingCreditsForBilling("8830175067");

//		Walkin_With_Membership.addMembership("8830175071", "Buying Membership");
//		Walkin_With_Membership.availMembershipBenefits("8830175071");
		
//		OperatorLogout.operatorLogout();

		/*
		 * Validating Subscriber Test Cases ------------------------------------
		 */

		
//		ServiceQuantity.verify_NoOfServiceQuantity();
//
//		ServiceBillValue.verifyPartialPaidBillValue();
//
//		ServiceBillValue.verifyPastDueAmountBillValue();
//
//		ServiceBillValue.verifyAddCreditsBillValue();
//
//		ServiceBillValue.verify_Credits_Used_BillValue();
//		ServiceBillValue.verify_UsingCreditsAndPaid_BillValue();

//		BalancePayable.verifyFullPaidAmount_BalancePayable("8115447525", "Verify B_Payable in Sb");
//
//		BalancePayable.verifyInitialPaidAmount_BalancePayable("8115447525", "Verify B_Payable in Sb");
//
//		BalancePayable.verifyClearingPastDueAmount_BalancePayable("8115447525");

		Service_Performance.lastOneWeekServicePerformance();

		Service_Performance.lastThreeDaysServicePerformace();

		Service_Performance.todayServicePerformance();

		Service_Performance.lastOneMonthServicePerformance();

		Employee_Performance.lastOneWeekEmployeePerformance();

		Employee_Performance.lastThreeDaysEmployeePerformance();

//		PaymentsSummary.totalBillAmount();

	}

}
