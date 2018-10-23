import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {UserLoginResponse} from "../../services/response/user-login-response";

@Component({
  selector: 'pol-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userInfo: UserLoginResponse;
  constructor(private authentication:AuthenticationService) { }

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

}
