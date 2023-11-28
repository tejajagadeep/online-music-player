import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL_MUSIC } from 'src/app/app-constants';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';

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
