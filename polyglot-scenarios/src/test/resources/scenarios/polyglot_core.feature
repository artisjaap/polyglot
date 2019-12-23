Feature: core polyglot features

  Scenario: Creating a user
    Given a user named Tibo
  And A backup service
    Given a property prop with value val
    Then when I request prop it has value val
    When the property prop is updated to value2
    Then when I request prop it has value value2

  Scenario: Een vertalingen definieren
    Given a user named Tibo
      And Tibo creates language pair NL-FR
      And Tibo adds the following translations on language pair NL-FR
      | Language A      | Language B    |
      | Ziehier         | Voici         |
      | Ziedaar         | Voila         |


  Scenario: Woordtrainer
    Given a user Tom with default dataset
    When Tom starts practicing his words with following settings
    | number of exercises                   | 100   |
    | number of words to start with         | 5     |
    | introduce new word every X exercises  | 20    |
    | flag as know when winning streak is   | 5     |
    | question lanquage                     | NL    |
    | answer language                       | FR    |


  Scenario: Manual practicing words by flipping cards
    Given a user Tom with default dataset
    When Tom starts to practice in NL-FR NORMAL order, 100 exercises, adding a new word every 20 turns


  Scenario: Autosave lesson from practiced words
    Given a user Tom with default dataset
    When Tom starts to practice in NL-FR NORMAL order, 100 exercises, adding a new word every 20 turns
    And Tom creates lesson with name 'TEST 1' for NL-FR aumatically with 5 words from practice list

  Scenario: do the test after creation a lesson
    Given a user Tom with default dataset
    When Tom starts to practice in NL-FR NORMAL order, 100 exercises, adding a new word every 20 turns
    And Tom creates lesson with name 'TEST 1' for NL-FR aumatically with 5 words from practice list
    And Tom does test with name 'TEST 1' without making any mistake

  Scenario: upload a list of words
    Given a user named Tom
    And Tom creates language pair NL-FR
    And Tom uploads a list translations-numbers.csv for language pair NL-FR
