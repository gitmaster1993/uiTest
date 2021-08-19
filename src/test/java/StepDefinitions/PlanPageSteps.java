package StepDefinitions;

import PageObjects.PlanPage;
import Support.Range;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanPageSteps {
  private BaseTestClass base;

  public PlanPageSteps(BaseTestClass base) {
    this.base = base;
  }

  @When("^I select shape (.*)$")
  public void iSelectShape(String shape) {
    base.currentShape = shape;
    base.planPage.selectShape(shape);
  }

  @Then("^the default shape should be (.*)$")
  public void theDefaultShapeShouldBe(String shape)  {
    Assert.assertTrue("Wrong default shape", base.planPage.isShapeSelected(shape));
  }

  @When("^I press the flip button the shape should be flipped$")
  public void iPressTheFlipButtonTheShapeShouldBeFlipped() {
    base.planPage.flipShape(base.currentShape, PlanPage.ShapeOption.FLIPPED);
    base.planPage.flipShape(base.currentShape, PlanPage.ShapeOption.NORMAL);

  }

  @Then("^when I select shape <a> the default measurement should be <b>$")
  public void whenISelectShapeATheDefaultMeasurementForShapeAShouldBeB(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String shape = data.get(i).get(0);
      String expectedDefaultMeasurements = data.get(i).get(1);

      base.planPage.selectShape(shape);
      String measurement = base.planPage.getDefaultMeasurementForShape(shape);
      Assert.assertEquals("Wrong default measurement", expectedDefaultMeasurements, measurement);
    }
  }

  @Then("^when I click the image of a shape or wall selection that is already selected an overlay image should be displayed$")
  public void whenIClickAShapeOrWallSelectionThatIsAlreadySelectedAnOverlayImageShouldBeDisplayed() throws Throwable {
    base.planPage.selectShape("l-shape");
    base.planPage.clickShapeImage("l-shape");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for shape selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.planPage.setWallSelection("three walls left");
    base.planPage.clickWallImage("three walls left");
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for wall selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
  }

  @Then("^when I set the measurement A to <a> and B to <b> it should display <c>$")
  public void whenISetMeasurementAToAAndMeasurementBToBItShouldDisplayC(DataTable dataTable) throws Throwable {
    setMeasurementAndCheckError(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a>, B to <b> and C to <c> it should display <d>$")
  public void whenISetTheMeasurementAToABToBAndCToCItShouldDisplayD(DataTable dataTable) throws Throwable {
    setMeasurementAndCheckError(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a>, B to <b>, C to <c> and D to <d> it should display <e>$")
  public void whenISetMeasurementAToABToBCToCAndDToDItShouldDisplayE(DataTable dataTable) throws Throwable {
    setMeasurementAndCheckError(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a>, B to <b>, C to <c>, D to <d>, E to <e> and F to <f> it should display <g>$")
  public void whenISetMeasurementAToABToBCToCDToDEToEAndFToFItShouldDisplayG(DataTable dataTable) throws Throwable {
    setMeasurementAndCheckError(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a> and B to <b> the price should be <c> kr$")
  public void whenISetTheMeasurementAToAAndMeasurementBToBThePriceShouldBeC(DataTable dataTable) throws Throwable {
    setMeasurementsAndCheckPrice(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a>, B to <b> and C to <c> the price should be <d> kr$")
  public void whenISetTheMeasurementAToABToBAndCToCThePriceShouldBeDKr(DataTable dataTable) throws Throwable {
    setMeasurementsAndCheckPrice(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a>, B to <b>, C to <c> and D to <d> the price should be <e> kr$")
  public void whenISetTheMeasurementAToABToBCToCAndDToDThePriceShouldBeEKr(DataTable dataTable) throws Throwable {
    setMeasurementsAndCheckPrice(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a>, B to <b>, C to <c>, D to <d>, E to <e> and F to <f> the price should be <e> kr$")
  public void whenISetTheMeasurementAToABToBCToCDToDEToEAndFToFThePriceShouldBeEKr(DataTable dataTable) throws Throwable {
    setMeasurementsAndCheckPrice(base.currentShape, dataTable);
  }

  @And("^I set the measurements A to (.*) and B to (.*)$")
  public void iSetTheMeasurementsAToAndBTo(String A, String B) throws Throwable {
    List<String> measurements = new ArrayList<String>();
    measurements.add(A);
    measurements.add(B);
    base.planPage.setShapeMeasurement(base.currentShape, measurements);
    Thread.sleep(500);
  }

  @And("^I set the measurements A to (.*), B to (.*) and C to (.*)$")
  public void iSetTheMeasurementsAToBToAndCTo(String A, String B, String C) throws Throwable {
    List<String> measurements = new ArrayList<String>();
    measurements.add(A);
    measurements.add(B);
    measurements.add(C);
    base.planPage.setShapeMeasurement(base.currentShape, measurements);
  }

  @And("^I set the measurements A to (.*), B to (.*), C to (.*) and D to (.*)$")
  public void iSetTheMeasurementsAToBToCToAndDTo(String A, String B, String C, String D) throws Throwable {
    List<String> measurements = new ArrayList<String>();
    measurements.add(A);
    measurements.add(B);
    measurements.add(C);
    measurements.add(D);
    base.planPage.setShapeMeasurement(base.currentShape, measurements);
  }

  @And("^I set the measurements A to (.*), B to (.*), C to (.*), D to (.*), E to (.*) and F to (.*)$")
  public void iSetTheMeasurementsAToBToCToDToEToAndFTo(String A, String B, String C, String D, String E, String F) throws Throwable {
    List<String> measurements = new ArrayList<String>();
    measurements.add(A);
    measurements.add(B);
    measurements.add(C);
    measurements.add(D);
    measurements.add(E);
    measurements.add(F);
    base.planPage.setShapeMeasurement(base.currentShape, measurements);
  }

  @Then("^when I set wall selection to (.*) the price should be (.*)$")
  public void whenISetWallSelectionToWallsThePriceShouldBePrice(String nrOfWalls, String price) throws Throwable {
    base.planPage.setWallSelection(nrOfWalls);
    base.evaluatePrice(price);
  }

  @Given("^I have selected shape (.*) with measurement (.*) and wall selection (.*) in the plan tab$")
  public void iHaveSelectedShapeWithMeasurementAndWallSelectionInThePlanTab(String shape, String measurement, String wallSelection) throws Throwable {
    base.commonTopPage.navigateTo("plan");
    base.planPage.selectShape(shape);
    List<String> measurements = Arrays.asList(measurement.split(","));
    base.planPage.setShapeMeasurement(shape, measurements);
    base.planPage.setWallSelection(wallSelection);
  }

  @Then("^shape (.*) should be selected with measurements (.*)$")
  public void shapeGShouldBeSelectedWithMeasurements(String shape, String measurements) throws Throwable {
    Assert.assertTrue("Wrong shape selected", base.planPage.isShapeSelected(shape));
    Assert.assertEquals("Wrong measurements", measurements, base.planPage.getDefaultMeasurementForShape(shape));
  }

  @Then("^shape (.*) and wall selection (.*) should be selected and the measurement for the shape should be (.*)$")
  public void shapeAndWallSelectionShouldBeSelectedAndTheMeasurementForTheShapeShouldBe(String shape, String wallSelection, String measurement) throws Throwable {
    Assert.assertTrue("Wrong shape selected", base.planPage.isShapeSelected(shape));
    Assert.assertTrue("Wrong wall selection selected", base.planPage.isWallLocationSelected(wallSelection));
    Assert.assertEquals("Wrong measurement for shape", measurement, base.planPage.getMeasurementForShape(shape));
  }

  @When("^I click in field (.*) for shape (.*)")
  public void iClickInFieldForShapeRectangular(String field, String shape) throws Throwable {
    Thread.sleep(1000);
    base.planPage.clickMeasurementField(shape, field);
  }

  @When("^I click in field (.*) for kitchen island$")
  public void iClickInFieldAForKitchenIsland(String field) throws Throwable {
    base.planPage.clickKitchenMeasurementField(field);
  }

  @Then("^a numerical virtual keyboard should be displayed$")
  public void aNumericalVirtualKeyboardShouldBeDisplayed() throws Throwable {
    Thread.sleep(500);
    Assert.assertTrue("Virtual keyboard was not displayed", base.planPage.isVirtualKeyboardDisplayed());
  }

  @And("^when entering values (.*) with the virtual keyboard$")
  public void whenEnteringValuesWithTheVirtualKeyboard(String measurement) throws Throwable {
    base.planPage.enterMeasurementFromVirtualKeyboard(measurement);
  }

  @Then("^the measurements for shape (.*) should be set to (.*)$")
  public void theMeasurementsForShapeShouldBeSetTo(String shape, String measurements) throws Throwable {
    Assert.assertEquals("Wrong measurements", measurements, base.planPage.getMeasurementForShape(shape));
  }

  private void setMeasurementAndCheckError(String shapeName, DataTable dataTable) throws InterruptedException {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      ArrayList<String> measurements = new ArrayList<>();
      measurements.addAll(data.get(i));
      Boolean errorShouldBeDisplayed = measurements.get(measurements.size()-1).equalsIgnoreCase("Error Message") ? true: false;
      measurements.remove(measurements.size()-1);
      base.planPage.setShapeMeasurement(shapeName, measurements);
      Thread.sleep(1000);
      if(errorShouldBeDisplayed != base.planPage.measurementErrorMessageDisplayed()) {
        String errorMessage = errorShouldBeDisplayed ? "Error message was not displayed for measurement " : "Error message was wrongly displayed for measurement ";
        Assert.fail(errorMessage + measurements.toString());
      }
    }
  }

  private void setMeasurementAndCheckErrorAndBackground(String shapeName, DataTable dataTable) throws InterruptedException {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      ArrayList<String> measurements = new ArrayList<>();
      measurements.addAll(data.get(i));
      Boolean errorShouldBeDisplayed = measurements.get(measurements.size()-1).equalsIgnoreCase("Error Message") ? true: false;
      measurements.remove(measurements.size()-1);
      base.planPage.setShapeMeasurement(shapeName, measurements);
      Thread.sleep(1000);
      if(errorShouldBeDisplayed != base.planPage.measurementErrorMessageDisplayedAndRedBackground()) {
        String errorMessage = errorShouldBeDisplayed ? "Error message was not displayed for measurement " : "Error message was wrongly displayed for measurement ";
        Assert.fail(errorMessage + measurements.toString());
      }
    }
  }

  private void setMeasurementsAndCheckPrice(String shapeName, DataTable dataTable) throws InterruptedException {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      ArrayList<String> list = new ArrayList<>();
      list.addAll(data.get(i));
      String itemList = list.get(list.size()-1);
      BigDecimal expectedPrice = Range.evaluatePrice(itemList);
      list.remove(list.size() - 1);

      base.planPage.setShapeMeasurement(shapeName, list);
      base.evaluatePrice(itemList);
    }
  }

  private void setWallSelectionAndCheckPrice(DataTable dataTable) throws InterruptedException {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      String wallSelection = data.get(i).get(0);
      String itemList = data.get(i).get(1);

      base.planPage.setWallSelection(wallSelection);
      base.evaluatePrice(itemList);
    }
  }

  @When("^I put the marker in the measurement field for (.*) without writing$")
  public void iPutTheMarkerInTheMeasurementFieldForWithoutWriting(String shapeName) throws Throwable {
    base.planPage.putMarkerInField(shapeName);
  }

  @When("^I set wall selection to (.*)$")
  public void iSetWallSelectionTo(String wall) throws Throwable {
    base.planPage.setWallSelection(wall);
    Assert.assertTrue("Wrong wall selection selected",base.planPage.isWallLocationSelected(wall));
  }

  @Then("^the measurement for shape (.*) should be (.*)$")
  public void theMeasurementsForShapeShouldBe(String shape, String measurements) throws Throwable {
    Assert.assertEquals("Wrong measurements received", measurements, base.planPage.getMeasurementForShape(shape));
  }

  @Then("^when I set the measurement A to <a>, B to <b>, C to <c>, D to <d>, E to <e> and F to <f> it should display <g> and background$")
  public void whenISetTheMeasurementAToABToBCToCDToDEToEAndFToFItShouldDisplayGAndBackground(DataTable dataTable) throws Throwable {
    setMeasurementAndCheckErrorAndBackground(base.currentShape, dataTable);
  }

  @Then("^when I set the measurement A to <a> and B to <b> it should display <c> and background$")
  public void whenISetTheMeasurementAToAAndBToBItShouldDisplayCAndBackground(DataTable dataTable) throws Throwable {
    setMeasurementAndCheckErrorAndBackground(base.currentShape, dataTable);
  }

  @And("^I set the kitchen island measurements A to (\\d+) and B to (\\d+)$")
  public void iSetTheKitchenIslandMeasurementsAToAndBTo(int a, int b) throws Throwable {
    base.planPage.setKitchenIslandMeasurements(a, b);
  }

  @When("^I set the kitchen island measurements A to <a> and B to <b> it should display <c>$")
  public void iSetTheKitchenIslandMeasurementsAToAAndBToBItShouldDisplayC(DataTable dataTable) throws Throwable {
    List<List<String>> data = dataTable.raw();
    for(int i = 1; i < data.size(); i++) {
      ArrayList<String> measurements = new ArrayList<>();
      measurements.addAll(data.get(i));
      Boolean errorShouldBeDisplayed = measurements.get(measurements.size() - 1).equalsIgnoreCase("Error Message") ? true : false;
      measurements.remove(measurements.size() - 1);
      base.planPage.setKitchenIslandMeasurements(measurements);
      Thread.sleep(1000);
      if (errorShouldBeDisplayed != base.planPage.measurementErrorMessageDisplayed()) {
        String errorMessage = errorShouldBeDisplayed ? "Error message was not displayed for measurement " : "Error message was wrongly displayed for measurement ";
        Assert.fail(errorMessage);
      }
      if (errorShouldBeDisplayed) {
        Assert.assertEquals(String.format("Wrong number of conflicts indicated in the kitchen island tab"), 1, base.commonTopPage.getNbrOfManualConflicts("kitchen-island"));
      }
    }
  }

  private void isZeroDisplayed(String values) {
    String[] ary = values.split(",");
    int length = ary.length;
    Boolean bol = false;
    for (int i=0; i<length; i++) {
      if (ary[i].equals("0")) {
        bol = true;
      }
    }
    if (bol.equals(false)) {
      Assert.fail("0 is not displayed any more");
    }
  }

  @Then("^the error message after entering zero should be available for shape (.*)$")
  public void theErrorMessageAfterEnteringZeroShouldBeAvailableForShape(String shape) throws Throwable {
    if (!base.planPage.measurementErrorMessageDisplayed()) {
      Assert.fail("Error message was not displayed for measurement ");
    }
    String values = base.planPage.getMeasurementForShape(shape);
    isZeroDisplayed(values);
  }

  @And("^the error message after entering zero should be available for kitchen island$")
  public void theErrorMessageAfterEnteringZeroShouldBeAvailableForKitchenIsland() throws Throwable {
    if (!base.planPage.measurementErrorMessageDisplayed()) {
      Assert.fail("Error message was not displayed for measurement ");
    }
    String values = base.planPage.getMeasurementForKitchenIsland();
    isZeroDisplayed(values);
  }

  @And("^the default overhang type is no overhang$")
  public void theDefaultOverhangTypeIsNoOverhang() throws Throwable {
    Assert.assertTrue("No overhang is not default selected", base.planPage.isNoOverhangSelected());
  }

  @Then("^when walls are set to (.*) the following overhang types (.*) should be available$")
  public void whenWallsAreSetToWallsTheFollowingOverhangTypesAvailableOverhangsShouldBeAvailable(String walls, String list) throws Throwable {
    String[] expectedOverhang = list.split(", ");
    base.planPage.selectWalls(walls);
    base.planPage.scrollToSelector("overhang type");
    if (list.equals("none")) {
      base.planPage.selectNoOverhang();
    }
    else {
      for (String overhang: expectedOverhang) {
        base.planPage.selectOverhang(overhang);
      }
    }
  }

  @And("^I select (.*) as walls and (.*) as overhang$")
  public void iSelectAsWallsAndAsOverhang(String walls, String overhang) throws Throwable {
    base.planPage.selectWalls(walls);
    //base.planPage.scrollToSelector("overhang type");
    base.planPage.selectOverhang(overhang);
  }

  @When("^I select (.*) as overhang side$")
  public void iSelectAsOverhangSide(String side) throws Throwable {
    base.planPage.selectOverhangSide(side);
  }

  /*@Then("^the overhang size is set to (\\d+) mm and (\\d+) mm$")
  public void theOverhangSizeIsSetToMm(int size1, int size2) throws Throwable {
    String overhangSide = base.planPage.getOverhangSideSelection();
    base.planPage.setOverhangSize(overhangSide, size1, size2);
  }*/

  @When("^I select (.*) as overhang side and I click the overhang side size$")
  public void iSelectOneSideLeftAsOverhangSideAndIClickTheOverhangSideSize(String side) throws Throwable {
    base.planPage.selectOverhangSide(side);
    Assert.assertTrue("The overhang side size is not found", base.planPage.markTheOverhangSideSize(side));

  }

  @Then("^the virtual keyboard is visible so I can enter the overhang size (.*) mm$")
  public void theVirtualKeyboardIsVisibleSoICanEnterTheOverhangSizeMm(String size) throws Throwable {
    Thread.sleep(500);
    Assert.assertTrue("Virtual keyboard was not displayed", base.commonTopPage.isVirtualKeyboardDisplayed());
    base.planPage.enterSizeWithVirtualKeyboard(size);
  }

  @Then("^when I select (.*) as overhang side and I set overhang side measurements to (.*) it should display (.*) with (.*) errors found$")
  public void whenISelectOneSideBottomAsOverhangSideAndISetOverhangSideMeasurementsToZItShouldDisplayErrorMessage(String overhangSide, int size, String message, int nrOfErrors) throws Throwable {
    Assert.assertTrue( overhangSide +" is not selected" , base.planPage.isOverhangSideSelected(overhangSide));
    Boolean errorShouldBeDisplayed = message.equalsIgnoreCase("Error Message") ? true : false;
    Assert.assertTrue("Overhang size field not set", base.planPage.setOverhangSize(overhangSide, size));
    Thread.sleep(1000);
    if (errorShouldBeDisplayed != base.planPage.sizeErrorMessageDisplayed()) {
      String errorMessage = errorShouldBeDisplayed ? "Error message was not displayed for measurement " : "Error message was wrongly displayed for measurement ";
      Assert.fail(errorMessage);
    }
    if (errorShouldBeDisplayed) {
      Assert.assertEquals(String.format("Wrong number of conflicts indicated in the plan tab"), nrOfErrors, base.commonTopPage.getNbrOfManualConflicts("plan"));
    }
  }

  @Then("^(.*) is selected as overhang side$")
  public void isSelectedAsOverhangSide(String overhangSide) throws Throwable {
    Assert.assertTrue(overhangSide +" is not selected" , base.planPage.isOverhangSideSelected(overhangSide));
  }

  @When("^I set (.*) as wall location$")
  public void iSetAsWallLocation(String walls) throws Throwable {
    base.planPage.setWallSelection(walls);
  }

  @Then("^when I set the overhang type to (.*) the overhang sides should be (.*)$")
  public void whenISetTheOverhangTypeToOverhangTheOverhangSidesShouldBeOverhangSides(String overhang, String overhangSides ) throws Throwable {
    if (overhang.equals("none")) {
      base.planPage.selectNoOverhang();
    }
    else {
      base.planPage.setOverhangType(overhang);
      String[] expectedOverhangSide = overhangSides.split(", ");
      for (String overhangSide: expectedOverhangSide) {
        base.planPage.selectOverhangSide(overhangSide);
      }
    }
  }

  @And("^the waterfall length should be displayed for the overhang side (.*)$")
  public void theWaterfallLengthShouldBeDisplayed(String side) throws Throwable {
    Assert.assertTrue("Waterfall length not displayed", base.planPage.isWaterfallLengthDisplayed(side));
  }

  @When("^I select (.*) as overhang side and the overhang size is changed to (\\d+) mm$")
  public void iSelectOneSideRightAsOverhangSideAndTheOverhangSizeIsChangedToMm(String overhangSide, int size) throws Throwable {
    base.planPage.setOverhangSize(overhangSide, size);
  }

  @Then("^no overhang is selected$")
  public void noOverhangIsSelected() throws Throwable {
    Assert.assertTrue("No overhang is not selected when expected", base.planPage.isNoOverhangSelected());
  }

  @When("^I select (.*) as overhang type$")
  public void iSelecAsOverhangType(String overhangType) throws Throwable {
    base.planPage.setOverhangType(overhangType);
  }

  @When("^I put the marker in the measurement field without writing$")
  public void iPutTheMarkerInTheMeasurementFieldWithoutWriting() throws Throwable {
    base.planPage.putMarkerInField("L-shape");
  }
}
