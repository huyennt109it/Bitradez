package utils;

public class Constants {
    public static final String URL = "http://id.bitradez.test/";
    public static final String WorkingDir = System.getProperty("user.dir");
    public static final String ExcelDataPathLogin = WorkingDir + "\\Data\\Login.xlsx";
    public static final String ExcelDataSheetLogin = "Login";

    public static final int Col_Login_TestCaseID = 0;
    public static final int Col_Login_Username = 1;
    public static final int Col_Login_Password = 2;
    public static final int Col_Login_Expected = 3;
    public static final int Col_Login_Result = 4;
}
