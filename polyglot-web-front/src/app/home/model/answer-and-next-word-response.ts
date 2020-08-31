import {PracticeWordResponse} from './practice-word-response';
import {AnswerResponse} from './answer-response';
import {LessonPracticeStatusResponse} from './lesson-practice-status-response';

export class AnswerAndNextWordResponse {
  constructor(readonly practiceWordResponse: PracticeWordResponse,
              readonly answerResponse: AnswerResponse,
              readonly lessonPracticeStatusResponse: LessonPracticeStatusResponse) {
  }
}
