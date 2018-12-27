import {TranslationJournalResponse} from './translation-journal-response';

export interface LanguagePracticeJournalResponse {

  userId: string;
  languagePairId: string;
  yearMonth: string;
  translationJournalList: TranslationJournalResponse[];
}
