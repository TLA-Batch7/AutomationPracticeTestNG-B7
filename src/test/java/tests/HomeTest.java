package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTest{
    HomePage page;

    @BeforeMethod
    public void setUp(){
        page = new HomePage(driver);
        driver.get("http://automation.techleadacademy.io/#/");
    }

    @Test(testName = "US101: Test Header")
    public void test101(){
        Assert.assertEquals(page.title.getText(), "Automation with Selenium");
    }
}
