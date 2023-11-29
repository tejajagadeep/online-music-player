import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SpotifyPlaylist } from '../../model/SpotifyPlaylist';
import { MusicDataService } from '../../service/data/music-data.service';

@Component({
  selector: 'app-bill-board100-playlist',
  templateUrl: './bill-board100-playlist.component.html',
  styleUrls: ['./bill-board100-playlist.component.css']
})
export class BillBoard100PlaylistComponent {
  spotifyPlaylist: SpotifyPlaylist | any;
  constructor(private route: ActivatedRoute, private musicService: MusicDataService) {}

  ngOnInit(): void {
    this.billBoard();
  }

  billBoard() {
    this.musicService.billBoard100Playlist().subscribe(
      {
        next: (v) => {this.spotifyPlaylist=v
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete') 
    }
    )
  }
}
