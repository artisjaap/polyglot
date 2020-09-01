import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {select, Store} from '@ngrx/store';
import {AppState} from '../../../reducers/app.reducer';
import {ActivatedRoute} from '@angular/router';
import {StudentActions} from '../action-types';
import {Update} from '@ngrx/entity/src/models';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LessonResponse} from '../../model/lesson-response';
import {lessonById} from '../reducers/student.selectors';
import {NewTranslationForLessonRequest} from '../../model/new-translation-for-lesson-request';

@Component({
  selector: 'app-lesson-detail',
  templateUrl: './lesson-detail.component.html',
  styleUrls: ['./lesson-detail.component.scss']
})
export class LessonDetailComponent implements OnInit {
  form: FormGroup;
  lesson$: Observable<LessonResponse>;


  constructor(private store: Store<AppState>, private route: ActivatedRoute, private fb: FormBuilder) {
    const lessonId = route.snapshot.params.lessonId;

    this.form = this.fb.group({
      question: ['question'],
      solution: ['solution']
    });

    this.lesson$ = store.select(lessonById, {lessonId});


  }

  ngOnInit() {

  }

  createTranslation(lesson: LessonResponse) {
    const translation: NewTranslationForLessonRequest = {
      languagePairId: lesson.languagePairId,
      lessonId: lesson.id,
      languageA: this.form.value.question,
      languageB: this.form.value.solution
    };

    this.store.dispatch(StudentActions.addNewTranslationToLesson({translation}));
  }

  removeWordFromLesson(translationId: string, lessonId: string) {
    this.store.dispatch(StudentActions.deleteTranslationFromLesson({translationId, lessonId}));
  }

  // removeWord(translationId: string, lessonId: string, lesson: Lesson) {
  //   const updatedLesson: Update<Lesson> = {
  //     id: lessonId,
  //     changes: {...lesson, translations: lesson.translations.filter(t => t.translationId !== translationId)}
  //   };
  //   this.store.dispatch(StudentActions.removeTranslationFromLesson({translationId, lessonId, updatedLesson}));
  // }
  //
  // createTranslation(lesson: Lesson) {
  //   const data = this.form.value;
  //
  //   const updatedLesson = {
  //     ...lesson,
  //     translations: [...lesson.translations, data]
  //   }
  //   this.store.dispatch(StudentActions.addNewTranslation({
  //     lessonId: lesson.id,
  //     translation: data,
  //     updatedLesson: updatedLesson
  //   }))
  // }
}
