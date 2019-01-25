package in.runo.operator.logout;

import org.openqa.selenium.By;

import in.runo.operator.login.OperatorLogin;



public class OperatorLogout extends OperatorLogin{
	

	public void operatorLogout() throws InterruptedException {
		
			
		Thread.sleep(6000);
		 
		driver.findElement(By.xpath("//input[@type='image']")).click();
		
		System.out.println(" Logout Successfully !! ");
				
		Thread.sleep(1000);
		
		driver.close();

	}


}
