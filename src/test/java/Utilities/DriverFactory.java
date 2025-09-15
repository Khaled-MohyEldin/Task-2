package Utilities;

import io.appium.java_client.AppiumDriver;

public class DriverFactory {
    // Use a ThreadLocal to make the driver instance thread-safe
    private static ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driverThread.get();
    }

    public static void setDriver(AppiumDriver driver) {
        driverThread.set(driver);
    }
}
