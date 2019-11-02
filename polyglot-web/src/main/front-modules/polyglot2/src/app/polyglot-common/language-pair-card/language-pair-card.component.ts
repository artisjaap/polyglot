import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {LanguagePairCard} from "./language-pair-card";

@Component({
  selector: 'app-language-pair-card',
  templateUrl: './language-pair-card.component.html',
  styleUrls: ['./language-pair-card.component.scss']
})
export class LanguagePairCardComponent implements OnInit {

  @Input()
  languagePairCard:LanguagePairCard;

  @Input()
  state:string = "readonly";

  @Output()
  openLanguage = new EventEmitter();

  @Output()
  createLanguagePair = new EventEmitter();

  @Output()
  updateLanguagePair = new EventEmitter();

  @ViewChild("languageFromUpdate", {static:true})
  languageFromUpdate: ElementRef;

  @ViewChild("languageToUpdate", {static:true})
  languageToUpdate: ElementRef;

  constructor() { }

  ngOnInit() {
  }

  doOpenLanguage(){
    this.openLanguage.emit(this.languagePairCard);
  }

  doCreateLanguagePair(languageFrom:HTMLInputElement, languageTo:HTMLInputElement){
    let languagePairCard:LanguagePairCard = {id:"", languageFrom: languageFrom.value, languageTo:languageTo.value}
    languageFrom.value = '';
    languageTo.value = '';
    this.createLanguagePair.emit(languagePairCard);
  }

  toUpdate(languageFrom:HTMLInputElement, languageTo:HTMLInputElement) {
    this.state = 'update';
    // languageFrom.value = this.languagePairCard.languageFrom;
    // languageTo.value = this.languagePairCard.languageTo;
  }

  doUpdateLanguagePair(languageFromUpdate: HTMLInputElement, languageToUpdate: HTMLInputElement) {
    let languagePairCard:LanguagePairCard = {id:'',languageFrom: languageFromUpdate.value, languageTo:languageToUpdate.value}
    this.updateLanguagePair.emit(languagePairCard);
    this.state = "readonly";
  }
}
