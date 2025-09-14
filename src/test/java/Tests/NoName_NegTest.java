package Tests;

import Pages.LandingPage;
import Utilities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NoName_NegTest extends BaseTest {
    @Test
    public void toastMSG_Negative(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.waitToLoad(); //wait until page loads

        //Testing not entering name and trying to shop
        String toastMsg = landingPage.goShop().get("name").toString();
        Assert.assertEquals(toastMsg,"Please enter your name");
    }
}
