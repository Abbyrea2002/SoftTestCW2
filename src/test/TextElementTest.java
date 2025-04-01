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
   private static final String CSV_FILE = "C:\\Users\\B00835054\\Downloads\\test_results.csv";
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
   public void testTitle(String title, String expectedOutcome){

      try{
         //get page title
         String actualTitle = driver.getTitle();
         //check if title is correct
         String expectedTitle = "Swag Labs";

         boolean correctTitle = actualTitle.equals(expectedTitle);
         report.addTestResult(correctTitle);

        TestReport.logTestResult(title, expectedOutcome, actualTitle);
         if(correctTitle){
            assertEquals(expectedTitle, driver.getTitle());
            report.addObservation("Titles match: Expected Title-" + expectedTitle + " Actual Title-" + actualTitle);

         }else{
            assertNotEquals(expectedTitle, driver.getTitle());
            report.addIssue("Titles do not match: Expected Title "+ expectedTitle + "Actual Title " + actualTitle);
         }
         logger.info("Valid text element test completed successfully.");
      }catch(Exception e){

         logger.severe("Test failed due to exception: " + e.getMessage());
      }

   }

//   private void logTestResult(String username, String password, String expectedOutcome, String actualOutcome) {
//      boolean fileExists = new File(CSV_FILE).exists(); // Check if file exists
//      try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
//         if (!fileExists) { // If first time, write headers
//            writer.write("Username,Password,ExpectedOutcome,ActualOutcome\n");
//         }
//         writer.write(username + "," + password + "," + expectedOutcome + "," + actualOutcome + "\n");
//         writer.flush();
//         logger.info("Test result saved to CSV.");
//      } catch (IOException e) {
//         logger.severe("Failed to write test result to CSV: " + e.getMessage());
//      }
//   }

   @AfterEach
   public void tearDown(){

      if (driver != null){
         driver.quit();
         logger.info("Browser closed and test execution finished.");
         //captureScreenshot();
      }
   }

   @AfterAll
   public static void generateReport(){
      TestReport.getInstance().generateSummaryReport();
   }








}//class
