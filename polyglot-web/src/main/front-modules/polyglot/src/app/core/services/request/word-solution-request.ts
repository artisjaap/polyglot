export class WordSolutionRequest {

  constructor(private translationId:string,
              private answerLanguage:string,
              private answer:string,
              private question:string) {

  }

}
