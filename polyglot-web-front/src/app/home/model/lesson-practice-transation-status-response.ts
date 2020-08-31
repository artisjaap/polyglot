export class LessonPracticeTransationStatusResponse {

  constructor(readonly languageA: string,
              readonly languageB: string,
              readonly asked: number,
              readonly correct: number,
              readonly percentage: number,
              readonly status: string) {

  }


}
