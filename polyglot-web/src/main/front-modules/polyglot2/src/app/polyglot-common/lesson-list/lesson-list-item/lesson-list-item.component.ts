import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LessonListItem} from "../lesson-list-item";

@Component({
  selector: 'app-lesson-list-item',
  templateUrl: './lesson-list-item.component.html',
  styleUrls: ['./lesson-list-item.component.scss']
})
export class LessonListItemComponent implements OnInit {

  @Input()
  lessonListItem: LessonListItem;

  @Output()
  openLessonEvent = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  openLesson() {
    this.openLessonEvent.emit(this.lessonListItem);
  }
}
