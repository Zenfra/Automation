package com.zenfra.automation;

import org.testng.annotations.Test;

public class ZenfraTestcase extends TestBase {

	@Test(description = "Login", priority = 1)
	public void Login() {
		pageFactoryManager.getLoginPage().loadLoginPage();
		pageFactoryManager.getLoginPage().enterUsername("prakash.subramanian@virtualtechgurus.com");
		pageFactoryManager.getLoginPage().enterPassword("Test@123");
		pageFactoryManager.getLoginPage().clickSignInBtn(); 
		
	}
	
	@Test(description = "Discovery", priority = 2)
	public void Discovery() {
		pageFactoryManager.getDashBoardPage().MigrationMenuItem();
		pageFactoryManager.getDashBoardPage().Discovery();
		
	}
	@Test(description = "CreateWindowsServer", priority = 3)
	public void WindowsServer() {
		pageFactoryManager.getDashBoardPage().createWindowsServer();
		
	}	
		
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		

