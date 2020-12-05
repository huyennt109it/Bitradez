package testcase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Constants;
import utils.ExcelUtils;

public class Login extends BaseTestCase {
    private ExcelUtils excelUtils;
    @Test(dataProvider = "loginAuthentication")
    public void LoginTest(String testcaseID, String username, String password, String expected) throws Exception {
        lp.loginAction(username,password);
        lp.btn_LoginButton.click();
        try {
            lp.btn_LogoutBtn.click();
            if (expected.equalsIgnoreCase("pass")) {
                excelUtils.setCellData("Pass", excelUtils.getRowContains(testcaseID, Constants.Col_Login_TestCaseID), Constants.Col_Login_Result);
            }else {
                excelUtils.setCellData("Fail", excelUtils.getRowContains(testcaseID, Constants.Col_Login_TestCaseID), Constants.Col_Login_Result);
                Assert.fail("Login successful but expected result is fail");
            }
        } catch (Exception e) {
            if (expected.equalsIgnoreCase("fail")) {
                excelUtils.setCellData("Pass", excelUtils.getRowContains(testcaseID, Constants.Col_Login_TestCaseID), Constants.Col_Login_Result);
            }else {
                excelUtils.setCellData("Fail", excelUtils.getRowContains(testcaseID, Constants.Col_Login_TestCaseID), Constants.Col_Login_Result);
                Assert.fail("Login unsuccessful but expected result is pass");
            }
        }
    }

    @DataProvider
    public Object[][] loginAuthentication() throws Exception {
        excelUtils = new ExcelUtils(Constants.ExcelDataPathLogin, Constants.ExcelDataSheetLogin);
        return excelUtils.getTableArray(4);
    }
}
