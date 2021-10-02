package com.zenfra.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;

public class DashBoardPage {

	@SuppressWarnings("unused")
	private final WebDriver driver;

	@FindBy(xpath = "//*[@id=\"draggable-column-list\"]/div/ul/li[1]/a")
	WebElement MigrationMenuItem;
	
	@FindBy(xpath="//*[@id=\"draggable-column-list\"]/div/ul/li[1]/div/div/ul[2]/li[1]/span/a")
	WebElement Discovery;
	
	@FindBy(xpath="//a[@class='nav-item nav-link dropdown-toggle un-size']")
	WebElement logoutBtn;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement logoutBtn1;		

	@Inject
	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void MigrationMenuItem() {
		MigrationMenuItem.click();
		
	}
	
	public void Discovery() {
		Discovery.click();
		
	}
	
	public void clickOnLogout() {
		logoutBtn.click();
		logoutBtn1.click();
		
	}
	public void clickAlertOKButton() {
		
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		//new WebDriverWait(driver,60).until(ExpectedConditions.alertIsPresent());
	 	//driver.switchTo().alert().accept();
	 		
	}
	
	public void createWindowsServer()
	{
		driver.findElement(By.xpath("//div[contains (@class ,'fav-rep-plusarr-btns fixed-plus-icon show-fixed-plus-icon')]/button")).click();
		new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains (@class,'row advance-filter-row')]//ul//li[contains (@class,'cat-server-icon')]"))).click();
		new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains (@class,'row advance-filter-row')]//ul//li[contains(@class ,'cat-windows-icon')]"))).click();
		new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Discovery Type']//li[contains(@class,'active-cat')]//span[contains(text(),'Server')]"))).click();
		new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains (@id,'Analytics By')]//ul[@class='filter-ul-rect']//span[contains(text(),'Server')]"))).click();	
		driver.findElement(By.cssSelector("button[title='Add to Favorite']")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//input[@placeholder='Enter Your Favorite View Name']")).sendKeys("Windows_Automation");
		//driver.findElement(By.xpath("//div[contains(@class,'css-ih8l0b-Control')]//div[contains(@class,'css-g1d714-ValueContainer')]")).click();
		
		new WebDriverWait(driver, 120);
		
		WebElement selectMyElement = driver.findElement(By.xpath("//div[contains(@class,'css-ih8l0b-Control')]//div[contains(@class,'css-g1d714-ValueContainer')]")); 
		selectMyElement.click();
        
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys("Test").perform();
		
		//Select drop = new Select (driver.findElement(By.xpath("//div[contains(@class,'css-ih8l0b-Control')]//div[contains(@class,'css-g1d714-ValueContainer')]")));
		//drop.selectByVisibleText("Test category");
		new WebDriverWait(driver, 180);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		new WebDriverWait(driver, 360);
		
	
	}
}