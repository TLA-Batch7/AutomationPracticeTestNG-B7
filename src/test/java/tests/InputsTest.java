package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class InputsTest extends BaseTest{
    @BeforeMethod
    public void setUp(){
        driver.get("http://automation.techleadacademy.io/#/inputs");
    }

    @Test(testName = "US401: Input field under Single Input Field section")
    public void test401(){
        String testData = "hello world";

        driver.findElement(By.id("message")).sendKeys(testData);
        driver.findElement(By.name("button1")).click();

        String actual = driver.findElement(By.name("message1")).getText();
        Assert.assertEquals(actual, testData);
    }
}
