import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/model/Item';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';


@Component({
  selector: 'app-today-top-hits-playlist',
  templateUrl: './today-top-hits-playlist.component.html',
  styleUrls: ['./today-top-hits-playlist.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})



export class TodayTopHitsPlaylistComponent implements AfterViewInit {


  spotifyPlaylist!: SpotifyPlaylist; // Adjust the type accordingly
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['position', 'image', 'name', 'artist', 'duration', 'play', 'action'];

  isExists: boolean = false;

  isSmallScreen = true;

ngOnInit() {
  this.breakpointObserver.observe([Breakpoints.Handset, Breakpoints.Small])
    .subscribe(result => {
      this.isSmallScreen = result.matches;
    });
}

  
  constructor(private route: ActivatedRoute, 
    private musicService: MusicDataService,
    private wishList: WishlistDataService,
    private cdr: ChangeDetectorRef,
    private breakpointObserver: BreakpointObserver) { }


  ngAfterViewInit(): void {
    this.todayTopHitsPlaylist();
    this.favoriteIsExists("4KULAymBBJcPRpk1yO4dOG")
    // setTimeout(() => {
    //   this.afterDataLoaded();
    // }, 1000);
    this.runcons();
  }

  todayTopHitsPlaylist() {
    this.musicService.getTodayTopHitsPlaylist().subscribe({
      next: (v) => {
        this.spotifyPlaylist = v;
        console.log(v.tracks.items[0].added_at)
        this.cdr.detectChanges();
      },
      error: (e) => {console.error('e')},
      complete: () => {console.info('complete'),
      this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
      this.dataSource.paginator = this.paginator;}
    });
  }

  runcons(){
    console.log(this.spotifyPlaylist)
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
        
        console.log(v.name)
      },
      error: (e) => console.error(e),
      complete: () => {console.info('complete'), this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
      this.dataSource.paginator = this.paginator;}
    });
  }

  deleteTrackToWishList(id: string) {

    this.wishList.deleteTrackByUsernameAndTrackId(id).subscribe({
      next: (a) => {
        this.todayTopHitsPlaylist();
      },
      error: (e) => console.error(e),
      complete: () => {console.info('complete'); this.todayTopHitsPlaylist();}
    });
  }

  favoriteIsExists(trackId: string){
    this.wishList.favoriteExists(trackId).subscribe({
      next: (a) => {
        this.isExists =a;
        console.log(a);
        this.cdr.detectChanges();
      },
      error: (e) => console.error(e),
      complete: () => this.isExists
    })
    return this.isExists;
    
  }

  playTrack(item: Item) {
    const link = item.track.external_urls.spotify;
    window.open(link, '_blank');
  }
}
