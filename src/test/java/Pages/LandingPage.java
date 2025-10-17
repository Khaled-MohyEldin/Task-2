package Pages;

import Utilities.Utilities;
import Utilities.DriverFactory;
import Utilities.Utilities;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

public class LandingPage{
    //Constructor and Driver
    private AndroidDriver driver;

    public LandingPage(AndroidDriver driver) {
        this.driver = driver;
    }

    WebDriverWait wait;
    Utilities util = new Utilities();

    private final By LOADING_MESSAGE_LOCATOR = By.id("android:id/message");
    private final By wethTime = By.id("com.info.weather.forecast:id/tv_hour_item");
    private final By wethRain = By.id("com.info.weather.forecast:id/tv_rain_chance");
    private final By wethHumidity = By.id("com.info.weather.forecast:id/tv_humidity");
    private final By navBar = By.id("com.info.weather.forecast:id/iv_bt_navigation_setting");
    private final By unitSettings = AppiumBy.androidUIAutomator("new UiSelector().text(\"Unit setting\")");

    private final By doneBtn = By.id("com.info.weather.forecast:id/tv_button_done");
    private final By gotItBtn = AppiumBy.androidUIAutomator("new UiSelector().text(\"GOT IT\")");
    private final By permissionBtn = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");

    private final By currTempUnitLoc = By.id("com.info.weather.forecast:id/tv_current_temper_unit");

    @Step("finish setup and load home page")
    public LandingPage finishSetupNavigate(){
        driver.findElement(doneBtn).click();
        util.waitNClick(gotItBtn);
        //wait for popup msg and click on permission
        waitPopUpMsg();
        util.waitNClick( permissionBtn);
        return this;
    }

    @Step("wait for message to go")
    public void waitPopUpMsg() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LOADING_MESSAGE_LOCATOR));
    }


    @Step("Click NavBar and go to settings")
    public void goToSettings(){
        util.waitNClick( navBar);
        driver.findElement( unitSettings).click();
    }


    @Step("Assert Visibility of rain humidity in home page")
    public LandingPage checkWeatherItemsVisible() {
        int i = 0;
        while (i <= 4) {
            try {
                String time = driver.findElements(wethTime).get(i).getAttribute("displayed");
                String rain = driver.findElements(wethRain).get(i).getAttribute("displayed");
                String humidity = driver.findElements(wethHumidity).get(i).getAttribute("displayed");
                System.out.println("Time => " + time + " ||Humidity => " + humidity + " ||Rain => " + rain);
                Assert.assertTrue(time.equalsIgnoreCase("true"));
                Assert.assertTrue(rain.equalsIgnoreCase("true"));
                Assert.assertTrue(humidity.equalsIgnoreCase("true"));
                i++; // only increment if successful
            } catch (Exception e) {
                waitPopUpMsg();
                i = 0; // reset count as page has reloaded
                System.out.println("****** Page Reloaded *********");
            }
        }
        return this;
    }

    @Step("Get Displayed Temperature Unit")
    public String getCurrentTempUnit(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(currTempUnitLoc));
        String currTempUnit = driver.findElement(currTempUnitLoc).getText();
        System.out.println("Temperature Unit => " + currTempUnit);
        return currTempUnit;
    }

    @Step("Get Displayed Time Formate")
    public String getCurrentTimeFormate(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(wethTime));
        String currTimeFormate = driver.findElements(wethTime).get(0).getText();
        System.out.println("Time Formate => " + currTimeFormate);
        return currTimeFormate;
    }

}
