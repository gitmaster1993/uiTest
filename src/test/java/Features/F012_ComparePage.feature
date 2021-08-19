Feature: As a customer I want to compare different plans

  #This Scenario needs to be run locally or towards se-version
  Scenario: F012 Load 4 codes and check when compare button is enabled
    #Code: 3329S, 33757
    Given I have navigated to the start page in country Sweden
    When I select to load plan with code 37SJC2
    Then I should be navigated to the item-list page
    When I select to go back
    Then I should be navigated to the style page
    When I select exit
    Then I should be navigated to the start page

    When I select to load plan with code 37SJ7G
    Then I should be navigated to the item-list page
    When I select to go back
    Then I should be navigated to the style page
    When I select exit
    Then I should be navigated to the start page

    When I select to load plan with code 37SNGM
    Then I should be navigated to the item-list page
    When I select to go back
    Then I should be navigated to the style page
    When I select exit
    Then I should be navigated to the start page

    When I select to load plan with code 37SPZL
    Then I should be navigated to the item-list page
    When I select to go back
    Then I should be navigated to the style page
    When I select exit
    Then I should be navigated to the start page

    When I check 1 checkboxes
    Then the compare button is enabled
    When I click the compare button
    Then 1 plans are compared
    When I select to go back from compare page
    And I check 2 checkboxes
    Then the compare button is enabled
    When I click the compare button
    Then 2 plans are compared
    When I select to go back from compare page
    And I check 3 checkboxes
    Then the compare button is enabled
    When I click the compare button
    Then 3 plans are compared
    When I select to go back from compare page
    And I check 4 checkboxes
    Then the compare button is disabled

  #works OK but can be improved with more tests
  Scenario: F012 Compare 2 plans with same styles, different shapes and same sink
    Given I have navigated to the start page
    When I click the compare button
    And I want to add my first plan with code 37SJC2
    Then 1 plans are compared
    When I want to add a plan with code 37SJ7G
    Then 2 plans are compared
    And Style image is marked
    And Sink image is not marked
    And Tap holes image is not marked
    When I click Show details for Style
    Then all the Style details should be displayed
    When I click Show details for Sink
    Then all the Sink details should be displayed
    When I click Show details for CutOuts
    Then all the CutOuts details should be displayed
    When I click Show details for TapHoles
    Then all the TapHoles details should be displayed

    #Check the codes and marks, works now but can be improved with more tests
    # almost same as the test above, see if I can diverge them more
  Scenario: F012 Compare 2 plans with different styles, same shapes and same sink
    Given I have navigated to the start page
    When I click the compare button
    And I want to add my first plan with code 37SJC2
    Then 1 plans are compared
    When I want to add a plan with code 37SJ7G
    Then 2 plans are compared
    And Worktop image is marked
    And Sink image is marked
    When I click Show details for Worktop
    Then all the Worktop details should be displayed
    When I click Show details for Sink
    Then all the Sink details should be displayed
    When I click Show details for Cut-outs
    Then all the Cut-outs details should be displayed
    When I click Show details for Tap holes
    Then all the Tap holes details should be displayed

  #Check the codes and marks
  Scenario: F012 Compare 3 plans with different styles and shapes, but same sink
    Given I have navigated to the start page
    When I click the compare button
    And I want to add my first plan with code 37SNGM
    Then 1 plans are compared
    When I want to add a plan with code 37SPZL
    Then 2 plans are compared
    When I want to add a plan with code MZ8Z7
    Then 3 plans are compared
    And Style image is marked
    And Sink image is marked
    And Cut-outs image is not marked
    And Tap holes image is not marked
    When I click Show details for Style
    Then all the Style details should be displayed
    When I click Show details for Sink
    Then all the Sink details should be displayed
    When I click Show details for CutOuts
    Then all the CutOuts details should be displayed
    When I click Show details for TapHoles
    Then all the TapHoles details should be displayed

  Scenario: F012 Check when Enter code button is enabled
    Given I have navigated to the start page
    When I click the compare button
    And I want to add my first plan with code 37SJC2
    Then 1 plans are compared
    When I want to add a plan with code 37SJ7G
    Then 2 plans are compared
    When I want to add a plan with code 37SNGM
    Then 3 plans are compared
    And Enter code button is disabled

  Scenario: F012 Add and remove plans
    Given I have navigated to the start page
    When I click the compare button
    Then it is possible to enter new plans
    And add new plan in the top menu not available
    When I want to add my first plan with code 37SJC2
    Then 1 plans are compared
    And add new plan in the top menu is enabled and clicked
    When I want to add a plan with code 37SJ7G
    Then 2 plans are compared
    And add new plan in the top menu is enabled and clicked
    When I want to add a plan with code 37SNGM
    Then 3 plans are compared
    #And add new plan in the top menu is disabled
    When I delete all plans
    Then it is possible to enter new plans
    When I want to add my first plan with code 37SJC2
    Then 1 plans are compared
    When I want to add a plan with code 200
    Then an error message is displayed after the enter code dialogue
    And 1 plans are compared

  Scenario: F012 Add a plan containing conflicts
    Given I have navigated to the start page
    When I click the compare button
    And I want to add my first plan with code 32R2B
    Then an error message appears in column 1
    When pressing OK button in column 1
    Then a popup for New country should appear
    When I close the New country dialog
    Then a dialog should be displayed informing that there are some options in the saved plan that is no longer available

    Scenario: F012 Compare images
      Given I have navigated to the start page
      When I click the compare button
      Then it is possible to enter new plans
      #Code with sink
      When I want to add my first plan with code 37SJC2
      Then 1 plans are compared
      #Code without sink
      When I want to add a plan with code N6T5T
      Then 2 plans are compared
      Then the worktop image should be displayed in column 1
      And the worktop image should be displayed in column 2
      When swiping to bullet 2 in column 1
      Then the edge image should be displayed in column 1
      And the edge image should be displayed in column 2
      When swiping to bullet 3 in column 1
      Then the shape image should be displayed in column 1
      And the shape image should be displayed in column 2
      When swiping to bullet 4 in column 1
      Then the sink image should be displayed in column 1
      And the shape image should be displayed in column 2
      When swiping to bullet 2 in column 2
      Then the edge image should be displayed in column 1
      And the edge image should be displayed in column 2

   Scenario: F012 Compare plans with hobs and overhang
     Given I have navigated to the start page
     When I click the compare button
     Then it is possible to enter new plans
      #Code with freestanding hob and overhang
     When I want to add my first plan with code 33GP7
     Then 1 plans are compared
      #Code with hob and a different overhang
     When I want to add a plan with code 33GP8
     Then 2 plans are compared
     And freestanding hob image is not marked

