package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Common {
	private WebDriver driver;

	public void setupBrowser(String browser, String url) {
		switch (browser.toLowerCase()) {
		case "chrome":
			this.driver = new ChromeDriver();
			break;
		case "firefox":
			this.driver = new FirefoxDriver();
			break;
		case "edge":
			this.driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser provided. Exiting...");
			System.exit(0); // If an invalid browser is provided, the program should not continue execution
							// with an undefined driver(Terminate the program)
		}

		driver.manage().window().maximize();
		driver.get(url);
	}

	public WebDriver getBrowser() {
		return this.driver;
	}

	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
