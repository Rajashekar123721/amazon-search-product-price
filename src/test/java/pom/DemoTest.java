package pom;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class DemoTest {

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement productName;

	@FindBy(id = "nav-search-submit-button")
	private WebElement searchButton;

	@FindBy(xpath = "//span[contains(text(),'results')]")
	private WebElement totalResultsText;

	@FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
	private WebElement totalProductText;

	@FindBy(xpath = "//div[@data-component-type='s-search-result']")
	private List<WebElement> productList;

	@FindBy(xpath = "//span[@class='a-price-whole']")
	private List<WebElement> productPrices;

	@FindBy(xpath = "//span[@class='a-price-symbol']")
	private List<WebElement> currencySymbol;

	@FindBy(xpath = "//span[@id='productTitle']")
	private WebElement productTitle;

	@FindBy(xpath = "//span[@class='a-size-base po-break-word']")
	private List<WebElement> productDetails;

	public DemoTest(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this); // Initializes all the @FindBy annotated WebElements in the current
												// class.
	}

	public void searchProduct(String product) {
		productName.sendKeys(product);
		searchButton.click();
	}

	public String resultDisplay1() {
		return totalResultsText.getText();
	}

	public String resultDisplay2() {
		return totalProductText.getText();
	}

	public int getProductCount() {

		wait.until(ExpectedConditions.visibilityOfAllElements(productList)); // Ensure products are visible
		return productList.size();
	}

	public void clickFirstProduct() {
		String parentWindow = driver.getWindowHandle();
		List<WebElement> productList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']")));
		if (!productList.isEmpty()) {
			productList.get(0).click();
		}

		// Switch to new window
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

	}

	public String getProductPrice() {
		try {
			if (productPrices == null || productPrices.isEmpty()) {
				return "No price elements found!";
			}

			WebElement lastPriceElement = productPrices.get(productPrices.size() - 1);

			wait.until(ExpectedConditions.visibilityOf(lastPriceElement)); // Ensure it's visible

			String priceText = lastPriceElement.getText().trim();
			return priceText.isEmpty() ? "Last price is empty!" : priceText;

		} catch (TimeoutException e) {
			return "Timed out waiting for price element!";
		}
	}

	public String getCurrencySymbol() {
		if (!currencySymbol.isEmpty()) {
			return currencySymbol.get(4).getText();
		} else {
			return "Currency Not Found!";
		}
	}


	public String getBrand() {
		if (!productDetails.isEmpty()) {
			WebElement firstDetail = productDetails.get(0);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstDetail);

			return firstDetail.getText().trim();
		} else {
			return "No elements found!";
		}
	}

	public String getOperatingSystem() {
		if (!productDetails.isEmpty()) {
			WebElement secondDetail = productDetails.get(1);
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondDetail);

			return secondDetail.getText().trim();
		} else {
			return "No elements found!";
		}
	}

	public String getRAM_Memory() {

		
		 int count = 0;

		    for (WebElement detail : productDetails) {
		        String text = detail.getText().trim().toLowerCase();

		        // Match common storage patterns like '128 gb', '1 tb', etc.
		        if (text.matches(".*\\d+\\s?(gb|tb).*")) {
		            count++;
		            if (count == 1) {
		               // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detail);
		                return detail.getText(); // return original (non-lowercased) text
		            }
		        }
		    }

		    return "RAM Memory Installed Size not found!";
	}



	public String getMemory_Capacity() {
	    int count = 0;

	    for (WebElement detail : productDetails) {
	        String text = detail.getText().trim().toLowerCase();

	        // Match common storage patterns like '128 gb', '1 tb', etc.
	        if (text.matches(".*\\d+\\s?(gb|tb).*")) {
	            count++;
	            if (count == 2) {
	                //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detail);
	                return detail.getText(); // return original (non-lowercased) text
	            }
	        }
	    }

	    return "Memory Storage Capacity not found!";
	}


	public String getScreens_Size() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    for (WebElement detail : productDetails) {
	    	
	    	 wait.until(ExpectedConditions.visibilityOf(detail)); // Wait until element is visible
	        String text = detail.getText().trim().toLowerCase();

	        if ((text.contains("inch") || text.contains("inches"))) {
	            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detail);
	            return detail.getText();
	        }
	    }
	    return "Screen size not found!";
	}
}
