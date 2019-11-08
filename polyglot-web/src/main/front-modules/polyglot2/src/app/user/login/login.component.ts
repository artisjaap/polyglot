import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";
import {Router} from "@angular/router";
import {tap} from "rxjs/operators";
import {noop} from "rxjs";
import {Store} from "@ngrx/store";
import {AppState} from "../../reducers";
import {login} from "../auth.actions";
import {AuthActions} from "../auth.action-types";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private loginService:LoginService, private router:Router, private store:Store<AppState>) { }

  ngOnInit() {
  }

  public authenticate(username:HTMLInputElement, password:HTMLInputElement){
    this.loginService.authenticate(username.value, password.value)
      .pipe(
        tap(user => {
          console.log(user);

          this.store.dispatch(AuthActions.login({user}));

          if(user.preferedRole === 'STUDENT'){
            this.router.navigate(['home/student/dashboard']);
          }
        })
      )
      .subscribe(noop);
  }

}
