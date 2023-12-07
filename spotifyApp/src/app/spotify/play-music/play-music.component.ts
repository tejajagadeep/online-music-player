import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Track } from 'src/app/model/Track';
import { MusicDataService } from 'src/app/service/data/music-data.service';

@Component({
  selector: 'app-play-music',
  templateUrl: './play-music.component.html',
  styleUrls: ['./play-music.component.css']
})
export class PlayMusicComponent implements OnInit {

  track!: Track;
  id!: string;

  constructor(private musicService: MusicDataService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.playSong(this.id);
    });
  }
  isPlaying: boolean = false;

  togglePlayPause() {
    this.isPlaying = !this.isPlaying;
  }

  playSong(trackId: string){
    this.musicService.getTrack(trackId).subscribe({
      next: (a) => {
        this.track = a;
      },
      error: (e) => console.error(e),
      complete: () => {console.info('complete');}
    });
  }

}
