import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ManagerTranslationService} from "../../common/services/manager-translation.service";
import {LanguagePairDTO} from "../../common/services/dto/language-pair-dto";
import {TranslationFilter} from "../../common/services/dto/translation-filter";
import {PaginationOptions} from "../../common/services/dto/pagination-options";
import {TranslationPracticeDTO} from "../../common/services/dto/translation-practice-dto";
import {PracticeTranslationService} from "../../common/services/practice-translation.service";
import {PagedResponse} from "../../common/services/request/paged-response";
import {TranslationResponse} from "../../common/services/request/translation-response";

@Component({
  selector: 'pol-translation-manager',
  templateUrl: './translation-manager.component.html',
  styleUrls: ['./translation-manager.component.css']
})
export class TranslationManagerComponent implements OnInit {
  languagePair: LanguagePairDTO;
  filteredTranslations: PagedResponse<TranslationResponse>;

  constructor(private route: ActivatedRoute,
              private translationService: ManagerTranslationService,
              private practiceTranslationService:PracticeTranslationService) {
  }

  ngOnInit() {
    let languagePairId = this.route.snapshot.params['pairId'];
    this.translationService.languagePairWithId(languagePairId).subscribe(response => {
      this.languagePair = response;
    });

    this.practiceTranslationService.allWordsForLanguagePair("", languagePairId)
      .subscribe(result => this.filteredTranslations = result);

    this.translationService.event.subscribe(t => {
      t.translations.forEach(trans => {
        this.filteredTranslations.data.push({languageA:trans.languageA, languageB:trans.languageB,languagePairId:t.languagePairId})

      })
    })

  }

}
