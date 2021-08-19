Feature: Select Sink and fastening
  As a customer
  I want to select a sink to my worktop
  so that I can see the price including the sink

  Scenario: F004 Default sink
    Given I have navigated to the Sink page
    Then when I select sink material <a> the default sink should be <b> and the default fastening should be <c>
      | a         | b                                                            | c            |
      | No Sink   | NoSink                                                       | no Fastening |
      | steel     | norrsjninsetsink1bowlstainlesssteel21x44cm                   | top Mounted  |
      | composite | hllvikeninsetsink1bowlwhitequartzcomposite56x50cm            | top Mounted  |
      | porcelain | havseninsetsink1bowlwhite53x47cm                             | top Mounted  |


  Scenario: F004 Select sink model
    Given I have navigated to the Sink page
    Then when I select sink material <a> the the available models should be <b>
      | a         | b           |
      | steel     | norrsjninsetsink1bowlstainlesssteel21x44cm,norrsjninsetsink1bowlstainlesssteel37x44cm,norrsjninsetsink1bowlstainlesssteel54x44cm,norrsjninsetsink1bowlstainlesssteel73x44cm,norrsjninsetsink2bowlsstainlesssteel73x44cm,lnguddeninsetsink1bowlstainlesssteel46x46cm,lnguddeninsetsink1bowlstainlesssteel56x53cm,lnguddeninsetsink2bowlsstainlesssteel75x53cm,hillesjninsetsink112bowlstainlesssteel58x46cm,hillesjninsetsink112bowlstainlesssteel75x46cm,vattudaleninsetsink1bowlwithdrainboardstainlesssteel69x47cm,vattudaleninsetsink1bowlwithdrainboardstainlesssteel86x47cm,vattudalen88x53,vattudalen109x53,boholmeninsetsink1bowlstainlesssteel47x30cm,boholmen45,ammernonsetsink1bowlstainlesssteel60x635cm|
      | composite | hllvikeninsetsink1bowlwhitequartzcomposite56x50cm,hllvikeninsetsink1bowlblackquartzcomposite56x50cm,hllviken100x50white,hllviken100x50black                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
      | porcelain | havseninsetsink1bowlwhite53x47cm,havsensinkbowlwvisiblefrontwhite62x48cm, havsensinkbowl2bowlswvisiblefrontwhite82x48cm|


  Scenario: F004 Select steel expression
    Given I have navigated to the Sink page
    Given I have selected steel material for the sink
    Then when I select model <a>, the available fastenings should be <b> with <c> default selected, there should be default <d> tapholes selected and the price should be <e>
      | a                                                                       | b                      | c           | d | e                                                                                                                                                                                       |
      | NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm                     | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(10333640 NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)                   |
      | NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm                     | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(49157650 NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)                   |
      | NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm                     | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(49157909 NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)                   |
      | NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm                    | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(39157919 NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)                  |
      | NORRSJÖN inset sink 1 bowl stainless steel 73x44 cm                     | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(19157915 NORRSJÖN inset sink 1 bowl stainless steel 73x44 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)                   |
      | LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm                    | top Mounted,underGlued | top Mounted | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(59157391 LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm) + 1*(10345577 Cut Out Laminate)                                                   |
      | LÅNGUDDEN inset sink 1 bowl stainless steel 56x53 cm                    | top Mounted,underGlued | top Mounted | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(29157477 LÅNGUDDEN inset sink 1 bowl stainless steel 56x53 cm) + 1*(10345577 Cut Out Laminate)                                                   |
      | LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm                   | top Mounted,underGlued | top Mounted | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(19157487 LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm) + 1*(10345577 Cut Out Laminate)                                                  |
      | HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm                | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(49140631 HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)              |
      | HILLESJÖN inset sink 1 1/2 bowl stainless steel 75x46 cm                | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(29171138 HILLESJÖN inset sink 1 1/2 bowl stainless steel 75x46 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)              |
      | VATTUDALEN inset sink 1 bowl with drainboard stainless steel 69x47 cm   | top Mounted,underGlued | top Mounted | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(69158168 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 69x47 cm) + 1*(10345577 Cut Out Laminate)                                  |
      | VATTUDALEN inset sink 1 bowl with drainboard stainless steel 86x47 cm   | top Mounted,underGlued | top Mounted | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(29158189 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 86x47 cm) + 1*(10345577 Cut Out Laminate)                                  |
      | VATTUDALEN 88x53                                                        | top Mounted,underGlued | top Mounted | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(89158191 VATTUDALEN 88x53) + 1*(10345577 Cut Out Laminate)                                                                                       |
      | VATTUDALEN 109x53                                                       | top Mounted,underGlued | top Mounted | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(09158190 VATTUDALEN 109x53) + 1*(10345577 Cut Out Laminate)                                                                                      |
      | BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm                     | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(99157501 BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)                   |
      | BOHOLMEN 45                                                             | top Mounted,underGlued | top Mounted | 1 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(79157494 BOHOLMEN 45) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate)                                                           |

  Scenario: F004 Select steel expression for AMMERÅN
    Given I have navigated to the Style page
    When I select ash effect laminate expression
    And I navigate to the Sink page
    Given I have selected steel material for the sink
    Then when I select model <a>, the available fastenings should be <b> with <c> default selected, there should be default <d> tapholes selected and the price should be <e>
      | a                                                                       | b                      | c           | d | e                                                                                                       |
      | AMMERÅN onset sink 1 bowl stainless steel 60x635 cm                     | fit Between            | fit Between | 0 | 1*(50348135 EKBACKEN 45.1-63.5*2.8) + 1*(59158164 AMMERÅN onset sink 1 bowl stainless steel 60x635 cm)  |

  Scenario: F004 Select composite expression
    Given I have navigated to the Sink page
    Given I have selected composite material for the sink
    Then when I select model <a>, the available fastenings should be <b> with <c> default selected, there should be default <d> tapholes selected and the price should be <e>
      | a                                                                       | b                       | c            | d | e                                                                                                                                             |
      | HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm             | top Mounted,underGlued  | top Mounted  | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(49157645 HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm) + 1*(10345577 Cut Out Laminate)  |
      | HÄLLVIKEN inset sink 1 bowl black quartz composite 56x50 cm             | top Mounted,underGlued  | top Mounted  | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(79157639 HÄLLVIKEN inset sink 1 bowl black quartz composite 56x50 cm) + 1*(10345577 Cut Out Laminate)  |
      | HÄLLVIKEN 100x50 white                                                  | top Mounted,underGlued  | top Mounted  | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(59158197 HÄLLVIKEN 100x50 white) + 1*(10345577 Cut Out Laminate)                                       |
      | HÄLLVIKEN 100x50 black                                                  | top Mounted,underGlued  | top Mounted  | 0 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(79158196 HÄLLVIKEN 100x50 black) + 1*(10345577 Cut Out Laminate)                                       |

  Scenario: F004 Select porcelain expression
    Given I have navigated to the Plan page
    When I select shape Rectangular
    And I set the measurements A to 3000 and B to 635
    When I navigate to the Sink page
    And I have selected porcelain material for the sink
    Then when I select model <a>, the available fastenings should be <b> with <c> default selected, there should be default <d> tapholes selected and the price should be <e>
      | a                                        | b           | c           | d | e                                                                                                                                                         |
      | havseninsetsink1bowlwhite53x47cm         | top Mounted | top Mounted | 1 | 3*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(80377843 havseninsetsink1bowlwhite53x47cm) + 1*(10345577 Cut Out Laminate) + 1*(00345573 Tap Hole Laminate) |
      | havsensinkbowlwvisiblefrontwhite62x48cm  | fit Between | fit Between | 1 | 3*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(20359229 havsensinkbowlwvisiblefrontwhite62x48cm) + 1*(10345577 Cut Out Laminate)+ 1*(00345573 Tap Hole Laminate)                               |
      |havsensinkbowl2bowlswvisiblefrontwhite82x48cm|fitBetween| fitBetween  | 1 | 3*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(90359216 havsensinkbowl2bowlswvisiblefrontwhite82x48cm) + 1*(10345577 Cut Out Laminate)+ 1*(00345573 Tap Hole Laminate)                               |

  Scenario: F004 Select fastening for steel sinks
    Given I have navigated to the Sink page
    Given I have selected steel material for the sink
    Then when I select model <a> and fastening <b> the price should be <c> kr
      | a                                                                     | b          | c                                                                                                                                                       |
      | NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm                   | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(00353346 NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm) + 1*(00345573 Tap Hole Laminate)                   |
      | NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm                   | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(80353352 NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm) + 1*(00345573 Tap Hole Laminate)                   |
      | NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm                  | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(70338668 NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm) + 1*(00345573 Tap Hole Laminate)                  |
      | NORRSJÖN inset sink 1 bowl stainless steel 73x44 cm                   | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(60353353 NORRSJÖN inset sink 1 bowl stainless steel 73x44 cm) + 1*(00345573 Tap Hole Laminate)                   |
      | LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm                  | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(20338661 LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm)                                                   |
      | LÅNGUDDEN inset sink 1 bowl stainless steel 56x53 cm                  | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(00338662 LÅNGUDDEN inset sink 1 bowl stainless steel 56x53 cm)                                                   |
      | LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm                 | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(80338663 LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm)                                                  |
      | HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm              | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(80338658 HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm) + 1*(00345573 Tap Hole Laminate)              |
      | HILLESJÖN inset sink 1 1/2 bowl stainless steel 75x46 cm              | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(60338659 HILLESJÖN inset sink 1 1/2 bowl stainless steel 75x46 cm) + 1*(00345573 Tap Hole Laminate)              |
      | VATTUDALEN inset sink 1 bowl with drainboard stainless steel 69x47 cm | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(30352270 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 69x47 cm)                                  |
      | VATTUDALEN inset sink 1 bowl with drainboard stainless steel 86x47 cm | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(30338670 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 86x47 cm)                                  |
      | BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm                   | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(60348031 BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm) + 1*(00345573 Tap Hole Laminate)                   |

  Scenario: F004 Select fastening for composite sinks
    Given I have navigated to the Sink page
    Given I have selected composite material for the sink
    Then when I select model <a> and fastening <b> the price should be <c> kr
      | a                                                           | b          | c                                                                                                            |
      | HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(40348013 HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm) |
      | HÄLLVIKEN inset sink 1 bowl black quartz composite 56x50 cm | underGlued | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(60348012 HÄLLVIKEN inset sink 1 bowl black quartz composite 56x50 cm) |

  Scenario: F004 Add tap holes
    Given I have navigated to the Sink page
    Given I have selected steel material for the sink
    Then when I add <a> tap holes the price should be <b>
      | a  | b                                                                                                                                                                          |
      | 0  | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(10333640 NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm) + 1*(10345577 Preparation Laminate)                                   |
      | 1  | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(10333640 NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm) + 1*(10345577 Preparation Laminate) + 1*(00345573 Tap Hole Laminate)  |
      | 25 | 1*(10345493 SÄLJAN 45.1-63.5*3.8) + 1*(10333640 NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm) + 1*(10345577 Preparation Laminate) + 25*(00345573 Tap Hole Laminate) |

  Scenario: F004 Drainer grooves available
    Given I have navigated to the Style page
    And I have selected Quartz material
    When I navigate to the Sink page
    Then when I select material <a> and model <b> and fastening <c>, <d> should be available
      | a         | b                                                                       | c           | d                  |
      | steel     | norrsjninsetsink1bowlstainlesssteel21x44cm                              | top Mounted | No Drainer Grooves    |
      | steel     | norrsjninsetsink1bowlstainlesssteel37x44cm                              | top Mounted | No Drainer Grooves |
      | steel     | norrsjninsetsink1bowlstainlesssteel54x44cm                              | top Mounted  | No Drainer Grooves    |
      | steel     | norrsjninsetsink1bowlstainlesssteel73x44cm                              | top Mounted | No Drainer Grooves |
      | steel     | norrsjninsetsink2bowlsstainlesssteel73x44cm                             | top  Mounted | No Drainer Grooves    |
      | steel     | lnguddeninsetsink1bowlstainlesssteel46x46cm                             | top Mounted | No Drainer Grooves |
      | steel     | lnguddeninsetsink1bowlstainlesssteel56x53cm                             | top Mounted  | No Drainer Grooves    |
      | steel     | lnguddeninsetsink2bowlsstainlesssteel75x53cm                            | top Mounted  | No Drainer Grooves    |
      | steel     | hillesjninsetsink112bowlstainlesssteel58x46cm                           | top Mounted | No Drainer Grooves |
      | steel     | hillesjninsetsink112bowlstainlesssteel75x46cm                           | top Mounted  | No Drainer Grooves    |
      | steel     | vattudaleninsetsink1bowlwithdrainboardstainlesssteel69x47cm             | top Mounted | No Drainer Grooves |
      | steel     | vattudaleninsetsink1bowlwithdrainboardstainlesssteel86x47cm             | top Mounted  | No Drainer Grooves    |
      | steel     | vattudalen88x53                                                         | top Mounted | No Drainer Grooves |
      | steel     | vattudalen109x53                                                        | top Mounted  | No Drainer Grooves    |
      | steel     | boholmen45                                                              | top Mounted  | No Drainer Grooves |
      | steel     | ammernonsetsink1bowlstainlesssteel60x635cm                              | fitBetween   | No Drainer Grooves    |
      | steel     | norrsjninsetsink1bowlstainlesssteel21x44cm                              | top Mounted  | No Drainer Grooves    |
      | composite | hllvikeninsetsink1bowlwhitequartzcomposite56x50cm                       | top Mounted  | No Drainer Grooves    |
      | composite | hllvikeninsetsink1bowlblackquartzcomposite56x50cm                       | top Mounted  | No Drainer Grooves    |
      | composite | hllviken100x50white                                                     | top Mounted  | No Drainer Grooves |
      | composite | hllviken100x50black                                                     | top Mounted  | No Drainer Grooves |
      | porcelain | havseninsetsink1bowlwhite53x47cm                                        | top Mounted  | No Drainer Grooves |
      | porcelain | havsensinkbowlwvisiblefrontwhite62x48cm                                 | fit Between  | No Drainer Grooves |
      | porcelain | havsensinkbowl2bowlswvisiblefrontwhite82x48cm                           | fit Between  | No Drainer Grooves |


  Scenario: F004 Select drainer grooves for Quartz material
    Given I have navigated to the Style page
    And I have selected Quartz material
    When I navigate to the Sink page
    Then when I select material <a>, model <b>, fastening <c> and add drainer grooves the price should be <d>
      | a     | b                                                        | c          | d                                                                                                                                                                              |
      | steel | NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm      | underGlued | 1*(30345524 OXSTEN 45.1-63.5*3.8) + 1*(80353347 NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm) + 1*(80345588 Drainer Grooves Quartz) + 1*(80345574 Tap Hole Quartz)      |
      | steel | HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm | underGlued | 1*(30345524 OXSTEN 45.1-63.5*3.8) + 1*(20351935 HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm) + 1*(80345588 Drainer Grooves Quartz) + 1*(80345574 Tap Hole Quartz) |

  Scenario: F004 Select drainer grooves for Acrylic material
    Given I have navigated to the Style page
    And I have selected Acrylic material
    When I navigate to the Sink page
    Then when I select material <a>, model <b>, fastening <c> and add drainer grooves the price should be <d>
      | a     | b                                                    | c          | d                                                                                                                                                                           |
      | steel | LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm | underGlued | 1*(50345434 FORNBY 45.1-63.5*3.8) + 1*(10352011 LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm) + 1*(40345585 Drainer Grooves Acrylic)                                |
      | steel | BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm  | underGlued | 1*(50345434 FORNBY 45.1-63.5*3.8) + 1*(90351932 BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm) + 1*(40345585 Drainer Grooves Acrylic) + 1*(50345575 Tap Hole Acrylic) |

  Scenario: F004 Overlay image
    Given I have navigated to the Sink page
    Then when I click the image of a material, model, fastening or taphole that is already selected an overlay image should be displayed

  Scenario: F004 Inspirational picture updated for sink material selection
    Given I have navigated to the Sink page
    And I have scrolled down on the page
    When I have selected steel material for the sink
    Then a button should appear indicating that the inspirational picture has been updated
    And clicking the button should scroll the page to the top to display the updated picture

  Scenario: F004 Inspirational picture updated for sink model
    Given I have navigated to the Sink page
    And I have selected steel material for the sink
    And I have scrolled down on the page
    When I select model LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm
    Then a button should appear indicating that the inspirational picture has been updated
    And clicking the button should scroll the page to the top to display the updated picture

  Scenario: F004 Inspirational picture updated for fastening selection
    Given I have navigated to the Sink page
    And I have selected steel material for the sink
    And I select model LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm
    And I have scrolled down on the page
    When I select fastening underGlued
    Then a button should appear indicating that the inspirational picture has been updated
    And clicking the button should scroll the page to the top to display the updated picture

  Scenario Outline: F004 Display fastening info dialog
    Given I have navigated to the Sink page
    And I have selected steel material for the sink
    When I click the info icon for the <a> fastening
    Then a info dialog should be displayed with more information about the fastening
    And clicking the X mark in the dialog should close it
    Examples:
      | a          |
      | topMounted |
      | underGlued |

  Scenario: F004 Do not reset selections when deselecting sink
    Given I have navigated to the style page
    And I have selected quartz material
    And I navigate to the sink page
    And I have selected sink material steel, model LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm, fastening underGlued, drainer grooves yes and 2 tap holes in the sink tab
    When I select sink material no Sink
    And I select sink material steel
    Then model LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm, fastening topMounted, drainer grooves no and 2 tap holes should be selected
    # Back to default except tap holes since it's a user selection

  Scenario: F004 Change fastening when changing sink
    Given I have navigated to the Sink page
    And I have selected porcelain material for the sink
    And I select model havsensinkbowlwvisiblefrontwhite62x48cm
    Then the fastening should be fit Between
    When I have selected steel material for the sink
    Then the fastening should be top Mounted
    And the price shouldn't indicate a conflict

  #Image is not missing any more, so not possible to test
  #Scenario: F004 Show pictogram for sink when image is missing
    #Given I have navigated to the Sink page
    #And I have selected steel material for the sink
    #And I select model NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm
    #Then the image should be missing and a pictogram should be displayed

  Scenario: F004 Check swiper arrows
    Given I have navigated to the Sink page
    When I select sink material steel
    And I select sink model NORRSJÖN inset sink 1 bowl stainless steel 73x44 cm
    Then the previous button is enabled for the image view
    And the next button is disabled for the image view
    When selecting the previous button for the image view
    Then the next button is enabled for the image view
    Then the worktop image is displayed


