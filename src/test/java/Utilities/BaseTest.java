package Utilities;

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
import java.util.Properties;

public class BaseTest extends Utilities{
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeMethod
    public void initialization() throws IOException {
        //Getting config values from .properties files
        Properties prop = new Properties();
        FileInputStream config = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\test\\resources\\GlobalData.properties");
        prop.load(config);
        String ipAddress = prop.getProperty("ipAddress");
        String deviceName = prop.getProperty("AndroidDeviceName");
        String port  = prop.getProperty("port");

        //code to start the server
        if (service == null || !service.isRunning()) {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C:/Users/Admin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                    .withIPAddress(ipAddress).usingPort(Integer.parseInt(port)).build();
            service.start();

            System.out.println("✅ Appium server started on port 4723");
        } else { System.out.println("⚠️ Appium server is already running");}


        // create object from AndroidDriver , IODriver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(System.getProperty("user.dir") + "\\Store.apk");
        options.setOrientation(ScreenOrientation.valueOf("PORTRAIT"));
        driver = new AndroidDriver(service.getUrl(), options);
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
        service.stop();
    }

}

