package com.zenfra.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.inject.Inject;

public class Loginpage {

	private final WebDriver driver;

	@FindBy(id = "userName")
	WebElement usernameField;

	@FindBy(id = "password")
	WebElement passwordField;

	@FindBy(className = "login100-form-btn")
	WebElement signInbtn;

	@Inject
	public Loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loadLoginPage() {
		driver.get("https://premigration.zenfra.co/login");
	}

	public void enterUsername(String usernameText) {
		usernameField.sendKeys(usernameText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void clickSignInBtn() {
		signInbtn.click();
	}
}
