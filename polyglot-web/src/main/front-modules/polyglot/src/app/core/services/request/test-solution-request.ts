import {WordSolutionRequest} from './word-solution-request';

export class TestSolutionRequest {

  constructor(private lessonId: string,
              private userId: string,
              private solutions: WordSolutionRequest[]){}

}
