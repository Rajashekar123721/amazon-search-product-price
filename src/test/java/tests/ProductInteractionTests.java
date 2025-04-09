package tests;

import org.testng.annotations.Test;

public class ProductInteractionTests extends BaseTest {

    @Test(groups = { "product" })
    public void clickFirstProduct() {
        demoTest.clickFirstProduct();
        System.out.println("First product clicked");
    }
}

