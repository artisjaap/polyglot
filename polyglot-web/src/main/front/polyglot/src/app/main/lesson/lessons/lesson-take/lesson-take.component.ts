import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LessonService} from "../../../common/services/lesson.service";
import {TestAssignmentResponse} from "../../../common/services/response/test-assignment-response";

@Component({
  selector: 'pol-lesson-take',
  templateUrl: './lesson-take.component.html',
  styleUrls: ['./lesson-take.component.scss']
})
export class LessonTakeComponent implements OnInit {
  lessonForTesting: TestAssignmentResponse;

  constructor(private route: ActivatedRoute,
              private lessonService:LessonService) { }

  ngOnInit() {
    let lessonId = this.route.snapshot.params['lessonId'];
    this.lessonService.getLessonForTesting(lessonId, "NORMAL")
      .subscribe(r => {
        return this.lessonForTesting = r;
      });

  }

}
