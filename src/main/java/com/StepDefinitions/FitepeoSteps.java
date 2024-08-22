package com.StepDefinitions;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Hooks.Hooks;

import io.cucumber.java.en.*;
import utils.ConfigReader;
import utils.WebUI;




public class FitepeoSteps 

{
    
     static WebDriver driver = Hooks.driver;
   
	@Given("Launch the web browser and navigate to FitPeo Homepage")
	public void launch_the_web_browser_and_navigate_to_fit_peo_homepage() {
		
		String url = ConfigReader.getProperty("url");
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		WebUI.waitForPageToLoad(driver);

        System.out.println("navigated to the "+url);
	}

	@When("I navigate to the Revenue Calculator Page")
	public void i_navigate_to_the_revenue_calculator_page() {
		WebUI.waitUntilClickable(driver, By.xpath("//a[@href='/revenue-calculator']")).click();
	}

	@When("I scroll down to the slider section")
	public void i_scroll_down_to_the_slider_section() throws InterruptedException {
		
		//WebElement slider = WebUI.waitUntilLocated(driver ,By.xpath("(//input[@type='range'])[1]")); 
		WebElement slider = WebUI.waitUntilLocated(driver ,By.xpath("//input[@id=':r0:']")); 

        Actions actions = new Actions(driver);
        actions.moveToElement(slider).perform();
		
	   
	}

	@When("I adjust the slider to set its value to {int}")
	public void i_adjust_the_slider_to_set_its_value_to(int value) throws InterruptedException {
		
		
		By ele = By.xpath("//span[@data-index='0']//input[@type='range']");
		WebUI.slide(driver,ele,value);
         Thread.sleep(1000);
		
	}

	@Then("the bottom text field value should be updated to {string}")
	public void the_bottom_text_field_value_should_be_updated_to(String value) {
		
		String ScrolledValue = driver.findElement(By.xpath("//input[@id=':r0:']")).getAttribute("value");
		Assert.assertEquals(ScrolledValue, value);
	   
	}

	@When("I update the bottom text field to {string}")
	public void i_update_the_bottom_text_field_to(String value) throws InterruptedException {
		
		WebUI.actionSendKeys(driver, By.id(":r0:") , value);

	}

	@Then("the slider position should be updated to the text field value {string}")
	public void the_slider_position_should_be_updated_to_the_text_field_value(String value) {
		String sliderValue = WebUI.waitUntilLocated(driver ,By.xpath("(//input[@type='range'])[1]")).getAttribute("value");

		Assert.assertEquals(sliderValue, value);

	}

	@When("I scroll down and select the checkboxes for all CPT codes {string}")
	public void i_scroll_down_and_select_the_checkboxes_for_all_cpt_codes(String codes) throws InterruptedException {
		List<String> checkList = Arrays.asList(codes.split(","));
		for(String s: checkList) {
			
			WebElement checkbox = WebUI.findEle(driver, By.xpath("//div[p[normalize-space()='CPT-" + s.trim() +"']]//input[@type='checkbox']"));
			if(!checkbox.isSelected()) {
				checkbox.click();
			}
		}
	}

	@Then("the Total Recurring Reimbursement should show the value Dollars: {string}")
	public void the_total_recurring_reimbursement_should_show_the_value_dollars(String value) throws InterruptedException {
		
		String total =WebUI.waitUntilLocated(driver, By.xpath("(//p[contains(text(),'Total Recurring Reimbursement for all Patients Per')])//p")).getText();
	    Assert.assertEquals(total, value);
	}

			
}
