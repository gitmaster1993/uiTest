Feature: Select Material, expression, edges and thickness
  As a customer
  I want to select material, expression, edges and thickness of my worktop
  so that I can style it after my own needs

  Background:
    Given I have navigated to the Style page
  Scenario: F003 Default expression, edge, thickness and splashback
  Then when I select material <a> the default expression should be <b>, the default edge should be <c>, the default thickness should be <d> mm, the default splashback should be <e>, wall panel material should be <f> and expression should be <g>
      | a          | b                              | c          | d  | e    | f        | g        |
      | Laminate   | concreteeffectlaminate         | same color | 28 | none | disabled | disabled |
      | Wood       | karlbybeech                    | same color | 38 | none | disabled | disabled |
      | Solid Wood | beech                          | same color | 38 | none | disabled | disabled |
      | Quartz     | anthracite stone effect/quartz | same color | 38 | none | disabled | disabled |
      | Acrylic    | white acrylic                  | same color | 38 | none | disabled | disabled |

  Scenario: F003 Select expressions
    Then when I select material <a> then the available expression should be <b>
      | a          | b                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
      | Laminate   | concrete effect laminate,lightoakeffectlaminate,aluminiumeffectlaminate,asheffectlaminate,blacklaminate,blackmarbleeffectlaminate,blackmineraleffectlaminate,blackstoneeffectlaminate,brownblacklaminate,darkgreylineneffectlaminate,darkgreylaminate,darkoakeffectlaminate,lightgreylaminate,lightgreystoneeffectlaminate,lightwalnuteffectlaminate,offwhitelaminate,walnuteffectlaminate,whitehighglosslaminate,whitelaminate,whitemarbleeffectlaminate,whitestoneeffectlaminate                                                                         |
      | Wood       | karlby beech, karlby walnut, karlby birch, karlby oak, mllekullaoak, barkaboda walnut,pinnarpash,barkabodawalnut                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
      | Solid Wood | beech,birch,oak                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
      | Quartz     | white quartz,anthracite stone effect/quartz,light grey stone effect/quartz,dark grey stone effect/quartz,black stone effect/quartz,white marble effect/quartz,white mineral effect/quartz,mattdarkgreymarbleeffectquartz                                                                                                                                                                                                                                                                                                                                  |
      | Acrylic    | white acrylic                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |

  Scenario: F003 Remember selected expression for each material
    Given I have selected have selected material a and expression b
      | a          | b                                   |
      | Laminate   | off white laminate                  |
      | Wood       | karlby birch                        |
      | Solid Wood | oak                                 |
      | Quartz     | matt dark grey/marble effect quartz |
      | Acrylic    | white acrylic                       |
    Then when selecting the same materials again the previous selected expressions should be remembered

  Scenario: F003 Select Laminate expression
    Given I have selected Laminate material
    Then when I select expression <a>, the available edge expressions should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected and the price should be <h>
      | a                                | b                    | c          | d        | e  | f                                   | g    | h                                   |
      | aluminium effect laminate        | same color,aluminium | same color | 38       | 38 | none, wall panel, wall edging strip | none | 1*(10345493 SÄLJAN 45.1-63.5*3.8)   |
      | ash effect laminate              | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(50348135 EKBACKEN 45.1-63.5*2.8) |
      | concrete effect laminate         | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(20345459 EKBACKEN 45.1-63.5*2.8) |
      | brown black laminate             | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(60345462 EKBACKEN 45.1-63.5*2.8) |
      | white high gloss/laminate        | same color,aluminium | same color | 28,38,77 | 28 | none, wall panel, wall edging strip | none | 1*(80345461 EKBACKEN 45.1-63.5*2.8) |
      | white marble effect/laminate     | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(70345466 EKBACKEN 45.1-63.5*2.8) |
      | light oak effect laminate        | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(80345456 EKBACKEN 45.1-63.5*2.8) |
      | off white laminate               | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(20345464 EKBACKEN 45.1-63.5*2.8) |
      | light walnut effect laminate     | same color,aluminium | same color | 38       | 38 | none, wall panel, wall edging strip | none | 1*(50345491 SÄLJAN 45.1-63.5*3.8)   |
      | light grey laminate              | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(00345460 EKBACKEN 45.1-63.5*2.8) |
      | light grey stone effect/laminate | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(40345458 EKBACKEN 45.1-63.5*2.8) |
      | dark oak effect laminate         | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(60345457 EKBACKEN 45.1-63.5*2.8) |
      | dark grey laminate               | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(40345463 EKBACKEN 45.1-63.5*2.8) |
      | dark grey/linen effect laminate  | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(90354313 EKBACKEN 45.1-63.5*2.8) |
      | black laminate                   | same color,aluminium | same color | 28,38,77 | 28 | none, wall panel, wall edging strip | none | 1*(50345467 EKBACKEN 45.1-63.5*2.8) |
      | black marble effect/laminate     | same color,aluminium | same color | 38       | 38 | none, wall panel, wall edging strip | none | 1*(20345497 SÄLJAN 45.1-63.5*3.8)   |
      | black mineral effect/laminate    | same color,aluminium | same color | 38       | 38 | none, wall panel, wall edging strip | none | 1*(40345496 SÄLJAN 45.1-63.5*3.8)   |
      | black stone effect/laminate      | same color,aluminium | same color | 28       | 28 | none, wall panel, wall edging strip | none | 1*(90345465 EKBACKEN 45.1-63.5*2.8) |
      | walnut effect laminate           | same color,aluminium | same color | 38       | 38 | none, wall panel, wall edging strip | none | 1*(60345495 SÄLJAN 45.1-63.5*3.8)   |
      | white laminate                   | same color,aluminium | same color | 28,38,77 | 28 | none, wall panel, wall edging strip | none | 1*(30345430 EKBACKEN 45.1-63.5*2.8) |
      | white stone effect/laminate      | same color,aluminium | same color | 38       | 38 | none, wall panel, wall edging strip | none | 1*(70345490 SÄLJAN 45.1-63.5*3.8)   |

  Scenario: F003 Select Wood expression
    Given I have selected Wood material
    Then when I select expression <a>, the available edge expressions should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected and the price should be <h>
      | a                | b          | c          | d  | e  | f                                   | g    | h                                     |
      | karlby beech     | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(00347548 KARLBY 45.1-63.5*3.8)     |
      | karlby walnut    | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(50351316 KARLBY 45.1-63.5*3.8)     |
      | barkaboda walnut | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(90347558 BARAKABODA 45.1-63.5*3.8) |
      | karlby birch     | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(80347549 KARLBY 45.1-63.5*3.8)     |
      | karlby oak       | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(60347550 KARLBY 45.1-63.5*3.8)     |
      | möllekulla oak   | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(50347555 MÖLLEKULLA 45.1-63.5*3.8) |

  Scenario: F003 Select Solid Wood expression
    Given I have selected Solid Wood material
    Then when I select expression <a>, the available edge expressions should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected and the price should be <h>
      | a     | b          | c          | d  | e  | f                                   | g    | h                                  |
      | beech | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(80345437 SKOGARP 45.1-63.5*4.0) |
      | birch | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(40345566 SKOGARP 45.1-63.5*4.0) |
      | oak   | same color | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(20345567 SKOGARP 45.1-63.5*4.0) |

  Scenario: F003 Select Quartz expression
    Given I have selected Quartz material
    Then when I select expression <a>, the available edge expressions should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected and the price should be <h>
      | a                                   | b                    | c          | d  | e  | f                                   | g    | h                                 |
      | white quartz                        | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(20345435 OXSTEN 45.1-63.5*3.8) |
      | anthracite stone effect/quartz      | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(30345524 OXSTEN 45.1-63.5*3.8) |
      | light grey stone effect/quartz      | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(50345518 OXSTEN 45.1-63.5*3.8) |
      | dark grey stone effect/quartz       | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(70345522 OXSTEN 45.1-63.5*3.8) |
      | black stone effect/quartz           | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(50345523 OXSTEN 45.1-63.5*3.8) |
      | white marble effect/quartz          | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(90345521 OXSTEN 45.1-63.5*3.8) |
      | white mineral effect/quartz         | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(30345519 OXSTEN 45.1-63.5*3.8) |
      | matt dark grey/marble effect quartz | same color,aluminium | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(60354319 OXSTEN 45.1-63.5*3.8) |

  Scenario: F003 Select Acrylic expression
    Given I have selected Acrylic material
    Then when I select expression <a>, the available edge expressions should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected and the price should be <h>
      | a             | b                     | c          | d  | e  | f                                   | g    | h                                 |
      | white acrylic | same color,aluminium  | same color | 38 | 38 | none, wall panel, wall edging strip | none | 1*(50345434 FORNBY 45.1-63.5*3.8) |

  Scenario: F003 Select laminate edge and thickness
    Given I have selected Laminate material
    Then when I select expression <a>, edge <b> and thickness <c> then the price should be <d> kr
      | a                         | b          | c  | d                                                                             |
      | white high gloss/laminate | same color | 38 | 1*(90345494 SÄLJAN 45.1-63.5*3.8)                                             |
      | white high gloss/laminate | same color | 77 | 1*(30345557 DEJE 45.1-63.5*7.7)                                               |
      | white high gloss/laminate | aluminium  | 28 | 1*(80345461 EKBACKEN 45.1-63.5*2.8) + 1*(60340558 Aluminium Edge 28 Laminate) |
      | white high gloss/laminate | aluminium  | 38 | 1*(90345494 SÄLJAN 45.1-63.5*3.8) + 1*(60345570 Aluminium Edge 38 Laminate)   |
      | white high gloss/laminate | aluminium  | 77 | 1*(30345557 DEJE 45.1-63.5*7.7) + 1*(40345571 Aluminium Edge 77 Laminate)     |
      | black laminate            | same color | 38 | 1*(00345498 SÄLJAN 45.1-63.5*3.8)                                             |
      | black laminate            | same color | 77 | 1*(10345563 DEJE 45.1-63.5*7.7)                                               |
      | black laminate            | aluminium  | 28 | 1*(50345467 EKBACKEN 45.1-63.5*2.8) + 1*(60340558 Aluminium Edge 28 Laminate) |
      | black laminate            | aluminium  | 38 | 1*(00345498 SÄLJAN 45.1-63.5*3.8) + 1*(60345570 Aluminium Edge 38 Laminate)   |
      | black laminate            | aluminium  | 77 | 1*(10345563 DEJE 45.1-63.5*7.7) + 1*(40345571 Aluminium Edge 77 Laminate)     |
      | white laminate            | same color | 38 | 1*(90345432 SÄLJAN 45.1-63.5*3.8)                                             |
      | white laminate            | same color | 77 | 1*(10345558 DEJE 45.1-63.5*7.7)                                               |
      | white laminate            | aluminium  | 28 | 1*(30345430 EKBACKEN 45.1-63.5*2.8) + 1*(60340558 Aluminium Edge 28 Laminate) |
      | white laminate            | aluminium  | 38 | 1*(90345432 SÄLJAN 45.1-63.5*3.8) + 1*(60345570 Aluminium Edge 38 Laminate)   |
      | white laminate            | aluminium  | 77 | 1*(10345558 DEJE 45.1-63.5*7.7) + 1*(40345571 Aluminium Edge 77 Laminate)     |

  Scenario: F003 Overlay image
    Then when I click the image of a material, expression, edge or thickness that is already selected an overlay image should be displayed

  Scenario: F003 Inspirational picture updated for material selection
    Given I have scrolled down on the page
    When I select wood material
    Then a button should appear indicating that the inspirational picture has been updated
    And clicking the button should scroll the page to the top to display the updated picture

  Scenario: F003 Inspirational picture updated for expression selection
    Given I have scrolled down on the page
    When I select white laminate expression
    Then a button should appear indicating that the inspirational picture has been updated
    And clicking the button should scroll the page to the top to display the updated picture

  Scenario: F003 Disabled material when selecting edge
    When I select aluminium edge
    Then the materials wood and solid wood should be disabled

  Scenario Outline: F003 Disabled material when selecting thickness
    When I select thickness <x>
    Then the materials wood and solid wood should be disabled
    Examples:
      | x  |
      | 77 |


  Scenario: F003 Wall panel height price calculation
    When scrolling to the splashback style selector
    And I select wall panel splashback
    Then the splashback height is 550 mm
    And the total price should be 1*(90345494 SÄLJAN 45.1-63.5*3.8) + 1.24*(70216662 SIBBARP 1 m2)
    When I set the splashback height to 1000 mm
    Then the total price should be 1*(90345494 SÄLJAN 45.1-63.5*3.8) + 2.24*(70216662 SIBBARP 1 m2)

  Scenario Outline: F003 Wall panel height error messages
    When scrolling to the splashback style selector
    And I select wall panel splashback
    And I set the splashback height to <a> mm
    Then it <b> display error message
    And the label should be set to <a> in pictogram
    Examples:
      | a     | b          |
      | 1200  | should not |
      | 1201  | should     |
      | 100   | should not |
      | 99    | should     |
      | 0     | should     |

  Scenario Outline: F003 Wall panels for C-shape
    When scrolling to the splashback style selector
    And I set the splashback to wall panel and material to <a> and expression to <b>
    Then the total price should be <c>
    When I navigate to the Plan page
    And I select shape C-shape
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | a        | b                           | c                                                                 | walls            | price |
      | laminate | brown black laminate        | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.24*(80216666 SIBBARP 1 m2)  | four walls left  | 6.99*(90345494 SÄLJAN 45.1-63.5*3.8) + 4*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate) + 4.25*(80216666 SIBBARP 1 m2)  |
      | glass    | white glass                 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.25*(80216751 ISHULT 1 m2)   | five walls       | 6.99*(90345494 SÄLJAN 45.1-63.5*3.8) + 4*(10345577 Joint Laminate) + 4.61*(80216751 ISHULT 1 m2)                                              |
      | quartz   | white mineral effect quartz | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.24*(70216624 RÅHULT 1 m2)   | four walls right | 6.99*(90345494 SÄLJAN 45.1-63.5*3.8) + 4*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate) + 4.26*(70216624 RÅHULT 1 m2)   |
      | acrylic  | white acrylic               | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.24*(40216649 KLINGSTA 1 m2) | three walls      | 6.99*(90345494 SÄLJAN 45.1-63.5*3.8) + 4*(10345577 Joint Laminate) + 1.27*(20350182 Same Color Edge Laminate) + 3.92*(40216649 KLINGSTA 1 m2) |

  Scenario Outline: F003 Wall panels for irregular shape
    When I select wood material, karlby beech expression and 38 as thickness
    And scrolling to the splashback style selector
    And I set the splashback to wall panel and material to <a> and expression to <b>
    Then the total price should be <c>
    When I navigate to the Plan page
    And I select shape Irregular
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | a        | b                               | c                                                                 | walls               | price |
      | laminate | stainless steel colour laminate | 1*(00347548 KARLBY 45.1-63.5*3.8) + 1.24*(80193721 SIBBARP 1 m2)  | three walls         | 3*(40347551 KARLBY 63.6-125*3.8) + 1*(10347562 Joint Wood) + 2.43*(80193721 SIBBARP 1 m2)                                        |
      | glass    | black glass                     | 1*(00347548 KARLBY 45.1-63.5*3.8) + 1.25*(60216752 ISHULT 1 m2)   | one long wall       | 3*(40347551 KARLBY 63.6-125*3.8) + 1*(10347562 Joint Wood) + 1.44*(80350184 Same Color Edge Wood) + 1.66*(60216752 ISHULT 1 m2)  |
      | quartz   | lightgrey stone effect quartz   | 1*(00347548 KARLBY 45.1-63.5*3.8) + 1.24*(10216622 RÅHULT 1 m2)   | two walls top right | 3*(40347551 KARLBY 63.6-125*3.8) + 1*(10347562 Joint Wood) + 0.64*(80350184 Same Color Edge Wood) + 2.09*(10216622 RÅHULT 1 m2)  |
      | acrylic  | white acrylic                   | 1*(00347548 KARLBY 45.1-63.5*3.8) + 1.24*(40216649 KLINGSTA 1 m2) | two walls top left  | 3*(40347551 KARLBY 63.6-125*3.8) + 1*(10347562 Joint Wood) + 0.8*(80350184 Same Color Edge Wood) + 2.00*(40216649 KLINGSTA 1 m2) |

  Scenario: F003 Overhang image and price
    Given I have navigated to the Style page
    When I select quartz material
    And I navigate to the Plan page
    And I select shape II-shape
    And I select three walls right as walls and emerald as overhang
    And I select two sides left as overhang side
    Then a button should appear indicating that the inspirational picture has been updated
    When clicking the button should scroll the page to the top to display the updated picture
    Then the overhang image should be displayed
    And the total price should be 7*(30345524 OXSTEN 45.1-63.5*3.8) + 4.77*(30350313 Same Color Edge Quartz) + 4*(50345580 Joint Quartz)
    When I select three walls right as walls and half emerald as overhang
    And I select one side top left as overhang side
    Then the total price should be 6.5*(30345524 OXSTEN 45.1-63.5*3.8) + 4.77*(30350313 Same Color Edge Quartz) + 2*(50345580 Joint Quartz)

  Scenario: F003 Match worktop style for wall panel material
    When I select laminate material, concreteeffectlaminate expression and 28 as thickness
    And scrolling to the splashback style selector
    And I set the splashback to wall panel and material to match worktop style
    Then splashback wall panel with material laminate and expression aluminiumeffectlaminate should be selected
    When I select quartz material, white quartz expression and 38 as thickness
    Then splashback wall panel with material quartz and expression white quartz should be selected
    When I select acrylic material, white acrylic expression and 38 as thickness
    Then splashback wall panel with material acrylic and expression white acrylic should be selected
    When I select no splashback
    And I select wood material, barkaboda walnut expression and 38 as thickness
    And scrolling to the splashback style selector
    And I select wall panel splashback
    Then splashback wall panel with material acrylic and expression white acrylic should be selected

