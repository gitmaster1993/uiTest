package Support;


import org.openqa.selenium.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SeleniumSupport {

  /**
   * Check if an element is displayed
   * @param driver Webdriver object
   * @param element The element to check for
   * @return true if element is displayed, otherwise false
   */
  public boolean isElementDisplayed(WebDriver driver, WebElement element) {
    return isElementDisplayed(driver, element, 5);
  }

  /**
   * Check if an element is displayed
   * @param driver Webdriver object
   * @param element The element to check for
   * @param timeout How many seconds to wait for the element to be displayed
   * @return true if element is displayed, otherwise false
   */
  public boolean isElementDisplayed(WebDriver driver, WebElement element, int timeout) {
    driver.manage().timeouts().implicitlyWait(timeout, SECONDS);
    boolean elementFound;
    try {
      if(ExecutionUtil.getExecutionBrowser().equals(ExecutionUtil.BROWSER.EDGE) ) {
        String elementVisible = ((JavascriptExecutor) driver).executeScript("return arguments[0].style.visibility = 'visible';", element).toString();
        elementFound = elementVisible.equals("visible") ? true : false;
      } else {
        if (element.isDisplayed()) {
          elementFound = !element.getCssValue("opacity").equals("0");
        } else {
          elementFound = false;
        }
      }
    } catch (NoSuchElementException e) {
      elementFound = false;
    } catch (StaleElementReferenceException e) {
      elementFound = false;
    }
    driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), SECONDS);
    return elementFound;
  }

  /**
   * Clicks an element using javascript. Workaround since regular click
   * doesn't always work in all browsers
   * @param driver Webdriver object
   * @param element The element to scroll to and click
   */
  public void click(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  /**
   * Wait until an element disappears
   * @param driver Webdriver object
   * @param element The element to check for
   * @param timeout How many seconds to wait for the element to disappear
   */
  public void waitForElementToDisappear(WebDriver driver, WebElement element, int timeout) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    while (isElementDisplayed(driver, element, 1)) {
      Thread.sleep(500);
      if (System.currentTimeMillis() > (startTime + timeout*1000)) {
        throw new TimeoutException("Timeout while waiting for element to disappear");
      }
    }
  }

  /**
   * Clear an input field. Workaround method since regular clear doesn't work in
   * Safari and IE for our shape measurement fields
   * @param driver Webdriver object
   * @param element The element to clear
   */
  public void clearElement(WebDriver driver, WebElement element) {
    if (ExecutionUtil.getExecutionBrowser().equals(ExecutionUtil.BROWSER.IE)
      || ExecutionUtil.getExecutionBrowser().equals(ExecutionUtil.BROWSER.SAFARI)) {
      element.sendKeys("\u0003","\u0008","\u0008","\u0008","\u0008","\u0008","\u0008","\u0008","\u0008");
    } else {
      element.clear();
    }
  }

  /**
   * Scrolls down a number of pixels
   * @param driver Webdriver object
   * @param nbrOfPixels Number of pixels to scroll down on the page
   */
  public void scrollDown(WebDriver driver, int nbrOfPixels) {
    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,arguments[0]);", nbrOfPixels);
  }

  /**
   * Gets the current scroll position of the page
   * @param driver Webdriver object
   * @return the current scroll position
   */
  public long getScrollPosition(WebDriver driver) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    Long value = (Long) executor.executeScript("return window.pageYOffset;");
    return value;
  }

  /**
   * Scrolls the element into view
   * @param driver Webdriver object
   * @param element The element to scroll into view
   */
  public void scrollElementIntoView(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }
}
