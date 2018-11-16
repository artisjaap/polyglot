export class PracticeWordCheckRequest{

  constructor(public userId:string,
  public translationId:string,
  public answerGiven:string,
  public answerOrderType:string,
  public nextOrderType:string,
  public lessonId:string){}
}
