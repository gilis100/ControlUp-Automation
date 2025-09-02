package controlup.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	private final By inventoryItems = By.cssSelector(".inventory_item");
	private final By firstAddToCartBtn = By.cssSelector(".inventory_item button.btn_inventory");
	private final By cartBadge = By.cssSelector(".shopping_cart_badge");	

	public InventoryPage(WebDriver driver) {
		super(driver);
	}


	public int getItemsCount() {
		List<WebElement> items = driver.findElements(inventoryItems);
		logger.info("Inventory contains {} items", items.size());
		return items.size();
	}

	public boolean isLoaded() {
		return !driver.findElements(inventoryItems).isEmpty();
	}
	public InventoryPage addFirstItemToCart() {
		click(firstAddToCartBtn);
		logger.info("Clicked on Add to cart for the first item");
		return this;
	}

	public String getCartBadge() {
		logger.info(" Get cart badge");
		return driver.findElement(cartBadge).getText();
	}
}
