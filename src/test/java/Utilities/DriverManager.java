package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static AndroidDriver driver;

    private DriverManager() {
        // private constructor prevents direct instantiation
    }

    public static AndroidDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setApp(System.getProperty("user.dir") + "\\Store.apk");
            options.setOrientation(ScreenOrientation.valueOf("PORTRAIT"));
            options.setDeviceName("Khaled2");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
