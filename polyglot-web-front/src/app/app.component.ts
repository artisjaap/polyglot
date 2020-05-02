import {Component, OnInit} from '@angular/core';
import {AppState} from "./reducers";
import {select, Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {isLoggedIn, isLoggedOut} from "./auth/auth.selectors";
import {login} from "./auth/auth.actions";
import {AuthActions} from "./auth/action-types";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'polyglot-R';

  isLoggedIn$: Observable<boolean>;
  isLoggedOut$: Observable<boolean>;

  constructor(private store:Store<AppState>){

  }

  ngOnInit(): void {
    const savedUser = localStorage.getItem('user');
    if(savedUser){
      this.store.dispatch(login({user:JSON.parse(savedUser)}));
    }

    this.isLoggedIn$ = this.store
      .pipe(
        select(isLoggedIn)
      );

    this.isLoggedOut$ = this.store
      .pipe(
        select(isLoggedOut)
      );
  }

  logout() {
    this.store.dispatch(AuthActions.logout());
  }
}
