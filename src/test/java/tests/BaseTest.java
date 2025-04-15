package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import pom.DemoTest;
import utils.Common;

import java.time.Duration;

public class BaseTest {
    
	protected static WebDriver driver;
    private Common common;
    protected DemoTest demoTest;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser", "amazonUrl"})
       public void setup(String browser, String url) {
        common = new Common();
        common.setupBrowser(browser, url);
        driver = common.getBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
      
    }

    
    @BeforeMethod(alwaysRun = true)
    public void initDemoTest() {
        demoTest = new DemoTest(driver);  // ✅ Ensures it's always ready
    }
    
    @AfterSuite
    public void teardown() {
        common.quitBrowser();
    }
}
