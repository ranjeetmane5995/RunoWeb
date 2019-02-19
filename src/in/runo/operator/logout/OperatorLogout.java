package in.runo.operator.logout;

import org.openqa.selenium.By;

import in.runo.operator.login.OperatorLogin;



public class OperatorLogout extends OperatorLogin{
	

	public static void operatorLogout() throws InterruptedException {
		
			
		Thread.sleep(3000);
		 
		driver.findElement(By.xpath("//input[@type='image']")).click();
		
		System.out.println(" Operator Logout Successfully !! ");
		
		//driver.close();
		
		
		
		

	}


}
