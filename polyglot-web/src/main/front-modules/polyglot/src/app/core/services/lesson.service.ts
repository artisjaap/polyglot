import {Inject, Injectable} from '@angular/core';
import {UserService} from './user.service';
import {LogService} from './log.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LessonResponse} from './response/lesson-response';
import {TestAssignmentResponse} from './response/test-assignment-response';
import {NewAutomaticLessonRequest} from './request/new-automatic-lesson-request';
import {LessonHeaderResponse} from './response/lesson-header-response';
import {TestSolutionRequest} from './request/test-solution-request';
import {NewLessonRequest} from './request/new-lesson-request';
import {LessonTranslationPairResponse} from './response/lesson-translation-pair-response';
import {PagedResponse} from "./request/paged-response";
import {PracticeWordResponse} from "./response/practice-word-response";
import {LessonsFilterRequest} from "./request/lessons-filter-request";

@Injectable({
  providedIn: 'root'
})
export class LessonService {

  constructor(private authenticationService: UserService,
              private logger: LogService,
              private httpClient: HttpClient,
              @Inject('API_URL') private apiurl: string) { }

  autocreate(languagePairId: string, lessonTitle: string, numberOfWords: number): Observable<LessonResponse> {
    const user = this.authenticationService.user;
    const newLesson = new NewAutomaticLessonRequest(user.userId, languagePairId, lessonTitle, numberOfWords);

    return this.httpClient.post<LessonResponse>(this.apiurl + 'api/lessons/autocreate', newLesson);
  }

  practiceLesson(lessonId: string): Observable<LessonResponse> {
    return this.httpClient.get<LessonResponse>(this.apiurl + 'api/lessons/practice/' + lessonId);
  }

  allLessonsForLanguage(textFilter: string,
                        languagePairId: string,
                        page: number,
                        pageSize: number): Observable<PagedResponse<LessonHeaderResponse>>{
    const body: LessonsFilterRequest = new LessonsFilterRequest(textFilter, languagePairId, pageSize, page);
    return this.httpClient.post<PagedResponse<LessonHeaderResponse>>(this.apiurl + 'api/lessons/list/all/filtered', body);
  }

  lessonsForLanguage(languagePairId: string): Observable<LessonHeaderResponse[]> {
    return this.httpClient.get<LessonHeaderResponse[]>(this.apiurl + 'api/lessons/' + languagePairId);
  }

  getLessonForTesting(lessonId: string, orderType: string): Observable<TestAssignmentResponse> {
    return this.httpClient.get<TestAssignmentResponse>(this.apiurl + 'api/lessons/test/' + lessonId + '/' + orderType);
  }


  correctLesson(lessonData: TestSolutionRequest): Observable<TestAssignmentResponse> {
    return this.httpClient.post<TestAssignmentResponse>(this.apiurl + 'api/lessons/test/correct', lessonData);
  }

  createLesson(languagePairId: string, title: string): Observable<LessonResponse> {
    const user = this.authenticationService.user;
    const newLesson = new NewLessonRequest(user.userId, languagePairId, title);
    return this.httpClient.post<LessonResponse>(this.apiurl + 'api/lessons/create', newLesson);
  }

  addWordToLesson(lessonId: string, translationId: string): Observable<LessonTranslationPairResponse> {
    return this.httpClient.put<LessonTranslationPairResponse>(this.apiurl + 'api/lessons/add/' + lessonId + '/' + translationId, null);
  }

  removeWordFromLesson(lessonId: string, translationId: string): Observable<LessonTranslationPairResponse> {
    return this.httpClient.delete<LessonTranslationPairResponse>(this.apiurl + 'api/lessons/remove/' + lessonId + '/' + translationId);
  }
}


