package Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        System.out.println("Test Succeeded: " + testName);
        // Add a step to Allure for documentation of success
        Allure.step("✅ Test passed successfully: " + testName);
    }

    @Override @SneakyThrows
    public void onTestFailure(ITestResult result) {
        //use Thread-Safe driver Factory Pattern instead of passing driver
        AndroidDriver driver = DriverFactory.getDriver();

        String testName = result.getName(); // Get the test method name
        System.out.println("Test Failed: " + testName);

        // Screenshot file name with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        if (driver != null) {
            try { // Saving a screenshot to screenshots Folder
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("screenshots/" + fileName));
                System.out.println("Screenshot saved: screenshots/" + fileName);

                // Attach screenshot to Allure
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshotBytes));
                // Add log to Allure
                Allure.step("Test failed: " + testName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Driver is null, screenshot not captured.");
        }
    }

}
