package StepDefinitions;

import PageObjects.*;
import Support.Range;
import Support.SeleniumController;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class BaseTestClass
{
  public final WebDriver driver;
  public final SeleniumController controller;
  public final StartPage startPage;
  public final CommonTopPage commonTopPage;
  public final PlanPage planPage;
  public final StylePage stylePage;
  public final SinkPage sinkPage;
  public final CutOutsPage cutOutsPage;
  public final ItemListPage itemListPage;
  public final ComparePage comparePage;
  public final KitchenIslandPage kitchenIslandPage;

  //Parameters shared between steps
  public String currentTab;
  public String currentShape;
  public String savedPlanName;
  public String updatedPlanName;
  public String savedPlanCode;

  public BaseTestClass() throws Exception {
    controller = new SeleniumController();
    driver = controller.getDriver();
    startPage = new StartPage(driver);
    commonTopPage = new CommonTopPage(driver);
    planPage = new PlanPage(driver);
    stylePage = new StylePage(driver);
    sinkPage = new SinkPage(driver);
    cutOutsPage = new CutOutsPage(driver);
    itemListPage = new ItemListPage(driver);
    comparePage = new ComparePage(driver);
    kitchenIslandPage = new KitchenIslandPage(driver);
  }

  public void evaluatePrice(String itemList) throws InterruptedException {
    BigDecimal expectedPrice = Range.evaluatePrice(itemList);
    //Add sleep to get price after size is changed
    Thread.sleep(1000);
    String actualPrice = commonTopPage.getPrice();
    if (!expectedPrice.toString().equals(actualPrice)) {
      System.out.println("Expected Items:\n" + itemList);
      System.out.println("Actual Items:\n" + commonTopPage.getItemList());
//      Assert.assertEquals("Price not correct", expectedPrice.toString(), actualPrice);
    }
  }
}
