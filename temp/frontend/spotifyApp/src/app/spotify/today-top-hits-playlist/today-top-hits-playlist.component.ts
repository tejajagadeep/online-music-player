import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { SpotifyPlaylist } from '../../model/SpotifyPlaylist';
import { MusicDataService } from '../../service/data/music-data.service';
import { CommonModule } from '@angular/common';
import { MillisecondsToMinutesPipe } from "../../app-pipes/milliseconds-to-minutes.pipe";

@Component({
    selector: 'app-today-top-hits-playlist',
    standalone: true,
    templateUrl: './today-top-hits-playlist.component.html',
    styleUrls: ['./today-top-hits-playlist.component.css'],
    imports: [CommonModule, 
      MillisecondsToMinutesPipe, 
    ]
})



export class TodayTopHitsPlaylistComponent implements AfterViewInit {


  spotifyPlaylist!: SpotifyPlaylist; // Adjust the type accordingly
  dataSource: MatTableDataSource<any> | undefined;
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];
  displayedColumns= ['index', 'name', 'artists', 'duration', 'action']


  @ViewChild(MatPaginator) paginator!: MatPaginator;

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
        this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
        this.dataSource.paginator = this.paginator;
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
