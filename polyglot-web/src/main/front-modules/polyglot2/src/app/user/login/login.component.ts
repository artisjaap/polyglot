import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../polyglot-common/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private loginService:LoginService, private router:Router) { }

  ngOnInit() {
  }

  public authenticate(username:HTMLInputElement, password:HTMLInputElement){
    this.loginService.authenticate(username.value, password.value).subscribe(r => {
      console.log(r);
      if(r.preferedRole === 'STUDENT'){

        this.router.navigate(['home/student/dashboard']);
      }
    });
  }

}
