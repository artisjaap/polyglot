import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../user/login.service";
import {LessonService} from "../../polyglot-common/lesson.service";
import {Observable} from "rxjs";
import {LessonDetail} from "../../core/model/lesson-detail";

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.scss']
})
export class StudentDashboardComponent implements OnInit {

  latestLessons: Observable<LessonDetail>;

  constructor(public lessonService:LessonService) { }

  ngOnInit() {
     this.latestLessons = this.lessonService.findLatestLessons();

  }


}
