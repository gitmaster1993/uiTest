package PageObjects;

import Support.ExecutionUtil;
import Support.SeleniumSupport;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;


public class CommonTopPage extends SeleniumSupport {

  private WebDriver driver;

  public CommonTopPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public enum PageMenuButton {
    PLAN("menu-button-plan"),
    STYLE("menu-button-style"),
    SINK("menu-button-sink"),
    CUTOUT("menu-button-cut-outs"),
    KITCHENISLAND("menu-button-kitchen-island");

    private String id;
    PageMenuButton(String id) {
      this.id=id;
    }

    public String getId() {
      return id;
    }
  }

  /* PAGE ELEMENTS */

  WebElement pageNavigation(PageMenuButton page) {
    return driver.findElement(By.id(page.getId()));
  }

  WebElement manualConflictIndicator(PageMenuButton page) {
    String id = page.getId() + "-conflict-badge";
    return driver.findElement(By.id(id));
  }

  WebElement automaticConflictIndicator(PageMenuButton page) {
    String id = page.getId() + "-automatic-badge";
    return driver.findElement(By.id(id));
  }

  @FindBy(id = "fullscreen-loading")
  WebElement loadingScreen;

  @FindBy(id = "screen-saver-overlay")
  WebElement screenSaver;

  @FindBy(id = "idle-timeout-dialog")
  WebElement idleTimeoutDialog;

  @FindBy(id = "idle-timeout-dialog-continue")
  WebElement idleTimeoutDialogContinueBtn;

  @FindBy(id = "idle-timeout-dialog-exit")
  WebElement idleTimeoutDialogExitBtn;

  @FindBy(id = "total-price")
  WebElement price;

  @FindBy(id = "main-header-next-button")
  WebElement itemListBtn;

  @FindBy(id = "footer-menu-next-button")
  WebElement itemListBtn2;

  @FindBy(id = "item-list-header-back-button")
  WebElement itemListBackBtn;

  @FindBy(id = "main-header-back-button")
  WebElement exitBtn;

  @FindBy(id = "exit-dialog-exit")
  WebElement exitDialogExitBtn;

  @FindBy(id = "exit-dialog-save")
  WebElement exitDialogSaveBtn;

  @FindBy(id = "modal-image-close")
  WebElement closeOverlayImageBtn;

  @FindBy(id = "new-image")
  WebElement inspirationPictureUpdatedBtn;

  @FindBy(id = "conflict-dialog")
  WebElement conflictDialog;

  @FindBy(id = "conflict-dialog-continue")
  WebElement conflictDialogContinueBtn;

  @FindBy(id = "conflict-dialog-back")
  WebElement conflictDialogBackBtn;

  @FindBy(id = "conflict-warning")
  WebElement priceConflictWarning;

  @FindBy(id = "price-error-dialog")
  WebElement priceErrorDialog;

  @FindBy(id = "price-error-dismiss")
  WebElement priceErrorDialogCloseBtn;

  @FindBy(id = "modal-unavailable-option-dialog")
  WebElement unavailableOptionsDialog;

  @FindBy(id = "unavailable-option-dismiss")
  WebElement unavailableOptionsDialogDismissBtn;

  @FindBy(id = "price-difference-dialog")
  WebElement newCountryDialog;

  @FindBy(id = "price-difference-dismiss")
  WebElement dismissPriceDifferenceDialog;

  @FindBy(id = "revert-to-last-arrow-back")
  WebElement revertArrow;

  @FindBy(id = "revert-to-last-arrow-forward")
  WebElement forwardArrow;

  @FindBy(id = "revert-to-last-speech-bubble")
  WebElement revertBubble;

  @FindBy(className = "swiper-button-next")
  WebElement nextImage;

  @FindBy(className = "swiper-button-prev")
  WebElement prevImage;

  @FindBy(id = "virtual-keyboard")
  WebElement virtualKeyboard;

  @FindBy(id = "kitchen-island-schematic-schematic-slide")
  WebElement kitchenIslandImage;

  @FindBy(id = "kitchen-island-edge-image-image-slide")
  WebElement kitchenIslandEdgeImage;

  @FindBy(id = "kitchen-island-worktop-image-image-slide")
  WebElement kitchenIslandWorktopImage;

  @FindBy(id = "worktop-image-image-slide")
  WebElement worktopImage;

  @FindBy(id = "edge-image-image-slide")
  WebElement edgeImage;

  @FindBy(id = "overhang-image-slide")
  WebElement overhangImage;

  @FindBy(id = "whats-included-button")
  WebElement whatsIncluded;

  @FindBy(id = "included-in-price-dialog")
  WebElement whatsIncludedDialog;

  @FindBy(id = "included-in-price-dismiss")
  WebElement dismissWhatsIncludedDialog;

  @FindBy(id = "virtual-keyboard-dismiss")
  WebElement dismissVirtualKeyboardBtn;

  @FindBy(id = "virtual-keyboard-next-or-done")
  WebElement nextVirtualKeyboardBtn;

  /* PAGE METHODS */

  public void load(String page, String[] queryParameters) throws InterruptedException {
    String url=ExecutionUtil.getUrl( "se/en/main/" + page.replace("-",""), queryParameters);
    driver.get(url);
    if (ExecutionUtil.getUrl().contains("iframe")) {
      driver.switchTo().frame("cwcalc-application");
    }
    waitForElementToDisappear(driver, loadingScreen, 5);
  }

  public void loadCountry(String page, String language) throws InterruptedException {
    String url;
    if (page.equalsIgnoreCase("start")) {
      url=ExecutionUtil.getUrl(language);
    }
    else {
      url=ExecutionUtil.getUrl( language + "main/" + page.replace("-",""));
    }
    driver.get(url);
    if (ExecutionUtil.getUrl().contains("iframe")) {
      driver.switchTo().frame("cwcalc-application");
    }
    waitForElementToDisappear(driver, loadingScreen, 10);
  }

  public void loadQueryCountry(String[] queryParameter, String language) throws InterruptedException{
    String url=ExecutionUtil.getUrl( language, queryParameter);
    driver.get(url);
    if (ExecutionUtil.getUrl().contains("iframe")) {
      driver.switchTo().frame("cwcalc-application");
    }
    waitForElementToDisappear(driver, loadingScreen, 5);
  }

  public void load() throws InterruptedException {
    driver.get(ExecutionUtil.getUrl("se/en/"));
    if (ExecutionUtil.getUrl().contains("iframe")) {
      driver.switchTo().frame("cwcalc-application");
    }
    waitForElementToDisappear(driver, loadingScreen, 5);
  }

  public void load(String[] queryParameters) throws InterruptedException {
    driver.get(ExecutionUtil.getUrl("se/en/", queryParameters));
    if (ExecutionUtil.getUrl().contains("iframe")) {
      driver.switchTo().frame("cwcalc-application");
    }
    waitForElementToDisappear(driver, loadingScreen, 5);
  }

  public void navigateTo(String page) {
    if (page.equalsIgnoreCase("plan")) {
      click(driver, pageNavigation(PageMenuButton.PLAN));
    } else if (page.equalsIgnoreCase("style")) {
      click(driver, pageNavigation(PageMenuButton.STYLE));
    } else if (page.equalsIgnoreCase("sink")) {
      click(driver, pageNavigation(PageMenuButton.SINK));
    } else if (page.equalsIgnoreCase("cut-outs")) {
      click(driver, pageNavigation(PageMenuButton.CUTOUT));
    } else if (page.equalsIgnoreCase("item-list")) {
      click(driver, itemListBtn);
    } else if (page.equalsIgnoreCase("item-list2")) {
      click(driver, itemListBtn2);
    } else if (page.equalsIgnoreCase("back")) {
      click(driver, itemListBackBtn);
    } else if (page.equalsIgnoreCase("kitchen-island")) {
      click(driver, pageNavigation(PageMenuButton.KITCHENISLAND));
    } else {
      Assert.fail("Unknown page: " + page);
    }
  }

  public String getPrice() {
    int split = price.getText().indexOf(" kr");
    return price.getText().substring(0, split).replace(" ", "");
  }

  public String getItemList() {
    String itemList = "";
    try {
      itemListBtn.click();
      LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
      List<LogEntry> entries = logEntries.getAll();
      itemList = entries.get(entries.size()-1).getMessage();
    } catch (Exception e) {
      itemList = "Item-list can't be fetched in this browser";
    }
    return itemList;
  }

  public void exit() {
    click(driver, exitBtn);
  }

  public boolean isExitDialogDisplayed() {
    return isElementDisplayed(driver, exitDialogExitBtn) && isElementDisplayed(driver,exitDialogSaveBtn);
  }

  public void exitWithoutSaving() {
    click(driver, exitDialogExitBtn);
  }

  public void exitAndSave() {
    click(driver, exitDialogSaveBtn);
  }

  public boolean isOverlayImageDisplayed() {
    return isElementDisplayed(driver, closeOverlayImageBtn);
  }

  public void closeOverlayImage() throws InterruptedException {
    click(driver, closeOverlayImageBtn);
  }

  public boolean isInspirationalPictureUpdated() {
    return isElementDisplayed(driver, inspirationPictureUpdatedBtn);
  }

  public void displayUpdatedInspirationalPicture() {
    click(driver, inspirationPictureUpdatedBtn);
  }

  public boolean isInspirationalPictureDisplayed() {
    return getScrollPosition(driver) == 0 ? true : false;
  }

  public void scrollDown(int pixels) {
    scrollDown(driver, pixels);
  }

  public boolean isConflictDialogDisplayed() {
    return isElementDisplayed(driver, conflictDialog);
  }

  public void conflictDialogContinue() {
    click(driver, conflictDialogContinueBtn);
  }

  public void conflictDialogGoBack() throws InterruptedException {
    click(driver, conflictDialogBackBtn);
    waitForElementToDisappear(driver, conflictDialog, 2);
  }

  public boolean isPriceConflictWarningIndicated() throws Throwable {
    return isElementDisplayed(driver, priceConflictWarning,1);
  }

  public void displayPriceConflictDialog() {
    click(driver, priceConflictWarning);
  }

  public boolean isPriceErrorDialogDisplayed() {
    return isElementDisplayed(driver, priceErrorDialog);
  }

  public void closePriceErrorDialog() throws InterruptedException {
    click(driver, priceErrorDialogCloseBtn);
    waitForElementToDisappear(driver, priceErrorDialog, 2);
  }

  public int getNbrOfManualConflicts(String page) {
    driver.manage().timeouts().implicitlyWait(1, SECONDS);
    try {
      if (page.equalsIgnoreCase("plan") && isElementDisplayed(driver, manualConflictIndicator(PageMenuButton.PLAN), 1)) {
        return Integer.parseInt(manualConflictIndicator(PageMenuButton.PLAN).getText().trim());
      } else if (page.equalsIgnoreCase("style") && isElementDisplayed(driver, manualConflictIndicator(PageMenuButton.STYLE), 1)) {
        return Integer.parseInt(manualConflictIndicator(PageMenuButton.STYLE).getText().trim());
      } else if (page.equalsIgnoreCase("sink") && isElementDisplayed(driver, manualConflictIndicator(PageMenuButton.SINK), 1)) {
        return Integer.parseInt(manualConflictIndicator(PageMenuButton.SINK).getText().trim());
      } else if (page.equalsIgnoreCase("cut-outs") && isElementDisplayed(driver, manualConflictIndicator(PageMenuButton.CUTOUT), 1)) {
        return Integer.parseInt(manualConflictIndicator(PageMenuButton.CUTOUT).getText().trim());
      } else if (page.equalsIgnoreCase("kitchen-island") && isElementDisplayed(driver, manualConflictIndicator(PageMenuButton.KITCHENISLAND), 1)) {
        return Integer.parseInt(manualConflictIndicator(PageMenuButton.KITCHENISLAND).getText().trim());
      }
    } catch (NoSuchElementException e) {

    }
    driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), SECONDS);
    return 0;
  }

  public int getNbrOfAutomaticConflicts(String page) {
    driver.manage().timeouts().implicitlyWait(1, SECONDS);
    try {
      if (page.equalsIgnoreCase("plan") && isElementDisplayed(driver, automaticConflictIndicator(PageMenuButton.PLAN), 1)) {
        return Integer.parseInt(automaticConflictIndicator(PageMenuButton.PLAN).getText().trim());
      } else if (page.equalsIgnoreCase("style") && isElementDisplayed(driver, automaticConflictIndicator(PageMenuButton.STYLE), 1)) {
        return Integer.parseInt(automaticConflictIndicator(PageMenuButton.STYLE).getText().trim());
      } else if (page.equalsIgnoreCase("sink") && isElementDisplayed(driver, automaticConflictIndicator(PageMenuButton.SINK),1)) {
        return Integer.parseInt(automaticConflictIndicator(PageMenuButton.SINK).getText().trim());
      } else if (page.equalsIgnoreCase("cut-outs") && isElementDisplayed(driver, automaticConflictIndicator(PageMenuButton.CUTOUT), 1)) {
        return Integer.parseInt(automaticConflictIndicator(PageMenuButton.CUTOUT).getText().trim());
      } else if (page.equalsIgnoreCase("kitchen-island") && isElementDisplayed(driver, automaticConflictIndicator(PageMenuButton.KITCHENISLAND), 1)) {
        return Integer.parseInt(automaticConflictIndicator(PageMenuButton.KITCHENISLAND).getText().trim());
      }
    } catch (NoSuchElementException e) {

    }
    driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), SECONDS);
    return 0;
  }

  public void waitForAutomaticConflictToDisappear(String page) throws InterruptedException {
    if (page.equalsIgnoreCase("plan")) {
      waitForElementToDisappear(driver, automaticConflictIndicator(PageMenuButton.PLAN), 5);
    } else if (page.equalsIgnoreCase("style")) {
      waitForElementToDisappear(driver, automaticConflictIndicator(PageMenuButton.STYLE), 5);
    } else if (page.equalsIgnoreCase("sink")) {
      waitForElementToDisappear(driver, automaticConflictIndicator(PageMenuButton.SINK), 5);
    } else if (page.equalsIgnoreCase("cut-outs")) {
      waitForElementToDisappear(driver, automaticConflictIndicator(PageMenuButton.CUTOUT), 5);
    } else if (page.equalsIgnoreCase("kitchen-island")) {
      waitForElementToDisappear(driver, automaticConflictIndicator(PageMenuButton.KITCHENISLAND), 5);
    }
  }

  public boolean isUnavailableOptionsDialogDisplayed() {
    return isElementDisplayed(driver, unavailableOptionsDialog);
  }

  public void dismissUnavailableOptionsDialog() {
    click(driver, unavailableOptionsDialogDismissBtn);
  }

  public boolean isScreensaverDisplayed() {
    return isElementDisplayed(driver, screenSaver);
  }

  public void dismissScreensaver() throws InterruptedException {
    click(driver, screenSaver);
    waitForElementToDisappear(driver, screenSaver,2);
  }

  public boolean isIdleTimeoutDialogDisplayed() {
    return isElementDisplayed(driver, idleTimeoutDialog);
  }

  public void dismissIdleTimeoutDialogByContinue() throws InterruptedException {
    click(driver, idleTimeoutDialogContinueBtn);
    waitForElementToDisappear(driver, idleTimeoutDialog, 2);
  }

  public void dismissIdleTimeoutDialogByExit() throws InterruptedException {
    click(driver, idleTimeoutDialogExitBtn);
    waitForElementToDisappear(driver, idleTimeoutDialog, 2);
  }

  public boolean newCountryPopupAvailable(){
    return isElementDisplayed(driver, newCountryDialog);
  }

  public void dismissNewCountryDialog() { click(driver, dismissPriceDifferenceDialog); }

  public boolean isRevertArrowDisplayed() { return isElementDisplayed(driver, revertArrow); }

  public boolean isForwardArrowDisplayed() { return isElementDisplayed(driver, forwardArrow); }

  public boolean isRevertPopupDisplayed() {
    return isElementDisplayed(driver, revertBubble);
  }

  public void clickRevertBubble() throws InterruptedException {
    click(driver, revertBubble);
    waitForElementToDisappear(driver, revertBubble, 2);
  }

  public void clickRevertArrow() throws InterruptedException {
    click(driver, revertArrow);
    waitForElementToDisappear(driver, revertArrow, 2);
  }

  public void clickForwardArrow() throws InterruptedException {
    click(driver, forwardArrow);
    waitForElementToDisappear(driver, forwardArrow, 2);
  }

  public boolean isArrowEnabled(String arrow) {
    if (arrow.equals("revert")){
      return revertArrow.isEnabled();
    }
    else {
      return forwardArrow.isEnabled();
    }
  }

  public void clickNextImage() {
    if (isElementDisplayed(driver, nextImage)) {
      click(driver, nextImage);
    }
    else {
      Assert.fail("NextImage button not found");
    }
  }

  public void clickPrevImage() {
    if (isElementDisplayed(driver, prevImage)) {
      click(driver, prevImage);
    }
    else {
      Assert.fail("PrevImage button not found");
    }
  }

  public boolean isVirtualKeyboardDisplayed() {
    return isElementDisplayed(driver, virtualKeyboard);
  }

  public boolean isImageDisplayed(String image) {
    if (image.equalsIgnoreCase("kitchen island")) {
      if (isElementDisplayed(driver, kitchenIslandImage)) {
        return true;
      }
    }
    else if (image.equalsIgnoreCase("worktop")) {
      if (isElementDisplayed(driver, worktopImage)) {
        return true;
      }
    }
    else if (image.equalsIgnoreCase("edge")) {
      if (isElementDisplayed(driver, edgeImage)) {
        return true;
      }
    }
    else if (image.equalsIgnoreCase("overhang")) {
      if (isElementDisplayed(driver, overhangImage)) {
        return true;
      }
    }
    else if (image.equalsIgnoreCase("kitchen island schematic")) {
      if (isElementDisplayed(driver, kitchenIslandImage)) {
        return true;
      }
    }
    else if (image.equalsIgnoreCase("kitchen island edge")) {
      if (isElementDisplayed(driver, kitchenIslandEdgeImage)) {
        return true;
      }
    }
    else if (image.equalsIgnoreCase("kitchen island worktop")) {
      if (isElementDisplayed(driver, kitchenIslandWorktopImage)) {
        return true;
      }
    }
    return false;
  }

  public boolean isNextImageButtonEnabled() {
    //String text = nextImageButton().getAttribute("class");
    String text = nextImage.getAttribute("class");
    if (text.contains("swiper-button-next")) {
      if (text.contains("swiper-button-disabled")) {
        return false;
      }
      else {
        return true;
      }
    }
    Assert.fail("Image button swipe next is not found");
    return false;
  }

  public boolean isPrevImageButtonEnabled() {
    //String text = prevImageButton().getAttribute("class");
    String text = prevImage.getAttribute("class");
    if (text.contains("swiper-button-prev")) {
      if (text.contains("swiper-button-disabled")) {
        return false;
      }
      else {
        return true;
      }
    }
    Assert.fail("Image button swipe prev is not found");
    return false;
  }

  public boolean iswhatsIncludedTextDisplayed() {
    return isElementDisplayed(driver, whatsIncluded);
  }

  public void clickWhatsIncluded() {
    click(driver,whatsIncluded);
  }

  public boolean isWhatsIncludedDialogDisplayed() {
    return isElementDisplayed(driver, whatsIncludedDialog);
  }

  public void clickWhatsIncludedDialogDismissBtn() {
    click(driver, dismissWhatsIncludedDialog);
  }

  public boolean isWhatsIncludedDialogDismissBtnDisplayed() {
    return isElementDisplayed(driver, dismissWhatsIncludedDialog);
  }

  public void dismissVirtualKeyboard() {
    click(driver, dismissVirtualKeyboardBtn);
  }

  public void clickNextWithVirtualKeyboard() {
    click(driver, nextVirtualKeyboardBtn);
  }
}

