import {WordSolutionRequest} from "./word-solution-request";

export interface TestSolutionRequest {

  lessonId:string;
  userId:string;
  solutions:WordSolutionRequest[];

}
