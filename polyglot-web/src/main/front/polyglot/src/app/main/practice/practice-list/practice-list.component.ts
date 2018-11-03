import { Component, OnInit } from '@angular/core';
import {ManagerTranslationService} from "../../common/services/manager-translation.service";
import {LogService} from "../../common/services/log.service";
import {LanguagePairResponse} from "../../common/services/response/language-pair-response";

@Component({
  selector: 'pol-practice-list',
  templateUrl: './practice-list.component.html',
  styleUrls: ['./practice-list.component.scss']
})
export class PracticeListComponent implements OnInit {
  languagePairs:LanguagePairResponse[] = [];


  constructor(private translationService:ManagerTranslationService,
              private logger:LogService) {
    // setTimeout(() => {this.allow =true;}, 2000);
  }

  ngOnInit() {
    this.translationService.allLanugagePairs().subscribe(result => this.languagePairs = result);
  }
}
