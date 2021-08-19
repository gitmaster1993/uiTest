package StepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Random;

public class StartPageSteps {
  private BaseTestClass base;

  public StartPageSteps(BaseTestClass base)
  {
    this.base = base;
  }

 // @Before (" @Initialize")
  public void beforeScenario(){
    System.out.println("started string");
  }//

  @When("^I select to create a new plan$")
  public void iSelectToCreateANewPlan() throws Throwable {
    base.startPage.createNewPlan();
  }

  @Then("^the app should display the Style page$")
  public void theAppShouldDisplayTheStylePage() throws Throwable {
    Assert.assertTrue("Style page was not displayed", base.stylePage.isPageDisplayed());
  }

  @When("^I select exit$")
  public void iSelectExit() throws Throwable {
    base.commonTopPage.exit();
  }

  @Then("^a popup (.*) appear where it should be possible to save or exit without saving$")
  public void aPopupShouldAppearWhereItShouldBePossibleToSaveOrExitWithoutSaving(String option) throws Throwable {
    if (option.equals("should")) {
      Assert.assertTrue("Exit dialog was not displayed", base.commonTopPage.isExitDialogDisplayed());
    }
    else {
      Assert.assertFalse("Exit dialog was displayed when not expected", base.commonTopPage.isExitDialogDisplayed());
    }
  }

  @And("^selecting exit without saving should navigate back to start page$")
  public void selectingExitWithoutSavingShouldNavigateBackToStartPage() throws Throwable {
    base.commonTopPage.exitWithoutSaving();
    Assert.assertTrue("Start page was not displayed after exit", base.startPage.isPageDisplayed());
  }

  @And("^the save plan dialog (.*) be displayed$")
  public void theSavePlanDialogShouldBeDisplayed(String option) throws Throwable {
    if (option.equals("should")) {
      Assert.assertTrue("Saved plan dialog was not displayed", base.startPage.isPlanSavedDialogDisplayed());  }
    else {
      Assert.assertFalse("Saved plan dialog was displayed when not expected", base.startPage.isPlanSavedDialogDisplayed());
    }
  }

  @When("^I load my saved plan from the code in the dialog$")
  public void iLoadMySavedPlanFromTheCodeInTheDialog() throws Throwable {
    String code = base.startPage.getSavedPlanCodeFromDialog();
    System.out.println("Save code: " + code);
    base.startPage.closePlanSavedDialog();
    if (!base.startPage.isPageDisplayed()) {
      Assert.assertTrue("Start over button not displayed", base.itemListPage.isStartOverBtnDisplayed());
      base.itemListPage.clickStartOverBtn();
    }
    base.startPage.loadSavedPlan(code);
  }

  @When("^I load my saved plan with lowercase letters$")
  public void iLoadMySavedPlanWithLowercaseLetters() throws Throwable {
    String code = base.startPage.getSavedPlanCodeFromList(1);
    System.out.println("Save code: " + code);
    String lowercaseCode = code.toLowerCase();
    base.startPage.loadSavedPlan(lowercaseCode);
  }

  @When("^I select to load plan with code (.*)$")
  public void iSelectToLoadPlanWithCode(String code)  {
    base.startPage.loadSavedPlan(code);
  }

  @When("^I click the enter existing code field$")
  public void iClickTheEnterExistingCodeField() throws Throwable {
    base.startPage.clickExistingCodeField();
  }

  @Then("^the virtual keyboard should be displayed$")
  public void theVirtualKeyboardShouldBeDisplayed() throws Throwable {
    Assert.assertTrue("Virtual keyboard was not displayed", base.startPage.isVirtualKeyboardDisplayed());
  }

  @And("^when entering code (.*) with the virtual keyboard$")
  public void whenEnteringACodeWithTheVirtualKeyboard(String code) throws Throwable {
    base.startPage.enterCodeFromVirtualKeyboard(code);
  }

  @Then("^(.*) should be entered in the existing code field$")
  public void thatCodeShouldBeEnteredInTheExistingCodeField(String code) throws Throwable {
    Assert.assertEquals("Wrong code entered", code, base.startPage.getEnteredCode());
  }

  @When("^I close the save plan dialog$")
  public void iCloseTheSavePlanDialog() throws Throwable {
    base.savedPlanCode = base.startPage.getSavedPlanCodeFromDialog();
    base.startPage.closePlanSavedDialog();
  }

  @And("^my recently saved plan should be on top of the saved plans list$")
  public void myRecentlySavedPlanShouldBeOnTopOfTheSavedPlansList() throws Throwable {
    Assert.assertEquals("Recent saved plan code not on top of list", base.savedPlanCode, base.startPage.getSavedPlanCodeFromList(1));
  }

  @And("^there should not be a saved plans list displayed$")
  public void thereShouldNotBeASavedPlansListDisplayed() throws Throwable {
    Assert.assertFalse("Saved plans list wrongly displayed", base.startPage.isSavedPlansListDisplayed());
  }

  @When("^I select to enter the plan from the top of the list$")
  public void iSelectToEnterThePlanFromTheTopOfTheList() throws Throwable {
    base.startPage.selectSavedPlanFromList(1);
  }

  @When("^I select to edit the name of the top plan$")
  public void iSelectToEditTheNameOfTheTopPlan() throws Throwable {
    Random random = new Random();
    base.savedPlanName = "CWT" + random.nextInt(100);
    base.startPage.editSavedPlanName(1, base.savedPlanName);
  }

  @Then("^the name in the top plan list should be changed to the new name$")
  public void theNameInTheTopPlanListShouldBeChangedToTheNewName() throws Throwable {
    Assert.assertEquals("Edited name not saved correctly", base.savedPlanName, base.startPage.getSavedPlanName(1));
  }

  @When("^I select delete for the top plan$")
  public void iSelectDeleteForTheTopPlan() throws Throwable {
    base.startPage.deleteSavedPlan(1);
  }

  @Then("^that plan should be deleted from the list$")
  public void thatPlanShouldBeDeletedFromTheList() throws Throwable {
    Assert.assertFalse("Plan was not deleted", base.startPage.isPlanWithCodeInList(base.savedPlanCode));
  }

  @Then("^a delete confirm dialog should be displayed$")
  public void aDeleteConfirmDialogShouldBeDisplayed() throws Throwable {
    Assert.assertTrue("The delete confirm dialog was not displayed", base.startPage.isDeletedSavedPlanDialogDisplayed());
  }

  @And("^if selecting to delete in the delete confirm dialog$")
  public void ifSelectingToDeleteInTheDeleteConfirmDialog() throws Throwable {
    base.startPage.confirmDeleteSavePlanInDialog();
  }

  @And("^if selecting to not delete in the delete confirm dialog$")
  public void ifSelectingToNotDeleteInTheDeleteConfirmDialog() throws Throwable {
    base.startPage.undoDeleteSavePlanInDialog();
  }

  @Then("^that plan should not have been deleted from the list$")
  public void thatPlanShouldNotHaveBeenDeletedFromTheList() throws Throwable {
    Assert.assertTrue("Plan was wrongly deleted", base.startPage.isPlanWithCodeInList(base.savedPlanCode));
  }

  @And("^my recently saved plan should be on top of the saved plans list with the correct updated name$")
  public void myRecentlySavedPlanShouldBeOnTopOfTheSavedPlansListWithTheCorrectUpdatedName() throws Throwable {
    Assert.assertEquals("Recent saved plan code not on top of list", base.savedPlanCode, base.startPage.getSavedPlanCodeFromList(1));
    Assert.assertEquals("Edited name not saved correctly", base.updatedPlanName, base.startPage.getSavedPlanName(1));
  }

  @And("^the old plan should still be in the list$")
  public void theOldPlanShouldStillBeInTheList() throws Throwable {
    Assert.assertEquals("Old name not kept in list", base.savedPlanName, base.startPage.getSavedPlanName(2));
  }

  @Then("^the list of saved codes should only be saved with capital letters$")
  public void theListOfSavedCodesShouldOnlyContainMyCodeOnce() throws Throwable {
    String code = base.startPage.getSavedPlanCodeFromList(1);
    Assert.assertNotEquals("Plan is saved with lowercase letters", code, code.toLowerCase());
  }

  @When("^I check (\\d+) checkboxes$")
  public void iCheckCheckboxes(int nbrOfCb) throws Throwable {
    base.startPage.checkCheckbox(nbrOfCb);
  }

  @When("^I uncheck all checkboxes$")
  public void iUncheckAllCheckboxes() throws Throwable {
    for (int i=1; i<=4; i++) {
      base.startPage.uncheckCheckbox(i);
    }
  }

  @Then("^the compare button is disabled$")
  public void theCompareButtonIsDisabled () throws Throwable {
    Assert.assertFalse("Compare button enabled when not expected", base.startPage.GetCompareButtonStatus());
  }

  @Then("^the compare button is enabled$")
  public void theCompareButtonIsEnabled() throws Throwable {
    Assert.assertTrue("Compare button disabled when not expected", base.startPage.GetCompareButtonStatus());
  }

  @When("I click the compare button$")
  public void iClickTheCompareButton() throws Throwable {
    base.startPage.clickCompareButton();
  }
}
