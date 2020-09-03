import {Inject, Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {LessonResponse} from '../model/lesson-response';
import {LessonHeaderResponse} from '../model/lesson-header-response';
import {NewLessonHeaderRequest} from '../model/new-lesson-header-request';


@Injectable()
export class LessonService {
  constructor(private httpClient: HttpClient,  @Inject('API_URL') private apiurl: string) {

  }

  loadLessonById(lessonId: string): Observable<LessonResponse> {
    return this.httpClient.get<LessonResponse>(this.apiurl + `api/lesson/${lessonId}` );
  }

  deleteWordFromLesson(lessonId: string, translationId: string): Observable<LessonResponse> {
    return this.httpClient.delete<LessonResponse>(this.apiurl + `api/lesson/${lessonId}/translation/${translationId}` );

  }

  deleteLesson(lessonId: string): Observable<LessonResponse> {
    return this.httpClient.delete<LessonResponse>(this.apiurl + `api/lesson/${lessonId}` );
  }

}
