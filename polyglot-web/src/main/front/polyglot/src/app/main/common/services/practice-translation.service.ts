import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PracticeWordResponse} from "./response/practice-word-response";
import {AnswerAndNextWordResponse} from "./response/answer-and-next-word-response";
import {AuthenticationService} from "./authentication.service";
import {PracticeWordCheckRequest} from "./request/practice-word-check-request";
import {CheckAnswerAndNextDto} from "./dto/check-answer-and-next-dto";
import {TranslationsFilterRequest} from "./request/translations-filter-request";
import {TranslationResponse} from "./request/translation-response";
import {PagedResponse} from "./request/paged-response";

@Injectable({
  providedIn: 'root'
})
export class PracticeTranslationService {

  constructor(private httpClient:HttpClient,
              private authenticationService:AuthenticationService,
              @Inject('API_URL') private apiurl: string) { }

  findAllOpenPracticeWords(languagePairId:string, orderType:string):Observable<PracticeWordResponse[]>{
    const user = this.authenticationService.user;
    return this.httpClient.get<PracticeWordResponse[]>(this.apiurl +"api/translations/practice/list/"+user.userId+"/"+languagePairId+"/"+orderType);
  }

  nextPracticeWord(languagePairId:string, orderType:string):Observable<PracticeWordResponse>{
    const user = this.authenticationService.user;
    return this.httpClient.get<PracticeWordResponse>(this.apiurl +"api/translations/practice/next/"+user.userId+"/"+languagePairId+"/"+orderType);
  }

  checkAnswerAndNext(checkAnswer:CheckAnswerAndNextDto):Observable<AnswerAndNextWordResponse>{
    const user = this.authenticationService.user;
    const body: PracticeWordCheckRequest = new PracticeWordCheckRequest(
      user.userId,
      checkAnswer.translationId,
      checkAnswer.answerGiven,
      checkAnswer.answerOrderType,
      checkAnswer.nextOrderType
    );
    return this.httpClient.post<AnswerAndNextWordResponse>(this.apiurl +"api/translations/practice/check-and-next", body);
  }

  allWordsForLanguagePair(textFilter:string, languagePairId: string):Observable<PagedResponse<TranslationResponse>> {
    const body: TranslationsFilterRequest = new TranslationsFilterRequest(textFilter, languagePairId, 1000, 0);
    return this.httpClient.post<PagedResponse<TranslationResponse>>(this.apiurl + "api/translations/practice/list/all/filterd", body);
  }
}
