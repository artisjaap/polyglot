import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {WordSelector} from "./word-selector";
import {WordSelectorService} from "../word-selector.service";
import {WordPair} from "../../core/model/word-pair";
import {Observable, of} from "rxjs";
import {IPaging} from "../table-paging/ipaging";
import {I18NHtmlParser} from "@angular/compiler";
import {PageNavigation} from "../table-paging/page-navigation";
import {PagedResponse} from "../../../../../polyglot/src/app/core/services/request/paged-response";
import {WordSelectorFilter} from "../word-selector-filter";

@Component({
  selector: 'app-word-selector',
  templateUrl: './word-selector.component.html',
  styleUrls: ['./word-selector.component.scss']
})
export class WordSelectorComponent implements OnInit, OnDestroy {

  @Input()
  wordSelectorData: WordSelector;

  currentWords:Observable<WordPair[]>;

  paging: PageNavigation<WordPair,WordSelectorFilter>;


  constructor(private wordSelectorService:WordSelectorService) {
  }

  ngOnInit() {

    this.currentWords = this.wordSelectorService.findWordsBy({languagePairId:this.wordSelectorData.languagePairId});
  }

  pagingControls(): IPaging {
    if(!this.paging){

      this.paging = new PageNavigationImpl(1, 10, {languagePairId: this.wordSelectorData.languagePairId}, this.wordSelectorService);
      this.paging.event.subscribe(result => {
        console.log("updated", result);
        this.currentWords = of(result.data);
      });
    }
    return this.paging;
  }

  ngOnDestroy(): void {
    this.paging.ngOnDestroy();
  }
}

class PageNavigationImpl extends PageNavigation<WordPair,WordSelectorFilter>{

  constructor( currentPage:number,  pageSize:number, filters:WordSelectorFilter, private wordSelectorService:WordSelectorService){
    super(currentPage, pageSize, filters);
  }

  doSearch(page: number, pageSize: number, filters: WordSelectorFilter): Observable<PagedResponse<WordPair>> {
    return this.wordSelectorService.findWordsByFilterPaged(filters);
  }

}
