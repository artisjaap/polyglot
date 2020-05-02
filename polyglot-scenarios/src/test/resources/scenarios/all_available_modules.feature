Feature: check if all modules are available in feature files

  Scenario: Check if all modules are available
    Given A backup service
    And A document service
    And A i18n service
    And A mail service
    And A properties service
    And A schedule service
