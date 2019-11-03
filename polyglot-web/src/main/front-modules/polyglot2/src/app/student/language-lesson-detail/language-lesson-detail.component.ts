import {Component, Input, OnInit} from '@angular/core';
import {LessonDetail} from "../../core/model/lesson-detail";

@Component({
  selector: 'app-language-lesson-detail',
  templateUrl: './language-lesson-detail.component.html',
  styleUrls: ['./language-lesson-detail.component.scss']
})
export class LanguageLessonDetailComponent implements OnInit {

  lessonDetail: LessonDetail;

  constructor() { }

  ngOnInit() {
    this.lessonDetail = {id:"", name:"lesson", translations:[]}
  }

}
