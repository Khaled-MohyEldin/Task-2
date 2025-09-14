package Pages;

import Utilities.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;

public class ProductPage extends BaseTest {

    private AndroidDriver driver;
    public ProductPage(AndroidDriver driver) {
        this.driver = driver;
    }

    //by Locators
    private By loading = By.xpath("//android.widget.LinearLayout");
    private By cartBtn = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
    private By firstProductLoc = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\").instance(5)");
    private By parentLoc = By.id("com.androidsample.generalstore:id/rvProductList");
    private By productsLoc = By.xpath("//android.widget.RelativeLayout");
    private By productLoc = By.xpath("//android.widget.TextView");
    private By priceLoc = By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]");
    private By addBtn = By.xpath("//android.widget.TextView[@text=\"ADD TO CART\"]");

    //Interactions
    public void waitToLoad(){
        waitToAppear(driver, loading);
    }

    public CartPage gotoCart(){
        driver.findElement(cartBtn).click();
        return new CartPage(driver);
    }

    public BigDecimal addToCart(List<String> productsToFind){
        // Locate first product to calculate height
        WebElement firstProduct = driver.findElement(firstProductLoc);
        int productHeight = firstProduct.getSize().getHeight();

        BigDecimal sum = BigDecimal.ZERO;
        boolean endOfList = false;

        WebElement parent = driver.findElement(parentLoc);

        while (!productsToFind.isEmpty() && !endOfList) {
            // Get all visible products in viewport
            List<WebElement> products = parent.findElements(productsLoc);

            for (WebElement product : products) {
                try {
                    String productName = product.findElement(productLoc).getText();
                    if (productsToFind.contains(productName)) {

                        // Find the price and add to the map
                        sum = sum.add(getPrice(product.findElement(priceLoc).getText()));
                        // Click the add to cart button
                        product.findElement(addBtn).click();
                        // Remove the product from the list so we don't look for it again
                        boolean wasRemoved = productsToFind.remove(productName);
                        break;
                    }

                } catch (Exception e) {
                    // skip if not interactable
                }
            }
            if (!productsToFind.isEmpty()){
                // Perform swipe by exactly one product height
                endOfList = scroll(driver, endOfList,productHeight);
            }

        }
        return sum;
    }

}

