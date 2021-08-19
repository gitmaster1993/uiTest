package PageObjects;

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

import static java.util.concurrent.TimeUnit.SECONDS;

public class ComparePage extends SeleniumSupport {

  private WebDriver driver;

  public ComparePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

    private String id;

    public String getId() {
      return id;
    }


  public enum ListType {
    STYLE("styleDetails"),
    SINK("sinkDetails"),
    CUTOUT("cutOutDetails"),
    TAPHOLE("tapHoleDetails");

    private String id;
    ListType(String id) {
      this.id=id;
    }

    public String getId() {
      return id;
    }
  }

  /* PAGE ELEMENTS */

  WebElement code(int nbr) {
    String id = "plan-code-" + (nbr);
    return driver.findElement(By.id(id));
  }

  @FindBy(id = "header-add-plan-button")
  WebElement addPlanBtn;

  @FindBy(id = "showLoadInputButton")
  WebElement enterCodeBtn;

  @FindBy(id = "loadInputField")
  WebElement loadInputField;

  @FindBy(id = "searchCodeButton")
  WebElement searchCodeBtn;

  WebElement removeBtn(int nbr) {
    String id = "removeButton-" + nbr;
    return driver.findElement(By.id(id));
  }

  @FindBy(id = "remove-confirm-dialog")
  WebElement removeDialog;

  @FindBy(id = "remove-confirm-dialog-remove")
  WebElement remove;

  @FindBy(id = "compareButton")
  WebElement compareBtn;

  @FindBy(id = "compare-worktop-group-0-image")
  WebElement worktopImg;

  @FindBy(id = "compare-sink-group-0-image")
  WebElement sinkImg;

  @FindBy(id = "compare-cutout-group-0-image")
  WebElement cutOutsImg;

  @FindBy(id = "compare-tapHole-group-0-image")
  WebElement tapHoleImg;

  @FindBy(id = "compare-freestandingHob-group-0-image")
  WebElement freestandingHobImg;

  @FindBy(id = "itemlist-header-back")
  WebElement backBtn;

  WebElement imageSwiperBullet(int column, int bullet) {
    String id = "plan-" + column + "-swiper-bullet-" + bullet;
    return driver.findElement(By.id(id));
  }

  WebElement worktopDetails(int column) {
    String id = "compare-worktop-details-" + column + "-toggle";
    return driver.findElement(By.id(id));
  }

  WebElement sinkDetails(int column) {
    String id = "compare-sink-details-" + column + "-toggle";
    return driver.findElement(By.id(id));
  }

  WebElement cutOutsDetails(int column) {
    String id = "compare-cutout-details-" + column + "-toggle";
    return driver.findElement(By.id(id));
  }

  WebElement tapHoleDetails(int column) {
    String id = "compare-tapHole-details-" + column + "-toggle";
    return driver.findElement(By.id(id));
  }

  WebElement worktopImage(int column){
    String id = "worktop-image-" + column + "-image-slide-image";
    return driver.findElement(By.id(id));
  }

  WebElement edgeImage(int column){
    String id = "edge-image-" + column + "-image-slide-image";
    return driver.findElement(By.id(id));
  }

  WebElement shapeImage(int column){
    //String id = "shape-image-" + column + "-schematic-slide";
    String id = "worktop-schematic-" + column + "-schematic-slide";
    return driver.findElement(By.id(id));
  }

  WebElement sinkImage(int column){
    String id = "sink-image-" + column + "-image-slide-image";
    return driver.findElement(By.id(id));
  }

  WebElement okErrorMessageColumn(int column) {
    String id = "error-edit-plan-" + column;
    return driver.findElement(By.id(id));
  }

  @FindBy(className = "swiper-button-next")
  WebElement nextImage;

  @FindBy(className = "swiper-button-prev")
  WebElement prevImage;

  @FindBy(id = "virtual-keyboard")
  WebElement virtualKeyboard;

  @FindBy(id = "code-field-error")
  WebElement errorMessage;

  public void removePlan(WebElement button) throws InterruptedException {
    if (isElementDisplayed(driver, button)) {
      button.click();
      Thread.sleep(1000);
      if (isElementDisplayed(driver, removeDialog)) {
        remove.click();
      } else {
        Assert.fail("No dialog appears to confirm removal of plan");
      }
    }
  }

  public boolean nrOfPlansCompared(int expectedNrOfPlans) {
    if (expectedNrOfPlans == 1) {
      return isElementDisplayed(driver, code(0));
    } else if (expectedNrOfPlans == 2) {
      return isElementDisplayed(driver, code(1));
    } else if (expectedNrOfPlans == 3) {
      return isElementDisplayed(driver, code(2));
    }  else {
      return false;
    }
  }

  public void selectAddPlan() throws InterruptedException {
    addPlanBtn.click();
  }

  public boolean isAddPlanDisplayed() {
    return isElementDisplayed(driver, addPlanBtn);
  }

//Check with other browsers...
  public boolean isAddPlanEnabled() {
    String color = addPlanBtn.getCssValue("color");
    if ((color.equals("rgba(34, 34, 34, 1)") || (color.equals("rgb(34, 34, 34)")))) {
      return true;
    }
    return false;
  }

  public boolean enterCode(String codeNbr) {
    if (isAddPlanEnabled()) {
      addPlanBtn.click();
      if (isElementDisplayed(driver, loadInputField)) {
        loadInputField.sendKeys(codeNbr);
        click(driver, searchCodeBtn);
        return true;
      }
    }
    return false;
  }

  public boolean clickEnterCodeButton() {
    if (enterCodeBtn.isEnabled()) {
      enterCodeBtn.click();
      if (isElementDisplayed(driver, loadInputField)) {
        return true;
      }
    }
    return false;
  }


  public boolean enterFirstCode(String codeNbr) {
    if (enterCodeBtn.isEnabled()) {
      enterCodeBtn.click();
      if (isElementDisplayed(driver, searchCodeBtn)) {
        searchCodeBtn.click();
        if (isElementDisplayed(driver, loadInputField)) {
          loadInputField.sendKeys(codeNbr);
          click(driver, searchCodeBtn);
          return true;
        }
      }
    }
    return false;
  }

  public boolean isEnterCodeDisabled() {
    if (enterCodeBtn.isEnabled()) {
      return false;
    }
    return true;
  }

  public void removeAllPlansFromComparePage() throws InterruptedException {
    for (int i=2; i>=0; i--) {
      removePlan(removeBtn(i));
    }
  }

  public boolean isEnterCodeDisplayed() {
    return isElementDisplayed(driver, enterCodeBtn);
  }

  public boolean isImageMarked(String type) {
    WebElement image;
    switch (type) {
      case "Worktop":
        image = worktopImg;
        break;
      case "Sink":
        image = sinkImg;
        break;
      case "Cut-outs":
        image = cutOutsImg;
        break;
      case "Tap holes":
        image = tapHoleImg;
        break;
      case "freestanding hob":
        image = freestandingHobImg;
        break;
      default:
        image = worktopImg;
        break;
    }

    String border = image.getCssValue("border");
    System.out.println("Border: " + border);
    if (border.equals("3px solid rgb(255, 172, 3)") || (border.equals("3px solid rgb(255, 172, 3)"))){
      return true;
    }
      //#FFAC03
     return false;
  }

  public void expandDetails(String type) {
    WebElement arrow;
    switch (type) {
      case "Worktop":
        arrow = worktopDetails(0);
        break;
      case "Sink":
        arrow = sinkDetails(0);
        break;
      case "Cut-outs":
        arrow = cutOutsDetails(0);
        break;
      case "Tap holes":
        arrow = tapHoleDetails(0);
        break;
      default:
        arrow = worktopDetails(0);
        break;
    }
    click(driver, arrow);
  }

  public boolean isDetailsExpanded(String type) {
    WebElement arrow;
    switch (type) {
      case "Worktop":
        arrow = worktopDetails(0);
        break;
      case "Sink":
        arrow = sinkDetails(0);
        break;
      case "Cut-outs":
        arrow = cutOutsDetails(0);
        break;
      case "Tap holes":
        arrow = tapHoleDetails(0);
        break;
      default:
        arrow = worktopDetails(0);
        break;
    }
    String details = arrow.getText();
    if (details.contains("Hide details")) {
      return true;
    }
    return false;
  }

  public boolean isAllDetailsExpanded(String type) {
    WebElement arrow;
    String details;
    for (int i=0; i<=1; i++) {
      switch (type) {
        case "Worktop":
          arrow = worktopDetails(i);
          break;
        case "Sink":
          arrow = sinkDetails(i);
          break;
        case "Cut-outs":
          arrow = cutOutsDetails(i);
          break;
        case "Tap holes":
          arrow = tapHoleDetails(i);
          break;
        default:
          arrow = worktopDetails(i);
          break;
      }
      details = arrow.getText();
      if (!details.contains("Hide details")){
        return false;
      }
    }
    return true;
  }

  public boolean isErrorMessageDisplayed(int column) {
    if (column == 3) {
      return isElementDisplayed(driver, okErrorMessageColumn(2));
    } else if (column == 2) {
      return isElementDisplayed(driver, okErrorMessageColumn(1));
    } else {
      return isElementDisplayed(driver, okErrorMessageColumn(0));
    }
  }

  public void clickOkButton(int column) {
    if (column == 3) {
      okErrorMessageColumn(2).click();
    } else if (column == 2) {
      okErrorMessageColumn(1).click();
    } else {
      okErrorMessageColumn(0).click();
    }
  }

  public boolean isVirtualKeyboardDisplayed() {
    if (isElementDisplayed(driver, virtualKeyboard)) {
      return true;
    }
    return false;
  }

  public void enterCodeWithVirtualKeyboard(String codeNbr) {
    for(char x : codeNbr.toCharArray())  {
      click(driver, virtualKeyboard.findElement(By.xpath("//*[text() = '" + String.valueOf(x) + "']")));
    }
    click(driver, searchCodeBtn);
  }

  public boolean isErrorMessageDisplayedAfterEnterCode() {
    if (isElementDisplayed(driver, errorMessage)) {
      if (errorMessage.getText().equals("Failed to find your code. Try again!")) {
        return true;
      }
    }
    return false;
  }

  public boolean swipeInImage(String direction, int column) {
    if (direction.equals("right")) {
      if (isElementDisplayed(driver, nextImage)) {
        nextImage.click();
        return true;
      }
      return false;
    }
    else {
      if (isElementDisplayed(driver, prevImage)) {
        nextImage.click();
        return true;
      }
      return false;
    }
  }

  public boolean isImageDisplayed(String image, int column) {
    WebElement type;
    switch (image) {
      case "worktop":
        type = worktopImage(column-1);
        break;
      case "sink":
        type = sinkImage(column-1);
        break;
      case "edge":
        type = edgeImage(column-1);
        break;
      case "shape":
        type = shapeImage(column-1);
        break;
      default:
        type = worktopImage(column-1);
        break;
    }
    if (isElementDisplayed(driver, type)){
      return true;
    }
    return false;
  }

  public void clickImageBullet(int bullet, int column) {
    click(driver, imageSwiperBullet(column-1, bullet-1));
  }

  public void gobackFromComparePage() {
    click(driver, backBtn);
  }
}
