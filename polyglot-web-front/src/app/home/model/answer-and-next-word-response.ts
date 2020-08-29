import {PracticeWordResponse} from './practice-word-response';
import {AnswerResponse} from './answer-response';

export class AnswerAndNextWordResponse {
  constructor(readonly practiceWordResponse: PracticeWordResponse,
              readonly answerResponse: AnswerResponse) {
  }
}
