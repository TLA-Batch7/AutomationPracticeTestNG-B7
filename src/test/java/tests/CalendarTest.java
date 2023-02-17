package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CalendarPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarTest extends BaseTest{
    CalendarPage page;

    @BeforeMethod
    public void setUp(){
        page = new CalendarPage(driver);
        driver.get("http://automation.techleadacademy.io/#/calendar");
    }

    @Test(testName = "US1015: Choosing date from calendar")
    public void test1015(){
        page.endDateInputField.click();
        page.calendarTomorrowDate.click();
        page.submitCalendarBtn.click();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        LocalDate current = LocalDate.now();
        String currentDate = formatter.format(current);
        String tomorrowDate = formatter.format(current.plusDays(1));

        String expectedDate = "There are 1 days between " + currentDate + " and " + tomorrowDate + "";

        String actualDate = page.resultText.getText();

        Assert.assertEquals(actualDate, expectedDate);
    }

    @Test(testName = "US1016: Choosing date from the calendar. Start date test.")
    public void test1016(){
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
        driver.findElement(By.xpath(
                "//div[contains(@class, 'selected')]/following-sibling::div")).click();
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String currentDate = formatter.format(LocalDate.now());

        String actualText = driver.findElement(By.id("num-days")).getText();

        Assert.assertTrue(actualText.contains(currentDate));
    }

    @Test(testName = "US1017: Choosing date from the calendar. End date test.")
    public void test1017(){
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
        driver.findElement(By.xpath(
                "//div[contains(@class, 'selected')]/following-sibling::div")).click();
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String selectedDate = formatter.format(LocalDate.now().plusDays(1));

        String actualText = driver.findElement(By.id("num-days")).getText();

        Assert.assertTrue(actualText.contains(selectedDate));
    }
}
