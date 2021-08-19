package StepDefinitions;

import Support.ExecutionUtil;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.Buffer;
import java.util.List;

public class CommonSteps {

  private BaseTestClass base;

  public CommonSteps(BaseTestClass base) {
    this.base = base;
  }

  @After
  public void tearDown(Scenario scenario) {
    if(scenario.isFailed()) {
      try {
        scenario.write("Current URL is " + base.driver.getCurrentUrl());
        File scrFile = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshot.jpg"));
        byte[] screenshot = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
        List<LogEntry> entries = base.driver.manage().logs().get(LogType.BROWSER).getAll();
        scenario.write("Console Log: ");
        for (LogEntry entry : entries) {
          scenario.write(entry.getMessage());
        }
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    base.controller.cleanup();
  }

  @Given("^I have navigated to the (.*) page$")
  public void iHaveNavigatedToThe(String page) throws Throwable {
    base.commonTopPage.load();
    if (!page.equalsIgnoreCase("start")) {
      base.startPage.createNewPlan();
      base.commonTopPage.navigateTo(page);
      base.currentTab = page;
    }
  }

  @Given("^I have navigated to the application in Kiosk mode$")
  public void iHaveNavigatedToTheApplicationInKioskMode() throws Throwable {
    String[] query = {"mode=kiosk"};
    base.commonTopPage.load(query);
  }

  @And("^I have navigated to the application in Kiosk mode in country (.*)")
  public void iHaveNavigatedToTheApplicationInKioskModeInCountry(String country) throws Throwable {
    String language = "se/en/";
    if (country.equals("Taiwan")) {
      language = "tw/zh/";
    }
    else if (country.equals("Singapore")) {
      language = "se/en/";
    }
    String[] query = {"mode=kiosk"};
    base.commonTopPage.loadQueryCountry(query, language);
  }

  @Given("^I have navigated to the application on a touch display kiosk$")
  public void iHaveNavigatedToTheApplicationOnATouchDisplayKiosk() throws Throwable {
    String[] query = {"mode=kiosk", "uitest=displayVirtualKeyboard"};
    base.commonTopPage.load(query);
  }

  @Given("^I have navigated to the (.*) page for printing$")
  public void iHaveNavigatedToThePageForPrinting(String page) throws Throwable {
    String[] query = {"uitest=displayprintformat"};
    base.commonTopPage.load(page, query);
  }

  @When("^I navigate to the (.*) page")
  public void iNavigateToThePage(String page) throws Throwable {
    base.commonTopPage.navigateTo(page);
    base.currentTab = page;
  }

  @When("^I press the View summary button")
    public void iPressTheViewSummaryButton() throws Throwable {
      base.commonTopPage.navigateTo("item-list2");
  }

  @Given("^I navigate to tab (.*) with item no (.*) as additional parameter in the url$")
  public void iNavigateToTabAWithItemNoBAsAdditionalParameterInTheUrl(String tab, String itemNo) throws Throwable {
    String[] query = {"itemNo=" + itemNo};
    base.commonTopPage.load(tab, query);
    base.currentTab = tab;
    Thread.sleep(2000); //Small wait for app to load and display correct data
  }

  @Given("^I have scrolled down on the page$")
  public void iHaveScrolledDownOnThePage() throws Throwable {
    base.commonTopPage.scrollDown(300);
  }

  @And("^I have scrolled down (\\d+) pixels on the page$")
  public void iHaveScrolledDownOnThePageXPixels(int pixels) {
    base.commonTopPage.scrollDown(pixels);
  }

  @When("^I have scrolled up on the page$")
  public void iHaveScrolledUpPixelsOnThePage()  {
    base.commonTopPage.displayUpdatedInspirationalPicture();
  }

  @Then("^a button should appear indicating that the inspirational picture has been updated$")
  public void aButtonShouldAppearIndicatingThatTheInspirationalPictureHasBeenUpdated() {
    Assert.assertTrue("Inspirational picture updated button was not displayed", base.commonTopPage.isInspirationalPictureUpdated());
  }

  @And("^clicking the button should scroll the page to the top to display the updated picture$")
  public void clickingTheButtonShouldScrollThePageToTheTopToDisplayTheUpdatedPicture() throws InterruptedException {
    base.commonTopPage.displayUpdatedInspirationalPicture();
    //Small wait for scroll to complete
    Thread.sleep(1000);
    Assert.assertTrue("Page not scrolled to top", base.commonTopPage.isInspirationalPictureDisplayed());
  }

  @Then("^the price (.*) indicate a conflict$")
  public void thePriceShouldIndicateAConflict(String conflict) throws Throwable {
    boolean expectedConflict;
    if (conflict.equals("should")) {
      expectedConflict = true;
    } else {
      expectedConflict = false;
      Thread.sleep(1000); //Small wait for conflict indication to disappear
    }
    Assert.assertSame("Price conflict indication was wrong", expectedConflict, base.commonTopPage.isPriceConflictWarningIndicated());
  }

  @And("^(\\d+) (.*) conflict should be indicated in the (.*) tab$")
  public void conflictShouldBeIndicatedInTheTab(int nbrOfConflicts, String conflictType, String tab) throws Throwable {
    if (conflictType.equalsIgnoreCase("manual")) {
      Assert.assertEquals(String.format("Wrong number of manual conflicts indicated in the %s tab", tab), nbrOfConflicts, base.commonTopPage.getNbrOfManualConflicts(tab));
    } else if (conflictType.equalsIgnoreCase("automatic")) {
      Assert.assertEquals(String.format("Wrong number of automatic conflicts indicated in the %s tab", tab), nbrOfConflicts, base.commonTopPage.getNbrOfAutomaticConflicts(tab));
    }
  }

  @When("^I click the price conflict indicator$")
  public void iClickThePriceConflictIndicator() throws Throwable {
    base.commonTopPage.displayPriceConflictDialog();
  }

  @Then("^a price error dialog should be displayed$")
  public void aPriceConflictDialogShouldBeDisplayed() {
    Assert.assertTrue("Price error dialog was not displayed", base.commonTopPage.isPriceErrorDialogDisplayed());
  }

  @And("^clicking \"Got it\" in the dialog should close it$")
  public void clickingInTheDialogShouldCloseIt() throws Throwable {
    base.commonTopPage.closePriceErrorDialog();
  }

  @Then("^a conflict dialog should be displayed$")
  public void aConflictDialogShouldBeDisplayed() {
    Assert.assertTrue("Conflict dialog was not displayed", base.commonTopPage.isConflictDialogDisplayed());
  }

  @When("^pressing \"(.*)\" in the conflict dialog$")
  public void pressingInTheDialog(String btn) throws Throwable {
    if (btn.equalsIgnoreCase("continue")) {
      base.commonTopPage.conflictDialogContinue();
    } else if (btn.equalsIgnoreCase("go back")) {
      base.commonTopPage.conflictDialogGoBack();
    }
  }

  @Then("^the automatic conflict indicator should disappear in the (.*) tab$")
  public void theAutomaticConflictIndicatorShouldDisappear(String tab) throws Throwable {
    base.commonTopPage.waitForAutomaticConflictToDisappear(tab);
  }

  @Then("^I should be navigated to the correct tab$")
  public void iShouldBeNavigatedToTheCorrectTab()  {
    if (base.currentTab.equalsIgnoreCase("plan")) {
      Assert.assertTrue("Plan page was not displayed", base.planPage.isPageDisplayed());
    } else if (base.currentTab.equalsIgnoreCase("style")) {
      Assert.assertTrue("Style page was not displayed", base.stylePage.isPageDisplayed());
    } else if (base.currentTab.equalsIgnoreCase("sink")) {
      Assert.assertTrue("Sink page was not displayed", base.sinkPage.isPageDisplayed());
    } else if (base.currentTab.equalsIgnoreCase("cut-outs")) {
      Assert.assertTrue("Cut-outs page was not displayed", base.cutOutsPage.isPageDisplayed());
    } else {
      Assert.fail("Unknown page: " + base.currentTab);
    }
  }

  @Then("^I should be navigated to the (.*) page$")
  public void iShouldBeNavigatedToThePage(String page) {
    if (page.equalsIgnoreCase("start")) {
      Assert.assertTrue("Start page was not displayed", base.startPage.isPageDisplayed());
    } else if (page.equalsIgnoreCase("plan")) {
      Assert.assertTrue("Plan page was not displayed", base.planPage.isPageDisplayed());
    } else if (page.equalsIgnoreCase("style")) {
      Assert.assertTrue("Style page was not displayed", base.stylePage.isPageDisplayed());
    } else if (page.equalsIgnoreCase("sink")) {
      Assert.assertTrue("Sink page was not displayed", base.sinkPage.isPageDisplayed());
    } else if (page.equalsIgnoreCase("cut-outs")) {
      Assert.assertTrue("Cut-outs page was not displayed", base.cutOutsPage.isPageDisplayed());
    } else if (page.equalsIgnoreCase("item-list")) {
      Assert.assertTrue("Item list page was not displayed", base.itemListPage.isPageDisplayed());
    } else {
      Assert.fail("Unknown page: " + page);
    }
  }

  @And("^the total price should be (.*)$")
  public void theTotalPriceShouldBe(String itemList) throws Throwable
  {
    base.evaluatePrice(itemList);
  }

  @Given("^I execute the test on an environment that allows saving$")
  public void iExecuteTheTestOnAnEnvironmentThatAllowsSaving()
  {
    ExecutionUtil.setUrl(ExecutionUtil.urlUnstableSE);
  }

  @When("^I select to save my plan in the popup dialog$")
  public void iSelectToSaveMyPlanInThePopupDialog() throws Throwable {

    base.commonTopPage.exitAndSave();
  }

  @Then("^a dialog (.*) be displayed informing that there are some options in the saved plan that is no longer available$")
  public void aDialogShouldBeDisplayedInformingThatThereAreSomeOptionsInTheSavedPlanThatIsNoLongerAvailable(String option) throws Throwable {
    if (option.equals("should")) {
      Assert.assertTrue("Unavailable options dialog was not displayed", base.commonTopPage.isUnavailableOptionsDialogDisplayed());
    }
    else {
      Assert.assertFalse("Unavailable options dialog was displayed when not expected", base.commonTopPage.isUnavailableOptionsDialogDisplayed());
    }
  }

  @When("^I close the dialog$")
  public void iCloseTheDialog() throws Throwable {
    base.commonTopPage.dismissUnavailableOptionsDialog();
  }

  @When("^I don't do any actions for more than (\\d+) seconds$")
  public void iDonTDoAnyActionsForMoreThanSeconds(int waitTime) throws Throwable {
    Thread.sleep(waitTime*1000 );
  }

  @Then("^a screensaver should appear$")
  public void aScreensaverShouldAppear() throws Throwable {
    Assert.assertTrue("Screensaver was not displayed", base.commonTopPage.isScreensaverDisplayed());
  }

  @And("^when clicking on the screensaver$")
  public void whenClickingOnTheScreensaver() throws Throwable {
    base.commonTopPage.dismissScreensaver();
  }

  @Then("^the screensaver should be closed$")
  public void theScreensaverShouldBeClosed() throws Throwable {
    Assert.assertFalse("Screensaver was not closed", base.commonTopPage.isScreensaverDisplayed());
  }

  @Then("^an idle timeout dialog should appear with a countdown$")
  public void aIdleTimeoutDialogShouldAppearWithACountdown() throws Throwable {
    Assert.assertTrue("Idle timeout dialog was not displayed", base.commonTopPage.isIdleTimeoutDialogDisplayed());
  }

  @When("^I click (.*) in the dialog$")
  public void iClickContinueInTheDialog(String action) throws Throwable {
    if (action.equalsIgnoreCase("Continue")) {
      base.commonTopPage.dismissIdleTimeoutDialogByContinue();
    } else if (action.equalsIgnoreCase("Exit")) {
      base.commonTopPage.dismissIdleTimeoutDialogByExit();
    } else {
      Assert.fail("Unknown action " + action);
    }
  }

  @And("^I should stay at the (.*) page$")
  public void iShouldStayAtTheStylePage(String page) throws Throwable {
    iShouldBeNavigatedToThePage(page);
  }

  @When("^I close the New country dialog$")
  public void closeNewCountryDialog() throws Throwable {
    base.commonTopPage.dismissNewCountryDialog();
  }

  @Then("^a popup for New country (.*) appear$")
  public void aPopupForNewCountryShouldAppear(String option) throws Throwable {
    if (option.equals("should")) {
      Assert.assertTrue("Dialog about new country not displayed", base.commonTopPage.newCountryPopupAvailable());
    }
    else {
      Assert.assertFalse("Dialog about new country appears when not expected", base.commonTopPage.newCountryPopupAvailable());
    }
  }

  @Then("^a (.*) arrow should be displayed$")
  public void aRevertArrowShouldBeDisplayed(String arrow) throws Throwable {
    if (arrow.equals("revert")) {
      Assert.assertTrue("The revert arrow is not displayed", base.commonTopPage.isRevertArrowDisplayed());
      Assert.assertTrue("The revert arrow is disabled", base.commonTopPage.isArrowEnabled("revert"));
    }
    else {
      Assert.assertTrue("The forward arrow is not displayed", base.commonTopPage.isForwardArrowDisplayed());
      Assert.assertTrue("The forward arrow is disabled", base.commonTopPage.isArrowEnabled("forward"));
    }
  }

  @And("^an information bubble should be displayed$")
  public void anInformationBubbleShouldBeDisplayed() throws Throwable {
    Assert.assertTrue("The revert information popup is not displayed", base.commonTopPage.isRevertPopupDisplayed());
  }

  @When("^I click the information bubble$")
  public void IClickTheInformationBubble() throws Throwable {
    Assert.assertTrue("Revert bubble not displayed",base.commonTopPage.isRevertPopupDisplayed());
    base.commonTopPage.clickRevertBubble();
  }

  @Then("^it should disappear$")
  public void itShouldDisappear() throws Throwable {
      Assert.assertFalse("Revert bubble still available",base.commonTopPage.isRevertPopupDisplayed());
  }

  @When("^clicking on the (.*) arrow$")
  public void clickingOnTheArrow(String arrow) throws Throwable {
    if (arrow.equals("revert")){
      base.commonTopPage.clickRevertArrow();
    }
    else {
      base.commonTopPage.clickForwardArrow();
    }
  }

  @And("^the (.*) arrow is changed to a (.*) arrow$")
  public void theArrowIsChangedToAForwardArrow(String arrow1, String arrow2) throws Throwable {
    if (arrow1.equals("forward")) {
      Assert.assertFalse("Forward arrow still visible", base.commonTopPage.isForwardArrowDisplayed());
      Assert.assertTrue("Revert arrow not visible", base.commonTopPage.isRevertArrowDisplayed());
    }
    else {
      Assert.assertFalse("Revert arrow still visible", base.commonTopPage.isRevertArrowDisplayed());
      Assert.assertTrue("Forward arrow not visible", base.commonTopPage.isForwardArrowDisplayed());
    }
  }

  @And("^the (.*) arrow should be disabled$")
  public void theForwardArrowShouldBeDisabled(String arrow) throws Throwable {
    if (arrow.equals("forward")) {
      base.commonTopPage.isForwardArrowDisplayed();
      base.commonTopPage.isArrowEnabled("forward");
    }
    else {
      base.commonTopPage.isRevertArrowDisplayed();
      base.commonTopPage.isArrowEnabled("revert");
    }

  }

  @And("^virtual keyboard (.*) displayed$")
  public void virtualKeyboardDisplayed(String vk_displayed) throws Throwable {
    if (vk_displayed.equals("is")) {
      Assert.assertTrue("Virtual keyboard is not displayed when expected" ,base.commonTopPage.isVirtualKeyboardDisplayed());
    }
    else {
      Assert.assertFalse("Virtual keyboard is displayed when not expected" ,base.commonTopPage.isVirtualKeyboardDisplayed());
    }
  }

  @And("^the (.*) image (.*) be displayed$")
  public void theImageShouldBeDisplayed(String image, String option) throws Throwable {
    if (option.equals("should")) {
      Assert.assertTrue("Image not displayed: " + image, base.commonTopPage.isImageDisplayed(image));
    }
    else {
      Assert.assertFalse("Image displayed when not expected: " + image, base.commonTopPage.isImageDisplayed(image));
    }
  }

  @When("^selecting the (.*) button for the image view$")
  public void selectingTheButtonForTheImageView(String direction) throws Throwable {
    if (direction.equalsIgnoreCase("next")) {
      base.commonTopPage.clickNextImage();
    }
    else {
      base.commonTopPage.clickPrevImage();
    }
  }

  @Then("^the (.*) button is disabled for the image view$")
  public void theButtonIsDisabledForTheImageView(String direction) throws Throwable {
    if (direction.equals("next")) {
      Assert.assertFalse("Next image button is enabled", base.commonTopPage.isNextImageButtonEnabled());
    } else {
      Assert.assertFalse("Prev image button is enabled", base.commonTopPage.isPrevImageButtonEnabled());
    }
  }

  @Then("^the (.*) button is enabled for the image view$")
  public void theButtonIsEnabledForTheImageView(String direction) throws Throwable {
    if (direction.equals("next")) {
      Assert.assertTrue("Next image button is disabled", base.commonTopPage.isNextImageButtonEnabled());
    }
    else {
      Assert.assertTrue("Prev image button is disabled", base.commonTopPage.isPrevImageButtonEnabled());
    }
  }

  @Then("^the worktop image is displayed$")
  public void theWorktopImageIsDisplayed() throws Throwable {
    Assert.assertTrue("", base.commonTopPage.isImageDisplayed("worktop"));
  }

  @Given("^I have navigated to the (.*) page in country (.*)")
  public void iHaveNavigatedToThePageInCountry(String page, String country) throws Throwable {
    String language = setLanguage(country);
    Thread.sleep(1000);
    base.commonTopPage.loadCountry(page, language);
  }

  private String setLanguage(String country) {
    if (country.equals("Taiwan")) {
      return "tw/zh/";
    }
    else if (country.equals("Sweden")) {
      return "se/sv/";
    }
    else if (country.equals("Bahrain")) {
      return "pt/pt/";
    }
    return "se/en/";
  }

  @And("^I navigate to the (.*) page in country (.*)")
  public void iNavigateToThePageInCountry(String page, String country) throws Throwable {
    String language = setLanguage(country);
    base.commonTopPage.loadCountry(page, language);
    base.currentTab = page;
  }

  @Given("^I have navigated to the (.*) page on a touch display kiosk$")
  public void iHaveNavigatedToThePageOnATouchDisplayKiosk(String page) throws Throwable {
    String[] query = {"mode=kiosk", "uitest=displayVirtualKeyboard"};
    base.commonTopPage.load(page, query);
  }

  @And("^what´s included text is (.*)")
  public void whatSIncludedTextIs(String option) throws Throwable {
    if (option.equalsIgnoreCase("missing")) {
      Assert.assertFalse("What´s included text is missing", base.commonTopPage.iswhatsIncludedTextDisplayed());
    }
    else {
      Assert.assertTrue("What´s included text is present when conflict exist", base.commonTopPage.iswhatsIncludedTextDisplayed());
    }
  }

  @When("^I select whats included text$")
  public void iSelectWhatsIncludedText() throws Throwable {
    base.commonTopPage.clickWhatsIncluded();
  }

  @Then("^a popup for whats included should (.*)$")
  public void aPopupForWhatsIncludedShouldAppear(String option) throws Throwable {
    if (option.equals("appear")) {
      Assert.assertTrue("Whats included dialog is missing", base.commonTopPage.isWhatsIncludedDialogDisplayed());
    }
    else {
      Thread.sleep(1000);
      Assert.assertFalse("Whats included dialog is still present", base.commonTopPage.isWhatsIncludedDialogDisplayed());
    }
  }

  @When("^the whats included dialog is dismissed$")
  public void theWhatsIncludedDialogIsDismissed() throws Throwable {
    Assert.assertTrue("The whats included dismiss dialog is not found", base.commonTopPage.isWhatsIncludedDialogDismissBtnDisplayed());
    base.commonTopPage.clickWhatsIncludedDialogDismissBtn();
  }

  @And("^kitchen island schematic image is displayed$")
  public void kitchenIslandSchematicImageIsDisplayed() throws Throwable {

  }

  @Then("^the numerical virtual keyboard should disappear$")
  public void theNumericalVirtualKeyboardShouldDisappear() throws Throwable {
    Thread.sleep(500);
    Assert.assertFalse("Virtual keyboard was displayed when not expected", base.planPage.isVirtualKeyboardDisplayed());
  }

  @When("^I press dismiss virtual keyboard$")
  public void iPressDismissVirtualKeyboard() throws Throwable {
    base.commonTopPage.dismissVirtualKeyboard();
  }

  @When("^I click next with the virtual keyboard$")
  public void iClickNextWithTheVirtualKeyboard() throws Throwable {
    base.commonTopPage.clickNextWithVirtualKeyboard();
  }
}
