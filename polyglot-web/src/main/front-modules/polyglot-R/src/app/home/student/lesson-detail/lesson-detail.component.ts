import {Component, OnInit} from '@angular/core';
import {Lesson} from "../../model/lesson";
import {Observable} from "rxjs";
import {select, Store} from "@ngrx/store";
import {lessonWithId} from "../student.selectors";
import {AppState} from "../../../reducers";
import {ActivatedRoute} from "@angular/router";
import {StudentActions} from "../action-types";
import {Update} from "@ngrx/entity/src/models";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-lesson-detail',
  templateUrl: './lesson-detail.component.html',
  styleUrls: ['./lesson-detail.component.scss']
})
export class LessonDetailComponent implements OnInit {
  private form: FormGroup;

  lesson$: Observable<Lesson>;

  constructor(private store: Store<AppState>, private route: ActivatedRoute, private fb: FormBuilder) {

    this.form = this.fb.group({
      question: ['question'],
      solution: ['solution']
    })

  }

  ngOnInit() {
    this.lesson$ = this.store.pipe(
      select(lessonWithId, {lessonId: this.route.snapshot.params['lessonId']})
    );


  }

  removeWord(translationId: string, lessonId: string, lesson: Lesson) {
    const updatedLesson: Update<Lesson> = {
      id: lessonId,
      changes: {...lesson, translations: lesson.translations.filter(t => t.translationId !== translationId)}
    };
    this.store.dispatch(StudentActions.removeTranslationFromLesson({translationId, lessonId, updatedLesson}));
  }

  createTranslation(lesson: Lesson) {
    const data = this.form.value;

    const updatedLesson = {
      ...lesson,
      translations: [...lesson.translations, data]
    }
    this.store.dispatch(StudentActions.addNewTranslation({
      lessonId: lesson.id,
      translation: data,
      updatedLesson: updatedLesson
    }))
  }
}
