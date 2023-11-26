import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL_AUTH } from 'src/app/app-contants';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authenticate(user: User){
    return this.httpClient.post<User>(`${API_URL_AUTH}/login`, user)
  }
}
