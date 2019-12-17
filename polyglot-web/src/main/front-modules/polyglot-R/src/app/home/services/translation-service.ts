import {Inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpEvent, HttpParams} from '@angular/common/http';
import {NewTranslationForLessonRequest} from '../model/new-translation-for-lesson-request';
import {TranslationForLessonResponse} from '../model/translation-for-lesson-response';
import {UpdateTranslationForLessonRequest} from '../model/update-translation-for-lesson-request';
import {UploadService} from './upload.service';
import {TranslationsLoadedByFileResponse} from '../model/translations-loaded-by-file-response';
import {FileUpload} from '../model/file-upload';

@Injectable()
export class TranslationService {

  constructor(private httpClient: HttpClient, @Inject('API_URL') private apiurl: string,
              private uploadService: UploadService) {

  }

  // public loadLatestWordsForLanguagePair(languagePairId: string): Observable<Translation[]> {
  //   let params = new HttpParams().set("languagePairId", languagePairId);
  //   return this.httpClient.get<Translation[]>(this.apiurl + `api/translations`, {params: params});
  // }

  public createNewTranslation(newTranslationForLessonRequest: NewTranslationForLessonRequest): Observable<TranslationForLessonResponse> {
    return this.httpClient.put<TranslationForLessonResponse>(this.apiurl + `api/translation`, newTranslationForLessonRequest);
  }

  public updateTranslation(newTranslationForLessonRequest: UpdateTranslationForLessonRequest): Observable<TranslationForLessonResponse> {
    return this.httpClient.patch<TranslationForLessonResponse>(this.apiurl + `api/translation`, newTranslationForLessonRequest);
  }

  public delete(translationId: string): Observable<TranslationForLessonResponse> {
    return this.httpClient.delete<TranslationForLessonResponse>(this.apiurl + `api/translation/${translationId}`);
  }

  public uploadTranslations(fileUpload: FileUpload): Observable<HttpEvent<{}>> {
    const url = this.apiurl + `api/upload-translations/${fileUpload.languagePairId}`;
    return this.uploadService.uploadFile(url, fileUpload.file);
  }

}

