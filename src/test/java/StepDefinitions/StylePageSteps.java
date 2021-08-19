package StepDefinitions;


import PageObjects.StylePage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.cs.A;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StylePageSteps {
  private BaseTestClass base;
  private List<List<String>> currentScenarioData;

  public StylePageSteps(BaseTestClass base) {
    this.base = base;
  }

  @Given("^I have selected (.*) material$")
  public void iHaveSelectedMaterial(String material) throws Throwable {
    base.stylePage.selectMaterial(material);
  }

  @Given("^I have selected have selected material a and expression b$")
  public void iHaveSelectedHaveSelectedMaterialAAndExpressionB(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    currentScenarioData = data;
    for(int i = 1; i < data.size(); i++) {
      String material = data.get(i).get(0);
      String expression = data.get(i).get(1);
      base.stylePage.selectMaterial(material);
      base.stylePage.selectExpression(expression);
    }
  }

  @When("^I select (.*) material$")
  public void whenISelectMaterial(String material) throws Throwable {
    base.stylePage.selectMaterial(material);
  }

  @When("^I select (.*) expression$")
  public void iSelectExpression(String expression) throws Throwable {
    base.stylePage.selectExpression(expression);
  }

@Then("^when I select material <a> the default expression should be <b>, the default edge should be <c>, the default thickness should be <d> mm, the default splashback should be <e>, wall panel material should be <f> and expression should be <g>$")
  public void whenISelectMaterialATheDefaultExpressionShouldBeBTheDefaultEdgeShouldBeCTheDefaultThicknessShouldBeDMmTheDefaultSplashbackShouldBeEWallPanelMaterialShouldBeFAndExpressionShouldBeG(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String material = data.get(i).get(0);
      String expectedDefaultExpression = data.get(i).get(1);
      String expectedDefaultEdge = data.get(i).get(2);
      String expectedDefaultThickness = data.get(i).get(3);
      String expectedDefaultSplashback = data.get(i).get(4);
      String expectedDefaultWallPanelExpression = data.get(i).get(5);
      String expectedDefaultWallPanelMaterial = data.get(i).get(6);

      base.stylePage.selectMaterial(material);
      Assert.assertTrue("Wrong default expression for material " + material, base.stylePage.isExpressionSelected(expectedDefaultExpression));
      Assert.assertTrue("Wrong default edge for material " + material, base.stylePage.isEdgeSelected(expectedDefaultEdge));
      Assert.assertTrue("Wrong default thickness for material " + material, base.stylePage.isThicknessSelected(expectedDefaultThickness));
      base.commonTopPage.scrollDown(900);
      Assert.assertTrue("Wrong default splashback for material " + material, base.stylePage.isSplashbackSelected(expectedDefaultSplashback));
      Assert.assertTrue("Wrong default wall panel expression for material " + material, base.stylePage.isWallPanelExpressionDisabled());
      Assert.assertTrue("Wrong default wall panel expression for material " + material, base.stylePage.isWallPanelMaterialDisabled());
    }
  }

  @Then("^when I select material <a> then the available expression should be <b>$")
  public void whenISelectMaterialAThenTheAvailableExpressionShouldBeB(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String material = data.get(i).get(0);
      String[] expectedExpressions = data.get(i).get(1).split(",");

      base.stylePage.selectMaterial(material);
      for (String expression: expectedExpressions) {
        base.stylePage.selectExpression(expression);
      }
    }
  }

  @Then("^when I select expression <a>, the available edge expressions should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected and the price should be <h>$")
  public void whenISelectExpressionATheAvailableEdgeExpressionsShouldBeBWithCDefaultSelectedTheAvailableThicknessesShouldBeDWithEDefaultSelectedTheAvailableSplashbacksShouldBeFWithGDefaultSelectedandThePriceShouldBeH(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String expression = data.get(i).get(0);
      String expectedEdges = data.get(i).get(1);
      String expectedDefaultEdge = data.get(i).get(2);
      String expectedThicknesses = data.get(i).get(3);
      String expectedDefaultThickness = data.get(i).get(4);
      String expectedSplashbacks = data.get(i).get(5);
      String expectedDefaultSplashback = data.get(i).get(6);
      String itemList = data.get(i).get(7);

      base.stylePage.selectExpression(expression);
      Assert.assertTrue(String.format("Edge %s was not default selected for expression %s", expectedDefaultEdge, expression), base.stylePage.isEdgeSelected(expectedDefaultEdge));
      Assert.assertTrue(String.format("Thickness %s was not default selected for expression %s", expectedDefaultThickness, expression), base.stylePage.isThicknessSelected(expectedDefaultThickness));

      for (String edge: StylePage.ALL_EDGES) {
        Assert.assertEquals(String.format("Wrong available edge %s for expression %s", edge, expression), expectedEdges.contains(edge.toLowerCase()), base.stylePage.isEdgeAvailable(edge));
      }
      for (String thickness: StylePage.ALL_THICKNESSES) {
        Assert.assertEquals(String.format("Wrong available thickness %s for expression %s", thickness, expression), expectedThicknesses.contains(thickness), base.stylePage.isThicknessAvailable(thickness));
      }
      for (String splashback : StylePage.ALL_SPLASHBACKS) {
        Assert.assertEquals(String.format("Wrong available splashback %s for expression %s", splashback, expression), expectedSplashbacks.contains(splashback.toLowerCase()), base.stylePage.isSplashbackAvailable(expectedDefaultSplashback));
      }
      base.evaluatePrice(itemList);
    }
  }

  @Then("^when selecting the same materials again the previous selected expressions should be remembered$")
  public void whenSelectingTheSameMaterialsAgainThePreviousSelectedExpressionsShouldBeRemembered() throws Throwable {
    for(int i = 1; i < currentScenarioData.size(); i++) {
      String material = currentScenarioData.get(i).get(0);
      String expectedSelectedExpression = currentScenarioData.get(i).get(1);

      base.stylePage.selectMaterial(material);
      Assert.assertTrue(String.format("Expression %s was not remembered for material %s", expectedSelectedExpression, material), base.stylePage.isExpressionSelected(expectedSelectedExpression));
    }
  }

  @Then("^when I select expression <a>, edge <b> and thickness <c> then the price should be <d> kr$")
  public void whenISelectExpressionAEdgeBAndThicknessCThenThePriceShouldBeDKr(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String expression = data.get(i).get(0);
      String edge = data.get(i).get(1);
      String thickness = data.get(i).get(2);
      String itemList = data.get(i).get(3);

      base.stylePage.selectExpression(expression);
      base.stylePage.selectEdge(edge);
      base.stylePage.selectThickness(thickness);
      base.evaluatePrice(itemList);
    }
  }

  @Then("^when I click the image of a material, expression, edge or thickness that is already selected an overlay image should be displayed$")
 public void whenIClickAMaterialExpressionEdgeOrThicknessThatIsAlreadySelectedAnOverlayImageShouldBeDisplayed() throws Throwable {
    base.stylePage.selectMaterial("laminate");
    base.stylePage.clickMaterialImage("laminate");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for material selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.stylePage.selectExpression("white laminate");
    base.stylePage.clickExpressionImage("white laminate");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for expression selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.stylePage.selectEdge("aluminium");
    base.stylePage.clickEdgeImage("aluminium");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for edge selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.stylePage.selectThickness("38");
    base.stylePage.clickThicknessImage("38");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for thickness selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();

    base.commonTopPage.scrollDown(1000);

    base.stylePage.selectSplashback("wall Panel");
    base.stylePage.clickSplashbackImage("wall Panel");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for splashback selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();

    base.stylePage.selectWallPanelMaterial("glass");
    base.stylePage.clickWallPanelMaterialImage("glass");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for wall panel material selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();

    base.stylePage.selectWallPanelExpression("black glass");
    base.stylePage.clickWallPanelExpressionImage("black glass");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for wall panel expression selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
  }

  @When("^I select (.*) material, (.*) expression and (.*) as thickness$")
  public void iSelectMaterialExpressionAndAsThickness(String material, String expression, String thickness) throws Throwable {
    base.stylePage.selectMaterial(material);
    base.stylePage.selectExpression(expression);
    base.stylePage.selectThickness(thickness);
  }

  @Then("^the selection should go back to (.*) material, (.*) expression and (.*) as thickness$")
  public void theSelectionShouldGoBackToMaterialExpressionAndAsThickness(String material, String expression, String thickness) throws Throwable {
    Assert.assertTrue(String.format("%s material was not selected", material), base.stylePage.isMaterialSelected(material));
    Assert.assertTrue(String.format("%s expression was not selected", expression), base.stylePage.isExpressionSelected(expression));
    Assert.assertTrue(String.format("%s thickness was not selected", thickness), base.stylePage.isThicknessSelected(thickness));
  }

  @When("^I select (.*) edge$")
  public void iSelectEdge(String edge) throws Throwable {
    base.stylePage.selectEdge(edge);
  }

  @When("^scrolling down to the (.*) style page selectors$")
  public void scrollingDownToTheSelectors(String selector) throws Throwable {
    base.stylePage.scrollToSelector(selector);
  }

  @Then("^material (.*), expression (.*) and thickness (.*) should be selected$")
  public void materialEAndExpressionFShouldBeSelected(String material, String expression, String thickness) throws Throwable {
    Assert.assertTrue(String.format("Material %s was not selected", material), base.stylePage.isMaterialSelected(material));
    Assert.assertTrue(String.format("Expression %s was not selected", expression), base.stylePage.isExpressionSelected(expression));
    Assert.assertTrue(String.format("Thickness %s was not selected", thickness), base.stylePage.isThicknessSelected(thickness));
  }

  @And("^I have selected worktop material (.*), expression (.*), edge (.*) and thickness (.*) in the style tab$")
  public void iHaveSelectedWorktopMaterialExpressionEdgeAndThicknessInTheStyleTab(String material, String expression, String edge, String thickness) throws Throwable {
    base.commonTopPage.navigateTo("style");
    base.stylePage.selectMaterial(material);
    base.stylePage.selectExpression(expression);
    base.stylePage.selectEdge(edge);
    base.stylePage.selectThickness(thickness);
  }

  @And("^worktop material (.*), expression (.*), edge (.*) and thickness (.*) should be selected$")
  public void worktopMaterialExpressionEdgeAndThicknessShouldBeSelected(String material, String expression, String edge, String thickness) throws Throwable {
    Assert.assertTrue(String.format("Material %s was not selected", material), base.stylePage.isMaterialSelected(material));
    Assert.assertTrue(String.format("Expression %s was not selected", expression), base.stylePage.isExpressionSelected(expression));
    Assert.assertTrue(String.format("Edge %s was not selected", edge), base.stylePage.isEdgeSelected(edge));
    Assert.assertTrue(String.format("Thickness %s was not selected", thickness), base.stylePage.isThicknessSelected(thickness));
  }

  @When("^I select thickness (.*)$")
  public void iSelectThicknessX(String thickness) throws Throwable {
    base.stylePage.selectThickness(thickness);
  }

  @Then("^the materials (.*) and (.*) should be disabled$")
  public void theMaterialsShouldBeDisabled(String materialA, String materialB) throws Throwable {
    Assert.assertFalse("Material " + materialA + " was not disabled", base.stylePage.isMaterialAvailable(materialA));
    Assert.assertFalse("Material " + materialB + " was not disabled", base.stylePage.isMaterialAvailable(materialB));
  }

  @Then("^the materials (.*) and (.*) should be enabled")
  public void theMaterialsShouldBeEnabled(String materialA, String materialB) throws Throwable {
    Assert.assertTrue("Material " + materialA + " was not disabled", base.stylePage.isMaterialAvailable(materialA));
    Assert.assertTrue("Material " + materialB + " was not disabled", base.stylePage.isMaterialAvailable(materialB));
  }

  @Then("^the expression (.*) should be displayed$")
  public void theExpressionShouldBeDisplayed(String expression) throws Throwable {
    Assert.assertTrue(String.format("Wrong expression selected", expression), base.stylePage.isExpressionSelected(expression));
  }

  @When("^I select (.*) splashback")
  public void iSelectSplashback(String splashback) throws Throwable {
    Thread.sleep(500);
    if (splashback.equals("no")) {
      Assert.assertTrue("No splashback button not found", base.stylePage.selectNoSplashback());
    }
    else {
      base.stylePage.selectSplashback(splashback);
    }
  }

  @When("^I select wall panel material (.*)")
  public void iSelectAsWallPanelMaterial(String material) throws Throwable {
    base.stylePage.selectWallPanelMaterial(material);
  }

  @When("^I select (.*) wall panel expression")
  public void iSelectWallPanelExpression(String expression) throws Throwable {
    base.stylePage.selectWallPanelExpression(expression);
  }

  @Then("^the splashback height is (\\d+) mm$")
  public void theSplashbackHeightIsMm(int expectedHeight) throws Throwable {
    Assert.assertEquals("Splashback height not as high as expected", expectedHeight, base.stylePage.getSplashbackHeight());
  }

  @When("^I set the splashback height to (\\d+) mm$")
  public void iSetTheSplashbackHeightToMm(int height) throws Throwable {
    base.stylePage.setSplashbackHeight(height);
  }

  @And("^I set the splashback to wall panel and material to (.*) and expression to (.*)$")
  public void iSetTheSplashbackToWallPanelAndMaterialToAAndExpressionToB(String material, String expression) throws Throwable {
    base.stylePage.selectSplashback("wall panel");
    base.stylePage.selectWallPanelMaterial(material);
    base.stylePage.selectWallPanelExpression(expression);
  }

  @And("^I set the splashback to wall panel and material to match worktop style$")
  public void iSetTheSplashbackToWallPanelAndMaterialToMatchWorktopStyle() throws Throwable {
    base.stylePage.selectSplashback("wall panel");
    Assert.assertTrue("Match worktop style button not found", base.stylePage.selectWallPanelMaterialMatch());
  }

  @Then("^when I set the measurement height to <a> it should display <b>$")
  public void whenISetTheMeasurementHeightToAItShouldDisplayB(String wallHeight, String bol) throws Throwable {
    int height= Integer.parseInt(wallHeight);
    base.stylePage.setSplashbackHeight(height);
  }

  @Then("^it (.*) display error message$")
  public void itBDisplayErrorMessage(String bol) throws Throwable {
    Boolean errorShouldBeDisplayed = bol.equalsIgnoreCase("should") ? true: false;
    if (errorShouldBeDisplayed) {
      Assert.assertTrue("Error message was not displayed for wall panel height",base.stylePage.isHeightErrorMessageDisplayed());
    }
    else {
      Assert.assertFalse("Error message was displayed for wall panel height when not expected",base.stylePage.isHeightErrorMessageDisplayed());
    }
  }

  @And("^select the OK button in the virtual keyboard$")
  public void selectTheOKButtonInTheVirtualKeyboard() throws Throwable {
    base.stylePage.selectVirtualKeyboardOkButton();
  }

  @And("^the wall panel height (.*) should be seen in the image$")
  public void theWallPanelHeightShouldBeSeenInTheImage(int height) throws Throwable {
    //base.commonTopPage.clickNextImage();
    Assert.assertTrue("Wall panel height is not displayed in image", base.stylePage.isWallPanelHeightDisplayed());
    Assert.assertEquals("No height received from wall panel", height, base.stylePage.getSplashbackHeightFromImage());
  }

  @When("^I mark the height of the splashback$")
  public void iMarkTheHeightOfTheSplashback() throws Throwable {
    Assert.assertTrue("The splashback height is not found", base.stylePage.markTheSplashbackHeight());
  }

  @And("^I have selected splashback (.*) with material (.*) and expression (.*) in the style tab$")
  public void iHaveSelectedSplashbackSplashbackWithMaterialWallPanelMaterialAndExpressionWallPanelExpressionInTheStyleTab(String splashback, String material, String expression) throws Throwable {
    if (!splashback.equalsIgnoreCase("none")) {
      base.stylePage.selectSplashback(splashback);
      if (!splashback.equalsIgnoreCase("wall edging strip")) {
        base.stylePage.selectWallPanelMaterial(material);
        base.stylePage.selectWallPanelExpression(expression);
      }
    }
  }

  @And("^splashback (.*) with material (.*) and expression (.*) should be selected$")
  public void splashbackWithMaterialAndExpressionShouldBeSelected(String splashback,String material, String expression) throws Throwable {
    Assert.assertTrue(String.format("Splashback %s was not selected", splashback), base.stylePage.isSplashbackOptionSelected(splashback));
    Assert.assertTrue(String.format("Material %s was not selected", material), base.stylePage.isWpMaterialSelected(material));
    Assert.assertTrue(String.format("Expression %s was not selected", expression), base.stylePage.isWpExpressionSelected(expression));

  }

  @And("^the label should be set to (.*) in pictogram$")
  public void theLabelShouldBeSetToAInPictogram(int a) throws Throwable {
    Assert.assertTrue("Pictogram height does not have its expected value: " + a, base.stylePage.isPictogramHeightSetToValue(a));
  }

  @Then("^when I select expression <a>, the available edge options should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected$")
  public void whenISelectExpressionATheAvailableEdgeOptionsShouldBeBWithCDefaultSelectedTheAvailableThicknessesShouldBeDWithEDefaultSelectedTheAvailableSplashbackShouldBeFWithGDefaultSelected(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String expression = data.get(i).get(0);
      String expectedEdgeOption = data.get(i).get(1);
      String expectedDefaultEdgeOption = data.get(i).get(2);
      String expectedThicknesses = data.get(i).get(3);
      String expectedDefaultThickness = data.get(i).get(4);
      String expectedSplashbacks = data.get(i).get(5);
      String expectedDefaultSplashback = data.get(i).get(6);
      //String itemList = data.get(i).get(7);

      base.stylePage.selectExpression(expression);
      Assert.assertTrue(String.format("Edge option %s was not default selected for expression %s", expectedDefaultEdgeOption, expression), base.stylePage.isEdgeOptionSelected(expectedDefaultEdgeOption));
      Assert.assertTrue(String.format("Thickness %s was not default selected for expression %s", expectedDefaultThickness, expression), base.stylePage.isThicknessSelected(expectedDefaultThickness));
      Assert.assertTrue(String.format("Splashback %s was not default selected for expression %s", expectedDefaultSplashback, expression), base.stylePage.isSplashbackSelected(expectedDefaultSplashback));

      for (String edgeOption : StylePage.ALL_EDGE_OPTIONS) {
        Assert.assertEquals(String.format("Wrong available edge %s for expression %s", edgeOption, expression), expectedEdgeOption.contains(edgeOption.toLowerCase()), base.stylePage.isEdgeOptionAvailable(expectedDefaultEdgeOption));
      }
      for (String thickness : StylePage.TAIWAN_THICKNESSES) {
        Assert.assertEquals(String.format("Wrong available thickness %s for expression %s", thickness, expression), expectedThicknesses.contains(thickness), base.stylePage.isThicknessAvailable(expectedDefaultThickness));
      }
      for (String splashback : StylePage.TAIWAN_SPLASHBACKS) {
        Assert.assertEquals(String.format("Wrong available splashback %s for expression %s", splashback, expression), expectedSplashbacks.contains(splashback.toLowerCase()), base.stylePage.isSplashbackAvailable(expectedDefaultSplashback));
      }
      //base.evaluatePrice(itemList);
    }
  }

  @When("^I remove the height of the splashback$")
  public void iRemoveTheHeightOfTheSplashback() throws Throwable {
    base.stylePage.removeSplashbackHeightWithVirtualKeyboard();
  }

  @When("^scrolling to the (.*) style selector$")
  public void scrollingToTheSelector(String selector) throws Throwable {
    base.stylePage.scrollToSelector(selector);
  }
}
