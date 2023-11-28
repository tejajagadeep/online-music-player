import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-bill-board100-playlist',
  templateUrl: './bill-board100-playlist.component.html',
  styleUrls: ['./bill-board100-playlist.component.css']
})
export class BillBoard100PlaylistComponent {
  spotifyPlaylist: SpotifyPlaylist | any;
  urlString: URL=new URL('https://open.spotify.com/playlist/6UeSakyzhiEt4NB3UAd6NQ');
  // urlString!: URL;
  constructor(private route: ActivatedRoute, private musicService: MusicDataService) {}

  ngOnInit(): void {
    this.billBoard();
  }

  billBoard() {
    this.musicService.billBoard100Playlist().subscribe(
      {
        next: (v) => {this.spotifyPlaylist=v, 
          console.log(v),
          console.log(this.spotifyPlaylist.externalUrls=v.external_urls)
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete') 
    }
    )
  }
}
