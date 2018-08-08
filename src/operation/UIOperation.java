package operation;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testcases.ExecuteTestThroughTestNG;

public class UIOperation extends ExecuteTestThroughTestNG {
		
		static Logger logger = Logger.getLogger("devpinoyLogger");
		/* Function Name : launchApplication
		 * Parameter : URL (String)
		 * Description :  This function invokes flight reservation application. */
		public static void invokeApplication(String URL){
			logger.info("Opening Flight Reservation Application.");
			driver.get(URL);
			logger.info("OpenedFlight Reservation Application.");
		}
		
		/* Function Name : assertTitle
		 * Parameter : value (String)
		 * Description :  This function verifies the page title. */
		
		public static void assertTitle(String value){
			logger.info("Verifying Page Title.....");
			Assert.assertEquals(driver.getTitle(),value);
			logger.info("Verified Page Title.....");
		}
		
		/* Function Name : sendKeys
		 * Parameter : locator (By), objectName(String), value(String)
		 * Description :  This function enters a specified value in an edit field */
		public static void sendKeys(By locator,String objectName,String value){
			logger.info("Entering '"+value+"' in the '"+objectName+"'");
			WebElement elementToBeSet = driver.findElement(locator);
			highlightElement(elementToBeSet);
			elementToBeSet.sendKeys(value);
			logger.info("Entered'"+value+"' in the '"+objectName+"'");
		}
		
		/* Function Name : click
		 * Parameter : locator (By), objectName(String)
		 * Description :  This function clicks on specified web object */
		
		public static void click(By locator, String objectName){
			logger.info("Clicking on '"+objectName+"'");
			WebElement elementToBeClicked = driver.findElement(locator);
			highlightElement(elementToBeClicked);
			elementToBeClicked.click();
			logger.info("Clicked on '"+objectName+"'");
		}
		
		/* Function Name : select
		 * Parameter : locator (By), objectName(String), value(String)
		 * Description :  This function selects a specified value from a web list. */
		
		public static void select(By locator, String objectName, String value){
			logger.info("Selecting '"+value+"' from the '"+objectName+"'");
			WebElement elementToBeSelected = driver.findElement(locator);
			highlightElement(elementToBeSelected);
			Select select = new Select(elementToBeSelected);
			select.selectByVisibleText(value);
			logger.info("Selected '"+value+"' from the '"+objectName+"'");
		}
		
		/* Function Name : verifyTextOnWebPage
		 * Parameter : value(String)
		 * Description :  This function verifies a specified text on web page. */
		
	public static void verifyTextOnWebPage(String value){
			logger.info("Verifying Text on Web page : "+value);
			Assert.assertTrue(driver.getPageSource().contains(value));
			logger.info("Verified Text on Web page : "+value);
		}
	
	/* Function Name : closeApp
	 * Parameter : Nil
	 * Description :  This function kills the driver */
	
		public static void closeApp(){
			logger.info("Closing driver.");
			driver.quit();
			logger.info("Closed driver.");
		
		}
		
	
	private static void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}
	
	
	}


