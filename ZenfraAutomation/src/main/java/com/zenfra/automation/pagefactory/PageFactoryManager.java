package com.zenfra.automation.pagefactory;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.zenfra.automation.pages.ConfigurationPage;
import com.zenfra.automation.pages.DashBoardPage;
import com.zenfra.automation.pages.Loginpage;

public class PageFactoryManager {

	private final WebDriver driver;

	@Inject
	private Provider<Loginpage> loginPage;

	@Inject
	private Provider<DashBoardPage> dashBoardPage;

	@Inject
	private Provider<ConfigurationPage> configurationPage;

	@Inject
	public PageFactoryManager(WebDriver driver) {
		this.driver = driver;
	}

	public Loginpage getLoginPage() {
		return loginPage.get();
	}

	public DashBoardPage getDashBoardPage() {
		return dashBoardPage.get();
	}

	public ConfigurationPage getConfigurationPage() {
		return configurationPage.get();
	}

	public void closeBrowser() {
		driver.quit();
	}

	public WebDriver getWebDriver() {
		return driver;
	}

}
