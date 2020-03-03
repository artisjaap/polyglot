$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("classpath:scenarios/documentbeheer.feature");
formatter.feature({
  "name": "General document generation",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Loading a simple template",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "a template MANDAAT_HEADER_FOOTER_NL.DOCX is uploaded with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenTemplateWordtOpgeladen(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the template with code MANDAAT_HEADER_FOOTER is available",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.isDeBriefMetCodeBeschikbaar(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Activating a template",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "a template MANDAAT_HEADER_FOOTER_NL.DOCX is uploaded with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenTemplateWordtOpgeladen(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "template with code MANDAAT_HEADER_FOOTER is activated in language nld",
  "keyword": "When "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deTemplateMetCodeActiefWordtGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "template with code MANDAAT_HEADER_FOOTER is active",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.staatDeTemplateMetCodeActief(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Stamping two templates on top of each other",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Only"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a combined template with code MANDAAT_MET_HEADER_FOOTER in language nld consists of templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT",
  "keyword": "When "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenGecombineerdeTemplateMetCodeWordtGemaaktUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the combined template with code MANDAAT_MET_HEADER_FOOTER is available",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.isDeGecombineerdeTemplateMetCodeBeschikbaar(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the combined template with code MANDAAT_MET_HEADER_FOOTER is activated in language nld",
  "keyword": "When "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deGecombineerdeTemplateMetCodeActiefWordtGezetInTaal(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the combined template with code MANDAAT_MET_HEADER_FOOTER is active",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.staatDeGecombineerdeTemplateMetCodeActief(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "A template can be used to generate a document",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeMANDAAT_BRIEFGegenereerdWordenMetEenDefaultSetVanDatasets(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verschillende templates kunnen gebruikt worden in een brief en deze brief kan dan gegenereerd worden",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeMANDAAT_BRIEFGegenereerdWordenMetEenDefaultSetVanDatasets(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Gecombineerde templates en andere templates kunnen gebruikt worden in een brief en deze brief kan dan gegenereerd worden",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a combined template with code MANDAAT_MET_HEADER_FOOTER in language nld consists of templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenGecombineerdeTemplateMetCodeWordtGemaaktUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the combined template with code MANDAAT_MET_HEADER_FOOTER is activated in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deGecombineerdeTemplateMetCodeActiefWordtGezetInTaal(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_MET_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeMANDAAT_BRIEFGegenereerdWordenMetEenDefaultSetVanDatasets(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Gegenereerde brieven kunnen opgeslagen worden in de database",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeMANDAAT_BRIEFGegenereerdWordenMetEenDefaultSetVanDatasets(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Gegenereerde brieven kunnen opgeslagen worden in een absoluut path",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeMANDAAT_BRIEFGegenereerdWordenMetEenDefaultSetVanDatasets(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Gegenereerde brieven kunnen opgeslagen worden in een relatief path",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved on relative path /docs/",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenOpHetRelatievePath(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "De naam van de gegenereerde brief is standaard de code van de brief, de taal een en timestamp",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "today is 26-06-2018 09:00",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.vandaagIs(java.time.LocalDateTime)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDB(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld available with name MANDAAT_BRIEF_NLD_20180626090000",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.isHetBestandBeschikbaarOnderDeNaam(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "De naam van de gegenereerde brief kan samengesteld worden uit de beschikbare datavelden",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB with filename Lid.naam, Lid.voornaam",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDBMetBestandsnaam(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld available with name NAAM_VOORNAAM",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.isHetBestandBeschikbaarOnderDeNaam(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "De velden van de brief zijn beschikbaar als metadata",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB with filename BriefMeta.isoCode, BriefMeta.isoCode, BriefMeta.BriefCode",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDBMetBestandsnaam(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld available with name NLD_NLD_MANDAAT_BRIEF",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.isHetBestandBeschikbaarOnderDeNaam(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Bepaalde dataset kunnen geblacklist worden in het kader van GDPR",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template MANDAAT_HEADER_FOOTER_NL.DOCX with code MANDAAT_HEADER_FOOTER in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document MANDAAT_BRIEF in language nld exists of templates MANDAAT_HEADER_FOOTER",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld can be generated using a default dataset and are saved in MongoDB and datasets Lid are blacklisted",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDBMetBlacklist(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code MANDAAT_BRIEF in language nld and ignored datasets are: Lid",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.isHetBestandMANDAAT_BRIEFInTaalNldBeschikbaarEnDeMetadataVanDeVolgendeDatasetsIsNietOpgeslagenLid(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Dynamische images kunnen vervangen worden door echte images",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template DUMMY_QR.DOCX with code DUMMY_QR in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document BRIEF_MET_QR in language nld exists of templates DUMMY_QR",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code BRIEF_MET_QR has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code BRIEF_MET_QR in language nld can be generated using a default dataset, are saved in absolute path c:/temp/docs/ and contain the following images",
  "rows": [
    {},
    {}
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.kanDeBriefMetCodeInTaaldGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenHetAbsolutePathMetImages(java.lang.String,java.lang.String,java.lang.String,java.util.List\u003cbe.artisjaap.document.cucumber.GherkinDocumentImage\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Een QR code kan eenvoudig toevoegd worden op een document",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template DUMMY_QR.DOCX with code DUMMY_QR in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a combined template with code TEMPLATE_MET_QR in language nld consists of templates DUMMY_QR, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenGecombineerdeTemplateMetCodeWordtGemaaktUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the combined template with code TEMPLATE_MET_QR is activated in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deGecombineerdeTemplateMetCodeActiefWordtGezetInTaal(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document BRIEF_MET_QR in language nld exists of templates TEMPLATE_MET_QR",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code BRIEF_MET_QR has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code BRIEF_MET_QR in language nld can be generated using a default dataset, is saved on absolute path c:/temp/docs/ and has a QR code with following data {test:data, wa_rommel:rommel}",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.briefMetCodeInTaalMetDefaultDatasetsEnQrCodeData(java.lang.String,java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Een QR code kan eenvoudig toevoegd worden op een document een qr code met veel data",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an active template DUMMY_QR.DOCX with code DUMMY_QR in language nld",
  "keyword": "Given "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an active template TEMPLATE_MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT_NL.DOCX with code MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefMetCodeInTaalDieActiefIs(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a combined template with code TEMPLATE_MET_QR in language nld consists of templates DUMMY_QR, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenGecombineerdeTemplateMetCodeWordtGemaaktUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the combined template with code TEMPLATE_MET_QR is activated in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deGecombineerdeTemplateMetCodeActiefWordtGezetInTaal(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a document BRIEF_MET_QR in language nld exists of templates TEMPLATE_MET_QR",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.eenBriefInNldDieBestaatUitDeTemplates(java.lang.String,java.lang.String,java.util.List\u003cjava.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code BRIEF_MET_QR has been set active in language nld",
  "keyword": "And "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerStepsDefinition.deBriefMANDAAT_BRIEFWordtActiefGezet(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the document with code BRIEF_MET_QR in language nld can be generated using a default dataset, is saved on absolute path c:/temp/docs/ and has a QR code with following data er staat heel wat data in deze qr code, de vraag is of die goed gelezen wordt als er zoveel data in wordt opgeslagen? Wordt de code niet te klein om te lezen en is dit moeilijk te parsen?",
  "keyword": "Then "
});
formatter.match({
  "location": "be.artisjaap.document.cucumber.DocumentbeheerThenStepsDefinition.briefMetCodeInTaalMetDefaultDatasetsEnQrCodeData(java.lang.String,java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
});