Feature: Get information on conflicts
  As a customer
  I want to get more information when I make conflicting choices
  In order to solve the conflicts

  Scenario: F006 Plan page measurement conflict and check what's included functionality
    Given I have navigated to the Plan page
    And I select shape Rectangular
    When I set the measurements A to 10 and B to 10
    Then the price should indicate a conflict
    And 1 manual conflict should be indicated in the Plan tab
    And what´s included text is missing
    When I set the measurements A to 200 and B to 200
    Then the price shouldn't indicate a conflict
    And 0 manual conflict should be indicated in the Plan tab


  Scenario: F006 Price error dialog
    Given I have navigated to the Plan page
    And I select shape Rectangular
    And I set the measurements A to 10 and B to 10
    When I click the price conflict indicator
    Then a price error dialog should be displayed
    And clicking "Got it" in the dialog should close it

  Scenario: F006 Go back from conflict
    Given I have navigated to the Style page
    When I select Laminate material, dark grey laminate expression and 38 as thickness
    Then a conflict dialog should be displayed
    When pressing "Go back" in the conflict dialog
    Then the selection should go back to Laminate material, dark grey laminate expression and 28 as thickness

  Scenario: F006 Thickness vs expression manual conflict
    Given I have navigated to the Style page
    When I select Laminate material, dark grey laminate expression and 38 as thickness
    Then a conflict dialog should be displayed
    # since this expression is not available in 38mm
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 2 manual conflict should be indicated in the Style tab
    When I select white laminate expression
    # that is available in 38mm
    Then the price shouldn't indicate a conflict
    And 0 manual conflict should be indicated in the Style tab

  Scenario: F006 Thickness vs expression automatic conflict
    Given I have navigated to the Style page
    And I select Laminate material, black laminate expression and 38 as thickness
    When I select dark grey laminate expression
    Then a conflict dialog should be displayed
    # since this expression is not available in 38mm
    When pressing "Continue" in the conflict dialog
    Then the price shouldn't indicate a conflict
    # since this conflict will be automatically solved
    And 1 automatic conflict should be indicated in the Style tab
    When scrolling down to the thickness style page selectors
    Then the automatic conflict indicator should disappear in the Style tab

  Scenario: F006 Edge vs material manual conflict
    Given I have navigated to the Style page
    And I have selected Wood material
    When I select aluminium edge
    Then a conflict dialog should be displayed
    # since wood material is not available with aluminium edge
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 2 manual conflict should be indicated in the Style tab
    When I select Laminate material
    # that is available with aluminium edge
    Then the price shouldn't indicate a conflict
    And 0 manual conflict should be indicated in the Style tab

  Scenario: F006 Edge vs material automatic conflict
    Given I have navigated to the Style page
    And I select aluminium edge
    When I select wood material
    Then a conflict dialog should be displayed
    # since wood is not available with aluminium edge
    When pressing "Continue" in the conflict dialog
    Then the price shouldn't indicate a conflict
    # since this conflict will be automatically solved
    And 1 automatic conflict should be indicated in the Style tab
    When scrolling down to the edge style page selectors
    Then the automatic conflict indicator should disappear in the Style tab

  Scenario: F006 Sink model vs fastening manual conflict
    Given I have navigated to the Sink page
    When I select material steel, model LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm and fastening fitBetween
    Then a conflict dialog should be displayed
    # since this model is not available with fit between fastening
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 2 manual conflict should be indicated in the Sink tab
    When I select material steel, model LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm and fastening underGlued
    # that is available with underglued fastening
    Then the price shouldn't indicate a conflict
    And 0 manual conflict should be indicated in the Sink tab

  Scenario: F006 Sink model vs fastening automatic conflict
    Given I have navigated to the Style page
    When I select Laminate material, ash effect laminate expression and 28 as thickness
    And I navigate to the Sink page
    And I select material steel, model NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm and fastening underGlued
    When I select model AMMERÅN onset sink 1 bowl stainless steel 60x635 cm
    Then a conflict dialog should be displayed
    # since this model is not available with underglued fastening
    When pressing "Continue" in the conflict dialog
    Then the price shouldn't indicate a conflict
    # since this conflict will be automatically solved
    And 1 automatic conflict should be indicated in the Sink tab
    When scrolling down to the fastening sink page selectors
    Then the automatic conflict indicator should disappear in the Sink tab

  Scenario: F006 Sink model vs shape conflict
    Given I have navigated to the Plan page
    When I select shape L-shape
    And I set the measurements A to 2000, B to 3000, C to 648 and D to 632
    And I navigate to the Sink page
    And I have selected porcelain material for the sink
    When I select model havsensinkbowlwvisiblefrontwhite62x48cm
    Then the price shouldn't indicate a conflict

  Scenario: F006 Sink model vs irregular shape conflict
    Given I have navigated to the Plan page
    When I select shape irregular
    And I navigate to the Sink page
    And I have selected porcelain material for the sink
    When I select model havsensinkbowl2bowlswvisiblefrontwhite82x48cm
    Then a conflict dialog should be displayed
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 2 manual conflict should be indicated in the Sink tab
    And 1 manual conflict should be indicated in the Plan tab

  Scenario: F006 Material vs Cut out manual conflict
    Given I have navigated to the Style page
    And I have selected Quartz material
    When I navigate to the Cut-outs page
    And I add 1 rounded corner
    Then a conflict dialog should be displayed
    # since rounded corner is not possible on Quartz material
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 1 manual conflict should be indicated in the Style tab
    And 1 manual conflict should be indicated in the Cut-outs tab
    When I navigate to the Style page
    And I select solid wood material
    Then the price shouldn't indicate a conflict
    And 0 manual conflict should be indicated in the Style tab
    And 0 manual conflict should be indicated in the Cut-outs tab

  Scenario: F006 Material vs Cut out automatic conflict
    Given I have navigated to the Cut-outs page
    And I add 1 rounded corner
    When I navigate to the Style page
    And I have selected Quartz material
    Then a conflict dialog should be displayed
    # since rounded corner is not possible on Quartz material
    When pressing "Continue" in the conflict dialog
    Then the price shouldn't indicate a conflict
    # since this conflict will be automatically solved
    And 1 automatic conflict should be indicated in the Cut-outs tab
    When I navigate to the Cut-outs page
    And scrolling down to the rounded corner cut outs selector
    Then the automatic conflict indicator should disappear in the Cut-outs tab

  Scenario: F006 Material vs Drainer Grooves manual conflict
    Given I have navigated to the Style page
    And I have selected Laminate material
    When I navigate to the Sink page
    And I select sink material steel
    And I select drainer grooves
    Then a conflict dialog should be displayed
    # since drainer grooves are not possible on laminate material
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 1 manual conflict should be indicated in the Style tab
    And 1 manual conflict should be indicated in the Sink tab
    When I navigate to the Style page
    And I select Quartz material
    # that is possible with drainer grooves
    Then the price shouldn't indicate a conflict
    And 0 manual conflict should be indicated in the Style tab
    And 0 manual conflict should be indicated in the Sink tab

  Scenario: F006 Material vs Drainer Grooves automatic conflict
    Given I have navigated to the Style page
    And I have selected Quartz material
    And I navigate to the Sink page
    And I select sink material steel
    And I select model BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm
    And I select fastening underGlued
    And I select drainer grooves
    When I navigate to the Style page
    And I select Laminate material
    Then a conflict dialog should be displayed
    # since drainer grooves are not possible on laminate material
    When pressing "Continue" in the conflict dialog
    Then the price shouldn't indicate a conflict
    # since this conflict will be automatically solved
    And 1 automatic conflict should be indicated in the Sink tab
    When I navigate to the Sink page
    And scrolling down to the drainer grooves sink page selectors
    Then the automatic conflict indicator should disappear in the Sink tab

  Scenario: F006 Wall edging strip
    Given I have navigated to the Style page
    When scrolling to the splashback style selector
    And I select wall edging strip splashback
    Then a conflict dialog should be displayed
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    And 2 manual conflict should be indicated in the Style tab
    When I have scrolled up on the page
    And I select Quartz material
    Then the price shouldn't indicate a conflict
    And the total price should be 1*(30345524 OXSTEN 45.1-63.5*3.8) + 2.27*(00345950 OTTARP)


    #For this TC to work a change has to be made in the code for matt dark grey marble effect quartz expression
    # If no expression matches a certain shape in a country, then that shape should not be displayed at all. This is hard to test.
  Scenario: F006 Shape vs selected expression conflict
    #This is only valid in Portugal: Many expressions there do not have a depth deeper than 900 mm, V-shape demands 1200
    #Given I have navigated to the Plan page in country Portugal
    Given I have navigated to the Plan page
    And I select shape V-Shape
    And I set the measurements A to 4000 and B to 3000
    And I navigate to the Style page
    And I have selected Quartz material
    When I select matt dark grey marble effect quartz expression
    #Then a conflict dialog should be displayed
    #When pressing "Continue" in the conflict dialog
    #Then 1 automatic conflict should be indicated in the Style tab
    #And 1 automatic conflict should be indicated in the Plan tab
    #When I select light grey stone effect quartz expression
    #Then the price shouldn't indicate a conflict
    #And 0 manual conflict should be indicated in the Style tab
    #And 0 manual conflict should be indicated in the Plan tab

  #Sink: required width = 635 mm, required length >= 920
  Scenario: F006 U-shape conflicts for fit between sink
    Given I have navigated to the Sink page
    And I have selected porcelain material for the sink
    And I select model havsensinkbowl2bowlswvisiblefrontwhite82x48cm
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    Then the fastening should be fit Between
    When I navigate to the Plan page
    And I select shape U-Shape
    Then when I set the measurement A to <a>, B to <b>, C to <c>, D to <d>, E to <e> and F to <f> it should display <g> and background
      | a     | b     | c     | d    | e    | f    | g                |
      | 3000  | 1545  | 3000  | 62   | 634  | 900  | Error Message    |
      | 3000  | 1545  | 3000  | 625  | 635  | 900  | No Error Message |
      | 3000  | 1129  | 1554  | 634  | 635  | 635  | No Error Message |
      | 3000  | 110   | 1553  | 634  | 635  | 635  | Error Message    |
      | 3000  | 1553  | 112   | 634  | 635  | 635  | Error Message    |

  Scenario: F006 C-shape conflicts for fit between sink
    Given I have navigated to the Sink page
    And I have selected porcelain material for the sink
    And I select model havsensinkbowl2bowlswvisiblefrontwhite82x48cm
    When pressing "Continue" in the conflict dialog
    Then the price should indicate a conflict
    Then the fastening should be fit Between
    When I navigate to the Plan page
    And I select shape C-Shape
    Then when I set the measurement A to <a> and B to <b> it should display <c> and background
      | a     | b     | c                |
      | 1183  | 1000  | Error Message    |
      | 2000  | 1100  | No Error Message |
      | 920   | 1182  | Error Message    |
      | 1182  | 1183  | Error Message    |

  Scenario: F006 Irregular conflict for fit between sink
    Given I have navigated to the Sink page
    And I have selected porcelain material for the sink
    And I select model havsensinkbowl2bowlswvisiblefrontwhite82x48cm
    Then the fastening should be fit Between
    When I navigate to the Plan page
    And I select shape Irregular
    Then a conflict dialog should be displayed
    When pressing "Continue" in the conflict dialog
    Then 1 manual conflict should be indicated in the Plan tab
    And 2 manual conflict should be indicated in the Sink tab

  Scenario: F006 Overhang conflict for different wall locations
    Given I have navigated to the Plan page
    When I select shape L-shape
    And I select four walls as walls and rounded as overhang
    Then a conflict dialog should be displayed
    When pressing "Continue" in the conflict dialog
    Then 3 manual conflict should be indicated in the Plan tab
    And the price should indicate a conflict
    When I select three walls top as walls and rounded as overhang
    And the price shouldn´t indicate a conflict
    And 0 manual conflict should be indicated in the Plan tab
    When I select one side right as overhang side
    Then a conflict dialog should be displayed
    When pressing "Continue" in the conflict dialog
    Then 2 manual conflict should be indicated in the Plan tab
    And the price should indicate a conflict

  Scenario: F006 Overhang conflict for different materials
    Given I have navigated to the Plan page
    When I select shape V-shape
    And I select two walls as walls and half rounded as overhang
    Then the price shouldn´t indicate a conflict
    When I navigate to the Style page
    And I select quartz material
    Then a conflict dialog should be displayed
    When pressing "Continue" in the conflict dialog
    Then 1 manual conflict should be indicated in the Plan tab
    And 1 manual conflict should be indicated in the Style tab
    And the price should indicate a conflict
    When I navigate to the Plan page
    And I select two walls as walls and emerald as overhang
    Then 0 manual conflict should be indicated in the Plan tab
    And 0 manual conflict should be indicated in the Style tab
    And the price shouldn´t indicate a conflict
