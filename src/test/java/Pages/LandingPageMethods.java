package Pages;

import Map.LandingPageMap;
import Utils.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LandingPageMethods {
    CommonMethods commonMethods;
    LandingPageMap landingPageMap = new LandingPageMap();
    public LandingPageMethods(WebDriver driver){
        commonMethods= new CommonMethods(driver);
    }
    public void hideAndShowValidation(){
        Assert.assertTrue(commonMethods.isElementDisplayed(landingPageMap.showHideInput));
        commonMethods.clickElement(landingPageMap.hideButton);
        Assert.assertFalse(commonMethods.isElementDisplayed(landingPageMap.showHideInput));
        commonMethods.clickElement(landingPageMap.showButton);
        Assert.assertTrue(commonMethods.isElementDisplayed(landingPageMap.showHideInput));
    }




}
