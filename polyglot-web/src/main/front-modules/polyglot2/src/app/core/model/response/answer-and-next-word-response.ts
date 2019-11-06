import {AnswerResponse} from "./answer-response";
import {PracticeWordResponse} from "./practice-word-response";

export interface AnswerAndNextWordResponse {
  practiceWordResponse:PracticeWordResponse;
  answerResponse:AnswerResponse;
}
