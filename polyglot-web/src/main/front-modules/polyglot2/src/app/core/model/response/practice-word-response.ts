import {WordStatsResponse} from "./word-stats-response";

export interface PracticeWordResponse {
  isReversed:boolean;
  translationId:string;
  question:string;
  questionLanguage:string;
  answer:string;
  anwserLanguage:string;
  wordStatsTO:WordStatsResponse;
}
