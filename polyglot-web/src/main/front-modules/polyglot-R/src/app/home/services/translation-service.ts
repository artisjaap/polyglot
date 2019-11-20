import {Inject, Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Translation} from "../model/translation";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable()
export class TranslationService {

  constructor(private httpClient: HttpClient, @Inject('API_URL') private apiurl: string) {

  }

  public loadLatestWordsForLanguagePair(languagePairId: string): Observable<Translation[]> {
    let params = new HttpParams().set("languagePairId", languagePairId);
    return this.httpClient.get<Translation[]>(this.apiurl + `api/translations`, {params: params});
  }

}

