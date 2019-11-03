import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LessonListItem} from "./lesson-list-item";

@Component({
  selector: 'app-lesson-list',
  templateUrl: './lesson-list.component.html',
  styleUrls: ['./lesson-list.component.scss']
})
export class LessonListComponent implements OnInit {

  @Input()
  lessons :LessonListItem[];

  @Output()
  openLesson = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  openLessonResponse($event: any) {
    this.openLesson.emit($event);
  }
}
