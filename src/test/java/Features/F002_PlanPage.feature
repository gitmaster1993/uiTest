Feature: Select shape and measurement of worktop
  As a customer
  I want to select what shape and measurement the worktop should be
  In order to fit in my kitchen

  Background:
    Given I have navigated to the Plan page

  Scenario: F002 Default shape
    Then the default shape should be Rectangular
    #And the default overhang type is no overhang

  Scenario: F002 Default measurement
    Then when I select shape <a> the default measurement should be <b>
      | a           | b                          |
      | Rectangular | 1000,635                   |
      | L-Shape     | 3000,3000,635,635          |
      | U-Shape     | 3000,3000,3000,635,635,635 |
      | V-Shape     | 3000,3000                  |
      | C-Shape     | 3000,3000                  |
      | II-Shape    | 3000,635,3000,635          |
      | Irregular   | 3000,635,800               |

  Scenario Outline: F002 Flip shape
    Given I select shape <shapeName>
    When I press the flip button the shape should be flipped
    Examples:
      | shapeName |
      | L-Shape   |
      | V-Shape   |
      | C-Shape   |

  #Scenario: F002 Overlay image
    #Then when I click the image of a shape or wall selection that is already selected an overlay image should be displayed

  # SET SHAPE AND MEASUREMENTS AND CHECK ERROR MESSAGE

  Scenario: F002 Set rectangular shape measurement
    When I select shape Rectangular
    Then when I set the measurement A to <a> and B to <b> it should display <c>
      | a     | b    | c                |
      | 100   | 100  | No Error Message |
      | 99    | 300  | Error Message    |
      | 20000 | 1250 | No Error Message |
      | 15000 | 1251 | Error Message    |
      | 0     | 1251 | Error Message    |

  Scenario: F002 Set L-shaped measurement
    When I select shape L-Shape
    Then when I set the measurement A to <a>, B to <b>, C to <c> and D to <d> it should display <e>
      | a     | b     | c    | d    | e                |
      | 101   | 101   | 100  | 100  | No Error Message |
      | 200   | 300   | 99   | 100  | Error Message    |
      | 200   | 300   | 100  | 99   | Error Message    |
      | 1000  | 2000  | 1200 | 1200 | Error Message    |
      | 20000 | 20000 | 1250 | 1250 | No Error Message |
      | 20001 | 10000 | 1000 | 800  | Error Message    |
      | 9000  | 20001 | 1100 | 650  | Error Message    |
      | 1600  | 15000 | 1251 | 100  | Error Message    |

  Scenario: F002 Set U-shaped measurement
    When I select shape U-Shape
    Then when I set the measurement A to <a>, B to <b>, C to <c>, D to <d>, E to <e> and F to <f> it should display <g>
      | a     | b     | c     | d    | e    | f    | g                |
      | 201   | 101   | 101   | 100  | 100  | 100  | No Error Message |
      | 99    | 3000  | 4000  | 1000 | 100  | 100  | Error Message    |
      | 9999  | 1000  | 99    | 120  | 1000 | 120  | Error Message    |
      | 2000  | 10000 | 10000 | 1111 | 1000 | 1000 | Error Message    |
      | 20000 | 20000 | 20000 | 1250 | 1250 | 1250 | No Error Message |
      | 20001 | 19000 | 9000  | 400  | 500  | 600  | Error Message    |
      | 12222 | 20000 | 20001 | 100  | 100  | 545  | Error Message    |
      | 19999 | 13000 | 13000 | 1000 | 1251 | 500  | Error Message    |

  Scenario: F002 Set V-shaped measurement
    When I select shape V-Shape
    Then when I set the measurement A to <a> and B to <b> it should display <c>
      | a     | b     | c                |
      | 1216  | 1216  | No Error Message |
      | 1215  | 3000  | Error Message    |
      | 20000 | 20000 | No Error Message |
      | 12345 | 20001 | Error Message    |

  Scenario: F002 Set C-shaped measurement
    When I select shape C-Shape
    Then when I set the measurement A to <a> and B to <b> it should display <c>
      | a     | b     | c                |
      | 413   | 413   | No Error Message |
      | 412   | 3000  | Error Message    |
      | 3000  | 412   | Error Message    |
      | 20000 | 20000 | No Error Message |
      | 12345 | 20001 | Error Message    |
      | 12345 | 0     | Error Message    |

  Scenario: F002 Set II-shaped measurement
    When I select shape II-Shape
    Then when I set the measurement A to <a>, B to <b>, C to <c> and D to <d> it should display <e>
      | a     | b    | c     | d    | e                |
      | 100   | 100  | 100   | 100  | No Error Message |
      | 99    | 150  | 300   | 100  | Error Message    |
      | 200   | 300  | 99    | 100  | Error Message    |
      | 20000 | 1250 | 20000 | 1250 | No Error Message |
      | 9000  | 1251 | 11000 | 650  | Error Message    |
      | 10000 | 1250 | 19000 | 1251 | Error Message    |

  Scenario: F002 Set Irregular shape measurement
    When I select shape Irregular
    Then when I set the measurement A to <a>, B to <b> and C to <c> it should display <d>
      | a     | b    | c    | d                |
      | 100   | 100  | 100  | Error Message    |
      | 100   | 125  | 100  | Error Message    |
      | 100   | 100  | 125  | Error Message    |
      | 100   | 155  | 100  | No Error Message |
      | 100   | 100  | 155  | No Error Message |
      | 99    | 150  | 400  | Error Message    |
      | 8000  | 800  | 99   | Error Message    |
      | 20000 | 1250 | 1250 | Error Message    |
      | 20000 | 1251 | 1234 | Error Message    |
      | 20000 | 999  | 1251 | Error Message    |

  # SET SHAPE AND MEASUREMENTS AND CHECK PRICE

  Scenario: F002 Calculate price for rectangular shape
    When I select shape rectangular
    Then when I set the measurement A to <a> and B to <b> the price should be <c> kr
      | a     | b    | c                                                                  |
      | 920   | 100  | 0.92*(90345446 EKBACKEN 10-45*2.8)                                  |
      | 10000 | 500  | 10*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) |
      | 20000 | 1250 | 20*(60345476 EKBACKEN 63.6-125*2.8) + 4*(10345577 Joint Laminate)  |

  Scenario: F002 Calculate price for L-shape
    When I select shape L-Shape
    Then when I set the measurement A to <a>, B to <b>, C to <c> and D to <d> the price should be <e> kr
      | a     | b     | c    | d    | e                                                                                                               |
      | 2000  | 2000  | 450  | 450  | 2*(90345446 EKBACKEN 10-45*2.8) + 1.55*(90345446 EKBACKEN 10-45*2.8) + 1*(10345577 Joint Laminate)              |
      | 12345 | 6789  | 451  | 635  | 6.79*(40345463 EKBACKEN 45.1-63.5*2.8) + 11.71*(40345463 EKBACKEN 45.1-63.5*2.8) + 4*(10345577 Joint Laminate)  |
      | 20000 | 1251  | 1250 | 100  | 20*(60345476 EKBACKEN 63.6-125*2.8) + 0.01*(90345446 EKBACKEN 10-45*2.8) + 5*(10345577 Joint Laminate)          |

  Scenario: F002 Calculate price for U-shape
    When I select shape U-Shape
    Then when I set the measurement A to <a>, B to <b>, C to <c>, D to <d>, E to <e> and F to <f> the price should be <e> kr
      | a     | b     | c     | d    | e    | f    | g                                                                                                                                                    |
      | 201   | 101   | 101   | 100  | 100  | 100  | 0.23*(90345446 EKBACKEN 10-45*2.8) + 2*(10345577 Joint Laminate)                                            |
      | 20000 | 2345  | 6789  | 450  | 635  | 1250 | 2.35*(40345463 EKBACKEN 45.1-63.5*2.8) + 6.79*(60345476 EKBACKEN 63.6-125*2.8) + 18.12*(90345446 EKBACKEN 10-45*2.8) + 7*(10345577 Joint Laminate)   |
      | 10000 | 4000  | 4000  | 635  | 1250 | 450  | 4*(60345476 EKBACKEN 63.6-125*2.8) + 8.75*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(90345446 EKBACKEN 10-45*2.8) + 4*(10345577 Joint Laminate)       |

  Scenario: F002 Calculate price for V-shape
    When I select shape v-shape
    Then when I set the measurement A to <a> and B to <b> the price should be <c> kr
      | a     | b     | c                                                                                                                                                                                            |
      | 1216  | 1216  | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 0.3*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate)                                          |
      | 1216  | 20000 | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 19.09*(40345463 EKBACKEN 45.1-63.5*2.8)  + 2*(10345577 Single Cut Laminate) + 6*(10345577 Joint Laminate) |
      | 20000 | 20000 | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 37.88*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 10*(10345577 Joint Laminate)                                        |

  Scenario: F002 Calculate price for C-shape
    When I select shape C-Shape
    Then when I set the measurement A to <a> and B to <b> the price should be <c> kr
      | a     | b     | c                                                                                                                                                     |
      | 413   | 413   | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*0.15*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate)    |
      | 20000 | 413   | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 19.89*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 6*(10345577 Joint Laminate)     |
      | 20000 | 20000 | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*19.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 10*(10345577 Joint Laminate)  |

  Scenario: F002 Calculate price for II-shape
    When I select shape II-Shape
    Then when I set the measurement A to <a>, B to <b>, C to <c> and D to <d> the price should be <e> kr
      | a     | b    | c     | d    | e                                                                                                           |
      | 100   | 100  | 100   | 100  | 2*0.1*(90345446 EKBACKEN 10-45*2.8)                                                                         |
      | 4000  | 635  | 4001  | 636  | 4*(40345463 EKBACKEN 45.1-63.5*2.8) + 4.01*(60345476 EKBACKEN 63.6-125*2.8) + 1*(10345577 Joint Laminate)   |
      | 100   | 100  | 20000 | 1250 | 0.1*(90345446 EKBACKEN 10-45*2.8) + 20*(60345476 EKBACKEN 63.6-125*2.8) + 4*(10345577 Joint Laminate)       |

  Scenario: F002 Calculate price for Irregular shape
    When I select shape Irregular
    Then when I set the measurement A to <a>, B to <b> and C to <c> the price should be <d> kr
      | a     | b   | c    | d                                                                                                                                                                               |
      | 8000  | 200 | 635  | 4*(90345446 EKBACKEN 10-45*2.8) + 4*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 1*(10345577 Joint Laminate)                                          |
      | 8001  | 700 | 400  | 4*(60345476 EKBACKEN 63.6-125*2.8) + 4*(40345463 EKBACKEN 45.1-63.5*2.8) + 0.01*(90345446 EKBACKEN 10-45*2.8) + 3*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate)  |

  # SET SHAPE, MEASUREMENTS AND WALL LOCATION AND CHECK PRICE

  Scenario Outline: F002 Wall selection for rectangular shape
    When I select shape Rectangular
    And I set the measurements A to 2000 and B to 635
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | walls                | price                                                                           |
      | three walls          | 2*(40345463 EKBACKEN 45.1-63.5*2.8)                                             |
      | two walls left       | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 0.64*(20350182 Same Color Edge Laminate)  |
      | two walls right      | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 0.64*(20350182 Same Color Edge Laminate)  |
      | one long wall        | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.27*(20350182 Same Color Edge Laminate)  |
      | one short wall left  | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.64*(20350182 Same Color Edge Laminate)  |
      | one short wall right | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.64*(20350182 Same Color Edge Laminate)  |
      | no walls             | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.27*(20350182 Same Color Edge Laminate)  |

  Scenario Outline: F002 Wall selection for L-shape
    When I select shape L-Shape
    And I set the measurements A to 2000, B to 3000, C to 451 and D to 635
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | walls            | price                                                                                                                                                 |
      | four walls       | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate)                                            |
      | three walls top  | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate) |
      | three walls left | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate) + 0.46*(20350182 Same Color Edge Laminate) |
      | two walls left   | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate) + 1.09*(20350182 Same Color Edge Laminate) |
      | two walls right  | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate) + 3.64*(20350182 Same Color Edge Laminate) |
      | one wall top     | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate) + 4.09*(20350182 Same Color Edge Laminate) |
      | one wall left    | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate) + 3.09*(20350182 Same Color Edge Laminate) |
      | no walls         | 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Joint Laminate) + 6.09*(20350182 Same Color Edge Laminate) |

  Scenario Outline: F002 Wall selection for U-shape
    When I select shape U-Shape
    And I set the measurements A to 2000, B to 3000, C to 4000, D to 635, E to 635 and F to 451
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | walls             | price                                                                                                                                                                                           |
      | five walls        | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate)                                             |
      | four walls left   | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 0.46*(20350182 Same Color Edge Laminate)  |
      | four walls right  | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate)  |
      | three walls       | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 1.09*(20350182 Same Color Edge Laminate)  |
      | three walls left  | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 4.46*(20350182 Same Color Edge Laminate)  |
      | three walls right | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 3.64*(20350182 Same Color Edge Laminate)  |
      | two walls left    | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 5.09*(20350182 Same Color Edge Laminate)  |
      | two walls right   | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 4.09*(20350182 Same Color Edge Laminate)  |
      | one wall          | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 8.09*(20350182 Same Color Edge Laminate)  |
      | no walls          | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.37*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Joint Laminate) + 10.09*(20350182 Same Color Edge Laminate) |

  Scenario Outline: F002 Wall selection for V-shape
    When I select shape V-Shape
    And I set the measurements A to 4000 and B to 3000
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | walls            | price                                                                                                                                                                                                                               |
      | four walls       | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 1.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate)                                            |
      | three walls top  | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 1.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate) |
      | three walls left | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 1.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate) |
      | two walls        | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 1.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 1.27*(20350182 Same Color Edge Laminate) |
      | one wall top     | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 1.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 4.27*(20350182 Same Color Edge Laminate) |
      | one wall left    | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 1.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 5.27*(20350182 Same Color Edge Laminate) |
      | no walls         | 1.51*(60345476 EKBACKEN 63.6-125*2.8) + 1.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.94*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 8.27*(20350182 Same Color Edge Laminate) |


  Scenario Outline: F002 Wall selection for C-shape
    When I select shape C-Shape
    And I set the measurements A to 4000 and B to 3000
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | walls            | price                                                                                                                                                                                                                                |
      | five walls       | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate)                                            |
      | four walls right | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate) |
      | four walls left  | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 0.64*(20350182 Same Color Edge Laminate) |
      | three walls      | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 1.27*(20350182 Same Color Edge Laminate) |
      | no walls         | 1.51*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.74*(40345463 EKBACKEN 45.1-63.5*2.8) + 2*(10345577 Single Cut Laminate) + 2*(10345577 Joint Laminate) + 9.41*(20350182 Same Color Edge Laminate) |

  Scenario Outline: F002 Wall selection for II-shape
    When I select shape II-Shape
    And I set the measurements A to 2000, B to 450, C to 3000 and D to 600
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | walls                     | price                                                                                                            |
      | six walls                 | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8)                                            |
      | four walls left           | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.05*(20350182 Same Color Edge Laminate) |
      | four walls right          | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 1.05*(20350182 Same Color Edge Laminate) |
      | three walls left          | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.05*(20350182 Same Color Edge Laminate) |
      | three walls right         | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 3.05*(20350182 Same Color Edge Laminate) |
      | three walls island top    | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.9*(20350182 Same Color Edge Laminate)  |
      | three walls island bottom | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 4.2*(20350182 Same Color Edge Laminate)  |
      | two long walls            | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 2.1*(20350182 Same Color Edge Laminate)  |
      | two short walls left      | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 6.05*(20350182 Same Color Edge Laminate) |
      | two short walls right     | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 6.05*(20350182 Same Color Edge Laminate) |
      | one wall island top       | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 4.1*(20350182 Same Color Edge Laminate)  |
      | one wall island bottom    | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 5.1*(20350182 Same Color Edge Laminate)  |
      | no walls                  | 2*(90345446 EKBACKEN 10-45*2.8) + 3*(40345463 EKBACKEN 45.1-63.5*2.8) + 7.1*(20350182 Same Color Edge Laminate)  |

  Scenario Outline: F002 Wall selection for Irregular shape
    When I select shape Irregular
    And I set the measurements A to 2000, B to 450 and C to 600
    Then when I set wall selection to <walls> the price should be <price>
    Examples:
      | walls                | price                                                                                                             |
      | three walls          | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Single Cut Laminate)                                            |
      | two walls top left   | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Single Cut Laminate) + 0.6*(20350182 Same Color Edge Laminate)  |
      | two walls top right  | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Single Cut Laminate) + 0.45*(20350182 Same Color Edge Laminate) |
      | one long wall        | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Single Cut Laminate) + 1.05*(20350182 Same Color Edge Laminate) |
      | one short wall left  | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Single Cut Laminate) + 2.61*(20350182 Same Color Edge Laminate) |
      | one short wall right | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Single Cut Laminate) + 2.46*(20350182 Same Color Edge Laminate) |
      | no walls             | 2*(40345463 EKBACKEN 45.1-63.5*2.8) + 1*(10345577 Single Cut Laminate) + 3.06*(20350182 Same Color Edge Laminate) |

  Scenario Outline: F002 Check the number of digits that are possible to insert for measurements (maximum: 5)
    When I select shape <shape>
    And I set the measurements A to <a> and B to <b>
    Then the measurement for shape <shape> should be <c>
    Examples:
      | shape       | a       | b      | c           |
      | rectangular | 12345   | 1234   | 12345,1234  |
      | rectangular | 123456  | 124    | 12345,124   |
      | v-shape     | 12345   | 12345  | 12345,12345 |
      | v-shape     | 123456  | 123456 | 12345,12345 |

  Scenario Outline: F002 Available overhangs and overhang sizes for rectangular
    When I select shape Rectangular
    Then when walls are set to <walls> the following overhang types <available overhangs> should be available
    Then when I set the overhang type to <overhang> the overhang sides should be <overhang sides>
    Examples:
      | walls                | available overhangs                                            | overhang                 | overhang sides                           |
      | three walls          | none                                                           | none                     | disabled                                 |
      | two walls left       | waterfall, waterfall with overhang, arc, rounded, half rounded | waterfall                | one side right                           |
      | two walls right      | waterfall, waterfall with overhang, arc, rounded, half rounded | waterfall with overhang  | one side left                            |
      | one long wall        | waterfall, waterfall with overhang, arc, rounded, half rounded | arc                      | one side left, one side right, two sides |
      | one short wall left  | waterfall, waterfall with overhang, arc, rounded, half rounded | rounded                  | one side right                           |
      | one short wall right | waterfall, waterfall with overhang, arc, rounded, half rounded | half rounded             | one side left                            |
      | no walls             | waterfall, waterfall with overhang, arc, rounded, half rounded | waterfall                | one side left, one side right, two sides |

  Scenario: F002 Rectangular overhang defaults for different wall locations
    When I select shape Rectangular
    And I select two walls left as walls and rounded as overhang
    Then one side right is selected as overhang side
    When I select two walls right as walls and rounded as overhang
    Then one side left is selected as overhang side
    When I select one long wall as walls and rounded as overhang
    Then one side left is selected as overhang side
    When I select one short wall left as walls and rounded as overhang
    Then one side right is selected as overhang side
    When I select one short wall right as walls and rounded as overhang
    Then one side left is selected as overhang side
    When I select no walls as walls and rounded as overhang
    Then one side left is selected as overhang side

  Scenario: F002 C-shape overhang defaults for different wall locations
    When I select shape C-shape
    And I select four walls right as walls and waterfall as overhang
    Then one side bottom is selected as overhang side
    When I select four walls left as walls and waterfall as overhang
    Then one side right is selected as overhang side
    When I select three walls as walls and waterfall as overhang
    Then one side right is selected as overhang side
    When I select no walls as walls and waterfall as overhang
    Then one side right is selected as overhang side

  Scenario Outline: F002 Overhang side size measurements for L-shape
    When I select shape L-shape
    And I select three walls top as walls and half rounded as overhang
    Then when I select one side bottom as overhang side and I set overhang side measurements to <z> it should display <error message> with <nrOferrors> errors found
    Examples:
      | z     | error message    | nrOferrors |
      | 230   | No Error Message | 0          |
      | 229   | Error Message    | 1          |
      | 0     | Error Message    | 1          |
      | 17000 | No Error Message | 0          |
      | 17001 | Error Message    | 2          |

  Scenario: F002 Overhang side size boundary value
    When I have selected shape rectangular with measurement 19000 and wall selection two walls left in the plan tab
    And when I set the overhang type to waterfall with overhang the overhang sides should be one side right
    Then when I select one side right as overhang side and I set overhang side measurements to 1001 it should display Error message with 2 errors found

  Scenario: F002 Include overhang edge in price calculation and list
    When I have selected shape rectangular with measurement 3876 and wall selection two walls left in the plan tab
    And when I set the overhang type to waterfall the overhang sides should be one side right
    Then the total price should be 4.79*(10345493 SÄLJAN 45.1-63.5*3.8) + 4.79*(00346271 Laminate edge 45.1-63.5) + 0.64*(20350182 Same Color Edge Laminate)
    And the waterfall length should be displayed for the overhang side one side right
    When I set one short wall left as wall location
    And when I set the overhang type to waterfall the overhang sides should be one side right
    Then the total price should be 4.79*(10345493 SÄLJAN 45.1-63.5*3.8) + 4.79*(00346271 Laminate edge 45.1-63.5) + 5.42*(20350182 Same Color Edge Laminate)
    And when I set the overhang type to waterfall with overhang the overhang sides should be one side right
    Then the total price should be 5.26*(10345493 SÄLJAN 45.1-63.5*3.8) + 5.26*(00346271 Laminate edge 45.1-63.5) + 5.9*(20350182 Same Color Edge Laminate) + 1*(10345577 Joint Laminate)
    When I set two walls left as wall location
    Then the total price should be 5.26*(10345493 SÄLJAN 45.1-63.5*3.8) + 5.26*(00346271 Laminate edge 45.1-63.5) + 0.64*(20350182 Same Color Edge Laminate) + 1*(10345577 Joint Laminate)
    And when I set the overhang type to arc the overhang sides should be one side right
    Then the total price should be 4.38*(10345493 SÄLJAN 45.1-63.5*3.8) + 4.38*(00346271 Laminate edge 45.1-63.5) + 0.68*(20350182 Same Color Edge Laminate) + 2*(10345577 Joint Laminate)
    When I set one short wall left as wall location
    Then the total price should be 4.38*(10345493 SÄLJAN 45.1-63.5*3.8) + 4.38*(00346271 Laminate edge 45.1-63.5) + 5.06*(20350182 Same Color Edge Laminate) + 2*(10345577 Joint Laminate)
    When I have scrolled up on the page
    Then the overhang image should be displayed

    #Addera print o se att bilderna o delarna kommer med, http://master.unstable.cwcalc.com/addon-app/cwcalc/latest/#/se/en/?uitest=displayprintformat
    #When I enter the print page
    #Then the total price in the print page should be 4.38*(10345493 SÄLJAN 45.1-63.5*3.8)

  Scenario: F002 Double sided worktop for laminate and wood
    #Laminate double sided when overhang is added
    When I have selected shape L-shape with measurement 3049 and wall selection two walls left in the plan tab
    Then the total price should be 5.42*(10345493 SÄLJAN 45.1-63.5*3.8) + 1.27*(20350182 Same Color Edge Laminate) + 1*(10345577 Joint Laminate)
    When I select arc as overhang type
    Then the total price should be 5.92*(10345493 SÄLJAN 45.1-63.5*3.8) + 5.92*(00346271 Laminate edge 45.1-63.5) + 1.32*(20350182 Same Color Edge Laminate) + 2*(10345577 Joint Laminate)
   #Solid wood not double sided
    When I navigate to the Style page
    And I select solid wood material
    And I select oak expression
    Then the total price should be 5.92*(20345567 SKOGARP 45.1-63.5*4.0) + 1.32*(00350183 Same Color Edge Solid Wood) + 2*(70345579 Joint Solid Wood)
    #Acrylic wood not double sided
    When I navigate to the Style page
    And I select acrylic material
    And I select white acrylic expression
    Then the total price should be 5.92*(50345434 FORNBY 45.1-63.5*3.8) + 1.32*(50350312 Same Color Edge Acrylic) + 2*(90345578 Joint Acrylic)
    #Quartz not double sided
    When I navigate to the Style page
    And I select quartz material
    And I select white marble effect quartz expression
    When I navigate to the Plan page
    And I select emerald as overhang type
    Then the total price should be 5.92*(90345521 OXSTEN 45.1-63.5*3.8)+ 1.27*(30350313 Same Color Edge Quartz) + 3*(50345580 Joint Quartz)
   #Wood double sided
    When I navigate to the Style page
    And I select wood material
    And I select Barkaboda walnut expression
    When I navigate to the Plan page
    And I select arc as overhang type
    Then the total price should be 5.92*(90347558 BARKABODA 45.1-63.5*3.8) + 5.92*(80351174 Wood edge 45.1-63.5) + 1.32*(80350184 Same Color Edge Wood) + 1*(10347562 Joint Wood)
    #Change sizes of double sided worktop
    When I navigate to the Plan page
    And I select shape L-shape
    And I set the measurements A to 2999, B to 3591, C to 420 and D to 640
    And I select waterfall as overhang type
    Then the total price should be 3.28*(10347557 BARKABODA 10-45*3.8) + 3.28*(00351173 Wood edge 10-45) + 3.6*(70347559 BARKABODA 63.6-125*3.8) + 3.6*(10351177 Wood edge 63.6-125) + 1.06*(80350184 Same Color Edge Wood) + 1*(10347562 Joint Wood)
