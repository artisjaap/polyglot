import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {UserService} from '../services/user.service';


@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private authenticationService: UserService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authenticationService.token();

    if (token) {
      // console.log('token', token);
      const copiedRequest = req.clone({headers : req.headers.set('Authorization', 'Bearer ' + token)});
      // console.log(copiedRequest);
      return next.handle(copiedRequest);
    } else {
      console.log('no token');
      return next.handle(req);
    }


  }

}
