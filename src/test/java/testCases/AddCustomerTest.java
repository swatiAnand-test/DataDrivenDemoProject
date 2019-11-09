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

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dp")

//	public void addCustomerTest(String firstName, String lastName, String postCode, String alertText, String runmode) throws InterruptedException {
	public void addCustomerTest(Hashtable<String,String>data) throws InterruptedException {
	
		//if(!runmode.equalsIgnoreCase("Y")) {
		if(!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the runmode for this test is NO");
			
		}
		
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");

//		driver.findElement(By.xpath(OR.getProperty("addCustBtn"))).click();
//		driver.findElement(By.xpath(OR.getProperty("firstName"))).sendKeys(firstName);
//		driver.findElement(By.xpath(OR.getProperty("lastName"))).sendKeys(lastName);
//		driver.findElement(By.xpath(OR.getProperty("postCode"))).sendKeys(postCode);
//		driver.findElement(By.xpath(OR.getProperty("addBtn"))).click();

		
		// with click and type methods in testBase
//		click("addCustBtn_CSS");
//		type("firstName_CSS", firstName);
//		type("lastName_CSS", lastName);
//		type("postCode_CSS", postCode);
//		click("addBtn_CSS");
		
// When Hashtable is added
		
		click("addCustBtn_CSS");
		type("firstName_CSS", data.get("firstName"));
		type("lastName_CSS", data.get("lastName"));
		type("postCode_CSS", data.get("lastName"));
		click("addBtn_CSS");
		System.out.println("HashTable added!");		
		
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));//with hashtable
		alert.accept();
		log.info("Customer Added Successfully");
		Reporter.log("Customer Added Successfully");
		Reporter.log("<a href=\"ImagePath.jpg\">Screenshot</a>");
		

	}

//	@DataProvider
//	public Object[][] getData() {
//
//		System.out.println("excel reading");
//		System.out.println(excel);
//		String sheetName = "AddCustomerTest";
//		int rows = excel.getRowCount(sheetName);
//
//		// int rows = excel.getRowCount("AddCustomerTest");
//		System.out.println(rows);
//		int column = excel.getColumnCount("AddCustomerTest");
//
//		System.out.println(column);
//		Object[][] data = new Object[rows - 1][column];
//		System.out.println("Object Created");
//
//		for (int rowNum = 2; rowNum <= rows; rowNum++) {
//			for (int colNum = 0; colNum < column; colNum++) {
//
//				System.out.println("entered in loop");
//
//				// data[rowNum - 2][colNum] = excel.getCellData("AddCustomerTest", colNum,
//				// rowNum);
//
//				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//				// System.out.println(data[rowNum - 2][colNum]);
//
//			}
//			System.out.println("colm loop ended");
//
//		}
//		return data;
//
//	}
}
