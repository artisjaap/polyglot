Feature: managing features
  Scenario: test i18n
    Given A translation with key message has value boodschap in NL
    And A translation with key message has value message in EN
    Then  The translation of key message in NL has value boodschap
    And  The translation of key message in EN has value message