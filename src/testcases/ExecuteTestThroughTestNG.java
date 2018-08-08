package testcases;


import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import excelInputAndOutput.ExcelReader;
import operation.ReadObject;
import operation.UIOperation;
import utility.Constant;

public class ExecuteTestThroughTestNG {
	public static WebDriver driver;
	Properties allObjects = null;
	ExcelReader reader = new ExcelReader();
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setUp() throws IOException {
		ReadObject object = new ReadObject();
		allObjects = object.getObjectRepository();
		if(allObjects.getProperty("browserType").equals("chrome")){
			System.setProperty("webdriver.chrome.driver", Constant.chromeDriverPath);
			driver = new ChromeDriver();
		}else if(allObjects.getProperty("browserType").equals("firefox")){
			System.setProperty("webdriver.gecko.driver", Constant.firefoxDriverPath);
			driver = new FirefoxDriver();
		}else{
			System.setProperty("webdriver.ie.driver", Constant.ieDriverPath);
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capability.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://newtours.demoaut.com");
			driver = new InternetExplorerDriver(capability);
		}
	}
	
	@Test(priority=0)
	public void launchFlightReservationApplication() throws IOException{
		String URL = reader.getCellData(Constant.filePath, Constant.fileName, "InvokeApplication", 1, 0);
		String expTitle = reader.getCellData(Constant.filePath, Constant.fileName, "InvokeApplication", 1, 1);
		// Launch Flight Reservation Application
		UIOperation.invokeApplication(URL);
		// Assert Page Title
		UIOperation.assertTitle(expTitle);
		}
	
	@Test(priority=1)
	public void loginToFlightReservation() throws IOException{
		String userName = reader.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 0);
		String password = reader.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 1);
		String expTitle = reader.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 2);
		
		// Enter UserName
		UIOperation.sendKeys(By.name(allObjects.getProperty("UserName")), "UserName", userName);
		// Enter Password
		UIOperation.sendKeys(By.name(allObjects.getProperty("Password")), "Password", password);
		// Click on 'Sign-In' button
		UIOperation.click(By.name(allObjects.getProperty("Sign-On")), "Sign-On");
		// Assert Page Title
		UIOperation.assertTitle(expTitle);
	}
	
	@Test(priority=2)
	public void bookATicket() throws IOException{
		String flyFrom = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 0);
		String flyTo= reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 1);
		String airlinePreference = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 2);
		String firstName = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 3);
		String lastName = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 4);
		String ccNumber = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 5);
		String expText = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 6);
		
		// Select Trip Type
		UIOperation.click(By.xpath(allObjects.getProperty("TripType")), "Trip Type");
		//Select Fly From 
		UIOperation.select(By.name(allObjects.getProperty("DepartureFrom")), "Departure From", flyFrom);
		//Select Fly To
		UIOperation.select(By.name(allObjects.getProperty("ArrivalTo")), "Arrival To", flyTo);
		//Click on  Class Preference
		UIOperation.click(By.xpath(allObjects.getProperty("ClassPreference")), "Class Preference");
		//Select Airline Preference
		UIOperation.select(By.name(allObjects.getProperty("AirlinePreference")), "Airline Preference", airlinePreference);
		//Click on Find Flights
		UIOperation.click(By.name(allObjects.getProperty("FindFlights")), "Find Flights");
		//Click on Reserve Flights
		UIOperation.click(By.name(allObjects.getProperty("ReserveFlights")), "Reserve Flights");
		//Enter the First Name
		UIOperation.sendKeys(By.name(allObjects.getProperty("FirstName")), "First Name", firstName);
		//Enter the Last Name
		UIOperation.sendKeys(By.name(allObjects.getProperty("LastName")), "Last Name", lastName);
		//Enter the Credit Number
		UIOperation.sendKeys(By.name(allObjects.getProperty("CreditCardNumber")), "Credit Card Number", ccNumber);
		//Click on the Buy Flights
		UIOperation.click(By.name(allObjects.getProperty("BuyFlights")), "Buy Flights");
		
		// Assert Page Text
		UIOperation.verifyTextOnWebPage(expText);
		
		
	}
	
	@AfterTest
	public void tesrDown(){
		UIOperation.closeApp();
	}
}
