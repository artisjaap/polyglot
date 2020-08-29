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
import {LessonPracticeTranslationService} from '../../services/lesson-practice-translation.service';
import {PracticeWordResponse} from '../../model/practice-word-response';
import {first} from 'rxjs/operators';
import {PracticeWordCheckRequest} from '../../model/practice-word-check-request';
import {AnswerResponse} from '../../model/answer-response';

@Component({
  selector: 'app-practice-lesson',
  templateUrl: './practice-lesson.component.html',
  styleUrls: ['./practice-lesson.component.scss']
})
export class PracticeLessonComponent implements OnInit {

  private lesson$: Observable<LessonResponse>;
  private lesson: LessonResponse = new LessonResponse();
  private currentTranslationQueue: TranslationForLessonResponse[] = [];
  private lessonId: string;





  private stats = {aantalOpgelost: 0, aantalJuist: 0, aantalFout: 0};

  private question: PracticeWordResponse;
  private previousAnswer$: Observable<AnswerResponse>;

  constructor(private store: Store<AppState>, private route: ActivatedRoute,private lessonPracticeTranslationService: LessonPracticeTranslationService) {
    this.lessonId = route.snapshot.params.lessonId;

    this.lesson$ = store.select(lessonById, {lessonId : this.lessonId});
    this.lesson$.subscribe(l => this.lesson = l);

    this.previousAnswer$ = store.select(previousAnswer, {});
    this.previousAnswer$.subscribe(previous => {
      this.updateStats(previous);
    });
  }

  ngOnInit() {
    this.lessonPracticeTranslationService.requestWordForLesson(this.lessonId, "NORMAL")
      .pipe(first())
      .subscribe(question => this.question = question);
  }






  check(answer: HTMLInputElement) {
    const practiceAnswer: PracticeAnswerValidateRequest = {
      lessonId: this.lesson.id,
      translationId: this.question.translationId,
      answerGiven: answer.value,
      answerOrderType: 'NORMAL'
    };


    this.lessonPracticeTranslationService.validatePracticeResult(new PracticeWordCheckRequest(
      this.lesson.id,
      "",
      this.question.translationId,
      answer.value,
      'NORMAL',
      'NORMAL'
    )).pipe(first())
      .subscribe(question => {
        this.updateStats(question.answerResponse);
        this.previousAnswer$ = of(question.answerResponse);
        this.question = question.practiceWordResponse;
      });

    // COMMENT this.store.dispatch(StudentActions.checkPracticeWordAnswer({practiceAnswer}));

    // const previousAnswer = new PreviousAnswer();
    // previousAnswer.question = this.translation.languageA;
    // previousAnswer.givenAnswer = answer.value;
    // previousAnswer.expectedAnswer = this.translation.languageB;
    answer.value = '';
    //
    // this.updateStats(previousAnswer);




    // this.loadTranslation();

  }

  private updateStats(practiceAnswer: AnswerResponse) {
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
