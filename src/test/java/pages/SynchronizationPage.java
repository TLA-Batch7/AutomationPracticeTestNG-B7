package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SynchronizationPage extends BasePage {
    public SynchronizationPage(WebDriver driver){PageFactory.initElements(driver,this);}

    @FindBy(xpath="//button[@class='btn btn-warning']")
    public WebElement alertBtn;
}
