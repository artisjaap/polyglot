export class PreviousAnswer {
  question: string;
  givenAnswer: string;
  expectedAnswer: string;

  public isCorrect() : boolean{
    return this.givenAnswer.toLowerCase() === this.expectedAnswer.toLowerCase();

  }
}
