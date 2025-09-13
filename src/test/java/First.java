import Utilities.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.time.Duration;

public class First extends BaseTest {

    //@Test //============ SendKeys ClipBoard LandScape navigations ===================
    public void wifiSettingsName() throws MalformedURLException, InterruptedException {
        /*
        //tagName[@attribute='value']
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();

        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();

        driver.findElement(By.id("android:id/checkbox")).click();

        //LandScape Mode
        DeviceRotation landscape = new DeviceRotation(0,0,90);
        driver.rotate(landscape);

        //click on wifi Check
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)")).click();

        //ClipBoard Testing sending keys from Clipboard
        driver.setClipboardText("Mohaha");
        driver.findElement(By.id("android:id/edit"))
                .sendKeys(driver.getClipboardText());
        driver.findElement(By.id("android:id/button1")).click();

        //navigating
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

         */

    }

    //@Test //============ gestures LongCick ===================
    public void gesturesLongCick(){

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement names = driver.findElement(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"People Names\")"));

        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)names).getId()));

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sample menu\")")).isDisplayed();
    }

    //@Test //============ gestures scroll ===================
    public void gesturesScroll(){

        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"WebView\"));"));

        driver.findElement(AppiumBy.accessibilityId("WebView")).click();


    }

    //@Test //============ gestures Swipe ===================
    public void gesturesSwipe(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        WebElement image = driver.findElement(AppiumBy
                .androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)"));

        //1- Asserting it's in focus
        Assert.assertEquals(image.getAttribute("focusable"),"true");

        //2- swiping
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
            "elementId",((RemoteWebElement)image).getId() ,
            "direction", "left",
            "percent", 0.21
        ));

        //3- asserting it's out of focus
        Assert.assertEquals(image.getAttribute("focusable"),"false");
    }

    //@Test //============ gestures Drag&Drop ===================
    public void gesturesDragDrop(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        //1- select the draggable element
        WebElement ball = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        //2- Dragging
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ball).getId(),
                "endX", 619,
                "endY", 560
        ));
        //3-Assert Text
        String text = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(text,"Dropped!");
    }

    //@Test //============ Assignment =============================
    public void Assignment(){
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
        //===========================
        driver.findElement(AppiumBy.accessibilityId("Text Entry dialog")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));

        driver.findElement(By.id("io.appium.android.apis:id/username_edit")).sendKeys("user");
        driver.findElement(By.id("io.appium.android.apis:id/password_edit")).sendKeys("pass");
        String msg = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(msg , "Text Entry dialog");
        driver.findElement(By.id("android:id/button1")).click();
        //1- "OK Cancel dialog with a message"
        //2- "OK Cancel dialog with a long message"
        //3- "OK Cancel dialog with ultra long message"

        //4- "List dialog"
                //4.1 "new UiSelector().text("Command two")"
                //4.2 "android:id/message" => "You selected: 1 , Command two"

        //5- "Progress dialog"
        //6- "Single choice list"
        //7- Repeat alarm
        //8- Send Call to VoiceMail
        //9- Text Entry dialog //android:id/alertTitle //android:id/button1
        // io.appium.android.apis:id/username_edit
        //io.appium.android.apis:id/password_edit

    }

    //@Test //============ landScape mode ===================
    public void landScape(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        DeviceRotation landscape = new DeviceRotation(0,0,90);
        driver.rotate(landscape);

        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
    }
}


