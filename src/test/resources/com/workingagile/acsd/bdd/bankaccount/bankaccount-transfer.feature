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



# --------------------------------------------------------------------------
# Same scenarios, but now using a scenario template
# --------------------------------------------------------------------------

  Scenario Template: Transfer between bank accounts

    For transfers between accounts to success there has to be sufficient funds in the
    senders account.

    Given a transfer fee of <fee>
    And Nathan has a bank account with <balance-nathan>
    And Sabrina has a bank account with <balance-sabrina>
    When Nathan tries to transfers <amount> to Sabrina
    Then the transfer is "<status>"
    And Nathan's account should have <resulting-balance-nathan>
    And Sabrina's account should have <resulting-balance-sabrina>

    Examples: Successful transfers
      | balance-nathan | balance-sabrina | fee | amount | status     | resulting-balance-nathan | resulting-balance-sabrina |
      | 1000           | 50              | 0   | 100    | successful | 900                      | 150                       |
      | 1000           | 60              | 0   | 1000   | successful | 0                        | 1060                      |
      | 1000           | 0               | 10  | 400    | successful | 590                      | 400                       |

    Examples: Cancelled transfers
      | balance-nathan | balance-sabrina | fee | amount | status    | resulting-balance-nathan | resulting-balance-sabrina |
      | 1000           | 20              | 0   | 1001   | cancelled | 1000                     | 20                        |
      | 1000           | 0               | 10  | 1000   | cancelled | 1000                     | 0                         |






