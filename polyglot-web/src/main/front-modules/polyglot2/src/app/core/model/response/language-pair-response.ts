export interface LanguagePairResponse {
  userId:string;
  languageFrom:string;
  languageTo:string;
  turnsDone:number;
  turnsDoneReverse:number;
  lastTurn:string; //LocalDateTime
  lastTurnReverse:string; //LocalDateTime
}
