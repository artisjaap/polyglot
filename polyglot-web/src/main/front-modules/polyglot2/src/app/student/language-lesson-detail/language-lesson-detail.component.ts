import {Component, Input, OnInit} from '@angular/core';
import {LessonDetail} from "../../core/model/lesson-detail";
import {WordSelector} from "../../polyglot-common/word-selector/word-selector";
import {LessonService} from "../../polyglot-common/lesson.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-language-lesson-detail',
  templateUrl: './language-lesson-detail.component.html',
  styleUrls: ['./language-lesson-detail.component.scss']
})
export class LanguageLessonDetailComponent implements OnInit {

  lessonDetail: LessonDetail;
  currentLesson: LessonDetail;

  constructor(private lessonService:LessonService) { }

  ngOnInit() {
    // this.lessonDetail = {id:"", name:"lesson", translations:[{id:"1", from:"from", to:"to"}]}

     this.lessonService.findLessonById("1")
      .subscribe(l => this.currentLesson = l);
  }

  wordSelectorData(): WordSelector {
    return {languagePairId:"1"}; //FIXME remove dummy
  }
}
