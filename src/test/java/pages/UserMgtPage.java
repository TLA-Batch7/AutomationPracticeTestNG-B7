package pages;

import data.pojo.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.util.List;

public class UserMgtPage extends BasePage{
    private WebDriver driver;

    public UserMgtPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

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

    @FindBy(xpath = "//td[1]")
    public WebElement tempFirstName;

    @FindBy(xpath = "//td[2]")
    public WebElement tempLastName;

    @FindBy(xpath = "//td[3]")
    public WebElement tempPhoneNumber;

    @FindBy(xpath = "//td[4]")
    public WebElement tempEmail;

    @FindBy(xpath = "//td[5]")
    public WebElement tempRole;

    @FindBy(id="access-db-btn")
    public WebElement accessDbBtn;

    @FindBy(id="clear-btn")
    public WebElement clearBtn;

    @FindBy(xpath = "//table[@id='list-table']//td")
    public List<WebElement> tableOptions;

    @FindBy(id="submit-table-btn")
    public WebElement submitTableBtn;

    public void fillNewUserForm(String fName, String lName, String phone, String emailInput, String roleInput){
       firstName.sendKeys(fName);
       lastName.sendKeys(lName);
       phoneNumber.sendKeys(phone);
       email.sendKeys(emailInput);
       role.sendKeys(roleInput);
       userMgtSubmitBtn.click();
    }

    public void addNewUserAndSwitchToUserDBPage(User user){
        fillNewUserForm(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getRole()
        );

        //submitting new user
        submitTableBtn.click();

        //access DB page
        accessDbBtn.click();

        BrowserUtils.switchToNewWindow(driver);
    }

}
