Feature: create document for training
  Scenario: create a new pdf
    Given a user named stijn
    Given an active template word-practice-answers-template.docx with code WORD_PRACTICE_ANSWERS in language nld
    And an active template word-practice-template.docx with code WORD_PRACTICE in language nld
    And a document TEST in language nld exists of templates WORD_PRACTICE_ANSWERS, WORD_PRACTICE
    And the document with code TEST has been set active in language nld
    Then the document with code TEST in language nld can be generated with default polyglot datasets
    And stijn creates language pair Nederlands-Frans
    And the lesson QE6b-planet-magasin.csv is loaded for language pair Nederlands-Frans
    Then a document TEST can be generated for language pair Nederlands-Frans
