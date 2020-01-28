import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Observable, of, Subscriber} from 'rxjs';
import {LessonResponse} from '../../model/lesson-response';
import {Store} from '@ngrx/store';
import {AppState} from '../../../reducers';
import {ActivatedRoute} from '@angular/router';
import {lessonById, previousAnswer} from '../student.selectors';
import {TranslationForLessonResponse} from '../../model/translation-for-lesson-response';
import {StudentActions} from '../action-types';
import {PracticeAnswerValidateRequest} from '../../model/practice-answer-validate-request';
import {PreviousAnswer} from './previous-answer';
import {selectPracticeLesson} from '../reducers';
import {PracticeAnswerResponse} from '../../model/practice-answer-response';
import {ArrayTools} from '../../../tools/ArrayTools';

@Component({
  selector: 'app-practice-lesson',
  templateUrl: './practice-lesson.component.html',
  styleUrls: ['./practice-lesson.component.scss']
})
export class PracticeLessonComponent implements OnInit {

  private translation: TranslationForLessonResponse;
  private lesson$: Observable<LessonResponse>;
  private lesson: LessonResponse = new LessonResponse();
  private currentTranslationQueue: TranslationForLessonResponse[] = [];


  private previousAnswer$: Observable<PracticeAnswerResponse>;

  private stats = {aantalOpgelost: 0, aantalJuist: 0, aantalFout: 0};

  constructor(private store: Store<AppState>, private route: ActivatedRoute) {
    const lessonId = route.snapshot.params.lessonId;

    this.lesson$ = store.select(lessonById, {lessonId});
    this.lesson$.subscribe(l => this.lesson = l);

    this.previousAnswer$ = store.select(previousAnswer, {});
    this.previousAnswer$.subscribe(previous => {
      this.updateStats(previous);
    });
  }

  ngOnInit() {
    this.loadTranslation();
  }

  loadTranslation() {
    if (this.currentTranslationQueue.length === 0) {
      this.currentTranslationQueue = ArrayTools.copy(this.lesson.translations);
    }

    if (this.currentTranslationQueue.length > 0) {
      this.translation = this.currentTranslationQueue.splice(Math.floor(Math.random() * this.currentTranslationQueue.length), 1)[0];
    }

  }





  check(answer: HTMLInputElement) {
    const practiceAnswer: PracticeAnswerValidateRequest = {
      lessonId: this.lesson.id,
      translationId: this.translation.id,
      answerGiven: answer.value,
      answerOrderType: 'NORMAL'
    };

    this.store.dispatch(StudentActions.checkPracticeWordAnswer({practiceAnswer}));

    // const previousAnswer = new PreviousAnswer();
    // previousAnswer.question = this.translation.languageA;
    // previousAnswer.givenAnswer = answer.value;
    // previousAnswer.expectedAnswer = this.translation.languageB;
    answer.value = '';
    //
    // this.updateStats(previousAnswer);




    this.loadTranslation();

  }

  private updateStats(practiceAnswer: PracticeAnswerResponse) {
    if (practiceAnswer) {

      this.stats.aantalOpgelost++;
      if (practiceAnswer.correctAnswer) {
        this.stats.aantalJuist++;
      } else {
        this.stats.aantalFout++;


      }
    }
  }
}
