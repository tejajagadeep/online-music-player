import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
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

  constructor(
    @Inject(MAT_DIALOG_DATA) public data:{id: string},
    private musicService: MusicDataService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.playSong(this.data.id);
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
