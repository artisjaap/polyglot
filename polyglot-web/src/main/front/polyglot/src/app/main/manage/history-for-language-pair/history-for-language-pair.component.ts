import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {LanguagePairJournalService} from "../../common/services/language-pair-journal.service";
import {ActivatedRoute} from "@angular/router";
import {LanguagePracticeJournalResponse} from "../../common/services/response/language-practice-journal-response";
import * as FileSaver from 'file-saver';

@Component({
  selector: 'pol-history-for-language-pair',
  templateUrl: './history-for-language-pair.component.html',
  styleUrls: ['./history-for-language-pair.component.scss']
})
export class HistoryForLanguagePairComponent implements OnInit {

  languagePracticeJournal: LanguagePracticeJournalResponse;
  @ViewChild("from") from:ElementRef;
  @ViewChild("until") until:ElementRef;

  constructor(private route: ActivatedRoute,
              private languagePairJournalService:LanguagePairJournalService) { }

  ngOnInit() {
    let languagePairId = this.route.snapshot.params['pairId'];
    let date = new Date();
    let month = date.getFullYear() + "-" + ((date.getMonth()+1 < 10) ? "0" : "") + (date.getMonth()+1) + "-" + ((date.getDate() < 10)? "0" : "") + date.getDate();

    this.from.nativeElement.value = month;
    this.until.nativeElement.value = month;

    this.languagePairJournalService.findAllJournalForFilter(languagePairId, null, month, month)
      .subscribe(r => this.languagePracticeJournal = r);


  }

  download() {
    let languagePairId = this.route.snapshot.params['pairId'];

    this.languagePairJournalService.findAllJournalForFilterAsPdf(languagePairId, null, this.from.nativeElement.value, this.until.nativeElement.value)
      .subscribe(r => {
        const blob = new Blob([r.body], { type: 'application/pdf' });
        FileSaver.saveAs(blob, "report.pdf");

        //open PDF in new tab
        //const blob = new Blob([r.body], { type: 'application/pdf' });
        // const url= window.URL.createObjectURL(blob);
        // window.open(url);

      });

  }

  search(){
    let languagePairId = this.route.snapshot.params['pairId'];
    this.languagePairJournalService.findAllJournalForFilter(languagePairId, null, this.from.nativeElement.value, this.until.nativeElement.value)
      .subscribe(r => this.languagePracticeJournal = r);

  }

}
