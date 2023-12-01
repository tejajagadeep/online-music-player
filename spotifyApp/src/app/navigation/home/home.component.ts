import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SpotifyPlaylist } from 'src/app/model/SpotifyPlaylist';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  spotifyPlaylistBillBoard: SpotifyPlaylist | any;
  spotifyPlaylistHot100: SpotifyPlaylist | any;
  @ViewChild('cardsContainer') cardsContainer!: ElementRef;
  
  constructor( private route: ActivatedRoute, private musicService: MusicDataService) {}

  ngOnInit(): void {
    this.billBoard();
    this.hot100();
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
  
  hot100() {
    this.musicService.getTodayTopHitsPlaylist().subscribe({
      next: (v) => {
        this.spotifyPlaylistHot100 = v;
        console.log(v);
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
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

  
}
