export interface WordStatsResponse {

  progressStatus: string;
  knowledgeStatus: string;
  knowledgeStatusReverse: string;
  knowledgeCounterSuccess: number;
  knowledgeCounterSuccessReverse: number;
  knowledgeCounterMiss: number;
  knowledgeCounterMissReverse: number;
  answerChecked: number;
  answerCheckedReverse: number;
  lastSuccess: string;
  lastSuccessReverse: string;
  lastMiss: string;
  lastMissReverse: string;

}
