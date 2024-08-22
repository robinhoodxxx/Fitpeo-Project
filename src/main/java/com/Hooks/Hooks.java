package com.Hooks;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.runners.Parameterized.Parameter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.TestNGCucumberRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class Hooks {
	
	public static WebDriver driver ;
	
    @Before(order = 0)
	    public void setUp() {
	    	if(driver==null) {
	    	String browser =ConfigReader.getProperty("browser");
	    	driver = BrowserSetup(browser);
      
	    	}
	    }
	    
	    

	    @After(order = 0)
	    public void tearDown(Scenario scenario) {
	        
            takeScreenshot(scenario);

	        if (driver != null) {
	            driver.quit();
	            driver =null;
	        }
	    }
	    
	    public  WebDriver BrowserSetup(String browser) {
	    	
	    	
	        browser = browser.trim().toLowerCase();
	        try {
	        	
	        switch (browser) {
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup(); 
	                return new FirefoxDriver();
	            case "edge":
	                WebDriverManager.edgedriver().setup(); 
	                return new EdgeDriver();
	            case "chrome":
	            default:
	                WebDriverManager.chromedriver().setup(); 
	                return new ChromeDriver();
	        }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to initialize WebDriver");
	        }
	    }

	    public void takeScreenshot(Scenario scenario) {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(source, new File("target/spark-reports/screenshots/" + scenario.getName() + ".png"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
