import { Component, OnInit } from '@angular/core';
import {PracticeTranslationService} from "../../common/services/practice-translation.service";
import {ActivatedRoute} from "@angular/router";
import {PracticeWordResponse} from "../../common/services/response/practice-word-response";
import {CheckAnswerAndNextDto} from "../../common/services/dto/check-answer-and-next-dto";
import {AnswerResponse} from "../../common/services/response/answer-response";

@Component({
  selector: 'pol-practice-continuous',
  templateUrl: './practice-continuous.component.html',
  styleUrls: ['./practice-continuous.component.scss']
})
export class PracticeContinuousComponent implements OnInit {

  currentWord:PracticeWordResponse;
  previous: AnswerResponse;

  constructor(private practiceTranslationService:PracticeTranslationService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    let languagePairId = this.route.snapshot.params['pairId'];

    this.practiceTranslationService.nextPracticeWord(languagePairId, "NORMAL")
      .subscribe(r => {
        this.currentWord = r;
      })
  }

  checkAnswer(answerGiven: HTMLInputElement) {
    this.practiceTranslationService.checkAnswerAndNext(new CheckAnswerAndNextDto(
      this.currentWord.translationId,
      answerGiven.value,
      "NORMAL",
      "NORMAL",
      null
    )).subscribe(r => {
      answerGiven.value = "";
      this.currentWord = r.practiceWordResponse;
      this.previous = r.answerResponse;
    })

  }
}
