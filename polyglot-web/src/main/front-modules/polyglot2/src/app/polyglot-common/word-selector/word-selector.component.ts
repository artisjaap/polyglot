import {Component, Input, OnInit} from '@angular/core';
import {WordSelector} from "./word-selector";
import {WordSelectorService} from "../word-selector.service";

@Component({
  selector: 'app-word-selector',
  templateUrl: './word-selector.component.html',
  styleUrls: ['./word-selector.component.scss']
})
export class WordSelectorComponent implements OnInit {

  @Input()
  wordSelectorData: WordSelector;

  constructor(wordSelectorService:WordSelectorService) {
  }

  ngOnInit() {
  }

}
