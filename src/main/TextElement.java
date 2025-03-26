package main;

import org.openqa.selenium.WebDriver;

/**
 * Created by abbyr on 26/03/2025
 * COMMENTS ABOUT PROGRAM HERE
 */
public class TextElement
{
   private final WebDriver driver;
   private String title;
   public TextElement(WebDriver driver){
      this.driver = driver;
   }

   public String getTitle(){
      return title;
   }
}//class
