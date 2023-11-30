import { Component, Renderer2 } from '@angular/core';
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
  constructor(private renderer: Renderer2, private route: ActivatedRoute, private musicService: MusicDataService) {}

  ngOnInit(): void {
    this.billBoard();
    this.loadScript('https://kit.fontawesome.com/cf98ff2373.js');
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
  private loadScript(scriptUrl: string) {
    const script = this.renderer.createElement('script');
    script.src = scriptUrl;
    script.async = true;
    script.defer = true;

    this.renderer.appendChild(document.body, script);
  }
}
