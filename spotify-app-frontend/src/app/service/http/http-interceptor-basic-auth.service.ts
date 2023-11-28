import { Injectable } from '@angular/core';
import { AuthenticationService } from '../data/authentication.service';
import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService  implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    let basicAuthHeaderString = localStorage.getItem('authenticatedUser');
    let username = localStorage.getItem('token')

    if (basicAuthHeaderString && username) {
      request = request.clone({
        setHeaders: {
          Authorization: basicAuthHeaderString
        }
      })
    }
    return next.handle(request);
  }

}
