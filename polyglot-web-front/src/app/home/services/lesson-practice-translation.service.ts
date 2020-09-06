import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PracticeAnswerValidateRequest} from '../model/practice-answer-validate-request';
import {Observable} from 'rxjs';
import {PracticeAnswerResponse} from '../model/practice-answer-response';
import {PracticeWordResponse} from '../model/practice-word-response';
import {PracticeWordCheckRequest} from '../model/practice-word-check-request';
import {AnswerAndNextWordResponse} from '../model/answer-and-next-word-response';

@Injectable({
  providedIn: 'root'
})
export class LessonPracticeTranslationService {

  constructor(private httpClient: HttpClient,  @Inject('API_URL') private apiurl: string) {

  }

  public requestWordForLesson(lessonId: string, orderType: string): Observable<PracticeWordResponse> {
    return this.httpClient.get<PracticeWordResponse>(this.apiurl + `api/practice-lesson/next/${lessonId}/${orderType}` );
  }

  public validatePracticeResult(practiceAnswerValidateRequest: PracticeWordCheckRequest): Observable<AnswerAndNextWordResponse> {
    return this.httpClient.post<AnswerAndNextWordResponse>(this.apiurl + `api/practice-lesson/check-and-next`, practiceAnswerValidateRequest );
  }

  public resetLessonPractice(lessonId: string): Observable<any>{
    return this.httpClient.get<PracticeWordResponse>(this.apiurl + `api/practice-lesson/${lessonId}/reset` );

  }
}
