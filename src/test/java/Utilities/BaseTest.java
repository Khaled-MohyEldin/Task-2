package Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest extends Utilities{
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeMethod
    public void initialization() throws MalformedURLException {
        /* if you want to start appium server automatically uncomment this block
        //code to start the server
        if (service == null || !service.isRunning()) {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C:/Users/Admin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                    .withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();

            System.out.println("✅ Appium server started on port 4723");
        } else {
            System.out.println("⚠️ Appium server is already running");
        }
         */

        // create object from AndroidDriver , IODriver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Khaled2");
        options.setApp(System.getProperty("user.dir") + "\\Store.apk");
        options.setOrientation(ScreenOrientation.valueOf("PORTRAIT"));
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
//        service.stop();
    }

}

