export interface TranslationJournalResponse {
  timestamp:string; //LocalDateTime
  translationId:string;
  question:string;
  givenAnswer:string;
  expectedAnswer:string;
  correct:boolean;
}
