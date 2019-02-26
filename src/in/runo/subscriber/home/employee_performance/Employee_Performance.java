package in.runo.subscriber.home.employee_performance;

import java.io.IOException;

import org.openqa.selenium.By;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.subscriber.login.SubscriberLogin;
import in.runo.subscriber.logout.SubscriberLogout;

public class Employee_Performance extends Walkin_With_Individual_Discount {

	public static double lastWeekPreEmpPerAmt;
	public static double lastThreeDayPreEmpPerAmt;
	public static double todayPreEmpPerAmt;
	public static double lastOneMonthPreEmpPerAmt;

	public static void lastOneWeekEmployeePerformance() throws InterruptedException, IOException {

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']");

		String getPreEmployeePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord1 = getPreEmployeePerformanceAmt.replaceAll("₹", " ");

		lastWeekPreEmpPerAmt = Double.valueOf(replaceWord1.trim().split(" ")[0]);

		System.out.println(" Previous employee performance Amount :" + lastWeekPreEmpPerAmt);

		SubscriberLogout.sbLogout();
		OperatorLogin.operatorLogin();
		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9821531212", " Verify Service Performance",
				"25");
		OperatorLogout.operatorLogout();

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		double IndividualDiscount = priceOfSelectedService1 * 0.25;

		double expEmployeePerformanceAmt = (priceOfSelectedService1 - IndividualDiscount) + lastWeekPreEmpPerAmt;

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']");

		String getactEmployeePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord2 = getactEmployeePerformanceAmt.replaceAll("₹", " ");

		double actEmployeePerformance = Double.valueOf(replaceWord2.trim().split(" ")[0]);

		if (actEmployeePerformance == expEmployeePerformanceAmt) {

			System.out.println(
					" Test Case Passed !! As Employee performance is matched with expected employee performance amount");

		} else {

			System.out.println(" Test Case Failed !! As Employee Performance isn't matched with employee performance");
			takeScreenshot("lastoneWeekEmployeePerformance.png");
		}

		SubscriberLogout.sbLogout();

	}

	public static void lastThreeDaysEmployeePerformance() throws InterruptedException, IOException {

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		wait("/html/body/app-dashboard/div/main/div/ng-component/div[1]/div/div/div/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/date-filter/mat-form-field/div/div[1]/div");

		driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div[1]/div/div/div/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/date-filter/mat-form-field/div/div[1]/div"))
				.click();

		wait("//span[text()=' Last 3 Days ']");

		driver.findElement(By.xpath("//span[text()=' Last 3 Days ']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']");

		String getPreEmployeePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord1 = getPreEmployeePerformanceAmt.replaceAll("₹", " ");

		lastThreeDayPreEmpPerAmt = Double.valueOf(replaceWord1.trim().split(" ")[0]);

		System.out.println(" **Previous employee performance Amount :" + lastThreeDayPreEmpPerAmt);

		SubscriberLogout.sbLogout();
		OperatorLogin.operatorLogin();
		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9821531212", " Verify Service Performance",
				"25");
		OperatorLogout.operatorLogout();

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		double IndividualDiscount = priceOfSelectedService1 * 0.25;

		double explastThreeDayPreEmpPerAmt = (priceOfSelectedService1 - IndividualDiscount) + lastThreeDayPreEmpPerAmt;

		wait("/html/body/app-dashboard/div/main/div/ng-component/div[1]/div/div/div/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/date-filter/mat-form-field/div/div[1]/div");

		driver.findElement(By.xpath(
				"/html/body/app-dashboard/div/main/div/ng-component/div[1]/div/div/div/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/date-filter/mat-form-field/div/div[1]/div"))
				.click();

		driver.findElement(By.xpath("//span[text()=' Last 3 Days ']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']");

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']");

		String getlastThreeDayPreEmpPerAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Automation 2']/../..//div[@class='col-sm-7 p-0 m-0']/div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord2 = getlastThreeDayPreEmpPerAmt.replaceAll("₹", " ");

		System.out.println(" ##:" + replaceWord2);

		double actlastThreeDayPreEmpPerAmt = Double.valueOf(replaceWord2.trim().split(" ")[0]);

		if (actlastThreeDayPreEmpPerAmt == explastThreeDayPreEmpPerAmt) {

			System.out.println(
					" Test Case Passed !! As Employee performance is matched with expected employee performance amount");

		} else {

			System.out.println(" Test Case Failed !! As Employee Performance isn't matched with employee performance");
			takeScreenshot("lastoneWeekEmployeePerformance.png");
		}
		
		SubscriberLogout.sbLogout();

	}

}
