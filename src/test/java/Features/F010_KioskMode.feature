Feature: Open application in an IKEA Kiosk
  As a customer
  I want to open the application in an IKEA Kiosk
  In order to plan my worktop in the IKEA store

  Scenario: F010 Screensaver in kiosk mode main screen
    Given I have navigated to the application in Kiosk mode
    When I don't do any actions for more than 300 seconds
    Then a screensaver should appear
    And when clicking on the screensaver
    Then the screensaver should be closed



  Scenario: F010 Virtual keyboard in Kiosk start page
    Given I have navigated to the application on a touch display kiosk
    When I click the enter existing code field
    Then the virtual keyboard should be displayed

  Scenario: F010 Virtual keyboard in Kiosk plan page
    Given I have navigated to the plan page on a touch display kiosk
    When I click in field A for shape rectangular
    Then a numerical virtual keyboard should be displayed
    And when entering values 1234 with the virtual keyboard
    #The line below generates the following error in Jenkins: Element is not clickable at point (-29, 631)
    #When I click in field B for shape rectangular
    When I click next with the virtual keyboard
    Then a numerical virtual keyboard should be displayed
    And when entering values 567 with the virtual keyboard
    Then the measurements for shape rectangular should be set to 1234,567
    And select the OK button in the virtual keyboard
    And the total price should be 1.24*(40345463 EKBACKEN 45.1-63.5*2.8)
    #And select the OK button in the virtual keyboard
    When I navigate to the kitchen-island page
    And selecting a kitchen island
    Then the kitchen island image should be displayed
    When I click in field A for kitchen island
    Then a numerical virtual keyboard should be displayed
    When I set the kitchen island measurements A to 1700 and B to 900
    Then the total price should be 1.24*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.7*(90345502 SÄLJAN 63.6-125*3.8) + 3.5*(20350182 Same Color Edge Laminate)

  Scenario: F010 Virtual keyboard in Kiosk style page
    Given I have navigated to the style page on a touch display kiosk
    When scrolling to the splashback style selector
    And I select wall panel splashback
    Then the splashback height is 550 mm
    When I mark the height of the splashback
    Then a numerical virtual keyboard should be displayed
    And when entering values 123 with the virtual keyboard
    And select the OK button in the virtual keyboard
    Then the numerical virtual keyboard should disappear
    And a button should appear indicating that the inspirational picture has been updated
    And clicking the button should scroll the page to the top to display the updated picture
    And the wall panel height 123 should be seen in the image
    When I mark the height of the splashback
    Then a numerical virtual keyboard should be displayed
    When I remove the height of the splashback
    Then the wall panel height 550 should be seen in the image

  Scenario: F010 Virtual keyboard in Kiosk compare page
    Given I have navigated to the application on a touch display kiosk
    When I click the compare button
    Then the virtual keyboard is visible so I can enter the first code 33757
    And 1 plans are compared
    And virtual keyboard is displayed
    When add new plan in the top menu is enabled and clicked
    Then the virtual keyboard is visible so I can enter the code 33752
    And 2 plans are compared
    And virtual keyboard is displayed
    When add new plan in the top menu is enabled and clicked
    Then the virtual keyboard is visible so I can enter the code 3375C
    And 3 plans are compared
    And virtual keyboard isn´t displayed

  Scenario: F10 Dismiss virtual keyboard when making a new selection for style page
    Given I have navigated to the style page on a touch display kiosk
    When scrolling to the splashback style selector
    And I select wall panel splashback
    Then the splashback height is 550 mm
    When I mark the height of the splashback
    Then a numerical virtual keyboard should be displayed
    And when entering values 658 with the virtual keyboard
    When I select wall panel material glass
    Then the numerical virtual keyboard should disappear

  Scenario: F10 Dismiss virtual keyboard when making a new selection for plan page
    Given I have navigated to the plan page on a touch display kiosk
    When I select shape V-Shape
    And I put the marker in the measurement field for V-shape without writing
    Then a numerical virtual keyboard should be displayed
    And when entering values 1267 with the virtual keyboard
    When I select shape C-Shape
    Then the numerical virtual keyboard should disappear
    When I put the marker in the measurement field for C-shape without writing
    Then a numerical virtual keyboard should be displayed
    When when entering values 2435 with the virtual keyboard
    And I set three walls as wall location
    Then the numerical virtual keyboard should disappear

  Scenario: F010 Saved plans list not displayed in Kiosk mode
    And I have navigated to the application in Kiosk mode in country Sweden
    And I select to create a new plan
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    And I navigate to the item-list page
    When I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And there should not be a saved plans list displayed

  Scenario: F10 Overhang size in Kiosk plan page
    Given I have navigated to the plan page on a touch display kiosk
    When I select shape irregular
    And I select one long wall as walls and arc as overhang
    When I select one side left as overhang side and I click the overhang side size
    Then the virtual keyboard is visible so I can enter the overhang size 239 mm
    When I press dismiss virtual keyboard
    Then the numerical virtual keyboard should disappear
