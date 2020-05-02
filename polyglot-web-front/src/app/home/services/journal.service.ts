import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {PracticeAnswerValidateRequest} from '../model/practice-answer-validate-request';
import {Observable} from 'rxjs';
import {PracticeAnswerResponse} from '../model/practice-answer-response';
import {CreatePracticePdfRequest} from '../model/create-practice-pdf-request';
import {CreateJournalPdfRequest} from '../model/create-journal-pdf-request';

@Injectable()
export class JournalService {
  constructor(private httpClient: HttpClient,  @Inject('API_URL') private apiurl: string) {

  }

  public createJournalPdf(createJournalPdfRequest: CreateJournalPdfRequest): Observable<HttpResponse<Blob>> {
    return this.httpClient.post(this.apiurl + `api/journal/generate-pdf`, createJournalPdfRequest,
      {observe: 'response', responseType: 'blob'});
  }
}
