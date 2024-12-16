Feature: Bank Account

  The Bank account feature allows clients to keep track of their current financial balance.
  Clients can deposit, withdraw and query their account balance.

  Scenario: Deposit an amount into the bank account

    Given a bank account with the initial balance of 1000
    When a client deposits 200
    Then the account should have a balance of 1200
