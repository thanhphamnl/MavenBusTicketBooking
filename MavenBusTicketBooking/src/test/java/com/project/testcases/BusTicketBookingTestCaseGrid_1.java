package com.project.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.generalbase.BaseDriver;
import com.project.pages.SearchBusPage;
import com.project.utilities.ExcelReader;
import com.project.utilities.PropertyReader;

public class BusTicketBookingTestCaseGrid_1{

	WebDriver driver = null;
	DesiredCapabilities caps = null;
	SearchBusPage sb = null;
	Actions action = null;
	String sTitleHomePage = "Book Bus Travels, AC Volvo Bus, rPool & Bus Hire - redBus India";

	// String browser = "firefox"; //this has to eventually come from testng.xml
	@Parameters({ "browser" })
	@BeforeClass
	public void Launch(String browser) {
		try {
			if (browser.equals("chrome")) {
				caps = DesiredCapabilities.chrome();
				caps.setBrowserName("chrome");
				// caps.setPlatform(Platform.ANY);
				// caps.setVersion("");
			} else if (browser.equals("firefox")) {
				caps = DesiredCapabilities.firefox();
				caps.setBrowserName("firefox");
			}
			driver = new RemoteWebDriver(new URL(PropertyReader.ReadProperty("RemoteWebDriverUrl")), caps);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void RedBus() {
		try {
			driver.get(PropertyReader.ReadProperty("urlBusTicket"));
			//driver.manage().window().maximize();

			// Check the Home Page is loaded?.
			assertEquals(driver.getTitle(), sTitleHomePage);
			System.out.println("Home Page is loaded successfully");
			BaseDriver.takeScreenshot(driver, "HomePage");
			BaseDriver.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "getBusData", dependsOnMethods = "RedBus")
	public void SearchBus(Hashtable<String, String> data) throws InterruptedException {
		sb = new SearchBusPage(driver);
		action = new Actions(driver);
		// From
		BaseDriver.sleep(1000);
		sb.stFrom.clear();
		sb.stFrom.sendKeys(data.get("from"));
		BaseDriver.sleep(1000);
		action.moveToElement(sb.stSelect).click().build().perform();

		// Destination
		BaseDriver.sleep(1000);
		sb.stTo.clear();
		sb.stTo.sendKeys(data.get("to"));
		BaseDriver.sleep(1000);
		action.moveToElement(sb.stSelect).click().build().perform();

		// Pick up date
		BaseDriver.sleep(1000);
		setDate(data.get("date"));
		BaseDriver.sleep(1000);
		BaseDriver.takeScreenshot(driver, "FilledSearchBus");

		// Search button Click
		sb.stIdSearch.click();
		BaseDriver.sleep(2000);
		BaseDriver.takeScreenshot(driver, "ListBus");

		// Fillter Bus
//		BaseDriver.sleep(3000);
//		sb.stFillter.click();
//		BaseDriver.sleep(3000);
//		BaseDriver.takeScreenshot(driver, "FillterBus");

//		// Seats Available
//		BaseDriver.sleep(3000);
//		sb.stViewSeatAvailble.click();
//		BaseDriver.sleep(5000);
//		BaseDriver.takeScreenshot(driver, "ViewSeatAvailable");
//
//		// Close Seats Available
//		BaseDriver.sleep(3000);
//		sb.stCloseIcon.click();
//		BaseDriver.sleep(2000);
//		BaseDriver.takeScreenshot(driver, "ClosedSeatAvailable");
//		
//		BaseDriver.sleep(2000);
//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_UP);
//		System.out.println("scroll up");
		
		// Return to Home Page
		BaseDriver.sleep(1000);
		sb.stHome.click();
		BaseDriver.sleep(2000);
	}

	// Set Date on Calendar
	public void setDate(String expectedDate) {

		// Click and open the datepickers
		driver.findElement(By.id("onward_cal")).click();

		// This is from date picker table
		WebElement dateWidgetFrom = driver.findElement(By.xpath("//table[@class='rb-monthTable first last']//tbody"));

		// This are the rows of the from date picker table
		// List<WebElement> rows = dateWidgetFrom.findElements(By.tagName("tr"));

		// This are the columns of the from date picker table
		List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

		// DatePicker is a table. Thus we can navigate to each cell
		// and if a cell matches with the current date then we will click it.
		for (WebElement cell : columns) {		
			// Select expected date
			//System.out.println(cell.getText().toString());
			if (cell.getText().equals(expectedDate)) {
				cell.click();
				break;
			}
		}

	}

	@DataProvider
	public Object[][] getBusData() throws IOException {

		String FilePath = System.getProperty("user.dir") + "/src/test/java/com/project/testdata";
		String FileName = "BusTestData.xlsx";
		String Sheetname = "BusTestData";
		return ExcelReader.ReadExcelDataToObjectArr(FilePath, FileName, Sheetname);

	}

	@AfterClass
	public void CloseBrowser() throws InterruptedException {
		BaseDriver.tearDown(driver);
	}
}
