import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {AppState} from '../reducers/app.reducer';
import {first, flatMap, withLatestFrom} from 'rxjs/operators';
import {userToken} from '../auth/reducers/auth.selectors';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private store: Store<AppState>) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return this.store.select(userToken).pipe(
      first(),
      flatMap(token => {
        const authReq = !!token ? req.clone({
          setHeaders: { Authorization: 'Bearer ' + token },
        }) : req;
        return next.handle(authReq);
      })
    );
  }

}



