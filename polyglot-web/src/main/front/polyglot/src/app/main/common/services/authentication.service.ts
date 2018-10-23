import {Inject, Injectable} from '@angular/core';
import {User} from "../model/user";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserLoginResponse} from "./response/user-login-response";
import {Observable} from "rxjs";
import {LogService} from "./log.service";
import {map} from "rxjs/operators";
import {NewUserRequest} from "./request/NewUserRequest";



@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  user: UserLoginResponse;

  constructor(private logger:LogService,
              private httpClient:HttpClient,
              @Inject('API_URL') private apiurl: string) { }

  authenticate(username:string, password:string): Observable<UserLoginResponse> {
    this.logger.logInfo(username + "," + password+ "," + this.apiurl);
    return this.httpClient.get<UserLoginResponse>(this.apiurl + "public/api/login/" + username + "/" + password)
      .pipe(map(r=> {
        this.user = r;
        return r;
      }));
  }


  registerUser(newUser:NewUserRequest):Observable<UserLoginResponse>{
    return this.httpClient.post<UserLoginResponse>(this.apiurl + "public/api/register", newUser);
  }

  isAuthenticated() {
    return this.user != undefined;
  }


  token():string {
    return this.user?this.user.token:null;
  }

  logout() {
    this.user = undefined;
  }
}
