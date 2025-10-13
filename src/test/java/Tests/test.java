package Tests;

import Pages.LandingPage;
import Pages.SettingPage;
import Utilities.DriverFactory;
import Utilities.Utilities;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class test {
    protected AndroidDriver driver;
//    WebDriverWait wait;
//    Utilities util;
    LandingPage landingPage;
    SettingPage settingPage;

    @Test
    public void changeSettingNValidate(){
        landingPage = new LandingPage(driver);

        landingPage.finishSetupNavigate()
                .goToSettings();

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

        //Validating all Weather Items are visible within 5 times
        landingPage.checkWeatherItemsVisible();

    }

    @BeforeMethod
    public void driverInitialization() throws IOException {
        // create object from AndroidDriver , IODriver
        UiAutomator2Options options = new UiAutomator2Options();
//        options.setApp( System.getProperty("user.dir") + "\\ApiDemos-debug.apk");
        options.setAppPackage("com.info.weather.forecast");
        //com.info.weather.forecast.activity.SettingUnitActivity
//        options.setAppActivity("");
//        options.setOrientation(ScreenOrientation.valueOf("PORTRAIT"));
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


}
