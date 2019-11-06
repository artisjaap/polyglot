import { Injectable } from '@angular/core';
import {LoginService} from "./login.service";
import {AuthenticatedBaseServiceService} from "./authenticated-base-service.service";
import {WordSelectorFilter} from "./word-selector-filter";
import {Observable, of} from "rxjs";
import {WordPair} from "../core/model/word-pair";
import {PagedResponse} from "../../../../polyglot/src/app/core/services/request/paged-response";

@Injectable({
  providedIn: 'root'
})
export class WordSelectorService extends AuthenticatedBaseServiceService {

  public findWordsBy(wordSelectorFilter: WordSelectorFilter):Observable<WordPair[]> {
    console.log("find words for", wordSelectorFilter);
    let result: WordPair[] = [];
    result.push({id: "1", from: "from", to: "to"}); //FIXME remove dummy
    return of(result);

  }

  public findWordsByFilterPaged(wordSelectorFilter:WordSelectorFilter): Observable<PagedResponse<WordPair>>{
    console.log("filter paged");
    let result:PagedResponse<WordPair> = new class implements PagedResponse<WordPair> {
      data: WordPair[];
      lastPage: boolean;
      numberOfPages: number;
      page: number;
      pageSize: number;

      constructor(){
        this.data = [];
        this.data.push({id: "1", from: "from@", to: "to2"}); //FIXME remove dummy
        this.lastPage = true;
        this.numberOfPages = 1;
        this.page = 1;
        this.pageSize = 10;
      }
    }

    return of(result);
  }

}
