import {WordResultResponse} from "./word-result-response";

export interface TestResultResponse {
  lessonName:string;
  score:number;
  wordResults:WordResultResponse;
}
