package Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class BaseTest extends Utilities{
    protected AndroidDriver driver;
    public AppiumDriverLocalService service;

    //@BeforeClass
    public void serverInitialization() throws FileNotFoundException {

        String configDriver = PropertyReader.get("driver");
        int port  = Integer.parseInt(PropertyReader.get("port"));
        //if u getting this from mvn terminal then ok if not then from our preperty file
        String ipAddress = System.getProperty("ipAddress") !=null ?
                System.getProperty("ipAddress") : PropertyReader.get("ipAddress");

        //code to start the server
        if (service == null || !service.isRunning()) {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C:/Users/Admin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
//                    .withAppiumJS(new File(System.getenv("APPIUM_PATH")))
                    .withIPAddress(ipAddress).usingPort(port)
                    .withArgument(GeneralServerFlag.USE_DRIVERS, configDriver)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                    .withLogFile(new File("logs/appium.log"))
                    .build();

            service.start();

            System.out.println("Appium server started on port 4723");
        } else { System.out.println("Appium server is already running");}
    }

    @BeforeMethod
    public void driverInitialization() throws IOException {
        // create object from AndroidDriver , IODriver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp( System.getProperty("user.dir") + "\\Store.apk");
        options.setOrientation(ScreenOrientation.valueOf("PORTRAIT"));

//        options.setDeviceName( PropertyReader.get("AndroidDeviceName"));
//        driver = new AndroidDriver(service.getUrl(), options);

        options.setDeviceName("Khaled2");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        DriverFactory.setDriver(driver);
    }

    @AfterMethod
    public void tearDown(){
        AndroidDriver driver = DriverFactory.getDriver();
        driver.quit();
    }

    //@AfterClass
    public void stopServer(){ service.stop();}

}

