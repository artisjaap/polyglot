import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {UserLoginResponse} from "../../services/response/user-login-response";
import {Observable, Subscription} from "rxjs";
import {Event} from "@angular/router";

@Component({
  selector: 'pol-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  userInfo: UserLoginResponse;
  changeLoginEvent:Subscription;
  constructor(private authentication:AuthenticationService) {
    this.changeLoginEvent = authentication.userLoginUpdated.subscribe(r => {
      this.userInfo = r
    });

    console.log(this.changeLoginEvent);

  }

  ngOnInit() {

  }

  login(username:HTMLInputElement, password:HTMLInputElement){
    this.authentication.authenticate(username.value, password.value).subscribe(response => {
      this.userInfo = response;
    });
  }

  logout(){
    this.authentication.logout();
  }

  isAuthenticated(){
    return this.authentication.isAuthenticated();
  }

  ngOnDestroy() {
    this.changeLoginEvent.unsubscribe();
  }

}
