import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {PracticeAnswerValidateRequest} from '../model/practice-answer-validate-request';
import {Observable} from 'rxjs';
import {LessonResponse} from '../model/lesson-response';
import {PracticeAnswerResponse} from '../model/practice-answer-response';
import {CreatePracticePdfRequest} from '../model/create-practice-pdf-request';

@Injectable()
export class PracticeAnswerService {
  constructor(private httpClient: HttpClient) {

  }

  public validatePracticeResult(practiceAnswerValidateRequest: PracticeAnswerValidateRequest): Observable<PracticeAnswerResponse> {
    return this.httpClient.put<PracticeAnswerResponse>(`api/practice/validate`, practiceAnswerValidateRequest );
  }

  public createPracticePdf(createPracticePdfRequest: CreatePracticePdfRequest): Observable<HttpResponse<Blob>> {
    return this.httpClient.post(`api/practice/generate-pdf`, createPracticePdfRequest,
      {observe: 'response', responseType: 'blob'});
  }


}
