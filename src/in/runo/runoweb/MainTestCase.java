package in.runo.runoweb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import in.runo.operator.login.OperatorLogin;
import in.runo.operator.logout.OperatorLogout;
import in.runo.operator.walkins.service.Walkin_With_Individual_Discount;
import in.runo.operator.walkins.service.Walkins_With_Individual_And_Common_Discount;
import in.runo.setpath.SetPath;

public class MainTestCase extends SetPath {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		//driver = new ChromeDriver();
		driver = new FirefoxDriver();

		OperatorLogin login = new OperatorLogin();
		login.operatorLogin();
		
		Walkin_With_Individual_Discount createWalkins1 = new Walkin_With_Individual_Discount();
		createWalkins1.createWalkins_With_Individual_Discount();
		
		Walkins_With_Individual_And_Common_Discount createWalkins2 = new Walkins_With_Individual_And_Common_Discount();
		createWalkins2.createWalkins_With_Individual_And_Common_Discount();
		
				
		OperatorLogout logout = new OperatorLogout();
		logout.operatorLogout();

	}

}
