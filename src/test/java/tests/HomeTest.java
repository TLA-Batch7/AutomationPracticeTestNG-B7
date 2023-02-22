package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
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
    }

    @Test(testName = "US101: Test Header")
    public void test101(){
        Assert.assertEquals(page.title.getText(), "Automation with Selenium");
    }

    @Test(testName = "report demo")
    public void test102(){
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report.html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("My Report");
        spark.config().setReportName("Test Report Name");
        extent.attachReporter(spark);

        ExtentTest test = extent.createTest("Report Demo Test");
        test.pass("PASS example");
        test.info("INFO example");
        test.skip("SKIP ex");
        test.fail("FAIL example");
        test.warning("WARNING example");

        //add a highlight to log message
        test.pass(MarkupHelper.createLabel("Pass test with highlight", ExtentColor.PINK));

        //add a table
        String table[][]={
                {"data1","data2","data3"},
                {"red","blue","orange"}
        };
        test.info(MarkupHelper.createTable(table));

        //add a list
        String list[]={"data1","data2","data3"};
        test.info(MarkupHelper.createOrderedList(list));

        //add details
        test.assignAuthor("Kuba");
        test.assignCategory("smoke test");
        test.assignDevice("Mac OS");

        extent.flush();
    }
}
