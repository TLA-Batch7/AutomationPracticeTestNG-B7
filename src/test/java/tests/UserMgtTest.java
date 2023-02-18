package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UserMgtPage;

import java.util.List;

public class UserMgtTest extends BaseTest{

    UserMgtPage page;

    @DataProvider(name="role")
    public Object[] data(){
        return new String[] {"Mentor", "Student","Instructor"};
    }

    @BeforeMethod
    public void setUp(){
        page = new UserMgtPage(driver);
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

        //version 1 - first way we learned
//        driver.findElement(By.id("Firstname")).sendKeys(firstName);
//        driver.findElement(By.id("Lastname")).sendKeys(lastName);
//        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
//        driver.findElement(By.id("Email")).sendKeys(email);
//        driver.findElement(By.id("Select-role")).sendKeys(role);
//
//        driver.findElement(By.id("submit-btn")).click();

        //version 2 - using object of the page we created
//        page.firstName.sendKeys(firstName);
//        page.lastName.sendKeys(lastName);
//        page.phoneNumber.sendKeys(phone);
//        page.email.sendKeys(email);
//        page.role.sendKeys(role);
//        page.userMgtSubmitBtn.click();

        //version 3 - we created a reusable method in page class
        page.newUserForm(firstName,lastName,phone,email,role);

        Assert.assertEquals(page.tempFirstName.getText(),firstName);
        Assert.assertEquals(page.tempLastName.getText(), lastName);
        Assert.assertEquals(page.phoneNumber.getText(), phone);
        Assert.assertEquals(page.email.getText(), email);
        Assert.assertEquals(page.role.getText(), role);
    }

    @Test(testName = "US1010: Staging table view - DB check", dataProvider = "role")
    public void test02(String role){
        //Using Faker to populate fake data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        page.newUserForm(firstName,lastName,phone,email,role);

        //accessing DB page
        page.accessDbBtn.click();

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

    @Test(testName = "US1011: Clear staging table option", dataProvider = "role")
    public void test1011(String role){
        //Using Faker to populate fake data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        page.newUserForm(firstName,lastName,phone,email,role);

        page.clearBtn.click();

        List<WebElement> dataCount = page.tableOptions;
        Assert.assertEquals(dataCount.size(), 0);
    }

    @Test(testName = "US1012: Adding a new user to DB",dataProvider = "role")
    public void test1012(String role){
        //Using Faker to populate fake data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        page.newUserForm(firstName,lastName,phone,email,role);

        page.submitTableBtn.click();

        page.accessDbBtn.click();

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
