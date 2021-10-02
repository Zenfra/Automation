package com.zenfra.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;

public class ConfigurationPage {

	private final WebDriver driver;

	@FindBy(id = "ContentPlaceHolder1_txtSearch")
	WebElement searchFieldBox;

	@FindBy(id = "ContentPlaceHolder1_btnGo")
	WebElement goBtn;
	
	@Inject
	public ConfigurationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterSearchText(String searchText) {
		searchFieldBox.sendKeys(searchText);
	}

	public void clickOnGoBtn() {
		goBtn.click();
	}
	
	public void clickToEditCurrentValueOfItem(String itemName) {
		new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tr[.//*[contains(text(),'"+itemName+"')]]/td[3]/a")));
		new WebDriverWait(driver, 90).until(ExpectedConditions.elementToBeClickable(By.xpath("//table//tr[.//*[contains(text(),'"+itemName+"')]]/td[3]/a"))).click();
	}
	public void enterNewValueToCurrentItem(String itemName,String newValue) {
		WebElement inputfield=new WebDriverWait(driver, 90).until(ExpectedConditions.elementToBeClickable(By.xpath("//table//tr[.//*[contains(text(),'"+itemName+"')]]/td[3]/input")));
		inputfield.clear();
		inputfield.sendKeys(newValue);
	}
	public void enterSelectValueToCurrentItem(String itemName,String newValue) {
		new Select(driver.findElement(By.xpath("//table//tr[.//*[contains(text(),'"+itemName+"')]]/td[3]/select"))).selectByValue(newValue);;
	}
	
					
	public void clickSubmitToUpdate(String itemName) {
		driver.findElement(By.xpath("//table//tr[.//*[contains(text(),'"+itemName+"')]]/td[3]/a[text()='Update']")).click();
	}
	public void clickAlertOKButton() {
		new WebDriverWait(driver, 90).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

}
