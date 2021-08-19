package StepDefinitions;

import Models.Article;
import PageObjects.ComparePage;
import com.udojava.evalex.Expression;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class KitchenIslandSteps {
  private BaseTestClass base;
  private String allItems;

  public KitchenIslandSteps(BaseTestClass base) {
    this.base = base;
  }

  @Then("^the kitchen island option (.*) should be selected$")
  public void theKitchenIslandOptionShouldBeSelected(String option) {
    Assert.assertTrue("Wrong kitchen island option selected " + option, base.kitchenIslandPage.isKitchenIslandSelected(option));
  }

  @Then("the kitchen island style is set to same as worktop$")
  public void kitchenIslandStyleIsSetToSameAsWorktop() throws Throwable {
    Assert.assertTrue("Kitchen island style is not set to same as worktop",base.kitchenIslandPage.isKitchenIslandStyleEnabled());
  }

  @When("^I select kitchen island material (.*), expression (.*), (.*) edge and thickness (\\d+) mm$")
  public void iSelectKitchenIslandMaterialLaminateExpressionDarkGreyLaminateSameColorEdgeAndThicknessMm(String material, String expression, String edge, int thickness) throws Throwable {
    base.kitchenIslandPage.selectMaterial(material);
    base.kitchenIslandPage.selectExpression(expression);
    base.commonTopPage.scrollDown(1000);
    base.kitchenIslandPage.selectEdge(edge);
    base.kitchenIslandPage.selectThickness(thickness);
  }

  @When("^selecting (.*) kitchen island$")
  public void selectingKitchenIsland(String option) throws Throwable {
    if (option.equals("a")) {
      base.kitchenIslandPage.setKitchenIslandSelection();
    }
    else {
      Assert.assertTrue("No kitchen island option not found", base.kitchenIslandPage.isNoKitchenIslandDisplayed());
      base.kitchenIslandPage.setNoKitchenIsland();
    }
  }

  @And("^the kitchen island material is (.*), expression is (.*), edge is (.*) and thickness is (\\d+) mm$")
  public void theKitchenIslandMaterialIsExpressionIsEdgeIsAndThicknessIsMm(String material, String expression, String edge, int thickness) throws Throwable {
    base.kitchenIslandPage.isMaterialSelected(material);
    base.kitchenIslandPage.isExpressionSelected(expression);
    base.commonTopPage.scrollDown(1000);
    base.kitchenIslandPage.isEdgeSelected(edge);
    base.kitchenIslandPage.isThicknessSelected(thickness);
  }

  @When("^I select kitchen island style same as worktop$")
  public void iSelectKitchenIslandStyleSameAsWorktop() throws Throwable {
    Assert.assertTrue("Kitchen island radio button same as worktop not found", base.kitchenIslandPage.isKitchenIslandSetAsWorktopDisplayed());
    base.kitchenIslandPage.clickKitchenIslandSetAsWorktop();
  }
}
