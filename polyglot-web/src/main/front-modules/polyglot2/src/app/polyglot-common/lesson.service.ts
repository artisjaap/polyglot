import { Injectable } from '@angular/core';
import {AuthenticatedBaseServiceService} from "./authenticated-base-service.service";
import {LessonDetail} from "../core/model/lesson-detail";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LessonService extends AuthenticatedBaseServiceService{

  findLessonById(lessonId: string): Observable<LessonDetail> {
    let lessonDetail: LessonDetail = {id:"1", name:"my lesson", translations: [{id:"1", from:"from", to:"to"}]};
    return of(lessonDetail);
  }
}
