import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserLoginResponse} from "./model/user-login-response";

@Injectable()
export class AuthService {

  constructor(private httpClient:HttpClient,
              @Inject('API_URL') private apiurl: string) { }

  public authenticate(username:string, password:string):Observable<UserLoginResponse>{
    return this.httpClient.get<UserLoginResponse>(this.apiurl + `public/api/login/${username}/${password}`);
  }
}
