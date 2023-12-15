import { AfterViewInit, ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { heartAnimation } from 'src/app/app-parsers/animation-trigger';
import { Item } from 'src/app/model/Item';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { Track } from 'src/app/model/Track';
import { PlayDialogService } from 'src/app/service/component/play-dialog.service';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';

@Component({
  selector: 'app-discover-weekly-playlist',
  templateUrl: './discover-weekly-playlist.component.html',
  styleUrls: ['./discover-weekly-playlist.component.css'],
  animations: [heartAnimation]
})
export class DiscoverWeeklyPlaylistComponent  implements AfterViewInit {


  spotifyPlaylist!: SpotifyPlaylist; // Adjust the type accordingly
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];

  trackIds: String[] = [];
  indexI!: number[];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['position', 'image', 'name', 'artist', 'duration', 'play', 'action'];

  heartStates: { [key: string]: string } = {};



  constructor(
    private musicService: MusicDataService,
    private wishList: WishlistDataService,
    private cdr: ChangeDetectorRef,
    private playDialogService: PlayDialogService,
  ) { }

  openPlayDialog(trackId: Track): void {
    let tracksList: Track[] = [];

    this.spotifyPlaylist.tracks.items.forEach(element => {
      tracksList.push(element.track)
    });

    this.playDialogService.openPlayDialog(trackId, tracksList);
  }

  ngAfterViewInit(): void {
    this.todayTopHitsPlaylist();
    this.wishListTracks();
  }


  toggleHeartState(trackId: Track): void {
    if (!this.trackIds.includes(trackId.id)) {
      if (this.heartStates[trackId.id] === 'active') {
        this.heartStates[trackId.id as any] = 'inactive';
      } else {
        this.heartStates[trackId.id as any] = 'active'
      }
      this.saveTrackToWishList1(trackId);
    } else {
      if (this.heartStates[trackId.id] === 'inactive') {
        this.heartStates[trackId.id as any] = 'active';
      } else {
        this.heartStates[trackId.id as any] = 'inactive'
      }
      this.deleteTrackToWishList(trackId.id);
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


  todayTopHitsPlaylist() {
    this.musicService.getDiscoverWeeklyPlaylist().subscribe({
      next: (v) => {

        this.spotifyPlaylist = v;
        console.log(v.tracks.items[0].added_at)
        this.cdr.detectChanges();
        this.spotifyPlaylist.tracks.items.forEach((track, index) => {
          track.track.index = index + 1;
        });
      },
      error: (e) => { console.error('e') },
      complete: () => {
        console.info('complete'),
          this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
        this.dataSource.paginator = this.paginator;
      }
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