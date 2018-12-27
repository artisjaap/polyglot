export interface LanguagePairResponse {
  id: string;
  userId: string;
  languageFrom: string;
  languageTo: string;
  turnsDone: number;
  turnsDoneReverse: number;
  lastTurn: string;
  lastTurnReverse: string;
}
