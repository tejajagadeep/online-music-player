import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-today-top-hits-playlist',
  templateUrl: './today-top-hits-playlist.component.html',
  styleUrls: ['./today-top-hits-playlist.component.css']
})
export class TodayTopHitsPlaylistComponent {

  spotifyPlaylist: SpotifyPlaylist | any;
  constructor(private route: ActivatedRoute, private musicService: MusicDataService) {}

  ngOnInit(): void {
    this.hot100();
  }

  hot100() {
    this.musicService.getTodayTopHitsPlaylist().subscribe(
      {
        next: (v) => {this.spotifyPlaylist=v,
          console.log(v.tracks.items[0].track.artists[0].name)
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete') 
    }
    )
  }
  openSpotify(url: string): void {
    window.open(url, '_blank');
  }
  
}
