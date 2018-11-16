import { Component, OnInit } from '@angular/core';
import {LessonService} from "../../../common/services/lesson.service";
import {ActivatedRoute} from "@angular/router";
import {LessonResponse} from "../../../common/services/response/lesson-response";
import {LessonTranslationPairResponse} from "../../../common/services/response/lesson-translation-pair-response";
import {CheckAnswerAndNextDto} from "../../../common/services/dto/check-answer-and-next-dto";
import {PracticeTranslationService} from "../../../common/services/practice-translation.service";
import {AnswerResponse} from "../../../common/services/response/answer-response";

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


  constructor(private route: ActivatedRoute,
              private lessonService:LessonService,
              private practiceTranslationService:PracticeTranslationService) { }

  ngOnInit() {
    let lessonId = this.route.snapshot.params['lessonId'];


    this.lessonService.practiceLesson(lessonId)
      .subscribe(r => {
        this.preparePracticeForLesson(r);
        return this.lessonForPractice = r;
      });
  }

  private preparePracticeForLesson(r: LessonResponse) {
    r.translations.forEach(r => {
      let translation:LessonTranslationPairResponse = null;
      if(this.order === "NORMAL"){
        translation = this.copyAsTranslation(r);
      }else if(this.order === "REVERSE") {
        translation = this.copyAsReverseTranslation(r);
      }
      else {
        translation = this.copyAsRandomOrderTranslation(r);
      }
      this.translations.push(translation);
    });

    for(let i = 0; i < this.translations.length / 2 + 1; i++){
      let index = Math.floor(Math.random() * this.translations.length);

      this.translations.push(this.translations.splice(index, 1)[0]);
    }

    if(this.translations.length > 0){
      this.currentWord = this.translations.splice(0, 1)[0];
    }
  }

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

  private switchDirection(translation:LessonTranslationPairResponse): LessonTranslationPairResponse{
    return {
      isReverse:!translation.isReverse,
      languageFrom: translation.languageFrom,
      languageTo:translation.languageTo,
      question:translation.solution,
      solution:translation.question,
      translationId:translation.translationId};
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
  }


}
