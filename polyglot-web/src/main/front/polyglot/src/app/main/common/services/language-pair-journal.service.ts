import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "./authentication.service";
import {Observable} from "rxjs";
import {LanguagePracticeJournalResponse} from "./response/language-practice-journal-response";
import {JournalReportRequest} from "./request/journal-report-request";

@Injectable({
  providedIn: 'root'
})
export class LanguagePairJournalService {

  constructor(private httpClient:HttpClient,
              private authenticationService:AuthenticationService,
              @Inject('API_URL') private apiurl: string) { }

  findAllJournalForFilter(languagePairId:string, lessonId:string, from:string, to:string):Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    let filter: JournalReportRequest = new JournalReportRequest(user.userId, languagePairId, lessonId, from, to);
    return this.httpClient.post<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/result", filter);
  }

  findAllJournalForFilterAsPdf(languagePairId:string, lessonId:string, from:string, to:string):Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    let filter: JournalReportRequest = new JournalReportRequest(user.userId, languagePairId, lessonId, from, to);
    return this.httpClient.post<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/result/pdf", filter);
  }

  findJournalForLanguageInYearMonth(languagePairId:string, yearMonth:string):Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/user/"+user.userId+"/translation-pair/"+languagePairId+"/year-month/"+yearMonth);
  }


}
