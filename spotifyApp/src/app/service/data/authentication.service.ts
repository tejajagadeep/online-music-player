import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { API_URL_AUTH } from 'src/app/app-constants';
import { AuthAccessToken } from 'src/app/model/AuthAccessToken';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authAccessToken!: AuthAccessToken

  authenticate(username: string, password: string): Observable<AuthAccessToken> {
    // Construct the query parameters
    const params = new HttpParams()
      .set('username', username)
      .set('password', password);

    // Include the query parameters in the request
    return this.httpClient.post<AuthAccessToken>(`${API_URL_AUTH}/login`, {}, { params }).pipe(
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
