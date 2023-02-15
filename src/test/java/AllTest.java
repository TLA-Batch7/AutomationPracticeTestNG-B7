import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllTest {

    @DataProvider(name="role")
    public Object[] data(){
        return new String[] {"Mentor", "Student","Instructor"};
    }

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(testName="US1010: Staging table view", description= "Verify temp table is getting populated",
            dataProvider = "role")
    public void test01(String role){
        driver.get("http://automation.techleadacademy.io/#/usermgt");

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

        driver.get("http://automation.techleadacademy.io/#/usermgt");

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

    @Test(testName = "US101: Test Header")
    public void test101(){
        driver.get("http://automation.techleadacademy.io/#/");
        Assert.assertEquals(driver.findElement(By.id("title")).getText(), "Automation with Selenium");
    }

    @Test(testName = "US401: Input field under Single Input Field section")
    public void test401(){
        String testData = "hello world";

        driver.get("http://automation.techleadacademy.io/#/inputs");
        driver.findElement(By.id("message")).sendKeys(testData);
        driver.findElement(By.name("button1")).click();

        String actual = driver.findElement(By.name("message1")).getText();
        Assert.assertEquals(actual, testData);
    }

    @Test(testName = "US1011: Clear staging table option")
    public void test1011(){
        driver.get("http://automation.techleadacademy.io/#/usermgt");

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
        driver.get("http://automation.techleadacademy.io/#/usermgt");

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

    @Test(testName = "US1013: Display alert within 10 seconds")
    public void test1013(){
        driver.get("http://automation.techleadacademy.io/#/synchronization");
        driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertTrue(true);
    }
}
