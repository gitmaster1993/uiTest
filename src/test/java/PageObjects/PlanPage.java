package PageObjects;

import Support.SeleniumSupport;
import gherkin.lexer.Th;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PlanPage extends SeleniumSupport {

  private WebDriver driver;

  public static enum ShapeOption {
    NORMAL,
    FLIPPED
  }

  public enum OverhangSide {
    ONE_SIDE_LEFT("option-overhang-side-one-side-left-image-normal"),
    ONE_SIDE_RIGHT("option-overhang-side-one-side-right-image-normal"),
    TWO_SIDES("option-overhang-side-two-sides-image-normal");

    private String id;
    OverhangSide(String id) {
      this.id=id;
    }

    public String getId() {
      return id;
    }
  }

  public enum Measurement {
    A(1), B(2), C(3), D(4), E(5), F(6);

    private int numVal;

    Measurement(int numVal) {
      this.numVal = numVal;
    }

    public int getNumVal() {
      return numVal;
    }
  }

  public PlanPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  /* PAGE ELEMENTS */
  @FindBy(id = "plan-page")
  WebElement pageContainer;

  WebElement shape(String name) {
    String shapeID = "option-card-shape-" + name.toLowerCase();
    return driver.findElement(By.id(shapeID));
  }

  WebElement wall(String name) {
    String shapeID = "option-card-wall-location-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(shapeID));
  }

  WebElement overhang(String name) {
    String shapeID = "option-card-overhang-type-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(shapeID));
  }

  WebElement overhangSide(String name) {
    String shapeID = "option-card-overhang-side-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(shapeID));
  }

  WebElement shapeImage(String name) {
    String elementID = "swipe-option-shape-" + name.toLowerCase() + "-image-normal";
    return driver.findElement(By.id(elementID));
  }

  WebElement measurement(String shapeName, Measurement field) {
    WebElement shape = shape(shapeName);
    String elementId = String.format("%s-measurements-field-%s-input", shapeName.toLowerCase(), field.toString().toLowerCase());
    return shape.findElement(By.id(elementId));
  }

  WebElement wallSelection(String wall) {
    String wallSelectionID = "option-card-wall-location-" + wall.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(wallSelectionID));
  }

  WebElement wallSelectionImage(String wall) {
    String elementId = "swipe-option-wall-location-" + wall.toLowerCase().replace(" ", "-") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  WebElement changeToFlippedShapeBtn(String shapeName) {
    String btnId = "option-shape-" + shapeName.toLowerCase() + "-action-image-flipped";
    return driver.findElement(By.id(btnId));
  }

  WebElement changeToNormalShapeBtn(String shapeName) {
    String btnId = "option-shape-" + shapeName.toLowerCase() + "-action-image-normal";
    return driver.findElement(By.id(btnId));
  }
  WebElement overhangSizeField(String overhangSide) {
    String elementId = overhangSide.toLowerCase().replace(" ", "-") + "-measurements-field-z-input";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(className = "shape-input-error")
  WebElement measurementErrorMessage;

  @FindBy(id = "schematic-error")
  WebElement schematicErrorMessage;

  @FindBy(id = "schematic-drawing-invalid-overlay")
  WebElement schematicRedErrorMessage;

  @FindBy(className = "swiper-button-prev")
  WebElement previousShapeBtn;

  @FindBy(className = "swiper-button-next")
  WebElement nextShapeBtn;

  WebElement shapeInputField(String shape, String field) {
    String elementId = String.format(shape.toLowerCase() + "-measurements-field-" + field.toLowerCase() + "-input");
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "virtual-keyboard")
  WebElement virtualKeyboard;

  @FindBy(id = "kitchen-island-measurements-field-a-input")
  WebElement kitchenIslandMeasureA;

  @FindBy(id = "kitchen-island-measurements-field-b-input")
  WebElement kitchenIslandMeasureB;

  WebElement kitchenMeasurement(Measurement field) {
    String elementId = String.format("kitchen-island-measurements-field-%s-input", field.toString().toLowerCase().replace(" ", "-"));
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "no-overhang-on")
  WebElement noOverhang;

  @FindBy(id = "two-sides-measurements-field-y-input")
  WebElement overhangTwoSideSize;

  @FindBy(id = "option-select-shape")
  WebElement shapeSelector;

  @FindBy(id = "option-select-wall-loaction")
  WebElement wallLocationSelector;

  @FindBy(id = "option-select-overhang-type")
  WebElement overhangTypeSelector;

  @FindBy(id = "option-select-overhang-side")
  WebElement overhangSideSelector;

  WebElement waterfallLength(String side) {
    String sideLength =  side.toLowerCase().replace(" ", "-") + "-measurements-field-z";
    return driver.findElement(By.id(sideLength));
  }

  WebElement overhangOneSideSize(String side) {
    String shapeID =  side.toLowerCase().replace(" ", "-") + "-measurements-field-z-input";
    return driver.findElement(By.id(shapeID));
  }

  /* PAGE METHODS */
  public boolean isPageDisplayed() {
    return isElementDisplayed(driver, pageContainer);
  }

  public void selectShape(String shapeName) {
    click(driver, shape(shapeName));
  }

  public void clickShapeImage(String shapeName) {
    click(driver, shapeImage(shapeName));
  }

  public boolean isShapeSelected(String shapeName) {
    return shape(shapeName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public void clickMeasurementField(String shapeName, String field) throws InterruptedException {
    measurement(shapeName, Measurement.valueOf(field)).click();
  }

  public String getDefaultMeasurementForShape(String shapeName) {
    String measurementA = measurement(shapeName, Measurement.A).getAttribute("placeholder");
    String measurementB = measurement(shapeName, Measurement.B).getAttribute("placeholder");
    if (shapeName.equalsIgnoreCase("rectangular") || shapeName.equalsIgnoreCase("v-shape")
      || shapeName.equalsIgnoreCase("c-shape")) {
      return measurementA + "," + measurementB;
    }
    String measurementC = measurement(shapeName, Measurement.C).getAttribute("placeholder");
    if (shapeName.equalsIgnoreCase("irregular")) {
      return measurementA + "," + measurementB + "," + measurementC;
    }
    String measurementD = measurement(shapeName, Measurement.D).getAttribute("placeholder");
    if (shapeName.equalsIgnoreCase("l-shape") || shapeName.equalsIgnoreCase("II-shape")) {
      return measurementA + "," + measurementB + "," + measurementC + "," + measurementD;
    }
    String measurementE = measurement(shapeName, Measurement.E).getAttribute("placeholder");
    String measurementF = measurement(shapeName, Measurement.F).getAttribute("placeholder");
    return measurementA + "," + measurementB + "," + measurementC + "," + measurementD + "," + measurementE + "," + measurementF;
  }

  public String getMeasurementForShape(String shapeName) {
    String measurementA = measurement(shapeName, Measurement.A).getAttribute("value");
    String measurementB = measurement(shapeName, Measurement.B).getAttribute("value");
    if (shapeName.equalsIgnoreCase("rectangular") || shapeName.equalsIgnoreCase("v-shape")
      || shapeName.equalsIgnoreCase("c-shape")) {
      return measurementA + "," + measurementB;
    }
    String measurementC = measurement(shapeName, Measurement.C).getAttribute("value");
    if (shapeName.equalsIgnoreCase("irregular")) {
      return measurementA + "," + measurementB + "," + measurementC;
    }
    String measurementD = measurement(shapeName, Measurement.D).getAttribute("value");
    if (shapeName.equalsIgnoreCase("l-shape") || shapeName.equalsIgnoreCase("II-shape")) {
      return measurementA + "," + measurementB + "," + measurementC + "," + measurementD;
    }
    String measurementE = measurement(shapeName, Measurement.E).getAttribute("value");
    String measurementF = measurement(shapeName, Measurement.F).getAttribute("value");
    return measurementA + "," + measurementB + "," + measurementC + "," + measurementD + "," + measurementE + "," + measurementF;
  }

  public void setShapeMeasurement(String shapeName, List<String> measurements) throws InterruptedException {
    for (int i = 0; i < measurements.size(); i++) {
      Measurement field = nbrToMeasurement(i+1);
      WebElement inputField = measurement(shapeName, field);
      Thread.sleep(500);
      clearElement(driver, inputField);
      inputField.sendKeys(measurements.get(i));
    }
    Thread.sleep(500);
  }

  public void putMarkerInField(String shapeName) {
    WebElement inputField = measurement(shapeName,Measurement.A);
    click(driver, inputField);
  }

  public void setWallSelection(String wall) {
    click(driver, wallSelection(wall));
  }

  public boolean isWallLocationSelected(String wallLocation) {
    return wallSelection(wallLocation).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public void clickWallImage(String wall) {
    click(driver, wallSelectionImage(wall));
  }

  public boolean measurementErrorMessageDisplayed() {
    return isElementDisplayed(driver, measurementErrorMessage, 1) && isElementDisplayed(driver, schematicErrorMessage, 1);
  }

  public boolean measurementErrorMessageDisplayedAndRedBackground() {
    return (isElementDisplayed(driver, measurementErrorMessage, 1)); //&& isElementDisplayed(driver, schematicRedErrorMessage, 1));
  }

  public boolean sizeErrorMessageDisplayed() {
    return (isElementDisplayed(driver, measurementErrorMessage, 1));
  }

  public void flipShape(String shapeName, ShapeOption option) {
    switch (option){
      case NORMAL:
        click(driver, changeToNormalShapeBtn(shapeName));
        break;
      case FLIPPED:
        click(driver, changeToFlippedShapeBtn(shapeName));
        break;
    }
  }

  private static Measurement nbrToMeasurement(int x) {
    switch (x) {
      case 1:
        return Measurement.A;
      case 2:
        return Measurement.B;
      case 3:
        return Measurement.C;
      case 4:
        return Measurement.D;
      case 5:
        return Measurement.E;
      case 6:
        return Measurement.F;
      default:
        throw new RuntimeException("Cant convert to Measurement enum");
    }
  }

  public boolean isVirtualKeyboardDisplayed() {
    return isElementDisplayed(driver, virtualKeyboard);
  }

  public void enterMeasurementFromVirtualKeyboard(String measurement) {
    for(char x : measurement.toCharArray())  {
      click(driver, virtualKeyboard.findElement(By.xpath("//*[text() = '" + String.valueOf(x) + "']")));
    }
  }

  public void setKitchenIslandMeasurements(List<String> measurements) throws InterruptedException {
    for (int i = 0; i < measurements.size(); i++) {
      Measurement field = nbrToMeasurement(i+1);
      WebElement inputField = kitchenMeasurement(field);
      Thread.sleep(500);
      clearElement(driver, inputField);
      inputField.sendKeys(measurements.get(i));
    }
    Thread.sleep(500);
  }

  public void setKitchenIslandMeasurements(int a, int b) throws InterruptedException {
    String A = Integer.toString(a);
    String B = Integer.toString(b);
    Thread.sleep(500);
    clearElement(driver, kitchenIslandMeasureA);
    kitchenIslandMeasureA.sendKeys(A);
    clearElement(driver, kitchenIslandMeasureB);
    kitchenIslandMeasureB.sendKeys(B);
  }

  public void clickKitchenMeasurementField(String field) {
    kitchenMeasurement(Measurement.valueOf(field)).click();
  }

  public String getMeasurementForKitchenIsland() {
    String measurementA = kitchenMeasurement(Measurement.A).getAttribute("value");
    String measurementB = kitchenMeasurement(Measurement.B).getAttribute("value");
    return measurementA + "," + measurementB;
  }

  public boolean isNoOverhangSelected() {
    return isElementDisplayed(driver, noOverhang);
  }

  public void selectWalls(String wallName) {
    click(driver, wall(wallName));
  }

  public void selectOverhang(String overhangName) {
    click(driver, overhang(overhangName));
  }

  public void selectOverhangSide(String side) {
    click(driver, overhangSide(side));
  }

  /*public boolean isOverhangAvailable() {
    return !overhang("no-overhang").findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }*/

  /*public String getOverhangSideSelection() {
    for (String selected: OverhangSide) {
      if (selected.findElement(By.xpath("..")).getAttribute("class").contains("selected"))
        return selected;
      }
    }
    return null;
  }*/

  public boolean setOverhangSize(String overhangSide, int size) throws InterruptedException {
    if (isElementDisplayed(driver, overhangSizeField(overhangSide))) {
      overhangSizeField(overhangSide).sendKeys();
      String s = Integer.toString(size);
      Thread.sleep(500);
      clearElement(driver, overhangSizeField(overhangSide));
      overhangSizeField(overhangSide).sendKeys(s);
      return true;
    }
    return false;
  }

  public boolean markTheOverhangSideSize(String side) {
    if (side.equals("one side left") || side.equals("one side right")) {
      if (isElementDisplayed(driver, overhangOneSideSize(side))) {
        click(driver, overhangOneSideSize(side));
        return true;
      }
      else {
        if (isElementDisplayed(driver, overhangTwoSideSize)) {
          click(driver, overhangTwoSideSize);
          return true;
        }
      }
    }
    return false;
  }

  public void enterSizeWithVirtualKeyboard(String size) {
    for(char x : size.toCharArray())  {
      click(driver, virtualKeyboard.findElement(By.xpath("//*[text() = '" + String.valueOf(x) + "']")));
    }
    //click(driver, searchCodeBtn);
  }

  public boolean isOverhangSideSelected(String overhangSideOption) {
    if (overhangSide(overhangSideOption).findElement(By.xpath("..")).getAttribute("class").contains("selected")) {
      return true;
    }
    return false;
  }

  public void scrollToSelector(String selector) {
    if(selector.equalsIgnoreCase("shape")) {
      scrollElementIntoView(driver, shapeSelector);
    } else if(selector.equalsIgnoreCase("wall location")) {
      scrollElementIntoView(driver, wallLocationSelector);
    } else if(selector.equalsIgnoreCase("overhang type")) {
      scrollElementIntoView(driver, overhangTypeSelector);
    } else if(selector.equalsIgnoreCase("overhang side")) {
      scrollElementIntoView(driver, overhangSideSelector);
    } else {
      Assert.fail("Unknown selector " + selector);
    }
  }

  public void setOverhangType(String overhangType) {
    click(driver, overhang(overhangType));
  }

  public void selectNoOverhang() {
    click(driver, noOverhang);
  }

  public boolean isWaterfallLengthDisplayed(String side) {
    if (isElementDisplayed(driver, waterfallLength(side))) {
      return true;
    }
    return false;
  }

  public void setMarkerInMeasurementField() {

  }
}
