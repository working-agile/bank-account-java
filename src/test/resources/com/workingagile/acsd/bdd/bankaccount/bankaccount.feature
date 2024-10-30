Feature: Bank Account

  The Bank account feature allows clients to keep track of their current financial balance.
  Clients can deposit, withdraw and query their account balance.

  Scenario: Deposit an amount into the bank account

    Given a bank account with the initial balance of 1000
    When a client deposits 200
    Then the account should have a balance of 1200

  Scenario: Withdrawing from a bank account

    Given a bank account with the initial balance of 1000
    When a client withdraws 200
    Then the account should have a balance of 800

  Scenario: Overdraft from a bank account is not allowed

    Given a bank account with the initial balance of 1000
    When a client withdraws 1100
    Then the transaction should be cancelled
    And the account should have a balance of 1000


