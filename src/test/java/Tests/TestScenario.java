package Tests;

import Pages.LandingPage;
import Pages.SettingPage;
import Utilities.DriverFactory;
import Utilities.PropertyReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

@Listeners(Utilities.TestListener.class)
public class TestScenario{
    protected AndroidDriver driver;
    public AppiumDriverLocalService service;
    LandingPage landingPage;
    SettingPage settingPage;

    @Step("Full Scenario first setup then change setting then Validate Changes")
    @Test
    public void changeSettingNValidate(){
        landingPage = new LandingPage(driver);

        landingPage.finishSetupNavigate(); // first open setup
        landingPage.goToSettings();

        settingPage = new SettingPage(driver);
        //Choosing Celsius and 12Hours Settings
        settingPage.chooseCelesiusTemp()
                .choose12HourTime()
                .submit();

        //1. Validating Temperature unit is as we set in settings (Celsius)
        String currTempUnit = landingPage.getCurrentTempUnit();
        Assert.assertTrue(currTempUnit.equalsIgnoreCase("°C"));

        //2. Validating Time Formate is as we set in settings (12 Hours)
        String currTimeFormate = landingPage.getCurrentTimeFormate();
        Assert.assertTrue(currTimeFormate.contains("pm") || currTimeFormate.contains("am"));

        //Validating all Weather Items are visible within next 5 hours (that's in viewport)
        landingPage.checkWeatherItemsVisible();

    }

    @BeforeClass
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
        options.setApp( System.getProperty("user.dir") + "\\Weather.apk");
//        options.setAppPackage("com.info.weather.forecast");
//        options.setAppActivity("com.info.weather.forecast.activity.SettingUnitActivity");
//        options.setNoReset(true);
        options.setNewCommandTimeout(Duration.ofSeconds(300));
//        options.setOrientation(ScreenOrientation.valueOf("PORTRAIT"));
//        options.setDeviceName( PropertyReader.get("AndroidDeviceName"));
        options.setDeviceName("Khaled2");
        driver = new AndroidDriver(service.getUrl(), options);

//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        DriverFactory.setDriver(driver);
    }

    @AfterMethod
    public void tearDown(){
        AndroidDriver driver = DriverFactory.getDriver();
        driver.quit();
    }

    @AfterClass
    public void stopServer(){ service.stop();}
}
