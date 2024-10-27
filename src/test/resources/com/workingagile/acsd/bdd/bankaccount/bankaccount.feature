Feature: Bank Account

  The Bank account feature allows clients to keep track of their current financial balance.
  Clients can deposit, withdraw and query their account balance.

  Scenario: Deposit an amount into the bank account

    Given a bank account with the initial balance of 1000
    When a client deposits 200
    Then the account should have a balance of 1200


  Rule: Bank accounts cannot have negative balances.

    Scenario: Client successfully withdraws an amount with sufficient balance

      Given a bank account with the initial balance of 1000
      When a client tries to withdraw 999
      Then the account should have a balance of 1

    Scenario: Client tries to overdraw from the bank account

    Overdrawing is not allowed, so the transaction will be canceled

      Given a bank account with the initial balance of 1000
      When a client tries to withdraw 1100
      Then the transaction should be canceled
      And the account should have a balance of 1000





    Should transfer a value to other account