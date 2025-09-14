package Utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilities {
    public WebDriverWait wait;

    public void waitToAppear(AppiumDriver driver, By loading){
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loading));
    }

    public void scrollToElement(AppiumDriver driver, String targetText){
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\""+targetText+"\"));"));

    }

    public void longPress(AppiumDriver driver, WebElement element){
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)element).getId()));
    }

    public BigDecimal getPrice(String total){
        String total2 = total.replace("$","").trim();

        return new BigDecimal(total2) // 2 decimal places
                .setScale(2, RoundingMode.HALF_UP);
    }

    public boolean scroll(AppiumDriver driver, boolean endOfList, int productHeight){
        Dimension screen = driver.manage().window().getSize();
        int screenHeight = screen.getHeight();
        int screenWidth = screen.getWidth();
        int startY = screenHeight / 2;
        int endY = startY - productHeight;
        int centerX = screenWidth / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Move “finger” to the starting point (middle of the screen, vertically centered)
        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), centerX, startY));
        // Press finger down (touch)
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // Move finger upwards by one product height
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), centerX, endY));
        // Lift the finger (release touch)
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        try {
            driver.perform(Collections.singletonList(swipe));
        } catch (Exception e) {
            // If swipe fails, assume end of list
            endOfList = true;
            System.out.println("Reached the end of the page.");
        }
        return endOfList;
    }

    public BigDecimal scrollProductsAndAddToCart(AppiumDriver driver, List<String> productsToFind) {
        // Locate first product to calculate height
        WebElement firstProduct = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.LinearLayout\").instance(5)"));
        int productHeight = firstProduct.getSize().getHeight();

        WebElement parent = driver.findElement(By.id("com.androidsample.generalstore:id/rvProductList"));

        BigDecimal sum = BigDecimal.ZERO;
        boolean endOfList = false;
        Set<String> seenProducts = new HashSet<>();

        while (!productsToFind.isEmpty() && !endOfList) {
            // Get all visible products in viewport
            List<WebElement> products = parent.findElements(By.xpath("//android.widget.RelativeLayout"));

            for (WebElement product : products) {
                try {
                    String productName = product.findElement(By.xpath("//android.widget.TextView")).getText();

                    if (productsToFind.contains(productName)) {
                        // Find the price and add to the map
                        sum = sum.add(getPrice(product.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]")).getText()));
                        // Click the add to cart button
                        product.findElement(By.xpath("//android.widget.TextView[@text=\"ADD TO CART\"]")).click();

                        // Remove the product from the list so we don't look for it again
                        productsToFind.remove(productName);
                        break;
                    }

                } catch (Exception e) {
                    // skip if not interactable
                }
            }

            if (!productsToFind.isEmpty()){
                // Perform swipe by exactly one product height
                endOfList = scroll(driver, endOfList, productHeight);
            }

        }
        return sum;
    }

    public List<TestData> readJson () throws IOException {
        File jsonFile = new File(System.getProperty("user.dir") + "\\data.json");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonFile, new TypeReference< List<TestData> >() {});
    }

}
