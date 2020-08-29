export class WordStatsResponse {

  constructor(readonly progressStatus: string,
              readonly knowledgeStatus: string,
              readonly knowledgeStatusReverse: string,
              readonly knowledgeCounterSuccess: number,
              readonly knowledgeCounterSuccessReverse: number,
              readonly knowledgeCounterMiss: number,
              readonly knowledgeCounterMissReverse: number,
              readonly answerChecked: number,
              readonly answerCheckedReverse: number,
              readonly lastSuccess: string,
              readonly lastSuccessReverse: string,
              readonly lastMiss: string,
              readonly lastMissReverse: string) {
  }
}
