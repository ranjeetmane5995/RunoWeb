package in.runo.subscriber.walkin_details;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.walkins.service.Walkin_With_Edit_Service_Price;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.runoweb.MainTestCase;
import in.runo.subscriber.login.SubscriberLogin;

public class ServiceQuantity extends Walkin_With_Individual_Discount {

	public static void verifyServiceQuantity() throws InterruptedException, IOException {
		OperatorLogin.operatorLogin();

		Walkin_With_Edit_Service_Price.editingServiceQuantity();
		OperatorLogout.operatorLogout();
		SubscriberLogin.subscriberLogin();

		Thread.sleep(4000);
		driver.findElement(By.linkText("Walkins")).click();

		wait("//mat-select[@id='mat-select-8']//div[@class='mat-select-arrow']");

		driver.findElement(By.xpath("//mat-select[@id='mat-select-8']//div[@class='mat-select-arrow']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Today')]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search walkins']")).sendKeys("9028123121");

		wait("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[4]");
		
		String actQuantity = driver.findElement(By
				.xpath("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[1]/td[4]"))
				.getText();
		// String proQuantity2 =
		// driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/ng-component/div/div/div[3]/div/table/tbody/tr[2]/td[4]")).getText();

		int getActQuantity = Integer.parseInt(actQuantity);

		if (actProvidedQuantity == getActQuantity) {
			System.out.println(" Test Case Passed !! As Provided quantity is correct");
		} else {
			System.out.println(" Test Case Failed !! As provided quantity is not correct, Correct noOfQuantity is :"
					+ actProvidedQuantity);
			takeScreenshot("noOfQuantity.png");
		}

	}

}
