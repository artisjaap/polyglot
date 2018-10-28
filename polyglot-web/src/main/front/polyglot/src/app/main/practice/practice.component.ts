import { Component, OnInit } from '@angular/core';
import {ManagerTranslationService} from "../common/services/manager-translation.service";
import {TranslationPracticeDTO} from "../common/services/dto/translation-practice-dto";
import {PracticeWordResponse} from "../common/services/response/practice-word-response";

@Component({
  selector: 'pol-practice',
  templateUrl: './practice.component.html',
  styleUrls: ['./practice.component.css']
})
export class PracticeComponent implements OnInit {
  practiceList:PracticeWordResponse[] = [];


  constructor(private translationService:ManagerTranslationService) {
    //listen to practice word created event and add to practice list

  }

  ngOnInit() {
    this.translationService.currentListToPracticeForLanguage("test")
      .subscribe(result => {this.practiceList = result;});
  }

}

