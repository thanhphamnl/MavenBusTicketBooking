package com.project.generalbase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseDriver {

	public static void waitForLoading(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public static void tearDown(WebDriver driver) throws InterruptedException {
		// Closing driver		
		if (driver != null) {
			sleep(4000);
			System.out.println("[Closing driver]");
			driver.close();
		}		
	}

	// STATIC SLEEP TO SEE TEST EXECUTION
	public static void sleep(int iWait) {
		try {
			Thread.sleep(iWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static String setCurrentDateTime() {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		   LocalDateTime now = LocalDateTime.now();
		   return dtf.format(now).toString();
	}

	public static void takeScreenshot(WebDriver driver, String fileName) {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/test-output/screenshots/" + setCurrentDateTime()+" " +fileName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
