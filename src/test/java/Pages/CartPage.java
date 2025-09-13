package Pages;

import Utilities.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

public class CartPage extends BaseTest {

    private AndroidDriver driver;
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private By loading = By.id("com.androidsample.generalstore:id/rvCartProductList");
    private By parentLoc = By.id("com.androidsample.generalstore:id/rvCartProductList");
    private By productsLoc = By.xpath("//android.widget.RelativeLayout");
    private By productNameLoc = By.id("com.androidsample.generalstore:id/productName");
    private By priceLoc = By.id("com.androidsample.generalstore:id/totalAmountLbl");
    private By termsBtm = By.id("com.androidsample.generalstore:id/termsButton");
    private By closeTermsBtn = By.id("android:id/button1");
    private By mailCheck = By.className("android.widget.CheckBox");
    private By proceedBtn = By.id("com.androidsample.generalstore:id/btnProceed");

    public void waitToLoad(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loading));
    }

    public List<String> checkAll(List<String> productsToFind){
        WebElement parent = driver.findElement(parentLoc);
        List<WebElement> products = parent.findElements(productsLoc);
        for (WebElement product : products) {
            String name = product.findElement(productNameLoc).getText();
            if (productsToFind.contains(name)) {
                // Remove the product from the list so we don't look for it again
                productsToFind.remove(name); break;
            }
        }
        return productsToFind;
    }

    public BigDecimal getTotal(){
        String totalShown = driver.findElement(priceLoc).getText();
        return getPrice(totalShown);
    }

    public CartPage readTerms(){
        WebElement read = driver.findElement(termsBtm);
        longPress(driver,read );
        driver.findElement(closeTermsBtn).click();
        return this;
    }

    public CartPage checkSendMails(){
        driver.findElement(mailCheck).click();
        return this;
    }

    public void proceed(){
        driver.findElement(proceedBtn).click();
    }

}
