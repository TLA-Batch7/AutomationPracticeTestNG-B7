package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserDBPage extends BasePage {
    private WebDriver driver;
    public UserDBPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath = "//table[@id='list-table']/tbody/tr[last()]//i[text()='Delete']")
    public WebElement lastUserDeleteBtn;


}
