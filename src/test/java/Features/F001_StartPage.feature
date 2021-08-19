Feature: Start creating my custom worktop
  As a customer
  I want to select if I want to create a new plan or start from an existing
  In order to begin/continue creating my custom worktop
  @Initialize
  Scenario: F001 Create new plan
    Given I have navigated to the start page
    When I select to create a new plan
    Then the app should display the Style page

  Scenario: F001 Exit ongoing planning
    Given I have navigated to the style page
    And I have selected wood material
    When I select exit
    Then a popup should appear where it should be possible to save or exit without saving
    And selecting exit without saving should navigate back to start page

  Scenario Outline: F001 Enter with preselected item number
    Given I navigate to tab <a> with item no <b> as additional parameter in the url
    Then I should be navigated to the correct tab
    And the total price should be <c>
    When I navigate to the Style page
    Then material <d>, expression <e> and thickness <f> should be selected
    When I navigate to the Plan page
    Then shape <g> should be selected with measurements <h>
    Examples:
      | a        | b        | c                                 | d        | e                         | f  | g           | h         |
      |style     | 50345434 | 1*(50345434 FORNBY 45.1-63.5*3.8) | acrylic  | white acrylic             | 38 | rectangular | 1000,635  |
      | plan     | 90345516 | 1*(90345516 OXSTEN 10-45*3.8)     | quartz   | black stone effect/quartz | 38 | rectangular | 1000,450  |
      | sink     | 00347553 | 1*(00347553 KARLBY 63.6-125*3.8)  | wood     | karlby oak                | 38 | rectangular | 1000,1250 |
      | style    | 00345498 | 1*(00345498 SÄLJAN 45.1-63.5*3.8) | laminate | black laminate            | 38 | rectangular | 1000,635  |

  Scenario: F001 Enter wall panel item number
    Given I navigate to tab plan with item no 80216751 as additional parameter in the url
    Then I should be navigated to the correct tab
    And the total price should be 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.25*(80216751 ISHULT 1 m2)
    When I navigate to the Style page
    Then material laminate, expression concrete effect laminate and thickness 28 should be selected
    And splashback wall panel with material glass and expression white glass should be selected
