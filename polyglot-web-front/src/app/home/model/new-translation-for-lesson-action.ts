export class NewTranslationForLessonAction {

  constructor(readonly uuid: string,
              readonly languagePairId: string,
              readonly lessonId: string,
              readonly languageA: string[],
              readonly languageB: string[]) {
  }
}
