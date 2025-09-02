package controlup.automation.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;

import controlup.automation.config.Config;


public class LoginPage extends BasePage {
	
	  public LoginPage(WebDriver driver)  {
	        super(driver);
	    }

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");

    public LoginPage open() {
        navigateTo(Config.baseUrl());
        return this;
    }
    
    
    public LoginPage typeUsername(String user){
        type(usernameInput, user);
        return this;
    }

  
    public LoginPage typePassword(String pass){
        type(passwordInput, pass);
        return this;
    }


    public LoginPage submit(){
        click(loginButton);
        return this;
    }

 
    public InventoryPage openAndLogin(String user, String pass) {
        open();                   
        typeUsername(user);      
        typePassword(pass);     
        submit();                 
        return new InventoryPage(driver);
    }
}
