import {Inject, Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {LessonHeaderResponse} from '../model/lesson-header-response';
import {NewLessonHeaderRequest} from '../model/new-lesson-header-request';

@Injectable()
export class LessonHeaderService {
  constructor(private httpClient: HttpClient) {

  }

  public loadLessonsForLanguagePair(languagePairId: string): Observable<LessonHeaderResponse[]> {
    const params = new HttpParams().set('languagePairId', languagePairId);
    return this.httpClient.get<LessonHeaderResponse[]>( `api/lesson-headers` , {params});
  }

  public createNewLesson(newLessonHeaderRequest: NewLessonHeaderRequest): Observable<LessonHeaderResponse> {
    return this.httpClient.post<LessonHeaderResponse>( `api/lesson-header` , newLessonHeaderRequest);
  }
}
