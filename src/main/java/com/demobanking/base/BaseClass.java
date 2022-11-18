package com.demobanking.base;


import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static	Properties prop;
	public static	FileInputStream ip;
	public static   WebDriver driver;
	public static Logger logger;
		public BaseClass(){
			logger = LogManager.getLogger();
			
			try {
				prop = new Properties();
				ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/config/env/base.properties");
				prop.load(ip);
			}catch (Exception e) {
					e.printStackTrace();
				}
			
			} 
		
		@BeforeClass
		public void setup() {
			String browser = prop.getProperty("browser");
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				//System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				//System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}
		@AfterClass
		public void tearDown() {
			driver.quit();
		}
		

		
		public static boolean isAlertPresent() {
			try
			{ 
		    driver.switchTo().alert();
			return true;
			}catch(Exception e) {
				return false;
			}
		}
}
