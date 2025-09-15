package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class BaseTest extends Utilities{
    protected AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeMethod
    public void initialization() throws IOException {
        /*
        //Getting config values from .properties files
        Properties prop = new Properties();
        FileInputStream config = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\test\\resources\\GlobalData.properties");
        prop.load(config);

        //if u getting this from mvn terminal then ok if not then from our preperty file
        String ipAddress = System.getProperty("ipAddress") !=null ?
                System.getProperty("ipAddress") : prop.getProperty("ipAddress");

        String deviceName = prop.getProperty("AndroidDeviceName");
        String port  = prop.getProperty("port");

        //code to start the server
        if (service == null || !service.isRunning()) {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C:/Users/Admin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                    .withIPAddress(ipAddress).usingPort(Integer.parseInt(port)).build();
            service.start();

            System.out.println("Appium server started on port 4723");
        } else { System.out.println("Appium server is already running");}

        // create object from AndroidDriver , IODriver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(System.getProperty("user.dir") + "\\Store.apk");
        options.setOrientation(ScreenOrientation.valueOf("PORTRAIT"));
//        options.setDeviceName(deviceName);
//        driver = new AndroidDriver(service.getUrl(), options);
        options.setDeviceName("Khaled2");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//        DriverFactory.setDriver(driver);
         */
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown(){
//        AppiumDriver driver = DriverFactory.getDriver();
//        if (driver != null) {
//            driver.quit();
//        }
//        service.stop();
        DriverManager.quitDriver();
    }

}

