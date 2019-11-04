import {Component, Input, OnInit} from '@angular/core';
import {WordSelector} from "./word-selector";
import {WordSelectorService} from "../word-selector.service";
import {WordPair} from "../../core/model/word-pair";
import {Observable} from "rxjs";

@Component({
  selector: 'app-word-selector',
  templateUrl: './word-selector.component.html',
  styleUrls: ['./word-selector.component.scss']
})
export class WordSelectorComponent implements OnInit {

  @Input()
  wordSelectorData: WordSelector;

  currentWords:Observable<WordPair[]>;


  constructor(private wordSelectorService:WordSelectorService) {
  }

  ngOnInit() {

    this.currentWords = this.wordSelectorService.findWordsBy({languagePairId:this.wordSelectorData.languagePairId});
  }

}
