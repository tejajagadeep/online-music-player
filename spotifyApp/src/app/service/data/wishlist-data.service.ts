import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL_WISHLIST } from 'src/app/app-constants';
import { Wishlist } from 'src/app/model/Wishlist';

@Injectable({
  providedIn: 'root'
})
export class WishlistDataService {

  constructor(private httpClient: HttpClient) { }

  getUserWishList(): Observable<Wishlist>{
    const username = localStorage.getItem('username');
    const params = new HttpParams().set('username', "jagadeep");
    return this.httpClient.get<Wishlist>(`${API_URL_WISHLIST}`, )
  }
}
