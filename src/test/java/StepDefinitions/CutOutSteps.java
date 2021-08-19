package StepDefinitions;

import PageObjects.CutOutsPage;
import Support.Range;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
public class CutOutSteps {
  private BaseTestClass base;
  private int nbrOfHobCutOuts;
  private int nbrOfRoundedCorners;
  private int nbrOfInvertedCorners;
  private int nbrOfSingleCuts;
  private int nbrOfDoubleCuts;
  private int nbrOfTripleCuts;

  public CutOutSteps(BaseTestClass base) {
    this.base = base;
  }

  @When("^I select to add (\\d+) hob cut outs, (\\d+) rounded corners, (\\d+) inverted corners, (\\d+) single cuts, (\\d+) double cuts and (\\d+) triple cuts$")
  public void iSelectToAdd(int hobCuts, int roundedCuts, int invertedCuts, int singleCuts, int doubleCuts, int tripleCuts) throws Throwable {
    nbrOfHobCutOuts = hobCuts;
    nbrOfRoundedCorners = roundedCuts;
    nbrOfInvertedCorners = invertedCuts;
    nbrOfSingleCuts = singleCuts;
    nbrOfDoubleCuts = doubleCuts;
    nbrOfTripleCuts = tripleCuts;
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.HOB, hobCuts);
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.ROUNDED, roundedCuts);
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.INVERTED, invertedCuts);
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.SINGLE, singleCuts);
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.DOUBLE, doubleCuts);
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.TRIPLE, tripleCuts);
  }

  @Then("^each cut out section should display the amount and price of those cut outs$")
  public void eachCutOutSectionShouldDisplayTheAmountAndPriceOfThoseCutOuts() throws Throwable {
    Assert.assertEquals("Incorrect number of hob cuts", nbrOfHobCutOuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.HOB));
    Assert.assertEquals("Incorrect number of rounded corners", nbrOfRoundedCorners, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.ROUNDED));
    Assert.assertEquals("Incorrect number of inverted corners", nbrOfInvertedCorners, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.INVERTED));
    Assert.assertEquals("Incorrect number of single cuts", nbrOfSingleCuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.SINGLE));
    Assert.assertEquals("Incorrect number of double cuts", nbrOfDoubleCuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.DOUBLE));
    Assert.assertEquals("Incorrect number of triple cuts", nbrOfTripleCuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.TRIPLE));

    Assert.assertEquals("Incorrect price for hob cuts", nbrOfHobCutOuts*Range.getPrice("(10345577 Cut Out Laminate)"), base.cutOutsPage.getPriceOfCutOuts(CutOutsPage.CutOutType.HOB));
    Assert.assertEquals("Incorrect price for rounded corners", nbrOfRoundedCorners*Range.getPrice("(10345577 Rounded Corner Laminate)"), base.cutOutsPage.getPriceOfCutOuts(CutOutsPage.CutOutType.ROUNDED));
    Assert.assertEquals("Incorrect price for inverted corners", nbrOfInvertedCorners*Range.getPrice("(10345577 Inverted Corner Laminate)"), base.cutOutsPage.getPriceOfCutOuts(CutOutsPage.CutOutType.INVERTED));
    Assert.assertEquals("Incorrect price for single cuts", nbrOfSingleCuts*Range.getPrice("(10345577 Single Cut Laminate)"), base.cutOutsPage.getPriceOfCutOuts(CutOutsPage.CutOutType.SINGLE));
    Assert.assertEquals("Incorrect price for double cuts", nbrOfDoubleCuts*Range.getPrice("(10345577 Double Cut Laminate)"), base.cutOutsPage.getPriceOfCutOuts(CutOutsPage.CutOutType.DOUBLE));
    Assert.assertEquals("Incorrect price for triple cuts", nbrOfTripleCuts*Range.getPrice("(10345577 Triple Cut Laminate)"), base.cutOutsPage.getPriceOfCutOuts(CutOutsPage.CutOutType.TRIPLE));
  }

  @Then("^when I click the image of a hob cut out, rounded corner, inverted corner, single cut, double cut or triple cut an overlay image should be displayed$")
  public void whenIClickTheImageOfAHobCutOutRoundedCornerInvertedCornerSingleCutDoubleCutOrTripleCutAnOverlayImageShouldBeDisplayed() throws Throwable {
    base.cutOutsPage.clickCutOutImage(CutOutsPage.CutOutType.HOB);
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for hob cut out selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.cutOutsPage.clickCutOutImage(CutOutsPage.CutOutType.ROUNDED);
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for rounded corner selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.cutOutsPage.clickCutOutImage(CutOutsPage.CutOutType.INVERTED);
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for inverted corner selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.cutOutsPage.clickCutOutImage(CutOutsPage.CutOutType.SINGLE);
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for single cut selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.cutOutsPage.clickCutOutImage(CutOutsPage.CutOutType.DOUBLE);
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for double cut selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
    Thread.sleep(1000);

    base.cutOutsPage.clickCutOutImage(CutOutsPage.CutOutType.TRIPLE);
    Thread.sleep(1000);
    Assert.assertTrue("Overlay image was not displayed for triple cut selection", base.commonTopPage.isOverlayImageDisplayed());
    base.commonTopPage.closeOverlayImage();
  }

  @And("^I add (\\d+) rounded corner$")
  public void iAddRoundedCorner(int nbrOfRoundedCorners) throws Throwable {
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.ROUNDED, nbrOfRoundedCorners);
  }

  @And("^scrolling down to the (.*) cut outs selector$")
  public void scrollingDownToTheCutOutsSelector(String selector) throws Throwable {
    base.cutOutsPage.scrollToSelector(selector);
  }

  @And("^I have selected (.*) hob cut outs, (.*) rounded corners, (.*) inverted corners, (.*) single cuts, (.*) double cuts and (.*) triple cuts in the cut outs page$")
  public void iHaveSelectedHobCutOutsRoundedCornersInvertedCornersSingleCutsDoubleCutsAndTripleCuts(String hobCuts, String roundedCorners, String invertedCorners, String singleCuts, String doubleCuts, String tripleCuts) throws Throwable {
    base.commonTopPage.navigateTo("cut-outs");
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.HOB, Integer.parseInt(hobCuts));
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.ROUNDED, Integer.parseInt(roundedCorners));
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.INVERTED, Integer.parseInt(invertedCorners));
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.SINGLE, Integer.parseInt(singleCuts));
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.DOUBLE, Integer.parseInt(doubleCuts));
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.TRIPLE, Integer.parseInt(tripleCuts));
  }

  @And("^I have added a (.*)$")
  public void iHaveAddedACutOut(String cut) throws Throwable {
    if (cut.equalsIgnoreCase("hob cut")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.HOB, 1);
    } else if (cut.equalsIgnoreCase("rounded corner")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.ROUNDED, 1);
    } else if (cut.equalsIgnoreCase("inverted corner")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.INVERTED, 1);
    } else if (cut.equalsIgnoreCase("single cut")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.SINGLE, 1);
    } else if (cut.equalsIgnoreCase("double cut")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.DOUBLE, 1);
    } else if (cut.equalsIgnoreCase("triple cut")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.TRIPLE, 1);
    } else {
      Assert.fail("Unknown cutout " + cut);
    }
  }

  @Then("^(\\d+) hob cut outs, (\\d+) rounded corners, (\\d+) inverted corners, (\\d+) single cuts, (\\d+) double cuts and (\\d+) triple cuts should have been added$")
  public void hobCutOutsRoundedCornersInvertedCornersSingleCutsAndTripleCutsShouldHaveBeenAdded(int hobCuts, int roundedCorners, int invertedCorners, int singleCuts, int doubleCuts, int tripleCuts) throws Throwable {
    Assert.assertEquals("Incorrect number of hob cuts", hobCuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.HOB));
    Assert.assertEquals("Incorrect number of rounded corners", roundedCorners, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.ROUNDED));
    Assert.assertEquals("Incorrect number of inverted corners", invertedCorners, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.INVERTED));
    Assert.assertEquals("Incorrect number of single cuts", singleCuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.SINGLE));
    Assert.assertEquals("Incorrect number of double cuts", doubleCuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.DOUBLE));
    Assert.assertEquals("Incorrect number of triple cuts", tripleCuts, base.cutOutsPage.getNbrOfCutOuts(CutOutsPage.CutOutType.TRIPLE));
  }

  @Then("^(\\d+) (.*) cut outs should be available$")
  public void CutOutsShouldBeAvailable(int nrOfCuts, CutOutsPage.CutOutType cutType) throws Throwable {
    Assert.assertEquals("Incorrect number of cut outs available", nrOfCuts, base.cutOutsPage.getNbrOfCutOuts(cutType));
  }

  @And("^I select to add (\\d+) (.*) cut out$")
  public void iSelectToAddCutOut(int nbrOfCuts, String cut) throws Throwable {
    if (cut.equalsIgnoreCase("hob")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.HOB, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("rounded corner")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.ROUNDED, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("inverted corner")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.INVERTED, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("single")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.SINGLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("double")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.DOUBLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("triple")) {
      base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.TRIPLE, nbrOfCuts);
    } else {
      Assert.fail("Unknown cutout " + cut);
    }
  }

  @And("^I add (\\d+) (.*) cut out$")
  public void iAddCutOut(int nbrOfCuts, String cut) throws Throwable {
    if (cut.equalsIgnoreCase("hob")) {
      base.cutOutsPage.addCutOut(CutOutsPage.CutOutType.HOB, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("rounded corner")) {
      base.cutOutsPage.addCutOut(CutOutsPage.CutOutType.ROUNDED, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("inverted corner")) {
      base.cutOutsPage.addCutOut(CutOutsPage.CutOutType.INVERTED, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("single")) {
      base.cutOutsPage.addCutOut(CutOutsPage.CutOutType.SINGLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("double")) {
      base.cutOutsPage.addCutOut(CutOutsPage.CutOutType.DOUBLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("triple")) {
      base.cutOutsPage.addCutOut(CutOutsPage.CutOutType.TRIPLE, nbrOfCuts);
    } else {
      Assert.fail("Unknown cutout " + cut);
    }
  }

  @When("^I select to add (\\d+) hob cut outs$")
  public void iSelectToAddHobCutOuts(int nbrOfCuts) throws Throwable {
    base.cutOutsPage.addCutOut(CutOutsPage.CutOutType.HOB, nbrOfCuts);
  }

  @Then("^wall panel cut outs should be disabled and collapsed$")
  public void wallPanelCutOutsShouldBeDisabledAndCollapsed() throws Throwable {
    Assert.assertTrue("Wall panel cut outs are collapsed when not expected", base.cutOutsPage.isWallPanelCutOutsCollapsed());
    Assert.assertTrue("Wall panel cut outs are enabled when not expected", base.cutOutsPage.isWallPanelCutOutsDisabled());
  }

  @Then("^wall panel cut outs should be enabled and not collapsed$")
  public void wallPanelCutOutsShouldBeEnabledAndNotCollapsed() throws Throwable {
    Assert.assertTrue("Wall panel cut outs are collapsed when not expected", base.cutOutsPage.isWallPanelCutOutsUncollapsed());
    Assert.assertTrue("Wall panel cut outs are disabled when not expected", base.cutOutsPage.isWallPanelCutOutsEnabled());
  }

  @When("^I select wall panel cut outs (\\d+) round small, (\\d+) round big, (\\d+) square, (\\d+) single, (\\d+) double, (\\d+) tripple$")
  public void iSelectWallPanelCutOutsRoundSmallRoundBigSquareSingleDoubleTripple(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I add (\\d+) (.*) wall panel cut out$")
  public void iAddWallPanelCutOut(int nbrOfCuts, String cut) throws Throwable {
    if (cut.equalsIgnoreCase("round small")) {
      base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.ROUND_SMALL, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("round big")) {
      base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.ROUND_BIG, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("square")) {
      base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.SQUARE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("single")) {
      base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.SINGLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("double")) {
      base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.DOUBLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("triple")) {
      base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.TRIPLE, nbrOfCuts);
    } else {
      Assert.fail("Unknown cutout " + cut);
    }
  }

  @When("^I remove (\\d+) (.*) wall panel cut out$")
  public void iRemoveWallPanelCutOut(int nbrOfCuts, String cut) throws Throwable {
    if (cut.equalsIgnoreCase("round small")) {
      base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.ROUND_SMALL, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("round big")) {
      base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.ROUND_BIG, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("square")) {
      base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.SQUARE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("single")) {
      base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.SINGLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("double")) {
      base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.DOUBLE, nbrOfCuts);
    } else if (cut.equalsIgnoreCase("triple")) {
      base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.TRIPLE, nbrOfCuts);
    } else {
      Assert.fail("Unknown cutout " + cut);
    }
  }

  @When("^I select to add (\\d+) round small cut outs, (\\d+) round big cut outs, (\\d+) square cut outs, (\\d+) single cuts, (\\d+) double cuts and (\\d+) triple cuts$")
  public void iSelectToAddARoundSmallCutOutsBRoundBigCutOutsCSquareCutOutsDSingleCutsEDoubleCutsAndFTripleCuts(int roundSmallCuts, int roundBigCuts, int squareCuts, int singleCuts, int doubleCuts, int tripleCuts) throws Throwable {
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.ROUND_SMALL, roundSmallCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.ROUND_BIG, roundBigCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.SQUARE, squareCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.SINGLE, singleCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.DOUBLE, doubleCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.TRIPLE, tripleCuts);
  }

  @When("^I select to remove (\\d+) round small cut outs, (\\d+) round big cut outs, (\\d+) square cut outs, (\\d+) single cuts, (\\d+) double cuts and (\\d+) triple cuts$")
  public void iSelectToRemoveARoundSmallCutOutsBRoundBigCutOutsCSquareCutOutsDSingleCutsEDoubleCutsAndFTripleCuts(int roundSmallCuts, int roundBigCuts, int squareCuts, int singleCuts, int doubleCuts, int tripleCuts) throws Throwable {
    base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.ROUND_SMALL, roundSmallCuts);
    base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.ROUND_BIG, roundBigCuts);
    base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.SQUARE, squareCuts);
    base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.SINGLE, singleCuts);
    base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.DOUBLE, doubleCuts);
    base.cutOutsPage.removeWallCutOuts(CutOutsPage.CutOutWallType.TRIPLE, tripleCuts);
  }

  @And("^I have selected (\\d+) small round cuts, (\\d+) big round cuts, (\\d+) square cuts, (\\d+) single cuts, (\\d+) double cuts and (\\d+) triple cuts in the cut outs page$")
  public void iHaveSelectedRoundCutsSmallSmallRoundCutsRoundCutsBigBigRoundCutsSquareCutsSquareCutsWpSingleCutsSingleCutsWpDoubleCutsDoubleCutsAndWpTripleCutsTripleCutsInTheCutOutsPage(int roundSmallCuts, int roundBigCuts, int squareCuts, int singleCuts, int doubleCuts, int tripleCuts) throws Throwable {
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.ROUND_SMALL, roundSmallCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.ROUND_BIG, roundBigCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.SQUARE, squareCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.SINGLE, singleCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.DOUBLE, doubleCuts);
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.TRIPLE, tripleCuts);
  }

  @And("^I select to add (\\d+) round small cut outs$")
  public void iSelectToAddRoundSmallCutOuts(int roundSmallCuts) throws Throwable {
    base.cutOutsPage.addWallCutOuts(CutOutsPage.CutOutWallType.ROUND_SMALL, roundSmallCuts);
  }

  @And("^I select to add (\\d+) freestanding hob$")
  public void iSelectToAddFreestandingHob(int nbrOfHobs) throws Throwable {
    base.cutOutsPage.addCutOuts(CutOutsPage.CutOutType.FREESTANDINGHOB, nbrOfHobs);
  }

  @And("^I set the freestanding hob width to (\\d+) mm$")
  public void iSetTheFrestandingHobWidthToMm(int width)
  {
   Assert.assertTrue("freestanding hob width not found", base.cutOutsPage.setFreestandingHobWidth(width));
  }

  @Then("^(.*) freestanding hob is included in the item list$")
  public void freestandingHobIsIncludedInTheItemList(String option) throws Throwable {
    if (option.equals("no")) {
      Assert.assertFalse("A freestanding hob is available in item list when not expected", base.itemListPage.isFreestandingHobAvailableInItemList());
    }
    else {
      int nrOfHobs = Integer.parseInt(option);
      Assert.assertTrue("Wrong number of freestanding hobs are available in item list", base.itemListPage.isFreestandingHobAvailableInItemList(nrOfHobs));
    }
  }
}
