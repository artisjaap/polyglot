import {Inject, Injectable} from "@angular/core";
import {Lesson} from "../model/lesson";
import {Observable, of} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {NewLessonRequest} from "../model/new-lesson-request";
import {NewTranslation} from "../model/new-translation";

@Injectable()
export class LessonService {
  constructor(private httpClient:HttpClient,  @Inject('API_URL') private apiurl: string) {

  }

  loadLessonById(lessonId:string): Observable<Lesson>{
    return this.httpClient.get<Lesson>(this.apiurl + `api/lesson/${lessonId}` );
  }

  createLesson(lesson: NewLessonRequest) : Observable<Lesson>{
    return this.httpClient.post<Lesson>(this.apiurl + `api/lessons/`, lesson );
  }

  removeTranslationFromLesson(lessonId:string, translationId:string) : Observable<any> {
    return this.httpClient.delete(this.apiurl + `api/lesson/${lessonId}/${translationId}`);
  }

  addNewTranslationToLesson(lessonId: string, translation: NewTranslation) : Observable<any> {
    return this.httpClient.put(this.apiurl + `api/lesson/${lessonId}/translation`, translation);
  }
}
