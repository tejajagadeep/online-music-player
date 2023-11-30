import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { API_URL_AUTH } from '../../app-constants';
import { AuthAccessToken } from '../../model/AuthAccessToken';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authAccessToken!: AuthAccessToken

  authenticate(username: string, password: string) {
    return this.httpClient.post<AuthAccessToken>(`${API_URL_AUTH}/login`, { username, password }).pipe(
      map(
        authAccessToken => {
          localStorage.setItem('authenticatedUser', authAccessToken.username);
          let tokenStr = 'Bearer ' + authAccessToken.jwtToken;
          localStorage.setItem('token', tokenStr);
          return authAccessToken;
        }
      )
    );
  }

}
