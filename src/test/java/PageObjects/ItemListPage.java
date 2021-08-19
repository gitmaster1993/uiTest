package PageObjects;

import Models.Article;
import Support.ExecutionUtil;
import Support.Range;
import Support.SeleniumSupport;
import gherkin.lexer.En;
import org.apache.maven.shared.utils.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ItemListPage extends SeleniumSupport {

  private WebDriver driver;

  public ItemListPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public enum ArticleType {
    WORKTOP("item-group-worktop"),
    SINK("item-group-sink"),
    DRAINERGROOVES("item-group-drainerGrooves"),
    TAPHOLE("item-group-tapHole"),
    CUTOUT("item-group-cutout"),
    FREESTANDINGHOB("item-group-freestandingHob"),
    SINGLECUT("item-group-singleCut"),
    DOUBLECUT("item-group-doubleCut"),
    TRIPLECUT("item-group-tripleCut"),
    ROUNDEDCORNER("item-group-roundedCorner"),
    INVERTEDCORNER("item-group-invertedCorner"),
    SPLASHBACK("item-group-splashback"),
    WPROUNDCUTSMALL("item-group-wallPanelRoundCutSmall"),
    WPROUNDCUTBIG("item-group-wallPanelRoundCutBig"),
    WPSQUARECUT("item-group-wallPanelSquareCut"),
    WPSINGLECUT("item-group-wallPanelSingleCut"),
    WPDOUBLECUT("item-group-wallPanelDoubleCut"),
    WPTRIPLECUT("item-group-wallPanelTripleCut"),
    KITCHENISLAND("item-group-kitchen-island");

    private String id;
    ArticleType(String id) {
      this.id=id;
    }

    public String getId() {
      return id;
    }
  }

  /* PAGE ELEMENTS */
  WebElement sectionTotalPrice(ArticleType articleType) {
    String xpath = "//*[@id=\"" + articleType.getId() + "\"]/div[1]/div[2]/div[2]/div[2]/div";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement sectionPriceEstimateIndicator(ArticleType articleType) {
    String xpath = "//*[@id=\"" + articleType.getId() + "\"]/div[1]/div[2]/div[2]/div[2]/div[2]";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement sectionDeleteBtn(ArticleType articleType) {
    String removeString = articleType.getId();
    removeString = removeString.replace("item-group-", "");
    return driver.findElement(By.id(String.format("%s-remove", removeString)));
  }

  WebElement worktopOverhang() {
    String image = "item-group-worktop";
    String xpath = "//*[@id=\"item-group-worktop" + "\"]/div[1]/div[2]/div[1]/div[1]/div[1]";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement showDetailBtn(ArticleType articleType){
    String xpath = "//*[@id=\"" + articleType.getId() + "\"]/div[2]/div[1]/div[1]";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement articleNumber(ArticleType articleType, int itemNbr) {
    String xpath = "//*[@id=\"" + articleType.getId() + "\"]/div[2]/div[1]/div[2]/div[" +(itemNbr) + "]/div[2]/div[2]";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement articleNbrOfItems(ArticleType articleType, int itemNbr) {
    String xpath = "//*[@id=\"" + articleType.getId() + "\"]/div[2]/div[1]/div[2]/div[" +itemNbr + "]/div[1]/div[3]/div[1]/span";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement articlePricePerItem(ArticleType articleType, int itemNbr) {
    String xpath = "//*[@id=\"" + articleType.getId() + "\"]/div[2]/div[1]/div[2]/div[" +itemNbr + "]/div[1]/div[3]/div[2]";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement articlePrice(ArticleType articleType, int itemNbr) {
    String xpath = "//*[@id=\"" + articleType.getId() + "\"]/div[2]/div[1]/div[2]/div[" +itemNbr + "]/div[1]/div[3]/div[3]";
    return driver.findElement(By.xpath(xpath));
  }

  @FindBy(id = "price-section-estimate-disclaimer")
  WebElement priceEstimateDisclaimer;

  @FindBy(id = "item-list-total-price-price-section")
  WebElement totalPrice;

  @FindBy(id = "item-list-total-price-price-section-print")
  WebElement printTotalPrice;

  @FindBy(id = "save-worktop-button")
  WebElement saveBtn;

  @FindBy(id = "item-list-page")
  WebElement pageContainer;

  @FindBy(id = "modal-remove-confirm-dialog")
  WebElement removeConfirmationDialog;

  @FindBy(id = "remove-confirm-dialog-remove")
  WebElement removeConfirmationDialogRemoveButton;

  @FindBy(id = "remove-confirm-dialog-return")
  WebElement removeConfirmationDialogReturnButton;

  @FindBy(id = "worktop-name")
  WebElement worktopNameField;

  @FindBy(id = "worktop-name-input")
  WebElement worktopNameFieldEditMode;

  @FindBy(id = "worktop-name-edit")
  WebElement worktopNameEditBtn;

  @FindBy(id = "worktop-name-confirm")
  WebElement worktopNameEditConfirmBtn;

  @FindBy(id = "item-list-country-code")
  WebElement countryCode;

  @FindBy(id = "print-worktop-button")
  WebElement printBtn;

  @FindBy(id = "content-to-print")
  WebElement printPage;

  @FindBy(id = "print-image-worktop-image-image-slide-image")
  WebElement printWorkTopImage;

  @FindBy(id = "print-image-edge-image-image-slide-image")
  WebElement printEdgeImage;

  @FindBy(id = "print-image-sink-image-image-slide-image")
  WebElement printSinkImage;

  @FindBy(id = "start-over-button")
  WebElement startOverbtn;

  @FindBy(id = "item-list-total-price-price-section-print")
  WebElement totalPriceForPrintPage;

  @FindBy(id = "item-group-freestandingHob")
  WebElement freestandingHobItem;

  @FindBy(id = "freestandingHob-image")
  WebElement freestandingHobItemImage;

  @FindBy(id = "freestandingHob-item-amount")
  WebElement nrOfFreestandingHobs;

  /* PAGE METHODS */
  public boolean isPageDisplayed() {
    return isElementDisplayed(driver, pageContainer);
  }


  public String getSectionTotalPrice(ArticleType articleType) {
    return cleanArticleFieldValue(sectionTotalPrice(articleType).getText());
  }

  public boolean isSectionPriceAnEstimate(ArticleType articleType) {
    driver.manage().timeouts().implicitlyWait(1, SECONDS);
    boolean elementFound = true;
    try {
      sectionPriceEstimateIndicator(articleType);
    } catch (NoSuchElementException e) {
      elementFound = false;
    }
    driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), SECONDS);
    return elementFound;
  }

  public void deleteItem(ArticleType articleType) {
    click(driver, sectionDeleteBtn(articleType));
  }

  public boolean isSectionDisplayed(ArticleType articleType) {
    driver.manage().timeouts().implicitlyWait(1, SECONDS);
    boolean elementFound = true;
    try {
      sectionTotalPrice(articleType);
    } catch (NoSuchElementException e) {
      elementFound = false;
    }
    driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), SECONDS);
    return elementFound;
  }

  public boolean isPriceEstimateDisclaimerDisplayed() {
    return isElementDisplayed(driver, priceEstimateDisclaimer, 1);
  }

  public List<Article> getAllArticles(ArticleType articleType) throws InterruptedException {
    List<Article> articles = new ArrayList<Article>();
    try {
      int itemNbr = 1;
      driver.manage().timeouts().implicitlyWait(1, SECONDS);
      click(driver, showDetailBtn(articleType));
      Thread.sleep(500);
      while (true) {
          String articleNbr = cleanArticleFieldValue(articleNumber(articleType, itemNbr).getText());
          String nbrOfItems = cleanArticleFieldValue(articleNbrOfItems(articleType, itemNbr).getText());
          String pricePerItem = cleanArticleFieldValue(articlePricePerItem(articleType, itemNbr).getText());
          String totalPrice = cleanArticleFieldValue(articlePrice(articleType, itemNbr).getText());
          articles.add(new Article(articleNbr, nbrOfItems, pricePerItem, totalPrice));
          itemNbr++;
      }
    } catch (NoSuchElementException e) {
      driver.manage().timeouts().implicitlyWait(ExecutionUtil.getTimeout(), SECONDS);
      return articles;
    }
  }

  public void clickToggleButton(ArticleType articleType) {
    click(driver, showDetailBtn(articleType));
  }

  public String getTotalPrice() {
    return cleanArticleFieldValue(totalPrice.getText());
  }

  public void savePlan() {
    click(driver, saveBtn);
  }

  private String cleanArticleFieldValue(String value) {
    String newString = value;
    if (StringUtils.countMatches(value, ".") > 1) {
      //Articlenumber, remove all .
      newString = newString.replace(".","");
    }
    newString = newString.replace(",", "."); //Change decimal separator
    newString = newString.replace(" ", ""); //Remove thousand separator
    newString = newString.replace("kr", ""); //Remove kr
    newString = newString.replace("\n", ""); //Remove newline
    return newString;
  }

  public boolean isRemoveConfirmationDialogShown() {
    return isElementDisplayed(driver, removeConfirmationDialog);
  }

  public void confirmRemoveSelection() {
    click(driver, removeConfirmationDialogRemoveButton);
  }

  public void abortRemoveSelection() {
    click(driver, removeConfirmationDialogReturnButton);
  }

  public String getWorktopName() {
    return worktopNameField.getText();
  }

  public boolean isWorktopNameEditable() {
    return isElementDisplayed(driver, worktopNameEditBtn);
  }

  public void editWorktopName(String newName) {
    click(driver, worktopNameEditBtn);
    worktopNameFieldEditMode.sendKeys(newName);
    click(driver, worktopNameEditConfirmBtn);
  }

  public boolean isCountryCodeAvailable() {
    return isElementDisplayed(driver, countryCode);
  }
  public String getCountryCode() {
    return countryCode.getText();
  }

  public void clickPrintPage() {
    if (isElementDisplayed(driver,printBtn)){
      click(driver, printBtn);
    }
    else {
      Assert.fail("Print button not found");
    }
  }

  public boolean isPrintPageDisplayed() {
    return isElementDisplayed(driver, printPage);
  }

  public boolean arePrintImagesDisplayed() {
    boolean imageAvailable = true;
    if (!isElementDisplayed(driver, printWorkTopImage)) {
      imageAvailable = false;
    }
    if (!isElementDisplayed(driver, printEdgeImage)) {
      imageAvailable = false;
    }
    if (!isElementDisplayed(driver, printSinkImage)) {
      imageAvailable = false;
    }
    return imageAvailable;
  }

  public boolean isPrintTotalPriceDisplayed() {
    return isElementDisplayed(driver, printTotalPrice);
  }

  public boolean isOverhangMentioned() {
    if (isElementDisplayed(driver, worktopOverhang())) {
      String worktopText = worktopOverhang().getText();
      if (worktopText.contains("overhang")) {
        return true;
      }
    }
    return false;
  }

  public boolean isStartOverBtnDisplayed() {
    return isElementDisplayed(driver, startOverbtn);
  }

  public void clickStartOverBtn() {
    click(driver, startOverbtn);
  }

  public int getTotalPriceForPrintPage() {
    int price = Integer.parseInt(totalPriceForPrintPage.getText());
    return price;
  }

  public boolean isFreestandingHobAvailableInItemList() {
    if (isElementDisplayed(driver, freestandingHobItem)) {
      if (isElementDisplayed(driver, freestandingHobItemImage)) {
        return true;
      }
    }
    return false;
  }

  public boolean isFreestandingHobAvailableInItemList(int nrOfHobs) {
    if (isElementDisplayed(driver, freestandingHobItem)) {
      if (isElementDisplayed(driver, nrOfFreestandingHobs)) {
        String hobs = nrOfFreestandingHobs.getText();
        int actualNrOfHobs = Integer.parseInt(hobs.replace("x ", ""));
        if (actualNrOfHobs == nrOfHobs) {
          return true;
        }
      }
    }
    return false;
  }
}
