import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { heartAnimation } from 'src/app/app-parsers/animation-trigger';
import { SpotifyTracks } from 'src/app/model/SpotifyTracks';
import { Track } from 'src/app/model/Track';
import { PlayDialogService } from 'src/app/service/component/play-dialog.service';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';

@Component({
  selector: 'app-search-tracks',
  templateUrl: './search-tracks.component.html',
  styleUrls: ['./search-tracks.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  animations: [heartAnimation]
})
export class SearchTracksComponent implements AfterViewInit{

  playlistId!: string;
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];
  trackIds: String[] = [];
  heartStates: { [key: string]: string } = {};
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['position', 'image', 'name', 'artist', 'duration', 'play', 'action'];


  spotifyTracks!: SpotifyTracks; // Adjust the type accordingly
  searchQuery: string = '';
  query=''
  
  constructor(
    private route: ActivatedRoute, 
    private musicService: MusicDataService,
    private wishList: WishlistDataService,
    private cdr: ChangeDetectorRef,
    private playDialogService: PlayDialogService
    ) { }

    openPlayDialog(trackId: Track): void {
      let tracksList: Track[] = [];
      this.spotifyTracks.tracks.items.forEach(element => {
        tracksList.push(element)
      });

      this.playDialogService.openPlayDialog(trackId,tracksList);
    }

  ngAfterViewInit(): void {
    this.query = this.route.snapshot.params['query'];
    this.searchTracks(this.query)
    this.wishListTracks();
  }

  toggleHeartState(trackId: Track): void {
    if (!this.trackIds.includes(trackId.id)) {
      if (this.heartStates[trackId.id] === 'active') {
        this.heartStates[trackId.id as any] = 'inactive';
        this.deleteTrackToWishList(trackId.id);
      } else {
        this.heartStates[trackId.id as any] = 'active'
        this.saveTrackToWishList1(trackId);
      }
    } else {
      if (this.heartStates[trackId.id] === 'inactive') {
        this.heartStates[trackId.id as any] = 'active';
        this.saveTrackToWishList1(trackId);
      } else {
        this.heartStates[trackId.id as any] = 'inactive'
        this.deleteTrackToWishList(trackId.id);
      }
    }
  }

  getHeartState(trackId: string): string {
    if (this.trackIds.includes(trackId)) {
      return this.heartStates[trackId] || 'active';
    } else {
      return this.heartStates[trackId] || 'inactive';
    }
  }
  
  deleteTrackToWishList(id: string) {

    this.wishList.deleteTrackByUsernameAndTrackId(id).subscribe({
      next: (a) => {
        console.log('track deleted')
      },
      error: (e) => console.error(e),
      complete: () => { console.info('complete');  }
    });
  }

  saveTrackToWishList1(id: Track) {
    this.wishList.saveTrackToWishlist(id).subscribe({
      next: (a) => {
        console.log(a)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });;
  }


  wishListTracks(){
    this.wishList.getUserWishList().subscribe({
      next: (a) => {
        a.tracks.forEach(track => this.trackIds.push(track.id))
      },
      error: (e) => console.error(e),
      complete: () => console.log('tracks added to wishlist')
    })
  }

  redirectToLink(link: string) {
    // Navigate to the provided HTTPS link
    window.open(link, '_blank');
  }
  
  searchTracks(query: string) {
    this.musicService.searchTracks(query).subscribe({
      next: (v) => {
        this.spotifyTracks = v;
        this.spotifyTracks.tracks.items.forEach((track, index) => {
          track.index = index + 1;
        });
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

  playTrack(track: Track) {
    const link = track.external_urls.spotify
    // Implement your play track logic here
    window.open(link, '_blank');
  }
}
