package PageObjects;

import Support.SeleniumSupport;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Support.ExecutionUtil.cleanId;

public class StylePage extends SeleniumSupport {

  private WebDriver driver;

  public static final String[] ALL_EDGES = new String[]{"Same color", "Aluminium"};
  public static final String[] ALL_THICKNESSES = new String[]{"28", "38", "77"};
  public static final String[] TAIWAN_THICKNESSES = new String[]{"20", "40"};
  public static final String[] ALL_EDGE_OPTIONS = new String[]{"Straight", "Chamfered", "Round", "Half round", "Bevel", "Anti-drip"};
  public static final String[] ALL_SPLASHBACKS = new String[]{"none", "wall panel", "wall edging strip"};
  public static final String[] TAIWAN_SPLASHBACKS = new String[]{"none", "wall panel"};

  public StylePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  /* PAGE ELEMENTS */
  @FindBy(id = "style-page")
  WebElement pageContainer;


  WebElement edgeOption(String name) {
    String elementId = "option-card-edge-" + name.toLowerCase();
    return driver.findElement(By.id(elementId));
  }

  WebElement material(String name) {
    String elementId = "option-card-material-" + name.toLowerCase();
    return driver.findElement(By.id(elementId));
  }

  WebElement materialImage(String name) {
    String elementId = "option-material-" + name.toLowerCase() + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  WebElement expression(String name) {
    String elementId = "option-card-expression-" + cleanId(name);
    return driver.findElement(By.id(elementId));
  }

  WebElement expressionImage(String name) {
    String elementId = "option-expression-" + cleanId(name) + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "option-select-edge")
  WebElement edgeSelector;

  WebElement edge(String name) {
    String elementId = "option-card-edge-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(elementId));
  }

  WebElement edgeImage(String name) {
    String elementId = "option-edge-" + name.toLowerCase().replace(" ", "-") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "option-select-thickness")
  WebElement thicknessSelector;

  WebElement thickness(String name) {
    String elementId = "option-card-thickness-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(elementId));
  }

  WebElement thicknessImage(String name) {
    String elementId = "option-thickness-" + name.toLowerCase().replace(" ", "-") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "option-select-splashback")
  WebElement splashbackSelector;

  WebElement splashback(String name) {
    String elementId = "option-card-splashback-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(elementId));
  }

  WebElement splashbackImage(String name) {
    String elementId = "option-splashback-" + name.toLowerCase().replace(" ", "-") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  WebElement virtualKeyboardBackBtn() {
    String xpath="//*[@id=\"virtual-keyboard" + "\"]/div[4]/div[3]";
    return driver.findElement(By.xpath(xpath));
  }

  @FindBy(id = "option-select-wall-panel-material")
  WebElement wallPanelMaterialSelector;

  @FindBy(id = "wall-panel-material-title-disabled")
  WebElement wallPanelMaterialDisabled;

  @FindBy(id = "wall-panel-expression-title-disabled")
  WebElement wallPanelExpressionDisabled;

  @FindBy(id = "match-worktop-radio-button-on")
  WebElement matchWorktopStyle;

  WebElement wallPanelMaterial(String name) {
    String elementId = "option-card-wall-panel-material-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(elementId));
  }

  WebElement wallPanelMaterialImage(String name) {
    String elementId = "option-wall-panel-material-" + name.toLowerCase().replace(" ", "-") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "option-select-wall-panel-expression")
  WebElement wallPanelExpressionSelector;

  WebElement wallPanelExpression(String name) {
    String elementId = "option-card-wall-panel-expression-" + name.toLowerCase().replace(" ", "");
    return driver.findElement(By.id(elementId));
  }

  WebElement wallPanelExpressionImage(String name) {
    String elementId = "option-wall-panel-expression-" + name.toLowerCase().replace(" ", "") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "wall-panel-height-input")
  WebElement splashbackHeight;

  @FindBy(className = "shape-input-error")
  WebElement heightErrorMessage;

  @FindBy(className = "virtual-keyboard-next-or-done")
  WebElement virtualOkBtn;

  @FindBy(id = "virtual-keyboard")
  WebElement virtualKeyboard;

  @FindBy(id = "wall-panel-height")
  WebElement pictogramHeight;

  @FindBy(id = "none-on")
  WebElement noSplashback;

  @FindBy(id = "none-off")
  WebElement noSplashbackOff;

  /* PAGE METHODS */
  public boolean isPageDisplayed() {
    return isElementDisplayed(driver, pageContainer);
  }
  public void selectMaterial(String materialName) {
    click(driver, material(materialName));
  }

  public void clickMaterialImage(String materialName) {
    click(driver, materialImage(materialName));
  }

  public boolean isMaterialSelected(String materialName) {
    return material(materialName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isWpMaterialSelected(String materialName) {
    return wallPanelMaterial(materialName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isMaterialAvailable(String materialName) {
    return !material(materialName).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public void selectExpression(String expressionName) {
    click(driver, expression(expressionName));
  }

  public void clickExpressionImage(String expressionName) {
    click(driver, expressionImage(expressionName));
  }

  public boolean isExpressionSelected(String expressionName) {
    return expression(expressionName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isWpExpressionSelected(String expressionName) {
    return wallPanelExpression(expressionName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isSplashbackOptionSelected(String splashbackName) {
    return splashback(splashbackName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public void selectEdge(String edgeName) {
    click(driver, edge(edgeName));
  }

  public void clickEdgeImage(String edgeName) {
    click(driver, edgeImage(edgeName));
  }

  public boolean isEdgeSelected(String edgeName) {
    return edge(edgeName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isEdgeAvailable(String edgeName) {
    return !edge(edgeName).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public void selectThickness(String thickness) {
    click(driver, thickness(thickness));
  }

  public void clickThicknessImage(String thickness) {
    click(driver, thicknessImage(thickness));
  }

  public boolean isThicknessSelected(String thickness) {
    return thickness(thickness).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isThicknessAvailable(String thickness) {
    return !thickness(thickness).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public void selectSplashback(String splashback) {
    click(driver, splashbackImage(splashback));
  }

  public void clickSplashbackImage(String splashback) {
    click(driver, splashbackImage(splashback));
  }

  public boolean isSplashbackSelected(String splashback) {
    if (splashback.equalsIgnoreCase("none")) {
      return isElementDisplayed(driver, noSplashback);
    }
    return splashback(splashback).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isSplashbackAvailable(String splashback) {
    if (splashback.equalsIgnoreCase("none")) {
      return isElementDisplayed(driver, noSplashback);
    }
    return !splashback(splashback).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public void selectWallPanelMaterial(String wp_material) {
    click(driver, wallPanelMaterial(wp_material));
  }

  public void clickWallPanelMaterialImage(String wp_material) {
    click(driver, wallPanelMaterialImage(wp_material));
  }

  public boolean isWallPanelMaterialSelected(String wp_material) {
    return wallPanelMaterial(wp_material).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isWallPanelMaterialAvailable(String wp_material) {
    return !wallPanelMaterial(wp_material).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public boolean isWallPanelMaterialDisabled() {
    if (wallPanelMaterialDisabled.isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isWallPanelExpressionDisabled() {
    if (wallPanelExpressionDisabled.isDisplayed()) {
      return true;
    }
    return false;
  }

  public void selectWallPanelExpression(String wp_expression) {
    click(driver, wallPanelExpression(wp_expression));
  }

  public void clickWallPanelExpressionImage(String wp_expression) {
    click(driver, wallPanelExpressionImage(wp_expression));
  }

  public boolean isWallPanelExpressionSelected(String wp_expression) {
    return wallPanelExpression(wp_expression).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isWallPanelExpressionAvailable(String wp_expression) {
    return !wallPanelExpression(wp_expression).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public boolean isEdgeOptionSelected(String edgeOptionName) {
    return edgeOption(edgeOptionName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isEdgeOptionAvailable(String edgeOptionName) {
    return !edgeOption(edgeOptionName).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public void scrollToSelector(String selector) {
    if(selector.equalsIgnoreCase("edge")) {
      scrollElementIntoView(driver, edgeSelector);
    } else if(selector.equalsIgnoreCase("thickness")) {
      scrollElementIntoView(driver, thicknessSelector);
    } else if(selector.equalsIgnoreCase("splashback")) {
      scrollElementIntoView(driver, splashbackSelector);
    } else if(selector.equalsIgnoreCase("wallPanelMaterial")) {
      scrollElementIntoView(driver, wallPanelMaterialSelector);
    } else if(selector.equalsIgnoreCase("wallPanelExpression")) {
      scrollElementIntoView(driver, wallPanelExpressionSelector);
    } else {
      Assert.fail("Unknown selector " + selector);
    }
  }

  public boolean compareExpression(String expectedExpression) {
    return expression(expectedExpression).findElement(By.id("swipe-select-expression")).getAttribute("class").contains("selected");
  }

  public int getSplashbackHeight() {
    if (splashbackHeight.isDisplayed()) {
      String temp = splashbackHeight.getText();
      if (!temp.equals("")) {
        return Integer.parseInt(splashbackHeight.getText());
      }
      else {
        return 550; //default value
      }
    }
    return 0;
  }

  public int getSplashbackHeightFromImage() {
    if (isElementDisplayed(driver, pictogramHeight)) {
      return Integer.parseInt(pictogramHeight.getText());
    }
    return -1;
  }

  public void setSplashbackHeight(int height) {
    if (splashbackHeight.isDisplayed()) {
      splashbackHeight.clear();
      splashbackHeight.sendKeys(String.valueOf(height));
    }
  }

  public boolean isHeightErrorMessageDisplayed() {
    return isElementDisplayed(driver, heightErrorMessage);
  }

  public void selectVirtualKeyboardOkButton() {
    click(driver, virtualKeyboard.findElement(By.id("virtual-keyboard-next-or-done")));
  }

  public boolean isWallPanelHeightDisplayed() {
    return isElementDisplayed(driver, pictogramHeight);
  }

  public boolean markTheSplashbackHeight() {
    if (isElementDisplayed(driver, splashbackHeight)) {
      splashbackHeight.click();
      return true;
    }
    return false;
  }

  public boolean isPictogramHeightSetToValue(int a) {
    if (isElementDisplayed(driver, pictogramHeight)) {
      int A = Integer.parseInt(pictogramHeight.getText());
      if (a == A) {
        return true;
      }
    }
    return false;
  }

  public void removeSplashbackHeightWithVirtualKeyboard() {
    for (int i=0; i<4; i++) {
      click(driver, virtualKeyboardBackBtn());
    }
  }

  public boolean selectWallPanelMaterialMatch() {
    if (isElementDisplayed(driver, matchWorktopStyle)) {
      click(driver, matchWorktopStyle);
      return true;
    }
    return false;
  }

  public boolean selectNoSplashback() {
    if (isElementDisplayed(driver, noSplashbackOff)) {
      click(driver, noSplashbackOff);
      return true;
    }
    return false;
  }
}
