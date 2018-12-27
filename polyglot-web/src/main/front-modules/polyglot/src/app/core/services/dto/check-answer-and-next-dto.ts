export class CheckAnswerAndNextDto {
  constructor(public translationId: string,
              public answerGiven: string,
              public answerOrderType: string,
              public nextOrderType: string,
              public lessonId: string) {}
}
