package in.runo.subscriber.home.PaymentsSummary;

import java.io.IOException;

import org.openqa.selenium.By;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.subscriber.login.SubscriberLogin;

public class PaymentsSummary extends Walkin_With_Individual_Discount {

	public static void totalBillAmount() throws InterruptedException, IOException {
		
//		OperatorLogin.operatorLogin();
//		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9024154215", "Validate TotalBillAmount",
//				"25");
//		OperatorLogout.operatorLogout();
		SubscriberLogin.subscriberLogin();
		
		
		Thread.sleep(3000);

		javascript.executeScript("window.scrollBy(0,650)", "");
		
		
		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 3']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");
	    String get=driver.findElement(By.xpath("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 3']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']")).getText();
		
		System.out.println(get);
	}

}
