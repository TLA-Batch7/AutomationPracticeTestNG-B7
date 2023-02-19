package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UserDBPage;
import utils.BrowserUtils;

import java.util.List;

public class UserDBTest extends BaseTest{

    UserDBPage page;

    @DataProvider(name="role")
    public Object[] data(){
        return new String[] {"Mentor", "Student","Instructor"};
    }

    @BeforeMethod
    public void setUp(){
        page = new UserDBPage(driver);
        driver.get("http://automation.techleadacademy.io/#/usermgt");
    }

    @Test(testName = "US2003 - New user's password format", description = "User’s default password in database should be formatted as 'firstName.lastName$'", dataProvider = "role")
    public void test01(String role){
        String firstName = "Sally";
        String lastName = "White";
        String phone = "123456789";
        String email = "sallyw@email.com";

        page.newUserForm2(firstName,lastName,phone,email,role);
        String newPassFormat = firstName + "." + lastName + "$";

        //submitting new user
        page.submitTableBtn.click();

        //access DB page
        page.accessDbBtn.click();

        BrowserUtils.switchToNewWindow(driver);

        List<WebElement> passwords = driver.findElements(By.xpath("//td[6]"));
        int lastIndex = passwords.size()-1;
            Assert.assertEquals(passwords.get(lastIndex).getText(), newPassFormat.toLowerCase());
        }
    }

