import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SpotifyPlaylist } from '../../model/SpotifyPlaylist';
import { API_URL_MUSIC } from '../../app-constants';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MusicDataService {

  constructor(private httpClient: HttpClient) { }

  billBoard100Playlist(): Observable<SpotifyPlaylist>{
    return this.httpClient.get<SpotifyPlaylist>(`${API_URL_MUSIC}/billBoard100Playlist`);
  }


  getTodayTopHitsPlaylist(): Observable<SpotifyPlaylist>{
    return this.httpClient.get<SpotifyPlaylist>(`${API_URL_MUSIC}/todayTopHitsPlaylist`);
  }

  
  getDiscoverWeeklyPlaylist(): Observable<SpotifyPlaylist>{
    return this.httpClient.get<SpotifyPlaylist>(`${API_URL_MUSIC}/discoverWeeklyPlaylist`);
  }
}
