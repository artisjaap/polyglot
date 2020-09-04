export class UpdateTranslationForLessonAction {
  constructor(readonly uuid: string,
              readonly lessonId: string,
              readonly translationId: string,
              readonly languageA: string[],
              readonly languageB: string[]) {
  }
}
