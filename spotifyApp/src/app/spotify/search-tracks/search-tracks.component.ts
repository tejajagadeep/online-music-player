import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { SpotifyTracks } from 'src/app/model/SpotifyTracks';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';

@Component({
  selector: 'app-search-tracks',
  templateUrl: './search-tracks.component.html',
  styleUrls: ['./search-tracks.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SearchTracksComponent implements AfterViewInit{

  playlistId!: string;
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['position', 'image', 'name', 'artist', 'duration', 'play', 'action'];


  spotifyTracks!: SpotifyTracks; // Adjust the type accordingly
  searchQuery: string = '';
  
  constructor(
    private musicService: MusicDataService,
    private wishList: WishlistDataService,
    private cdr: ChangeDetectorRef) {}

  ngAfterViewInit(): void {
    this.searchTracks("leo");
  }

  redirectToLink(link: string) {
    // Navigate to the provided HTTPS link
    window.open(link, '_blank');
  }
  
  searchTracks(query: string) {
    this.musicService.searchTracks(query).subscribe({
      next: (v) => {
        this.spotifyTracks = v;
        this.cdr.detectChanges();
      },
      error: (e) => console.error(e),
      complete: () => {console.info('complete'),
      this.dataSource = new MatTableDataSource(this.spotifyTracks.tracks.items);
      this.dataSource.paginator = this.paginator;}
    });
  }

  saveTrackToWishList(id: string){

    this.musicService.getTrack(id).subscribe({
      next: (v) => {
        this.wishList.saveTrackToWishlist(v).subscribe({
          next: (a) => {
            console.log(a)
          },
          error: (e) => console.error(e),
          complete: () => console.info('complete')
        });;
        this.dataSource = new MatTableDataSource(this.spotifyTracks.tracks.items);
        this.dataSource.paginator = this.paginator;
        console.log(v.name)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }

  playTrack(link: URL) {
    // Implement your play track logic here
    window.open(link, '_blank');
  }
}
