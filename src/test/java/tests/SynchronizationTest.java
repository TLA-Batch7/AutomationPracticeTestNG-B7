package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SynchronizationTest extends BaseTest{

    @BeforeMethod
    public void setUp(){
        driver.get("http://automation.techleadacademy.io/#/synchronization");
    }

    @Test(testName = "US1013: Display alert within 10 seconds")
    public void test1013(){
        driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertTrue(true);
    }
}
