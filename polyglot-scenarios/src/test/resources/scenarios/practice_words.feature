Feature: Practicing words
  @Only
  Scenario: A list of words is available for practice and users starts to practice
    Given a user named Tom
    And Tom creates language pair NL-FR
    And Tom uploads a list translations-numbers.csv for language pair NL-FR
    When Tom practiced 10 words for language pair NL-FR

  Scenario: create a new property
    Given a property prop with value val
    Then when I request prop it has value val
    When the property prop is updated to value2
    Then when I request prop it has value value2

  Scenario: practice some words and generate a document with only the errors
    Given a user named tom
    And tom creates language pair Nederlands-Frans
    And tom uploads a list translations-numbers.csv for language pair Nederlands-Frans
    And a document PRACTICE_MISTAKES made in nld of template word-practice-mistakes.docx
    And tom practiced 10 words for language pair Nederlands-Frans and made 2 mistakes
    And tom generates the document PRACTICE_MISTAKES for language pair Nederlands-Frans


  Scenario: if multiple times the same error is made, it is shown only once on the report
    Given a user named tom
    And tom creates language pair NL-FR
    And tom uploads a list translations-numbers-1-5.csv for language pair NL-FR
    And a document PRACTICE_MISTAKES made in nld of template word-practice-mistakes.docx
    And tom practiced 50 words for language pair NL-FR and made 30 mistakes
    And tom generates the document PRACTICE_MISTAKES for language pair NL-FR
