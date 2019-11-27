import {Inject, Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {NewTranslationForLessonRequest} from "../model/new-translation-for-lesson-request";
import {TranslationForLessonResponse} from "../model/translation-for-lesson-response";
import {UpdateTranslationForLessonRequest} from "../model/update-translation-for-lesson-request";

@Injectable()
export class TranslationService {

  constructor(private httpClient: HttpClient, @Inject('API_URL') private apiurl: string) {

  }

  // public loadLatestWordsForLanguagePair(languagePairId: string): Observable<Translation[]> {
  //   let params = new HttpParams().set("languagePairId", languagePairId);
  //   return this.httpClient.get<Translation[]>(this.apiurl + `api/translations`, {params: params});
  // }

  public creaeNewTranslation(newTranslationForLessonRequest: NewTranslationForLessonRequest): Observable<TranslationForLessonResponse> {
    return this.httpClient.put<TranslationForLessonResponse>(this.apiurl + `api/translation`, newTranslationForLessonRequest);
  }

  public updateTranslation(newTranslationForLessonRequest: UpdateTranslationForLessonRequest): Observable<TranslationForLessonResponse> {
    return this.httpClient.patch<TranslationForLessonResponse>(this.apiurl + `api/translation`, newTranslationForLessonRequest);
  }

  public delete(translationId: string): Observable<TranslationForLessonResponse> {
    return this.httpClient.delete<TranslationForLessonResponse>(this.apiurl + `api/translation/${translationId}`);
  }

}

