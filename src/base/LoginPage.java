package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.ID, using = "Email")
    public WebElement txt_Username;

    @FindBy(how = How.ID, using = "Password")
    public WebElement txt_Password;

    @FindBy(how = How.CLASS_NAME, using = "btnForm")
    public WebElement btn_LoginButton;

    @FindBy(how = How.CLASS_NAME, using = "lni-power-switch")
    public WebElement btn_LogoutBtn;


    public void loginAction(String username, String password) {
        txt_Username.sendKeys(username);
        txt_Password.sendKeys(password);
        btn_LoginButton.click();
    }
}