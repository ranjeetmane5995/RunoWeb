package in.runo.runoweb;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
import in.runo.subscriber.login.SubscriberLogin;
import in.runo.subscriber.logout.SubscriberLogout;
import in.runo.subscriber.walkin_details.ServiceBillValue;
import in.runo.subscriber.walkin_details.ServiceQuantity;

public class MainTestCase extends SetPath {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		
//		String nodeURL = "http://192.168.0.247:43515/wd/hub";
//		DesiredCapabilities cap = DesiredCapabilities.chrome();
//		cap.setBrowserName("chrome");
//		cap.setPlatform(Platform.WIN10);

			driver = new ChromeDriver();
		// driver = new FirefoxDriver();
		// driver = new InternetExplorerDriver();
		
		//driver = new RemoteWebDriver(new URL(nodeURL), cap);
		driver.manage().window().maximize();
		driver.get("http://13.126.72.115:4200/");

		/*
		 * Validating Operator Test Cases -----------------------------------
		 */

//		OperatorLogin.operatorLogin();
//		
//		Walkin_With_BundledOffer.addMultipleBundledOfferWithCommonDiscount();
//
//		Walkin_With_BundledOffer.availFreeServicesForMultipleB_Offer();
//
//		Walkin_With_BundledOffer.availPaidMultiService();
//
//		Walkin_With_BundledOffer.addSingleBundledOfferWithIndividualDiscount();
//		
//		Walkin_With_BundledOffer.availFreeServicesForSingleB_Offer();
//
//		Walkin_With_BundledOffer.availPaidSingleService("//span[text()='Fruit Facial   ( FACIALS ) ']");
//
//
//		Walkin_With_Individual_Discount.customerPersonalDetails("9294939799", "WebAuto Walkin With Ind Disc");
//		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("35");
//		Walkin_With_Individual_Discount.takeScreenshot("IgnoreFile");
//
//		Walkin_With_Individual_And_Com_Discount createWalkins2 = new Walkin_With_Individual_And_Com_Discount();
//		createWalkins2.createWalkinsWithIndividualAndCommonDiscount();
//
//		Walkin_With_Common_Discount createWalkins3 = new Walkin_With_Common_Discount();
//		createWalkins3.commonDiscountInPercentage("20");
//		createWalkins3.commonDiscountInINR("350");
//
//
//		 Walkin_With_PartialPaid_Amount.verifyPartialPaidAmount("9267848459", "Partial Paid");
//		 Walkin_With_PartialPaid_Amount.verifyPastDueAmount("9267848459");
//		// createWalkins4.runMethod();
//
//		Walkin_With_Edit_Service_Price.takeScreenshot("Ignore File");
//		Walkin_With_Edit_Service_Price.editingServicePrice();
//		Walkin_With_Edit_Service_Price.editingServiceQuantity();
//
//		Walkin_With_Credits.addingWalkinForCredits("//span[text()='Crimping (Starting)  ( HAIR CUT ) ']");
//		Walkin_With_Credits.addingCredits("8830175067", "3540", "2000");
//		Walkin_With_Credits.usingCreditsForBilling();

//		Walkin_With_Membership.addMembership("8830175071", "Buying Membership");
//		Walkin_With_Membership.availMembershipBenefits("8830175071");



		/*
		 * Validating Subscriber Test Cases ------------------------------------
		 */
		
		 //OperatorLogout.operatorLogout();
//		 ServiceQuantity.verifyServiceQuantity();
//		 SubscriberLogout.sbLogout();
//
//		 ServiceBillValue.verifyPartialPaidBillValue();
//		 SubscriberLogout.sbLogout();
//		 
//		 ServiceBillValue.verifyPastDueAmountBillValue();
//		 SubscriberLogout.sbLogout();
		 ServiceBillValue.verifyAddCreditsBillValue();

		 SubscriberLogout.sbLogout();
		ServiceBillValue.verify_Credits_Used_BillValue();

		SubscriberLogout.sbLogout();

	}

}
