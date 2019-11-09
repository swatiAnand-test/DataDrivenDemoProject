package testCases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testBase.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")

	//public void openAccountTest(String customer, String currency, String runmode)throws InterruptedException {
	public void openAccountTest(Hashtable<String,String>data)throws InterruptedException {	
	
		if (!TestUtil.isTestRunable("OpenAccountTest", excel)) {
			
			throw  new SkipException("Skipping the OpenAccountTest as the Run mode is NO");
		}
		if(!data.get("runmode").equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping the test case as the runmode for this test is NO");
			
		}
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		click("opnAccBtn_CSS");
		select("customer_XPATH", data.get("customer"));
		select("currency_XPATH", data.get("currency"));
		click("process_CSS");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();

		
//		driver.findElement(By.xpath(OR.getProperty("addCustBtn"))).click();
//		driver.findElement(By.xpath(OR.getProperty("firstName"))).sendKeys(firstName);
//		driver.findElement(By.xpath(OR.getProperty("lastName"))).sendKeys(lastName);
//		driver.findElement(By.xpath(OR.getProperty("postCode"))).sendKeys(postCode);
//		driver.findElement(By.xpath(OR.getProperty("addBtn"))).click();

//		click("addCustBtn");
//		type("firstName", firstName);
//		type("lastName", lastName);
//		type("postCode", postCode);
//		click("addBtn");
//				

//		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//		Assert.assertTrue(alert.getText().contains(alertText));
//		alert.accept();
//		
//		log.info("Customer Added Successfully");
//		Reporter.log("Customer Added Successfully");
//		Reporter.log("<a href=\"ImagePath.jpg\">Screenshot</a>");
//		

	}
}