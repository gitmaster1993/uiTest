Feature:
  The first time the user can use the revert arrow, an info box is shown explaining the feature.
  Clicking anywhere on the inspirational picture or on the icon (step backward) will remove the info box.
  Hitting the icon will revert the user's current choice to the user's previous choice - if applicable the image and price are updated.
  While the previous choice is shown, the icon is flipped horizontally (step forward) until the icon is hit again or until a new choice is made.

  Background:
    Given I have navigated to the Style page

  Scenario: F011 Arrow and popup handling when changing materials
    #Information popup should appear first time arrow appears and be removed when pressed or if pressing in the area nearby
    Given I have selected Laminate material
    When I select aluminium effect laminate expression
    When I select concrete effect laminate expression
    Then a revert arrow should be displayed
    And an information bubble should be displayed
    When I click the information bubble
    Then it should disappear
    When clicking on the revert arrow
    Then the expression aluminium effect laminate should be displayed
    And the revert arrow is changed to a forward arrow
    When clicking on the forward arrow
    Then the expression concrete effect laminate should be displayed

  Scenario: F011 Arrow handling during edge conflict
    #  After reverting from conflicting state it should not display a forward button (you can never go forward into a conflicting state)
    Given I have selected Wood material
    When I select karlby oak expression
    And I select aluminium edge
    Then a conflict dialog should be displayed
    # Conflict appears since wood material is not available with aluminium edge
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 2 manual conflict should be indicated in the Style tab
    Then a revert arrow should be displayed
    When clicking on the revert arrow
    Then the price shouldn't indicate a conflict
    And 0 manual conflict should be indicated in the Style tab
    And the forward arrow should be disabled

  Scenario: F011 Arrow handling when current and previous plans are equal
  #Pressing undo in conflict dialog causes current and previous plans are equal
    When I navigate to the Sink page
    And I have selected steel material for the sink
    When I select model VATTUDALEN inset sink 1 bowl with drainboard stainless steel 69x47 cm
    And I select drainer grooves
    Then a conflict dialog should be displayed
    When pressing "Go back" in the conflict dialog
    Then the revert arrow should be disabled

  Scenario: F011 Arrow handling when making a new choice
    Given I have selected Quartz material
    When I select dark grey stone effect quartz expression
    Then a revert arrow should be displayed
    When clicking on the revert arrow
    Then the expression anthracite stone effect quartz should be displayed
    And the revert arrow is changed to a forward arrow
    When clicking on the forward arrow
    Then a revert arrow should be displayed
    When I select black stone effect quartz expression
    Then a revert arrow should be displayed

  Scenario: F011 Arrow handling for cut outs
    #Number of cut outs should be possible to change with revert arrow
    Given I navigate to the cut-outs page
    And I add 1 hob cut out
    And I add 1 double cut out
    Then 1 DOUBLE cut outs should be available
    And a revert arrow should be displayed
    When clicking on the revert arrow
    Then 0 DOUBLE cut outs should be available
    And 1 HOB cut outs should be available
    And the revert arrow is changed to a forward arrow

  Scenario: F011 Arrow handling when changing shape
  #Changing shape measurement should disable the arrow, but flip should be possible to revert
    When I navigate to the Plan page
    And I select shape L-Shape
    Then a revert arrow should be displayed
    When I press the flip button the shape should be flipped
    Then a revert arrow should be displayed
    When I set the measurements A to 2000, B to 3000, C to 451 and D to 635
    Then the revert arrow should be disabled
    When I press the flip button the shape should be flipped
    Then a revert arrow should be displayed
    When I put the marker in the measurement field without writing
    Then a revert arrow should be displayed
    When I set wall selection to two walls left
    Then a revert arrow should be displayed

  Scenario: F011 Arrow handling when going back and forth to summary page
    #Arrow should stay in the same shape when going to summary page
    Given I have selected solid wood material
    When I select oak expression
    And I navigate to the Sink page
    And I have selected steel material for the sink
    When I select model NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm
    Then a revert arrow should be displayed
    When I navigate to the item-list page
    Then it should include worktop items and sink items
    When I select to go back
    Then I should be navigated to the sink page
    And a revert arrow should be displayed
    When clicking on the revert arrow
    Then the revert arrow is changed to a forward arrow
    #Back to default value
    And the selected material is steel and model is NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm
    When I navigate to the item-list page
    Then it should include worktop items and sink items
    When I select to go back
    Then a forward arrow should be displayed
    When clicking on the forward arrow
    Then a revert arrow should be displayed
    And I have selected material steel and model NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm

  Scenario: F011 Arrow handling when changing things in summary page
    Given I have selected solid wood material
    When I select oak expression
    And I navigate to the Sink page
    And I have selected steel material for the sink
    When I select model BOHOLMEN 45
    Then a revert arrow should be displayed
    When I navigate to the item-list page
    Then it should include worktop items and sink items
    When I select to delete the sink
    Then the remove confirmation dialog is shown
    When I confirm that I want to remove the selection
    Then it should only include worktop items and not any sink items
    When I select to go back
    Then a revert arrow should be displayed
    And no sink should be selected
    When clicking on the revert arrow
    Then the revert arrow is changed to a forward arrow
    And the selected material is steel and model is BOHOLMEN 45

  Scenario: F011 Arrow handling when changing overhang side measurement
    Given I have selected Quartz material
    When I navigate to the Plan page
    And I select shape V-shape
    And I select two walls as walls and half emerald as overhang
    Then a revert arrow should be displayed
    When clicking on the revert arrow
    Then no overhang is selected
    When clicking on the forward arrow
    And I select one side right as overhang side and the overhang size is changed to 600 mm
    Then the revert arrow should be disabled
