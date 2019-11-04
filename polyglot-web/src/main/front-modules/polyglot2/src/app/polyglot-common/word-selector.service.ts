import { Injectable } from '@angular/core';
import {LoginService} from "./login.service";
import {AuthenticatedBaseServiceService} from "./authenticated-base-service.service";
import {WordSelectorFilter} from "./word-selector-filter";
import {Observable, of} from "rxjs";
import {WordPair} from "../core/model/word-pair";

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

}
