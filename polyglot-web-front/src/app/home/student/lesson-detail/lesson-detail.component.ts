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
import {NewPotentialTranslation} from '../../model/new-potential-translation';

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

  newPotentialTranslation(lesson: LessonResponse) {
    const translation = new NewPotentialTranslation(lesson.id, lesson.languagePairId);

    this.store.dispatch(StudentActions.addNewPotentialTranslationToLesson({translation}));
  }




}
