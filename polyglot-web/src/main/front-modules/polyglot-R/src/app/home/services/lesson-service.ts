import {Inject, Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {LessonResponse} from '../model/lesson-response';
import {LessonHeaderResponse} from '../model/lesson-header-response';
import {NewLessonHeaderRequest} from '../model/new-lesson-header-request';


@Injectable()
export class LessonService {
  constructor(private httpClient: HttpClient) {

  }

  loadLessonById(lessonId: string): Observable<LessonResponse> {
    return this.httpClient.get<LessonResponse>( `api/lesson/${lessonId}` );
  }

  deleteWordFromLesson(lessonId: string, translationId: string): Observable<LessonResponse> {
    return this.httpClient.delete<LessonResponse>( `api/lesson/${lessonId}/translation/${translationId}` );

  }

  deleteLesson(lessonId: string): Observable<LessonHeaderResponse> {
    return this.httpClient.delete<LessonHeaderResponse>( `api/lesson/${lessonId}/${lessonId}` );
  }

}
