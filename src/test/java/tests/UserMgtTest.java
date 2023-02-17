package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class UserMgtTest extends BaseTest{
    @DataProvider(name="role")
    public Object[] data(){
        return new String[] {"Mentor", "Student","Instructor"};
    }

    @BeforeMethod
    public void setUp(){
        driver.get("http://automation.techleadacademy.io/#/usermgt");
    }

    @Test(testName="US1010: Staging table view", description= "Verify temp table is getting populated",
            dataProvider = "role")
    public void test01(String role){
        //Using Faker to populate fake data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys(role);

        driver.findElement(By.id("submit-btn")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//td[1]")).getText(), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[2]")).getText(), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[3]")).getText(), phone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[4]")).getText(), email);
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]")).getText(), role);
    }

    @Test(testName = "US1010: Staging table view - DB check", dataProvider = "role")
    public void test02(String role){
        //Using Faker to populate fake data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys(role);

        driver.findElement(By.id("submit-btn")).click();

        //accessing DB page
        driver.findElement(By.id("access-db-btn")).click();

        //switch to DB window
        for(String each: driver.getWindowHandles()){
            if(!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }
        //validate user email doesn't exist
        String xpath = "//td[text()='"+ email + "']";

        //using list to avoid NoSuchElementException, which would stop the execution and not reach Assertion
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        Assert.assertEquals(elementList.size(),0);
    }

    @Test(testName = "US1011: Clear staging table option")
    public void test1011(){
        //Using Faker to populate fake data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys("Student");

        driver.findElement(By.id("submit-btn")).click();

        driver.findElement(By.id("clear-btn")).click();

        List<WebElement> dataCount = driver.findElements(By.xpath("//table[@id='list-table']//td"));
        Assert.assertEquals(dataCount.size(), 0);
    }

    @Test(testName = "US1012: Adding a new user to DB")
    public void test1012(){
        //Using Faker to populate fake data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys("Student");

        driver.findElement(By.id("submit-btn")).click();
        driver.findElement(By.id("submit-table-btn")).click();

        driver.findElement(By.id("access-db-btn")).click();

        for(String each: driver.getWindowHandles()){
            if (!each.equalsIgnoreCase(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }

        List<WebElement> emailList = driver.findElements(By.xpath("//td[5]"));
        boolean isPresent = false;

        for (WebElement each: emailList){
            if (each.getText().equalsIgnoreCase(email)) {
                isPresent = true;
                break;
            }
        }

        Assert.assertEquals(isPresent, true);
    }
}
