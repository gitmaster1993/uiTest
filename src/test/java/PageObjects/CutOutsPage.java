package PageObjects;

import Support.SeleniumSupport;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CutOutsPage extends SeleniumSupport
{
  private WebDriver driver;

  public CutOutsPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public enum CutOutWallType {
    ROUND_SMALL("round-cut-small"),
    ROUND_BIG("round-cut-big"),
    SQUARE("square-cut"),
    SINGLE("single-cut"),
    DOUBLE("double-cut"),
    TRIPLE("triple-cut");

    private String id;
    CutOutWallType(String id) {
      this.id=id;
    }

    public String getId() {
      return id;
    }
  }

  public enum CutOutType {
    HOB("cutout"),
    ROUNDED("rounded-corner"),
    INVERTED("inverted-corner"),
    SINGLE("single-cut"),
    DOUBLE("double-cut"),
    TRIPLE("triple-cut"),
    FREESTANDINGHOB("freestanding-hob");

    private String id;
    CutOutType(String id) {
      this.id=id;
    }

    public String getId() {
      return id;
    }
  }

  /* PAGE ELEMENTS */
  @FindBy(id = "cut-outs-page")
  WebElement pageContainer;

  @FindBy(id = "toggle-wall-panel-cutouts")
  WebElement wallPanelCutOutsOption;

  @FindBy(id = "wall-panel-round-cut-small")
  WebElement wallPanelRoundCutSmall;

  @FindBy(id = "numerical-select-add-wall-panel-round-cut-small")
  WebElement roundCutSmallPlus;

  @FindBy(id = "wall-panel-cutouts-title-disabled")
  WebElement wallPanelCutOutsTitleDisabled;

  @FindBy(id = "wall-panel-cutouts-title")
  WebElement wallPanelCutOutsTitle;

  @FindBy(id = "freestanding-hob-width-input")
  WebElement freestandingHobWidth;

  WebElement cutOut(CutOutType type) {
    return driver.findElement(By.id(type.getId()));
  }

  WebElement cutOutImage(CutOutType type) {
    String xpath = "//*[@id=\"" + type.getId() + "\"]/div[4]/img";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement addCutOutBtn(CutOutType type) {
    String id = "numerical-select-add-" + type.getId();
    return driver.findElement(By.id(id));
  }

  WebElement addWallCutOutBtn(CutOutWallType type) {
    String id = "numerical-select-add-wall-panel-" + type.getId();
    return driver.findElement(By.id(id));
  }

  WebElement removeWallCutOutBtn(CutOutWallType type) {
    String id = "numerical-select-remove-wall-panel-" + type.getId();
    return driver.findElement(By.id(id));
  }

  WebElement removeCutOutBtn(CutOutType type) {
    String id = "numerical-select-remove-" + type.getId();
    return driver.findElement(By.id(id));
  }

  WebElement nbrOfCutOuts(CutOutType type) {
    String id = type.getId() + "-count";
    return driver.findElement(By.id(id));
  }

  WebElement priceOfCutOuts(CutOutType type) {
    String id = type.getId() + "-price";
    return driver.findElement(By.id(id));
  }


  /* PAGE METHODS */
  public boolean isPageDisplayed() {
    return isElementDisplayed(driver, pageContainer);
  }

  //Test both add and remove cut outs
  public void addCutOuts(CutOutType type, int nbrOfCutOuts) {
    int offset = 2;
    for(int i = 0; i < nbrOfCutOuts+offset; i++) {
      click(driver, addCutOutBtn(type));
    }
    for(int i = 0; i < offset; i++) {
      click(driver, removeCutOutBtn(type));
    }
  }

  public void removeWallCutOuts(CutOutWallType type, int nbrOfCutOuts) {
    for(int i = 0; i < nbrOfCutOuts; i++) {
      click(driver, removeWallCutOutBtn(type));
    }
  }

  public void addWallCutOuts(CutOutWallType type, int nbrOfCutOuts) {
    for(int i = 0; i < nbrOfCutOuts; i++) {
      click(driver, addWallCutOutBtn(type));
    }
  }

  public void addCutOut(CutOutType type, int nbrOfCutOuts) {
    for(int i = 0; i < nbrOfCutOuts; i++) {
      click(driver, addCutOutBtn(type));
    }
  }

  public int getNbrOfCutOuts(CutOutType type) {
    return Integer.parseInt(nbrOfCutOuts(type).getText());
  }

  public int getPriceOfCutOuts(CutOutType type) {
    return Integer.parseInt(priceOfCutOuts(type).getText().replace(" ", "").replace("kr", ""));
  }

  public void clickCutOutImage(CutOutType type) {
    click(driver, cutOutImage(type));
  }

  public void scrollToSelector(String selector) {
    if(selector.equalsIgnoreCase("hob cut-outs")) {
      scrollElementIntoView(driver, cutOut(CutOutType.HOB));
    } else if(selector.equalsIgnoreCase("rounded corner")) {
      scrollElementIntoView(driver, cutOut(CutOutType.ROUNDED));
    } else if(selector.equalsIgnoreCase("inverted corner")) {
      scrollElementIntoView(driver, cutOut(CutOutType.INVERTED));
    } else if(selector.equalsIgnoreCase("single cut")) {
      scrollElementIntoView(driver, cutOut(CutOutType.SINGLE));
    } else if(selector.equalsIgnoreCase("double cut")) {
      scrollElementIntoView(driver, cutOut(CutOutType.DOUBLE));
    } else if(selector.equalsIgnoreCase("triple cut")) {
      scrollElementIntoView(driver, cutOut(CutOutType.TRIPLE));
    } else {
      Assert.fail("Unknown selector " + selector);
    }
  }

  public boolean isWallPanelCutOutsCollapsed() {
    String text = wallPanelCutOutsOption.getText();
    if (text.contains("Show options")) {
      return true;
    }
    return false;
  }

  public boolean isWallPanelCutOutsUncollapsed() {
    if (wallPanelCutOutsOption.getText().contains("Hide options")) {
      return true;
    }
    return false;
  }

  public boolean isWallPanelCutOutsDisabled() {
    if (wallPanelCutOutsTitleDisabled.isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isWallPanelCutOutsEnabled() {
    if (wallPanelCutOutsTitle.isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean setFreestandingHobWidth(int width) {
    if (isElementDisplayed(driver, freestandingHobWidth)) {
      freestandingHobWidth.sendKeys(Integer.toString(width));
      return true;
    }
    return false;
  }
}
