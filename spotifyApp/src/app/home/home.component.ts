import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { Track } from 'src/app/model/Track';
import { MusicDataService } from 'src/app/service/data/music-data.service';
import { WishlistDataService } from 'src/app/service/data/wishlist-data.service';
import { PlayDialogService } from '../service/component/play-dialog.service';
import { AuthenticationService } from '../service/data/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  spotifyPlaylistBillBoard: SpotifyPlaylist | any;
  spotifyPlaylistHot100: SpotifyPlaylist | any;
  @ViewChild('cardsContainer') cardsContainer!: ElementRef;

  tracks!: Track[];
  
  constructor(private authService: AuthenticationService ,private wishlist: WishlistDataService, private musicService: MusicDataService,
    private playDialogService: PlayDialogService
    ) { }

    openPlayDialog(trackId: Track): void {
      this.playDialogService.openPlayDialog(trackId);
    } 

  ngOnInit(): void {
    this.billBoard();
    this.wishlists();
    this.verifyToken();
  }

  verifyToken(){
    const token = localStorage.getItem('token')+'';
    this.authService.validateToken(token).subscribe({
      next: (v) => {
        console.log(v);
      },
      error: (e) => {console.error(e); localStorage.removeItem('token'), localStorage.removeItem('authenticatedUser')},
      complete: () => console.info('complete')
    });
  }

  billBoard() {
    this.musicService.billBoard100Playlist().subscribe(
      {
        next: (v) => {this.spotifyPlaylistBillBoard=v
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete') 
    }
    )
  }

  wishlists() {
    this.wishlist.getUserWishList().subscribe({
      next: (v) => {
        this.tracks = v.tracks;
      },
      error: (e) => console.error(e),
      complete: () => {console.info('complete')}
    });
  }

  
  
  scrollCards(direction: 'left' | 'right'): void {
    const container = this.cardsContainer.nativeElement;
    const scrollAmount = 300; // Adjust the scroll amount as needed

    if (direction === 'left') {
      container.scrollLeft -= scrollAmount;
    } else {
      container.scrollLeft += scrollAmount;
    }
  }
  playTrack(link: string) {
    window.open(link, '_blank');
  }
  
}
