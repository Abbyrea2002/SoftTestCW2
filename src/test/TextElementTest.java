package test;

import main.TestReport;
import main.TextElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by abbyr on 26/03/2025
 * COMMENTS ABOUT PROGRAM HERE
 */
public class TextElementTest
{

   private WebDriver driver;
   private TextElement textElement;

   //CSV generated with test results
   private static final String CSV_FILE = "C:\\Users\\abbyr\\Downloads\\test_results.csv";
   //summary report file generated
  private static TestReport report = TestReport.getInstance();



   private static final Logger logger = Logger.getLogger(TextElementTest.class.getName());
   @BeforeEach
   public void setUp(){
      try{
         //set path to chromdriver
         //set path to chromdriver
         System.setProperty("webdriver.chrome.driver", "C:\\Users\\abbyr\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

         //open Chrome browser
         driver = new ChromeDriver();

         //go to website
         driver.get("https://www.saucedemo.com/v1/inventory.html");
        textElement = new TextElement(driver);

         logger.info("Test setup completed. Browser launched and navigated to login page.");
         }catch(Exception e){
         logger.severe("Failed to initialize WebDriver: " + e.getMessage());
         fail("WebDriver initialization failed.");
}     }

   @ParameterizedTest
   @CsvFileSource(resources = "/TitleData(Sheet1).csv", numLinesToSkip = 1)
   public void testTitle(String testCaseId, String title, String expectedOutcome){

      try{
         //get page title
         String actualTitle = driver.getTitle();
         //check if title is correct
         String expectedTitle = "Swag Labs";

         boolean correctTitle = actualTitle.equals(expectedTitle);
         report.addTestResult(correctTitle);

        TestReport.logTestResult(testCaseId, title, expectedOutcome, actualTitle);
         if(correctTitle){
            assertEquals(expectedTitle, driver.getTitle());
            report.addObservation("Success!! Titles match: Expected Title-" + expectedTitle + " Actual Title-" + actualTitle);

         }else{
            assertNotEquals(expectedTitle, driver.getTitle());
            report.addIssue("Failure!! Titles do not match: Expected Title "+ expectedTitle + "Actual Title " + actualTitle);

            logger.info("Capturing screenshot due to title mismatch for test case: " + testCaseId);
            TestReport.captureScreenshot(testCaseId, driver);
         }
         logger.info("Valid text element test completed successfully.");
      }catch(Exception e){

         logger.severe("Test failed due to exception: " + e.getMessage());
      }

   }


   @AfterEach
   public void tearDown(){

      if (driver != null){
         driver.quit();
         logger.info("Browser closed and test execution finished.");

      }
   }

   @AfterAll
   public static void generateReport(){
      TestReport.getInstance().generateSummaryReport();
   }








}//class
