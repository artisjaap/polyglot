export class NewAutomaticLessonRequest {

  constructor(public userId: string,
              public languagePairId: string,
              public lessonTitle: string,
              public maxNumberOfWords: number) {}

}
