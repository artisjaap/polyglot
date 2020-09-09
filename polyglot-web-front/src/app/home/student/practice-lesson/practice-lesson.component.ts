import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Observable, of, Subscription} from 'rxjs';
import {LessonResponse} from '../../model/lesson-response';
import {Store} from '@ngrx/store';
import {AppState} from '../../../reducers/app.reducer';
import {ActivatedRoute} from '@angular/router';
import {lessonById, previousAnswer} from '../reducers/student.selectors';
import {TranslationForLessonResponse} from '../../model/translation-for-lesson-response';
import {PracticeAnswerValidateRequest} from '../../model/practice-answer-validate-request';
import {LessonPracticeTranslationService} from '../../services/lesson-practice-translation.service';
import {PracticeWordResponse} from '../../model/practice-word-response';
import {first} from 'rxjs/operators';
import {PracticeWordCheckRequest} from '../../model/practice-word-check-request';
import {AnswerResponse} from '../../model/answer-response';
import {LessonPracticeStatusResponse} from '../../model/lesson-practice-status-response';
import {LessonPracticeTransationStatusResponse} from '../../model/lesson-practice-transation-status-response';

@Component({
  selector: 'app-practice-lesson',
  templateUrl: './practice-lesson.component.html',
  styleUrls: ['./practice-lesson.component.scss']
})
export class PracticeLessonComponent implements OnInit {

  lesson$: Observable<LessonResponse>;
  private lesson: LessonResponse = new LessonResponse();
  private currentTranslationQueue: TranslationForLessonResponse[] = [];
  private lessonId: string;
   languagePairId: string;


  public lessonPracticeStatus: LessonPracticeStatusResponse;

  form: FormGroup;


  stats = {aantalOpgelost: 0, aantalJuist: 0, aantalFout: 0};

  question: PracticeWordResponse;
  previousAnswer$: Observable<AnswerResponse>;

  private settingsChangedSubscription: Subscription;

  constructor(private store: Store<AppState>,
              private route: ActivatedRoute,
              private lessonPracticeTranslationService: LessonPracticeTranslationService,
              private formBuilder: FormBuilder) {
    this.lessonId = route.snapshot.params.lessonId;
    this.languagePairId = route.snapshot.params.languagePairId;

    this.lesson$ = store.select(lessonById, {lessonId: this.lessonId});
    this.lesson$.subscribe(l => this.lesson = l);

    this.previousAnswer$ = store.select(previousAnswer, {});
    this.previousAnswer$.subscribe(previous => {
      this.updateStats(previous);
    });

    this.form = formBuilder.group({
      reversed: [false]
    });

    this.settingsChangedSubscription = this.form.valueChanges.subscribe(v => {
      this.lessonPracticeTranslationService.requestWordForLesson(this.lessonId, this.form.value.reversed ? 'REVERSE' : 'NORMAL')
        .pipe(first())
        .subscribe(question => this.question = question);
    });
  }

  ngOnInit() {
    this.lessonPracticeTranslationService.requestWordForLesson(this.lessonId, this.form.value.reversed ? 'REVERSE' : 'NORMAL')
      .pipe(first())
      .subscribe(question => this.question = question);
  }


  check(answer: HTMLInputElement) {
    const practiceAnswer: PracticeAnswerValidateRequest = {
      lessonId: this.lesson.id,
      translationId: this.question.translationId,
      answerGiven: answer.value,
      answerOrderType: this.question.isReversed ? 'REVERSE' : 'NORMAL'
    };

    this.lessonPracticeTranslationService.validatePracticeResult(new PracticeWordCheckRequest(
      this.lesson.id,
      '',
      this.question.translationId,
      answer.value,
      this.form.value.reversed ? 'REVERSE' : 'NORMAL',
      this.form.value.reversed ? 'REVERSE' : 'NORMAL'
    )).pipe(first())
      .subscribe(question => {
        this.updateStats(question.answerResponse);
        this.previousAnswer$ = of(question.answerResponse);
        this.question = question.practiceWordResponse;
        this.lessonPracticeStatus = question.lessonPracticeStatusResponse;
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

  lessonPracticeTranslationsInStatus(status: string): LessonPracticeTransationStatusResponse[] {
    if (this.lessonPracticeStatus) {
      return this.lessonPracticeStatus.translationstatusses.filter(translation => translation.status === status);
    }
  }

}
