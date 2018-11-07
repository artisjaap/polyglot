import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ManagerTranslationService} from "../../common/services/manager-translation.service";
import {LanguagePairDTO} from "../../common/services/dto/language-pair-dto";
import {PracticeTranslationService} from "../../common/services/practice-translation.service";
import {PagedResponse} from "../../common/services/request/paged-response";
import {TranslationResponse} from "../../common/services/request/translation-response";
import {PracticeWordResponse} from "../../common/services/response/practice-word-response";

@Component({
  selector: 'pol-translation-manager',
  templateUrl: './translation-manager.component.html',
  styleUrls: ['./translation-manager.component.scss']
})
export class TranslationManagerComponent implements OnInit {
  languagePair: LanguagePairDTO;
  filteredTranslations: PagedResponse<PracticeWordResponse>;
  @ViewChild("search") search:ElementRef;

  constructor(private route: ActivatedRoute,
              private translationService: ManagerTranslationService,
              private practiceTranslationService:PracticeTranslationService) {
  }

  ngOnInit() {
    let languagePairId = this.route.snapshot.params['pairId'];
    this.translationService.languagePairWithId(languagePairId).subscribe(response => {
      this.languagePair = response;
    });

    this.practiceTranslationService.allWordsForLanguagePair("", languagePairId, 0, 10)
      .subscribe(result => this.filteredTranslations = result);

    this.translationService.event.subscribe(t => {
      t.translations.forEach(trans => {
        //FIXME need to find the new PracticeWordResponse

        //this.filteredTranslations.data.push({question:trans.languageA, answer:trans.languageB})

      })
    })
  }

  firstPage(){
    this.practiceTranslationService.allWordsForLanguagePair("", this.languagePair.id, 0, 10)
      .subscribe(result => this.filteredTranslations = result);

  }

  lastPage(){
    this.practiceTranslationService.allWordsForLanguagePair("", this.languagePair.id, this.filteredTranslations.numberOfPages-1, 10)
      .subscribe(result => this.filteredTranslations = result);
  }

  previousPage(){
    this.practiceTranslationService.allWordsForLanguagePair("", this.languagePair.id, Math.max(0, this.filteredTranslations.page-1), 10)
      .subscribe(result => this.filteredTranslations = result);

  }

  nextPage(){
    this.practiceTranslationService.allWordsForLanguagePair("", this.languagePair.id, Math.min(this.filteredTranslations.numberOfPages-1, this.filteredTranslations.page+1), 10)
      .subscribe(result => this.filteredTranslations = result);

  }

  doSearch(){
    this.practiceTranslationService.allWordsForLanguagePair(this.search.nativeElement.value, this.languagePair.id, 0, 10)
      .subscribe(result => this.filteredTranslations = result);
  }

}
