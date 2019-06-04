import {EventEmitter, Inject, Injectable} from '@angular/core';
import {UserService} from './user.service';
import {LogService} from './log.service';
import {HttpClient} from '@angular/common/http';
import {LanguagePairResponse} from './response/language-pair-response';
import {Observable} from 'rxjs';
import {LanguagePairRequest} from './request/LanguagePairRequest';
import {NewTranslationsForUserRequest} from './request/new-translations-for-user-request';
import {NewSimpleTranslationRequest} from './request/new-simple-translation-request';
import {TranslationsForUserResponse} from './response/translations-for-user-response';
import {map} from 'rxjs/operators';
import {PracticeWordResponse} from './response/practice-word-response';
import {UploadService} from './upload.service';
import {UpdateTranslationRequest} from "./request/update-translation-request";

@Injectable({
  providedIn: 'root'
})
export class ManagerTranslationService {
  public event: EventEmitter<TranslationsForUserResponse> = new EventEmitter<TranslationsForUserResponse>();

  constructor(private authenticationService: UserService,
              private logger: LogService,
              private httpClient: HttpClient,
              private uploadService: UploadService,
              @Inject('API_URL') private apiurl: string) {


    this.event.subscribe((e) => {
      logger.logInfo('Catch event' + e);
    });
  }


  allLanguagePairs(): Observable<LanguagePairResponse[]> {
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePairResponse[]>(this.apiurl + 'api/translations/pairs/user/' + user.userId);
  }


  createNewLanguagePair(languageFrom: string, languageTo: string): Observable<LanguagePairResponse> {
    const body: LanguagePairRequest = new LanguagePairRequest(this.authenticationService.user.userId, languageFrom, languageTo);
    return this.httpClient.post<LanguagePairResponse>(this.apiurl + 'api/translations/pairs/', body);
  }

  removeLanguagePair(languagePairId:string){
    return this.httpClient.delete<LanguagePairResponse>(this.apiurl + 'api/translations/pair/' + languagePairId);
  }

  languagePairWithId(languagePairId: string): Observable<LanguagePairResponse> {
    return this.httpClient.get<LanguagePairResponse>(this.apiurl + 'api/translations/pairs/' + languagePairId);
  }

  updateTranslation(translationId: string, languageFrom: string, languageTo: string): Observable<PracticeWordResponse> {
    const body: UpdateTranslationRequest = new UpdateTranslationRequest(translationId, languageFrom, languageTo);
    return this.httpClient.post<PracticeWordResponse>(this.apiurl + 'api/translations/update', body);
  }

  addNewWord(languagePairId: string, languageFrom: string, languageTo: string): Observable<TranslationsForUserResponse> {
    const user = this.authenticationService.user;

    const translations = [];
    translations.push(new NewSimpleTranslationRequest(languageFrom, languageTo));
    const body: NewTranslationsForUserRequest = new NewTranslationsForUserRequest(user.userId, languagePairId, translations);
    return this.httpClient.post<TranslationsForUserResponse>(this.apiurl + 'api/translations/pairs/translations', body)
      .pipe(map(t => {
        this.event.emit(t);
        return t;
      }));

  }

  public currentListToPracticeForLanguage(languagePairId: string, orderType: string): Observable<PracticeWordResponse[]> {
    const user = this.authenticationService.user;
    return this.httpClient.get<PracticeWordResponse[]>(this.apiurl + 'api/translations/practice/list/' +
      user.userId + '/' + languagePairId + '/' + orderType);

  }

  uploadTranslations(translationPairId: string, files: Set<File>): {[key: string]: Observable<number>} {
    const user = this.authenticationService.user;
    const url = this.apiurl + 'api/translations/pairs/' + user.userId + '/translations/' + translationPairId + '/file';

    return this.uploadService.upload(files, url);
  }
}
