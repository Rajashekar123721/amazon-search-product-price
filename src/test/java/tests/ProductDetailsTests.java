package tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ProductDetailsTests extends BaseTest {

    @Test(groups = { "product-details" })
    public void verify_brand() {

		String expectedResult = "Apple";
		String actualResult = demoTest.getBrand();

		Reporter.log("Expected Result = " + expectedResult);
		Reporter.log("Actual Result = " + actualResult);

		assertTrue(actualResult.equals(expectedResult), "Mismatch in the brand,");

	}

    @Test(groups = { "product-details" })
    public void verify_OS() {

		String actualResult = demoTest.getOperatingSystem();
	    
	    Assert.assertNotNull(actualResult, "iOS is not found!");
	    Assert.assertFalse(actualResult.isEmpty(), "iOS is empty!");

	    System.out.println("Detected iOS: " + actualResult);
	    Reporter.log("Detected iOS = " + actualResult);

	    // Optional: just validate it contains "iOS"
	    assertTrue(actualResult.contains("iOS"), "iOS does not contain 'iOS'");
	}
    

    @Test(groups = { "product-details" })
    public void verify_ram_memory() {

		String expectedResult = "128 GB";
		String actualResult = demoTest.getRAM_Memory();

		Reporter.log("Expected Result = " + expectedResult);
		Reporter.log("Actual Result = " + actualResult);

		assertTrue(actualResult.equals(expectedResult), "Mismatch in the ram memory,");

	}


    @Test(groups = { "product-details" })
    public void verify_memory_capacity() {

		String expectedResult = "128 GB";
		String actualResult = demoTest.getMemory_Capacity();

		Reporter.log("Expected Result = " + expectedResult);
		Reporter.log("Actual Result = " + actualResult);

		assertTrue(actualResult.equals(expectedResult), "Mismatch in the memory capacity,");

	}

    @Test(groups = { "product-details" })
    public void verify_screen_size() {

		String expectedResult = "6.1 Inches";
		String actualResult = demoTest.getScreens_Size();

		Reporter.log("Expected Result = " + expectedResult);
		Reporter.log("Actual Result = " + actualResult);

		assertTrue(actualResult.equals(expectedResult), "Mismatch in the screensize,");

	}

    @Test(groups = { "product-details" })
    public void product_price() {
    	
	    String price = demoTest.getProductPrice();
	    Assert.assertNotNull(price, "Price is not displayed or found!");

	    price = price.trim();
	    System.out.println("Product Price: ₹" + price);
	    Reporter.log("Product Price = ₹" + price);

	    assertTrue(!price.isEmpty(), "Price is empty!");

	    // Optional: parse and validate numeric price
	    try {
	        int numericPrice = Integer.parseInt(price.replace(",", "").replace("₹", "").trim());
	        assertTrue(numericPrice > 1000, "Price seems too low.");
	    } catch (NumberFormatException e) {
	        assertTrue(false, "Price format is not numeric.");
	    }
	}
}

