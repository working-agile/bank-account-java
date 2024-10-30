Feature: Transfer between bank accounts


  Scenario: A client transfers an amount to another account

    Given Nathan has a bank account with 1000
    And Sabrina has a bank account with 500
    When Nathan transfers 150 to Sabrina
    Then Sabrina should have 650
    And Nathan should have 850


# @DisplayName("Transfer amount higher than the balance, should not transfer")
# @DisplayName("Transfer fee is charged when transferring money")