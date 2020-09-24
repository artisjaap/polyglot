export class PracticeWordCheckRequest {
  constructor(readonly lessonId: string,
              readonly userId: string,
              readonly translationId: string,
              readonly answerGiven: string,
              readonly answerOrderType: string,
              readonly nextOrderType: string,
              readonly normalized: boolean) {
  }
}
