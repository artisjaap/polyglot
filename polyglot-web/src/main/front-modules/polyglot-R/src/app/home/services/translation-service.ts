import {Inject, Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpEvent, HttpParams} from '@angular/common/http';
import {NewTranslationForLessonRequest} from '../model/new-translation-for-lesson-request';
import {TranslationForLessonResponse} from '../model/translation-for-lesson-response';
import {UpdateTranslationForLessonRequest} from '../model/update-translation-for-lesson-request';
import {UploadService} from './upload.service';
import {TranslationsLoadedByFileResponse} from '../model/translations-loaded-by-file-response';
import {FileUpload} from '../model/file-upload';
import {LessonHeaderResponse} from '../model/lesson-header-response';

@Injectable()
export class TranslationService {

  constructor(private httpClient: HttpClient,
              private uploadService: UploadService) {

  }

  // public loadLatestWordsForLanguagePair(languagePairId: string): Observable<Translation[]> {
  //   let params = new HttpParams().set("languagePairId", languagePairId);
  //   return this.httpClient.get<Translation[]>( `api/translations`, {params: params});
  // }

  public createNewTranslation(newTranslationForLessonRequest: NewTranslationForLessonRequest): Observable<TranslationForLessonResponse> {
    return this.httpClient.put<TranslationForLessonResponse>( `api/translation`, newTranslationForLessonRequest);
  }

  public updateTranslation(newTranslationForLessonRequest: UpdateTranslationForLessonRequest): Observable<TranslationForLessonResponse> {
    return this.httpClient.patch<TranslationForLessonResponse>( `api/translation`, newTranslationForLessonRequest);
  }

  public delete(translationId: string): Observable<TranslationForLessonResponse> {
    return this.httpClient.delete<TranslationForLessonResponse>( `api/translation/${translationId}`);
  }

  public uploadTranslations(fileUpload: FileUpload): Observable<HttpEvent<{}>> {
    const url =  `api/upload-translations/${fileUpload.languagePairId}`;
    return this.uploadService.uploadFile(url, fileUpload.file);
  }

  loadLatestTranslationsForLanguagePair(languagePairId: any): Observable<TranslationForLessonResponse[]> {

    const params = new HttpParams().set('languagePairId', languagePairId).set('latest', '10');
    return this.httpClient.get<TranslationForLessonResponse[]>( `api/translations`, {params});

  }
}
