package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CalendarPage;
import pages.InputsPage;


public class InputsTest extends BaseTest{

    InputsPage page;
    @BeforeMethod
    public void setUp(){
        page = new InputsPage(driver);
        driver.get("http://automation.techleadacademy.io/#/inputs");
    }

    @Test(testName = "US401: Input field under Single Input Field section")
    public void test401(){
        String testData = "hello world";

        page.messageField.sendKeys(testData);
        page.showMessageBtn.click();

        String actual = page.messageResult.getText();
        Assert.assertEquals(actual, testData);
    }
}
