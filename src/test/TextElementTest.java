package test;

import main.TextElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
   private static final String REPORT_FILE = "C:\\Users\\B00835054\\Downloads\\test_summary.txt";

   //initialize test record variables
   private static int totalTests = 0;
   private static int passedTests = 0;
   private static int failedTests = 0;

   private static final Logger logger = Logger.getLogger(TextElementTest.class.getName());
   @BeforeEach
   public void setUp(){
      try{
         //set path to chromdriver
         //set path to chromdriver
         System.setProperty("webdriver.chrome.driver", "C:\\Users\\abbyr\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

         //open Chrome browser
         WebDriver driver = new ChromeDriver();

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
      totalTests++;
      try{
         //get page title
         title = driver.getTitle();

         //check if title is correct
         String expectedTitle = "Products";
         if(expectedOutcome.equals("Success")){
            assertEquals(expectedTitle, driver.getTitle());
            passedTests++;

         }else{
            assertNotEquals(expectedTitle, driver.getTitle());
            failedTests++;
         }
         logger.info("Valid text element test completed successfully.");
      }catch(Exception e){
         failedTests++;
         logger.severe("Test failed due to excpetion: " + e.getMessage());
      }

   }

   @AfterEach
   public void tearDown(){

      if (driver != null){
         driver.quit();
         logger.info("Browser closed and test execution finished.");
         //captureScreenshot();
      }
   }
//   @AfterAll
//   public static void generateSummaryReport(){
//
//   }




}//class
