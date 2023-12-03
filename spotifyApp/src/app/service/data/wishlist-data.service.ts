import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL_WISHLIST } from 'src/app/app-constants';
import { Track } from 'src/app/model/Track';
import { Wishlist } from 'src/app/model/Wishlist';

@Injectable({
  providedIn: 'root'
})
export class WishlistDataService {

  constructor(private httpClient: HttpClient) { }

  getUserWishList(): Observable<Wishlist>{
    const username = localStorage.getItem('username');
    const params = new HttpParams().set('username', "jagadeep");
    return this.httpClient.get<Wishlist>(`${API_URL_WISHLIST}/userWishList`, {params})
  }

  favoriteExists(trackId: string): Observable<boolean>{
    const username="jagadeep";
    const params = new HttpParams()
      .set('username', username)
      .set('trackId', trackId);
    return this.httpClient.get<boolean>(`${API_URL_WISHLIST}/favoriteExists`, {params});
  }

  saveTrackToWishlist(track: Track){
    const username = localStorage.getItem('username');
    const params = new HttpParams().set('username', "jagadeep");
    return this.httpClient.post<Track>(`${API_URL_WISHLIST}/saveTrackToWishlist`, track,{params});
  }

  deleteTrackByUsernameAndTrackId(trackId: string){
    const username="jagadeep";
    const params = new HttpParams()
      .set('username', username)
      .set('trackId', trackId);
    return this.httpClient.delete(`${API_URL_WISHLIST}/removeTrack`, {params});
  }
}

