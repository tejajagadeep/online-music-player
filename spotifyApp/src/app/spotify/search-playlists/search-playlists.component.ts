import { AfterViewInit, Component } from '@angular/core';
import { SpotifyPlaylistSearch } from 'src/app/model/SpotifyPlaylistSearch';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-search-playlists',
  templateUrl: './search-playlists.component.html',
  styleUrls: ['./search-playlists.component.css']
})
export class SearchPlaylistsComponent implements AfterViewInit{

  spotifyTracks!: SpotifyPlaylistSearch; // Adjust the type accordingly
  searchQuery: string = '';

  constructor(private musicService: MusicDataService) {}

  ngOnInit(): void {
    
  }

  
  ngAfterViewInit(): void {
    // this.searchTracks("leo");
  }

  redirectToLink(link: string) {
    // Navigate to the provided HTTPS link
    window.open(link, '_blank');
  }

  searchTracks(query: string) {
    this.musicService.searchPlaylists(query).subscribe({
      next: (v) => {
        this.spotifyTracks = v;
        console.log(v);
        console.log(v.playlists.items[0].external_urls.spotify)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }
}