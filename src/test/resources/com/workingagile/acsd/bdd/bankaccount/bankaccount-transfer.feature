Feature: Transfer between bank accounts

# @DisplayName("Should transfer a value to other account")
  Scenario: A client transfers an amount to another account

    Given Nathan has a bank account with 1000
    And Sabrina has a bank account with 500
    When Nathan transfers 150 to Sabrina
    Then Sabrina should have 650
    And Nathan should have 850

# @DisplayName("Transfer amount higher than the balance, should not transfer")
  Scenario: A client cannot transfers more money than is in the account

    Given Nathan has a bank account with 1000
    And Sabrina has a bank account with 500
    When Nathan tries to transfers 1001 to Sabrina
    Then the transfer should be cancelled
    And both should have the same amount in their accounts

# @DisplayName("Transfer fee is charged when transferring money")
  Scenario: Transfer fee is charged when transferring money between accounts

    The bank can charge a transfer fee.

    Given the bank is charging a transfer fee of 10
    And Nathan has a bank account with 1000
    And Sabrina has a bank account with 500
    When Nathan transfers 150 to Sabrina
    Then Sabrina should have 650
    And Nathan should have 840




