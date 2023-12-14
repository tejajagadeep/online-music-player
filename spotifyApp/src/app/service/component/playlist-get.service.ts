import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { PlayMusicComponent } from 'src/app/spotify/play-music/play-music.component';
import { MusicDataService } from '../data/music-data.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaylistGetService {

  constructor(private musicService: MusicDataService) {}

  getPlaylistSearch(playlistId: string): Observable<any> {
    // this.playlistMusic.getPlaylistSearch(playlistId);
    return this.musicService.getPlaylist(playlistId);
  }

}
