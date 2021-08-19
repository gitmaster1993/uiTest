package PageObjects;

import Support.SeleniumSupport;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static Support.ExecutionUtil.cleanId;

public class SinkPage extends SeleniumSupport{

  private WebDriver driver;

  public static final String[] ALL_FASTENINGS = new String[]{"top Mounted", "underGlued"};

  public SinkPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  /* PAGE ELEMENTS */
  @FindBy(id = "sink-page")
  WebElement pageContainer;

  WebElement material(String name) {
    String elementId = "option-card-sink-material-" + name.replace(" ", "");
    return driver.findElement(By.id(elementId));
  }

  WebElement materialImage(String name) {
    String elementId = "option-sink-material-" + name.replace(" ", "") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "option-select-sink-model")
  WebElement modelSlider;

  WebElement model(String name) {
    String elementId = "option-card-sink-model-" + cleanId(name);
    return driver.findElement(By.id(elementId));
  }

  WebElement modelImage(String name) {
    String elementId = "option-sink-model-" + cleanId(name) + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "option-select-sink-fastening")
  WebElement fasteningSelector;

  WebElement fastening(String name) {
    String elementId = "option-card-sink-fastening-" + name.replace(" ", "");
    return driver.findElement(By.id(elementId));
  }

  WebElement fasteningImage(String name) {
    String elementId = "option-sink-fastening-" + name.replace(" ", "") + "-image-normal";
    return driver.findElement(By.id(elementId));
  }

  @FindBy(id = "option-select-drainer-grooves")
  WebElement drainerGroovesSelector;

  @FindBy(id = "option-card-drainer-grooves-drainer-grooves")
  WebElement drainerGrooves;

  @FindBy(id = "swipe-option-drainer-grooves-drainer-grooves-image-normal")
  WebElement drainerGroovesImage;

  @FindBy(xpath = "//*[@id=\"tap-holes\"]/div[4]/img")
  WebElement tapHoleImage;

  @FindBy(id = "numerical-select-add-tap-holes")
  WebElement addTapHole;

  @FindBy(id = "numerical-select-remove-tap-holes")
  WebElement removeTapHole;

  @FindBy(xpath = "//*[@id=\"tap-holes\"]/div[5]/div[1]/div[2]")
  WebElement nbrOfTapHoles;

  @FindBy(xpath = "//*[@id=\"tap-holes\"]/div[5]/div[2]")
  WebElement priceOfTapHoles;

  @FindBy(id = "option-info-sink-fastening-topMounted")
  WebElement topMountedInfoDialogBtn;

  @FindBy(id = "option-info-sink-fastening-underGlued")
  WebElement underGluedInfoDialogBtn;

  @FindBy(id = "option-info-dialog")
  WebElement popupInfoDialog;

  @FindBy(id = "dialog-close")
  WebElement closePopupInfoDialogBtn;

  @FindBy(id = "noSink-on")
  WebElement noSink;

  @FindBy(id = "noSink-off")
  WebElement noSinkOff;

  @FindBy(id = "sink-image-pictogram")
  WebElement sinkPictogram;

  @FindBy(id = "sink-image-missing-image")
  WebElement sinkMissingImage;

  /* PAGE METHODS */
  public boolean isPageDisplayed() {
    return isElementDisplayed(driver, pageContainer);
  }

  public void selectMaterial(String materialName) {
    if (materialName.equalsIgnoreCase("no Sink")) {
      if (!isElementDisplayed(driver, noSink)) {
        click(driver,noSinkOff);
      }
    }
    else {
      click(driver, material(materialName));
    }
  }

  public void clickMaterialImage(String materialName) {
    click(driver, materialImage(materialName));
  }

  public boolean isMaterialSelected(String materialName) {
    if (materialName.equalsIgnoreCase("no Sink")) {
      return isElementDisplayed(driver, noSink);
    }
    return material(materialName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public void selectModel(String modelName) {
    click(driver, model(modelName));
  }

  public void clickModelImage(String modelName) {
    click(driver, modelImage(modelName));
  }

  public boolean isModelSelected(String modelName) {
    return model(modelName).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isAnyModelSelected() {

    boolean staleElement = true;
    while(staleElement){
      try{
        List<WebElement> allModels = modelSlider.findElements(By.xpath(".//*"));
        for(WebElement model: allModels) {
          if (model.findElement(By.xpath("..")).getAttribute("class").contains("selected")) {
            return true;
          }
        }
        staleElement = false;

      } catch(StaleElementReferenceException e){
        staleElement = true;
      }
    }
    return false;
  }

  public void selectFastening(String fastening) {
    click(driver, fastening(fastening));
  }

  public void clickFasteningImage(String fastening) {
    click(driver, fasteningImage(fastening));
  }

  public boolean isFasteningSelected(String fastening) {
    return fastening(fastening).findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isAnyFasteningSelected() {
    List<WebElement> allFastenings = fasteningSelector.findElements(By.xpath(".//*"));
    for(WebElement fastening: allFastenings) {
      if (fastening.findElement(By.xpath("..")).getAttribute("class").contains("selected")) {
        return true;
      }
    }
    return false;
  }

  public boolean isFasteningAvailable(String fastening) {
    return !fastening(fastening).findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public void selectDrainerGrooves() {
    click(driver, drainerGrooves);
  }

  public void clickDrainerGroovesImage() {
    click(driver, drainerGroovesImage);
  }

  public boolean isDrainerGroovesSelected() {
    return drainerGrooves.findElement(By.xpath("..")).getAttribute("class").contains("selected");
  }

  public boolean isDrainerGroovesAvailable() {
    return !drainerGrooves.findElement(By.xpath("..")).getAttribute("class").contains("disabled");
  }

  public void addTapHoles(int nbrOfTapHoles) {
    removeTapHole(); //Since some models have a taphole default
    int offset = 2;
    for(int i = 0; i < nbrOfTapHoles+offset; i++) {
      addTapHole();
    }
    for(int i = 0; i < offset; i++) {
      removeTapHole();
    }
  }

  public void clickTapHoleImage() {
    click(driver, tapHoleImage);
  }

  public void addTapHole() {
    click(driver, addTapHole);
  }

  public void removeTapHole() {
    click(driver, removeTapHole);
  }

  public int getNbrOfTapHoles() {
    return Integer.parseInt(nbrOfTapHoles.getText());
  }

  public int getPriceOfTapHoles() {
    return Integer.parseInt(priceOfTapHoles.getText().replace(" kr", ""). replace(" ", ""));
  }

  public void scrollToSelector(String selector) {
    if(selector.equalsIgnoreCase("fastening")) {
      scrollElementIntoView(driver, fasteningSelector);
    } else if(selector.equalsIgnoreCase("drainer grooves")) {
      scrollElementIntoView(driver, drainerGroovesSelector);
    } else {
      Assert.fail("Unknown selector " + selector);
    }
  }

  public void displayFasteningInfoDialog(String fastening) {
    if (fastening.equalsIgnoreCase("underGlued")) {
      click(driver, underGluedInfoDialogBtn);
    } else if (fastening.equalsIgnoreCase("topMounted")) {
      click(driver, topMountedInfoDialogBtn);
    } else {
      Assert.fail("Unknown fastening " + fastening);
    }
  }

  public boolean isInfoDialogDisplayed() {
    return isElementDisplayed(driver, popupInfoDialog);
  }

  public void closeInfoDialog() throws InterruptedException {
    click(driver, closePopupInfoDialogBtn);
    waitForElementToDisappear(driver, popupInfoDialog, 2);
  }

  public boolean isNoSinkSelected() {
    return noSink.isEnabled();
  }

  public boolean isSinkImageMissingAvailable() {
    return isElementDisplayed(driver, sinkMissingImage);
  }

  public boolean isSinkPictogramAvailable() {
    return isElementDisplayed(driver, sinkPictogram);
  }
}
