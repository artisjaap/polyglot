import {Inject, Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {LanguagePracticeJournalResponse} from "../../common/services/response/language-practice-journal-response";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TemplateCodeResponse} from "./response/template-code-response";
import {TemplateResponse} from "./response/template-response";
import {TemplateRequest} from "./request/template-request";
import {UploadService} from "../../common/services/upload.service";
import {CombinedTemplateCodeResponse} from "./response/combined-template-code-response";
import {NewCombinedTemplateCodeRequest} from "./request/new-combined-template-code-request";
import {TemplateNewCodeRequest} from "./request/template-new-code-request";
import {CombinedTemplateResponse} from "./response/combined-template-response";
import {NewCombinedTemplateRequest} from "./request/new-combined-template-request";
import {JournalReportRequest} from "../../common/services/request/journal-report-request";
import {NewDocumentCodeRequest} from "./request/new-document-code-request";
import {NewDocumentCodeResponse} from "./response/new-document-code-response";

@Injectable({
  providedIn: 'root'
})
export class DocumentServiceService {

  constructor(private httpClient:HttpClient, @Inject('API_URL') private apiurl: string,private uploadService:UploadService) { }

  createNewTemplateType(templateCode:string, description:string):Observable<TemplateCodeResponse>{
    let templateCodeRequest:TemplateNewCodeRequest = new TemplateNewCodeRequest(templateCode, description);
    return this.httpClient.post<TemplateCodeResponse>(this.apiurl +"api/document/template", templateCodeRequest);
  }

  uploadNewTemplate(templateCode:string, language:string, files: Set<File>):{[key:string]:Observable<number>}{
    let templateCodeRequest:TemplateRequest = new TemplateRequest(templateCode, language);
    let url = this.apiurl +"api/document/template/upload";
    return this.uploadService.upload(files, url, templateCodeRequest);
  }

  createNewCombinedTemplateCode(templateCode:string, desciption:string):Observable<CombinedTemplateCodeResponse>{
    let templateCodeRequest:NewCombinedTemplateCodeRequest = new NewCombinedTemplateCodeRequest(templateCode, desciption);
    return this.httpClient.post<CombinedTemplateCodeResponse>(this.apiurl +"api/document/template", templateCodeRequest);
  }

  createNewCombinedTemplate(language:string, code:string, templates:string[]):Observable<CombinedTemplateResponse>{
    let templateCodeRequest:NewCombinedTemplateRequest = new NewCombinedTemplateRequest(language, templates, code);
    return this.httpClient.post<CombinedTemplateResponse>(this.apiurl +"api/document/combined-template/config", templateCodeRequest);
  }


  createNewDocument(documentCode:string, desciption:string): Observable<NewDocumentCodeResponse>{
    let templateCodeRequest:NewDocumentCodeRequest = new NewDocumentCodeRequest(documentCode, desciption);
    return this.httpClient.post<NewDocumentCodeResponse>(this.apiurl +"api/document/document", templateCodeRequest);
  }

  activateTemplate(templateId:string):Observable<any>{
    return this.httpClient.put<any>(this.apiurl +"api/document/activate/template/" + templateId, {});
  }

  activateCombinedTemplate(templateId:string):Observable<any>{
    return this.httpClient.put<any>(this.apiurl +"api/document/activate/combined-template/" + templateId, {});
  }

  activateDocument(templateId:string):Observable<any>{
    return this.httpClient.put<any>(this.apiurl +"api/document/activate/document/" + templateId, {});
  }

  previewDocument(documentId:string):Observable<any> {

    let header: HttpHeaders = new HttpHeaders();
    header.append("content-Type", "blob");

    return this.httpClient.get(this.apiurl +"api/document/preview/document/" + documentId, {observe: 'response', responseType: 'blob'});


  }
}

