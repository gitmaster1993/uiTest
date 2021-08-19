Feature: Select kitchen island
  As a customer
  I want to be able to select to add a kitchen island and style it accordingly
  In order to fit in my kitchen

  Background:
    Given I have navigated to the Plan page

  Scenario: F014 Add kitchen island
    When I select shape Rectangular
    And I set the measurements A to 2000 and B to 635
    And I navigate to the kitchen-island page
    Then the kitchen island option No kitchen island should be selected
    When selecting a kitchen island
    Then a revert arrow should be displayed
    And the kitchen island image should be displayed
    And the previous button is enabled for the image view
    When I set the kitchen island measurements A to 1700 and B to 900
    Then the revert arrow should be disabled
    When I navigate to the plan page
    Then when I set wall selection to one long wall the price should be 2*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.27*(20350182 Same Color Edge Laminate) + 1.7*(90345502 SÄLJAN 63.6-125*3.8) + 3.5*(20350182 Same Color Edge Laminate)

  Scenario: F014 Kitchen island measurements
    When I select shape Rectangular
    And I navigate to the kitchen-island page
    And selecting a kitchen island
    When I set the kitchen island measurements A to <a> and B to <b> it should display <c>
    | a     | b     | c                |
    | 100   | 100   | No Error Message |
    | 99    | 3000  | Error Message    |
    | 20000 | 1250  | No Error Message |
    | 4000  | 0     | Error Message    |
    | 20001 | 1250  | Error Message    |
    | 20000 | 1251  | Error Message    |

  Scenario: F014 Measurement error messages when field is set to zero
    When I select shape V-shape
    And I set the measurements A to 2000 and B to 0
    Then the error message after entering zero should be available for shape v-shape
    And I navigate to the Style page
    Then 1 manual conflict should be indicated in the Plan tab
    When I navigate to the Plan page
    Then the error message after entering zero should be available for shape v-shape
    And I navigate to the kitchen-island page
    When selecting a kitchen island
    And I set the kitchen island measurements A to 2500 and B to 0
    Then the error message after entering zero should be available for kitchen island
    And I navigate to the Style page
    Then 1 manual conflict should be indicated in the Plan tab
    Then 1 manual conflict should be indicated in the kitchen-island tab
    When I navigate to the Plan page
    Then the error message after entering zero should be available for shape v-shape
    When I navigate to the kitchen-island page
    Then the error message after entering zero should be available for kitchen island

  Scenario: F014 Kitchen island with different materials than worktop
    When I select shape L-shape
    And I set wall selection to two walls left
    And I navigate to the Style page
    And I select quartz material, light grey stone effect quartz expression and 38 as thickness
    When I navigate to the kitchen-island page
    Then the kitchen island option No kitchen island should be selected
    When selecting a kitchen island
    And I set the kitchen island measurements A to 2000 and B to 1000
    Then the kitchen island style is set to same as worktop
    And the kitchen island material is quartz, expression is light grey stone effect quartz, edge is same color and thickness is 28 mm
    And the kitchen island schematic image should be displayed
    When I select kitchen island material laminate, expression dark grey laminate, same color edge and thickness 28 mm
    Then the kitchen island worktop image should be displayed
    And the kitchen island edge image should be displayed
    Then the total price should be 5.37*(50345518 OXSTEN 45.1-63.5*3.8) + 1.27*(30350313 Same Color Edge Quartz) + 1*(50345580 Joint Quartz) + 2*(60345476 EKBACKEN 63.6-125*2.8) + 4*(20350182 Same Color Edge Laminate)
    When selecting no kitchen island
    Then the worktop image should be displayed
    And the edge image should be displayed
    And the kitchen island edge image shouldn´t be displayed
    And the kitchen island worktop image shouldn´t be displayed
    And the kitchen island schematic image shouldn´t be displayed

  Scenario: F014 Kitchen island returning to same style as the worktop
    When I select shape irregular
    And I set wall selection to two walls top right
    And I navigate to the Style page
    And I select laminate material, white high gloss laminate expression and 77 as thickness
    And I select aluminium edge
    When I navigate to the kitchen-island page
    Then the kitchen island option No kitchen island should be selected
    When selecting a kitchen island
    And I set the kitchen island measurements A to 1500 and B to 800
    Then the kitchen island style is set to same as worktop
    And the kitchen island material is laminate, expression is white high gloss laminate, edge is aluminium and thickness is 77 mm
    When I select kitchen island material laminate, expression walnut effect laminate, same color edge and thickness 38 mm
    Then the kitchen island worktop image should be displayed
    And the total price should be 3*(50345561 DEJE 63.6-125*7.7) + 3.64*(40345571 Aluminium Edge 77 Laminate) + 1*(10345577 Joint Laminate) + 1.5*(50345504 SÄLJAN 63.6-125*3.8) + 3.1*(20350182 Same Color Edge Laminate)
    When I select kitchen island style same as worktop
    Then the kitchen island material is laminate, expression is white high gloss laminate, edge is aluminium and thickness is 77 mm
    And the total price should be 3*(50345561 DEJE 63.6-125*7.7) + 3.64*(40345571 Aluminium Edge 77 Laminate) + 1*(10345577 Joint Laminate) + 1.5*(50345561 DEJE 63.6-125*7.7) + 4.6*(40345571 Aluminium Edge 77 Laminate)



