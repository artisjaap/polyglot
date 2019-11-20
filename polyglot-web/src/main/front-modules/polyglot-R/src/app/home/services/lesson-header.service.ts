import {Inject, Injectable} from "@angular/core";
import {Observable, of} from "rxjs";
import {Translation} from "../model/translation";
import {LessonHeader} from "../model/lesson-header";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable()
export class LessonHeaderService {
  constructor(private httpClient:HttpClient,  @Inject('API_URL') private apiurl: string) {

  }

  public loadLatestLessonsForLanguagePair(languagePairId: string): Observable<LessonHeader[]> {
    let params = new HttpParams().set("languagePairId", languagePairId);

    return this.httpClient.get<LessonHeader[]>(this.apiurl + `api/lesson-headers` , {params:params});
  }
}
