import {PracticeWordResponse} from './practice-word-response';

export interface TranslationsForUserResponse {
  userId: string;
  languagePairId: string;
  translations: PracticeWordResponse[];
}
