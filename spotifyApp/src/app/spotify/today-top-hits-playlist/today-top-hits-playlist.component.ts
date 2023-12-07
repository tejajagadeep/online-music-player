import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Item } from 'src/app/model/Item';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';
import { heartAnimation } from 'src/app/app-parsers/animation-trigger';
import { PlayDialogService } from 'src/app/service/component/play-dialog.service';


@Component({
  selector: 'app-today-top-hits-playlist',
  templateUrl: './today-top-hits-playlist.component.html',
  styleUrls: ['./today-top-hits-playlist.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  animations: [heartAnimation]

})



export class TodayTopHitsPlaylistComponent implements AfterViewInit {


  spotifyPlaylist!: SpotifyPlaylist; // Adjust the type accordingly
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];

  trackIds: String[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['position', 'image', 'name', 'artist', 'duration', 'play', 'action'];

  heartStates: { [key: string]: string } = {};



  constructor(
    private musicService: MusicDataService,
    private wishList: WishlistDataService,
    private cdr: ChangeDetectorRef,
    private playDialogService: PlayDialogService
    ) { }

    openPlayDialog(trackId: string): void {
      this.playDialogService.openPlayDialog(trackId);
    }

  ngAfterViewInit(): void {
    this.todayTopHitsPlaylist();
    this.runcons();
    this.wishListTracks();
  }

  

  toggleHeartState(trackId: string): void {
    if (!this.trackIds.includes(trackId)) {
      if (this.heartStates[trackId] === 'active') {
        this.heartStates[trackId as any] = 'inactive';
      } else {
        this.heartStates[trackId as any] = 'active'
      }
      this.saveTrackToWishList(trackId);
    } else {
      if (this.heartStates[trackId] === 'inactive') {
        this.heartStates[trackId as any] = 'active';
      } else {
        this.heartStates[trackId as any] = 'inactive'
      }
      this.deleteTrackToWishList(trackId);
    }

  }

  getHeartState(trackId: string): string {
    if (this.trackIds.includes(trackId)) {
      return this.heartStates[trackId] || 'active';
    } else {
      return this.heartStates[trackId] || 'inactive';
    }
  }
  
  todayTopHitsPlaylist() {
    this.musicService.getTodayTopHitsPlaylist().subscribe({
      next: (v) => {
        this.spotifyPlaylist = v;
        console.log(v.tracks.items[0].added_at)
        this.cdr.detectChanges();
      },
      error: (e) => { console.error('e') },
      complete: () => {
        console.info('complete'),
        this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  runcons() {
    console.log(this.spotifyPlaylist)
  }

  saveTrackToWishList(id: string) {
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
      complete: () => { this.todayTopHitsPlaylist() }
    });
  }

  deleteTrackToWishList(id: string) {

    this.wishList.deleteTrackByUsernameAndTrackId(id).subscribe({
      next: (a) => {
        this.todayTopHitsPlaylist();
      },
      error: (e) => console.error(e),
      complete: () => { console.info('complete'); this.todayTopHitsPlaylist(); }
    });
  }


  wishListTracks() {
    this.wishList.getUserWishList().subscribe({
      next: (a) => {
        a.tracks.forEach(track => this.trackIds.push(track.id))
      },
      error: (e) => console.error(e),
      complete: () => console.log('tracks added to wishlist')
    })
  }

  playTrack(item: Item) {
    const link = item.track.external_urls.spotify;
    window.open(link, '_blank');
  }
}
