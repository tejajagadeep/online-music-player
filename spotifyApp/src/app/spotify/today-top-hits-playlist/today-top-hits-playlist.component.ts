import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/model/Item';
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
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['position', 'image', 'name', 'artist', 'duration', 'play', 'action'];


  constructor(private route: ActivatedRoute, private musicService: MusicDataService) { }

  ngOnInit(): void {

  }


  ngAfterViewInit(): void {
    this.hot100();
  }


  hot100() {
    this.musicService.getTodayTopHitsPlaylist().subscribe({
      next: (v) => {
        this.spotifyPlaylist = v;
        this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
        this.dataSource.paginator = this.paginator;
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
  playTrack(item: Item) {
    // Implement your play track logic here
    const link = item.track.external_urls.spotify;
    window.open(link, '_blank');
  }
}
