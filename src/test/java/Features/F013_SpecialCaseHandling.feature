Feature: Special cases which only appears in some countries

  Scenario: F013 Check edge options for quartz in Taiwan
    Given I have navigated to the style page in country Taiwan
    When I have selected quartz material
    Then when I select expression <a>, the available edge options should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected
      | a             | b                                                        | c        | d      | e  | f                | g    |
      | white quartz  | straight, chamfered, round, half round, bevel, anti-drip | straight | 20, 40 | 20 | none, wall panel | none |
    And the edge image should be displayed

  Scenario: F013 Check edge options for acrylic in Taiwan
    Given I have navigated to the style page in country Taiwan
    When I have selected quartz material
    Then when I select expression <a>, the available edge options should be <b> with <c> default selected, the available thicknesses should be <d> with <e> default selected, the available splashback should be <f> with <g> default selected
      | a                       | b                                                        | c        | d      | e  | f                | g    |
      | whitemarbleeffectquartz | straight, chamfered, bevel, round, half round, anti-drip | straight | 20,40  | 20 | none, wall panel | none |

