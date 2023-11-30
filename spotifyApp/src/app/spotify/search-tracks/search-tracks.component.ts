import { AfterViewInit, Component } from '@angular/core';
import { SpotifyTracks } from 'src/app/model/SpotifyTracks';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-search-tracks',
  templateUrl: './search-tracks.component.html',
  styleUrls: ['./search-tracks.component.css']
})
export class SearchTracksComponent implements AfterViewInit{
  spotifyTracks!: SpotifyTracks; // Adjust the type accordingly

  constructor(private musicService: MusicDataService) {}

  ngOnInit(): void {
    
  }

  
  ngAfterViewInit(): void {
    this.searchTracks("leo");
  }


  searchTracks(query: string) {
    this.musicService.searchTracks(query).subscribe({
      next: (v) => {
        this.spotifyTracks = v;
        console.log(v.tracks.items[0].name)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }

}
