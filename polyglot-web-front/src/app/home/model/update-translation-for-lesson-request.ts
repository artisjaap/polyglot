export class UpdateTranslationForLessonRequest {
  constructor(readonly lessonId: string,
              readonly translationId: string,
              readonly languageA: string[],
              readonly languageB: string[]) {
  }


}
