package TestComp;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());
        } catch (Exception e) {throw new RuntimeException(e);}

        attachScreenshot(driver,result.getMethod().getMethodName());
    }
}
