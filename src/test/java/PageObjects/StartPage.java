package PageObjects;

import Support.SeleniumSupport;
import Support.ExecutionUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends SeleniumSupport{

  private WebDriver driver;

  public StartPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  /* PAGE ELEMENTS */
  @FindBy(id = "createNewButton")
  WebElement createNewBtn;

  @FindBy(id = "showLoadInputButton")
  WebElement loadBtn;

  @FindBy(id = "loadInputField")
  WebElement loadInputField;

  @FindBy(id = "searchCodeButton")
  WebElement searchCodeBtn;

  @FindBy(id = "planSaved-dialog")
  WebElement planSavedDialog;

  @FindBy(id = "dialog-close")
  WebElement planSavedDialogExitBtn;

  @FindBy(id = "planSaved-code")
  WebElement planSavedCode;

  @FindBy(id = "virtual-keyboard")
  WebElement virtualKeyboard;

  @FindBy(id = "saved-worktops-list")
  WebElement savedPlansList;

  @FindBy(id = "remove-confirm-dialog")
  WebElement deleteSavedPlanDialog;

  @FindBy(id = "remove-confirm-dialog-remove")
  WebElement dismissDeleteSavedPlanDialogRemove;

  @FindBy(id = "remove-confirm-dialog-return")
  WebElement dismissDeleteSavedPlanDialogUndo;

  @FindBy(id = "compareButton")
  WebElement compareBtn;

  @FindBy(id = "saved-plan-checkbox-0")
  WebElement checkbox1;

  @FindBy(id = "saved-plan-checkbox-1")
  WebElement checkbox2;

  @FindBy(id = "saved-plan-checkbox-2")
  WebElement checkbox3;

  @FindBy(id = "saved-plan-checkbox-3")
  WebElement checkbox4;

  WebElement savedWorktopName(int position, boolean editMode) {
    int pos = position + 2;
    String append = editMode ? "input" : "div[1]";
    String xpath = "//*[@id=\"saved-worktops-list\"]/div[" + pos + "]/div[1]/div[2]/" + append;
    return driver.findElement(By.xpath(xpath));
  }

  WebElement compareButtonText() {
    String xpath = "//*[@id=\"compareButton\"]/div[1]";
    return driver.findElement(By.xpath(xpath));
  }
  WebElement savedWorktopCode(int position) {
    int pos = position + 2;
    String xpath = "//*[@id=\"saved-worktops-list\"]/div[" + pos + "]/div[1]/div[2]/div[2]";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement savedWorktopEditSaveBtn(int position) {
    int pos = position + 2;
    String xpath = "//*[@id=\"saved-worktops-list\"]/div[" + pos + "]/div[1]/div[3]/div[1]";
    return driver.findElement(By.xpath(xpath));
  }

  WebElement savedWorktopDeleteBtn(int position) {
    int pos = position + 2;
    String xpath = "//*[@id=\"saved-worktops-list\"]/div[" + pos + "]/div[1]/div[3]/div[2]";
    return driver.findElement(By.xpath(xpath));
  }


  /* PAGE METHODS */

  public boolean isPageDisplayed() {
    return isElementDisplayed(driver, createNewBtn);
  }

  public void createNewPlan() {
    click(driver, createNewBtn);
  }

  public boolean isPlanSavedDialogDisplayed() {
    return isElementDisplayed(driver, planSavedDialog);
  }

  public String getSavedPlanCodeFromDialog() {
    return planSavedCode.getText();
  }

  public void closePlanSavedDialog() {
    click(driver, planSavedDialogExitBtn);
  }

  public void loadSavedPlan(String code) {
    click(driver, loadBtn);
    loadInputField.sendKeys(code);
    click(driver, searchCodeBtn);
  }

  public void clickExistingCodeField() {
    click(driver, loadBtn);
  }

  public boolean isVirtualKeyboardDisplayed() {
    return isElementDisplayed(driver, virtualKeyboard);
  }

  public void enterCodeFromVirtualKeyboard(String code) {
    for(char x : code.toCharArray())  {
      click(driver, virtualKeyboard.findElement(By.xpath("//*[text() = '" + String.valueOf(x) + "']")));
    }
  }

  public String getEnteredCode() {
    return loadInputField.getAttribute("value");
  }

  public boolean isSavedPlansListDisplayed() {
    return isElementDisplayed(driver, savedPlansList, 2);
  }

  public String getSavedPlanCodeFromList(int position) {
    String code = savedWorktopCode(position).getText();
    code = code.substring(code.indexOf(":")+2);
    if (code.contains("\n")) {
      code = code.split("\n")[0];
    }
    else
      code = code.split("Last")[0];
    return code;
  }

  public void selectSavedPlanFromList(int position) {
    click(driver, savedWorktopName(1, false));
  }

  public void editSavedPlanName(int position, String newName) {
    click(driver, savedWorktopEditSaveBtn(position));
    while (savedWorktopName(1, true).getAttribute("value").length() != 0) {
      savedWorktopName(1, true).sendKeys(Keys.DELETE);
    }
    savedWorktopName(1, true).sendKeys(newName);
    click(driver,savedWorktopEditSaveBtn(position));
  }

  public String getSavedPlanName(int position) {
    return savedWorktopName(position, false).getText();
  }

  public void deleteSavedPlan(int position) {
    click(driver, savedWorktopDeleteBtn(position));
  }

  public boolean isDeletedSavedPlanDialogDisplayed() {
    return isElementDisplayed(driver, deleteSavedPlanDialog);
  }

  public void undoDeleteSavePlanInDialog() {
    click(driver, dismissDeleteSavedPlanDialogUndo);
  }

  public void confirmDeleteSavePlanInDialog() {
    click(driver, dismissDeleteSavedPlanDialogRemove);
  }

  public boolean isPlanWithCodeInList(String code) {
    if(!isElementDisplayed(driver, savedPlansList, 2)) {
      return false;
    }
    int position = 1;
    while (isElementDisplayed(driver, savedWorktopCode(position), 1)) {
      if (code.equalsIgnoreCase(getSavedPlanCodeFromList(position))) {
        return true;
      }
      position++;
    }
    return false;
  }

  public boolean checkCheckbox(int number) {
    WebElement checkbox;
    switch (number) {
      case 1:
        checkbox = checkbox1;
        break;
      case 2:
        checkbox = checkbox2;
        break;
      case 3:
        checkbox = checkbox3;
        break;
      case 4:
        checkbox = checkbox4;
        break;
      default:
        checkbox = checkbox1;
        break;
    }
    if (!checkbox.isSelected()) {
      checkbox.click();
    }
    return checkbox.isSelected();
  }

  public void uncheckCheckbox(int number) {
    WebElement checkbox;
    switch (number) {
      case 1:
        checkbox = checkbox1;
      case 2:
        checkbox = checkbox2;
      case 3:
        checkbox = checkbox3;
      case 4:
        checkbox = checkbox4;
      default:
        checkbox = checkbox1;
    }
    if (checkbox.isSelected()) {
      checkbox.click();
    }
  }

  public void clickCompareButton() {
    compareBtn.click();
  }

  public boolean GetCompareButtonStatus(){
    String actualColor = compareButtonText().getCssValue("color");
    if (actualColor.equals("rgba(64, 122, 177, 1)") || (actualColor.equals("rgb(64, 122, 177)"))) {
      return true;
    }
      //else String buttonColor = "rgba(204, 204, 204, 1)";
    return false;
  }
}
