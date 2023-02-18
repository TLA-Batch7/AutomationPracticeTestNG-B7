package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SynchronizationPage;


public class SynchronizationTest extends BaseTest{

    SynchronizationPage page;
    @BeforeMethod
    public void setUp(){
        page = new SynchronizationPage(driver);
        driver.get("http://automation.techleadacademy.io/#/synchronization");
    }

    @Test(testName = "US1013: Display alert within 10 seconds")
    public void test1013(){
        page.alertBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertTrue(true);
    }
}
