import { Component, OnInit } from '@angular/core';
import {LanguagePairCard} from "../../polyglot-common/language-pair-card/language-pair-card";
import {Router} from "@angular/router";

@Component({
  selector: 'app-languages',
  templateUrl: './languages.component.html',
  styleUrls: ['./languages.component.scss']
})
export class LanguagesComponent implements OnInit {
  languages:LanguagePairCard[] = [];

  constructor(private router:Router) { }

  ngOnInit() {
   let languagePair:LanguagePairCard = {id: 'a', languageFrom:'from', languageTo:'to'};
   let languagePair2:LanguagePairCard = {id: 'b', languageFrom:'from', languageTo:'to'};
    this.languages.push(languagePair);
    this.languages.push(languagePair2);
  }

  public createLanguagePair(newLanguagePair:LanguagePairCard) {
    this.languages.push(newLanguagePair);


  }

  navigateToLanguagePair(languagePairCard: LanguagePairCard) {
    this.router.navigate(['home','student','languages',languagePairCard.id]);
  }
}
