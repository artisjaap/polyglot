import {PracticeWordResponse} from './practice-word-response';
import {AnswerResponse} from './answer-response';

export interface AnswerAndNextWordResponse {
  practiceWordResponse: PracticeWordResponse;
  answerResponse: AnswerResponse;

}
