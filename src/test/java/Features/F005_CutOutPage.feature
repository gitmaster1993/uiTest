Feature: Add cut outs to my custom worktop
  As a customer
  I want to add different cut outs to my worktop
  In order to make it fit my needs

  Background:
    Given I have navigated to the cut-outs page

  Scenario Outline: F005 Add cut outs
    When I select to add <a> hob cut outs, <b> rounded corners, <c> inverted corners, <d> single cuts, <e> double cuts and <f> triple cuts
    Then each cut out section should display the amount and price of those cut outs
    And the total price should be <g>
    Examples:
    | a | b | c | d | e | f  | g                                                                                                                                                                                                                                                            |
    | 1 | 1 | 1 | 1 | 1 | 1  | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(10345577 Cut Out Laminate) + 1*(10345577 Rounded Corner Laminate) + 1*(10345577 Inverted Corner Laminate) + 1*(10345577 Single Cut Laminate) + 1*(10345577 Double Cut Laminate) + 1*(10345577 Triple Cut Laminate)  |
    | 2 | 0 | 0 | 1 | 2 | 0  | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 2*(10345577 Cut Out Laminate) + 1*(10345577 Single Cut Laminate) + 2*(10345577 Double Cut Laminate)                                                                                                                    |
    | 5 | 6 | 7 | 8 | 9 | 10 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 5*(10345577 Cut Out Laminate) + 6*(10345577 Rounded Corner Laminate) + 7*(10345577 Inverted Corner Laminate) + 8*(10345577 Single Cut Laminate) + 9*(10345577 Double Cut Laminate) + 10*(10345577 Triple Cut Laminate) |

  Scenario: F005 Overlay image
    Then when I click the image of a hob cut out, rounded corner, inverted corner, single cut, double cut or triple cut an overlay image should be displayed

  Scenario: F005 Wall panel cut outs functionality
    When I have scrolled down 1000 pixels on the page
    Then wall panel cut outs should be disabled and collapsed
    When I navigate to the Style page
    And scrolling to the splashback style selector
    And I select wall panel splashback
    Then the total price should be 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.24*(70216662 SIBBARP 1 m2)
    When I navigate to the cut-outs page
    And I have scrolled down 1000 pixels on the page
    Then wall panel cut outs should be enabled and not collapsed

  Scenario Outline: F005 Add and remove wall panel cut outs
    When I navigate to the Style page
    And I have selected worktop material quartz, expression matt darkgrey marble effect quartz, edge same color and thickness 38 in the style tab
    And scrolling to the splashback style selector
    And I have selected splashback wall panel with material quartz and expression matt darkgrey marble effect quartz in the style tab
    And I navigate to the cut-outs page
    When I have scrolled down 1600 pixels on the page
    And I select to add <a> round small cut outs, <b> round big cut outs, <c> square cut outs, <d> single cuts, <e> double cuts and <f> triple cuts
    Then the total price should be <g>
    When I select to remove <a> round small cut outs, <b> round big cut outs, <c> square cut outs, <d> single cuts, <e> double cuts and <f> triple cuts
    Then the total price should be 1*(60354319 OXSTEN 45.1-63.5*3.8) + 1.24*(00395638 RÅHULT 1 m2)
    Examples:
      | a  | b  | c  | d  | e | f  | g                                                                                                                                        |
      | 1  | 1  | 1  | 1  | 1 | 1  | 1*(60354319 OXSTEN 45.1-63.5*3.8) + 1.24*(00395638 RÅHULT 1 m2) + 3*(30346260 WP Cut out Quartz) + 3*(60345947 WP Preparation Quartz)  |
      | 10 | 0  | 3  | 0  | 5 | 0  | 1*(60354319 OXSTEN 45.1-63.5*3.8) + 1.24*(00395638 RÅHULT 1 m2) + 13*(30346260 WP Cut out Quartz) + 5*(60345947 WP Preparation Quartz) |

   Scenario: F005 Add a freestanding hob
     When I navigate to the plan page
     And I select shape rectangular
     And I set the measurements A to 2000 and B to 635
     Then the total price should be 2*(10345493 SÄLJAN 45.1-63.5*3.8)
     When I navigate to the cut-outs page
     And I select to add 1 freestanding hob
     Then the total price should be 1.4*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.27*(20350182 Same Color Edge Laminate)
     When I set the freestanding hob width to 700 mm
     Then the total price should be 1.3*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.27*(20350182 Same Color Edge Laminate)
     When I navigate to the item-list page
     Then 1 freestanding hob is included in the item list
     When I delete the freestanding hob from item list
     Then no freestanding hob is included in the item list

