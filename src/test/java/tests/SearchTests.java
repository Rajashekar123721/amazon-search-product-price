package tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {

	@Parameters({ "productName" })
    @Test(groups = { "search" })
    public void productSearch(String product) {
        demoTest.searchProduct(product);
        System.out.println("Product search completed");
    }


	
	@Test(groups = { "search" },dependsOnMethods = "productSearch")
	public void get_Count() {
		System.out.println("iPhone list is displayed");

		String actualResult = demoTest.resultDisplay1() + " " + demoTest.resultDisplay2();
		System.out.println("Actual Result: " + actualResult);
		Reporter.log("Actual Result = " + actualResult);

		try {
			// This splits the string at " of " and takes the first part (e.g., "1-16").
			String range = actualResult.split(" of ")[0].trim();

/* Gets the second part after " of " → "528 results for \"iphone 16\""
   Splits it at " results" → gets just the number part → "528" or "over 8,000"
   Cleans it:
   Removes "over" if present
   Removes commas (so "8,000" becomes "8000")
   Trims spaces  */
			String countText = actualResult.split(" of ")[1].split(" results")[0].replace("over", "").replace(",", "")
					.trim();
			
			
			//string to an integer → for example: "8000" → 8000.
			int totalCount = Integer.parseInt(countText);

			System.out.println("Range shown: " + range);
			System.out.println("Total iPhones listed: " + totalCount);

			// Simple format check
			assertTrue(actualResult.contains("results for \"iphone 16\""), "Expected text not found.");

		} catch (Exception e) {
			assertTrue(false, "Error parsing result string.");
		}
	}

}

