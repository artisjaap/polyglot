export class NewTranslationForLessonRequest {


  constructor(readonly languagePairId: string,
              readonly lessonId: string,
              readonly languageA: string[],
              readonly languageB: string[]) {
  }
}
