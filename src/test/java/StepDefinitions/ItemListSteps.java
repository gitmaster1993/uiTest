package StepDefinitions;

import Models.Article;
import PageObjects.ItemListPage;
import Support.Range;
import com.udojava.evalex.Expression;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ItemListSteps {
  private BaseTestClass base;
  private String allItems;

  public ItemListSteps(BaseTestClass base) {
    this.base = base;
  }

  @Then("^the item list should include worktop (.*)$")
  public void theItemListShouldIncludeA(String items) throws Throwable {
    evaluateItemList(items, ItemListPage.ArticleType.WORKTOP);
  }

  @Then("^the item list should include (.*), (.*) and (.*)$")
  public void theItemListShouldIncludeABC(String sinkItems, String drainerGrooveItems, String tapHoleItems) throws Throwable {
    evaluateItemList(sinkItems, ItemListPage.ArticleType.SINK);
    evaluateItemList(drainerGrooveItems, ItemListPage.ArticleType.DRAINERGROOVES);
    evaluateItemList(tapHoleItems, ItemListPage.ArticleType.TAPHOLE);
  }

  @Then("^the itemlist should include (.*), (.*), (.*), (.*), (.*) and (.*)$")
  public void theItemlistShouldIncludeABCDEF(String cutOutItems, String singleCutItems, String doubleCutItems, String tripleCutItems, String roundedCornerItems, String invertedCornerItems) throws Throwable {
    evaluateItemList(cutOutItems, ItemListPage.ArticleType.CUTOUT);
    evaluateItemList(singleCutItems, ItemListPage.ArticleType.SINGLECUT);
    evaluateItemList(doubleCutItems, ItemListPage.ArticleType.DOUBLECUT);
    evaluateItemList(tripleCutItems, ItemListPage.ArticleType.TRIPLECUT);
    evaluateItemList(roundedCornerItems, ItemListPage.ArticleType.ROUNDEDCORNER);
    evaluateItemList(invertedCornerItems, ItemListPage.ArticleType.INVERTEDCORNER);
  }

  //Then the it should include <worktop articles>, <sink articles>, <drainer groove articles>, <tap hole articles>, <hob cut out articles>, <single cut articles>, <double cut articles>, <triple cut articles>, <rounded corner articles>, <inverted corner articles>, <splashbacks>, <round small cut articles>, <round big cut articles>, <square cut articles>, <wp single cut articles>, <wp double cut articles> and <wp triple cut articles>
  @Then("^the it should include (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*) and (.*)$")
  public void theItShouldIncludeArticles(String worktop, String sink, String drainerGrooves, String tapHoles, String hobCuts, String singleCuts, String doubleCuts, String tripleCuts, String roundedCorners, String invertedCorners, String splashback, String smallRoundCuts, String bigRoundCuts, String squareCuts, String wpSingleCuts, String wpDoubleCuts, String wpTripleCuts) throws Throwable {
    evaluateItemListExcludeNA(worktop, ItemListPage.ArticleType.WORKTOP);
    evaluateItemListExcludeNA(sink, ItemListPage.ArticleType.SINK);
    evaluateItemListExcludeNA(drainerGrooves, ItemListPage.ArticleType.DRAINERGROOVES);
    evaluateItemListExcludeNA(tapHoles, ItemListPage.ArticleType.TAPHOLE);
    evaluateItemListExcludeNA(hobCuts, ItemListPage.ArticleType.CUTOUT);
    evaluateItemListExcludeNA(singleCuts, ItemListPage.ArticleType.SINGLECUT);
    evaluateItemListExcludeNA(doubleCuts, ItemListPage.ArticleType.DOUBLECUT);
    evaluateItemListExcludeNA(tripleCuts, ItemListPage.ArticleType.TRIPLECUT);
    evaluateItemListExcludeNA(splashback, ItemListPage.ArticleType.SPLASHBACK);
    evaluateItemListExcludeNA(roundedCorners, ItemListPage.ArticleType.ROUNDEDCORNER);
    evaluateItemListExcludeNA(invertedCorners, ItemListPage.ArticleType.INVERTEDCORNER);
    evaluateItemListExcludeNA(smallRoundCuts, ItemListPage.ArticleType.WPROUNDCUTSMALL);
    evaluateItemListExcludeNA(bigRoundCuts, ItemListPage.ArticleType.WPROUNDCUTBIG);
    evaluateItemListExcludeNA(squareCuts, ItemListPage.ArticleType.WPSQUARECUT);
    evaluateItemListExcludeNA(wpSingleCuts, ItemListPage.ArticleType.WPSINGLECUT);
    evaluateItemListExcludeNA(wpDoubleCuts, ItemListPage.ArticleType.WPDOUBLECUT);
    evaluateItemListExcludeNA(wpTripleCuts, ItemListPage.ArticleType.WPTRIPLECUT);

    allItems = worktop + " + " + sink + " + " + drainerGrooves + " + " + tapHoles + " + " + hobCuts + " + " + singleCuts + " + " + doubleCuts + " + " + tripleCuts + " + " + roundedCorners + " + " + invertedCorners + " + " + splashback + " + " + smallRoundCuts + " + " + bigRoundCuts + " + " + squareCuts + " + " + wpSingleCuts + " + " + wpDoubleCuts + " + " + wpTripleCuts;
  }

  @And("^the total price, both in the header and in the item list should be correct$")
  public void theTotalPriceBothInTheHeaderAndInTheItemListShouldBeCorrect() throws Throwable {
    String items = allItems.replace(" + NA", "");
    evaluatePrice(items, base.commonTopPage.getPrice());
    base.commonTopPage.scrollDown(1000);
    evaluatePrice(items, base.itemListPage.getTotalPrice());
  }

  private void evaluateItemList(String expectedItemList, ItemListPage.ArticleType articleType) throws InterruptedException {
    if (!expectedItemList.equalsIgnoreCase("NA")) {
      evaluatePrice(expectedItemList, base.itemListPage.getSectionTotalPrice(articleType));
    }
    List<Article> expectedArticleList = getArticleList(expectedItemList);
    List<Article> actualArticleList = base.itemListPage.getAllArticles(articleType);
    if(!CollectionUtils.isEqualCollection(expectedArticleList, actualArticleList)) {
      System.out.println("Expected Items: ");
      for(Article expectedArticle: expectedArticleList) {
        System.out.println(expectedArticle);
      }
      System.out.println("Actual Items: ");
      for(Article actualArticle: actualArticleList) {
        System.out.println(actualArticle);
      }
      Assert.fail("Wrong items in itemList");
    }
  }

  private void evaluateItemListExcludeNA(String expectedItemList, ItemListPage.ArticleType articleType) throws InterruptedException {
    if (!expectedItemList.equalsIgnoreCase("NA")) {
      evaluatePrice(expectedItemList, base.itemListPage.getSectionTotalPrice(articleType));

      List<Article> expectedArticleList = getArticleList(expectedItemList);
      List<Article> actualArticleList = base.itemListPage.getAllArticles(articleType);
      base.itemListPage.clickToggleButton(articleType);
      if (!CollectionUtils.isEqualCollection(expectedArticleList, actualArticleList)) {
        System.out.println("Expected Items: ");
        for (Article expectedArticle : expectedArticleList) {
          System.out.println(expectedArticle);
        }
        System.out.println("Actual Items: ");
        for (Article actualArticle : actualArticleList) {
          System.out.println(actualArticle);
        }
        Assert.fail("Wrong items in itemList");
      }
    }
  }

  private List<Article> getArticleList(String itemList) {
    List<Article> articleList = new ArrayList<Article>();
    Pattern pattern = Pattern.compile("(.*?)\\*(\\(.*?\\))");
    String list = itemList;
    Matcher matcher = pattern.matcher(list);
    while (matcher.find()) {
      String nbrOfItems = matcher.group(1).replace(" + ", "");
      String item = matcher.group(2);
      String articleNbr =  item.substring(item.indexOf("(") + 1, item.indexOf(" "));
      int pricePerItem = Range.getPrice(item);
      BigDecimal totalPrice = new Expression(matcher.group(0).replace(item, Integer.toString(pricePerItem)).replace(" + ", "")).eval();
      articleList.add(new Article(articleNbr, nbrOfItems, String.valueOf(pricePerItem), (totalPrice.setScale(0, BigDecimal.ROUND_HALF_UP)).toString()));
    }
    return articleList;
  }

  public void evaluatePrice(String itemList, String price) throws InterruptedException {
    BigDecimal expectedPrice = Range.evaluatePrice(itemList);
    Assert.assertEquals("Wrong price", expectedPrice.toString(), price);
  }

  @Then("^the (.*) section (.*) indicate that the price is an estimate$")
  public void theSectionEstimateIndicateThatThePriceIsAnEstimate(String section, String estimate) throws Throwable {
    boolean expectedEstimateIndicator = estimate.equalsIgnoreCase("should") ? true : false;
    boolean actualEstimateIndicator = false;
    if (section.equalsIgnoreCase("worktop")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.WORKTOP);
    } else if (section.equalsIgnoreCase("sink")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.SINK);
    } else if (section.equalsIgnoreCase("drainer groove")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.DRAINERGROOVES);
    } else if (section.equalsIgnoreCase("tap hole")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.TAPHOLE);
    } else if (section.equalsIgnoreCase("hob cut")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.CUTOUT);
    } else if (section.equalsIgnoreCase("single cut")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.SINGLECUT);
    } else if (section.equalsIgnoreCase("double cut")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.DOUBLECUT);
    } else if (section.equalsIgnoreCase("triple cut")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.TRIPLECUT);
    } else if (section.equalsIgnoreCase("rounded corner")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.ROUNDEDCORNER);
    } else if (section.equalsIgnoreCase("inverted corner")) {
      actualEstimateIndicator = base.itemListPage.isSectionPriceAnEstimate(ItemListPage.ArticleType.INVERTEDCORNER);
    } else {
      Assert.fail("Unknown item list section " + section);
    }
    Assert.assertEquals("Incorrect price estimation indicator for section " + section, expectedEstimateIndicator, actualEstimateIndicator);
  }

  @And("^by the total price it (.*) indicate that the price is an estimate$")
  public void byTheTotalPriceItEstimateIndicateThatThePriceIsAnEstimate(String estimate) throws Throwable {
    boolean expectedEstimateIndicator = estimate.equalsIgnoreCase("should") ? true : false;
    Assert.assertEquals("Incorrect price estimate disclaimer", expectedEstimateIndicator, base.itemListPage.isPriceEstimateDisclaimerDisplayed());
  }

  @Then("^it should include worktop items and (.*) items$")
  public void itShouldIncludeWorktopItemsCAndSinkItemsD(String section) throws Throwable {
    Assert.assertTrue("Worktop items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WORKTOP));
    if (section.equalsIgnoreCase("sink")) {
      Assert.assertTrue("Sink items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.SINK));
    } else if (section.equalsIgnoreCase("tap hole")) {
      Assert.assertTrue("Tap hole items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.TAPHOLE));
    } else if (section.equalsIgnoreCase("hob cut")) {
      Assert.assertTrue("Hob cut items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.CUTOUT));
    } else if (section.equalsIgnoreCase("single cut")) {
      Assert.assertTrue("Single cut items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.SINGLECUT));
    } else if (section.equalsIgnoreCase("double cut")) {
      Assert.assertTrue("Double cut items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.DOUBLECUT));
    } else if (section.equalsIgnoreCase("triple cut")) {
      Assert.assertTrue("Triple cut items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.TRIPLECUT));
    } else if (section.equalsIgnoreCase("rounded corner")) {
      Assert.assertTrue("Rounded corner items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.ROUNDEDCORNER));
    } else if (section.equalsIgnoreCase("inverted corner")) {
      Assert.assertTrue("Inverted corner items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.INVERTEDCORNER));
    } else if (section.equalsIgnoreCase("kitchen island")) {
      Assert.assertTrue("Kitchen island items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.KITCHENISLAND));
    } else {
      Assert.fail("Unknown item list section " + section);
    }
  }

  @When("^I select to delete the (.*)$")
  public void iSelectToDeleteThe(String section) throws Throwable {
    if (section.equalsIgnoreCase("sink")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.SINK);
    } else if (section.equalsIgnoreCase("tap hole")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.TAPHOLE);
    } else if (section.equalsIgnoreCase("hob cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.CUTOUT);
    } else if (section.equalsIgnoreCase("single cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.SINGLECUT);
    } else if (section.equalsIgnoreCase("double cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.DOUBLECUT);
    } else if (section.equalsIgnoreCase("triple cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.TRIPLECUT);
    } else if (section.equalsIgnoreCase("rounded corner")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.ROUNDEDCORNER);
    } else if (section.equalsIgnoreCase("inverted corner")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.INVERTEDCORNER);
    } else if (section.equalsIgnoreCase("splashback")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.SPLASHBACK);
    } else if (section.equalsIgnoreCase("wall panel round cut small")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.WPROUNDCUTSMALL);
    } else if (section.equalsIgnoreCase("wall panel round cut big")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.WPROUNDCUTBIG);
    } else if (section.equalsIgnoreCase("wall panel square cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.WPSQUARECUT);
    } else if (section.equalsIgnoreCase("wall panel single cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.WPSINGLECUT);
    } else if (section.equalsIgnoreCase("wall panel double cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.WPDOUBLECUT);
    } else if (section.equalsIgnoreCase("wall panel triple cut")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.WPTRIPLECUT);
    } else if (section.equalsIgnoreCase("kitchen island")) {
      base.itemListPage.deleteItem(ItemListPage.ArticleType.KITCHENISLAND);
    } else {
      Assert.fail("Unknown item list section " + section);
    }
  }

  @Then("^it should only include worktop items and not any (.*) items$")
  public void itShouldOnlyIncludeWorktopItemsCAndNotItemsD(String section) throws Throwable {
    Assert.assertTrue("Worktop items was not displayed", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WORKTOP));
    if (section.equalsIgnoreCase("sink")) {
      Assert.assertFalse("Sink items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.SINK));
    } else if (section.equalsIgnoreCase("tap hole")) {
      Assert.assertFalse("Tap hole items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.TAPHOLE));
    } else if (section.equalsIgnoreCase("hob cut")) {
      Assert.assertFalse("Hob cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.CUTOUT));
    } else if (section.equalsIgnoreCase("single cut")) {
      Assert.assertFalse("Single cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.SINGLECUT));
    } else if (section.equalsIgnoreCase("double cut")) {
      Assert.assertFalse("Double cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.DOUBLECUT));
    } else if (section.equalsIgnoreCase("triple cut")) {
      Assert.assertFalse("Triple cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.TRIPLECUT));
    } else if (section.equalsIgnoreCase("rounded corner")) {
      Assert.assertFalse("Rounded corner items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.ROUNDEDCORNER));
    } else if (section.equalsIgnoreCase("inverted corner")) {
      Assert.assertFalse("Inverted corner items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.INVERTEDCORNER));
    } else if (section.equalsIgnoreCase("splashback")) {
      Assert.assertFalse("Single cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.SPLASHBACK));
    } else if (section.equalsIgnoreCase("wall panel round cut small")) {
      Assert.assertFalse("Single cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WPROUNDCUTSMALL));
    } else if (section.equalsIgnoreCase("wall panel round cut big")) {
      Assert.assertFalse("Double cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WPROUNDCUTBIG));
    } else if (section.equalsIgnoreCase("wall panel square cut")) {
      Assert.assertFalse("Single cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WPSQUARECUT));
    } else if (section.equalsIgnoreCase("wall panel single cut")) {
      Assert.assertFalse("Double cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WPSINGLECUT));
    } else if (section.equalsIgnoreCase("wall panel double cut")) {
      Assert.assertFalse("Single cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WPDOUBLECUT));
    } else if (section.equalsIgnoreCase("wall panel triple cut")) {
      Assert.assertFalse("Double cut items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.WPTRIPLECUT));
    } else if (section.equalsIgnoreCase("kitchen island")) {
      Assert.assertFalse("Kitchen island items was wrongly displayed after delete", base.itemListPage.isSectionDisplayed(ItemListPage.ArticleType.KITCHENISLAND));
    } else {
      Assert.fail("Unknown item list section " + section);
    }
  }

  @And("^I select to save my plan$")
  public void iSelectToSaveMyPlan() throws Throwable {
    base.itemListPage.savePlan();
  }

  @Then("^the remove confirmation dialog is shown$")
  public void theRemoveConfirmationDialogIsShown() throws Throwable {
    Assert.assertTrue("Remove confirmation dialog was not displayed", base.itemListPage.isRemoveConfirmationDialogShown());
  }

  @When("^I confirm that I want to remove the selection$")
  public void iConfirmThatIWantToRemoveTheSelection() throws Throwable {
    base.itemListPage.confirmRemoveSelection();
  }

  @When("^I don't confirm that I want to remove the selection$")
  public void iDonTConfirmThatIWantToRemoveTheSelection() throws Throwable {
    base.itemListPage.abortRemoveSelection();
  }

  @And("^the new name of the plan should be displayed on the top of the list$")
  public void theNewNameOfThePlanShouldBeDisplayedOnTheTopOfTheList() throws Throwable {
    Assert.assertEquals("Worktop name not correct in itemlist", base.savedPlanName, base.itemListPage.getWorktopName());
  }

  @When("^I select to go back$")
  public void iSelectToGoBack() throws Throwable {
    base.commonTopPage.navigateTo("back");
  }

  @And("^the worktop name (.*) be editable$")
  public void theWorktopNameShouldNotBeEditable(String assertion) throws Throwable {
    boolean editable = assertion.equalsIgnoreCase("should") ? true : false;
    Assert.assertEquals("Wrong edit state of worktop name", editable, base.itemListPage.isWorktopNameEditable());
  }

  @When("^I select to edit the name of the worktop$")
  public void iSelectToEditTheNameOfTheWorktop() throws Throwable {
    base.savedPlanName = base.itemListPage.getWorktopName();
    Random random = new Random();
    base.updatedPlanName = "CWT" + random.nextInt(100);
    base.itemListPage.editWorktopName(base.updatedPlanName);
    Assert.assertEquals("Worktop name not correct", base.updatedPlanName, base.itemListPage.getWorktopName());

  }

  @And("^country should be displayed$")
  public void countryShouldBeDisplayed() throws Throwable {
    Assert.assertTrue("Country code missing", base.itemListPage.isCountryCodeAvailable());
    String countryCode = base.itemListPage.getCountryCode();
    System.out.println("Country is: "+ countryCode);
  }

  @And("^I select to print the page$")
  public void iSelectToPrintThePage() throws Throwable {
    base.itemListPage.clickPrintPage();
  }


  @Then("^the print view is displayed$")
  public void thePrintPageIsDisplayed() throws Throwable {
    Assert.assertTrue("The print page is not displayed", base.itemListPage.isPrintPageDisplayed());
    Assert.assertTrue("The print images are not displayed", base.itemListPage.arePrintImagesDisplayed());
    Assert.assertTrue("The print total price is not displayed", base.itemListPage.isPrintTotalPriceDisplayed());
  }

  @And("^I select to start over$")
  public void iSelectToStartOver() throws Throwable {
    Assert.assertTrue("The start over button was not found", base.itemListPage.isStartOverBtnDisplayed());
    base.itemListPage.clickStartOverBtn();
  }

  @When("^I delete the freestanding hob from item list$")
  public void iDeleteTheFreestandingHobFromItemList() throws Throwable {
    base.itemListPage.deleteItem(ItemListPage.ArticleType.FREESTANDINGHOB);
    base.itemListPage.confirmRemoveSelection();
  }
}
