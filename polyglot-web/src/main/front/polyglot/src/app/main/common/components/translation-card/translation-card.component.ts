import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'pol-translation-card',
  templateUrl: './translation-card.component.html',
  styleUrls: ['./translation-card.component.css']
})
export class TranslationCardComponent implements OnInit {
  @Input() element : {languageFrom:string, languageTo:string };

  constructor() { }

  ngOnInit() {
  }

}
