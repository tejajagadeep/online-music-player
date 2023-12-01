import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-today-top-hits-playlist',
  templateUrl: './today-top-hits-playlist.component.html',
  styleUrls: ['./today-top-hits-playlist.component.css']
})



export class TodayTopHitsPlaylistComponent implements AfterViewInit {


  spotifyPlaylist!: SpotifyPlaylist; // Adjust the type accordingly
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];
  displayedColumns= ['index', 'name', 'artists', 'duration', 'action']



  constructor(private route: ActivatedRoute, private musicService: MusicDataService) {}

  ngOnInit(): void {
    
  }

  
  ngAfterViewInit(): void {
    this.hot100();
  }


  hot100() {
    this.musicService.getTodayTopHitsPlaylist().subscribe({
      next: (v) => {
        this.spotifyPlaylist = v;
        console.log(v.tracks.items[0].added_at)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }

  onPageChange(event: any): void {
    // Handle page changes if needed
    const pageIndex = event.pageIndex;
  const pageSize = event.pageSize;
  const length = event.length;

  // You can perform actions based on the page change, for example, fetching new data
  this.hot100();
  }
  
}
