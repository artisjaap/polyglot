import {Inject, Injectable} from '@angular/core';
import {LoginService} from "./login.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticatedBaseServiceService {

  constructor(private loginService: LoginService, private httpClient: HttpClient, @Inject('API_URL') private apiurl: string) { }


   get<T>(url:string): Observable<T>{
    return this.httpClient.get<T>(this.apiurl + url);
  }

  post<T>(url:string, payload:any): Observable<T> {
    return this.httpClient.post<T>(this.apiurl + url, payload);
  }
}
