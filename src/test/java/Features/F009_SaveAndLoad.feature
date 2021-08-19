Feature: Save and load my plan
  As a customer
  I want to save my plan
  In order to open it at another time and/or place

  Scenario Outline: F009 Save my plan from item list and load it again
    Given I have navigated to the plan page in country Sweden
    And I have selected shape <shape> with measurement <measurement> and wall selection <wall selection> in the plan tab
    And I have selected worktop material <worktop material>, expression <expression>, edge <edge> and thickness <thickness> in the style tab
    And I have selected sink material <sink material>, model <sink model>, fastening <sink fastening>, drainer grooves <drainer grooves> and <tap holes> tap holes in the sink tab
    And I have selected <hob cut outs> hob cut outs, <rounded corners> rounded corners, <inverted corners> inverted corners, <single cuts> single cuts, <double cuts> double cuts and <triple cuts> triple cuts in the cut outs page
    When I navigate to the item-list page
    And I select to save my plan
    Then the save plan dialog should be displayed
    When I load my saved plan from the code in the dialog
    Then I should be navigated to the item-list page
    When I navigate to the back page
    Then I should be navigated to the style page
    When I navigate to the style page
    And worktop material <worktop material>, expression <expression>, edge <edge> and thickness <thickness> should be selected
    When I navigate to the plan page
    Then shape <shape> and wall selection <wall selection> should be selected and the measurement for the shape should be <measurement>
    When I navigate to the sink page
    Then sink material <sink material>, model <sink model>, fastening <sink fastening>, drainer grooves <drainer grooves> should be selected and <tap holes> number of tap holes should have been added
    When I navigate to the cut-outs page
    Then  <hob cut outs> hob cut outs, <rounded corners> rounded corners, <inverted corners> inverted corners, <single cuts> single cuts, <double cuts> double cuts and <triple cuts> triple cuts should have been added
    Examples:
      | shape       | measurement                 | wall selection     | worktop material | expression                          | edge       | thickness | sink material | sink model                                                              | sink fastening | drainer grooves | tap holes | hob cut outs | rounded corners | inverted corners | single cuts | double cuts | triple cuts |
      | rectangular | 2000,470                    | one long wall      | solid wood       | oak                                 | same color | 38        | steel         | HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm                | topMounted     | no              | 1         | 1            | 0               | 0                | 0           | 0           | 3           |
      | l-shape     | 3100,4321,111,222           | four walls         | laminate         | white laminate                      | aluminium  | 77        | no Sink       | NA                                                                      | NA             | NA              | 1         | 1            | 2               | 0                | 7           | 0           | 1           |
      | u-shape     | 10000,2100,2990,635,635,800 | two walls left     | quartz           | matt dark grey/marble effect quartz | same color | 38        | composite     | HÄLLVIKEN 100x50 black                                                  | topMounted     | no              | 0         | 1            | 0               | 0                | 0           | 2           | 0           |

  Scenario Outline: F009 Save my plan from exit dialog and load it again
    Given I have navigated to the plan page in country Sweden
    And I have selected shape <shape> with measurement <measurement> and wall selection <wall selection> in the plan tab
    And I have selected worktop material <worktop material>, expression <expression>, edge <edge> and thickness <thickness> in the style tab
    And I have selected sink material <sink material>, model <sink model>, fastening <sink fastening>, drainer grooves <drainer grooves> and <tap holes> tap holes in the sink tab
    And I have selected <hob cut outs> hob cut outs, <rounded corners> rounded corners, <inverted corners> inverted corners, <single cuts> single cuts, <double cuts> double cuts and <triple cuts> triple cuts in the cut outs page
    When I select exit
    Then a popup should appear where it should be possible to save or exit without saving
    When I select to save my plan in the popup dialog
    Then the save plan dialog should be displayed
    When I load my saved plan from the code in the dialog
    Then I should be navigated to the item-list page
    When I navigate to the back page
    Then I should be navigated to the style page
    And worktop material <worktop material>, expression <expression>, edge <edge> and thickness <thickness> should be selected
    When I navigate to the plan page
    Then shape <shape> and wall selection <wall selection> should be selected and the measurement for the shape should be <measurement>
    When I navigate to the sink page
    Then sink material <sink material>, model <sink model>, fastening <sink fastening>, drainer grooves <drainer grooves> should be selected and <tap holes> number of tap holes should have been added
    When I navigate to the cut-outs page
    Then  <hob cut outs> hob cut outs, <rounded corners> rounded corners, <inverted corners> inverted corners, <single cuts> single cuts, <double cuts> double cuts and <triple cuts> triple cuts should have been added
    Examples:
      | shape       | measurement                 | wall selection     | worktop material | expression                  | edge       | thickness | sink material | sink model                                                  | sink fastening | drainer grooves | tap holes | hob cut outs | rounded corners | inverted corners | single cuts | double cuts | triple cuts |
      | v-shape     | 9999,8888                   | no walls           | acrylic          | white acrylic               | same color | 38        | steel         | LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm       | underGlued     | yes             | 0         | 0            | 0               | 2                | 0           | 0           | 0           |
      | ii-shape    | 900,450,5000,1100           | six walls          | laminate         | black stone effect/laminate | same color | 28        | steel         | BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm         | underGlued     | no              | 2         | 1            | 1               | 0                | 2           | 0           | 0           |
      | irregular   | 8000,200,635                | two walls top left | wood             | barkaboda walnut            | same color | 38        | composite     | HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm | underGlued     | no              | 0         | 2            | 0               | 0                | 0           | 0           | 0           |

  Scenario: F009 Saved plan added to list
    Given I have navigated to the style page in country Sweden
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    And I navigate to the item-list page
    When I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And my recently saved plan should be on top of the saved plans list

  Scenario: F009 Enter planner from saved plan in list
    Given I have navigated to the style page in country Sweden
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    And I navigate to the item-list page
    When I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And my recently saved plan should be on top of the saved plans list
    When I select to enter the plan from the top of the list
    Then I should be navigated to the item-list page
    And the item list should include worktop 1*(60347550 KARLBY 45.1-63.5*3.8)

  Scenario: F009 Edit name of saved plan in start page
    Given I have navigated to the style page in country Sweden
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    And I navigate to the item-list page
    When I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And my recently saved plan should be on top of the saved plans list
    When I select to edit the name of the top plan
    Then the name in the top plan list should be changed to the new name
    When I select to enter the plan from the top of the list
    Then I should be navigated to the item-list page
    And the new name of the plan should be displayed on the top of the list
    And the item list should include worktop 1*(60347550 KARLBY 45.1-63.5*3.8)

  Scenario: F009 Edit name of saved plan in item list
    Given I have navigated to the style page in country Sweden
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    And I navigate to the item-list page
    When I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And my recently saved plan should be on top of the saved plans list
    When I select to enter the plan from the top of the list
    Then I should be navigated to the item-list page
    And the worktop name should not be editable
    When I select to go back
    And I have selected laminate material
    And I navigate to the item-list page
    Then the worktop name should be editable
    When I select to edit the name of the worktop
    And I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And my recently saved plan should be on top of the saved plans list with the correct updated name
    And the old plan should still be in the list

  Scenario: F009 Delete saved plan from list, undo
    Given I have navigated to the style page in country Sweden
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    And I navigate to the item-list page
    When I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And my recently saved plan should be on top of the saved plans list
    When I select delete for the top plan
    Then a delete confirm dialog should be displayed
    And if selecting to not delete in the delete confirm dialog
    Then that plan should not have been deleted from the list

  Scenario: F009 Delete saved plan from list
    Given I have navigated to the style page in country Sweden
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    And I navigate to the item-list page
    When I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to start over
    Then I should be navigated to the start page
    And my recently saved plan should be on top of the saved plans list
    When I select delete for the top plan
    Then a delete confirm dialog should be displayed
    And if selecting to delete in the delete confirm dialog
    Then that plan should be deleted from the list



  Scenario: F009 Load plan with lowercase letters
    Given I have navigated to the style page in country Sweden
    And I have selected worktop material wood, expression karlby oak, edge same color and thickness 38 in the style tab
    When I select exit
    Then a popup should appear where it should be possible to save or exit without saving
    When I select to save my plan in the popup dialog
    Then the save plan dialog should be displayed
    When I load my saved plan from the code in the dialog
    Then I should be navigated to the item-list page
    And I navigate to the back page
    When I select exit
    Then I should be navigated to the start page
    When I load my saved plan with lowercase letters
    Then I should be navigated to the item-list page
    When I navigate to the back page
    And I select exit
    Then the list of saved codes should only be saved with capital letters

  Scenario: F009 Save plan and exit
    Given I have navigated to the plan page in country Sweden
    When I have selected shape C-shape with measurement 2343,1543 and wall selection three walls in the plan tab
    And I navigate to the cut-outs page
    And I select to add 1 freestanding hob
    And I navigate to the style page
    And I have selected worktop material wood, expression pinnarp ash, edge same color and thickness 38 in the style tab
    When I navigate to the item-list page
    And I select to save my plan
    Then the save plan dialog should be displayed
    When I close the save plan dialog
    And I select to go back
    Then I should be navigated to the style page
    When I select exit
    Then a popup shouldn´t appear where it should be possible to save or exit without saving
    And my recently saved plan should be on top of the saved plans list

  Scenario: F009 Print page button
    Given I have navigated to the plan page
    When I have selected shape v-shape with measurement 2000,1216 and wall selection three walls left in the plan tab
    And I have selected worktop material solid wood, expression oak, edge same color and thickness 38 in the style tab
    When I navigate to the item-list page
    Then I select to print the page
    Then the save plan dialog shouldn´t be displayed

  Scenario: F009 Print page
    Given I have navigated to the plan page for printing
    When I have selected shape v-shape with measurement 2000,1216 and wall selection three walls left in the plan tab
    And I have selected worktop material solid wood, expression oak, edge same color and thickness 38 in the style tab
    And I have selected sink material steel, model HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm, fastening topMounted, drainer grooves 1 and 1 tap holes in the sink tab
    When I navigate to the item-list page
    Then the print view is displayed
