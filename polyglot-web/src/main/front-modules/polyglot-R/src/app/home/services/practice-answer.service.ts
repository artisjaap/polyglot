import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PracticeAnswerValidateRequest} from '../model/practice-answer-validate-request';
import {Observable} from 'rxjs';
import {LessonResponse} from '../model/lesson-response';
import {PracticeAnswerResponse} from '../model/practice-answer-response';

@Injectable()
export class PracticeAnswerService {
  constructor(private httpClient: HttpClient,  @Inject('API_URL') private apiurl: string) {

  }

  public validatePracticeResult(practiceAnswerValidateRequest: PracticeAnswerValidateRequest): Observable<PracticeAnswerResponse> {
    return this.httpClient.put<LessonResponse>(this.apiurl + `api/practice/validate`, practiceAnswerValidateRequest );
  }
}
