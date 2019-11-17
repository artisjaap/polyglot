import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";
import {Translation} from "../model/translation";

@Injectable()
export class TranslationService {

  constructor() {

  }

  public loadLatestWordsForLanguagePair(languagePairId: string): Observable<Translation[]> {
    return of([{id:'a1', languageA: 'A', languageB: 'B'}]);
  }

}
