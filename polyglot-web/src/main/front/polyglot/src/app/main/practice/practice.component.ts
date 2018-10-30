import { Component, OnInit } from '@angular/core';
import {ManagerTranslationService} from "../common/services/manager-translation.service";
import {TranslationPracticeDTO} from "../common/services/dto/translation-practice-dto";
import {PracticeWordResponse} from "../common/services/response/practice-word-response";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'pol-practice',
  templateUrl: './practice.component.html',
  styleUrls: ['./practice.component.scss']
})
export class PracticeComponent implements OnInit {
  practiceList:PracticeWordResponse[] = [];


  constructor(private translationService:ManagerTranslationService, private route: ActivatedRoute) {
    //listen to practice word created event and add to practice list

  }

  ngOnInit() {
    let languagePairId = this.route.snapshot.params['pairId'];

    this.translationService.currentListToPracticeForLanguage(languagePairId)
      .subscribe(result => {this.practiceList = result;});
  }

}

