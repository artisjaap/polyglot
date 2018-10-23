import { Component, OnInit } from '@angular/core';
import {ManagerTranslationService} from "../common/services/manager-translation.service";
import {TranslationPracticeDTO} from "../common/services/dto/translation-practice-dto";

@Component({
  selector: 'pol-practice',
  templateUrl: './practice.component.html',
  styleUrls: ['./practice.component.css']
})
export class PracticeComponent implements OnInit {
  practiceList:TranslationPracticeDTO[] = [];


  constructor(private translationService:ManagerTranslationService) {
    //listen to practice word created event and add to practice list

  }

  ngOnInit() {
    this.practiceList = this.translationService.currentListToPracticeForLanguage("test");
  }

}

