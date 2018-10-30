import {Component, Input, OnInit} from '@angular/core';
import {PracticeWordResponse} from "../../services/response/practice-word-response";

@Component({
  selector: 'pol-translation-card',
  templateUrl: './translation-card.component.html',
  styleUrls: ['./translation-card.component.scss']
})
export class TranslationCardComponent implements OnInit {
  @Input() element : PracticeWordResponse;

  showFront: boolean = true;

  constructor() { }

  ngOnInit() {
  }

  turn() {
    this.showFront = !this.showFront;
  }
}
