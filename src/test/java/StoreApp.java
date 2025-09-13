import Pages.CartPage;
import Pages.LandingPage;
import Pages.ProductPage;
import Utilities.BaseTest;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StoreApp extends BaseTest {

    @Test
    public void test() throws InterruptedException {

        LandingPage landingPage = new LandingPage(driver);
        landingPage.waitToLoad(); //wait until page loads

        //Testing Toast message
        String toastMsg = landingPage.goShop().get("name").toString();
        Assert.assertEquals(toastMsg,"Please enter your name");

        //landingPage.selectCountry("Austria")
        ProductPage products = (ProductPage) landingPage.enterName("khaled")
                                                        .goShop().get("page");
        products.waitToLoad(); //wait until page loads

        List<String> productsToFind = new ArrayList<>();
        productsToFind.add("Nike Blazer Mid '77");
        productsToFind.add("Air Jordan 4 Retro");
//        productsToFind.add("PG 3");
        productsToFind.add("Nike SFB Jungle");

        //Adding Items to cart and returning sum of their Prices
//        scrollProductsAndAddToCart(driver, productsToFind);
        BigDecimal total1 = products.addToCart(productsToFind);
        CartPage cartPage = products.gotoCart();

        cartPage.waitToLoad(); //wait until page loads

        //1- Asserting all elements are there
        productsToFind = cartPage.checkAll(productsToFind);
        Assert.assertTrue(productsToFind.isEmpty());

        //2- Asserting total price
        BigDecimal total2 = cartPage.getTotal();
        Assert.assertEquals(total1, total2);

        //4- Read Terms & conditions and close them
        //5- Check send Mails and proceed btn to load a website
        cartPage.readTerms()
                .checkSendMails()
                .proceed();

        Thread.sleep(2000);

        //6- ClickBack
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        /*
        System.out.println("before=> " + driver.getContext());
        Set<String> contexts = driver.getContextHandles();//get the context name Correctly as dev name it
        for (String context : contexts) {
            System.out.println("names=> " + context);
            if (context.contains("WEBVIEW")) {
                driver.context(context);
            }
        }
        System.out.println("After=> " + driver.getContext());
         */
    }

}

/*
// I want to automate Adding items in cart in Appium in scrollable page/Activity
// and I have a list of products to choose
1. get list of current visible products
2. check if one of them is in array then if not go to 3
     2.1 add it to cart
     2.2 get its price
     2.3 remove it from array
3. scroll a bit and then
4. repeat 1 & 2 until 2 things happens (Reach Scrolling end or Array is empty)
*/
