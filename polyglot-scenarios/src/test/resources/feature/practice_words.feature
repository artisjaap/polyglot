Feature: Practicing words
  Scenario: A list of words is available for practice and users starts to practice
    Given a user named Tom
    And Tom creates language pair NL-FR
    And Tom uploads a list translations-numbers.csv for language pair NL-FR
    When Tom practiced 10 words for language pair NL-FR