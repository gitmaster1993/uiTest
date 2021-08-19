package StepDefinitions;

import Models.Article;
import PageObjects.ComparePage;
import com.udojava.evalex.Expression;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;


public class ComparePageSteps {
  private BaseTestClass base;
  private String allItems;

  public ComparePageSteps(BaseTestClass base) {
    this.base = base;
  }

  @Then("(\\d+) plans are compared$")
  public void plansAreCompared(int expectedNrOfPlans) throws Throwable {
    Thread.sleep(3000);
    Assert.assertTrue("Number of plans not as expected",base.comparePage.nrOfPlansCompared(expectedNrOfPlans));
  }

  @When("^I want to add a plan with code (.*)$")
  public void iWantToAddAPlanWithCode(String code) throws Throwable {
    Assert.assertTrue("Not possible to enter code, since button is disabled", base.comparePage.enterCode(code));
  }

  @And("^I want to add my first plan with code (.*)$")
  public void iWantToAddMyFistPlanWithCode(String code) throws Throwable {
    Thread.sleep(500);
    Assert.assertTrue("Not possible to enter first code, since button is disabled", base.comparePage.enterFirstCode(code));
  }

  @And("^Enter code button is disabled$")
  public void enterCodeButtonIsDisabled() throws Throwable {
    Assert.assertFalse("Enter code button is enabled when not expected", base.comparePage.isEnterCodeDisabled());
  }

  @When("^I delete all plans$")
  public void iDeleteAllPlans() throws Throwable {
    base.comparePage.removeAllPlansFromComparePage();
  }

  @Then("^it is possible to enter new plans$")
  public void itIsPossibleToEnterNewPlans() throws Throwable {
    Assert.assertTrue("Not possible to add new plan",base.comparePage.isEnterCodeDisplayed());
  }

  @And("^(.*) image is marked$")
  public void styleImageIsMarked(String type) throws Throwable {
    Assert.assertTrue("Image of type " + type + " is not marked when expected" , base.comparePage.isImageMarked(type));
  }

  @And("^(.*) image is not marked$")
  public void styleImageIsNotMarked(String type) throws Throwable {
    Assert.assertFalse("Image of type " + type + " is marked when not expected", base.comparePage.isImageMarked(type));
  }

  @And("^add new plan in the top menu is enabled and clicked")
  public void addNewPlanInTheTopMenuIsEnabledAndClicked() throws Throwable {
    Thread.sleep(500);
    Assert.assertTrue("Add new plan is not enabled in top menu when expected", base.comparePage.isAddPlanEnabled());
    base.comparePage.selectAddPlan();
  }

  /*@And("^add new plan in the top menu is enabled")
  public void addNewPlanInTheTopMenuIsEnabled() throws Throwable {
    Assert.assertTrue("Add new plan is not enabled in top menu when expected", base.comparePage.isAddPlanEnabled());
  }*/

  @And("^add new plan in the top menu is disabled")
  public void addNewPlanInTheTopMenuIsDisabled() throws Throwable {
    Assert.assertFalse("Add new plan is enabled in top menu when not expected", base.comparePage.isAddPlanEnabled());
  }

  @And("^add new plan in the top menu not available$")
  public void addNewPlanInTheTopMenuNotAvailable() throws Throwable {
    Assert.assertFalse("Add new plan is enabled in top menu when not expected", base.comparePage.isAddPlanDisplayed());
  }

  @When("^I click Show details for (.*)$")
  public void iClickShowDetailsForType(String type) throws Throwable {
    if (!base.comparePage.isDetailsExpanded(type)) {
      base.comparePage.expandDetails(type);
      Thread.sleep(500);
      Assert.assertTrue("Not all details are expanded for type " + type, base.comparePage.isAllDetailsExpanded(type));
    }
  }

  @Then("^all the (.*) details should be displayed$")
  public void allTheStyleDetailsShouldBeDisplayed(String type) throws Throwable {
    Assert.assertTrue("", base.comparePage.isDetailsExpanded(type));
  }

  @Then("^an error message appears in column (\\d+)$")
  public void anErrorMessageAppearsInColumn(int column) {
    Assert.assertTrue("No error message is displayed for code that contains errors", base.comparePage.isErrorMessageDisplayed(column));
  }

  @When("^pressing OK button in column (\\d+)$")
  public void pressingOKButtonInColumn(int column) throws Throwable {
    base.comparePage.clickOkButton(column);
  }

  @Then("^the virtual keyboard is visible so I can enter the first code (.*)$")
  public void theVirtualKeyboardIsVisibleSoICanEnterTheFirstCode(String code) throws Throwable {
    Thread.sleep(500);
    Assert.assertTrue("Enter code button not found", base.comparePage.clickEnterCodeButton());
    Assert.assertTrue("Virtual keyboard was not displayed", base.comparePage.isVirtualKeyboardDisplayed());
    base.comparePage.enterCodeWithVirtualKeyboard(code);
  }

  @Then("^the virtual keyboard is visible so I can enter the code (.*)$")
  public void theVirtualKeyboardIsVisibleSoICanEnterTheCode(String code) throws Throwable {
    Thread.sleep(500);
    //Assert.assertTrue("Enter code button not found", base.comparePage.clickEnterCodeButton());
    Assert.assertTrue("Virtual keyboard was not displayed", base.comparePage.isVirtualKeyboardDisplayed());
    base.comparePage.enterCodeWithVirtualKeyboard(code);
  }

  @Then("^an error message is displayed after the enter code dialogue$")
  public void anErrorMessageIsDisplayedAfterTheEnterCodeDialogue() throws Throwable {
    Assert.assertTrue("Error message not displayed", base.comparePage.isErrorMessageDisplayedAfterEnterCode());
  }

  @Then("^the (.*) image should be displayed in column (\\d+)$")
  public void theImageShouldBeDisplayedInColumn(String image, int column) throws Throwable {
    Thread.sleep(500);
    Assert.assertTrue("The image is not displayed: " + image + column, base.comparePage.isImageDisplayed(image, column));
  }

  @When("^swiping once (.*) in column (\\d+)$")
  public void swipingOnceInColumn(String direction, int column) throws Throwable {
    base.comparePage.swipeInImage(direction, column);
  }

  @When("^swiping to bullet (\\d+) in column (\\d+)$")
  public void swipingToBulletInColumn(int bullet, int column) throws Throwable {
    base.comparePage.clickImageBullet(bullet, column);
  }

  @When("^I select to go back from compare page$")
  public void iSelectToGoBackFromComparePage() throws Throwable {
    base.comparePage.gobackFromComparePage();
  }
}
