import { AfterViewInit, ChangeDetectorRef, Component, Renderer2, ViewChild } from '@angular/core';
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
  selector: 'app-bill-board100-playlist',
  templateUrl: './bill-board100-playlist.component.html',
  styleUrls: ['./bill-board100-playlist.component.css'],
  animations: [heartAnimation]
})
export class BillBoard100PlaylistComponent implements AfterViewInit {


  spotifyPlaylist!: SpotifyPlaylist; // Adjust the type accordingly
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];
  trackIds: String[] = [];
  heartStates: { [key: string]: string } = {};
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['position', 'image', 'name', 'artist', 'duration', 'play', 'action'];


  constructor(
    private musicService: MusicDataService,
    private wishList: WishlistDataService,
    private cdr: ChangeDetectorRef,
    private playDialogService: PlayDialogService
    ) { }

    openPlayDialog(trackId: Track): void {
      let tracksList: Track[] = [];

      this.spotifyPlaylist.tracks.items.forEach(element => {
        tracksList.push(element.track)
      });

      this.playDialogService.openPlayDialog(trackId,tracksList);
    }

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {
    this.todayTopHitsPlaylist();
    this.wishListTracks()
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

  printFavorites(id: string) {
    let tracks1 = ['123','123','234']
    console.log(id);
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


  todayTopHitsPlaylist() {
    this.musicService.billBoard100Playlist().subscribe({
      next: (v) => {
        this.spotifyPlaylist = v;
        console.log(v.tracks.items[0].added_at);
        this.spotifyPlaylist.tracks.items.forEach((track, index) => {
          track.track.index = index + 1;
        });
        this.cdr.detectChanges();
      },
      error: (e) => {console.error('e')},
      complete: () => {console.info('complete'),
      this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
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
        this.dataSource = new MatTableDataSource(this.spotifyPlaylist.tracks.items);
        this.dataSource.paginator = this.paginator;
        console.log(v.name)
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }


  playTrack(item: Item) {
    // Implement your play track logic here
    const link = item.track.external_urls.spotify;
    window.open(link, '_blank');
  }
}
