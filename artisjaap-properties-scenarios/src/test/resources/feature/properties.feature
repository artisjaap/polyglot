Feature: managing features
  Scenario: create a new property
    Given a property prop with value val
    Then when I request prop it has value val
    When the property prop is updated to value2
    Then when I request prop it has value value2