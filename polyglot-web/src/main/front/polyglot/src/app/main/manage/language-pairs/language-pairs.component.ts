import { Component, OnInit } from '@angular/core';
import {ManagerTranslationService} from "../../common/services/manager-translation.service";
import {LogService} from "../../common/services/log.service";
import {LanguagePairDTO} from "../../common/services/dto/language-pair-dto";
import {LanguagePairResponse} from "../../common/services/response/language-pair-response";

@Component({
  selector: 'pol-language-pairs',
  templateUrl: './language-pairs.component.html',
  styleUrls: ['./language-pairs.component.scss']
})
export class LanguagePairsComponent implements OnInit {
  languagePairs:LanguagePairResponse[] = [];


  constructor(private translationService:ManagerTranslationService,
              private logger:LogService) {
    // setTimeout(() => {this.allow =true;}, 2000);
  }

  ngOnInit() {
    this.translationService.allLanguagePairs().subscribe(result => this.languagePairs = result);
  }

  createLanguagePair(languageFrom:HTMLInputElement, languageTo:HTMLInputElement) {
    this.logger.logInfo("push new language pair");
    this.translationService.createNewLanguagePair(new LanguagePairDTO(languageFrom.value, languageTo.value, null))
      .subscribe(result => this.languagePairs.push(result));

  }



}
