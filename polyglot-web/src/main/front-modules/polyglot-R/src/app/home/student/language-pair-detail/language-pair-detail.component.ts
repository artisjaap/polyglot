import { Component, OnInit } from '@angular/core';
import {Translation} from "../../model/translation";
import {Observable} from "rxjs";
import {AppState} from "../../../reducers";
import {select, Store} from "@ngrx/store";
import {latestLessonsForLanguagePair, latestTranslationsForLanguagePair} from "../student.selectors";
import {ActivatedRoute, ActivatedRouteSnapshot, Route, Router} from "@angular/router";
import {LessonDataService} from "../../dataservice/lesson-data-service";
import {map} from "rxjs/operators";
import {Lesson} from "../../model/lesson";
import {LessonHeader} from "../../model/lesson-header";

@Component({
  selector: 'app-language-pair-detail',
  templateUrl: './language-pair-detail.component.html',
  styleUrls: ['./language-pair-detail.component.scss']
})
export class LanguagePairDetailComponent implements OnInit {

  latestTranslations: Observable<Translation[]>;
  latestLessons: Observable<LessonHeader[]>;

  constructor(private store:Store<AppState>, private route:ActivatedRoute, private lessonService: LessonDataService) { }

  ngOnInit() {
    this.latestTranslations = this.store.pipe(
      select(latestTranslationsForLanguagePair,{languagePairId:this.route.snapshot.params['languagePairId']})
    );

    this.latestLessons = this.store.pipe(
      select(latestLessonsForLanguagePair,{languagePairId:this.route.snapshot.params['languagePairId']})
    );
  }

}


