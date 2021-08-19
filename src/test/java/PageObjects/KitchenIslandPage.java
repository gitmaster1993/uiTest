package PageObjects;

import Support.SeleniumSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.typesafe.config.ConfigException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Models.Article;
import Support.ExecutionUtil;
import Support.Range;
import Support.SeleniumSupport;
import gherkin.lexer.En;
import org.apache.maven.shared.utils.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import static Support.ExecutionUtil.cleanId;
import static java.util.concurrent.TimeUnit.SECONDS;

public class KitchenIslandPage extends SeleniumSupport {

  private WebDriver driver;

  public KitchenIslandPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  private String id;

  public String getId() {
    return id;
  }

  /* PAGE ELEMENTS */
  @FindBy(id = "kitchenIsland-page")
  WebElement pageContainer;

  @FindBy(id = "option-kitchen-island-kitchen-island-image-normal")
  WebElement kitchenIsland;

  @FindBy(id = "no-kitchen-island-off")
  WebElement noKitchenIsland;

  @FindBy(id = "no-kitchen-island-on")
  WebElement noKitchenIslandSelected;

  @FindBy(id = "same-as-worktop-radio-button-on")
  WebElement kitchenIslandStyleRbOn;

  @FindBy(id = "same-as-worktop-radio-button-off")
  WebElement kitchenIslandStyleRbOff;

  WebElement material(String name) {
    String elementId = "option-card-material-" + name.toLowerCase();
    return driver.findElement(By.id(elementId));
  }

  WebElement expression(String name) {
    String elementId = "option-card-expression-" + cleanId(name);
    return driver.findElement(By.id(elementId));
  }

  WebElement edge(String name) {
    String elementId = "option-card-edge-" + name.toLowerCase().replace(" ", "-");
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "select-thickness")
  WebElement thicknessSelector;

  WebElement thickness(int number) {
    String elementId = "option-card-thickness-" + number;
    return driver.findElement(By.id(elementId));
  }

  /* PAGE METHODS */
  public boolean isPageDisplayed() {
    return isElementDisplayed(driver, pageContainer);
  }

  public void setKitchenIslandSelection() {
    click(driver, kitchenIsland);
  }

  public boolean isKitchenIslandSelected( String option) {
    if (option.equalsIgnoreCase("kitchen island")) {
      return kitchenIsland.findElement(By.xpath("..")).getAttribute("class").contains("selected");
    }
    else {
      return isElementDisplayed(driver, noKitchenIslandSelected);
    }
  }
  public void selectMaterial(String materialName) {
    click(driver, material(materialName));
  }

  public boolean isMaterialSelected(String materialName) {
    return material(materialName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public void selectExpression(String expressionName) {
    click(driver, expression(expressionName));
  }

  public boolean isExpressionSelected(String expressionName) {
    return expression(expressionName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }


  public boolean isEdgeSelected(String edgeName) {
    return edge(edgeName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }
  public void selectThickness(int thicknessName) {
    click(driver, thickness(thicknessName));
  }

  public boolean isThicknessSelected(int thicknessName) {
    return thickness(thicknessName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isKitchenIslandStyleEnabled() {
    return isElementDisplayed(driver, kitchenIslandStyleRbOn);
  }

  public boolean isNoKitchenIslandDisplayed() {
    return isElementDisplayed(driver, noKitchenIsland);
  }

  public void setNoKitchenIsland() {
    click(driver, noKitchenIsland);
  }

  public void selectEdge(String edgeName) {
    click(driver, edge(edgeName));
  }

  public boolean isKitchenIslandSetAsWorktopDisplayed() {
    return isElementDisplayed(driver, kitchenIslandStyleRbOff);
  }

  public void clickKitchenIslandSetAsWorktop() {
    click(driver, kitchenIslandStyleRbOff);
  }
}
