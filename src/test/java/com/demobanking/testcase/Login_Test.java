package com.demobanking.testcase;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demobanking.base.BaseClass;
import com.demobanking.excel.utils.Read_Excel;
import com.demobanking.pages.LoginPage;

public class Login_Test extends BaseClass {
	
	@Test
public void TC001_user_login_with_valid_credential() throws IOException{
	LoginPage lp = new LoginPage();
	driver.get(prop.getProperty("baseURL"));
	logger.info("url open successfully");
	lp.set_Username(prop.getProperty("username"));
	logger.info("sending username successfully");
	lp.set_Password(prop.getProperty("password"));
	logger.info("sending password successfully");
	lp.click_Login_Btn();
	logger.info("login btn click successfully");

	if(lp.verify_Login_Page()) {
		Assert.assertTrue(true);
		logger.info("login successfull");
		System.out.println();
	}else {
		logger.warn("login failed");
		System.out.println();
		Assert.assertTrue(false);
		}

	}

	
	@Test(dataProvider = "loginTest", dataProviderClass = Read_Excel.class)
	public void TC002_user_login_with_valid_and_invalid_credential(String userName,String password) throws IOException {
		LoginPage lp = new LoginPage();
		driver.get(prop.getProperty("baseURL"));
		logger.info("url open successfully");
		lp.set_Username(userName);
		logger.info("sending username successfully");
		lp.set_Password(password);
		logger.info("sending password successfully");
		lp.click_Login_Btn();
		logger.info("login btn click successfully");
		
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.warn("login failed");
			logger.warn("check username and password");
			System.out.println();
			Assert.assertTrue(false);
		}else {
			if(lp.verify_Login_Page()) {
			Assert.assertTrue(true);
			lp.click_Logout_Btn();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("login successfull");
			System.out.println();
			}
			else {
				logger.warn("login failed");
				logger.warn("check page title");
				System.out.println();
				Assert.assertTrue(false);
			}
			
		}
	}
	
}
