package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class BankManagerLoginTest extends TestBase {

	
	@Test
	public void bankManagerLoginTest() throws InterruptedException{
		
		click("bmloginBtn_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))), "Login not succesful");
		
		log.info("Bank Manager login");
		Reporter.log("Bank Manager Login Done");
		
		
		
		
		
	}
}
