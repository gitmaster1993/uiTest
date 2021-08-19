package Support;


import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SeleniumController {

  private WebDriver driver;

  public SeleniumController() throws Exception
  {
    if (System.getProperty("remote") != null) {
      remote();
    } else {
      init();
    }
  }

  public WebDriver getDriver()
  {
    return driver;
  }

  public void remote() throws MalformedURLException
  {
    if (driver != null) {
      return;
    }

    DesiredCapabilities capability = DesiredCapabilities.chrome();
    driver = new RemoteWebDriver(new URL("http://52.18.223.25:8080/wd/hub"), capability);

    // driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), TimeUnit.SECONDS);
  }

  public void init() {
    if (driver != null) {
      return;
    }
    DesiredCapabilities dc = new DesiredCapabilities();
    LoggingPreferences logs = new LoggingPreferences();
    logs.enable( LogType.BROWSER, Level.ALL);
    dc.setCapability(CapabilityType.LOGGING_PREFS, logs);

    switch(ExecutionUtil.getExecutionBrowser()){
      case IE:
        InternetExplorerDriverManager.getInstance().setup();
        dc.setCapability("requireWindowFocus", true);
        driver = new InternetExplorerDriver(dc);
        driver.manage().window().maximize();
        break;
      case EDGE:
        //EdgeDriverManager.getInstance().setup();
        driver = new EdgeDriver(dc);
        driver.manage().window().maximize();
        break;
      case CHROME:
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver(dc);
        driver.manage().window().maximize();
        break;
      case FIREFOX:
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver(dc);
        //driver.manage().window().maximize();
        //Enable the row above again when Firefox version 55 is released
        break;
      case SAFARI:
        driver = new SafariDriver(dc);
        driver.manage().window().maximize();
        break;
      case CHROME_MOBILE_EMULATED:
        ChromeDriverManager.getInstance().setup();
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Google Nexus 5");
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(dc);
        break;
      case CHROME_ANDROID:
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidPackage", "com.android.chrome");
        dc = new DesiredCapabilities();
        dc.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(dc);
        break;
      case PHANTOMJS:
        PhantomJsDriverManager.getInstance().setup();
        dc = new DesiredCapabilities();
        driver = new PhantomJSDriver(dc);
        break;
    }
    driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), TimeUnit.SECONDS);
  }

  public void cleanup() {
    if (driver != null) {
      driver.manage().deleteAllCookies();
      driver.quit();
      driver = null;
    }
  }
}
