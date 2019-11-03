import {Component, Input, OnInit} from '@angular/core';
import {WordPairItem} from "./word-pair-item";

@Component({
  selector: 'app-word-pair-list',
  templateUrl: './word-pair-list.component.html',
  styleUrls: ['./word-pair-list.component.scss']
})
export class WordPairListComponent implements OnInit {

  @Input()
  wordPairs: WordPairItem[];

  constructor() { }

  ngOnInit() {
  }

}
