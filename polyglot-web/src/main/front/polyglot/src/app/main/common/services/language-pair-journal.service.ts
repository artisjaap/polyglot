import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "./authentication.service";
import {Observable} from "rxjs";
import {LanguagePracticeJournalResponse} from "./response/language-practice-journal-response";

@Injectable({
  providedIn: 'root'
})
export class LanguagePairJournalService {

  constructor(private httpClient:HttpClient,
              private authenticationService:AuthenticationService,
              @Inject('API_URL') private apiurl: string) { }

  findJournalForLanguageInYearMonth(languagePairId:string, yearMonth:string):Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/user/"+user.userId+"/translation-pair/"+languagePairId+"/year-month/"+yearMonth);
  }

  findJournalForLanguagePairOnDate(languagePairId:string, date:string): Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/user/"+user.userId+"/translation-pair/"+languagePairId+"/date/"+date);
  }

  findJournalForLesson(lessonId:string): Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/user/"+user.userId+"/lesson/"+lessonId);
  }

  findJournalForLessonInYearMonth(lessonId:string, yearMonth:string): Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/user/"+user.userId+"/lesson/"+lessonId + "/year-month/" + yearMonth);
  }

  findJournalForLessonOnDate(lessonId:string, date:string): Observable<LanguagePracticeJournalResponse>{
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePracticeJournalResponse>(this.apiurl +"api/journal/user/"+user.userId+"/lesson/"+lessonId + "/date/" + date);
  }
}
