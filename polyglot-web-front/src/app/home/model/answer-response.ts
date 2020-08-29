export class AnswerResponse {
  constructor(readonly translationId: string,
              readonly question: string,
              readonly givenAnswer: string,
              readonly expectedAnswer: string[],
              readonly correctAnswer: boolean) {
  }
}
