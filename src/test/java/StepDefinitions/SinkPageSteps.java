package StepDefinitions;

import PageObjects.SinkPage;
import Support.Range;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

public class SinkPageSteps {
  private BaseTestClass base;

  public SinkPageSteps(BaseTestClass base) {
    this.base = base;
  }

  @Given("^I have selected (.*) material for the sink$")
  public void iHaveSelectedMaterialForTheSink(String material) throws Throwable {
    base.sinkPage.selectMaterial(material);
  }

  @When("^I select model (.*)$")
  public void iSelectModel(String model) throws Throwable {
    base.sinkPage.selectModel(model);
  }

  @When("^I select fastening (.*)$")
  public void iSelectFastening(String fastening) throws Throwable {
    base.sinkPage.selectFastening(fastening);
  }

  @Then("^when I select sink material <a> the default sink should be <b> and the default fastening should be <c>$")
  public void whenISelectSinkMaterialATheDefaultSinkShouldBeBAndTheDefaultFasteningShouldBeC(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String material = data.get(i).get(0);
      String expectedDefaultModel = data.get(i).get(1);
      String expectedDefaultFastening = data.get(i).get(2);

      base.sinkPage.selectMaterial(material);
      if(material.equalsIgnoreCase("No sink")) {
        Thread.sleep(1000);
        Assert.assertFalse("A Model was wrongly selected for material " + material, base.sinkPage.isAnyModelSelected());
        Assert.assertFalse("A Fastening was wrongly selected for material " + material, base.sinkPage.isAnyFasteningSelected());
      } else {
        Assert.assertTrue("Wrong default model for sink material " + material, base.sinkPage.isModelSelected(expectedDefaultModel));
        Assert.assertTrue("Wrong default fastening for sink material " + material, base.sinkPage.isFasteningSelected(expectedDefaultFastening));
      }
    }
  }

  @Then("^when I select sink material <a> the the available models should be <b>$")
  public void whenISelectSinkMaterialATheTheAvailableModelsShouldBeB(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String material = data.get(i).get(0);
      String[] expectedModels = data.get(i).get(1).split(",");

      base.sinkPage.selectMaterial(material);
      for (String model: expectedModels) {
        base.sinkPage.selectModel(model);
      }
    }
  }


  @Then("^when I select model <a>, the available fastenings should be <b> with <c> default selected, there should be default <d> tapholes selected and the price should be <e>$")
  public void whenISelectModelATheAvailableFasteningsShouldBeBWithCDefaultSelectedThereShouldBeDefaultDTapholesSelectedAndThePriceShouldBeE(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String model = data.get(i).get(0);
      String expectedFastenings = data.get(i).get(1);
      String expectedDefaultFastening = data.get(i).get(2);
      int expectedNbrOfTapHoles = Integer.parseInt(data.get(i).get(3));
      String itemList = data.get(i).get(4);

      base.sinkPage.selectModel(model);
      for (String fastening: SinkPage.ALL_FASTENINGS) {
        Assert.assertEquals(String.format("Wrong available fastenings %s for model %s", fastening, model), expectedFastenings.contains(fastening), base.sinkPage.isFasteningAvailable(fastening));
      }
      Assert.assertTrue(String.format("Fastening %s was not default selected for model %s", expectedDefaultFastening, model), base.sinkPage.isFasteningSelected(expectedDefaultFastening));
      Assert.assertEquals("Wrong number of tap holes for model " + model, expectedNbrOfTapHoles, base.sinkPage.getNbrOfTapHoles());

      base.evaluatePrice(itemList);
    }
  }

  @Then("^when I add <a> tap holes the price should be <b>$")
  public void whenIAddATapHolesThePriceShouldBeB(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      int nbrOfTapHoles = Integer.parseInt(data.get(i).get(0));
      String itemList = data.get(i).get(1);

      base.sinkPage.addTapHoles(nbrOfTapHoles);
      Assert.assertEquals("Incorrect number of tap holes", nbrOfTapHoles, base.sinkPage.getNbrOfTapHoles());
      Assert.assertEquals("Incorrect price for tap holes", nbrOfTapHoles*Range.getPrice("(00345573 Tap Hole Laminate)"), base.sinkPage.getPriceOfTapHoles());
      base.evaluatePrice(itemList);
      for (int j = 0; j < nbrOfTapHoles; j++) {
        base.sinkPage.removeTapHole();
      }
    }
  }

  @Then("^when I click the image of a material, model, fastening or taphole that is already selected an overlay image should be displayed$")
  public void whenIClickTheImageOfAMaterialModelFasteningOrTapholeThatIsAlreadySelectedAnOverlayImageShouldBeDisplayed() throws Throwable {
    base.sinkPage.selectMaterial("steel");
    base.sinkPage.clickMaterialImage("steel");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for sink material selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.sinkPage.selectModel("LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm");
    base.sinkPage.clickModelImage("LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for model selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.sinkPage.selectFastening("underGlued");
    base.sinkPage.clickFasteningImage("underGlued");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for fastening selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.sinkPage.clickTapHoleImage();
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for taphole selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
  }

  @Then("^when I select model <a> and fastening <b> the price should be <c> kr$")
  public void whenISelectModelAAndFasteningBThePriceShouldBeCKr(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String model = data.get(i).get(0);
      String fastening = data.get(i).get(1);
      String itemList = data.get(i).get(2);

      base.sinkPage.selectModel(model);
      base.sinkPage.selectFastening(fastening);
      base.evaluatePrice(itemList);
    }
  }

  @When("^I select material (.*), model (.*) and fastening (.*)")
  public void iSelectMaterialModelAndFastening(String material, String model, String fastening) throws Throwable {
    base.sinkPage.selectMaterial(material);
    base.sinkPage.selectModel(model);
    base.sinkPage.selectFastening(fastening);
  }

  @When("^scrolling down to the (.*) sink page selectors$")
  public void scrollingDownToTheSinkPageSelectors(String selector) throws Throwable {
    base.sinkPage.scrollToSelector(selector);
  }

  @And("^I select drainer grooves$")
  public void iAddDrainerGrooves() throws Throwable {
    base.sinkPage.selectDrainerGrooves();
  }

  @And("^I select sink material (.*)")
  public void iSelectSinkMaterial(String material) throws Throwable {
    base.sinkPage.selectMaterial(material);
  }

  @Then("^when I select material <a> and model <b> and fastening <c>, <d> should be available$")
  public void whenISelectMaterialAAndModelBAndFasteningCDShouldBeAvailable(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String material = data.get(i).get(0);
      String model = data.get(i).get(1);
      String fastening = data.get(i).get(2);
      boolean drainerGrooves = data.get(i).get(3).equalsIgnoreCase("drainer grooves") ? true : false;

      base.sinkPage.selectMaterial(material);
      base.sinkPage.selectModel(model);
      base.sinkPage.selectFastening(fastening);
      Assert.assertSame(String.format("Drainer grooves availability not correct material %s, model %s and fastening %s", material, model, fastening), drainerGrooves, base.sinkPage.isDrainerGroovesAvailable());
    }
  }

  @Then("^when I select material <a>, model <b>, fastening <c> and add drainer grooves the price should be <d>$")
  public void whenISelectMaterialAModelBFasteningCAndAddDrainerGroovesThePriceShouldBeD(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String material = data.get(i).get(0);
      String model = data.get(i).get(1);
      String fastening = data.get(i).get(2);
      String itemList = data.get(i).get(3);

      base.sinkPage.selectMaterial(material);
      base.sinkPage.selectModel(model);
      base.sinkPage.selectFastening(fastening);
      base.sinkPage.selectDrainerGrooves();
      base.evaluatePrice(itemList);
    }
  }

  @And("^I have selected sink material (.*), model (.*), fastening (.*), drainer grooves (.*) and (.*) tap holes in the sink tab$")
  public void iHaveSelectedSinkMaterialModelFasteningDrainerGroovesAndNbrOfTapHolesInTheSinkTab(String material, String model, String fastening, String drainerGrooves, String tapholes) throws Throwable {
    base.commonTopPage.navigateTo("sink");
    if (!material.equalsIgnoreCase("no sink")) {
      base.sinkPage.selectMaterial(material);
      base.sinkPage.selectModel(model);
      base.sinkPage.selectFastening(fastening);
      if (drainerGrooves.equalsIgnoreCase("yes")) {
        base.sinkPage.selectDrainerGrooves();
      }
    }
    base.sinkPage.addTapHoles(Integer.parseInt(tapholes));
  }

  @When("^I click the info icon for the (.*) fastening$")
  public void iClickTheInfoIconForTheAFastening(String fastening) throws Throwable {
    base.sinkPage.displayFasteningInfoDialog(fastening);
  }

  @Then("^a info dialog should be displayed with more information about the fastening$")
  public void aInfoDialogShouldBeDisplayedWithMoreInformationAboutTheFastening() throws Throwable {
    Assert.assertTrue("Info dialog was not displayed", base.sinkPage.isInfoDialogDisplayed());
  }

  @And("^clicking the X mark in the dialog should close it$")
  public void clickingTheXMarkInTheDialogShouldCloseIt() throws Throwable {
    base.sinkPage.closeInfoDialog();
  }

  @Then("^model (.*), fastening (.*), drainer grooves (.*) and (.*) tap holes should be selected$")
  public void modelBFasteningCDrainerGroovesDAndETapHolesShouldBeSelected(String model, String fastening, String drainerGrooves, String tapHoles) throws Throwable {
    Assert.assertTrue("Wrong model selected", base.sinkPage.isModelSelected(model));
    Assert.assertTrue("Wrong fastening selected", base.sinkPage.isFasteningSelected(fastening));
    boolean drainerGroovesExpectedSelected = drainerGrooves.equalsIgnoreCase("yes") ? true : false;
    Assert.assertEquals("Wrong drainer grooves selected", drainerGroovesExpectedSelected, base.sinkPage.isDrainerGroovesSelected());
    Assert.assertEquals("Wrong number of tap holes", Integer.parseInt(tapHoles), base.sinkPage.getNbrOfTapHoles());
  }

  @And("^I have selected material (.*) and model (.*)$")
  public void iHaveSelectedMaterialAAndModelB(String material, String model) throws Throwable {
    base.sinkPage.selectMaterial(material);
    base.sinkPage.selectModel(model);
  }

  @Then("^sink material (.*), model (.*), fastening (.*), drainer grooves (.*) should be selected and (.*) number of tap holes should have been added$")
  public void sinkMaterialModelFasteningDrainerGroovesShouldBeSelectedAndNumberOfTapHolesShouldHaveBeenAdded(String material, String model, String fastening, String drainerGrooves, String tapHoles) throws Throwable {
    Assert.assertTrue("Wrong material selected", base.sinkPage.isMaterialSelected(material));
    if(!material.equalsIgnoreCase("No sink")) {
      Assert.assertTrue("Wrong model selected", base.sinkPage.isModelSelected(model));
      Assert.assertTrue("Wrong fastening selected", base.sinkPage.isFasteningSelected(fastening));
      boolean drainerGroovesSelected = drainerGrooves.equalsIgnoreCase("yes") ? true : false;
      Assert.assertEquals("Wrong drainer grooves selected", drainerGroovesSelected, base.sinkPage.isDrainerGroovesSelected());
    } else {
      Assert.assertFalse("Model was wrongly selected", base.sinkPage.isAnyModelSelected());
      Assert.assertFalse("Fastening was wrongly selected", base.sinkPage.isAnyFasteningSelected());
      Assert.assertFalse("Drainer groove was wrongly selected", base.sinkPage.isDrainerGroovesSelected());
    }
    Assert.assertEquals("Wrong number of tap holes", Integer.parseInt(tapHoles), base.sinkPage.getNbrOfTapHoles());
  }


  @And("^no sink should be selected")
  public void noSinkShouldBeDisplayed() throws Throwable {
    Assert.assertTrue("Sink still available",base.sinkPage.isNoSinkSelected());
  }

  @And("^the selected material is (.*) and model is (.*)$")
  public void theSelectedMaterialIsAndModelIs(String material, String sinkModel) throws Throwable {
    Assert.assertTrue("Wrong material selected", base.sinkPage.isMaterialSelected(material));
    Assert.assertTrue("Wrong sink model selected", base.sinkPage.isModelSelected(sinkModel));
  }

  @Then("^the fastening should be (.*)$")
  public void theFasteningShouldBe(String fastening) throws Throwable {
    Assert.assertTrue("Fastening is not as expected", base.sinkPage.isFasteningSelected(fastening));
  }

  @Then("^the image should be missing and a pictogram should be displayed$")
  public void theImageShouldBeMissingAndAPictogramShouldBeDisplayed() throws Throwable {
    Assert.assertTrue("The missing sink image is not displayed", base.sinkPage.isSinkImageMissingAvailable());
    Assert.assertTrue("The sink pictogram is not displayed", base.sinkPage.isSinkPictogramAvailable());
  }

  @When("^I select sink model (.*)$")
  public void iSelectSinkModel(String model) throws Throwable {
    base.sinkPage.selectModel(model);
  }
}
