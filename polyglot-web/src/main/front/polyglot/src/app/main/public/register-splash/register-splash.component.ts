import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../common/services/authentication.service";
import {NewUserRequest} from "../../common/services/request/NewUserRequest";

@Component({
  selector: 'pol-register-splash',
  templateUrl: './register-splash.component.html',
  styleUrls: ['./register-splash.component.scss']
})
export class RegisterSplashComponent implements OnInit {

  loginActive: boolean = true;

  constructor(private authenticationService:AuthenticationService) { }

  ngOnInit() {
  }

  login(username:HTMLInputElement, password:HTMLInputElement){
    this.authenticationService.authenticate(username.value, password.value);
  }

  register(username:HTMLInputElement, password:HTMLInputElement){
    this.authenticationService.registerUser(new NewUserRequest(username.value, password.value))
  }

  toggle() {
    this.loginActive = !this.loginActive;
  }

}
