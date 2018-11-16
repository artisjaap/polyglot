import { Component, OnInit } from '@angular/core';
import {ManagerTranslationService} from "../../../common/services/manager-translation.service";
import {LanguagePairResponse} from "../../../common/services/response/language-pair-response";

@Component({
  selector: 'pol-lesson-language-pair',
  templateUrl: './lesson-language-pair.component.html',
  styleUrls: ['./lesson-language-pair.component.scss']
})
export class LessonLanguagePairComponent implements OnInit {

  languagePairs:LanguagePairResponse[] = [];

  constructor(private translationService:ManagerTranslationService,) { }

  ngOnInit() {
    this.translationService.allLanguagePairs().subscribe(result => this.languagePairs = result);
  }

}
