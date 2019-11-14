import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {LoginService} from "../../user/login.service";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private authenticationService: LoginService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authenticationService.token();
    console.log("Add authentication token" + token);
    if (token) {

      // const copiedRequest = req.clone({headers : req.headers.set('Authorization', 'Bearer ' + token)});
      const authReq = req.clone({
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': 'Bearer ' + token
        })
      });

      return next.handle(authReq);
    } else {
      console.log('no token');
      return next.handle(req);
    }


  }

}

