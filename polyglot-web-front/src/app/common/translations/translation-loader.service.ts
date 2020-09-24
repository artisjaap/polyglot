import {Inject, Injectable} from '@angular/core';
import {AppTranslations} from './app-translations';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TranslationLoaderService {

  constructor(private httpClient: HttpClient,  @Inject('API_URL') private apiurl: string) { }

  public forLanguage(language: string): Observable<AppTranslations>{
    const translations = {key: 'value'};
    return this.httpClient.get<AppTranslations>(this.apiurl + `api/app-translations/${language}/polyglot-front`);
    // return of(translations);
  }
}
