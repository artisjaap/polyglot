import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {LessonService} from "../../../common/services/lesson.service";
import {ActivatedRoute} from "@angular/router";
import {LessonResponse} from "../../../common/services/response/lesson-response";
import {LessonTranslationPairResponse} from "../../../common/services/response/lesson-translation-pair-response";
import {CheckAnswerAndNextDto} from "../../../common/services/dto/check-answer-and-next-dto";
import {PracticeTranslationService} from "../../../common/services/practice-translation.service";
import {AnswerResponse} from "../../../common/services/response/answer-response";
import {ArrayUtilsService} from "../../../common/array-utils.service";
import {interval, Subject, Subscription} from "rxjs"

@Component({
  selector: 'pol-lesson-practice',
  templateUrl: './lesson-practice.component.html',
  styleUrls: ['./lesson-practice.component.scss']
})
export class LessonPracticeComponent implements OnInit {
  lessonForPractice : LessonResponse;

  translations:LessonTranslationPairResponse[] = [];
  currentWord:LessonTranslationPairResponse;
  previous: AnswerResponse;
  order:string = "RANDOM";
  private autohintVal: boolean = false;

  autohint:Subject<boolean> = new Subject<boolean>();

  @ViewChild("answerGiven") answerGiven: ElementRef;

  constructor(private route: ActivatedRoute,
              private lessonService:LessonService,
              private practiceTranslationService:PracticeTranslationService,
              private arrayUtilService:ArrayUtilsService) { }

  ngOnInit() {
    let lessonId = this.route.snapshot.params['lessonId'];


    this.lessonService.practiceLesson(lessonId)
      .subscribe(r => {
        this.lessonForPractice = r;

        this.preparePracticeForLesson(r);
        let subscription:Subscription = undefined;
        this.autohint.subscribe( value => {

          if(value){
            subscription = interval(1000).subscribe(x => this.updateWithNext());
          }else if(subscription){
            subscription.unsubscribe();
          }
        })

        // interval(1000)
          // .subscribe(x =>{
          //   this.updateWithNext();
          // })
          
      });
  }

  private updateWithNext(){
    let currentVal = this.answerGiven.nativeElement.value;

    while(this.currentWord.solution.indexOf(currentVal) != 0 && currentVal !== ""){
      currentVal = currentVal.substring(0, currentVal.length - 1);
    }

    if(currentVal.length < this.currentWord.solution.length){
      this.answerGiven.nativeElement.value = currentVal + this.currentWord.solution.substring(currentVal.length, currentVal.length+1);

    }else {
      this.checkAnswer(this.answerGiven.nativeElement);
    }



  }

  public checkAnswer(answerGiven:HTMLInputElement) {
    this.practiceTranslationService.checkAnswerAndNext(new CheckAnswerAndNextDto(
      this.currentWord.translationId,
      answerGiven.value,
      this.currentWord.isReverse?"REVERSE":"NORMAL",
      "NORMAL",
      this.lessonForPractice.id
    )).subscribe(r => {
      answerGiven.value = "";
      this.nextWord();
      this.previous = r.answerResponse;

    })
  }

  private nextWord() {
    if(this.translations.length > 0){
      this.newWord();
    }else {
      this.preparePracticeForLesson(this.lessonForPractice);
    }
  }

  private preparePracticeForLesson(r: LessonResponse) {
    let newArray = r.translations.slice();
    this.translations = this.arrayUtilService.shuffle(newArray);



    if(this.translations.length > 0){
      this.newWord();
    }
  }

  private newWord(){
    let theQuestion = this.translations.splice(0, 1)[0];
    if(this.order === 'NORMAL'){
      this.currentWord = theQuestion
    }else if(this.order === 'REVERSE'){
      this.currentWord = this.switchDirection(theQuestion);
    }else{
      if(Math.random()*2 > 1){
        this.currentWord = theQuestion;
      }
      else {
        this.currentWord = this.switchDirection(theQuestion);
      }
    }
  }

  private switchDirection(translation:LessonTranslationPairResponse): LessonTranslationPairResponse{
    return {
      isReverse:!translation.isReverse,
      languageFrom: translation.languageFrom,
      languageTo:translation.languageTo,
      question:translation.solution,
      solution:translation.question,
      translationId:translation.translationId};
  }

  /*

  private copyAsTranslation(translation:LessonTranslationPairResponse): LessonTranslationPairResponse{
    if(translation.isReverse){
      return this.switchDirection(translation);
    }
    return translation;
  }

  private copyAsReverseTranslation(translation:LessonTranslationPairResponse): LessonTranslationPairResponse{
    if(!translation.isReverse){
      return this.switchDirection(translation);
    }
    return translation;
  }

  private copyAsRandomOrderTranslation(translation:LessonTranslationPairResponse): LessonTranslationPairResponse{
    if(Math.floor(Math.random()*2) % 2 === 0){
      return this.copyAsTranslation(translation);
    }
    return this.copyAsReverseTranslation(translation);

  }






  private nextWord() {
    if(this.translations.length > 0){
      this.currentWord = this.translations.splice(0, 1)[0];
    }else {
      this.preparePracticeForLesson(this.lessonForPractice);
    }
  }

  public checkAnswer(answerGiven:HTMLInputElement) {
    this.practiceTranslationService.checkAnswerAndNext(new CheckAnswerAndNextDto(
      this.currentWord.translationId,
      answerGiven.value,
      this.currentWord.isReverse?"REVERSE":"NORMAL",
      "NORMAL",
      this.lessonForPractice.id
    )).subscribe(r => {
      answerGiven.value = "";
      this.nextWord();
      this.previous = r.answerResponse;

    })
  }*/

  public defineOrder(definedOrder:string){
    this.order = definedOrder;
    console.log(this.order);
  }

  public toggleAutohint(){
    this.autohintVal = !this.autohintVal;
    this.autohint.next(this.autohintVal);
  }
}
