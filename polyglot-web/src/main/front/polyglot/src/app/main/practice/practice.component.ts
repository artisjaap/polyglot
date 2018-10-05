import { Component, OnInit } from '@angular/core';
import {TranslationManagerService} from "../common/services/translation-manager.service";
import {TranslationPracticeDTO} from "../common/services/dto/translation-practice-dto";

@Component({
  selector: 'pol-practice',
  templateUrl: './practice.component.html',
  styleUrls: ['./practice.component.css']
})
export class PracticeComponent implements OnInit {
  practiceList:TranslationPracticeDTO[] = [];


  constructor(private translationService:TranslationManagerService) {
    //listen to practice word created event and add to practice list

  }

  ngOnInit() {
    this.practiceList = this.translationService.currentListToPracticeForLanguage("test");
  }

}

