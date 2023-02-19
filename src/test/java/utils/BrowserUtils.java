package utils;

import org.openqa.selenium.WebDriver;
import tests.BaseTest;

public class BrowserUtils extends BaseTest {
    public static void switchToNewWindow(WebDriver driver){
        for(String each: driver.getWindowHandles()){
            if(!each.equalsIgnoreCase(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }
    }
}
