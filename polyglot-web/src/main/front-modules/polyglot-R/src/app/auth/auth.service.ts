import {Inject, Injectable} from '@angular/core';
import {UserLoginResponse} from "../../../../polyglot2/src/app/user/model/user-login-response";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class AuthService {

  constructor(private httpClient:HttpClient,
              @Inject('API_URL') private apiurl: string) { }

  public authenticate(username:string, password:string):Observable<UserLoginResponse>{
    return this.httpClient.get<UserLoginResponse>(this.apiurl + `public/api/login/${username}/${password}`);
  }
}
