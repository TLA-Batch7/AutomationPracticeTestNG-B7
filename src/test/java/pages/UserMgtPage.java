package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserMgtPage extends BasePage{
    public UserMgtPage(WebDriver driver){PageFactory.initElements(driver,this);}

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


}
