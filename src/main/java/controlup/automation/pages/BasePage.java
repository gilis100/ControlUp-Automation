package controlup.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.time.Duration;




public class BasePage {
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	protected WebDriver driver;
	protected final WebDriverWait wait;
	private static final int DEFAULT_TIMEOUT = 10;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
		PageFactory.initElements(this.driver, this);
	}



	protected WebElement waitTillClickable(By locator) {
		logger.info("Waiting until element is clickable: {}", locator);

		try {
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			logger.error("Element not clickable: " + locator, e);
			return null;
		}
	}

	public void click(By locator) {
		logger.info("Attempting to click on: {}", locator);
		try {
			WebElement element = waitTillClickable(locator);
			if (element != null) {
				element.click();
				logger.info("Clicked on: {}", locator);
			} else {
				logger.warn("Element was null for locator: {}", locator);
			}
		} catch (Exception e) {
			logger.error("Failed to click on: " + locator, e);
			throw e; 
		}
	}

	public void type(By locator, String text) {
		logger.info("Attempting to type '{}' into: {}", text, locator);

		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.clear();
			element.sendKeys(text);
			logger.info("Typed '{}' into: {}", text, locator);
		} catch (Exception e) {
			logger.error("Failed to type into: " + locator, e);
			throw e;
		}
	}

	public void navigateTo(String url) {
		logger.debug("Navigating to URL: {}", url);
		try {
			driver.get(url);
			logger.info("Navigated to URL: {}", url);
		} catch (Exception e) {
			logger.error("Failed to navigate to: " + url, e);
			throw e;
		}
	}
}