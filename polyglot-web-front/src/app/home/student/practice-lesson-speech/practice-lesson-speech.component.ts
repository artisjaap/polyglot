import {Component, OnDestroy, OnInit} from '@angular/core';
import {noop, Observable, of, Subscription} from 'rxjs';
import {LessonResponse} from '../../model/lesson-response';
import {TranslationForLessonResponse} from '../../model/translation-for-lesson-response';
import {LessonPracticeStatusResponse} from '../../model/lesson-practice-status-response';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PracticeWordResponse} from '../../model/practice-word-response';
import {AnswerResponse} from '../../model/answer-response';
import {Store} from '@ngrx/store';
import {AppState} from '../../../reducers/app.reducer';
import {ActivatedRoute} from '@angular/router';
import {LessonPracticeTranslationService} from '../../services/lesson-practice-translation.service';
import {TextToSpeechService} from '../../../common/text-to-speech/services/text-to-speech.service';
import {lessonById, previousAnswer} from '../reducers/student.selectors';
import {first} from 'rxjs/operators';
import {PracticeWordCheckRequest} from '../../model/practice-word-check-request';
import {LessonPracticeTransationStatusResponse} from '../../model/lesson-practice-transation-status-response';

@Component({
  selector: 'app-practice-lesson-speech',
  templateUrl: './practice-lesson-speech.component.html',
  styleUrls: ['./practice-lesson-speech.component.scss']
})
export class PracticeLessonSpeechComponent implements OnInit, OnDestroy {

  private play: boolean = true;

  get question() {
    return this._question;
  }

  set question(q){
    this._question = q;

    const subscription = this.textToSpeechService.talk(q.question, 'nl').subscribe(noop, noop, () => {
      console.log(Date.now().toLocaleString(), 'question');
      subscription.unsubscribe();
      if(this.play){
        this.readAnswer();
      }
    } );
  }

  constructor(private store: Store<AppState>,
              private route: ActivatedRoute,
              private lessonPracticeTranslationService: LessonPracticeTranslationService,
              private formBuilder: FormBuilder,
              private textToSpeechService: TextToSpeechService) {
    this.lessonId = route.snapshot.params.lessonId;
    this.languagePairId = route.snapshot.params.languagePairId;

    this.lesson$ = store.select(lessonById, {lessonId: this.lessonId});
    this.lesson$.subscribe(l => this.lesson = l);

    this.previousAnswer$ = store.select(previousAnswer, {});
    this.previousAnswer$.subscribe(previous => {
      this.updateStats(previous);
    });

    this.form = formBuilder.group({
      reversed: [false],
      normalized: [false]
    });

    this.settingsChangedSubscription = this.form.valueChanges.subscribe(v => {
      this.lessonPracticeTranslationService.requestWordForLesson(this.lessonId, this.form.value.reversed ? 'REVERSE' : 'NORMAL')
        .pipe(first())
        .subscribe(question => this.question = question);
    });
  }


  lesson$: Observable<LessonResponse>;
  private lesson: LessonResponse = new LessonResponse();
  private lessonId: string;
  languagePairId: string;


  public lessonPracticeStatus: LessonPracticeStatusResponse;

  form: FormGroup;


  stats = {aantalOpgelost: 0, aantalJuist: 0, aantalFout: 0};

  _question: PracticeWordResponse;
  previousAnswer$: Observable<AnswerResponse>;

  private settingsChangedSubscription: Subscription;

  private readAnswer() {
    setTimeout(() => {
      console.log(Date.now().toLocaleString(), 'read');
      const subscription = this.textToSpeechService.talk(this.question.answer[0], 'fr').subscribe(noop, noop, () => {
        subscription.unsubscribe();
        if(this.play) {
          setTimeout(() => {
            this.check(this.question.answer[0]);
          }, 1000);
        }
      });

    }, 3000);
  }

  ngOnInit() {
    this.lessonPracticeTranslationService.requestWordForLesson(this.lessonId, this.form.value.reversed ? 'REVERSE' : 'NORMAL')
      .pipe(first())
      .subscribe(question => this.question = question);
  }


  check(answer: string) {


    this.lessonPracticeTranslationService.validatePracticeResult(new PracticeWordCheckRequest(
      this.lesson.id,
      '',
      this.question.translationId,
      answer,
      this.form.value.reversed ? 'REVERSE' : 'NORMAL',
      this.form.value.reversed ? 'REVERSE' : 'NORMAL',
      this.form.value.normalized
    )).pipe(first())
      .subscribe(question => {
        this.updateStats(question.answerResponse);
        this.previousAnswer$ = of(question.answerResponse);
        this.question = question.practiceWordResponse;
        this.lessonPracticeStatus = question.lessonPracticeStatusResponse;
      });



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

  ngOnDestroy(): void {
    this.play = false;
  }
}
