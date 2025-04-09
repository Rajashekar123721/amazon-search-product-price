package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    @Test(groups = { "smoke" })
    public void urlValidation() {
        String expected = "https://www.amazon.in/";
        String actual = driver.getCurrentUrl();
        Reporter.log("Expected: " + expected);
        Reporter.log("Actual: " + actual);
        Assert.assertEquals(actual, expected, "URL mismatch");
    }

    @Test(groups = { "smoke" })
    public void titleValidation() {
        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actual = driver.getTitle();
        Reporter.log("Expected: " + expected);
        Reporter.log("Actual: " + actual);
        Assert.assertEquals(actual, expected, "Title mismatch");
    }
}

