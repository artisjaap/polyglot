import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {LessonHeaderResponse} from '../model/lesson-header-response';
import {LanguagePairResponse} from '../model/language-pair-response';

@Injectable()
export class LanguagePairService {

  constructor(private httpClient: HttpClient, @Inject('API_URL') private apiurl: string) {

  }

  public allLanguagePairs(): Observable<LanguagePairResponse[]> {
    return this.httpClient.get<LanguagePairResponse[]>(this.apiurl + `api/language-pairs`);
  }

  deleteLanguaegPair(languagePair: LanguagePairResponse ): Observable<LanguagePairResponse> {
    return this.httpClient.delete<LanguagePairResponse>(this.apiurl + `api/language-pair/${languagePair.id}`);
  }

  creaetLanguaegPair(languagePair: LanguagePairResponse) {
    return this.httpClient.post<LanguagePairResponse>(this.apiurl + `api/language-pair`, languagePair);
  }
}
