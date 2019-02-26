package in.runo.subscriber.home.service_performance;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.subscriber.login.SubscriberLogin;
import in.runo.subscriber.logout.SubscriberLogout;

public class Service_Performance extends Walkin_With_Individual_Discount {

	public static double preServicePerformanceAmt;

	public static void lastOneWeekServicePerformance() throws InterruptedException, IOException {

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getPreServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord1 = getPreServicePerformanceAmt.replaceAll("₹", " ");

		preServicePerformanceAmt = Double.valueOf(replaceWord1.trim().split(" ")[0]);

		System.out.println(" Previous Service performance price :" + preServicePerformanceAmt);

		SubscriberLogout.sbLogout();
		OperatorLogin.operatorLogin();
		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9821531212", " Verify Service Performance",
				"25");
		OperatorLogout.operatorLogout();
		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		double IndividualDiscount = priceOfSelectedService1 * 0.25;

		double expServicePerformanceAmt = (priceOfSelectedService1 - IndividualDiscount) + preServicePerformanceAmt;

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getActServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord2 = getActServicePerformanceAmt.replaceAll("₹", " ");

		int actServicePerfomanceAmt = Integer.parseInt(replaceWord2.trim().split(" ")[0]);

		if (actServicePerfomanceAmt == expServicePerformanceAmt)

		{
			System.out.println(" Test Case Passed !! As Service Performance Amount is matched with expected result is :"
					+ expServicePerformanceAmt);
		} else {
			System.out.println(" Test Case Failed !! As Service Performance Amount is not matched with ");
			takeScreenshot("LastOneWeekServicePerformanceAmount.png");
		}

		SubscriberLogout.sbLogout();

	}

	public static void lastThreeDaysServicePerformace() throws IOException, InterruptedException {

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,650)", "");

		wait("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']");

		wait("//mat-option[@ng-reflect-value='3Days']");

		driver.findElement(By.xpath("//mat-option[@ng-reflect-value='3Days']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getPreServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord1 = getPreServicePerformanceAmt.replaceAll("₹", " ");

		preServicePerformanceAmt = Double.valueOf(replaceWord1.trim().split(" ")[0]);

		System.out.println(" Previous Service performance price :" + preServicePerformanceAmt);

		SubscriberLogout.sbLogout();
		OperatorLogin.operatorLogin();
		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9821531212", " Verify Service Performance",
				"25");
		OperatorLogout.operatorLogout();

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		double IndividualDiscount = priceOfSelectedService1 * 0.25;

		double expServicePerformanceAmt = (priceOfSelectedService1 - IndividualDiscount) + preServicePerformanceAmt;

		wait("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']");

		driver.findElement(By.xpath("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']")).click();

		wait("//mat-option[@ng-reflect-value='3Days']");

		driver.findElement(By.xpath("//mat-option[@ng-reflect-value='3Days']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getActServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord2 = getActServicePerformanceAmt.replaceAll("₹", " ");

		int actServicePerfomanceAmt = Integer.parseInt(replaceWord2.trim().split(" ")[0]);

		if (actServicePerfomanceAmt == expServicePerformanceAmt)

		{
			System.out.println(" Test Case Passed !! As Service Performance Amount is matched with expected result is :"
					+ expServicePerformanceAmt);
		} else {
			System.out.println(" Test Case Failed !! As Service Performance Amount is not matched with ");
			takeScreenshot("ThreeDayServicePerformanceAmount.png");
		}

		SubscriberLogout.sbLogout();

	}

	public static void todayServicePerformance() throws IOException, InterruptedException {

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,650)", "");

		wait("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']");

		driver.findElement(By.xpath("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']")).click();

		wait("//mat-option[@ng-reflect-value='0Days']");

		driver.findElement(By.xpath("//mat-option[@ng-reflect-value='0Days']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getPreServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord1 = getPreServicePerformanceAmt.replaceAll("₹", " ");

		preServicePerformanceAmt = Double.valueOf(replaceWord1.trim().split(" ")[0]);

		System.out.println(" Previous Service performance price :" + preServicePerformanceAmt);

		SubscriberLogout.sbLogout();
		OperatorLogin.operatorLogin();
		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9821531212", " Verify Service Performance",
				"25");
		OperatorLogout.operatorLogout();

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		double IndividualDiscount = priceOfSelectedService1 * 0.25;

		double expServicePerformanceAmt = (priceOfSelectedService1 - IndividualDiscount) + preServicePerformanceAmt;

		wait("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']");

		driver.findElement(By.xpath("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']")).click();

		wait("//mat-option[@ng-reflect-value='0Days']");

		driver.findElement(By.xpath("//mat-option[@ng-reflect-value='0Days']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getActServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord2 = getActServicePerformanceAmt.replaceAll("₹", " ");

		int actServicePerfomanceAmt = Integer.parseInt(replaceWord2.trim().split(" ")[0]);

		if (actServicePerfomanceAmt == expServicePerformanceAmt)

		{
			System.out.println(" Test Case Passed !! As Service Performance Amount is matched with expected result is :"
					+ expServicePerformanceAmt);
		} else {
			System.out.println(" Test Case Failed !! As Service Performance Amount is not matched with ");
			takeScreenshot("TodayservicePerformanceAmount.png");
		}

		SubscriberLogout.sbLogout();

	}

	public static void lastOneMonthServicePerformance() throws IOException, InterruptedException {

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,650)", "");

		wait("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']");

		driver.findElement(By.xpath("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']")).click();

		wait("//mat-option[@ng-reflect-value='30Days']");

		driver.findElement(By.xpath("//mat-option[@ng-reflect-value='30Days']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getPreServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord1 = getPreServicePerformanceAmt.replaceAll("₹", " ");

		preServicePerformanceAmt = Double.valueOf(replaceWord1.trim().split(" ")[0]);

		System.out.println(" Previous Service performance price :" + preServicePerformanceAmt);

		SubscriberLogout.sbLogout();
		OperatorLogin.operatorLogin();
		Walkin_With_Individual_Discount.createWalkinsWithIndividualDiscount("9821531212", " Verify Service Performance",
				"25");
		OperatorLogout.operatorLogout();

		SubscriberLogin.subscriberLogin();

		Thread.sleep(3000);
		javascript.executeScript("window.scrollBy(0,600)", "");

		double IndividualDiscount = priceOfSelectedService1 * 0.25;

		double expServicePerformanceAmt = (priceOfSelectedService1 - IndividualDiscount) + preServicePerformanceAmt;

		wait("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']");

		driver.findElement(By.xpath("//mat-select[@class='mat-select ng-tns-c11-10 ng-star-inserted']")).click();

		wait("//mat-option[@ng-reflect-value='30Days']");

		driver.findElement(By.xpath("//mat-option[@ng-reflect-value='30Days']")).click();

		Thread.sleep(4000);

		wait("//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']");

		String getActServicePerformanceAmt = driver.findElement(By.xpath(
				"//div[@class='custom-bar-chart__item ng-star-inserted']//span[text()='Skin Pearl Lightening ( FACIALS )']/../..//div[@class='col-sm-7 p-0 m-0'] /div/span[@class='custom-bar-chart__value medium']"))
				.getText();

		String replaceWord2 = getActServicePerformanceAmt.replaceAll("₹", " ");

		int actServicePerfomanceAmt = Integer.parseInt(replaceWord2.trim().split(" ")[0]);

		if (actServicePerfomanceAmt == expServicePerformanceAmt)

		{
			System.out.println(" Test Case Passed !! As Service Performance Amount is matched with expected result is :"
					+ expServicePerformanceAmt);
		} else {
			System.out.println(" Test Case Failed !! As Service Performance Amount is not matched with ");
			takeScreenshot("lastOneMonthPerformanceAmount.png");
		}

		SubscriberLogout.sbLogout();

	}

}
