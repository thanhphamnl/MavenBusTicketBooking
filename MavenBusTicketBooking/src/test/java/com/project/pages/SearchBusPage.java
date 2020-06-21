package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.project.objectrepository.ObjectRepos;
import com.project.pageobject.PageObject;

public class SearchBusPage extends PageObject {

	public SearchBusPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = ObjectRepos.HomePage.st_HomePage)
	public WebElement stHome;
	
	@FindBy(xpath = ObjectRepos.HomePage.st_CloseIcon)
	public WebElement stCloseIcon;
	
	@FindBy(xpath = ObjectRepos.HomePage.st_From)
	public WebElement stFrom;

	@FindBy(xpath = ObjectRepos.HomePage.st_To)
	public WebElement stTo;

	@FindBy(xpath = ObjectRepos.HomePage.st_Select)
	public WebElement stSelect;

	@FindBy(xpath = ObjectRepos.HomePage.st_Calendar)
	public WebElement stCalendar;

	@FindBy(xpath = ObjectRepos.HomePage.st_PickDay)
	public WebElement stPickDay;

	@FindBy(id = ObjectRepos.HomePage.st_IdSearch)
	public WebElement stIdSearch;

	@FindBy(xpath = ObjectRepos.HomePage.st_Fillter)
	public WebElement stFillter;

	@FindBy(xpath = ObjectRepos.HomePage.st_SeatAvailble)
	public WebElement stSeatAvailble;
	
	@FindBy(xpath = ObjectRepos.HomePage.st_ViewSeatAvailable)
	public WebElement stViewSeatAvailble;

}