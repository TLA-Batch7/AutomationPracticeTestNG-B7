package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InputsPage;
import pages.OthersPage;

public class OthersTest extends BaseTest{

    OthersPage page;

    @BeforeMethod
    public void setUp(){
        page = new OthersPage(driver);
        driver.get("http://automation.techleadacademy.io/#/others");
    }

    @Test(testName = "US2001 - Disabled button", description = "“Disabled Button” on Others page is disabled initially")
    public void test01(){
        String disableText = "DISABLED BUTTON";
        Assert.assertEquals(page.statusBtn.getText(),disableText);
        Assert.assertFalse(page.statusBtn.isEnabled());
    }

    @Test(testName = "US2002 - Enabled option from toggle button", description = "I need a Toggle button that will enable disabled button on Others page")
    public void test02(){
        String enableText = "ENABLED BUTTON";
        page.toggleBtn.click();

        Assert.assertEquals(page.statusBtn.getText(),enableText);
        Assert.assertTrue(page.statusBtn.isEnabled());
    }



}
