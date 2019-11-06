export interface WordStatsResponse {
  progressStatus:string;
  knowledgeStatus:string;
  knowledgeStatusReverse:string;
  knowledgeCounterSuccess:number;
  knowledgeCounterSuccessReverse:number;
  knowledgeCounterMiss:number;
  knowledgeCounterMissReverse:number;
  answerChecked:number;
  answerCheckedReverse:number;
  lastSuccess:string; //LocalDateTime
  lastSuccessReverse:string; //LocalDateTime
  lastMiss:string; //LocalDateTime
  lastMissReverse:string; //LocalDateTime
}
