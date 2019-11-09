package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream configFis;
	public static FileInputStream orFis;
	public static Logger log = Logger.getLogger(TestBase.class.getName());
	public static ExcelReader excel = new ExcelReader(
			"C:\\Selenium\\ProjectWork\\DataDrivenFramework\\src\\test\\resources\\excel\\TestData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	@BeforeSuite
	public void setUp() {

		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");

		if (driver == null) {
			try {

				configFis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(configFis);
				log.info("config file loaded.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				orFis = new FileInputStream(
						"C:\\Selenium\\ProjectWork\\DataDrivenFramework\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(orFis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Jenkins Browser filter config

			if (System.getenv("browser") != null && System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Selenium\\ProjectWork\\DataDrivenFramework\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
			}

			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("ImplicitWait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 10);

		}
	}

	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on: " + locator);
	}

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing in: " + locator + "Enter value as: " + value);

	}

	static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			test.log(LogStatus.INFO, "Using CSS");
		} else if (locator.endsWith("_XPATH")) {

			System.out.println("Read xpath");
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
			System.out.println(dropdown);
		}
		Select select = new Select(dropdown);
		System.out.println("Select dropdown");
		select.selectByVisibleText(value);
		System.out.println("Visible text selected");

		test.log(LogStatus.INFO, "Selecting from dropdown :  " + locator + "Value as: " + value);

	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

	}

}
