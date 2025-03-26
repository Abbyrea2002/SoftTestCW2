//package test;
//
//import main.LoginPage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.logging.Logger;
//
///**
// * Created by abbyr on 26/03/2025
// * COMMENTS ABOUT PROGRAM HERE
// */
//public class LoginPageTest
//{
//   private WebDriver driver;
//   private LoginPage loginPage;
//   private static final Logger logger = Logger.getLogger(LoginPageTest.class.getName());
//   private static final String CSV_FILE = "C:\\Users\\B00835054\\Downloads\\test_results.csv";
//   private static final String REPORT_FILE = "C:\\Users\\B00835054\\Downloads\\test_summary.txt"; // Summary report file
//
//   private static int totalTests = 0;
//   private static int passedTests = 0;
//   private static int failedTests = 0;
//   private String testStatus = "Unknown";
////   static {
////      try {
////         FileHandler fileHandler = new FileHandler("test_execution.log", true);
////         fileHandler.setFormatter(new SimpleFormatter());
////         logger.addHandler(fileHandler);
////         logger.setLevel(Level.INFO);
////      } catch (IOException e) {
////         System.err.println("Failed to initialize log file: " + e.getMessage());
////      }
////   }
//
//
//   @BeforeEach
//   public void setUp(){
//      try
//      {
//         System.setProperty("webdriver.chrome.driver", "C:\\Users\\B00835054\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
//         driver = new ChromeDriver();
//         driver.manage().window().maximize();
//         driver.get("https://practicetestautomation.com/practice-test-login/");
//         loginPage = new LoginPage(driver);
//         passwordValidator PasswordValidator = new passwordValidator();
//         logger.info("Test setup completed. Browser launched and navigated to login page.");
//      }catch(Exception e){
//         logger.severe("Failed to initialize WebDriver: " + e.getMessage());
//         fail("WebDriver initialization failed.");
//      }
//   }
//
//   public void captureScreenshot(String screenshotName) {
//      // Define the folder where screenshots will be saved.
//      // Make sure this folder exists or create it.
//      String folder = "C:\\Users\\B00835054\\Downloads\\Screenshots\\";
//      File directory = new File(folder);
//      if (!directory.exists()) {
//         directory.mkdirs();  // Creates the folder if it doesn't exist
//      }
//
//      // Create a filename with a timestamp to avoid overwriting previous screenshots.
//      String fileName = folder + screenshotName + "_" + System.currentTimeMillis() + ".png";
//
//      // Capture the screenshot.
//      File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//      try {
//         // Use Java NIO to copy the file to the destination path.
//         Path destination = Paths.get(fileName);
//         Files.copy(screenshotFile.toPath(), destination);
//         logger.info("Screenshot saved: " + fileName);
//      } catch (IOException e) {
//         logger.severe("Error saving screenshot: " + e.getMessage());
//         e.printStackTrace();
//      }
//   }
//
//   @ParameterizedTest
//   //import data in file numlines skipped skips firstline(column names)
//   // @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
//   @CsvSource({
//         "student, Password123, Success",
//         "invalidUser, short1, Failure",
//         "student, NoNumber, Failure",
//
//   })
//   public void testLogin(String username, String password, String expectedOutcome){
//      totalTests++;
//      try
//      {
////        boolean isPasswordValid = passwordValidator.isValidPassword(password);
////        assumeTrue(isPasswordValid, "Skipping test due to invalid password format: " + password);
//         //works to implement method but still executes test
//         if(!passwordValidator.isValidPassword(password)){
//            System.out.println("invalid password " + password);
//         }
//         loginPage.enterUsername(username);
//         loginPage.enterPassword(password);
//         loginPage.clickLogin();
//         String actualOutcome = driver.getCurrentUrl().contains("logged-in-successfully") ? "Success" : "Failure";
//
//
//         logTestResult(username, password, expectedOutcome, actualOutcome);
//
//         String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
//         if (expectedOutcome.equals("Success"))
//         {
//            assertEquals(expectedUrl, driver.getCurrentUrl());
//            passedTests++;
//         } else
//         {
//            String failureUrl = "https://practicetestautomation.com/practice-test-login/";
//            assertEquals(failureUrl, driver.getCurrentUrl());
//            failedTests++;
//         }
//         logger.info("Valid login test completed successfully.");
//      }catch(Exception e){
//         failedTests++;
//         logger.severe("Test failed due to exception: " + e.getMessage());
//         logTestResult(username, password, expectedOutcome, "Exception");
//         fail("Test encountered an unexpected exception.");
//         captureScreenshot("testLogin_failure");
//      }
//   }
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
//
//
//   @AfterEach
//   public void tearDown(){
//      if (driver != null){
//         driver.quit();
//         logger.info("Browser closed and test execution finished.");
//         captureScreenshot(testStatus);
//      }
//   }
//   @AfterAll
//   public static void generateSummaryReport()
//   {
//      try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE)))
//      {
//         writer.write("===== TEST SUMMARY REPORT =====\n");
//         writer.write("Total Test Cases Executed: " + totalTests + "\n");
//         writer.write("Number of Passed Tests: " + passedTests + "\n");
//         writer.write("Number of Failed Tests: " + failedTests + "\n");
//         writer.write("==============================\n");
//
//         System.out.println("Test Summary Report saved to: " + REPORT_FILE);
//      } catch (IOException e)
//      {
//         System.err.println("ERROR: Could not write test summary report - " + e.getMessage());
//      }
//   }
//
//}//class
