package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class WebUI {

	static long time = Long.parseLong(ConfigReader.getProperty("exptimeout"));

	public static WebElement waitUntilLocated(WebDriver driver, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitUntilPresent(WebDriver driver, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static WebElement waitUntilClickable(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public static void sliderScroll(WebDriver driver, By sliderLocator, int value) {
		WebElement slider = waitUntilLocated(driver, sliderLocator);
		int maxValue = Integer.parseInt(slider.getAttribute("max"));
		int minValue = Integer.parseInt(slider.getAttribute("min"));
		int sliderWidth = slider.getSize().width;

		Actions actions = new Actions(driver);

		int desiredValue = value;
		int range = maxValue - minValue;
		int offset = (int) (((desiredValue - minValue) / (double) range) * sliderWidth);

		actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();

	}

	public static void actionSendKeys(WebDriver driver, By locator, String val) {
		WebElement inputField = driver.findElement(locator);
		// String val = inputField.getAttribute("value");

		Actions actions = new Actions(driver);

		actions.click(inputField).perform();
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		actions.sendKeys(val).perform();

//    
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void slide(WebDriver driver, By sliderLocator, int value) {
		WebElement sliderThumb = driver.findElement(sliderLocator);

		double startingOffset = (value / 9) + 3; // 816 //823;

		Actions actions = new Actions(driver);

		actions.clickAndHold(sliderThumb).moveByOffset((int) startingOffset, 0).release().perform();

	}

	public static WebElement findEle(WebDriver driver, By sliderLocator) {
		return driver.findElement(sliderLocator);
	}

}
