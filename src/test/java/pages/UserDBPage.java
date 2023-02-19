package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserDBPage extends BasePage {
    public UserDBPage(WebDriver driver) { PageFactory.initElements(driver,this); }

    @FindBy(id="Firstname")
    public WebElement firstName;

    @FindBy(id="Lastname")
    public WebElement lastName;

    @FindBy(id="Phonenumber")
    public WebElement phoneNumber;

    @FindBy(id="Email")
    public WebElement email;

    @FindBy(id="Select-role")
    public WebElement role;

    @FindBy(id="submit-btn")
    public WebElement userMgtSubmitBtn;

    @FindBy(id="submit-table-btn")
    public WebElement submitTableBtn;

    @FindBy(id="access-db-btn")
    public WebElement accessDbBtn;


    public void newUserForm2(String fName, String lName, String phone, String emailInput, String roleInput){
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        phoneNumber.sendKeys(phone);
        email.sendKeys(emailInput);
        role.sendKeys(roleInput);

        userMgtSubmitBtn.click();
    }
}
