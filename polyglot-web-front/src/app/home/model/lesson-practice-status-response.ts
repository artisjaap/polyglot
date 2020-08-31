import {LessonPracticeTransationStatusResponse} from './lesson-practice-transation-status-response';

export class LessonPracticeStatusResponse {

  constructor(readonly translationstatusses: LessonPracticeTransationStatusResponse[]) {
  }
}
