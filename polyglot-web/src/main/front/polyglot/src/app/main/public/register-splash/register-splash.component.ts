import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../common/services/authentication.service";
import {NewUserRequest} from "../../common/services/request/NewUserRequest";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'pol-register-splash',
  templateUrl: './register-splash.component.html',
  styleUrls: ['./register-splash.component.scss']
})
export class RegisterSplashComponent  {

  loginActive: boolean = true;

  constructor(private authenticationService:AuthenticationService, private router:Router) { }


  login(username:HTMLInputElement, password:HTMLInputElement){
    this.authenticationService.authenticate(username.value, password.value)
      .subscribe(r => {
        this.router.navigate(['/manage']);

      });
  }

  register(username:HTMLInputElement, password:HTMLInputElement){
    this.authenticationService.registerUser(new NewUserRequest(username.value, password.value))
      .subscribe(r => {
        this.router.navigate(['/manage']);

      })
  }

  toggle() {
    this.loginActive = !this.loginActive;
  }

}
