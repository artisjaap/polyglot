Feature: General document generation
  Scenario: Loading a simple template
    Given a template MANDAAT_HEADER_FOOTER_NL.DOCX is uploaded with code MANDAAT_HEADER_FOOTER in language nld
    Then the template with code MANDAAT_HEADER_FOOTER is available

  Scenario: Activating a template
    Given a template MANDAAT_HEADER_FOOTER_NL.DOCX is uploaded with code MANDAAT_HEADER_FOOTER in language nld
    When template with code MANDAAT_HEADER_FOOTER is activated in language nld
    Then template with code MANDAAT_HEADER_FOOTER is active

    @Only
  Scenario: Stamping two templates on top of each other
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld
    When a combined template with code MANDAAT_MET_HEADER_FOOTER in language nld consists of templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    Then the combined template with code MANDAAT_MET_HEADER_FOOTER is available
    When the combined template with code MANDAAT_MET_HEADER_FOOTER is activated in language nld
    Then the combined template with code MANDAAT_MET_HEADER_FOOTER is active

  Scenario: A template can be used to generate a document
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    Then the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset

  Scenario: Verschillende templates kunnen gebruikt worden in een brief en deze brief kan dan gegenereerd worden
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    And the document with code MANDAAT_BRIEF has been set active in language nld
    Then the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset

  Scenario: Gecombineerde templates en andere templates kunnen gebruikt worden in een brief en deze brief kan dan gegenereerd worden
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld
    And a combined template with code MANDAAT_MET_HEADER_FOOTER in language nld consists of templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    And the combined template with code MANDAAT_MET_HEADER_FOOTER is activated in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_MET_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    Then the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset

  Scenario: Gegenereerde brieven kunnen opgeslagen worden in de database
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    Then the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset

  Scenario: Gegenereerde brieven kunnen opgeslagen worden in een absoluut path
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    Then the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset

  Scenario: Gegenereerde brieven kunnen opgeslagen worden in een relatief path
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    Then the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved on relative path /docs/

  Scenario: De naam van de gegenereerde brief is standaard de code van de brief, de taal een en timestamp
    Given today is 26-06-2018 09:00
    And an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    And the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB
    Then the document with code MANDAAT_BRIEF in language nld available with name MANDAAT_BRIEF_NLD_20180626090000


  Scenario: De naam van de gegenereerde brief kan samengesteld worden uit de beschikbare datavelden
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    And the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB with filename Lid.naam, Lid.voornaam
    Then the document with code MANDAAT_BRIEF in language nld available with name NAAM_VOORNAAM

  Scenario: De velden van de brief zijn beschikbaar als metadata
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    And the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB with filename BriefMeta.isoCode, BriefMeta.isoCode, BriefMeta.BriefCode
    Then the document with code MANDAAT_BRIEF in language nld available with name NLD_NLD_MANDAAT_BRIEF

  Scenario: Bepaalde dataset kunnen geblacklist worden in het kader van GDPR
    Given an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld
    And a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER
    And the document with code MANDAAT_BRIEF has been set active in language nld
    And the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB and datasets Lid are blacklisted
    Then the document with code MANDAAT_BRIEF in language nld and ignored datasets are: Lid

  Scenario: Dynamische images kunnen vervangen worden door echte images
    Given an active template DUMMY_QR.DOCX with code DUMMY_QR in language nld
    And a document BRIEF_MET_QR in language nld exists of templates DUMMY_QR
    And the document with code BRIEF_MET_QR has been set active in language nld
    Then the document with code BRIEF_MET_QR in language nld can be generated using a default dataset, are saved in absolute path c:/temp/docs/ and contain the following images
      | bookmark naam | image  |
      | qr            | qr.png |


  Scenario: Een QR code kan eenvoudig toevoegd worden op een document
    Given an active template DUMMY_QR.DOCX with code DUMMY_QR in language nld
    And an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld
    And a combined template with code TEMPLATE_MET_QR in language nld consists of templates DUMMY_QR, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    And the combined template with code TEMPLATE_MET_QR is activated in language nld
    And a document BRIEF_MET_QR in language nld exists of templates TEMPLATE_MET_QR
    And the document with code BRIEF_MET_QR has been set active in language nld
    Then the document with code BRIEF_MET_QR in language nld can be generated using a default dataset, is saved on absolute path c:/temp/docs/ and has a QR code with following data {test:data, wa_rommel:rommel}


  Scenario: Een QR code kan eenvoudig toevoegd worden op een document een qr code met veel data
    Given an active template DUMMY_QR.DOCX with code DUMMY_QR in language nld
    And an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld
    And a combined template with code TEMPLATE_MET_QR in language nld consists of templates DUMMY_QR, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    And the combined template with code TEMPLATE_MET_QR is activated in language nld
    And a document BRIEF_MET_QR in language nld exists of templates TEMPLATE_MET_QR
    And the document with code BRIEF_MET_QR has been set active in language nld
    Then the document with code BRIEF_MET_QR in language nld can be generated using a default dataset, is saved on absolute path c:/temp/docs/ and has a QR code with following data er staat heel wat data in deze qr code, de vraag is of die goed gelezen wordt als er zoveel data in wordt opgeslagen? Wordt de code niet te klein om te lezen en is dit moeilijk te parsen?

  #voor actief moet er een preview gemaakt worden om te valideren of brief ok is tov een referentie dataset voor die brief
