package tests;

import com.github.javafaker.Faker;
import data.pojo.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UserDBPage;
import pages.UserMgtPage;
import utils.BrowserUtils;

import java.util.List;

public class UserDBTest extends BaseTest {

    private UserDBPage page;
    private UserMgtPage userMgtPage;

    @DataProvider(name = "role")
    public Object[] data() {
        return new String[]{"Mentor"}; //, "Student","Instructor"
    }

    @BeforeMethod
    public void setUp() {
        page = new UserDBPage(driver);
        userMgtPage = new UserMgtPage(driver);
        driver.get("http://automation.techleadacademy.io/#/usermgt");
    }

    @Test(testName = "US2003 - New user's password format", description = "User’s default password in database should be formatted as 'firstName.lastName$'", dataProvider = "role")
    public void test01(String role) {
        String firstName = "Sally";
        String lastName = "White";
        String phone = "123456789";
        String email = "sallyw@email.com";

        userMgtPage.fillNewUserForm(firstName, lastName, phone, email, role);
        String newPassFormat = firstName + "." + lastName + "$";

        //submitting new user
        userMgtPage.submitTableBtn.click();

        //access DB page
        userMgtPage.accessDbBtn.click();

        BrowserUtils.switchToNewWindow(driver);

        List<WebElement> passwords = driver.findElements(By.xpath("//td[6]"));
        int lastIndex = passwords.size() - 1;
        Assert.assertEquals(passwords.get(lastIndex).getText(), newPassFormat.toLowerCase());
    }

    @Test(testName = "US2004 - New user's password format using POJO", description = "User’s default password in database should be formatted as 'firstName.lastName$'", dataProvider = "role")
    public void test2004(String role) {
        Faker faker = new Faker();
        User user = new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(),
                role
        );
        String newPassFormat = user.getFirstName() + "." + user.getLastName() + "$";

        userMgtPage.addNewUserAndSwitchToUserDBPage(user);

        List<WebElement> passwords = driver.findElements(By.xpath("//td[6]"));
        int lastIndex = passwords.size() - 1;
        Assert.assertEquals(passwords.get(lastIndex).getText(), newPassFormat.toLowerCase());
    }

    @Test(testName = "US2004 - Delete option for new user", dataProvider = "role")
    public void test2004c(String role) {
        Faker faker = new Faker();
        User user = new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(),
                role
        );

        userMgtPage.addNewUserAndSwitchToUserDBPage(user);

        Assert.assertTrue(page.lastUserDeleteBtn.isDisplayed());
    }
}

