export class NewLessonRequest {

  constructor(public userId: string, public languagePairId: string, public lessonTitle: string) {}
}
