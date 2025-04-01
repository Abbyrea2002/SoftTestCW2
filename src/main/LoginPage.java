package main;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * Created by abbyr on 26/03/2025
 * COMMENTS ABOUT PROGRAM HERE
 */
public class LoginPage
{

   private WebDriver driver;
   //logger instance java.util.logging (javas logging framework)
   //logger - records events during execution of the program
   //getlogger method - retrives logger
   private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
   //TODO - Locators
   private By usernameField = By.id("username");
   private By passwordField = By.id("password");
   private By loginButton = By.id("submit");
   private By errorMessage = By.id("error");

   //TODO Constructor
   public LoginPage(WebDriver driver){
      this.driver = driver;

   }

   //TODO Actions
   public void enterUsername(String username){
      try{
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
         userField.sendKeys(username);
         //driver.findElement(usernameField).sendKeys(username);
         logger.info("Entered username: " + username);
      }catch(NoSuchElementException e){
         logger.severe("Username field not found: " + e.getMessage());
      }catch (TimeoutException e) {
         logger.severe("Timed out waiting for username field: " + e.getMessage());
      } catch (Exception e) {
         logger.severe("An unexpected error occurred while entering username: " + e.getMessage());
      }
   }

   public void enterPassword(String password){
      try
      {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
         passField.sendKeys(password);
         //driver.findElement(passwordField).sendKeys(password);
         logger.info("Entered password: ******");
      }catch(NoSuchElementException e){
         logger.severe("Password field not found: " + e.getMessage());
      }catch (TimeoutException e) {
         logger.severe("Timed out waiting for password field: " + e.getMessage());
      } catch (Exception e) {
         logger.severe("An unexpected error occurred while entering password: " + e.getMessage());
      }
   }
   public void clickLogin(){
      try
      {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
         loginBtn.click();
         //driver.findElement(loginButton).click();
         logger.info("Clicked Login Button");
      }catch(org.openqa.selenium.NoSuchElementException e){
         logger.severe("Login button not found: " + e.getMessage());
      }catch (TimeoutException e) {
         logger.severe("Timed out waiting for login button: " + e.getMessage());
      } catch (Exception e) {
         logger.severe("An unexpected error occurred while clicking login: " + e.getMessage());
      }
   }
   public String getErrorMessage(){
      try
      {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
         return errorElement.getText();
         //return driver.findElement(usernameField).getText();
      }catch(NoSuchElementException | TimeoutException e){
         logger.warning("Error message not found or took too long to appear: " + e.getMessage());
         return "";
      }
   }

}//class
