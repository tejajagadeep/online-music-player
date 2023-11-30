import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-discover-weekly-playlist',
  templateUrl: './discover-weekly-playlist.component.html',
  styleUrls: ['./discover-weekly-playlist.component.css']
})
export class DiscoverWeeklyPlaylistComponent implements AfterViewInit{
  spotifyPlaylist!: SpotifyPlaylist; // Adjust the type accordingly

  constructor(private musicService: MusicDataService) {}

  ngOnInit(): void {
    
  }

  
  ngAfterViewInit(): void {
    this.hot100();
  }


  hot100() {
    this.musicService.getTodayTopHitsPlaylist().subscribe({
      next: (v) => {
        this.spotifyPlaylist = v;
        console.log(v.tracks.items[0].track.album.images[1].url)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }

}
