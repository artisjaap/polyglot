Feature: core polyglot features
  Scenario: Creating a user
    Given a user named Tibo

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
    When Tom starts to practice in NL-FR normal order, 100 exercises, adding a new word every 20 turns