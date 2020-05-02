Feature: Loading verbs

  Scenario: upload a file containing verbs
    Given a user named Tom
    And Tom creates language pair NL-FR
    And Tom uploads a list verb_etre.csv for language pair NL-FR
