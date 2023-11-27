import { Injectable } from '@angular/core';
import { AuthenticationService } from '../data/authentication.service';
import { HttpHandler, HttpRequest } from '@angular/common/http';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService {

  user!: User;

  constructor(private authService: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    let basicAuthHeaderString = this.authService.authenticate(this.user).subscribe(
      response => localStorage.setItem('username', response.username)
    );
    let username = this.authService.getLoggedInUserName()

    if (basicAuthHeaderString && username) {
      request = request.clone({
        setHeaders: {
          Authorization: basicAuthHeaderString
        }
      })
    }
    return next.handle(request);

}
