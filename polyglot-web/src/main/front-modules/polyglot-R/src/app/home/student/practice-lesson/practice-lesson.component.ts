import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Observable, of, Subscriber} from 'rxjs';
import {LessonResponse} from '../../model/lesson-response';
import {Store} from '@ngrx/store';
import {AppState} from '../../../reducers';
import {ActivatedRoute} from '@angular/router';
import {lessonById} from '../student.selectors';
import {TranslationForLessonResponse} from '../../model/translation-for-lesson-response';
import {StudentActions} from '../action-types';
import {PracticeAnswerValidateRequest} from '../../model/practice-answer-validate-request';
import {PreviousAnswer} from './previous-answer';

@Component({
  selector: 'app-practice-lesson',
  templateUrl: './practice-lesson.component.html',
  styleUrls: ['./practice-lesson.component.scss']
})
export class PracticeLessonComponent implements OnInit {

  private translation: TranslationForLessonResponse;
  private lesson$: Observable<LessonResponse>;
  private lesson: LessonResponse = new LessonResponse();

  private previousAnswer$: Observable<PreviousAnswer>;

  private stats = {aantalOpgelost:0, aantalJuist:0, aantalFout:0}

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
    const practiceAnswer: PracticeAnswerValidateRequest = {
      lessonId: this.lesson.id,
      translationId: this.translation.id,
      answerGiven: answer.value,
      answerOrderType: 'NORMAL'
    };

    this.store.dispatch(StudentActions.checkPracticeWordAnswer({practiceAnswer}));

    const previousAnswer = new PreviousAnswer();
    previousAnswer.question = this.translation.languageA;
    previousAnswer.givenAnswer = answer.value;
    previousAnswer.expectedAnswer = this.translation.languageB;
    answer.value = "";

    this.updateStats(previousAnswer);

    this.previousAnswer$ = of(previousAnswer);



    this.loadTranslation();

  }

  private updateStats(previousAnswer: PreviousAnswer) {
    this.stats.aantalOpgelost++;
    if(previousAnswer.isCorrect()){
      this.stats.aantalJuist++;
    }else {
      this.stats.aantalFout++;


    }
  }
}
