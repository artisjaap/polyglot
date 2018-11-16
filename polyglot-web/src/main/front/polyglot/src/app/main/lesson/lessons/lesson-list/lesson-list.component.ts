import { Component, OnInit } from '@angular/core';
import {LessonService} from "../../../common/services/lesson.service";
import {ActivatedRoute} from "@angular/router";
import {LessonHeaderResponse} from "../../../common/services/response/lesson-header-response";
import {NewAutomaticLessonRequest} from "../../../common/services/request/new-automatic-lesson-request";

@Component({
  selector: 'pol-lesson-list',
  templateUrl: './lesson-list.component.html',
  styleUrls: ['./lesson-list.component.scss']
})
export class LessonListComponent implements OnInit {
  lessonForLanguagePair: LessonHeaderResponse[];
  languagePairId:string;

  constructor(private route: ActivatedRoute,
              private lessonService:LessonService) { }

  ngOnInit() {
    this.languagePairId = this.route.snapshot.params['pairId'];

    this.lessonService.lessonsForLanguage(this.languagePairId)
      .subscribe(r => this.lessonForLanguagePair = r);
  }

  autocreateLesson(lessonTitle:HTMLInputElement, numberOfWords:HTMLInputElement) {
    this.lessonService.autocreate(this.languagePairId, lessonTitle.value, +numberOfWords.value)
      .subscribe(r => {});
  }

  createNewLesson(lessonTitle:HTMLInputElement){
    this.lessonService.createLesson(this.languagePairId, lessonTitle.value)
      .subscribe(r => {});
  }

}
