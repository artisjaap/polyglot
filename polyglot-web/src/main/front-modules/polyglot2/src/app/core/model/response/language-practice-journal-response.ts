import {TranslationJournalResponse} from "./translation-journal-response";

export interface LanguagePracticeJournalResponse {
  userId:string;
  languagePairId:string;
  from:string;
  until:string;
  translationJournalList:TranslationJournalResponse[];
}
