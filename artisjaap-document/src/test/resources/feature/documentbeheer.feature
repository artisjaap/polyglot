#language: nl
Functionaliteit: Handleiding voor brieftemplates
  Scenario: Een standaard template opladen
    Als een template MANDAAT_HEADER_FOOTER_NL.DOCX wordt opgeladen als code MANDAAT_HEADER_FOOTER in nld
    Dan is de template met code MANDAAT_HEADER_FOOTER beschikbaar

  Scenario: Een template kan actief gezet worden
    Gegeven een template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    Als de template met code MANDAAT_HEADER_FOOTER actief wordt gezet in taal nld
    Dan staat de template met code MANDAAT_HEADER_FOOTER actief

  Scenario: Twee of meerdere templates kunnen over elkaar gestamped worden en actief worden gezet
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een actieve template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX met code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in nld
    Als een gecombineerde template met code MANDAAT_MET_HEADER_FOOTER in taal nld wordt gemaakt uit de templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    Dan is de gecombineerde template met code MANDAAT_MET_HEADER_FOOTER beschikbaar
    Als de gecombineerde template met code MANDAAT_MET_HEADER_FOOTER actief wordt gezet in taal nld
    Dan staat de gecombineerde template met code MANDAAT_MET_HEADER_FOOTER actief

  Scenario: Een template kan gebruikt worden in een brief en deze brief kan dan gegenereerd worden
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    Dan kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets

  Scenario: Verschillende templates kunnen gebruikt worden in een brief en deze brief kan dan gegenereerd worden
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een actieve template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX met code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    Dan kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets

  Scenario: Gecombineerde templates en andere templates kunnen gebruikt worden in een brief en deze brief kan dan gegenereerd worden
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een actieve template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX met code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in nld
    En een gecombineerde template met code MANDAAT_MET_HEADER_FOOTER in taal nld wordt gemaakt uit de templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    En de gecombineerde template met code MANDAAT_MET_HEADER_FOOTER actief wordt gezet in taal nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_MET_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    Dan kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets

  Scenario: Gegenereerde brieven kunnen opgeslagen worden in de database
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    Dan kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB

  Scenario: Gegenereerde brieven kunnen opgeslagen worden in een absoluut path
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    Dan kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets en worden opgeslagen het absolute path c:/temp/docs/

  Scenario: Gegenereerde brieven kunnen opgeslagen worden in een relatief path
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    Dan kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets en worden opgeslagen op het relatieve path /docs/

  Scenario: De naam van de gegenereerde brief is standaard de code van de brief, de taal een en timestamp
    Gegeven vandaag is 26/06/2018 09:00
    En een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    En kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB
    Dan is het bestand MANDAAT_BRIEF in taal nld beschikbaar onder de naam MANDAAT_BRIEF_NLD_20180626090000


  Scenario: De naam van de gegenereerde brief kan samengesteld worden uit de beschikbare datavelden
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    En kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB met bestandsnaam Lid.naam, Lid.voornaam
    Dan is het bestand MANDAAT_BRIEF in taal nld beschikbaar onder de naam NAAM_VOORNAAM

  Scenario: De velden van de brief zijn beschikbaar als metadata
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    En kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB met bestandsnaam BriefMeta.isoCode, BriefMeta.isoCode, BriefMeta.BriefCode
    Dan is het bestand MANDAAT_BRIEF in taal nld beschikbaar onder de naam NLD_NLD_MANDAAT_BRIEF

  Scenario: Bepaalde dataset kunnen geblacklist worden in het kader van GDPR
    Gegeven een actieve template MANDAAT_HEADER_FOOTER_NL.DOCX met code MANDAAT_HEADER_FOOTER in nld
    En een brief MANDAAT_BRIEF in nld die bestaat uit de templates MANDAAT_HEADER_FOOTER
    En de brief met code MANDAAT_BRIEF actief wordt gezet in taal nld
    En kan de brief met code MANDAAT_BRIEF in taal nld gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB en volgende datasets worden geblacklist: Lid
    Dan is het bestand MANDAAT_BRIEF in taal nld beschikbaar en de metadata van de volgende datasets is niet opgeslagen: Lid

  Scenario: Dynamische images kunnen vervangen worden door echte images
    Gegeven een actieve template DUMMY_QR.DOCX met code DUMMY_QR in nld
    En een brief BRIEF_MET_QR in nld die bestaat uit de templates DUMMY_QR
    En de brief met code BRIEF_MET_QR actief wordt gezet in taal nld
    Dan kan de brief met code BRIEF_MET_QR in taal nld gegenereerd worden met een default set van datasets, wordt opgeslagen op het absolute path c:/temp/docs/ en bevat volgende images
      | bookmark naam | image  |
      | qr            | qr.png |


  Scenario: Een QR code kan eenvoudig toevoegd worden op een document
    Gegeven een actieve template DUMMY_QR.DOCX met code DUMMY_QR in nld
    En een actieve template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX met code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in nld
    En een gecombineerde template met code TEMPLATE_MET_QR in taal nld wordt gemaakt uit de templates DUMMY_QR, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    En de gecombineerde template met code TEMPLATE_MET_QR actief wordt gezet in taal nld
    En een brief BRIEF_MET_QR in nld die bestaat uit de templates TEMPLATE_MET_QR
    En de brief met code BRIEF_MET_QR actief wordt gezet in taal nld
    Dan kan de brief met code BRIEF_MET_QR in taal nld gegenereerd worden met een default set van datasets, wordt opgeslagen op het absolute path c:/temp/docs/ en bevat in de QR de volgende gegevens {test:data, wa_rommel:rommel}


  Scenario: Een QR code kan eenvoudig toevoegd worden op een document een qr code met veel data
    Gegeven een actieve template DUMMY_QR.DOCX met code DUMMY_QR in nld
    En een actieve template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX met code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in nld
    En een gecombineerde template met code TEMPLATE_MET_QR in taal nld wordt gemaakt uit de templates DUMMY_QR, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    En de gecombineerde template met code TEMPLATE_MET_QR actief wordt gezet in taal nld
    En een brief BRIEF_MET_QR in nld die bestaat uit de templates TEMPLATE_MET_QR
    En de brief met code BRIEF_MET_QR actief wordt gezet in taal nld
    Dan kan de brief met code BRIEF_MET_QR in taal nld gegenereerd worden met een default set van datasets, wordt opgeslagen op het absolute path c:/temp/docs/ en bevat in de QR de volgende gegevens er staat heel wat data in deze qr code, de vraag is of die goed gelezen wordt als er zoveel data in wordt opgeslagen? Wordt de code niet te klein om te lezen en is dit moeilijk te parsen?

  #voor actief moet er een preview gemaakt worden om te valideren of brief ok is tov een referentie dataset voor die brief