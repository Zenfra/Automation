package com.zenfra.automation.setup;

import org.openqa.selenium.WebDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class DriverModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(DriverManager.class).to(DriverImplSetup.class).in(Scopes.SINGLETON);
	}
	@Provides
	public WebDriver getWebDriver(DriverManager driverManager) {
		return driverManager.getWebDriver();
	}
	

}
