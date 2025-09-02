package controlup.automation.ui.tests;

import controlup.automation.config.Config;
import controlup.automation.pages.InventoryPage;
import controlup.automation.pages.LoginPage;
import controlup.automation.utils.DriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VerifyCartBadgeTest {

    @Test(description = "Scenario 2: Add first item to cart and verify cart badge shows 1")
    public void cartBadgeShouldShowOneAfterAddingFirstItem() {
        WebDriver driver = DriverManager.getDriver();

        try {
            InventoryPage inventory = new LoginPage(driver)
            		 .openAndLogin(Config.username(), Config.password());       

            assertTrue(inventory.isLoaded(), "Inventory page should be loaded");

            inventory.addFirstItemToCart();

            assertEquals(inventory.getCartBadge(), "1", "Cart badge should display 1");

        } finally {
            DriverManager.quitDriver();
        }
    }
}