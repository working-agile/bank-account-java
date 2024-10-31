Feature: Email alerts

  # @DisplayName("Overdraft should trigger an email")

  Scenario: Overdrafting trigger email alerts

    Overdrafting can happen in multiple ways. When it happens,
    an email alert is sent to the bank administration.

    When a client overdrafts his account
    Then an email alert is sent to the bank