package Pages;

import Utilities.Utilities;
import Utilities.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingPage {

    Utilities util = new Utilities();
    private AndroidDriver driver;
    public SettingPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private final By tempSelect = By.id("com.info.weather.forecast:id/iv_temp_dropdown");
    private final By tempCelUnit = AppiumBy.androidUIAutomator("new UiSelector().text(\"C\")");
    private final By timeSelect = By.id("com.info.weather.forecast:id/tv_timeformat_setting");
    private final By time12Unit = AppiumBy.androidUIAutomator("new UiSelector().text(\"12 Hour\").instance(0)");
    private final By doneBtn = By.id("com.info.weather.forecast:id/tv_button_done");

    @Step("Click on Temperature and choose celsius Formate")
    public SettingPage chooseCelesiusTemp(){
        util.waitNClick(tempSelect);
        util.waitNClick(tempCelUnit);
        return this;
    }

    @Step("Click on Time and choose 12 Hour Formate")
    public SettingPage choose12HourTime(){
        util.waitNClick(timeSelect);
        util.waitNClick(time12Unit);
        return this;
    }

    @Step("Submit Current Settings")
    public void submit(){
        driver.findElement(doneBtn).click();
    }

}
