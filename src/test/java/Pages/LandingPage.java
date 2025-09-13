package Pages;
import Utilities.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class LandingPage extends BaseTest{
    //Constructor and Driver
    private AndroidDriver driver;
    public LandingPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private final By Loading = By.id("android:id/text1");
    private final By countryDrop = By.id("android:id/text1");
//    private By country1 = By.xpath("//android.widget.TextView[@text=\"Azerbaijan\"]");
    private final By nameField = By.id("com.androidsample.generalstore:id/nameField");
    private final By goBtn = By.id("com.androidsample.generalstore:id/btnLetsShop");
    private final By toastMSG = By.xpath("(//android.widget.Toast)[1]");


    //Wait to Load
    public void waitToLoad(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Loading));
    }

    // Click the dropDown and select country
    public LandingPage selectCountry(String country){
        By cntryLocator = By.xpath("//android.widget.TextView[@text=\""+country+"\"]");
        driver.findElement(countryDrop).click();
        scrollToElement(driver,country);
        driver.findElement(cntryLocator).click();
        return this;
    }

    //send keys to nameField
    public LandingPage enterName(String keys){
        driver.findElement(nameField).sendKeys(keys);
        return this;
    }

    //click let's go button
    public Map<String,Object> goShop(){
        String name = driver.findElement(nameField).getText();

        driver.findElement(goBtn).click();
        if(name.contains("Enter name here")){
            name =  driver.findElement(toastMSG).getAttribute("name");
        }
        ProductPage products = new ProductPage(driver);
        return Map.of("name", name,
                "page", products );
    }

}
