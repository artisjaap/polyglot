import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PracticeWordResponse} from './response/practice-word-response';
import {AnswerAndNextWordResponse} from './response/answer-and-next-word-response';
import {PracticeWordCheckRequest} from './request/practice-word-check-request';
import {TranslationsFilterRequest} from './request/translations-filter-request';
import {PagedResponse} from './request/paged-response';
import {UserService} from './user.service';
import {CheckAnswerAndNextDto} from './dto/check-answer-and-next-dto';

@Injectable({
  providedIn: 'root'
})
export class PracticeTranslationService {

  constructor(private httpClient: HttpClient,
              private authenticationService: UserService,
              @Inject('API_URL') private apiurl: string) { }

  findAllOpenPracticeWords(languagePairId: string, orderType: string): Observable<PracticeWordResponse[]> {
    const user = this.authenticationService.user;
    return this.httpClient.get<PracticeWordResponse[]>(this.apiurl + 'api/translations/practice/list/' +
      user.userId + '/' + languagePairId + '/' + orderType);
  }

  nextPracticeWord(languagePairId: string, orderType: string): Observable<PracticeWordResponse> {
    const user = this.authenticationService.user;
    return this.httpClient.get<PracticeWordResponse>(this.apiurl + 'api/translations/practice/next/' +
      user.userId + '/' + languagePairId + '/' + orderType);
  }

  checkAnswerAndNext(checkAnswer: CheckAnswerAndNextDto): Observable<AnswerAndNextWordResponse> {
    const user = this.authenticationService.user;
    const body: PracticeWordCheckRequest = new PracticeWordCheckRequest(
      user.userId,
      checkAnswer.translationId,
      checkAnswer.answerGiven,
      checkAnswer.answerOrderType,
      checkAnswer.nextOrderType,
      checkAnswer.lessonId
    );
    return this.httpClient.post<AnswerAndNextWordResponse>(this.apiurl + 'api/translations/practice/check-and-next', body);
  }

  private updateStatusOfTranslation(translationId: string, status: string): Observable<PracticeWordResponse> {
    return this.httpClient.patch<PracticeWordResponse>(this.apiurl + 'api/translations/practice/update-status/' +
      translationId + '/' + status, null);
  }

  updateStatusOfTranslationToKnown(translationId: string): Observable<PracticeWordResponse> {
    return this.updateStatusOfTranslation(translationId, 'KNOWN');
  }
  updateStatusOfTranslationToInProgress(translationId: string): Observable<PracticeWordResponse> {
    return this.updateStatusOfTranslation(translationId, 'IN_PROGRESS');
  }
  updateStatusOfTranslationToNew(translationId: string): Observable<PracticeWordResponse> {
    return this.updateStatusOfTranslation(translationId, 'NEW');
  }
  updateStatusOfTranslationToHold(translationId: string): Observable<PracticeWordResponse> {
    return this.updateStatusOfTranslation(translationId, 'ON_HOLD');
  }

  allWordsForLanguagePair(textFilter: string,
                          languagePairId: string,
                          page: number,
                          pageSize: number): Observable<PagedResponse<PracticeWordResponse>> {
    const body: TranslationsFilterRequest = new TranslationsFilterRequest(textFilter, languagePairId, pageSize, page);
    return this.httpClient.post<PagedResponse<PracticeWordResponse>>(this.apiurl + 'api/translations/practice/list/all/filterd', body);
  }
}
