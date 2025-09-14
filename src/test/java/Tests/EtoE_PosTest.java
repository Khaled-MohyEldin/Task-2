package Tests;

import Pages.CartPage;
import Pages.LandingPage;
import Pages.ProductPage;
import Utilities.BaseTest;
import Utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EtoE_PosTest extends BaseTest {

    @Test (dataProvider = "data2")
    public void EndtoEnd_Positive(TestData data){
        String name = data.getName();
        String country = data.getCountry();
        List<String> productsToFind = data.getProducts();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.waitToLoad(); //wait until page loads

        landingPage.selectCountry(country);
        ProductPage products = (ProductPage) landingPage.enterName(name)
                                                        .goShop().get("page");
        products.waitToLoad(); //wait until page loads

        //Adding Items to cart and returning sum of their Prices
        BigDecimal total1 = products.addToCart(productsToFind);
        CartPage cartPage = products.gotoCart();

        cartPage.waitToLoad(); //wait until page loads

        //1- Asserting all elements are there
        Assert.assertTrue(cartPage.checkAll(productsToFind).isEmpty());

        //2- Asserting total price
        BigDecimal total2 = cartPage.getTotal();
        Assert.assertEquals(total1, total2);

        //4- Read Terms & conditions and close them
        //5- Check send Mails and proceed btn to load a website
        cartPage.readTerms()
                .checkSendMails()
                .proceed();
    }


    @DataProvider
    public Object[][] data1(){
        return new Object[][] {
                {"Khaled", "Austria", Arrays.asList("Nike Blazer Mid '77", "PG 3")},
                {"Ahmed", "Algeria", Arrays.asList("Nike SFB Jungle", "Air Jordan 4 Retro")}
        };
    }

    @DataProvider //reading from JsonFile Directly to POJO => TestData
    public Object[][] data2() throws IOException {
      List<TestData> data = readJson();
      return new Object[][] { {data.get(0)} , {data.get(1)} };
    }

}