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

    this.languagePairJournalService.findJournalForLanguageInYearMonth(languagePairId, month)
      .subscribe(r => this.languagePracticeJournal = r);

    // this.languagePairJournalService.findJournalForLanguagePairOnDate(languagePairId, "2018-11-15")
    //   .subscribe(r => {});
    //
    // this.languagePairJournalService.findJournalForLesson("5be74e3c9b72fa2e88516e8e")
    //   .subscribe(r => {});
    //
    // this.languagePairJournalService.findJournalForLessonInYearMonth("5be74e3c9b72fa2e88516e8e", month)
    //   .subscribe(r => {});
    //
    // this.languagePairJournalService.findJournalForLanguagePairOnDate(languagePairId, "2018-11-15")
    //   .subscribe(r => {});
    //
    // this.languagePairJournalService.findJournalForLessonOnDate("5be74e3c9b72fa2e88516e8e", "2018-11-15")
    //   .subscribe(r => {});

  }

}
