package testcase;

import base.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.Constants;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseTestCase {
    protected WebDriver driver;
    protected LoginPage lp;

    @Parameters({ "browser" })
    @BeforeMethod
    public void setUp(String browser) {
        System.setProperty("webdriver.chrome.driver", Constants.WorkingDir + "\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", Constants.WorkingDir + "\\drivers\\IEDriverServer.exe");
    	System.setProperty("webdriver.gecko.driver", Constants.WorkingDir + "\\drivers\\geckodriver.exe");
        if (browser.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(Constants.URL);
        lp = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Calendar cal = Calendar.getInstance();
                String timeStamp = dateFormat.format(cal.getTime());
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile,
                        new File(Constants.WorkingDir + "\\Screenshots\\" + result.getName() + timeStamp + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
