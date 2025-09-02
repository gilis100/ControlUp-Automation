package controlup.automation.ui.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import controlup.automation.config.Config;
import controlup.automation.pages.InventoryPage;
import controlup.automation.pages.LoginPage;
import controlup.automation.utils.DriverManager;

public class VerifyInventoryItemsTest {

  @Test(description = "Scenario 1: Verify that the inventory page displays exactly 6 items.")
    public void verifyInventoryHasSixItems() {
        WebDriver driver = DriverManager.getDriver();

        try {
        	
            InventoryPage inventory = new LoginPage(driver)
                    .openAndLogin(Config.username(), Config.password());                        
                    
            assertTrue(inventory.isLoaded(), "Inventory page should be loaded");
            assertEquals(inventory.getItemsCount(), 6, "Inventory should show exactly 6 items");

        } finally {
            DriverManager.quitDriver();
        }
    }
}