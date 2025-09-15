package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {
    // Use a ThreadLocal to make the driver instance thread-safe
    private static ThreadLocal<AndroidDriver> driverThread = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        return driverThread.get();
    }

    public static void setDriver(AndroidDriver driver) {
        driverThread.set(driver);
    }
}
