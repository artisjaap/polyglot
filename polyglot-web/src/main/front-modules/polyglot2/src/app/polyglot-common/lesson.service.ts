import { Injectable } from '@angular/core';
import {AuthenticatedBaseServiceService} from "./authenticated-base-service.service";
import {LessonDetail} from "../core/model/lesson-detail";
import {Observable, of} from "rxjs";
import {LessonsFilterRequest} from "../../../../polyglot/src/app/core/services/request/lessons-filter-request";

@Injectable({
  providedIn: 'root'
})
export class LessonService extends AuthenticatedBaseServiceService{

  findLessonById(lessonId: string): Observable<LessonDetail> {

    let payload:LessonsFilterRequest = {pageSize:10,languagePairId:null,pageNumber:0, textFilter:''}
    return this.post('api/lessons/list/all/filtered', payload);
    // let lessonDetail: LessonDetail = {id:"1", name:"my lesson", translations: [{id:"1", from:"from", to:"to"}]};
    // return of(lessonDetail);
  }

  findLatestLessons(): Observable<LessonDetail> {
    console.log("find latest lessons")
    let payload:LessonsFilterRequest = {pageSize:10,languagePairId:null,pageNumber:0, textFilter:''}
    return this.post('api/lessons/list/all/filtered', payload);
    // let lessonDetail: LessonDetail = {id:"1", name:"my lesson", translations: [{id:"1", from:"from", to:"to"}]};
    // return of(lessonDetail);
  }
}
