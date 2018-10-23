import {Component, Input, OnInit} from '@angular/core';
import {ManagerTranslationService} from "../../common/services/manager-translation.service";

@Component({
  selector: 'pol-translation-simple-add',
  templateUrl: './translation-simple-add.component.html',
  styleUrls: ['./translation-simple-add.component.css']
})
export class TranslationSimpleAddComponent implements OnInit {

  @Input() translationPairId:string;

  constructor(private translationService:ManagerTranslationService) { }

  ngOnInit() {

  }

  addNewTranslation(languageFrom:HTMLInputElement, languageTo:HTMLInputElement){
    this.translationService.addNewWord(this.translationPairId, languageFrom.value, languageTo.value)
      .subscribe(r =>{});
  }


}
