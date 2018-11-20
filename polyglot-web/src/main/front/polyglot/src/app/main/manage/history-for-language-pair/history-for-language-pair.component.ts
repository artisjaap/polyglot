import { Component, OnInit } from '@angular/core';
import {LanguagePairJournalService} from "../../common/services/language-pair-journal.service";
import {ActivatedRoute} from "@angular/router";
import {LanguagePracticeJournalResponse} from "../../common/services/response/language-practice-journal-response";

@Component({
  selector: 'pol-history-for-language-pair',
  templateUrl: './history-for-language-pair.component.html',
  styleUrls: ['./history-for-language-pair.component.scss']
})
export class HistoryForLanguagePairComponent implements OnInit {

  languagePracticeJournal: LanguagePracticeJournalResponse;

  constructor(private route: ActivatedRoute,
              private languagePairJournalService:LanguagePairJournalService) { }

  ngOnInit() {
    let languagePairId = this.route.snapshot.params['pairId'];
    let date = new Date();
    let month = date.getFullYear() + "-" + ((date.getMonth()+1 < 10) ? "0" : "") + (date.getMonth()+1);

    this.languagePairJournalService.findAllJournalForFilter(languagePairId, null, "2018-11-01", "2018-11-30")
      .subscribe(r => this.languagePracticeJournal = r);


  }

}
