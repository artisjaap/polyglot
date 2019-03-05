import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {LessonService} from '../../core/services/lesson.service';
import {LessonResponse} from '../../core/services/response/lesson-response';
import {UtilService} from '../../core/services/util.service';
import {LessonTranslationPairResponse} from '../../core/services/response/lesson-translation-pair-response';
import {PracticeTranslationService} from '../../core/services/practice-translation.service';
import {AnswerResponse} from '../../core/services/response/answer-response';
import {interval, Subject, Subscription} from 'rxjs';

@Component({
  selector: 'pol-practice-lesson',
  templateUrl: './practice-lesson.component.html',
  styleUrls: ['./practice-lesson.component.scss']
})
export class PracticeLessonComponent implements OnInit, OnDestroy {
  lessonId: string;
  lesson: LessonResponse;
  translationsInProgress: LessonTranslationPairResponse[];
  currentWord: LessonTranslationPairResponse;
  order: string = 'NORMAL';
  previous: AnswerResponse;

  autohintSubscription: Subscription;

  @ViewChild('answerGiven') answerGiven: ElementRef;

  private autohintVal: boolean = false;
  autohint: Subject<boolean> = new Subject<boolean>();

  constructor(private route: ActivatedRoute,
              private lessonService: LessonService,
              private practiceService: PracticeTranslationService,
              private utilService: UtilService) {
  }

  ngOnInit() {
    this.lessonId = this.route.snapshot.params['lessonId'];
    this.lessonService.practiceLesson(this.lessonId).subscribe(l => {
      this.lesson = l;
      this.unshiftNextWord();
    });

    this.autohint.subscribe(value => {

      if (value) {
        this.startAutoHint();
      } else if (this.autohintSubscription) {
        this.stopAutoHint();
      }
    });

  }

  startAutoHint() {
    if (this.autohintVal && (!this.autohintSubscription || this.autohintSubscription.closed)) {
      this.autohintSubscription = interval(1000).pipe().subscribe( x => this.updateWithNext());
    }
  }

  stopAutoHint() {
    if (this.autohintSubscription) {
      this.autohintSubscription.unsubscribe();

    }
  }

  private updateWithNext() {
    let currentVal = this.answerGiven.nativeElement.value;

    while (this.currentWord.solution.indexOf(currentVal) != 0 && currentVal !== '') {
      currentVal = currentVal.substring(0, currentVal.length - 1);
    }

    if (currentVal.length < this.currentWord.solution.length) {
      this.answerGiven.nativeElement.value = currentVal + this.currentWord.solution.substring(currentVal.length, currentVal.length + 1);

    } else {
      this.checkAndNext(this.answerGiven.nativeElement);
    }


  }

  private unshiftNextWord() {
    if (!this.translationsInProgress || this.translationsInProgress.length === 0) {
      this.translationsInProgress = this.utilService.shuffle(this.lesson.translations);
    }
    if (this.translationsInProgress.length > 0) {
      let theQuestion = this.translationsInProgress.splice(0, 1)[0];

      if (this.order === 'NORMAL') {
        this.currentWord = theQuestion;
      } else if (this.order === 'REVERSE') {
        this.currentWord = this.switchDirection(theQuestion);
      } else {
        if (Math.random() * 2 > 1) {
          this.currentWord = theQuestion;
        } else {
          this.currentWord = this.switchDirection(theQuestion);
        }
      }
    }
  }

  checkAndNext(answerGiven: HTMLInputElement) {
    this.practiceService.checkAnswerAndNext({
      translationId: this.currentWord.translationId,
      answerGiven: answerGiven.value,
      answerOrderType: this.currentWord.isReverse ? 'REVERSE' : 'NORMAL',
      nextOrderType: 'NORMAL',
      lessonId: this.lessonId
    }).subscribe(r => {
      answerGiven.value = '';
      this.unshiftNextWord();
      this.previous = r.answerResponse;

    });
  }

  private switchDirection(translation: LessonTranslationPairResponse): LessonTranslationPairResponse {
    return {
      isReverse: !translation.isReverse,
      languageFrom: translation.languageFrom,
      languageTo: translation.languageTo,
      question: translation.solution,
      solution: translation.question,
      translationId: translation.translationId
    };
  }

  public defineOrder(definedOrder: string) {
    this.order = definedOrder;

  }

  public toggleAutohint() {
    this.autohintVal = !this.autohintVal;
    this.autohint.next(this.autohintVal);
  }

  ngOnDestroy(): void {
    if (this.autohintSubscription) {
      this.autohintSubscription.unsubscribe();
    }
  }
}

