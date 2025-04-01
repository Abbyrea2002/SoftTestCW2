package main;

import test.TextElementTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by abbyr on 01/04/2025
 * COMMENTS ABOUT PROGRAM HERE
 */
public class TestReport
{
   private static final String REPORT_FILE = "C:\\Users\\abbyr\\Downloads\\test_summary.txt";
   //CSV generated with test results
   private static final String CSV_FILE = "C:\\Users\\abbyr\\Downloads\\test_results.csv";
   private static final Logger logger = Logger.getLogger(TextElementTest.class.getName());
   private static int totalTests = 0;
   private static int passedTests = 0;
   private static int failedTests = 0;
   private static List<String> observations = new ArrayList<>();
   private static List<String> issues = new ArrayList<>();
   private static final TestReport instance = new TestReport();

   private TestReport(){

   }
   public static TestReport getInstance(){
      return instance;
   }
   public void addTestResult(boolean passed){
      totalTests++;
      if(passed){
         passedTests++;
      }else{
         failedTests++;
      }
   }
   public void addObservation(String observation){
      observations.add(observation);

   }
   public void addIssue(String issue){
      issues.add(issue);

   }

   public static void generateSummaryReport()
   {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE)))
      {
         writer.write("===== TEST SUMMARY REPORT =====\n");
         writer.write("Total Test Cases Executed: " + totalTests + "\n");
         writer.write("Number of Passed Tests: " + passedTests + "\n");
         writer.write("Number of Failed Tests: " + failedTests + "\n");
         writer.write("==============================\n");

         writer.write("======== OBSERVATIONS ========\n");
         if(observations.isEmpty()){
            writer.write("No observations to report\n");
         }else{
            for(String obs : observations){
               writer.write("-" + obs + "\n");
            }
         }
         writer.write("=========== ISSUES ===========\n");
         if(issues.isEmpty()){
            writer.write("No major issues found\n");
         }else{
            for(String issue : issues){
               writer.write("-" + issue + "\n");
            }
         }
         writer.write("==============================\n");
         System.out.println("Test Summary Report saved to: " + REPORT_FILE);
      } catch (IOException e)
      {
         System.err.println("ERROR: Could not write test summary report - " + e.getMessage());
      }
   }
   public static void logTestResult(String title, String expectedOutcome, String actualOutcome) {
      boolean fileExists = new File(CSV_FILE).exists(); // Check if file exists
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
         if (!fileExists) { // If first time, write headers
            writer.write("Title,ExpectedOutcome,ActualOutcome\n");
         }
         writer.write(title + "," + expectedOutcome + "," + actualOutcome + "\n");
         writer.flush();
         logger.info("Test result saved to CSV.");
      } catch (IOException e) {
         logger.severe("Failed to write test result to CSV: " + e.getMessage());
      }
   }
}//class
