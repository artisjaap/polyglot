import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from 'rxjs/operators';
import {UserLoginResponse} from "./model/user-login-response";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private user: UserLoginResponse = { firstName: '', lastName: '', preferedRole: '', userId: '', username: '',  password: '',  token: ''};

  constructor(private httpClient:HttpClient, @Inject('API_URL') private apiurl: string) { }

  public authenticate(username:string, password:string):Observable<UserLoginResponse>{
    return this.httpClient.get<UserLoginResponse>(this.apiurl + `public/api/login/${username}/${password}`)
      .pipe(map(response => {
        Object.assign(this.user, response);
        return response;
      }));
  }

  public loggedInUser(): UserLoginResponse {
    return this.user;
  }

  token():string {
      return this.user.token;
  }
}
