import {WordStatsResponse} from './word-stats-response';

export class PracticeWordResponse {

  constructor(readonly isReversed: boolean,
              readonly translationId: string,
              readonly question: string,
              readonly questionLanguage: string,
              readonly answer: string[],
              readonly anwserLanguage: string,
              readonly wordStatsTO: WordStatsResponse) {
  }
}
