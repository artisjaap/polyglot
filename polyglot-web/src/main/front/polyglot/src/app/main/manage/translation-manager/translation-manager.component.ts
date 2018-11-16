import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ManagerTranslationService} from "../../common/services/manager-translation.service";
import {LanguagePairDTO} from "../../common/services/dto/language-pair-dto";
import {PracticeTranslationService} from "../../common/services/practice-translation.service";
import {PagedResponse} from "../../common/services/request/paged-response";
import {PracticeWordResponse} from "../../common/services/response/practice-word-response";
import {PageNavigation} from "../../common/components/page-navigation/page-navigation";
import {Observable} from "rxjs";

@Component({
  selector: 'pol-translation-manager',
  templateUrl: './translation-manager.component.html',
  styleUrls: ['./translation-manager.component.scss']
})
export class TranslationManagerComponent implements OnInit {
  languagePair: LanguagePairDTO;
  filteredTranslations: PagedResponse<PracticeWordResponse>;
  @ViewChild("search") search:ElementRef;
  pageNav: PageNavigationImpl;

  constructor(private route: ActivatedRoute,
              private translationService: ManagerTranslationService,
              private practiceTranslationService:PracticeTranslationService) {
  }

  ngOnInit() {
    let languagePairId = this.route.snapshot.params['pairId'];
    this.pageNav = new PageNavigationImpl(0, 10, this.practiceTranslationService, languagePairId);

    this.translationService.languagePairWithId(languagePairId).subscribe(response => {
      this.languagePair = response;
    });


    // this.practiceTranslationService.allWordsForLanguagePair("", languagePairId, 0, 10)
    //   .subscribe(result => this.filteredTranslations = result);

    this.translationService.event.subscribe(t => {
      t.translations.forEach(trans => {
        //FIXME need to find the new PracticeWordResponse

        //this.filteredTranslations.data.push({question:trans.languageA, answer:trans.languageB})

      })
    });

    this.pageNav.event.subscribe(r => {
      this.filteredTranslations = r
    });
  }


  updateTranslationToStatusKnown(translationId:string, index:number) {
    this.practiceTranslationService.updateStatusOfTranslationToKnown(translationId)
      .subscribe(r => this.filteredTranslations.data[index] = r);
  }

  updateTranslationToStatusHold(translationId:string, index:number) {
    this.practiceTranslationService.updateStatusOfTranslationToHold(translationId)
      .subscribe(r => this.filteredTranslations.data[index] = r);
  }
  updateTranslationToStatusInProgress(translationId:string, index:number) {
    this.practiceTranslationService.updateStatusOfTranslationToInProgress(translationId)
      .subscribe(r => this.filteredTranslations.data[index] = r);
  }
  updateTranslationToStatusNew(translationId:string, index:number) {
    this.practiceTranslationService.updateStatusOfTranslationToNew(translationId)
      .subscribe(r => this.filteredTranslations.data[index] = r);
  }


  getControl() : PageNavigation<PracticeWordResponse> {
    return this.pageNav;
  }

}

class PageNavigationImpl extends PageNavigation<PracticeWordResponse> {

  constructor(page:number, pageSize:number, private practiceTranslationService:PracticeTranslationService, private languagePairId:string){
    super(page, pageSize);
  }

  doSearch(page: number, pageSize: number, text: string): Observable<PagedResponse<PracticeWordResponse>> {
    return this.practiceTranslationService.allWordsForLanguagePair(text, this.languagePairId, page, pageSize);

  }

}
