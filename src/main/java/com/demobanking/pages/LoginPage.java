package com.demobanking.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demobanking.base.BaseClass;

public class LoginPage extends BaseClass {
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "uid")
	@CacheLookup
	private WebElement txtUsername;

	@FindBy(name = "password")
	@CacheLookup
	private WebElement txtPassword;

	@FindBy(name = "btnLogin")
	@CacheLookup
	private WebElement btnLogin;
	
	@FindBy(xpath = "//*[text()='Log out']")
	@CacheLookup
	private WebElement logout;

	public void set_Username(String username) {
		txtUsername.sendKeys(username);
	}

	public WebElement get_Username() {

		return txtUsername;
	}

	public void set_Password(String password) {
		txtPassword.sendKeys(password);
	}

	public WebElement get_Password() {

		return txtPassword;
	}

	public void click_Login_Btn() {
		btnLogin.click();
	}
	
	public void click_Logout_Btn() {
		logout.click();
	}
	public boolean verify_Login_Page() {
		String expected = prop.getProperty("home_page_title");
		String actual = driver.getTitle();
		if(actual.equals(expected)) {
			return true;
		}
		return false;
	}
}
