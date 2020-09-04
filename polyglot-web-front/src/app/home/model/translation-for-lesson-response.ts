export class TranslationForLessonResponse {
  constructor(readonly id: string,
              readonly lessonId: string,
              readonly languageA: string[],
              readonly languageB: string[]){}
}
