Feature: See what items are included in my worktop selections
  As a customer
  I want to see what items are included in my worktop planning
  In order to get a good overview

  Scenario Outline: F007 Item list for worktop selection
    Given I have navigated to the plan page
    And I have selected shape <shape> with measurement <measurement> and wall selection <wall selection> in the plan tab
    And I navigate to the style page
    And I have selected worktop material <material>, expression <expression>, edge <edge> and thickness <thickness> in the style tab
    When I navigate to the item-list page
    Then the item list should include worktop <items>
    Examples:
      | shape       | measurement                | wall selection     | material | expression                 | edge       | thickness | items                                                                                                                                                  |
      | rectangular | 3500,600                   | two walls right    | wood     | karlby oak                 | same color | 38        | 3.5*(60347550 KARLBY 45.1-63.5*3.8) + 0.6*(80350184 Same Color Edge Wood)                                                                              |
      | u-shape     | 3510,2100,2990,635,635,800 | two walls left     | quartz   | white marble effect/quartz | same color | 38        | 2.99*(40345528 OXSTEN 63.6-125*3.8) + 4.18*(90345521 OXSTEN 45.1-63.5*3.8) + 4.43*(30350313 Same Color Edge Quartz) + 2*(50345580 Joint Quartz)        |
      | irregular   | 8000,200,635               | two walls top left | laminate | black laminate             | aluminium  | 28        | 4*(80345442 EKBACKEN 10-45*2.8) + 4*(50345467 EKBACKEN 45.1-63.5*2.8) + 3*(10345577 Preparation Laminate) + 8.64*(60340558 Aluminium Edge 28 Laminate) |

  Scenario Outline: F007 Item list for sink selection
    Given I have navigated to the style page
    And I have selected <worktop material> material
    And I navigate to the sink page
    And I have selected sink material <sink material>, model <sink model>, fastening <sink fastening>, drainer grooves <drainer grooves> and <tap holes> tap holes in the sink tab
    When I navigate to the item-list page
    Then the item list should include <sink items>, <drainer groove items> and <tap hole items>
    Examples:
      | worktop material | sink material | sink model                                                | sink fastening | drainer grooves | tap holes | sink items                                                                                                           | drainer groove items                 | tap hole items                 |
      | laminate         | steel         | HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm  | topMounted     | no              | 1         | 1*(49140631 HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm) + 1*(10345577 Cut Out Laminate)                | NA                                   | 1*(00345573 Tap Hole Laminate) |
      | acrylic          | steel         | LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm     | underGlued     | yes             | 0         | 1*(70352032 LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm)                                                   | 1*(40345585 Drainer Grooves Acrylic) | NA                             |
      | quartz           | composite     | HÄLLVIKEN 100x50 white                                    | topMounted     | no              | 2         | 1*(59158197 HÄLLVIKEN 100x50 white) + 1*(50345580 Cut Out Quartz)                                                    | NA                                   | 2*(80345574 Tap Hole Quartz)   |
      | wood             | no sink       | NA                                                        | NA             | NA              | 5         | NA                                                                                                                   | NA                                   | 5*(30347561 Tap Hole Wood)     |

  Scenario Outline: F007 Item list for cut outs
    Given I have navigated to the style page
    And I have selected <worktop material> material
    And I navigate to the cut-outs page
    And I select to add <hob cuts> hob cut outs, <rounded corners> rounded corners, <inverted corners> inverted corners, <single cuts> single cuts, <double cuts> double cuts and <triple cuts> triple cuts
    When I navigate to the item-list page
    Then the itemlist should include <cut out items>, <single cut items>, <double cut items>, <triple cut items>, <rounded corner items> and <inverted corner items>
    Examples:
      | worktop material | hob cuts | rounded corners | inverted corners | single cuts | double cuts | triple cuts | cut out items                   | single cut items                   | double cut items                   | triple cut items                   | rounded corner items                   | inverted corner items                    |
      | laminate         | 1        | 1               | 1                | 1           | 1           | 1           | 1*(10345577 Cut Out Laminate)   | 1*(10345577 Single Cut Laminate)   | 1*(10345577 Double Cut Laminate)   | 1*(10345577 Triple Cut Laminate)   | 1*(10345577 Rounded Corner Laminate)   | 1*(10345577 Inverted Corner Laminate)    |
      | quartz           | 1        | 0               | 0                | 10          | 0           | 0           | 1*(50345580 Cut Out Quartz)     | 10*(50345580 Single Cut Quartz)    | NA                                 | NA                                 | NA                                     | NA                                       |
      | solid wood       | 2        | 5               | 10               | 0           | 3           | 3           | 2*(70345579 Cut Out Solid Wood) | NA                                 | 3*(70345579 Double Cut Solid Wood) | 3*(70345579 Triple Cut Solid Wood) | 5*(70345579 Rounded Corner Solid Wood) | 10*(70345579 Inverted Corner Solid Wood) |

  Scenario Outline: F007 Indicating price is an estimate
    Given I have navigated to the cut-outs page
    And I have added a <cut>
    When I navigate to the item-list page
    Then the <cut> section <estimate> indicate that the price is an estimate
    And by the total price it <estimate> indicate that the price is an estimate
    Examples:
      | cut             | estimate  |
      | hob cut         | shouldn't |
      | rounded corner  | should    |
      | inverted corner | should    |
      | single cut      | should    |
      | double cut      | shouldn't |
      | triple cut      | shouldn't |

  Scenario: F007 Delete sink
    Given I have navigated to the sink page
    And I have selected material steel and model NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm
    When I navigate to the item-list page
    Then it should include worktop items and sink items
    When I select to delete the sink
    Then the remove confirmation dialog is shown
    When I confirm that I want to remove the selection
    Then it should only include worktop items and not any sink items

  Scenario: F007 Delete cut out
    Given I have navigated to the cut-outs page
    And I have added a single cut
    When I navigate to the item-list page
    Then it should include worktop items and single cut items
    When I select to delete the single cut
    Then the remove confirmation dialog is shown
    When I confirm that I want to remove the selection
    Then it should only include worktop items and not any single cut items

  Scenario: F007 Change mind when deleting cut out
    Given I have navigated to the cut-outs page
    And I have added a single cut
    When I navigate to the item-list page
    Then it should include worktop items and single cut items
    When I select to delete the single cut
    Then the remove confirmation dialog is shown
    When I don't confirm that I want to remove the selection
    Then it should include worktop items and single cut items
    #And country should be displayed

  Scenario: F007 Change mind when deleting sink
    Given I have navigated to the sink page
    And I have selected material steel and model NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm
    When I press the View summary button
    Then it should include worktop items and sink items
    When I select to delete the sink
    Then the remove confirmation dialog is shown
    When I don't confirm that I want to remove the selection
    Then it should include worktop items and sink items

  Scenario: F007 Item list handling kitchen island
    Given I have navigated to the kitchen-island page
    And selecting a kitchen island
    And I press the View summary button
    Then it should include worktop items and kitchen island items
    When I select to delete the kitchen island
    Then the remove confirmation dialog is shown
    When I confirm that I want to remove the selection
    Then it should only include worktop items and not any kitchen island items

  #Scenario: F007 Item list handling overhang
    #Given I have navigated to the Style page
    #When I select wood material
    #And I navigate to the Plan page
    #And I select shape U-shape
    #And I select three walls as walls and waterfall with overhang as overhang
    #And I select one side bottom left as overhang side
    #When I navigate to the item-list page
    #Then the item list should include worktop 9.12*(80347549 KARLBY 45.1-63.5*3.8) + 1.27*(80350184 Same Color Edge Wood) + 2*(10347562 Joint Wood)
    #And the overhang should be mentioned in the worktop summary

