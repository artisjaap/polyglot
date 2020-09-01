import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {noop} from 'rxjs';
import {FormBuilder, FormGroup} from '@angular/forms';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {AppState} from '../../reducers/app.reducer';
import {login} from '../reducers/auth.actions';

@Component({
  selector: 'app-auth',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(private authService: AuthService, private fb: FormBuilder,
              private router: Router,
              private store: Store<AppState>) {
    this.form = fb.group({
      username: ['stijn'],
      password: ['abc']
    });
  }

  ngOnInit() {
  }

  login() {
    const formVals = this.form.value;
    this.authService.authenticate(formVals.username, formVals.password)
      .pipe(
        tap(user => {
          this.store.dispatch(login({user}));
          this.router.navigateByUrl('/home/student/dashboard');
        })
      )
      .subscribe(noop);
  }
}
