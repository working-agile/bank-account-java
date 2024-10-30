Feature: Transfer between bank accounts


  Scenario: A client transfers an amount to another account

    Given Nathan with a bank account with balance of 1000
    And Sabrina with a bank account with balance of 500
    When Nathan transfers 150 from his account to Sabrina's account
    Then Sabrina should now have 650
    And Nathan should have left 850 in his account

# @DisplayName("Transfer amount higher than the balance, should not transfer")
# @DisplayName("Transfer fee is charged when transferring money")