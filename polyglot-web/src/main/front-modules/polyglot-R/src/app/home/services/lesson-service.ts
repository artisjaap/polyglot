import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";
import {Translation} from "../model/translation";
import {LessonHeader} from "../model/lesson-header";

@Injectable()
export class LessonService {
  constructor() {

  }

  public loadLatestLessonsForLanguagePair(languagePairId: string): Observable<LessonHeader[]> {
    return of([{id:'a1', name: 'Lesson'}]);
  }
}
