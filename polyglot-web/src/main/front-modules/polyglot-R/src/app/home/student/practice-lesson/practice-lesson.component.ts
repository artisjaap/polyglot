import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Observable, Subscriber} from 'rxjs';
import {LessonResponse} from '../../model/lesson-response';
import {Store} from '@ngrx/store';
import {AppState} from '../../../reducers';
import {ActivatedRoute} from '@angular/router';
import {lessonById} from '../student.selectors';
import {TranslationForLessonResponse} from '../../model/translation-for-lesson-response';

@Component({
  selector: 'app-practice-lesson',
  templateUrl: './practice-lesson.component.html',
  styleUrls: ['./practice-lesson.component.scss']
})
export class PracticeLessonComponent implements OnInit {

  private translation: TranslationForLessonResponse;
  private lesson$: Observable<LessonResponse>;
  private lesson: LessonResponse;


  constructor(private store: Store<AppState>, private route: ActivatedRoute) {
    const lessonId = route.snapshot.params.lessonId;

    this.lesson$ = store.select(lessonById, {lessonId});
    this.lesson$.subscribe(l => this.lesson = l);


  }

  ngOnInit() {
    this.loadTranslation();
  }


  private loadTranslation() {
   this.translation = this.lesson.translations[Math.floor(Math.random() * this.lesson.translations.length)];
  }

  check(answer: HTMLInputElement) {
    console.log(this.translation.languageB);
    if(answer.value === this.translation.languageB){
      this.loadTranslation();
    }
  }
}
